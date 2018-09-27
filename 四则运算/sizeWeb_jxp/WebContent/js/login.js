function check()
{
	//姓名
	var username = document.getElementById('username');
	var password = document.getElementById('password');
	if(username.value=="")
		{
		alert("姓名不能为空");
		username.focus();
		return false;
		}
	
	//密码验证
	if(password.value=="")
		{
		 alert("密码不能为空");
		 password.focus();
		 return false;
		}
	
      return true;
}