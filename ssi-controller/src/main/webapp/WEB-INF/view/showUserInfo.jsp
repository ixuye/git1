<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


</head>
<body>
<%-- <center>
<input type="button" value="新增" onclick="toAddUserPage()">
<input type="button" value="修改" onclick="toUpdateUserPage()">
<input type="button" value="删除" onclick="todeleteUserInfo()">
</center> --%>
<br>


		<!-- ================================条件查询表单开始============================= -->
		<form id="movie_from" class="form-inline">
				<!--============================名称条件查询===========================-->
			  <div class="form-group">
			    <label for="proName">账号</label>
			    <input type="text" class="form-control" id="proName" placeholder="账号">
			  </div>&nbsp;&nbsp;&nbsp;&nbsp;
			  <!--名称条件查询-->
			  <!--==============================时间条件查询=========================-->
			  <div class="form-group">
			    <label for="exampleInputFile">日期</label>
			    <input class="form-control " id="minTime" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'maxTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />至
				<input  class="form-control " id="maxTime" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'minTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" /><br/>
			  </div>&nbsp;&nbsp;&nbsp;&nbsp;
			   <!--==============================时间条件查询=============================-->
			    <!--==================================下拉列表条件查询 开始==================================-->
			  <div class="form-group">
			   
			  </div>
			  <!--=============================下拉列表条件查询=========================================-->
			
			  <!--=====================复选框条件查询=================================-->
			  <button type="button" class="btn btn-success" onclick="search()">搜索</button>
			  <button type="button" class="btn btn-primary" onclick="reset()">重置</button>
		</form>
		<!-- ================================条件查询表单开始============================= -->

	<!-- 搜索面板 -->
    <div align="center" class="tab-content" style="padding: 4px">
	    <form id="search_user_form">
	    	<label>用户账号：</label>
			<input id="query_user_account" class="form-control-sm" placeholder="请输入用户账号">
			<div align="center">
				<!-- Single button -->
				<div class="btn-group">
				  <button type="button" class="btn btn-success glyphicon glyphicon-search" onclick="search_user()">搜索</button>
				</div>
				<div class="btn-group">
				  <button type="button" class="btn btn-danger glyphicon glyphicon-repeat" onclick="reset_search_user_form()">重置</button>
				</div>
			</div>
		</form>
    </div>

<div id="user_tb">
		<!-- Single button -->
		<div class="btn-group">
		  <button type="button" class="btn btn-success" onclick="toAddUserPage()">新增</button>
		</div>
		<div class="btn-group">
		  <button type="button" class="btn btn-danger" onclick="todeleteUserInfo()">删除</button>
		</div>
	</div>
<table id="user_datagrids"></table>

