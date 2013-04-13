package com.shin.login;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.shin.login.Util.PMF;
import com.shin.login.model.Bicycle;

@SuppressWarnings("serial")
public class RecieveGPSServlet extends HttpServlet{
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)throws IOException{
		/*
		 * gps의 이름이 기존에 존재하는 것인지 확인한다.->test를 위해 확인하지 않는다.
		 * gps의 좌표가 유효한지 체크한다.
		 * 	유효하다면 저장한다.
		 * 	유효하지 않다면 저장하지 않는다.
		 */
		postGeoPoint(req.getParameter("bicycle"),req.getParameter("geopoint"));
	}
	
	private void postGeoPoint(String bicycle, String geopoint)throws IOException{
		
		Bicycle s = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		//List<BicycleBean> result = (List<BicycleBean>)pm.newQuery("SELECT FROM BicycleBean").execute();
		try{
			javax.jdo.Query q = pm.newQuery(Bicycle.class);
			q.setFilter("bicycle == bicycle_");
			q.declareParameters("String bicycle_");
			List<Bicycle> results = (List<Bicycle>)q.execute(bicycle);
			s = results.get(0);
			s.setBicycle(bicycle);
			if(geopoint.length() > 0){
				//String regex = "[()]";
				s.setGeopoint(geopoint);
			}
			pm.makePersistent(s);
		}finally{
			pm.close();
		}
		return; 
	}

}
