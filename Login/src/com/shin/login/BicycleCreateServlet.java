package com.shin.login;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mortbay.log.Log;

import com.shin.login.Util.PMF;
import com.shin.login.model.Bicycle;
import com.shin.login.model.Person;

@SuppressWarnings("serial")
public class BicycleCreateServlet extends HttpServlet{
	private Bicycle biNew;
	private PersistenceManager pm = PMF.get().getPersistenceManager();
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp){
		
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String name = req.getParameter("bicycle");
		String geoPoint = req.getParameter("geo_point").replaceAll("[() ]", "");
		if(req.getSession().getAttribute("loginID").equals("admin")){
			setBicycle(name,geoPoint);
			resp.sendRedirect("/main");
		}else
			resp.sendRedirect("/");
	}
	
	private void setBicycle(String name, String geo){
		biNew = new Bicycle(name,geo);
		biNew.setUsed('a');
		
		if(pm.isClosed()){
			pm = PMF.get().getPersistenceManager();
		}
		try{
			pm.makePersistent(biNew);
		}
		finally{
			pm.close();
		}
	}
}
