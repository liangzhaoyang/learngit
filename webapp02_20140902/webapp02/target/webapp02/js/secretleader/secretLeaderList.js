/**工具栏**/
var toolbarlist = [{
	text:'添加',
	iconCls:'icon-add',
	handler:function(){
		addInfosys();
	}
},'-',{
	text:'删除',
	iconCls:'icon-remove',
	handler:function(){
		batchOperation("bmgbsc.do", "ids", $("#tbInfosys"), "确定要删除吗?", true);
    }
},'-',{
	text:'修改',
	iconCls:'icon-remove',
	handler:function(){
		updateInfosys();
    }
}];
$(function(){
	/**表格**/
	$('#tbInfosys').datagrid({
		url:'bmgbcx.do',//path+'/page/secretleader/data/secretLeaderList.json', 
		singleSelect:false,  
		striped:true,
		loadMsg:"正在加载数据，请稍候...",
		rownumbers:true,
		sortName: 'name',
		sortOrder: 'desc',
		pagination:true,
		checkOnSelect:true,
		//pageList:[1,2,3,4], //设置分页显示的每页显示条数
		toolbar:toolbarlist,
		frozenColumns:[[
			{field:'ck',checkbox:true}
		]],
		columns:[[
			
		
			{field:'zhiwu',title:'职务',width:160},
			{field:'name',title:'姓名',width:50,align:'center'},
			{field:'xingbie',title:'性别',width:50,align:'center'},
			{field:'minzu',title:'民族',width:50,align:'center'},
			{field:'chushengnianyue',title:'出生年月',width:80,align:'center'},			
			{field:'xueli',title:'学历',width:80,align:'center'},
			{field:'zhengzhimianmao',title:'政治面貌',width:80,align:'center'},
			{field:'zhuanye',title:'专业',width:100,align:'center'},
			{field:'xingzhengjibie',title:'行政级别',width:60,align:'center'},
			{field:'jishuzhicheng',title:'技术职程',width:60,align:'center'},	
			{field:'shifouzhuanzhi',title:'是否专职',width:60,align:'center'},
			//{field:'thirteen',title:'其他职务',width:70,align:'center'},
			{field:'kaishiriqi',title:'何时从事保密工作',width:100,align:'center'},
			{field:'tel',title:'电话',width:80,align:'center'},
			{field:'mobilenum',title:'手机',width:80,align:'center'},
			{field:'suoshubumen',title:'部门名称',width:60,align:'center'},
			{field:'danweimingcheng',title:'单位名称',width:60,align:'center'},
			{field:'yaohaibumengrenyuan',title:'是否属于要害部门工作',width:100,align:'center'},			
			{field:'renyuanleixing',title:'人员类型',width:50,align:'center'},
			{field:'shemidengji',title:'涉密等级',width:50,align:'center'},
			{field:'id',title:'操作',width:50,align:'center',hidden:"true"}
			
			
//			{field:'id',title:'操作',width:50,align:'center',
//			formatter:function(value,rec){
//				var str =  "<a href=\"#\" onclick=\"editInfosys('" + value + "')\" >编辑</a>&nbsp;&nbsp";
//				str +=  "<a href=\"#\" onclick=\"delSingle('" + value + "')\" >删除</a>&nbsp;&nbsp";
//				//str +=  "<a href=\"#\" onclick=\"exportInfosys('" + value + "')\" >导出</a>";
//				return str;
//			}}
		]]
	});
	
}); 

