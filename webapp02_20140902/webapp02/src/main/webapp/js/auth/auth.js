/**工具栏**/
var toolbarlist = [{
	text:'授权',
	iconCls:'icon-add',
	handler:function(){
		batchOperation("sq.do", "ids", $("#tbInfosys"), "确定要授权吗?", true);
		//addAuth();
	}
},'-',{
	text:'解除授权',
	iconCls:'icon-remove',
	handler:function(){
		batchOperation("jcsq.do", "ids", $("#tbInfosys"), "确定要解除授权吗?", true);
		//delInfosys();
    }
}];

$(function(){
	/**表格**/
	$('#tbInfosys').datagrid({
		url:'sqcx.do',//path+'/demo_data/auth.json', 
		loadMsg:"正在加载数据，请稍候...",
		sortName: 'name',
		sortOrder: 'desc',
		pagination:true,
		toolbar:toolbarlist,
		frozenColumns:[[
		    			{field:'ck',checkbox:true}
		    		]],
		columns:[[
			{field:'id',title:'用户名',width:100},
			{field:'name',title:'姓名',width:100},
			{field:'roles',title:'用户角色'},
			{field:'orgName',title:'机构名称',width:200},
			{field:'suorgname',title:'主管单位',width:200},
			{field:'status',title:'授权状态',width:80},
		]]
	});
}); 

/**表格内的条件查询**/
function query(){
	var queryParams = $('#tbInfosys').datagrid('options').queryParams;
	queryParams.id = $('#id').val();
	queryParams.name = $('#name').val();
	queryParams.role = $('#role').val();
	queryParams.orgName = $('#orgName').val();
	queryParams.status = $('#status').val();

	//重新加载datagrid的数据  
	$("#tbInfosys").datagrid('reload');
}
