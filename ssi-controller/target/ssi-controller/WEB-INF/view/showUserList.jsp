<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>bootStrp</title>
<!-- 引入jquery -->

<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/jquery-1.10.2.js"></script>
<link rel="icon" type="image/x-icon" href="photo/ooopic_1498823704.ico" />
<link rel="stylesheet" href="bootstrap/css/font-awesome.min.css">
 <link rel="stylesheet" href="bootstrap/table/bootstrap-table.css">
<!-- 引入bootstrap的css -->
<link  href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" >
<!-- 引入bootstrap-treeview的css -->
<link  href="<%=request.getContextPath()%>/bootstrap/treeview/bootstrap-treeview.min.css" rel="stylesheet" >
<!-- 引入bootstrap-addTabs的css -->
<link  href="<%=request.getContextPath()%>/bootstrap/addTabs/addTabs.css" rel="stylesheet" >
<link rel="stylesheet" href="css/zTreeStyle.css">
<link rel="stylesheet" type="text/css" href="uploadify/uploadify.css" />
<link rel="stylesheet" href="bootstrap/bootstrap-dialog/dist/css/bootstrap-dialog.min.css">
<link rel="stylesheet" href="jquery-treegrid/css/jquery.treegrid.css">


<!-- 引入bootstrap的js-->
<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="bootstrap/table/bootstrap-table.js"></script>
<script type="text/javascript" src="bootstrap/table/locale/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="bootstrap/bootstrap-dialog/dist/js/bootstrap-dialog.min.js"></script>
<script type="text/javascript" src="js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="uploadify/jquery.uploadify.min.js"></script>
<!-- 引入bootstrap的js-->
<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/treeview/bootstrap-treeview.min.js"></script>
<!-- 引入bootstrap的js-->
<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/addTabs/addTabs.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-treegrid/extension/jquery.treegrid.extension.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-treegrid/js/jquery.treegrid.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
    <style>	
    	a:link{background:shocking red }
    	a:visited{color:camel}
    	a:hover{color:emerald green}
    	a:action{color:acid blue}	
		body {padding-top:55px;}
		.show-grid [class ^="col-"] {
            padding-top: 10px;
            padding-bottom: 10px;
            background-color: #eee;
            border: 1px solid #ddd;
            background-color: rgba(86, 61, 124, .15);
            border: 1px solid rgba(86, 61, 124, .2);
        }

		#wrapper {
    padding-left: 0;
    -webkit-transition: all 0.5s ease;
    -moz-transition: all 0.5s ease;
    -o-transition: all 0.5s ease;
    transition: all 0.5s ease;
}

#wrapper.toggled {
    padding-left: 250px;
}

#sidebar-wrapper {
    z-index: 1000;
    position: fixed;
    left: 250px;
    width: 0;
    height: 100%;
    margin-left: -250px;
    overflow-y: auto;
    background: papayawhip;
    -webkit-transition: all 0.5s ease;
    -moz-transition: all 0.5s ease;
    -o-transition: all 0.5s ease;
    transition: all 0.5s ease;
}

#wrapper.toggled #sidebar-wrapper {
    width: 250px;
}

#page-content-wrapper {
    width: 100%;
    position: absolute;
    padding: 15px;
}

#wrapper.toggled #page-content-wrapper {
    position: absolute;
    margin-right: -250px;
}

/* Sidebar Styles */

.sidebar-nav {
    position: absolute;
    top: 0;
    width: 250px;
    margin: 0;
    padding: 0;
    list-style: none;
}

.sidebar-nav li {
    text-indent: 20px;
    line-height: 40px;
}

.sidebar-nav li a {
    display: block;
    text-decoration: none;
    color: #999999;
}

.sidebar-nav li a:hover {
    text-decoration: none;
    color: #fff;
    background: rgba(255,255,255,0.2);
}

.sidebar-nav li a:active,
.sidebar-nav li a:focus {
    text-decoration: none;
}

.sidebar-nav > .sidebar-brand {
    height: 65px;
    font-size: 18px;
    line-height: 60px;
}

.sidebar-nav > .sidebar-brand a {
    color: #999999;
}

.sidebar-nav > .sidebar-brand a:hover {
    color: #fff;
    background: none;
}

@media(min-width:768px) {
    #wrapper {
        padding-left: 250px;
    }

    #wrapper.toggled {
        padding-left: 0;
    }

    #sidebar-wrapper {
        width: 250px;
    }

    #wrapper.toggled #sidebar-wrapper {
        width: 0;
    }

    #page-content-wrapper {
        padding: 20px;
        position: relative;
    }

    #wrapper.toggled #page-content-wrapper {
        position: relative;
        margin-right: 0;
    }
}
	</style>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
	 <div class="container-fluid " >
			 
			
				<marquee id="affiche" behavior="scroll" align="middle" width="500" height="20" direction="right" onMouseOut="this.start()" onMouseOver="this.stop()">
