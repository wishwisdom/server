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
            alert("ID�� �Է��ϼ���");
            return false;
        }
       
        if(!userinput.pwd1.value ) {
            alert("��й�ȣ�� �Է��ϼ���");
            return false;
        }
        if(userinput.pwd1.value != userinput.pwd1.value)
        {
            alert("��й�ȣ�� �����ϰ� �Է��ϼ���");
            return false;
        }
        
      
        if(!userinput.email.value) {
            alert("gmail�� �Է��ϼ���");
            return false;
        }
        if(!userinput.gmailpwd.value) {
            alert("����� �̸��� �Է��ϼ���");
            return false;
        }
  
    }

    // ���̵� �ߺ� ���θ� �Ǵ�
    function openConfirmid(userinput) {
        // ���̵� �Է��ߴ��� �˻�
        if (userinput.id.value == "") {
            alert("���̵� �Է��ϼ���");
            return;
        }
        // url�� ����� �Է� id�� �����մϴ�.
        url = "confirmId?id=" + userinput.id.value ;
       
        // ���ο� �����츦 ���ϴ�.
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