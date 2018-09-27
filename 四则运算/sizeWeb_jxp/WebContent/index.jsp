<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>

<script src="js/jquery-3.2.0.min.js"></script>


<script type="text/javascript" src="js/index.js"></script>

<style type="text/css">
	#logo  {
	position: absolute;
	left: 0px;
	top: 17px;
	width: 100%;
	z-index: 100;
		}
	img {z-index: -1;}
    #logo form h1 {
	font-style: italic;
	font-size: 36px;
	color: #C63;}
	#user{position:absolute;left:5%;top:10px;z-index:200;}
	#login{position:absolute;left:91%;top:10px;z-index:200;}
	#logout{position:absolute;left:95%;top:10px;z-index:200;}
 	#wrap {position:absolute;left:0px;top:0px;width:100%;height:100%}
	#menu {display: none;text-align: center;}
	#userInfo {display: none;text-align: center;}
	#zuoTiInput {display: none;}
	#selectInput {display: none;text-align: center;}
	#show {display: none; height: 350px;overflow: auto;}
	#right {position:absolute;left:50%;top:40px;height:80%;width:40%}
	#left {
	position: absolute;
	width: 518px;
	top: 116px;
	left: 48px;
	height: 13px;
}
	#zuoTiFuZhu {text-align: center;}
	#result {text-align: center;}
#user {
	color: #C63;
}
#user {
	font-weight: bold;
}
#user {
	color: #900;
}
#user {
	color: #480000;
}
</style>


</head>
<body>
	<img src="images/1492086826324.jpg" width="100%" height="100%">
    <div id="logo">
    <form>
    <center>
    <h1>PH250答题网</h1>
    </center>
    </form>
    </div>
    <!--
    <div id="loginBT" align="right">
			<a href="Login.html"><button>登录</button></a>
	</div>
    !-->
<div id="login">
    <a href="Login.html"><button>登录</button></a>
    </div>
<div id="logout">
    <a href="Logoutac"><button>注销</button></a>
    </div>
<div id="user">
    您好！${user.username}
    </div>
	<div id="wrap">
	
	<div id="left">
	
	
		<div id="loginMessage"></div>

		
		<!--<div id="userInfo">
			用户名：${user.username}
           <!--  <div id="loginBT" align="right">
			<a href="Logoutac"><button id="logout">注销</button></a>
	</div> !-->
		

	  <div id="menu">
			<button id="zuoTi">做题</button>
			<button id="selectLiShiShiTi">查询历史试题</button>
		</div>
		
		<div id="zuoTiInput" >

			<table align="center">
				<tr><td>试题类型：</td><td><label>整数式<input type="radio" name="type" value="0" checked="checked"></label></td><td><label>真分数式<input type="radio" name="type" value="1"></label></td></tr>
				<tr><td>有无乘除：</td><td><label>无<input type="radio" name="hasChengChu" value="0" checked="checked"></label></td><td><label>有<input type="radio" name="hasChengChu" value="1"></label></td></tr>
				<tr><td>有无括号：</td><td><label>无<input type="radio" name="hasKuoHao" value="0" checked="checked"></label></td><td><label>有<input type="radio" name="hasKuoHao" value="1"></label></td></tr>
				<tr><td>最大值：</td><td colspan="2"><input type="text" name="maxNum" value="10"><span id="maxNumInfo"></span></td></tr>
				<tr><td>试题个数：</td><td colspan="2"><input type="text" name="num" value="10"><span id="numInfo"></span></td></tr>
				<tr><td colspan="3"><input type="button" id="zuoTiInputTiJiao" value="提交"></td></tr>
			</table>

		</div>

		<div id="selectInput">
			<select id="shiJuanList">
				
			</select>
			
			<select id="typeSelect">
				<option value="all" selected="selected">
					全部
				</option>

				<option value="right">
					正确
				</option>
				
				<option value="wrong">
					错误
				</option>
				
			</select>

			<button id="selectShiJuan">
				查询
			</button>
	</div>
	
	
	<div id="show">
		<table id="showShiTiTable" align="center" border="1">

		</table>
		<div id="zuoTiFuZhu"><button id="jiaoJuanBT">交卷</button><span id="shengYuTime"></span></div>
		<div id="result">
			
		</div>
	</div>
		
	</div>
</div>
</body>
</html>