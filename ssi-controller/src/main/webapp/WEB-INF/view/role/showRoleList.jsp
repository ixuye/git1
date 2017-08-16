<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>

</head>
<body>

	<!-- 搜索面板 -->
    <div class="tab-content" style="padding: 4px">
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

	<!-- datagrid -->
	<table id="role_datagrids"></table>
	
	<!-- toolbar -->
	<div id="role_tb">
		<!-- Single button -->
		<div class="btn-group">
		  <button type="button" class="btn btn-success" onclick="show_add_role_dialog()">新增</button>
		</div>
		<div class="btn-group">
		  <button type="button" class="btn btn-danger" onclick="delete_all_checked_book()">删除</button>
		</div>
	</div>

	<script type="text/javascript">
		
		$(function() {
			init_role_dg();
		});
	
		function init_role_dg() {
			//初始化数据表格
			$('#role_datagrids').bootstrapTable({
				url:"<%=request.getContextPath() %>/role/selectRoleListJson.jhtml",
				dataType:"json",
				//请求方式
				method:"post",
				//必须的，操你大爷！！！！不然会造成中文乱码
				contentType: "application/x-www-form-urlencoded",
				//斑马线
				striped:true,
				//设置分页
				pagination:true,
				paginationLoop:true,
				pageNumber:1,
				pageSize:5,
				pageList:[3,5,8,10],
				//工具条
				toolbar:"#role_tb",
				//设置后台分页
				sidePagination:"server",
				//开启搜索框
				/* search:true, */
				//显示刷新按钮
				showRefresh:true,
				//拼接查询参数
				queryParams:function(params) {
					/* params.userAccount = $("#query_user_account").val();
					console.log(params); */
					return params;
				},
				queryParamsType:"",
			    columns: [{
			        checkbox:true
			    }, {
			        field: 'roleID',
			        title: '主键'
			    }, {
			        field: 'roleName',
			        title: '角色名称'
			    }, {
			        field: 'roleDesc',
			        title: '角色描述'
			    }, {
			        field: 'cz',
			        title: '操作',
			        formatter:function(value, row, index) {
			        	var zc_btn_group = '<div class="btn-group">'
			        	+ '<button type="button" class="btn btn-xs btn-success" onclick="show_edit_role_dialog(\'' + row.roleID + '\')">编辑</button>'
			        	+ '</div>&nbsp;&nbsp;'
			        	+ '<div class="btn-group">'
			        	+ '<button type="button" class="btn btn-xs btn-danger" onclick="delete_checked_role(\'' + row.roleID + '\')">删除</button>'
			        	+ '</div>&nbsp;&nbsp;'
			        	+ '<div class="btn-group">'
			        	+ '<button type="button" class="btn btn-xs btn-success" onclick="edit_role_menu(\'' + row.roleID + '\')">菜单操作</button>'
			        	+ '</div>';
			        	return zc_btn_group;
			        }
			    },]
			});
		}
		
		//编辑角色权限
		function edit_role_menu(role_id) {
			BootstrapDialog.show({
				title:"角色管理>>角色赋权限",
				message: $('<div></div>').load('<%=request.getContextPath() %>/role/toRoleMenuPage.jhtml?roleID=' + role_id),
				buttons: [{
	                label: '确定',
	                cssClass:"btn btn-success",
	                action: function(dialogItself){
	                	var menu_json_array = get_selection_tree_nodes();
	                	//使用ajax保存结果
	                	$.ajax({
	                		url:"<%=request.getContextPath() %>/role/insertRoleMenuList.jhtml",
	                		data:JSON.stringify(menu_json_array),
	                		dataType:"json",
	                		type:"post",
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
		
		//新增角色
		function show_add_role_dialog() {
			BootstrapDialog.show({
				title:"新增用户",
				message:$('<div></div>').load('<%=request.getContextPath() %>/role/toAddRolesPage.jhtml'),
				buttons: [{
	                label: '确定',
	                cssClass:"btn btn-success",
	                action: function(dialogItself){
	                	//使用ajax保存结果
	                	$.ajax({
	                		type:"post",
	                		url:"<%=request.getContextPath() %>/role/updateRoleInfoById.jhtml",
	                		data:$("#roleAddForms").serialize(),
	                		dataType:"json",
	                		success:function(data) {
	                			//关闭对话框
	                			dialogItself.close();
	                			//刷新表格
	                			$('#role_datagrids').bootstrapTable("refresh");
	                		},
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
		
		//条件查询
		function search_user() {
			//刷新表格
			//$('#user_dg').bootstrapTable("refresh");
			$("#role_datagrids").bootstrapTable('destroy');//先要将table销毁，否则会保留上次加载的内容
			init_user_dg();//重新初使化表格。
		}
		
		
		//单删
		function delete_checked_role(roleID){
			$.ajax({
				type:"post",
				url:"<%=request.getContextPath()%>/role/deleteRoles.jhtml",
				data:{"roleID":roleID},
				dataType:"json",
				success:function(){
					//刷新表格
	    			$('#role_datagrids').bootstrapTable("refresh");
					
				}
				
			})
		}
		
		//修改角色信息
		function show_edit_role_dialog(roleId){
			
					BootstrapDialog.show({
						title:"新增用户",
						message:$('<div></div>').load('<%=request.getContextPath() %>/role/getRoleInfoById.jhtml?roleID='+roleId),
						buttons: [{
			                label: '确定',
			                cssClass:"btn btn-success",
			                action: function(dialogItself){
			                	//使用ajax保存结果
			                	$.ajax({
			                		type:"post",
			                		url:"<%=request.getContextPath() %>/role/updateRoleInfoById.jhtml",
			                		data:$("#roleAddForms").serialize(),
			                		dataType:"json",
			                		success:function(data) {
			                			//刷新表格
			                			$('#role_datagrids').bootstrapTable("refresh");
			                			//关闭对话框
			                			dialogItself.close();
			                		},
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
	
	</script>
</body>
</html>