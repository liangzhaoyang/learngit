/**工具栏**/

$(function(){
	/**表格**/
	$('#tborgancounts').datagrid({
		url:'zgdwtj.do',
		loadMsg:"正在加载数据，请稍候...",
		columns:[[
			{field:'orgname',title:'主管单位',width:150},
			{field:'count',title:'数量',width:150},
		]]
	});
	
}); 

$(function(){
	/**表格**/
	$('#tborgancountsByXingzhengjibie').datagrid({
		url:'xzjbtj.do',
		loadMsg:"正在加载数据，请稍候...",
		columns:[[
			{field:'xingzhengjibie',title:'行政级别',width:150},
			{field:'count',title:'数量',width:150},
		]]
	});
	
}); 

$(function(){
	/**表格**/
	$('#tborgancountsByJigouleibie').datagrid({
		url:'jglbtj.do',
		loadMsg:"正在加载数据，请稍候...",
		columns:[[
			{field:'jigouname',title:'机构类别',width:150},
			{field:'count',title:'数量',width:150},
		]]
	});
	
}); 