</body>
<script type="text/javascript">

		$(function(){
			getData()
		})
	
		
		function getData(){
			$("#user_datagrids").bootstrapTable({
				 url:"/ssi-controller/queryUserInfo.jhtml",
				 method:'post',
				 dataType:"json",
				 striped: true,  	// 斑马线效果     默认false 
				 //只允许选中一行
				 singleSelect:false,
				 //选中行是不选中复选框或者单选按钮
				 clickToSelect:true,
				 showToggle:true,                    // 是否显示详细视图和列表视图的切换按钮
				 cardView: false,                    //是否显示详细视图
				 uniqueId: "userId",                     // 每一行的唯一标识，一般为主键列
				 showColumns: true,                  //是否显示所有的列
				 showRefresh: true,                  //是否显示刷新按钮
				 minimumCountColumns: 2, 
				 detailView: false,
				//设置分页
					pagination:true,
					paginationLoop:true,
					pageNumber:1,
					pageSize:5,
					pageList:[3,5,8,10],//是否显示父子表
				 //发送到服务器的数据编码类型  
				contentType: "application/x-www-form-urlencoded",   //数据编码纯文本  offset=0&limit=5
				//工具条
				toolbar:"#user_tb",
				//设置后台分页
				sidePagination:"server",
				//拼接查询参数
				queryParams:function(params) {
					/* params.userAccount = $("#query_user_account").val();
					console.log(params); */
					return params;
				},
				queryParamsType:"",
				columns:[
							{
								field:'aaa',
								title:'',
								width:50,
								formatter:function(value,row,index){
									return "<input type='checkbox' value="+row.userId+" name='chk'/>";
								}
						    },
							{
						    	field:'userId',
						    	title:'用户id',
						    	width:50,
							},
							{
								field:'userName',
								title:'用户账号',
								width:100
							},
							{
								field:'fileUrl',
								title:'用户头像',
								width:100,
								formatter: function(value,row,index){
									
									var str = '<img src="http://192.168.1.198:8088/'+value+'" alert="没有图片" style="width: 120px"/>';
									return str;
								}
							},
							{
						        field: 'cz',
						        title: '操作',
						        formatter:function(value, row, index) {
						        	var zc_btn_group = '<div class="btn-group">'
						        	+ '<button type="button" class="btn btn-xs btn-success" onclick="toUpdateUserPage(\'' + row.userId + '\')">编辑</button>'
						        	+ '</div>&nbsp;&nbsp;'
						        	+ '<div class="btn-group">'
						        	+ '<button type="button" class="btn btn-xs btn-danger" onclick="deleteUser(\'' + row.userId + '\')">删除</button>'
						        	+ '</div>&nbsp;&nbsp;'
						        	+ '<div class="btn-group">'
						        	+ '<button type="button" class="btn btn-xs btn-success" onclick="edit_user_role(\'' + row.userId + '\')">角色操作</button>'
						        	+ '</div>';
						        	return zc_btn_group;
						        }
						    }
							
				         ],
			   })
		}
		


	function getAuthority(userId){
		
		alert(userId)
		
	}
	
	//编辑用户的角色
	function edit_user_role(user_id) {
		BootstrapDialog.show({
			title:"用户管理>>用户赋角色",
			message: $('<div></div>').load('<%=request.getContextPath() %>/toUserRolePage.jhtml?userId=' + user_id),
			buttons: [{
                label: '确定',
                cssClass:"btn btn-success",
                action: function(dialogItself){
                	var role_json_array = get_selection_tree_nodes();
                	//使用ajax保存结果
                	$.ajax({
                		type:"post",
                		url:"<%=request.getContextPath() %>/insertUserRoleList.jhtml",
                		data:JSON.stringify(role_json_array),
                		dataType:"json",
                		success:function(data) {
                			//关闭对话框
                			dialogItself.close();
                		},
                		contentType:"application/json"
                	});
                }
            }, {
                label: '取消',
                cssClass:"btn btn-danger",
                action: function(dialogItself){
                	dialogItself.close();
                }
            }]
		});
	}
	
	function toAddUserPage(){
		BootstrapDialog.show({
			title:"用户管理>>用户赋角色",
			message: $('<div></div>').load('<%=request.getContextPath() %>/toAddUserPage.jhtml'),
			
			buttons: [{
                label: '确定',
                cssClass:"btn btn-success",
                action: function(dialogItself){
                	//var role_json_array = get_selection_tree_nodes();
                	//使用ajax保存结果
                	alert($("#userAddForms").serialize())
                	$.ajax({
                		type:"post",
                		url:"<%=request.getContextPath() %>/insertUserInfo.jhtml",
                		data:$("#userAddForms").serialize(),
                		dataType:"json",
                		success:function(data) {
                			//关闭对话框
                			dialogItself.close();
            				//刷新表格
                			$('#user_datagrids').bootstrapTable("refresh");
                		},
                		//contentType:"application/json"
                	});
                }
            }, {
                label: '取消',
                cssClass:"btn btn-danger",
                action: function(dialogItself){
                	dialogItself.close();
                }
            }]
		});
		
		<%-- location.href="<%=request.getContextPath()%>/toAddUserPage.jhtml"; --%>
	}
	
	function toUpdateUserPage(id){
		/* var arr = $("[name='btSelectItem']:checked"); 
		if(arr.length==1){
			
			var id = arr[0].value; */
			alert(id)
			BootstrapDialog.show({
				title:"用户管理>>用户赋角色",
				message: $('<div></div>').load('<%=request.getContextPath() %>/queryUserInfoByUserId.jhtml?userId=' +id),
				buttons: [{
	                label: '确定',
	                cssClass:"btn btn-success",
	                action: function(dialogItself){
	                	//var role_json_array = get_selection_tree_nodes();
	                	//使用ajax保存结果
	                	$.ajax({
	                		type:"post",
	                		url:"<%=request.getContextPath() %>/insertUserInfo.jhtml",
	                		data:$("#userAddForms").serialize(),
	                		dataType:"json",
	                		success:function(data) {
	                			//关闭对话框
	                			dialogItself.close();
	                			//刷新表格
	                			$('#user_datagrids').bootstrapTable("refresh");
	                			
	                		},
	                		//contentType:"application/json"
	                	});
	                }
	            }, {
	                label: '取消',
	                cssClass:"btn btn-danger",
	                action: function(dialogItself){
	                	dialogItself.close();
	                }
	            }]
			});
		
	}
	//单删
	function deleteUser(userid){
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/deleteUser.jhtml",
			data:{"userId":userid},
			dataType:"json",
			success:function(){
				//刷新表格
    			$('#user_datagrids').bootstrapTable("refresh");
				
			}
			
		})
	}
	//批删
	function todeleteUserInfo(){
		var arr = $("[name='chk']:checked");
		alert(arr[0].value)
		if(arr.length>0){
			ids = "";
			for (var i = 0; i < arr.length; i++) {
				ids += ","+arr[i].value;
			}
			
			ids = ids.substring(1);
			alert(ids)
			$.ajax({
				type:"post",
				url:"<%=request.getContextPath()%>/toDeleteUserInfo.jhtml",
				data:{"ids":ids},
				dataType:"json",
				success:function(){
					//刷新表格
	    			$('#user_datagrids').bootstrapTable("refresh");
					
				}
			})
			<%-- //location.href="<%=request.getContextPath()%>/toDeleteUserInfo.jhtml?ids="+ids;
			 --%>
		}else{
			alert("请选择要删除的数据!")
		}
	}
</script>
</html>