package cycle;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shin.login.model.Member;

import java.io.IOException;
import java.util.Date;
import cycle.PMF;

@SuppressWarnings("serial")
public class MemberSaveCycleServlet extends HttpServlet {
	//湲�벐湲��쒕툝��- ��땐 ���앸궃��
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8"); //�쒓�源⑥쭚諛⑹�
    	//�먭쿊���놁쑝硫��ㅻ쪟�섍만���쇰떒 吏묒뼱�덈큵
	}
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	response.setContentType("text/html;charset=utf-8"); //�쒓�源⑥쭚諛⑹� 	
    	//�덈줈�닿� ��옣
		String id = request.getParameter("id");
		String password = request.getParameter("pwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String mobile = request.getParameter("mobile");		
    	Member member = new Member (id, password);
    	member.setName(name);
    	member.setEmail(email);
    	member.setAddress(address);
    	member.setPhone(phone);
    	member.setMobile(mobile);
    	Date date = new Date(); 
        member.setInDate(date); //�좎쭨��옣
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
        	pm.makePersistent(member); //�붾퉬��옣
        } finally {
        	pm.close();
        }
         response.sendRedirect("/index.jsp"); //�뚯븘媛�
        return;
        }
}
