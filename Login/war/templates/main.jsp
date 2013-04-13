<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Welcome, Trace Bicycles</title>
</head>
<body>
	<% 
	if(session.getAttribute("loginID") == null){
		response.sendRedirect("/");
	}
%>
	<div id="logo">Trace Bicycles</div>
	<div id="wrap">
		<header>
			<div id="logout">
				<a href="/templates/logout.jsp">LogOut</a>
			</div>
		</header>
	</div>
	<div id="menu">
		Home
		<%
			if(session.getAttribute("loginID").toString().equals("admin")){
		%>
		<a href="/bicycle/new">Create</a>
		<%
			}
		%>
	</div>
	<div id="content">
		Hell, <br\> Search bicycles.<br> <br\> <b>[Trace
			Bicycles]</b>
		<ul>
			<li><a href="/bicycle/empty/">Show empty bicycles</a></li>
			<li><a href="/bicycle/used/">Show used bicycles</a></li>
			<li><a href="/bicycle/all/">Show all bicycles</a></li>
			<%
					if(session.getAttribute("loginID").equals("admin")){
				%>
			<li><a href="/bicycle/modify/">Modify bicycles</a></li>
			<li><a href="/bicycle/new/">Insert a new bicycle</a></li>
			<%
					}
				%>
		</ul>
	</div>
</body>
</html>