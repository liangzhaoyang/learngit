function checkInput() {
	var bRtn = true;
	
	if($("#id").val() == null || $("#id").val() == "") {
		$("#idMsg").html("用户名不能为空！");
		bRtn = false;
	} else {
		$("#idMsg").html("");
	}

	if($("#name").val() == null || $("#name").val() == "") {
		$("#nameMsg").html("姓名不能为空！");
		bRtn = false;
	} else {
		$("#nameMsg").html("");
	}

	if($("#name").val() == null || $("#name").val() == "") {
		$("#nameMsg").html("姓名不能为空！");
		bRtn = false;
	} else {
		$("#nameMsg").html("");
	}

	var rolesNoChecked = true;
	$("input[name='roles']:checkbox").each(function(){ 
		if($(this).attr("checked")) {
			rolesNoChecked = false;
//			break;
		}
	});
	if(rolesNoChecked) {
		$("#rolesMsg").html("请为用户选择角色！");
		bRtn = false; 
	} else {
		$("#rolesMsg").html("");
	}
	
	if($("#orgName").val() != null && $("#orgName").val() != "" && $("#orgName").val() == $("#suOrgName").val()) {
		$("#suOrgNameMsg").html("用户所属的机构名称与用户所属机构的上级单位名称相同！");
		bRtn = false;
	} else {
		$("#suOrgNameMsg").html("");
	}

	return bRtn;
}

function editUser(userId) {
	$('#userDialog').dialog({
		title : '修改用户',
		width : 600,
		height : 400,
		closed : false,
		cache : false,
		href : "showDialog.do?url='yhxg.do?id=" + userId +"'",
		modal : true,
		minimizable : true,
		resizable : true,
		onClose : function() {
			$('#tbInfosys').datagrid('reload');
		}
	});
}

// 删除用户
//function delUsers() {
//	batchOperation("yhsc.do", "ids", "#tblUsers", "确定要删除吗?", true);
//}

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
				$('#tblUsers').datagrid('reload');
			}
		});
	}
},'-',{
	text:'删除',
	iconCls:'icon-remove',
	handler:function(){
		batchOperation("yhsc.do", "ids", $("#tblUsers"), "确定要删除吗?", true);
    }
},'-', {
	text:'密码重置',
	iconCls:'icon-remove',
	handler:function(){
		batchOperation("mmcz.do", "ids", $("#tblUsers"), "确定要重置密码吗?", true);
    }
}]
;

$(function(){
	/**表格**/
	$('#tblUsers').datagrid({
		url:'yhcx.do', 
		loadMsg:"正在加载数据，请稍候...",
		sortName: 'name',
		sortOrder: 'asc',
		remoteSort:true,
		pagination:true,
		toolbar:toolbarlist,
		queryParams:{},
		rownumbers:true,
		frozenColumns:[[
		    			{field:'ck',checkbox:true}
		    		]],
		columns:[[
			{field:'id',title:'用户名',width:150,align:'left', sortable:true,
				formatter:function(value, rec){
					var str =  "<a href=\"javascript:editUser('" + value + "');\">" + rec.id +"</a>";
					return str;
			}},
			{field:'name',title:'姓名',width:150, sortable:true},
			{field:'orgName',title:'机构名称',width:300, sortable:true},
			{field:'roles',title:'用户角色',width:300},
		]]
	});

	$("#btnSearch").click(function() {
        var params = $('#tblUsers').datagrid('options').queryParams; //先取得 datagrid 的查询参数  
        var fields =$('#frmCondition').serializeArray(); //自动序列化表单元素为JSON对象  
        $.each( fields, function(i, field){  
            params[field.name] = field.value; //设置查询参数  
        });
        $('#tblUsers').datagrid('reload'); //设置好查询参数 reload 一下就可以了  
	});
});
