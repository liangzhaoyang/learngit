/**工具栏**/
var xingzhengtoolbarlist = [{
	text:'添加',
	iconCls:'icon-add',
	handler:function(){
		var editRow=undefined;
		
		if(editRow!=undefined)
		{
			$('#tbdepartsadd1').datagrid('endEdit',editRow);
			}
		else
		{
			$('#tbdepartsadd1').datagrid('insertRow',{index:0,row:{}
			});
			$('#tbdepartsadd1').datagrid('beginEdit',0);
		}
	}
},'-',{
	text:'删除',
	iconCls:'icon-remove',
	handler:function(){
		
    }
},'-',{
    	text:'修改',
    	iconCls:'icon-remove',
    	handler:function(){
    		
        }
}];

var toolbarlist = [{
	text:'添加',
	iconCls:'icon-add',
	handler:function(){
		adddepartmessage();
	}
},'-',{
	text:'删除',
	iconCls:'icon-remove',
	handler:function(){
		
    }
},'-',{
	text:'修改',
	iconCls:'icon-remove',
	handler:function(){
		
    }
}];
var toolbarlist1 = [{
	text:'添加',
	iconCls:'icon-add',
	handler:function(){
		addbasemessage();
	}
},'-',{
	text:'删除',
	iconCls:'icon-remove',
	handler:function(){
		
    }
},'-',{
    	text:'修改',
    	iconCls:'icon-remove',
    	handler:function(){
    		
        }
}];
$(function(){
	/**表格**/
	/*
	$('#tbdepartsadd1').datagrid({
		url:'bmxzxzbmcx.do', 
		singleSelect:false,  
		striped:true,
		loadMsg:"正在加载数据，请稍候...",
		rownumbers:true,
//		sortName: 'reality_staff',
//		sortOrder: 'desc',
		pagination:true,
		checkOnSelect:true,
		//pageList:[1,2,3,4], //设置分页显示的每页显示条数
		toolbar:xingzhengtoolbarlist,
		frozenColumns:[[
			{field:'ck',checkbox:true}
		]],
		columns:[[
			{field:'xzbmname',title:'部门名称',width:150,editor:{type:'text',options:{required:true}}},
			{field:'xzbmbianzhirenshu',title:'编制人数',width:150,editor:{type:'text',options:{required:true}}},
			{field:'xzbmshijirenshu',title:'实有人数',width:150,align:'center',sortable:true,editor:{type:'text',options:{required:true}}},
			{field:'xzbmxingzhengjibie',title:'行政级别',width:150,align:'center',editor:{type:'text',options:{required:true}}},
			{field:'xzbmid',title:'操作',width:150,align:'center',hidden:true
			}
		]]
	});
	
	
	$('#tbdepartsadd2').datagrid({
		url:'bmxzglbmcx.do', 
		singleSelect:false,  
		striped:true,
		loadMsg:"正在加载数据，请稍候...",
		rownumbers:true,
//		sortName: 'reality_staff',
//		sortOrder: 'desc',
		pagination:true,
		checkOnSelect:true,
		//pageList:[1,2,3,4], //设置分页显示的每页显示条数
		toolbar:toolbarlist,
		frozenColumns:[[
			{field:'ck',checkbox:true}
		]],
		columns:[[
			{field:'name',title:'部门名称',width:150,editor:{type:'text',options:{required:true}}},
			{field:'bianzhirenshu',title:'编制人数',width:150,editor:{type:'text',options:{required:true}}},
			{field:'shijirenshu',title:'实有人数',width:150,align:'center',sortable:true,editor:{type:'text',options:{required:true}}},
			{field:'xingzhengjibie',title:'行政级别',width:150,align:'center',editor:{type:'text',options:{required:true}}},
			{field:'id',title:'操作',width:150,align:'center',hidden:true
			}
		]]
	});
//--
	$('#tbdepartsadd3').datagrid({
		url:'bmxzsydwcx.do', 
		singleSelect:false,  
		striped:true,
		loadMsg:"正在加载数据，请稍候...",
		rownumbers:true,
//		sortName: 'reality_staff',
//		sortOrder: 'desc',
		pagination:true,
		checkOnSelect:true,
		//pageList:[1,2,3,4], //设置分页显示的每页显示条数
		toolbar:toolbarlist,
		frozenColumns:[[
			{field:'ck',checkbox:true}
		]],
		columns:[[
			{field:'name',title:'部门名称',width:150,editor:{type:'text',options:{required:true}}},
			{field:'bianzhirenshu',title:'编制人数',width:150,editor:{type:'text',options:{required:true}}},
			{field:'shijirenshu',title:'实有人数',width:150,align:'center',sortable:true,editor:{type:'text',options:{required:true}}},
			{field:'xingzhengjibie',title:'行政级别',width:150,align:'center',editor:{type:'text',options:{required:true}}},
			{field:'id',title:'操作',width:150,align:'center',hidden:true
			}
		]]
	});
	//--
	$('#tbdepartsadd4').datagrid({
		url:'bmxzjcsscx.do', 
		singleSelect:false,  
		striped:true,
		loadMsg:"正在加载数据，请稍候...",
		rownumbers:true,
//		sortName: 'depart_code',
//		sortOrder: 'desc',
		pagination:true,
		checkOnSelect:true,
		//pageList:[1,2,3,4], //设置分页显示的每页显示条数
		toolbar:toolbarlist1,
		frozenColumns:[[
			{field:'ck',checkbox:true}
		]],
		columns:[[
			//{field:'depart_code',title:'序号',width:150,sortable:true},
			{field:'name',title:'单位名称',width:150},
			{field:'leibie',title:'基础设施类别',width:150,align:'center'},
			{field:'mianji',title:'占地面积(平方米)',width:150,align:'center'},
			{field:'touruzijin',title:'投入资金(万元)',width:150,align:'center'},
			{field:'id',title:'操作',width:150,align:'center',hidden:true
			}
		]]
	});
	*/
	$("#move-animate-left").tabso({
		cntSelect:"#leftcon",
		tabEvent:"click",
		tabStyle:"move-animate",
		direction : "left"
	});
});

