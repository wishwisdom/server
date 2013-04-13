package com.shin.login;


import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.shin.login.Util.Encrypt;
import com.shin.login.Util.PMF;
import com.shin.login.model.Person;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	private PersistenceManager pm = PMF.get().getPersistenceManager();
	private static Logger log = Logger.getLogger(LoginServlet.class.getName());
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		//Login 화면이기 때문에 기존에 존재하는 
		//id와 password를 체크하여 로그인 여부를 알려준다.
		String id = req.getParameter("login");
		String pwd = Encrypt.encrypt(req.getParameter("password"));
		boolean check = false;
		
		check = checkUser(id,pwd);
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("utf-8");
		//login에 실패했을 경우
		if(!check){
			resp.getWriter().print("<p>로그인 실패</p>");
			log.info(id+"login error");
			try {
				Thread.sleep(2000);
				resp.sendRedirect("/");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				log.log(Level.SEVERE, "Thread Error");
				e.printStackTrace();
			}
			resp.sendRedirect("/");
		}else{
			if(req.getParameter("login").equalsIgnoreCase("admin")){
				log.info("admin enter!!!");
				//admin만 사용할 수 있는 곳으로 보낸다.
			}
			log.info("session created!!");
			check=false;
			HttpSession session = req.getSession();
			session.setAttribute("loginID", id);
			session.setMaxInactiveInterval(1000);
			resp.sendRedirect("/main");
		}
		
		
	}

	private boolean checkUser(String id, String pwd){
		try{
			if(id.isEmpty()||pwd.isEmpty()){
				return false;
			}else{
				if(pm.isClosed()){
					pm = PMF.get().getPersistenceManager();
				}
				Query q = pm.newQuery(Person.class);
				q.setFilter("id == id_ && password == pwd");
				q.declareParameters("String id_ , String pwd");
				//resp.getWriter().print("NO,NO,NO");
				List<Person> p = (List<Person>)q.execute(id,pwd);
				if(p.isEmpty()){
					//Dialog를 띄어 회원 가입을 하라고 하거나 login화면으로 다시 넘어가게 한다.
					
					//resp.getWriter().print("NO,NO,NO");
					//resp.sendRedirect("/");
					return false;
				}
			}
		}finally{
			pm.close();
		}
		return true;
	}
}
