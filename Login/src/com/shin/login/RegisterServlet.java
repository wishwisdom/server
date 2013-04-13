package com.shin.login;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shin.login.Util.Encrypt;
import com.shin.login.Util.PMF;
import com.shin.login.model.Person;

@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {
	private PersistenceManager pm = PMF.get().getPersistenceManager();
	private static Logger log = Logger.getLogger(LoginServlet.class.getName());
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp){
		String pwd = Encrypt.encrypt(req.getParameter("pwd1"));
		String id = req.getParameter("id").toString();
		String gmail = req.getParameter("gmail").toString();
		String name =req.getParameter("name").toString();;
		String birth = req.getParameter("birth").toString();
		String phone = req.getParameter("phone").toString();
		if(registUser(id,pwd,gmail,name,birth,phone)){
			try {
				resp.sendRedirect("/");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				log.info(req.getParameter("id").toString());
				e.printStackTrace();
			}
		}
	}
	
	private boolean registUser(String id,String pwd,String gmail,String name, String bir, String phone){
			if(id.isEmpty()||pwd.isEmpty() || gmail.isEmpty() || name.isEmpty() || phone.isEmpty()){
				return false;
			}else{
				if(pm.isClosed()){
					pm = PMF.get().getPersistenceManager();
				}
				Query q = pm.newQuery(Person.class);
				q.setFilter("id == id_ ");
				q.declareParameters("String id_");
				//resp.getWriter().print("NO,NO,NO");
				List<Person> p = (List<Person>)q.execute(id);
				if(p.isEmpty()){
					PersistenceManager pm = PMF.get().getPersistenceManager();
					Person shop = new Person(id,pwd,gmail,name,bir,phone);
					try{
						pm.makePersistent(shop);
					}finally{
						pm.close();
					}
					return true;
				}
			}
		return false;
	}
}