function infosysSave() {
	/*
	var content = $("#tbdepartsadd1").datagrid('getRows');
	alert(content);
	$.ajax({
			type: "POST",
			url: 'doxzbmbc.do',
			data:JSON.stringify({"xzbm":[{xzbmname:"Tom",xzbmbianzhirenshu:12},{xzbmname:"Petter",xzbmbianzhirenshu:14}]}),
			datatype: "json",contentType: "application/json; charset=utf-8",
			success: function(msg){
	   		if(msg){
	    		$.messager.alert('提示信息','信息系统删除成功','info', function(){});
	    	}else{
	    		$.messager.alert('提示信息','信息系统删除失败','info', function(){});
	    	}
			//$('#tbInfosys').datagrid("reload");
			}
	}); 
*/
	$('#bumenWeihuForm').form('submit', {
		onSubmit : function() {
//			if (!checkInput()) {
//				return false;
//			}
			return true;
		},
		success : function(data) {
			if (data == "success") {
//				window.parent.$("#userDialog").dialog("close");
				alert("机构信息保存成功!");
			} else {
				alert(data);
				alert(typeof (data));
			}
		}
	});
	
}
//机构类别选择1
function selChange(value)
{
	//隐藏保密学院信息
	$("#baomixueyuan").hide();
	//如果是“其他”，显示类别2
	if($("#jigoufenlei1").val()=="3")
	{
		$("#jigoufenlei2").show();
		//如果保密学院已被选中，则直接显示
		if($("#jigoufenlei2").val()=="3")
		{
			$("#baomixueyuan").show();
			}
	}
	else
	{
		$("#jigoufenlei2").hide();
		}
	//如果保密办被选中，显示保密办输入信息
	if($("#jigoufenlei1").val()=="2")
	{
		$("#baomiban1").show();
		$("#baomiban2").show();
		}
	else
	{
		$("#baomiban1").hide();
		$("#baomiban2").hide();
		}
	}
//机构类别选择2
function selectChange()
{
	
	if($("#jigoufenlei2").val()=="3")
	{
		$("#baomixueyuan").show();
		}
	else
	{
		$("#baomixueyuan").hide();
		}
}

function infoUpdate()
{
	var innerHtml = $("#tbdepartsadd1").html().substr(0, $("#tbdepartsadd1").html().length - 8) + "<tr><td><input type='checkbox' id='aaa'/> <td><input type='text' class='input'/></td><td><input type='text'  class='input'/></td><td><input type='text'  class='input'/></td><td><input type='text'  class='input'/></td></tr></tbody>";
	$("#tbdepartsadd1").html(innerHtml);

}

function adddepartmessage(){
   openWinWidthHeight(path+'/page/departmentmanage/departmanageAddWin.jsp', "添加",500,330);
}

function editdepartmessage(id){
   openWinWidthHeight(path+'/page/departmentmanage/departmanageAddWin.jsp', "编辑",500,330);
}
function addbasemessage(){
   openWinWidthHeight(path+'/page/departmentmanage/departbaseAddWin.jsp', "添加",500,330);
}

function editbasemessage(id){
   openWinWidthHeight(path+'/page/departmentmanage/departbaseAddWin.jsp', "编辑",500,330);
}