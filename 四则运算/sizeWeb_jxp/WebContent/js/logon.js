function check()
{
	//姓名
	var username = document.getElementById('n1');
	var pwd1 = document.getElementById('pwd1');
	var pwd2 = document.getElementById('pwd2');
	if(username.value=="")
		{
		alert("姓名不能为空");
		username.focus();
		return false;
		}
	
	//密码验证
	if(pwd1.value=="")
		{
		 alert("密码不能为空");
		 pwd1.focus();
		 return false;
		}
	if(pwd2.value=="")
	{
	 alert("请再次输入密码");
	 pwd2.focus();
	 return false;
	}
	if(pwd1.value!=pwd2.value)
	{
	 alert("两次输入的密码不相同");
	 pwd2.focus();
	 return false;
	}

      return true;
}