function setPanduan(panduan)
{	
	var a;
	var rows = $('#tbInfosys').datagrid('getRows');
	if (rows.length>0) {
		$.each(rows,function(i,row1){
			if(row1.id==panduan){
				alert("该人员已经存在，请勿重复添加");
				a=1;
			}else
				a=0;
			});
	}
	return a;
}
/**增加信息系统**/
function addInfosys()
{
	$('#personDialog').dialog({
		title : '新增人员',
		width : 700,
		height :550,
		closed : false,
		cache : false,
		href : "showDialog.do?url='bmgbxj.do'",
		modal : true,
		resizable : true,
		onClose : function() {
			$('#tbInfosys').datagrid('reload');
		}
	});
}
//更新人员信息
function updateInfosys()
{
	//选中一行使用getSelected，选中多行使用getSelections
	var row = $("#tbInfosys").datagrid('getSelected'); 
	if(row == null || row.length == 0) {
		$.messager.alert('提示', "请先选择要操作的行", 'info'); 
		return;
	}

	$('#personDialog').dialog({
		title : '修改人员信息',
		width : 700,
		height : 550,
		closed : false,
		cache : false,
		href : "showDialog.do?url='ryxg.do?id="+row.id+"'",
		modal : true,
		resizable : true,
		onClose : function() {
			$('#tbInfosys').datagrid('reload');
		}
	});
}

/**表格内的条件查询**/
function query(){
	var queryParams = $('#tbInfosys').datagrid('options').queryParams;
	queryParams.name = $('#name').val();
	queryParams.xingbie = $('#xingbie').val();
	queryParams.zhuanye = $('#zhuanye').val();
	queryParams.chushengnianyueFrom = $("#date1").datebox('getValue');
	queryParams.chushengnianyueTo = $("#date2").datebox('getValue');
	queryParams.zhuanye = $('#zhuanye').val();
	queryParams.zhengzhimianmao = $('#zhengzhimianmao').val();
	queryParams.shifouzhuanzhi = $('#shifouzhuanzhi').val();
	queryParams.suoshubumen = $('#suoshubumen').val();
	queryParams.danweimingcheng = $('#danweimingcheng').val();
	//重新加载datagrid的数据  
	$("#tbInfosys").datagrid('reload');
;
}

	
//删除多个信息系统
function delInfosys(){
	var rows = $('#tbInfosys').datagrid('getSelections');
    var ids = [];
    for(var i=0;i<rows.length;i++){
    	ids.push(rows[i].id);
    }
    var idstr = ids.join(';');
    if(idstr == "") {
    	$.messager.alert('提示信息','请选择要删除的信息系统！','error');
    	return;
    }
	$.messager.confirm("信息确认","你确定要删除选择的信息系统吗？", function(result) {
       	if(result == true) {
       		/*var delUrl = "remove.action";
         	delUrl += "?ids=" + ids.join(";");
         	$.ajax({
   				type: "POST",
   				url: delUrl,
   				success: function(msg){
			   		if(msg){
			    		$.messager.alert('提示信息','信息系统删除成功','info', function(){});
			    	}else{
			    		$.messager.alert('提示信息','信息系统删除失败','info', function(){});
			    	}
	    			$('#tbInfosys').datagrid("reload");
   				}
			}); */
		}
      });
}
	
//单一删除
function delSingle(id){
    var ids = [];
    ids.push(id);
	$.messager.confirm("信息确认","你确定要删除选择的信息系统吗？", function(result) {
       	if(result == true) {
       		var delUrl = "bmgbsc.do";
         	delUrl += "?id=" + ids.join(";");
         	$.ajax({
   				type: "POST",
   				url: delUrl,
   				success: function(msg){
			   		if(msg){
			    		$.messager.alert('提示信息','信息系统删除成功','info', function(){});
			    	}else{
			    		$.messager.alert('提示信息','信息系统删除失败','info', function(){});
			    	}
	    			$('#tbInfosys').datagrid("reload");
   				}
			}); 
		}
      });
}
/**
 * 导出信息系统
 * id 信息系统id
 * **/
function exportInfosys(id)
{
	var ids = [];
    ids.push(id);
	$.messager.confirm("信息确认","你确定要导出选择的信息系统吗？", function(result) {
       	if(result == true) {
       		/*var delUrl = "remove.action";
         	delUrl += "?ids=" + ids.join(";");
         	$.ajax({
   				type: "POST",
   				url: delUrl,
   				success: function(msg){
			   		if(msg){
			    		$.messager.alert('提示信息','信息系统导出成功','info', function(){});
			    	}else{
			    		$.messager.alert('提示信息','信息系统导出失败','info', function(){});
			    	}
	    			$('#tt').datagrid("reload");
   				}
			}); */
		}
      });
}
