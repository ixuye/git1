<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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

<!-- toolbar -->
	<div id="menu_tb">
		<!-- Single button -->
		<div class="btn-group">
		  <button type="button" class="btn btn-success" onclick="show_add_menu_dialog()">新增</button>
		</div>
	</div><p>
<table id="menuTreeGrid"></table>
	
	sjdhfjskhf
	<script type="text/javascript">
	
	$(function(){
		getMenuList();
	})
		function getMenuList(){
			$("#menuTreeGrid").treegridData({
				 id: 'id',
	                parentColumn: 'pid',
	                type: "GET", //请求数据的ajax类型
	                url: '<%=request.getContextPath() %>/menu/selectMenuListJson.jhtml',   //请求数据的ajax的url
	                ajaxParams: {}, //请求数据的ajax的data属性
	                expandColumn: null,//在哪一列上面显示展开按钮
	                striped: true,   //是否各行渐变色
	                bordered: true,  //是否显示边框
	                //expandAll: false,  //是否全部展开
	                columns: [
	                    {
	                        title: '菜单ID',
	                        field: 'id'
	                    }, {
	                        title: '菜单名称',
	                        field: 'name'
	                    }, {
	                        title: '菜单链接',
	                        field: 'url'
	                    }, {
	                        title: '菜单父ID',
	                        field: 'pid'
	                    }, {
	                    	title: '操作',
	                    	formatter:function(row) {
	                    		var zc_btn_group = '<div class="btn-group">'
	        			        	+ '<button type="button" class="btn btn-xs btn-success" onclick="show_edit_menu_dialog(\'' + row.id + '\')">编辑</button>'
	        			        	+ '</div>&nbsp;&nbsp;'
	        			        	+ '<div class="btn-group">'
	        			        	+ '<button type="button" class="btn btn-xs btn-danger" onclick="delete_checked_menu(\'' + row.id + '\')">删除</button>'
	        			        	+ '</div>';
	        			    	return zc_btn_group;
	                    	}
	                    }
	                ]
	            });
		}
	
	//新增菜单
	function show_add_menu_dialog(){
		
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/menu/toAddMenuPage.jhtml",
			dataType:"html",
			success:function(data){
				BootstrapDialog.show({
					title:"新增权限",
					message:data,
					buttons: [{
		                label: '确定',
		                cssClass:"btn btn-success",
		                action: function(dialogItself){
		                	//使用ajax保存结果
		                	$.ajax({
		                		type:"post",
		                		url:"<%=request.getContextPath() %>/menu/insertMenu.jhtml",
		                		data:$("#addMenuForms").serialize(),
		                		dataType:"json",
		                		success:function(data) {
		                			//刷新表格
		                			$("#menuTreeGrid").html("");
		                			getMenuList();
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
		})
	}
	
	//单删
	function delete_checked_menu(menuId){
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath() %>/menu/deleteMenuById.jhtml",
			data:{"menuID":menuId},
			success:function(data){
				//刷新表格
    			$("#menuTreeGrid").html("");
    			getMenuList();
			}
		})
	}
	
	//修改
	function show_edit_menu_dialog(menuid){
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath() %>/menu/toAddMenuPage.jhtml",
			data:{"menuID":menuid},
			dataType:"html",
			success:function(data){
				BootstrapDialog.show({
					title:"修改权限",
					message:data,
					buttons: [{
		                label: '确定',
		                cssClass:"btn btn-success",
		                action: function(dialogItself){
		                	//使用ajax保存结果
		                	$.ajax({
		                		type:"post",
		                		url:"<%=request.getContextPath() %>/menu/insertMenu.jhtml",
		                		data:$("#addMenuForms").serialize(),
		                		dataType:"json",
		                		success:function(data) {
		                			//刷新表格
		                			$("#menuTreeGrid").html("");
		                			getMenuList();
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
		})
	}
	</script>
</body>
</html>