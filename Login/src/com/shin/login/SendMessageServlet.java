package com.shin.login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mortbay.log.Log;

import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.MessageBuilder;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;
import com.shin.login.Util.PMF;
import com.shin.login.model.Bicycle;

public class SendMessageServlet extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		XMPPService xmpp = XMPPServiceFactory.getXMPPService();
		
		messageReceive(req,resp);
	}
	
	
	/*
	 * void messageReceive(HttpServletRequest req, HttpServletResponse resp)
	 * param	: 
	 * 		HttpServletRequest req: Request from a user on web or on his phone.
	 * 		HttpServletResponse resp: The Server Will response on user's request/
	 * return: None
	 * description:
	 * 		Give you several selection. 
	 * 		1. reserve
	 * 		2. Use
	 * 		3. Request all of the bicycle location. 
	 */
	public void messageReceive(HttpServletRequest req,HttpServletResponse resp){
		XMPPService xmpp = XMPPServiceFactory.getXMPPService();

		try {
			Message message = xmpp.parseMessage(req);
			JID fromJid = message.getFromJid();
			String msgBody = null;
			//받은 메시지가 어떤 종류인지 확인한다.
			/*
			 * message form
			 * #,tag
			 */
			String[] mess = message.getBody().toString().split(",");
			int sel = Integer.parseInt(mess[0]);
			//1. 예약을 위한 것인가?
			//2. 사용을 위한 것인가?
			//3. 반납을 위한 것인가?
			//4. 자전거의 위치를 원하는 것인가?
			switch(sel){
			case 1:
				updateBicycle(mess[1],'c');
				break;
			case 2:
				updateBicycle(mess[1],'b');
				break;
			case 3:
				updateBicycle(mess[1],'a');
				break;
				
			default:
				//error!!!
				Log.info("XMPP Select", "App에서 잘못된 내용을 전달했다:"+message.getBody().toString());
				//Send an error message.
			}
			
			msgBody = sendBicycleInfo();
			
			Message msg;
			if(xmpp.getPresence(fromJid).isAvailable()){
				//Send an error message 
				if(msgBody.isEmpty()){
					msgBody = "E0 No Bicycle Data";
					Log.info("XMPP", "Empty Result");
					msg = new MessageBuilder().withRecipientJids(fromJid).withBody(msgBody).build();
				}else
					msg = new MessageBuilder().withRecipientJids(fromJid).withBody(msgBody).build();
				xmpp.sendMessage(msg);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * String sendBicycleInfo()
	 * param: None
	 * return:
	 * 		String 	: return bicycles info (location, name, use)
	 * description:
	 *		make a message including bicycles info( location, name, use)
	 *		and return the message 		 
	 */
	private String sendBicycleInfo()
	{
		List<Bicycle> result = null;
		String msg = "";
		
		result = getBicycleList();
		
		if(result.isEmpty()){
			Log.info("SendMessageServlet","result is empty");
			return msg;
		}
		
		for(int i=0; i < result.size();i++){
			msg += result.get(i).getKey().getId()+","+result.get(i).getUsed()+","+result.get(i).getGeopoint()+";";
		}
		
		return msg;
	}
	/*
	 * String updateBicycle()
	 * param:
	 * 		bTag	: Indentification for a bicycle
	 * 		state	: exchange current bicycle' status with state.
	 * return:
	 * 	String msg	: If it is available for user's reservation, 
	 * 					To server: check its reservation on bicycle
	 * 								store the bicycle and the user information
	 * 					To user:	send messages about bookable or not.
	 * 
	 * description:
	 * 	It is a purpose that the method make a reservation for the selected bicycle.
	 *  It should check out some users can't reserve of use the bicycle  except for the user. 
	 *  지금 선택된 자전거를 예약하기 위한 메소드
	 *  다른 유저가 자전거 예약을 하거나 사용할 수 없게 한다.
	 *  다른 유저가 예약하거나 사용하고 있으면, 예약할 수 없다는 메시지를 보낸다.
	 */
	private void updateBicycle(String bTag, char state)
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Bicycle b = (Bicycle)pm.getObjectById(bTag);
		
		if(state == 'c'){
			if(b.getUsed() == 'a'){
				b.setUsed('c');
				pm.close();
				//return "O";
			}else{
				//return "N";
			}
		}else if( state == 'b'){
			//return "N";
		}else{
			b.setUsed('a');
			pm.close();
			//return "E";
		}
	}

	/*
	 * List<Bicycle> getBicycleList()
	 * param: None
	 * return:
	 * 		List<bicycle>: return results from the datastore, whitch are about 
	 * 						bicycles.
	 * description:
	 * 	It gives all of the bicycle typed by List<Bicycle> to requester 
	 */
	public List<Bicycle> getBicycleList(){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Query q = pm.newQuery(Bicycle.class);
		List<Bicycle> result = (List<Bicycle>) q.execute();
		if(result.isEmpty()){
			return null;
		}
		return result;
	}
}
