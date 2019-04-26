<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
<style>
	.form-box {
		background: #fff;
		margin: 30px auto;
		max-width: 500px;
		box-shadow: 0 3px 6px 0px rgba(0,0,0,0.16), 0 3px 6px 0px rgba(0,0,0,0.23);
	}
	form#login-form {
		overflow: hidden;
		position: relative;
		padding: 40px;
	}
	.head {
		color: #fff;
		font-size: 34px;
		font-weight: normal;
		padding: 50px 0;
		text-align: center;
		text-transform: uppercase;
		background: #6498fe;
	}
	.form-group {
	  margin-bottom: 15px;
	  position: relative;
	  width: 100%;
	  overflow: hidden;
	}
 
	.form-group .label-control {
	  color: #888;
	  display: block;
	  font-size: 14px;
	  position: absolute;
	  top: 0;
	  left: 0;
	  padding: 0;
	  width: 100%;
	  pointer-events: none;
	  height: 100%; 
	}
	.form-group .label-control::before,
	.form-group .label-control::after{
	  content: "";
	  left: 0;
	  position: absolute;
	  bottom: 0;
	  width: 100%;
	}
	.form-group .label-control::before{
	  border-bottom: 1px solid #B9C1CA;
	  transition: transform 0.3s;
	  -webkit-transition: -webkit-transform 0.3s;
	}
	 
	.form-group .label-control::after {
	  border-bottom: 2px solid #03A9F4;
	  -webkit-transform: translate3d(-100%, 0, 0);
	  transform: translate3d(-100%, 0, 0);
	  -webkit-transition: -webkit-transform 0.3s;
	  transition: transform 0.3s;
	}
	 
	.form-control {
	  border: none;
	  border-radius: 0;
	  margin-top: 20px;
	  padding: 12px 0;
	  width: 100%;
	  font-size: 14px;
	}
	.form-control:focus {
	  outline: none;
	  box-shadow: none;
	}
	.form-group .label-control .label-text{
	   -webkit-transform: translate3d(0, 30px, 0) scale(1);
	    -moz-transform: translate3d(0, 30px, 0) scale(1);
	    transform: translate3d(0, 30px, 0) scale(1);
	    -webkit-transform-origin: left top;
	    -moz-transform-origin: left top;
	    transform-origin: left top;
	    -webkit-transition: 0.3s;
	    -moz-transition: 0.3s;
	    transition: 0.3s;
	    position: absolute;
	}
	.active .label-control::after{
	   -webkit-transform: translate3d(0%, 0, 0);
	  transform: translate3d(0%, 0, 0);
	}
	.active .label-control .label-text {
	    opacity: 1;
	    -webkit-transform: scale(0.9);
	    -moz-transform: scale(0.9);
	    transform: scale(0.9);
	    color: #03A9F4 !important;
	}
	.input-field label:before{
	  content: '';
	  position: absolute;
	  bottom: 0;
	  left: 0;
	  width: 100%;
	  border-bottom: 1px solid #B9C1CA;
	  transition: transform 0.3s;
	  -webkit-transition: -webkit-transform 0.3s;
	}
	input.btn[type="submit"] {
	  background: #6498fe;
	  border:none;
	  border-radius: 2px;
	  color: #fff;
	  cursor: pointer;
	  font-size: 16px;
	  font-weight: bold;
	  letter-spacing: 3px;
	  margin: 5px 0;
	  outline: medium none;
	  overflow: hidden;
	  padding: 10px;
	  text-transform: uppercase;
	  transition: all 0.15s ease-in-out 0s;
	  width: 100%;
	  box-shadow: 0 1px 2px 0px rgba(0,0,0,0.16), 0 1px 2px 0px rgba(0,0,0,0.23);
	}
	input.btn[type="submit"]:hover {
	  background: #4b81eb;
	  box-shadow: 0 2px 4px 0px rgba(0,0,0,0.16), 0 2px 4px 0px rgba(0,0,0,0.23);
	}
	.text-p{
	  font-size: 14px;
	  text-align: center;
	  margin: 10px 0;
	}
	.text-p a{
	  color: #175690;
	}
</style>
</head>
<body>
	<div class="form-box">
	<div class="head">Member Login</div>
	<form id="login-form" action="/jspbook/member/memberProcServlet?action=login" method=post>
		<div class="form-group">
			<label class="label-control">
				<span class="label-text">ID</span>
			</label>
			<input type="text" name="id" class="form-control" />
		</div>
		<div class="form-group">
			<label class="label-control">
				<span class="label-text">PASSWORD</span>
			</label>
			<input type="password" name="password" class="form-control" />
		</div>
		<input type="submit" value="로그인" name="B1" class="form-control"/>
		<p class="text-p">회원가입을 원하시나요?<a href="register.html">회원가입</a></p>
	</form>
	</div>
</body>
</html>