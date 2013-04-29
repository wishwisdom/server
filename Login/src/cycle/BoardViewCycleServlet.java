package cycle;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shin.login.model.Board;

import cycle.PMF;
import java.io.IOException;

@SuppressWarnings("serial")
public class BoardViewCycleServlet extends HttpServlet {
	
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	//seq 媛�諛쏆�嫄�洹몃�濡�doGet�쇰줈 蹂대깂
    	doGet(request, response);
        }	
    
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//寃뚯떆���댁슜 異쒕젰 �쒕툝由�
	response.setContentType("text/html;charset=utf-8"); //�쒓�源⑥쭚諛⑹�
	Long seq = Long.parseLong(request.getParameter("seq"));		//寃뚯떆臾�踰덊샇 諛쏄퀬
	PersistenceManager pm = PMF.get().getPersistenceManager();
	try {
		Board results = pm.getObjectById(Board.class, seq); //seq媛�媛숈�嫄���results濡���옣
		request.setAttribute("test_board", results); //肉뚮┝
		String url = "/notice/update_news_view.jsp"; //�ш린濡�蹂대깂
		try {
		request.getRequestDispatcher(url).forward(request, response);
		} catch (ServletException e) {
		e.printStackTrace();      	
	}
	}finally {
	}
	 return;
}
}
