/**工具栏**/
var toolbarlist = [{
	text:'添加',
	iconCls:'icon-add',
	handler:function(){
		addorganman();
	}
},'-',{
	text:'删除',
	iconCls:'icon-remove',
	handler:function(){
    }
}];


$(function(){
	/**表格**/
	$('#tborgans').datagrid({
		url:"JgcxInfo.do", 
		singleSelect:false,  
		striped:true,
		loadMsg:"正在加载数据，请稍候...",
		rownumbers:true,
		//sortName: 'create_time',
		//sortOrder: 'desc',
		//pagination:true,
		checkOnSelect:true,
		//pageList:[1,2,3,4], //设置分页显示的每页显示条数
		//toolbar:toolbarlist,
//		frozenColumns:[[
//			{field:'ck',checkbox:true}
//		]],
		columns:[[
			{field:'orgname',title:'单位名称(全称)',width:120,align:'center',
			},
			{field:'suorgid',title:'主管单位',width:120,align:'center',
			},
			{field:'chenglishijian',title:'成立时间',width:120,align:'center',
			},
			{field:'xingzhengjibie',title:'行政级别',width:120,align:'center',sortable:true,
			},
			{field:'jingfeilaiyuan',title:'经费来源',width:120,align:'center'},
			{field:'zhengfuxulie',title:'是否政府序列',width:120,align:'center'},
			{field:'jigouleibie',title:'机构类别',width:120,align:'center'}
		]]
	});
	//
	$('#tbxingzhengbumen').datagrid({
		url:"jgcxxzbm.do", 
		singleSelect:false,  
		striped:true,
		loadMsg:"正在加载数据，请稍候...",
		rownumbers:true,
		//sortName: 'create_time',
		//sortOrder: 'desc',
		//pagination:true,
		checkOnSelect:true,
		//pageList:[1,2,3,4], //设置分页显示的每页显示条数
		//toolbar:toolbarlist,
//		frozenColumns:[[
//			{field:'ck',checkbox:true}
//		]],
		columns:[[
			{field:'xzname',title:'部门名称',width:80,align:'center',
			},
			{field:'xzbianzhirenshu',title:'编制人数',width:80,align:'center',
			},
			{field:'xzshijirenshu',title:'实际人数',width:80,align:'center',
			},
			{field:'xzxingzhengjibie',title:'行政级别',width:80,align:'center',sortable:true,
			}
		]]
	});
	//
	$('#tbguanlijigou').datagrid({
		url:"jgcxgljg.do", 
		singleSelect:false,  
		striped:true,
		loadMsg:"正在加载数据，请稍候...",
		rownumbers:true,
		//sortName: 'create_time',
		//sortOrder: 'desc',
		//pagination:true,
		checkOnSelect:true,
		//pageList:[1,2,3,4], //设置分页显示的每页显示条数
		//toolbar:toolbarlist,
//		frozenColumns:[[
//			{field:'ck',checkbox:true}
//		]],
		columns:[[
			{field:'glname',title:'部门名称',width:80,align:'center',
			},
			{field:'glbianzhirenshu',title:'编制人数',width:80,align:'center',
			},
			{field:'glshijirenshu',title:'实际人数',width:80,align:'center',
			},
			{field:'glxingzhengjibie',title:'行政级别',width:80,align:'center',sortable:true,
			}
		]]
	});
	//
	$('#tbshiyedanwei').datagrid({
		url:"jgcxsybm.do", 
		singleSelect:false,  
		striped:true,
		loadMsg:"正在加载数据，请稍候...",
		rownumbers:true,
		//sortName: 'create_time',
		//sortOrder: 'desc',
		//pagination:true,
		checkOnSelect:true,
		//pageList:[1,2,3,4], //设置分页显示的每页显示条数
		//toolbar:toolbarlist,
//		frozenColumns:[[
//			{field:'ck',checkbox:true}
//		]],
		columns:[[
			{field:'syname',title:'部门名称',width:80,align:'center',
			},
			{field:'sybianzhirenshu',title:'编制人数',width:80,align:'center',
			},
			{field:'syshijirenshu',title:'实际人数',width:80,align:'center',
			},
			{field:'syxingzhengjibie',title:'行政级别',width:80,align:'center',sortable:true,
			}
		]]
	});
	//
	$('#tbjichusheshi').datagrid({
		url:"jgcxjcss.do", 
		singleSelect:false,  
		striped:true,
		loadMsg:"正在加载数据，请稍候...",
		rownumbers:true,
		//sortName: 'create_time',
		//sortOrder: 'desc',
		//pagination:true,
		checkOnSelect:true,
		//pageList:[1,2,3,4], //设置分页显示的每页显示条数
		//toolbar:toolbarlist,
//		frozenColumns:[[
//			{field:'ck',checkbox:true}
//		]],
		columns:[[
			{field:'jcid',title:'序号',width:120,align:'center',
			},
			{field:'jcname',title:'单位名称',width:120,align:'center',
			},
			{field:'jcleibie',title:'基础设施类别',width:120,align:'center',
			},
			{field:'jcmianji',title:'占地面积(平方米)',width:120,align:'center',
			},
			{field:'jctouruzijin',title:'投入资金(万元)',width:120,align:'center',sortable:true,
			},
			{field:'jcjianshedanwei',title:'建设单位',width:120,align:'center'}
		]]
	});
	
	$('#tbbaomijulingdaobangzi').datagrid({
		url:"jgcxbmjldbz.do", 
		singleSelect:false,  
		striped:true,
		loadMsg:"正在加载数据，请稍候...",
		rownumbers:true,
		//sortName: 'create_time',
		//sortOrder: 'desc',
		//pagination:true,
		checkOnSelect:true,

		columns:[[
//			{field:'id',title:'序号',width:80,align:'center',
//			},
			{field:'zhiwu',title:'职务',width:80,align:'center',
			},
			{field:'name',title:'姓名',width:80,align:'center',
			},
			{field:'chushengnianyue',title:'出生年月',width:80,align:'center',
			},
			{field:'xueli',title:'学历',width:80,align:'center',sortable:true,
			},
			{field:'zhengzhimianmao',title:'政治面貌',width:80,align:'center',
			},
			{field:'zhuanye',title:'专业',width:80,align:'center',
			},
			{field:'xingzhengjibie',title:'行政级别',width:80,align:'center',
			},
			{field:'jishuzhicheng',title:'技术职称',width:80,align:'center',
			},
			{field:'renmingdanwei',title:'任命单位',width:80,align:'center',
			},
			{field:'shifouzhuanzhi',title:'是否专职',width:80,align:'center',
			},
			{field:'jianrenqitazhiwu',title:'兼任其他职务',width:80,align:'center',
			},
			{field:'kaishiriqi',title:'何时从事保密工作',width:80,align:'center',
			},
			{field:'suoshubumen',title:'部门名称',width:80,align:'center',
			},
			{field:'danweimingcheng',title:'单位名称',width:80,align:'center',
			}
		]]
	});
	//
	$('#tbbaomiganbu').datagrid({
		url:"jgcxbmgb.do", 
		singleSelect:false,  
		striped:true,
		loadMsg:"正在加载数据，请稍候...",
		rownumbers:true,
		//sortName: 'create_time',
		//sortOrder: 'desc',
		//pagination:true,
		checkOnSelect:true,

		columns:[[
		//	{field:'id',title:'序号',width:80,align:'center',
		//	},
			{field:'jigouleibie',title:'机构类别',width:80,align:'center',
			},
			{field:'xingzhengjibie1',title:'行政级别',width:80,align:'center',
			},
			{field:'bianzhirenshu',title:'编制人数',width:80,align:'center',
			},
			{field:'zhiwu',title:'职务',width:80,align:'center',
			},
			{field:'name',title:'姓名',width:80,align:'center',
			},
			{field:'chushengnianyue',title:'出生年月',width:80,align:'center',
			},
			{field:'xueli',title:'学历',width:80,align:'center',sortable:true,
			},
			{field:'zhengzhimianmao',title:'政治面貌',width:80,align:'center',
			},
			{field:'zhuanye',title:'专业',width:80,align:'center',
			},
			{field:'xingzhengjibie',title:'行政级别',width:80,align:'center',
			},
			{field:'jishuzhicheng',title:'技术职称',width:80,align:'center',
			},
			{field:'renmingdanwei',title:'renmingdanwei',width:80,align:'center',
			},
			{field:'shifouzhuanzhi',title:'是否专职',width:80,align:'center',
			},
			{field:'jianrenqitazhiwu',title:'兼任其他职务',width:80,align:'center',
			},
			{field:'kaishiriqi',title:'何时从事保密工作',width:80,align:'center',
			},
			{field:'suoshubumen',title:'bumenmingcheng',width:80,align:'center',
			},
			{field:'danweimingcheng',title:'单位名称',width:80,align:'center',
			}
		]]
	});
	
	$('#tbshemirenyuan').datagrid({
		url:'jgcxsmrycx.do',
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
		
		//	{field:'zhiwu',title:'职务',width:160},
			{field:'name',title:'姓名',width:50,align:'center'},
			{field:'xingbie',title:'性别',width:50,align:'center'},
			{field:'chushengnianyue',title:'出生年月',width:80,align:'center'},
			{field:'minzu',title:'民族',width:50,align:'center'},	
			{field:'zhengzhimianmao',title:'政治面貌',width:80,align:'center'},
			{field:'xueli',title:'学历',width:80,align:'center'},
			{field:'shemidengji',title:'涉密等级',width:50,align:'center'},
			//{field:'zhuanye',title:'专业',width:100,align:'center'},
			{field:'xingzhengjibie',title:'行政级别',width:60,align:'center'},
			{field:'jishuzhicheng',title:'技术职程',width:60,align:'center'},	
			{field:'yaohaibumengrenyuan',title:'是否属于要害部门工作',width:100,align:'center'},	
			{field:'renyuanleixing',title:'人员类型',width:50,align:'center'},
			//{field:'shifouzhuanzhi',title:'是否专职',width:60,align:'center'},
			//{field:'thirteen',title:'其他职务',width:70,align:'center'},
			//{field:'kaishiriqi',title:'何时从事保密工作',width:100,align:'center'},
			//{field:'tel',title:'电话',width:80,align:'center'},
			//{field:'mobilenum',title:'手机',width:80,align:'center'},
			{field:'suoshubumen',title:'部门名称',width:60,align:'center'},
			{field:'danweimingcheng',title:'单位名称',width:60,align:'center'}
		]]
	});
}); 


