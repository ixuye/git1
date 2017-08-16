<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


	<form id="userAddForms">
	
	<input type="hidden" name="userId" value="${user.userId }">
	<input type="hidden" name="fid" id="myUploadImgHidden" value="${user.userPhoto }">
	
	
	头像:<div style="padding-left: 60px">
			<img src="${user.userPhoto }" id="myUploadImg" style="width: 120px"/>
			<input type="file" id="upLoadImg" name="file"/>
		</div>
	用户名:<input type="text" name="userName" id="userNames" value="${user.userName }"><span id="promptUserName"></span><br>
	密&nbsp;码:<input type="password" name="userPassword" value="${user.userPassword }"><br>
	用户角色:
	验证码：<input name="codeImg" id="userCodeImg"><br>
		<span onclick="change_imgcode()">
			<img id="imgcode_src_node" src="<%=request.getContextPath() %>/imgcode">
			<font color="red">看不清，点击换一张</font>
		</span>
	
	
	<!-- <input type="button" value="提交" onclick="addUserInfo()">
 -->
	
	</form>

<script type="text/javascript">
	
	$(function(){
		uoLoad();
		$("#userNames").blur(function(){
			checkUserName();
		})
	})
	
	function checkUserName(){
		var userName = $("#userNames").val();
		if(userName == null || userName == "" || userName == undefined){
			alert("请输入账号");
			return ;
		}
		$.ajax({
			type:"post",
			url:"/ssi-controller/checkUserName.jhtml",
			data:{"userName":userName},
			dataType:"json",
			success:function(data){
				if(data.checkInfo == 6){
					alert("用户名可用")
					$("#promptUserName").html("<font color='green'>用户名可用</font>");
				}
				if(data.checkInfo == 7){
					alert("用户名不可用")
					$("#promptUserName").html("<font color='red'>用户名不可用</font>");
					
				}
			}
		})
	}
	
	function addUserInfo(){
		var userCode = $("#userCodeImg").val();
		if(userCodeImg == null || userCodeImg == "" || userCodeImg == undefined){
			alert("请填写验证码")
			return;
		}
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/insertUserInfo.jhtml",
			data:$("#userAddForm").serialize(),
			dataType:"json",
			success:function(data){
				if(data.insertInfo){
					alert("提交成功");
					location.href="<%=request.getContextPath()%>/queryUserInfo.jhtml";
				}
				if(data.codeInfo == 4){
					alert("验证码错误");
				}
				
			}
		})
	}
	
	/*
	上传图片
*/
function uoLoad(){
	$("#upLoadImg").uploadify({
		'swf':'uploadify/uploadify.swf',//swf uploadify的控制展示属性 flash基础文件 上传的进度条 和上传按钮功能
		'uploader':'uploadPhoto.jhtml',//声明文件的上传地址 上传到对应的action请求
		'buttonText':'上传海报',
		'mulit':false,
		'fileTypeDesc':'只能上传图片',
		'fileTypeExts':'*.jpg;*.png',
		'fileObjName':'file',
		'onUploadSuccess':function(response,data){//第二个参数为后台返回的数据
			//data = eval("("+data+")");
		//alert(data)
		//alert(response)
		   //替换图片原有路径 达到上传图片预览的目的
		   console.error(data);
		   console.error("response==========="+response);
		  
		  var d = eval("("+data+")");
		  //alert(d)
		   var url = "http://192.168.1.198:8088/"+d.path;
		  // alert(url)
			 //$("#loadPhotos").val(data.fid);
		    $("#myUploadImg").attr("src",url);
		    $("#myUploadImgHidden").val(d.fid);
			
		}
	})
}
	
//切换验证码
function change_imgcode() {
	$("#imgcode_src_node").attr("src", "<%=request.getContextPath() %>/imgcode?time=" + new Date().getTime());
}
</script>
