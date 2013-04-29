<!-- 공통 jsp 설정 받아오기 -->
<%@ include file="../HdFt/jsp_header.jsp" %>

<!-- 헤더, 서브이미지, 좌측메뉴 -->
<jsp:include page="../HdFt/header.jsp" flush="true"></jsp:include>
<jsp:include page="sub_img.jsp" flush="true"></jsp:include>
<jsp:include page="left_menu.jsp" flush="true"></jsp:include>
<script type="text/javascript" src="../script/sitemapstyler.js"></script>

<style>

body{
	margin:0;
	padding:0;
	font:70% Arial, Helvetica, sans-serif; 
	color:#555;
	line-height:150%;
	text-align:left;
}
a{
	text-decoration:none;
	color:#057fac;
}
a:hover{
	text-decoration:none;
	color:#999;
}
h1 {
	font-family: "Times New Roman", Times, serif;
	font-size: 45px;
	color: #CCC;
	margin:20px 0;
	font-weight: normal;
}
#container{
	margin:0 auto;
	width:680px;
	padding-bottom:20px;
}
#content{margin:0 20px;}
p{	
	margin:0 auto;
	width:680px;
	padding:1em 0;
}

</style>

<!-- 내용 -->
<article>
<div id="container">
	<h1>Site Map</h1>
	<div id="content">
		
		<noscript><p><strong>Sorry, this page needs JavaScript to make any sense at all</strong></p></noscript>
				
		<ul id="sitemap">
			<li><a href="#">First link</a>
				<ul>
					<li><a href="#">First link</a>
						<ul>
							<li><a href="#">First link</a></li>
							<li><a href="#">Second link</a></li>
							<li><a href="#">Third link</a></li>
							<li><a href="#">Fourth link</a></li>
							<li><a href="#">Fifth link</a></li>
						</ul>							
					</li>
					<li><a href="#">Second link</a></li>
					<li><a href="#">Third link</a>
						<ul>
							<li><a href="#">First link</a></li>
							<li><a href="#">Second link</a></li>
							<li><a href="#">Third link</a></li>
							<li><a href="#">Fourth link</a></li>
							<li><a href="#">Fifth link</a></li>
						</ul>							
					</li>
					<li><a href="#">Fourth link</a>
						<ul>
							<li><a href="#">First link</a></li>
							<li><a href="#">Second link</a></li>
							<li><a href="#">Third link</a></li>
							<li><a href="#">Fourth link</a></li>
							<li><a href="#">Fifth link</a></li>
						</ul>							
					</li>
					<li><a href="#">Fifth link</a></li>
				</ul>					
			</li>
			<li><a href="#">Second link</a>
				<ul>
					<li><a href="#">First link</a>
						<ul>
							<li><a href="#">First link</a></li>
							<li><a href="#">Second link</a></li>
							<li><a href="#">Third link</a></li>
							<li><a href="#">Fourth link</a></li>
							<li><a href="#">Fifth link</a></li>
						</ul>							
					</li>
					<li><a href="#">Second link</a>
						<ul>
							<li><a href="#">First link</a></li>
							<li><a href="#">Second link</a></li>
							<li><a href="#">Third link</a></li>
							<li><a href="#">Fourth link</a></li>
							<li><a href="#">Fifth link</a></li>
						</ul>							
					</li>
					<li><a href="#">Third link</a></li>
					<li><a href="#">Fourth link</a></li>
					<li><a href="#">Fifth link</a></li>
				</ul>					
			</li>
			<li><a href="#">Third link</a>
				<ul>
					<li><a href="#">First link</a></li>
					<li><a href="#">Second link</a></li>
					<li><a href="#">Third link</a>
						<ul>
							<li><a href="#">First link</a></li>
							<li><a href="#">Second link</a></li>
							<li><a href="#">Third link</a></li>
							<li><a href="#">Fourth link</a></li>
							<li><a href="#">Fifth link</a></li>
						</ul>							
					</li>
					<li><a href="#">Fourth link</a></li>
					<li><a href="#">Fifth link</a></li>
				</ul>					
			</li>
			<li><a href="#">Fourth link</a>
				<ul>
					<li><a href="#">First link</a></li>
					<li><a href="#">Second link</a></li>
					<li><a href="#">Third link</a></li>
					<li><a href="#">Fourth link</a></li>
					<li><a href="#">Fifth link</a></li>
				</ul>					
			</li>
			<li><a href="#">Fifth link</a>
				<ul>
					<li><a href="#">First link</a></li>
					<li><a href="#">Second link</a>
						<ul>
							<li><a href="#">First link</a></li>
							<li><a href="#">Second link</a></li>
							<li><a href="#">Third link</a></li>
							<li><a href="#">Fourth link</a></li>
							<li><a href="#">Fifth link</a></li>
						</ul>							
					</li>
					<li><a href="#">Third link</a></li>
					<li><a href="#">Fourth link</a>
						<ul>
							<li><a href="#">First link</a></li>
							<li><a href="#">Second link</a></li>
							<li><a href="#">Third link</a></li>
							<li><a href="#">Fourth link</a></li>
							<li><a href="#">Fifth link</a></li>
						</ul>							
					</li>
					<li><a href="#">Fifth link</a></li>
				</ul>					
			</li>
		</ul>
	
		
		
	</div>
</div>   
</article>

 <!-- 푸터 -->
<jsp:include page="../HdFt/footer.jsp" flush="true"></jsp:include>