后台管理系统
</marquee>
 
			 <!-- // 右边的    导航 按钮 -->
	      <ul class="nav navbar-nav navbar-right">
	        <li class="dropdown">
	          <a href="<%=request.getContextPath()%>/logOff.jhtml" vspace="50" hspace="20" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">退出登录</a>
	          
	        </li>
	      </ul>
		</div>
		  
	</nav> 
	
	
	
	<!-- =======================剩下的布局============================= -->


	
		
		<div class="row" id="wrapper">
				 <div class="col-md-3" id="sidebar-wrapper" style="background:beige">
				
					
						<!-- tree 的插件 -->
							 <div id="treeDivXys">
							 
							 </div>                  
					 
				 </div>
		  
		  		 <div class="col-md-12">
				
						<!--   选项卡    -->
						<!-- Nav tabs -->
						<ul id="tabs" class="nav nav-tabs" role="tablist">
						    <li role="presentation" class="active">
						        <a href="#home" aria-controls="home" role="tab" data-toggle="tab">欢迎</a></li>
						</ul>
					  	<!-- 选项卡  内容 -->
						<!-- Tab panes -->
						<div class="tab-content">
						    <div role="tabpanel" style="" class="tab-pane active " id="home">
						 		
						    </div>
						</div> 
				</div>
		</div>
		
	
	</body>
	
	<script type="text/javascript">
	
	var msg="aaaaaaa";
	var interval = 100;
	var space10="";
	var seq=0;
	 
	function Scroll() {
	document.tmForm.tmText.value = msg.substring(seq, msg.length) + space10 + msg.substring(0, msg.length);
	seq++;seq++;
	if ( seq > msg.length ) { seq = 0 };
	window.setTimeout("Scroll();", interval );
	} 
	 
	 /* $(function(){
			/* 选项卡高度 */
			// $.addtabs({iframeUse:true})	
			//$.addtabs({iframeHeight:1000});
			
					//$("#treeDivXys").treeview({
						
						//data属性 树节点信息 json数组
						 // data:getTreeData(),         
						  //tree默认展开的节点级别默认为2
						 // levels: 0,
						  //selectedIcon: "glyphicon glyphicon-stop",//选中有框
						  //含有子节点的图标
						 // collapseIcon:'glyphicon glyphicon-phone',
						  //没有子节点的图标
						 // emptyIcon:'glyphicon glyphicon-asterisk',
						  //背景颜色
						//  backColor: '#84DEDF',
						  //是否显示复选框
						  //showCheckbox:true, 
						  //是否允许选中多个节点 
						  //multiSelect:true,
						  //启用节点的超链接功能默认为false,节点需指定href属性
						  //enableLinks:true,
						  //事件当节点选中时
						 // onNodeSelected:function(event,node){
							  //动态向 nav-tabs 导航标签添加tab选项卡
							  //addTabs方法  add() 添加tab  close()关闭tab  closeAll() 关闭全部tab
							// alert(node.url)
							 // if(null != node.href && "" != node.href){
								//发送ajax请求
				            		//$.ajax({
				            		//	url:node.href,
				            			//success:function(data) {
				            				//$.addtabs.add({id:node.text,title:node.text,content:data});
				            			//}
				            		//});
							 //}

						 // }
						
					//})
		//})  
	
		
		
		 //初始化树
    	$('#treeDivXys').treeview({
    				data:getTreeData(),
    				onNodeSelected:function(event, node) {
    				if (null != node.href && "" != node.href) {
	    				//发送ajax请求
	            		$.ajax({
	            			url:"/ssi-controller/"+node.href,
	            			success:function(data) {
	            				$.addtabs.add({id:node.text,title:node.text,content:data});
	            			}
	            		});
    			}
    		}
    	});
    	
		
		//获取菜单数据
    	function getTreeData() {
    		var tree_data = [];
    		//发送ajax请求
    		$.ajax({
    			async:false,//请求为同步
    			url:"<%=request.getContextPath() %>/selectTreeListJson.jhtml",
    			data:{userId:"${userInfo.userId }"},
    			dataType:"json",
    			success:function(data) {
    				tree_data = data;
    			}
    		});
    		return tree_data
    	}
		
		/* 	//初始化树
    	$('#treeDivXys').treeview({
    		data:tree_data,
    		onNodeSelected:function(event, node) {
    			if (null != node.href && "" != node.href) {
    				//发送ajax请求
            		$.ajax({
            			url:node.href,
            			success:function(data) {
            				$.addtabs.add({id:node.text,title:node.text,content:data});
            			}
            		});
    			}
    		}
    	}); */
		
	</script>
</html>