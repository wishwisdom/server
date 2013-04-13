<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="../css/regForm.css" />
<script language="JavaScript">
   
    function checkIt() {
        var userinput = eval("document.userForm");
        if(!userinput.id.value) {
            alert("ID를 입력하세요");
            return false;
        }
       
        if(!userinput.pwd1.value ) {
            alert("비밀번호를 입력하세요");
            return false;
        }
        if(userinput.pwd1.value != userinput.pwd1.value)
        {
            alert("비밀번호를 동일하게 입력하세요");
            return false;
        }
        
      
        if(!userinput.email.value) {
            alert("gmail을 입력하세요");
            return false;
        }
        if(!userinput.gmailpwd.value) {
            alert("사용자 이름을 입력하세요");
            return false;
        }
  
    }

    // 아이디 중복 여부를 판단
    function openConfirmid(userinput) {
        // 아이디를 입력했는지 검사
        if (userinput.id.value == "") {
            alert("아이디를 입력하세요");
            return;
        }
        // url과 사용자 입력 id를 조합합니다.
        url = "confirmId?id=" + userinput.id.value ;
       
        // 새로운 윈도우를 엽니다.
        open(url, "confirm",
        "toolbar=no, location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=300, height=200");
    }
</script>
</head>
<body>
	<div id="stylized" class="myform">
		<form action="/Register" name="userForm" method="post"
			onSubmit="return checkIt()">
			<div name="important">
				<label>ID</label> <input name="id" placeholder="ID" type="text" />
				<button id="check" onClick="openConfirmid(this.userForm)"
					type="button">Check ID!</button>
				<div name="check" style="display: none;"></div>
				<label>Password</label> <input name="pwd1" placeholder="Password"
					type="password" /> <label>Re Password</label> <input name="pwd2"
					placeholder="Re Password" type="password" /> <label>Gmail</label> <input
					name="gmail" placeholder="Gmail" type="text" /> <label>Gmail
					Password</label> <input name="gpwd" placeholder="Gmail_Password"
					type="password" />
				<button type="submit">Sign-up</button>
				<div class="spacer"></div>
			</div>
		</form>
	</div>
</body>
</html>