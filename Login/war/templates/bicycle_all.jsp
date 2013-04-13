<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page import="java.util.List"%>
<%@ page import="com.shin.login.model.*" %>
<% 
	if(session.getAttribute("loginID") == null){
		response.sendRedirect("/");
	}
	List<Bicycle> b = (List<Bicycle>)request.getAttribute("bicycle");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html charset=utf-8">
<title>Welcome, Trace Bicycles</title>
</head>
<body>
	<div id="logo">Trace Bicycles</div>
	<div id="menu">
		Home <a href="/bicycle/new/">Create</a>
	</div>
	<div id="content">
		Hell, <br\> Search bicycles.<br> <br\> <b>[Trace
			Bicycles]</b>
		<div id="map" style="width: 450px; height: 250px"></div>
		<ul>
				<c:forEach items="${bicycle }" var ="s">
					<c:out value="${s.bicycle }"/>
				</c:forEach>
			</ul>
	</div>
</body>

<script type="text/javascript"
	src="https://maps.google.com/maps/api/js?sensor=false">
</script>
<script src="http://www.google.com/jsapi"></script>
<script type="text/javascript">google.load("jquery","1");</script>
<script type="text/javascript">

var map = null;
var mgr = null;

function initialize(){
	var centerlocation = new google.maps.LatLng(36.10309, 129.388447);
	var mapOptions = {
			zoom: 16,
			center:centerlocation,
			mapTypeId:google.maps.MapTypeId.ROADMAP
	};
	map = new google.maps.Map(document.getElementById("map"),mapOptions);
 	var mark = [];
 	var location = [];
 	var loc;
	<%for(int i=0;i<b.size();i++){%>
		var loc = new String("<%=b.get(i).getGeopoint()%>");
		var title = new String("<%=b.get(i).getBicycle()%>");
		location = loc.split(",");
		addMarker(new google.maps.LatLng(location[0],location[1]),title);
	<%}%> 
}

function addMarker(location,title){
	marker = new google.maps.Marker({
		position: location,
		title: ""+title,
		map:map
	});
}

$(document).ready(function(){
	initialize();
});
$(document).unload(function(){
	GUnload();
});

</script>
</html>
