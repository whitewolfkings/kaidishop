function checkNick() {
	var userName = document.redForm.userName.value;
	if (userName == null || trim(userName) == "") {
		return false;
	}
	if(userName.length<5 || userName.length>20){
		alert("会员名的长度不正确,正确的长度为5-20位字符");
		document.redForm.userName.focus();
		return false;
	}

	document.checkNickForm.userName.value = userName;
	document.getElementById("check_username_info").innerHTML = "检测中，请稍等...";
	document.getElementById("check_username_info").className = "WarningMsg";
	document.getElementById("username_info").className = "";
	document.checkNickForm.submit();
	return true;
}

function chanestyle(idname) {
	document.getElementById("username_info").className = "";
	document.getElementById("password_info").className = "";
	document.getElementById("confirm_password_info").className = "";
	document.getElementById("email_info").className = "";
	document.getElementById(idname).className = "WarningMsg";	
}

function validateForm(form){
	var userName = form.userName.value;
	var password = form.password.value;
	var confirm_password = form.confirm_password.value;
	var email = form.email.value;
	if(userName==null || trim(userName)==""){
		alert("会员名不能为空");
		form.userName.focus();
		return false;
	}
	if(userName.length<5 || userName.length>20){
		alert("会员名的长度不正确,正确的长度为5-20位字符");
		form.userName.focus();
		return false;
	}
	if(password==null || trim(password)==""){
		alert("密码不能为空");
		form.password.focus();
		return false;
	}
	if(password.length<6 || password.length>16){
		alert("密码的长度不正确,正确的长度为6-16位字符");
		form.password.focus();
		return false;
	}
	if(password!=confirm_password){
		alert("两次输入的密码不一致，请重新输入。");
		form.confirm_password.focus();
		return false;
	}
	if(email==null || trim(email)==""){
		alert("电子邮件不能为空");
		form.email.focus();
		return false;
	}
	if(!verifyEmailAddress(email)){
		alert("电子邮件的格式不正确");
		form.email.focus();
		return false;
	}
	return true;
}