package com.shin.login;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateDataSevlet extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		//들어오는 정보가 자전거에게서 오는 것인지 확인한다.
		
		//들어오는 자전거의 이름과 매치하는 것에 정보를 업데이트 한다.
		
		//없는 것이면 response를 보내준다.그리고 로그에 남긴다.
	}
}
