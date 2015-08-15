
$(function(){
	/**表格**/
	$('#tbLog').datagrid({
		url:'rzcxInfo.do', 
		loadMsg:"正在加载数据，请稍候...",
//		sortName: 'code',
//		sortOrder: 'desc',
		pagination:true,
		columns:[[
			{field:'logtime',title:'发生时间',width:210},
			{field:'type',title:'日志类型',width:210},
			{field:'userid',title:'用户ID',width:210},
			{field:'desc',title:'描述',width:211},
		]]
	});
}); 


/**表格内的条件查询**/
function query(){
	var queryParams = $('#tbLog').datagrid('options').queryParams;
	queryParams.userid = $('#name').val();
	queryParams.type = $('#logtype').val();
	queryParams.logtimeFrom = $('#dateFrom').val();
	queryParams.logtimeTo = $('#dateFrom').val();
	queryParams.desc = $('#miaoshu').val();

//    var params = $('#tbLog').datagrid('options').queryParams; //先取得 datagrid 的查询参数  
//    var fields =$('#frmCondition').serializeArray(); //自动序列化表单元素为JSON对象  
//    $.each( fields, function(i, field){  
//        params[field.name] = field.value; //设置查询参数  
//    });

	//重新加载datagrid的数据  
	$("#tbLog").datagrid('reload');
}
