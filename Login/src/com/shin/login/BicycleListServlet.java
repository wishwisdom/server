package com.shin.login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shin.login.Util.PMF;
import com.shin.login.model.Bicycle;


@SuppressWarnings("serial")
public class BicycleListServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		List<Bicycle> result = getBicycleList();
		List<Bicycle> empty = new ArrayList<Bicycle>();
		List<Bicycle> used = null;
		
		if(result!=null){ 
			for(int i=0; i < result.size(); i++){
				if(result.get(i).getUsed() == 'a'){
					empty.add(result.get(i));
				}else{
					used.add(result.get(i));
				}
			}
		}
		String path = req.getPathInfo().toString();

		if(path.equals("/empty/")){
			if(empty != null){
				String regex = "()";
				for(int i = 0; i < empty.size(); i++){
					empty.get(i).setGeopoint(empty.get(i).getGeopoint().replaceAll(regex, ""));
				}
				req.setAttribute("emp", empty);
			}
				String url = "/templates/bicycle_used.jsp";
				try {
					req.getRequestDispatcher(url).forward(req, resp);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		if(path.equals("/new/")){
			resp.sendRedirect("/templates/bicycle_new.jsp");
		}
		if(path.equals("/all/")){
			postAll(req,resp);
		}
		
		
		return;
	}
	
	public void postAll(HttpServletRequest req, HttpServletResponse resp){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String query = "SELECT FROM "+Bicycle.class.getName();
		System.out.println("kdjflsd");
		
		try{
			List<Bicycle> s = (List<Bicycle>) pm.newQuery(query).execute(); 
			if(s.size() > 0){
				req.setAttribute("bicycle", s);
			}
			String url = "/templates/cycle/map.jsp";
			try{
				req.getRequestDispatcher(url).forward(req, resp);
			}catch(ServletException e){
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}finally{
			pm.close();
		}
		return;
	}
	
	public List<Bicycle> getBicycleList(){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Query q = pm.newQuery(Bicycle.class);
		List<Bicycle> result = (List<Bicycle>) q.execute();
		if(result.isEmpty()){
			return null;
		}
		
		return result;
	}
	public void postModify(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// TODO Auto-generated method stub
		/*UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if(user == null){
			resp.sendRedirect("/user/login/");
			return;
		}*/
		
		String delem = "/";
		String[] path = req.getPathInfo().toString().split(delem);
		
		Bicycle s = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			s = pm.getObjectById(Bicycle.class,Long.parseLong(path[1]));
				s.setBicycle(req.getParameter("bicycle"));
				if(req.getParameter("geo_point").length() > 0){
					String regex = "[()]";
					String geoPoint = req.getParameter("geo_point").replaceAll(regex, "");
					s.setGeopoint(geoPoint);
				}
				pm.makePersistent(s);
				resp.sendRedirect("/bicycle/"+s.getKey().getId()+"/read/");
		}finally{
			pm.close();
		}
		return;
	}

	public void postNew(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// TODO Auto-generated method stub
		/*UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if(user == null){
			resp.sendRedirect(userService.createLoginURL("/user/login"));
			return;
		}*/
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Bicycle shop = new Bicycle(req.getParameter("bicycle"),req.getParameter("geo_point"));
		if(req.getParameter("geo_point").length() > 0){
			String reqex = "[()]";
			String geoPoint = req.getParameter("geo_point").replace(reqex, "");
			shop.setGeopoint(geoPoint);
		}
		try{
			pm.makePersistent(shop);
		}finally{
			pm.close();
		}
		
		resp.sendRedirect("/bicycle/"+shop.getKey().getId()+"/read/");
		return;
	}
}
