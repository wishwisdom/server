package cycle;

import java.util.List;
import java.lang.String;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;

import com.shin.login.model.Member;

import cycle.PMF;
import java.io.IOException;

@SuppressWarnings("serial")
public class MemberCheckCycleServlet extends HttpServlet {
	
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	//�꾨뵒 媛�諛쏆�嫄�洹몃�濡�doGet�쇰줈 蹂대깂
    	doGet(request, response);
        }	
    
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//寃뚯떆���댁슜 異쒕젰 �쒕툝由�
	HttpSession session = request.getSession(); //�몄뀡�몄뀡
	response.setContentType("text/html;charset=utf-8"); //�쒓�源⑥쭚諛⑹�
	String id = request.getParameter("id");		//�꾨뵒諛쏄퀬
	String password = request.getParameter("pwd");		//鍮꾨쾲諛쏄퀬
	PersistenceManager pm = PMF.get().getPersistenceManager();
	Query query = pm.newQuery(Member.class);
	query.setFilter("id == idParam"); //�꾨뵒�꾪꽣荑쇰━�좊즺
	query.declareParameters("String idParam");
	try {
		@SuppressWarnings("unchecked")
		List<Member> results = (List<Member>) query.execute(id);//results濡���옣
		//�꾩씠�붽��덇퀬 鍮꾨쾲��媛숆퀬 �쇰컲�좎��쇰븣
		if( results.size() > 0 && results.get(0).getPassword().equals(password) && results.get(0).getAuthority().equals("user") ) {
			session.setAttribute("auth",results.get(0).getAuthority());
			session.setAttribute("id",results.get(0).getId()); //�몄뀡蹂대궡蹂대궡
			response.sendRedirect("../index.jsp"); //�뚯븘媛�
		}
		//�꾩씠�붽��덇퀬 鍮꾨쾲�닿컳怨�愿�━�먯씪��
		else if (results.size() > 0 && results.get(0).getPassword().equals(password) && results.get(0).getAuthority().equals("admin")) {
			session.setAttribute("auth", results.get(0).getAuthority());
			session.setAttribute("id",results.get(0).getId()); //�몄뀡蹂대궡蹂대궡
	        response.sendRedirect("../index.jsp"); //�뚯븘媛�
		}
		//爰쇱죲��
		else {
	         response.sendRedirect("../member/login.jsp"); //�뚯븘媛�
		}
		
	}finally {
	}
	 return;
}
}
