<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh">
<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>欢迎登陆</title>
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta name="author" content="yinq" />
<meta charset="utf-8" />
<meta name="author" content="Matthew Wagerfield" />
<meta name="keywords" content="flat,surface,shader,Float32Array" />
<meta name="description"
	content="Simple, lightweight Flat Surface Shader for rendering lit triangles." />
<meta property="og:description"
	content="Simple, lightweight Flat Surface Shader for rendering lit triangles." />
<meta property="og:site_name" content="Flat Surface Shader" />
<meta property="og:title" content="Flat Surface Shader" />
<meta property="og:type" content="website" />
<link rel="stylesheet" type="text/css" href="css/style1.css" />

<!-- Favicon -->
<link rel="shortcut icon" href="favicon.png">
<link rel="icon" href="favicon.png" type="image/x-icon">
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="css/style1.css" rel="stylesheet" type="text/css">
<link rel="icon" href="https://teamemo.com/favicon.ico">
	<link rel="stylesheet"	href="https://teamemo.com/static/styles/importer-823a728db7.css">
	<link rel="stylesheet"
	href="https://teamemo.com/static/bower_components/font-awesome/css/font-awesome.min.css">

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>

<body>
<script src="js/canvas-nest.js" count="200" zindex="-2" opacity="0.5" color="0,0,255" type="text/javascript"></script>


	<div class="login">
		<div class="login-body">

			<div class="login-form">
				<form id="loginform" class="form-horizontal" role="form"
					action="/auth/local/login?url=%2Fsettings%2Fwikis%2FAAA-1MJJzHCJZU2n7jfQBQ"
					method="post">
					<input type="hidden" name="_form" value="login" />
					<div style="margin-bottom: 10px;" class="input-group">
						<span class="input-group-addon material-input-addon"><i
							class="glyphicon glyphicon-user"></i></span>
						<div class="material-input-group">
							<input type="text" class="form-control"
								id="userName" name="email" label="Email address" defaultValue="" />
								<label
								for="email" class="md-control-label">User</label>
						</div>
					</div>
					<div style="margin-bottom: 10px;" class="input-group">
						<span class="input-group-addon material-input-addon"> <i
							class="glyphicon glyphicon-lock"></i></span>
						<div class="material-input-group">
							<input type="password" class="form-control"
								id="userPassword" name="password" label="Password" defaultValue="" />
								<label
								for="email" class="md-control-label">Password</label> 
						</div>
					</div>
					<div style="margin-bottom: 10px;" class="input-group">
						<span class="input-group-addon material-input-addon"> <i
							class="glyphicon glyphicon-qrcode"></i></span>
						<div class="material-input-group">
							<input type="text" class="form-control"
								id="userCodeImg" name="password" label="Password" defaultValue="" /> 
									<label
								for="email" class="md-control-label">UserCodeImg</label> 
						</div>
							<span onclick="change_imgcode()">
								<img id="imgcode_src_node" src="<%=request.getContextPath() %>/imgcode?time=" + new Date().getTime())>
								<font color="red">看不清，点击换一张</font>
							</span>
					</div>
					<div style="margin-bottom: 10px;" class="input-group">
                        <span class="input-group-addon material-input-addon"> <i class="glyphicon glyphicon-qrcode"></i></span>
						<div class="material-input-group">
							<input type="text" class="form-control"
								id="userQRcode" name="qrCode" label="Password" defaultValue="" />
								<label
								for="email" class="md-control-label">UserQRcode</label> 
						</div>
						<span onclick="change_QRcode()">
							<img id="checkimgqrcode" alt="验证码图片不存在" src="<%=request.getContextPath()%>/qrCode.jhtml" height="100"></img>
			                        	<font color="red">看不清，点击换一张</font>
						</span>
			        </div>
					<div style="margin-bottom: 20px; width: 100%;" class="input-group">
						<div style="float: right;">
							<a href="/resetPassword">Forgot password?</a>
						</div>
						<div class="checkbox" style="margin-right: 150px;">
							<label class="cb-enhanced"><input id="login-remember"
								type="checkbox" name="remember" value="1" /> <span
								class="cb-label">Remember me</span></label>
						</div>
					</div>
					<div></div>
					<div style="margin-top: 10px;" class="form-group">
						<div class="col-sm-12 controls">
							 <button id="btn-login" onclick="login()" class="btn btn-info" type="button">login</button> 
							 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							
							 <button id="btn-login" onclick="registers()" class="btn btn-success" type="button">register</button> 
						</div>
					</div>
					
					 <a class="login-brand" href="#"> 
					<img class="img-responsive"
						src="images/ddd.png" style="height: 60px" />
					</a> 

					 <div style="margin-bottom: 0px;" class="form-group">
						<div class="col-md-12 control">
							<div style="border-top: 1px solid #eee; padding-top: 15px;">
								Don't have an account? <a href="/join">Sign up here</a>
							</div>
						</div>
					</div> 
				</form>
			</div>
		</div>
		<div class="login-footer">
			© <a href="http://v3.bootcss.com/">http://v3.bootcss.com/</a> 2016
		</div>
	</div>


</body>
<script type="text/javascript">
	function login(){
			var userName = $("#userName").val();
			var userPassword = $("#userPassword").val();
			var userCodeImg = $("#userCodeImg").val();
			var userQRcode = $("#userQRcode").val();
			if(userCodeImg == null || userCodeImg == "" || userCodeImg == undefined){
				alert("请填写验证码")
				return;
			}
			/* if(userQRcode == null || userQRcode == "" || userQRcode == undefined){
				alert("请填写二维码")
				return;
			} */
			if(userName == null || userName == "" || userName == undefined){
				alert("请输入账号");
				return ;
			}
			if(userPassword == null || userPassword == "" || userPassword == undefined){
				alert("请输入密码");
				return;
				/* <div class="alert alert-warning alert-dismissible" role="alert">
				  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				  <strong>Warning!</strong> Better check yourself, you're not looking too good.
				</div> */
			}
			$.ajax({
				type:"post",
				url:"/ssi-controller/checkUserInfo.jhtml",
				data:{"userName":userName,"userPassword":userPassword,"codeImg":userCodeImg,"qrCode":userQRcode},
				async:false,
				dataType:"json",
				success:function(result){
					if(result.flag == 1){
						alert("登陆成功")
						alert(result.loginMsg)
						location.href="/ssi-controller/toHonerPage.jhtml";
						return;
					}
					if(result.flag == 2){
						alert("密码错误" + result.loginFailNum + "次");
						return;
					}
					if(result.flag == 3){
						alert("用户名错误");
						return;
					}
					if(result.flag == 4){
						alert("验证码错误");
						return;
					}
					if(result.flag == 5){
						alert("验证码为空");
						return;
						
					}
					if(result.flag == 8){
						alert("二维码错误");
						return;
						
					}
					if(result.flag == 9){
						alert("用户名锁定!!!请五分钟后进行登录");
						return;
						
					}
				}
			})
		}
	function registers(){
		location="/ssi-controller/toAddUserPage.jhtml";
		
	}
	
	//切换验证码
	function change_imgcode() {
		$("#imgcode_src_node").attr("src", "<%=request.getContextPath() %>/imgcode?time=" + new Date().getTime());
	}
	
	//切换二维码
	function change_QRcode(){
		$("#checkimgqrcode").attr("src", "<%=request.getContextPath()%>/qrCode.jhtml");
		
	}
</script>
</html>
