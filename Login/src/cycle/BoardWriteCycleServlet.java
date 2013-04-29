package cycle;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shin.login.model.Board;

import java.io.IOException;
import java.util.Date;

@SuppressWarnings("serial")
public class BoardWriteCycleServlet extends HttpServlet {
	//湲�벐湲��쒕툝��- ��땐 ���앸궃��
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	//�먭쿊���놁쑝硫��ㅻ쪟�섍만���쇰떒 吏묒뼱�덈큵
	}
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	//�덈줈�닿� ��옣
		String boardName = request.getParameter("boardName");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String user = request.getParameter("user");
    	Board board = new Board (boardName, subject, content, user);
        Date date = new Date(); 
        board.setPosttime(date); //�좎쭨��옣
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
        	pm.makePersistent(board); //�붾퉬��옣
        } finally {
        	pm.close();
        }
         response.sendRedirect("/board"); //�뚯븘媛�
        return;
        }
}