$(function(){
	$.get("jigouchaxunriqi.do", function(data, status) {
		if(status == "success") {
			$('#tianbiaoren').val(data.tianbiaoren);
			$('#tianbiaoriqi').val(data.tianbiaoriqi);
			$('#shenheren').val(data.shenheren);
			//alert(""+data.tianbiaoren+data.tianbiaoriqi);
		}
	});
	
	$("#move-animate-left").tabso({
		cntSelect:"#leftcon",
		tabEvent:"click",
		tabStyle:"move-animate",
		direction : "left"
	});
});
	
/**增加信息系统**/
function addorganman()
{
	//使用tab页打开
	parent.addtabs('添加机构',path+'/page/organizationmanage/organizationmanageAdd.jsp');
}
/**编辑信息系统**/
function editorganman(id)
{
	//使用tab页打开
	parent.addtabs('编辑机构',path+'/page/organizationmanage/organizationmanageAdd.jsp');
}

/**增加信息系统**/
function addInfosys2()
{
	//使用tab页打开
	parent.addtabs('添加系统',path+'/page/info/infosysAdd.jsp');
}

/**表格内的条件查询**/
function query(){
	var queryParams = $('#tbInfosys').datagrid('options').queryParams;
	queryParams.name = $('#name').val();
	//重新加载datagrid的数据  
	$("#tbInfosys").datagrid('reload');
}

	
//删除多个信息系统
function delInfosys(){
	var rows = $('#tbInfosys').datagrid('getSelections');
    var ids = [];
    for(var i=0;i<rows.length;i++){
    	ids.push(rows[i].id);
    }
    var idstr = ids.join(';');
    if(idstr == "") {
    	$.messager.alert('提示信息','请选择要删除的信息系统！','error');
    	return;
    }
	$.messager.confirm("信息确认","你确定要删除选择的信息系统吗？", function(result) {
       	if(result == true) {
       		
		}
      });
}
	
//单一删除
function delSingle(id){
    var ids = [];
    ids.push(id);
	$.messager.confirm("信息确认","你确定要删除选择的信息系统吗？", function(result) {
       	if(result == true) {
       		
		}
      });
}
