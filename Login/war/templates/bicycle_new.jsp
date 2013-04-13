<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<% 
	if(session.getAttribute("loginID") == null){
		response.sendRedirect("/");
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Welcome, Coffee-Shop</title>
<%
		if(!session.getAttribute("loginID").toString().equals("admin")){
			response.sendRedirect("/");
		}
	%>
</head>
<body>
	<div id="log">Trace Bicycles</div>
	<div id="menu">
		<a href="/main">Home</a> <a href="/bicycle/new/">Create</a>
	</div>
	<div id="content">
		<br\> Register Your bicycle. <br\>
		<div id="map" style="width: 450px; height: 250px"></div>
		<form action="/create" method="post">
			<table>
				<tr>
					<th><label for="bicycle">Bicycle Name:</label></th>
					<td><input id="bicycle" type="text" name="bicycle" size="54"
						maxlength="100" /></td>
				</tr>
				<input type="hidden" name="geo_point" id="geo_point" />
				</td>
				</tr>
			</table>
			<input type="submit" name="submit" id="submit" value="Save" /> <input
				type="button" name="go_home" id="go_home" value="Cancel"
				onClick="history.go(-1)" />
		</form>
	</div>
</body>
<script src="https://maps.google.com/maps/api/js?sensor=false"></script>
<script src="http://www.google.com/jsapi"></script>
<script type="text/javascript">google.load("jquery","1");</script>
<script type="text/javascript">
var map;
var markersArray = [];

function initialize() {
  var haightAshbury = new google.maps.LatLng(36.10309, 129.388447);
  var mapOptions = {
    zoom: 16,
    center: haightAshbury,
    mapTypeId: google.maps.MapTypeId.ROADMAP
  };
  map =  new google.maps.Map(document.getElementById("map"), mapOptions);

  google.maps.event.addListener(map, 'click', function(event) {
    addMarker(event.latLng);
  });
}
  
function addMarker(location) {
  marker = new google.maps.Marker({
    position: location,
    map: map
  });
	$('#geo_point').attr('value', location);
}

google.maps.event.addDomListener(window, 'load', initialize);

</script>

</html>