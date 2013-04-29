package cycle;

import java.util.List;
import java.util.regex.*;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import com.shin.login.model.*;

@SuppressWarnings("serial")
public class BoardCycleServlet extends HttpServlet {
	
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	doGet(request, response);
    		//�놁쑝硫��ㅻ쪟�섍만���덉��닿굅
    	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8"); //�쒓�源⑥쭚諛⑹�
		
		if(true) {
			getList(request, response); // �섏쨷���섎닠蹂쇰씪怨��쇰떒 硫붿냼�쒕줈 留뚮뱾�대큵
		}
	}
    



public void getList(HttpServletRequest request, HttpServletResponse response) throws IOException {
	//寃뚯떆��湲�ぉ濡�諛쏆븘�ㅻ뒗嫄�
	//String boardName = "UpdateNews";		//�섏쨷��由ы��ㅽ듃�뚮씪誘명꽣 �댁꽌 �대쫫 諛쏆븘�ш굅��
	String boardName = request.getParameter("boardName");
	PersistenceManager pm = PMF.get().getPersistenceManager();
	Query query = pm.newQuery(Board.class);
	query.setFilter("boardName == boardNameParam"); //寃뚯떆���대쫫�쇰줈 �꾪꽣�앹꽦
	query.declareParameters("String boardNameParam");

	try {
		@SuppressWarnings("unchecked")
		List <Board> results = (List<Board>)  query.execute(boardName); //寃뚯떆���대쫫�쇰줈 �꾪꽣留�
		if(results.size()>0) 
		{	request.setAttribute("test_board", results); //怨꾩냽 李얠븘��肉뚮┝
		}
		String url = "/notice/"+boardName+".jsp"; //�뚯븘媛�(�닿쾬���섏쨷��+boardName �대윴嫄��섎㈃���뚮젆�쒕툝�섍쾶 �좉굅
		try {
		request.getRequestDispatcher(url).forward(request, response);
		} catch (ServletException e) {
		e.printStackTrace();      	
	}
	}finally {
		query.closeAll();
	}
	 return;
}

}
