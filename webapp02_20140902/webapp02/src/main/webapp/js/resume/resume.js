
/**工具栏**/
var toolbarlist = [{
	text:'添加',
	iconCls:'icon-add',
	handler:function(){
		$('#resumeDialog').dialog({
			title : '新增简历',
			width : 600,
			height : 400,
			closed : false,
			cache : false,
			href : "showDialog.do?url='jltj.do'",
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
		batchOperation("jlsc.do", "ids", $("#tbInfosys"), "确定要删除吗?", true);
//		deleterow();
    }
},'-',{
	text:'修改',
	iconCls:'icon-remove',
	handler:function(){
		updateResume();
    }
},'-',{
	text:'导出',
	iconCls:'icon-save',
	handler:function(){
		leadingOut();
    }
}];


//更新简历信息
function updateResume()
{
	//选中一行使用getSelected，选中多行使用getSelections
	var row = $("#tbInfosys").datagrid('getSelected'); 
	if(row == null || row.length == 0) {
		$.messager.alert('提示', "请先选择要操作的行", 'info'); 
		return;
	}

	$('#resumeDialog').dialog({
		title : '简历修改',
		width : 600,
		height : 400,
		closed : false,
		cache : false,
		href : "showDialog.do?url='jlxg.do?personid="+row.personid+"'",
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
	$('#tbInfosys').datagrid({
		url:'jlcx.do',//path+'/demo_data/resume.json', 
		loadMsg:"正在加载数据，请稍候...",
		sortName: 'name',
		sortOrder: 'desc',
		pagination:true,
		idField:'id', 
		toolbar:toolbarlist,
		frozenColumns:[[
		    			{field:'ck',checkbox:true}
		    		]],
		columns:[[
			{field:'id',title:'详情',width:59,align:'center',hidden:"true"},
//				formatter:function(value,rec){
//					var str =  "<a href=\"#\" onclick=\"resumeInfo('" + value + "')\" >详情</a>&nbsp;&nbsp";
//					var queryParams = $('#tbInfosys').datagrid('options').queryParams;
//					queryParams.personid = $('#personid').val();
//					return str;
//	}},
			{field:'name',title:'姓名',width:100,align:'center'},
			{field:'xingbie',title:'性别',width:100,align:'center'},
			{field:'minzu',title:'民族',width:100,align:'center'},
			{field:'chushengnianyue',title:'出生年月',width:150,align:'center'},
			{field:'xianrenzhuiwu',title:'现任职务',width:150,align:'center'},
			{field:'zhuanyejishuzhiwu',title:'专业技术职务',width:150,align:'center'},
			{field:'canjiagongzuoshijian',title:'参加工作时间',width:150,align:'center'},
			{field:'personid',title:'操作',width:59,align:'center',
			formatter:function(value,rec){
				var str =  "<a href=\"#\" onclick=\"resumeInfo('" + value + "')\" >详情</a>&nbsp;&nbsp";
				return str;
}},

			
			
		]]
	});
}); 



/**表格内的条件查询**/
function query(){
	var queryParams = $('#tbInfosys').datagrid('options').queryParams;
	queryParams.name = $('#name').val();
	queryParams.xianrenzhuiwu = $('#xianrenzhiwu').val();
	queryParams.zhuanyejishuzhiwu = $('#zhuanyejishuzhiwu').val();
	queryParams.canjiagongzuoshijianFrom = $('#canjiagongzuoshijianFrom').val();
	queryParams.canjiagongzuoshijianTo = $('#canjiagongzuoshijianTo').val();
	//重新加载datagrid的数据  
	$("#tbInfosys").datagrid('reload');
}

function resumeInfo (personid){
	
	$('#resumeDialog').dialog({
		
		title : '简历详情',
		width : 600,
		height : 400,
		closed : false,
		cache : false,
		href : "showDialog.do?url='jlxq.do?personid=" + personid + "'",
		modal : true,
		minimizable : true,
		resizable : true,
		onClose : function() {
			$('#tbInfosys').datagrid('reload');
		}
	});
}

//function deleterow(){  
//    $.messager.confirm('提示','确定要删除吗?',function(result){  
//        if (result){  
//            var rows = $('#tbInfosys').datagrid('getSelections');  
//            var ps = "";  
//            $.each(rows,function(i,n){  
//                if(i==0)   
//                    ps += "?id="+n.uid;  
//                else  
//                    ps += "&id="+n.uid;  
//            });  
//            $.post('jlsc'+ps,function(data){  
//                $('#tbInfosys').datagrid('reload');   
//                $.messager.alert('提示',data.mes,'info');  
//            });  
//        }  
//    });  
//}  