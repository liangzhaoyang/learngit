/**工具栏**/
var toolbarlist = [{
	text:'录入简历',
	iconCls:'icon-add',
	handler:function(){
		addInfosys();
	}
/*},'-',{
	text:'删除',
	iconCls:'icon-remove',
	handler:function(){
		batchOperation("smrysc.do", "ids", $("#tbInfosys"), "确定要删除吗?", true);
	//delInfosys();
    }
},'-',{
	text:'修改',
	iconCls:'icon-remove',
	handler:function(){
		updateInfosys();
    }*/
}];

$(function(){
	/**表格**/
	$('#tbInfosys').datagrid({
		url:'smrycx.do',//path+'/secretpersonList.json', 
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
//			formatter:function(value,rec){
//				var str =  "<a href=\"#\" onclick=\"editInfosys('" + value + "')\" >编辑</a>&nbsp;&nbsp";
//				str +=  "<a href=\"#\" onclick=\"delSingle('" + value + "')\" >删除</a>&nbsp;&nbsp";
//				//str +=  "<a href=\"#\" onclick=\"exportInfosys('" + value + "')\" >导出</a>";
//				return str;
//			}},
		]]
	});
	
}); 
