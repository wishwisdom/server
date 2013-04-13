<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page import="java.util.List"%>
<%@ page import="com.shin.login.model.Bicycle"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Welcom, Trace Bicycles</title>
<c:forEach items="${emp }" var="s">
	<c:out value="${s.geopoint }" />
	<br />
	<br />
</c:forEach>
</head>
<body>
	<div id="log">Trace Bicycles</div>
	<div id="menu">
		<a href="/main">Home</a>
	</div>
	<div id="content">
		<div id="map" style="width: 450px; height: 250px"></div>
		</form>
	</div>
</body>
<script type="text/javascript"
	src="https://maps.google.com/maps/api/js?sensor=false">
</script>
<script src="http://www.google.com/jsapi"></script>
<script type="text/javascript">google.load("jquery","1");</script>
<script type="text/javascript">
function initialize(){
	/* var lat = new String("${bicycle.geopoint}");
	var lats = lat.replace('(','');
	lats = lats.replace(')','');
	var latLng = lats.split(",");
	var myLatlng = new google.maps.LatLng(latLng[0],latLng[1]);
	  var myOptions = {
	    zoom: 16,
	    center: myLatlng,
	    mapTypeId: google.maps.MapTypeId.ROADMAP,
	  }
	 var map = new google.maps.Map(document.getElementById("map"), myOptions);
	    
	 var marker = new google.maps.Marker({
	      position: myLatlng,
	      title:"Hello World!"
	  });
	  
	  // To add the marker to the map, call setMap();
	  marker.setMap(map); */
};

$(document).ready(function(){
//	initialize();
});
$(document).unload(function(){
	GUnload();
});
</script>
</html>