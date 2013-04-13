package com.shin.login;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shin.login.Util.PMF;
import com.shin.login.model.Bicycle;

public class MainServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String query = "SELECT FROM "+Bicycle.class.getName();
		
		try{
			List<Bicycle> s = (List<Bicycle>) pm.newQuery(query).execute(); 
			if(s.size() > 0){
				req.setAttribute("bicycle", s);
			}
			String url = "/templates/main.jsp";
			try{
				req.getRequestDispatcher(url).forward(req, resp);
			}catch(ServletException e){
				e.printStackTrace();
			}
		}finally{
			pm.close();
		}
		return;
	}
}
