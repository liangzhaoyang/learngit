/**工具栏**/
var toolbarlist = [{
	text:'选择',
	iconCls:'icon-ok',
	handler:function(){
		addInfosys();
	}

}];

$(function(){
	/**表格**/
	$('#tbInfosys').datagrid({
		url:'rycx.do',//path+'/secretpersonList.json', 
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
		]]
	});
	
}); 


function addInfosys(id)
{
	//选中一行使用getSelected，选中多行使用getSelections
	var row = $("#tbInfosys").datagrid('getSelected'); 
	if(row == null || row.length == 0) {
		$.messager.alert('提示', "请先选择要操作的行", 'info'); 
		return;
	}
	$.messager.confirm("信息确认","你确定要添加所选人员吗？", function(result) {
       	if(result == true) {
         	$.ajax({
//   				type: "POST",
//   				url: addUrl,
   				success: function(msg){
   					window.parent.$("#personDialog").dialog("close");
   					window.parent.setId(row.id);
//   					window.parent.setXingbie(row.xingbie,row.name);
   					
			   		if(msg){
			    		$.messager.alert('提示信息','选择成功','info', function(){});
			    	}
//			   		else{
//			    		$.messager.alert('提示信息','信息系统删除失败','info', function(){});
//			    	}
//	    			$('#tbInfosys').datagrid("reload");
   				}
			}); 
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
}

