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
		rownumbers:false,
		//sortName: 'create_time',
		//sortOrder: 'desc',
		//pagination:true,
		checkOnSelect:true,
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
	
	//行政部门基本信息
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
			{field:'xzshijirenshu',title:'实有人数',width:80,align:'center',
			},
			{field:'xzxingzhengjibie',title:'行政级别',width:80,align:'center',sortable:true,
			}
		]]
	});
	
	//管理机构基本信息
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
			{field:'glshijirenshu',title:'实有人数',width:80,align:'center',
			},
			{field:'glxingzhengjibie',title:'行政级别',width:80,align:'center',sortable:true,
			}
		]]
	});
	
	//事业单位基本信息
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
			{field:'syshijirenshu',title:'实有人数',width:80,align:'center',
			},
			{field:'syxingzhengjibie',title:'行政级别',width:80,align:'center',sortable:true,
			}
		]]
	});
	
	//基础设施基本信息
	$('#tbjichusheshi').datagrid({
		url:"jgcxjcss.do", 
		singleSelect:false,  
		striped:true,
		loadMsg:"正在加载数据，请稍候...",
		rownumbers:true,
		checkOnSelect:true,
		columns:[[
//			{field:'jcid',title:'序号',width:120,align:'center',
//			},
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
	
	//保密局领导班子基本信息
	$('#tbbaomijulingdaobanzi').datagrid({
		url:'jgcxbmjldbz.do', 
		singleSelect:false,  
		striped:true,
		loadMsg:"正在加载数据，请稍候...",
		rownumbers:true,
		pagination:true,
		checkOnSelect:true,
		columns:[[
//			{field:'id',title:'序号',width:80,align:'center',
//			},
			{field:'zhiwu',title:'职务',width:80,align:'center'
			},
			{field:'name',title:'姓名',width:80,align:'center'
			},
			{field:'xingbie',title:'性别',width:80,align:'center'
			},
			{field:'chushengnianyue',title:'出生年月',width:80,align:'center'
			},
			{field:'xueli',title:'学历',width:80,align:'center'
			},
			{field:'zhengzhimianmao',title:'政治面貌',width:80,align:'center'
			},
			{field:'zhuanye',title:'专业',width:80,align:'center'
			},
			{field:'xingzhengjibie',title:'行政级别',width:80,align:'center'
			},
			{field:'jishuzhicheng',title:'技术职称',width:80,align:'center'
			},
			{field:'renmingdanwei',title:'任命单位',width:80,align:'center'
			},
			{field:'shifouzhuanzhi',title:'是否专职',width:80,align:'center'
			},
			{field:'jianrenqitazhiwu',title:'兼任其他职务',width:80,align:'center'
			},
			{field:'kaishiriqi',title:'何时从事保密工作',width:80,align:'center'
			},
			{field:'suoshubumen',title:'部门名称',width:80,align:'center'
			},
			{field:'danweimingcheng',title:'单位名称',width:80,align:'center'
			}
		]]
	});
	
	//保密干部基本信息
	$('#tbbaomiganbu').datagrid({
		url:"jgcxbmgb.do", 
		singleSelect:false,  
		striped:true,
		loadMsg:"正在加载数据，请稍候...",
		rownumbers:true,
		//sortName: 'create_time',
		//sortOrder: 'desc',
		pagination:true,
		checkOnSelect:true,

		columns:[[
			{field:'zhiwu',title:'职务',width:80,align:'center',
			},
			{field:'name',title:'姓名',width:80,align:'center',
			},
			{field:'xingbie',title:'性别',width:80,align:'center',
			},
			{field:'chushengnianyue',title:'出生年月',width:80,align:'center',
			},
			{field:'xueli',title:'学历',width:80,align:'center',sortable:true,
			},
			{field:'zhengzhimianmao',title:'政治面貌',width:80,align:'center',
			},
			{field:'zhuanye',title:'专业',width:80,align:'center',
			},
			{field:'xingzhengjibie2',title:'行政级别',width:80,align:'center',
			},
			{field:'jishuzhicheng',title:'技术职称',width:80,align:'center',
			},
			{field:'shifouzhuanzhi',title:'是否专职',width:80,align:'center',
			},
			{field:'jianrenqitazhiwu',title:'其他职务',width:80,align:'center',
			},
			{field:'kaishiriqi',title:'何时从事保密工作(年)',width:80,align:'center',
			},
			{field:'tel',title:'联系座机电话',width:80,align:'center',
			},
			{field:'mobilenum',title:'手机',width:80,align:'center',
			},
			{field:'suoshubumen',title:'部门名称',width:80,align:'center',
			},
			{field:'danweimingcheng',title:'单位名称',width:80,align:'center',
			}
		]]
	});
	//涉密人员信息表格
	$('#tbshemirenyuan').datagrid({
		url:'jgcxsmrycx.do',
		singleSelect:false,  
		striped:true,
		loadMsg:"正在加载数据，请稍候...",
		rownumbers:true,
		pagination:true,
		checkOnSelect:true,
		//pageList:[1,2,3,4], //设置分页显示的每页显示条数
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
			{field:'jishuzhicheng',title:'技术职级',width:60,align:'center'},	
			{field:'yaohaibumengrenyuan',title:'是否属于要害部门工作人员',width:100,align:'center'},	
			{field:'renyuanleixing',title:'人员类型',width:50,align:'center'},
			{field:'suoshubumen',title:'部门名称',width:60,align:'center'},
			{field:'danweimingcheng',title:'单位名称',width:60,align:'center'}
		]]
	});
	//
	/**简历基本信息表格**/
	$('#tbjianli').datagrid({
		url:'jgcxjl.do',//path+'/demo_data/resume.json', 
		loadMsg:"正在加载数据，请稍候...",
		pagination:true,
		columns:[[
			{field:'jlname',title:'姓名',width:100,align:'center'},
			{field:'jlxingbie',title:'性别',width:100,align:'center'},
			{field:'jlminzu',title:'民族',width:100,align:'center'},
			{field:'jlchushengnianyue',title:'出生年月',width:100,align:'center'},
			{field:'jlxianrenzhuiwu',title:'现任职务',width:100,align:'center'},
			{field:'jlzhuanyejishuzhiwu',title:'专业技术职务',width:100,align:'center'},
			{field:'jlcanjiagongzuoshijian',title:'参加工作时间',width:100,align:'center'},
			{field:'jlid',title:'详情',width:100,align:'center',
				formatter:function(value,rec){
					var str =  "<a href=\"#\" onclick=\"resumeDetail('" + value + "')\" >简历详情</a>&nbsp;&nbsp";
					return str;
			}}			
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

/**简历详情信息**/
function resumeDetail(id)
{
	//alert(id);
	$('#jianlixiangqingDialog').dialog({
		title : '简历详情',
		width : 1000,
		height : 600,
		closed : false,
		cache : false,
		href : "showDialog.do?url='JianliXiangqing.do?id=" + id + "'",
		modal : true,
		minimizable : true,
		resizable : true,
		onClose : function() {
			//$('#tbInfosys').datagrid('reload');
		}
	});
}
