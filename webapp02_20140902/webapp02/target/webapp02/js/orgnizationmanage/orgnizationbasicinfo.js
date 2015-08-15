
$(function(){
	/**表格**/
	$('#tblJigou').datagrid({
		url:'JigouChaxunQuery.do', 
		loadMsg:"正在加载数据，请稍候...",
//		sortName: 'name',
//		sortOrder: 'desc',
//		remoteSort:true,
		pagination:true,
	//	toolbar:toolbarlist,
		queryParams:{},
		rownumbers:true,
		frozenColumns:[[
		    			{field:'ck',checkbox:true}
		    		]],
		columns:[[
			{field:'orgname',title:'机构名称',width:150,align:'center',
//				formatter:function(value, rec){
//					var str =  "<a href=\"javascript:editUser('" + value + "');\">" + value +"</a>";
//					return str;
//			}
			},
			{field:'jigouleibie',title:'机构类别',width:150},
			{field:'chenglishijian',title:'成立时间',width:150},
			{field:'xingzhengjibie',title:'行政级别',width:150},
			{field:'jingfeilaiyuan',title:'经费来源',width:150},
			{field:'zhengfuxulie',title:'是否是政府序列',width:150},
			{field:'id',title:'详情',width:150,align:'center',
				formatter:function(value,rec){
					var str =  "<a href=\"javascript:void(0);\" onclick=\"editInfosys('" + value + "')\" >详情</a>&nbsp;&nbsp";
					return str;
				}}
		]]
	});

}); 

$(function() {
	$("#btnSearch").click(function() {
        var params = $('#tblJigou').datagrid('options').queryParams; //先取得 datagrid 的查询参数  
       // alert($("#leibieSelection option:selected").val());
        params.orgname=$("#orgname").val();
        params.jigouleibie=$("#leibieSelection option:selected").val();
        params.chenglishijianFrom=$("#chenglishijianFrom").val();
        params.chenglishijianTo=$("#chenglishijianTo").val();
        params.xingzhengjibie=$("#jibieSelection").val();
        params.jingfeilaiyuan=$("#jingfeilaiyuan").val();
        params.zhengfuxulie=$("#xulieSelection").val();
        $('#tblJigou').datagrid('reload'); //设置好查询参数 reload 一下就可以了  
	});
});

/**编辑用户信息**/
function editInfosys(id)
{
	//alert(id);
	$('#jigouDialog').dialog({
		title : '机构详情',
		width : 1000,
		height : 600,
		closed : false,
		cache : false,
		href : "showDialog.do?url='JigouXiangqing.do?id=" + id + "'",
		modal : true,
		minimizable : true,
		resizable : true,
		onClose : function() {
			//$('#tbInfosys').datagrid('reload');
		}
	});
}
