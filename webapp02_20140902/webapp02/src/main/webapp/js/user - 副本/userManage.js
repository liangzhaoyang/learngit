// 删除用户
function delUsers() {
	batchOperation("yhsc.do", "ids", "#tblUsers", "确定要删除吗?", true);
}

/**工具栏**/
var toolbarlist = [{
	text:'添加',
	iconCls:'icon-add',
	handler : function() {
		$('#userDialog').dialog({
			title : '新增用户',
			width : 600,
			height : 400,
			closed : false,
			cache : false,
			href : "showDialog.do?url='yhtj.do'",
			modal : true,
			minimizable : true,
			resizable : true,
			onClose : function() {
				$('#tbInfosys').datagrid('reload');
			}
		});
	}
},'-',{
	text:'删除',
	iconCls:'icon-remove',
	handler:function(){
		batchOperation("yhsc.do", "ids", "#tblUsers", "确定要删除吗?", true);
    }
},'-', {
	text:'密码重置',
	iconCls:'icon-remove',
	handler:function(){
		batchOperation("mmcz.do", "ids", "#tblUsers", "确定要重置密码吗?", true);
    }
}]
;

/**编辑用户信息**/
function editUser(id)
{
	$('#userDialog').dialog({
		title : '新增用户',
		width : 600,
		height : 400,
		closed : false,
		cache : false,
		href : "showDialog.do?url='yhxg.do?id=" + id + "'",
		modal : true,
		minimizable : true,
		resizable : true,
		onClose : function() {
			$('#tbInfosys').datagrid('reload');
		}
	});
}


$(function(){
	/**表格**/
	$('#tblUsers').datagrid({
		url:'yhcx.do', 
		loadMsg:"正在加载数据，请稍候...",
		sortName: 'name',
		sortOrder: 'desc',
		remoteSort:true,
		pagination:true,
		toolbar:toolbarlist,
		queryParams:{},
		rownumbers:true,
		frozenColumns:[[
		    			{field:'ck',checkbox:true}
		    		]],
		columns:[[
			{field:'id',title:'用户名',width:150,align:'center',
				formatter:function(value, rec){
					var str =  "<a href=\"javascript:editUser('" + value + "');\">" + value +"</a>";
					return str;
			}},
			{field:'name',title:'姓名',width:150},
			{field:'orgName',title:'机构名称',width:300},
			{field:'roles',title:'用户角色',width:300},
		]]
	});
}); 

$(function() {
	$("#btnSearch").click(function() {
        var params = $('#tblUsers').datagrid('options').queryParams; //先取得 datagrid 的查询参数  
        var fields =$('#frmCondition').serializeArray(); //自动序列化表单元素为JSON对象  
        $.each( fields, function(i, field){  
            params[field.name] = field.value; //设置查询参数  
        });
        $('#tblUsers').datagrid('reload'); //设置好查询参数 reload 一下就可以了  
	});
});
