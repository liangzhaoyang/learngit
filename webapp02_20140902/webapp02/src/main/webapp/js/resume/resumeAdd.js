/**工具栏**/
var toolbarlist = [{
	text:'添加',
	iconCls:'icon-add',
	handler:function(){
		add002();
	}
},'-',{
	text:'删除',
	iconCls:'icon-remove',
	handler:function(){
		del002();
    }
}];

function choose(){
	$("#SendProduct").dialog("open");
	}

//function editInfosys(id){
//	   openWinWidthHeight(path+'/js/resume/resumeAddWin.jsp', "编辑",400,200);
//	}
//
//function selectUser(){
//		   openWinWidthHeight(path+'/js/resume/selectUserWin.jsp', "添加",400,200);
//		}
//$(function(){
//	/**表格**/
//	$('#resumeadd4').datagrid({
//		url:'jltj.do',//path+'/demo_data/user.json', 
//		loadMsg:"正在加载数据，请稍候...",
//		sortName: 'name',
//		sortOrder: 'desc',
//		pagination:true,
//		toolbar:toolbarlist,
//		columns:[[
//			{field:'chengwei',title:'称谓',width:100},
//			{field:'xingming',title:'姓名',width:100},
//			{field:'chushengnianfen',title:'出生年份',width:100},
//			{field:'zhizhengmianmao',title:'政治面貌',width:150},
//			{field:'gongzuodanweijizhiwu',title:'工作单位及职务',width:150},
//		]]
//	});
//}); 


function jlmxbc(){
	document.jlmxbc.action="jlmxbc.do";
	document.jlmxbc.submit();
}
