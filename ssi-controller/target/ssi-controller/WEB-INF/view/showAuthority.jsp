<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/zTreeStyle.css">
<script type="text/javascript" src="bootstrap/js/jquery-1.11.3.min.js"></script>

<script type="text/javascript" src="js/jquery.ztree.all.js"></script>

</head>
<body>
<h3>ajdalksj</h3>


<div>
	<ul class="ztree" id="myZtrees"></ul>
</div>


<script type="text/javascript">

  var settings = {
		 isSimpleData : true,              //数据是否采用简单 Array 格式，默认false  
        treeNodeKey : "id",               //在isSimpleData格式下，当前节点id属性  
        treeNodeParentKey : "pId",        //在isSimpleData格式下，当前节点的父节点id属性  
        showLine : true,                  //是否显示节点间的连线  
    	check: {
    		enable: true,                //是否显示复选框
    		//autoCheckTrigger: true
    	},
    	data: {							//节点具有父子关系
    		simpleData: {
    			enable: true,
    			idKey: "id",
    			pIdKey: "pId",
    			rootPId: 0
    		}
    	},
        //Y 属性定义 checkbox 被勾选后的情况；   
        //N 属性定义 checkbox 取消勾选后的情况；   
        //"p" 表示操作会影响父级节点；   
        //"s" 表示操作会影响子级节点。 
       
};

  
  
var treeNodes = [
                 { name: "test1",id:1},
				  { name: "test1_1",id:2,pId:1}, 
				  { name: "test1_2",id:3,pId:1},
				 {name: "test2",id:4},
				 { name: "test2_1",id:5,pId:4},
				 { name: "test2_2",id:6,pId:4}
             ];
		 $(function() {  
			
			//$("#myZtrees").zTree(settings, treeNodes); 
			$.fn.zTree.init($("#myZtrees"), settings, treeNodes);
			//$("#myZtrees").click(GetCheckedAll);
			
		 });  
		 
		 function  
		 
		 
		 function GetCheckedAll() {
		        var treeObj = $.fn.zTree.getZTreeObj("myZtrees");
		        var nodes = treeObj.getCheckedNodes(true);
		        var msg = "name--id--pid\n";
		        for (var i = 0; i < nodes.length; i++) {
		            msg += nodes[i].name+"--"+nodes[i].id+"--"+nodes[i].pId+"\n";
		        }
		        $("#msg").val();
		        $("#msg").val(msg);
		        alert(msg)
		    } 
		 

		


</script>
</body>
</html>