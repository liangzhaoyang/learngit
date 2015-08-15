
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
	//重新加载datagrid的数据  
	var datefrom=$('#dateFrom').combobox("getValue");
	var dateto=$('#dateTo').combobox("getValue");
	if((datefrom!="")&&(dateto!="")&&(datefrom>dateto))
	{
		alert("后面的日期不能比前面的日期小！");
		return;
		}
	$("#tbLog").datagrid('reload',{name:$('#name').val(),logtype:$('#logtype').val(),dateFrom:$('#dateFrom').combobox("getValue"),dateTo:$('#dateTo').combobox("getValue"),miaoshu:$('#miaoshu').val()});
}

function logQuery()
{
	$('#rizifrm').form('submit', {
		onSubmit : function() {
			
			return true;
		},
		success : function(data) {
			if (data == "success") {
				$('#tbLog').datagrid('reload');
				alert("查询成功!");
			} else {
				alert(data);
				alert(typeof (data));
			}
		}
	});
	
}