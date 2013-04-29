<!-- 공통 jsp 설정 받아오기 -->
<%@ include file="../HdFt/jsp_header.jsp" %>

<!-- 헤더, 서브이미지, 좌측메뉴 -->
<jsp:include page="../HdFt/header.jsp" flush="true"></jsp:include>
<jsp:include page="sub_img.jsp" flush="true"></jsp:include>
<jsp:include page="left_menu.jsp" flush="true"></jsp:include>
<%@ page import="java.util.List"%>
<%@ page import="com.shin.login.model.*" %>
<% 
	if(session.getAttribute("loginID") == null){
		//response.sendRedirect("/index.jsp");
	}
	List<Bicycle> b = (List<Bicycle>)request.getAttribute("bicycle");
%>

<!-- 내용 -->

 <div id="logo">Trace Bicycles</div>
	<div id="content">
		Hell, <br\> Search bicycles.<br> <br\> <b>[Trace
			Bicycles]</b>
		<div id="map" style="width: 450px; height: 250px; margin-right:-150px"></div>
		<%-- <ul>
				<c:forEach items="${bicycle }" var ="s">
					<c:out value="${s.bicycle }"/>
				</c:forEach>
			</ul>
	</div> --%>
    
 <!-- 푸터 -->
<jsp:include page="../HdFt/footer.jsp" flush="true"></jsp:include>
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
