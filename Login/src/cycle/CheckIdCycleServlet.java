package cycle;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shin.login.model.Member;

import cycle.PMF;
import java.io.IOException;

@SuppressWarnings("serial")
public class CheckIdCycleServlet extends HttpServlet {
	
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	//id媛�諛쏆�嫄�洹몃�濡�doGet�쇰줈 蹂대깂
    if(request.getParameter("id")!=null)
    	doGet(request, response);
    }	
    
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//寃뚯떆���댁슜 異쒕젰 �쒕툝由�
	response.setContentType("text/html;charset=utf-8"); //�쒓�源⑥쭚諛⑹�
	String id = request.getParameter("id"); //�꾩씠��諛쏄퀬
	PersistenceManager pm = PMF.get().getPersistenceManager();
	Query query = pm.newQuery(Member.class); //荑쇰━留뚮벉
	query.setFilter("id == idParam");	//�꾩씠���덈뒗吏�泥댄겕�섎뒗嫄몃줈
	query.declareParameters("String idParam");
	String url = "/member/join.jsp"; //�ш린濡�蹂대깂
	try {
		@SuppressWarnings("unchecked")
		List<Member> results = (List<Member>) query.execute(id);
		if(!results.isEmpty())		{
		    request.setAttribute("id_check", "fail"); //�꾩씠��以묐났
		    request.setAttribute("id", ""); //�꾩씠��null媛�蹂대깂
			try {
				request.getRequestDispatcher(url).forward(request, response);
				} catch (ServletException e) {
				e.printStackTrace();   
				}
		}
		else {
			request.setAttribute("id_check","success"); // �꾩씠���놁쓬. �몄쨷蹂�
			request.setAttribute("id", id); //�꾩씠�붽컪 蹂대깂
			try {
			request.getRequestDispatcher(url).forward(request, response);
			} catch (ServletException e) {
			e.printStackTrace();   
			}
		}
	}finally {
	}
	 return;
}
}