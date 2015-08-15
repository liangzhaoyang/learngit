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
		url:path+'/demo_data/organzationmanageList.json', 
		singleSelect:false,  
		striped:true,
		loadMsg:"正在加载数据，请稍候...",
		rownumbers:true,
		sortName: 'create_time',
		sortOrder: 'desc',
		pagination:true,
		checkOnSelect:true,
		//pageList:[1,2,3,4], //设置分页显示的每页显示条数
		toolbar:toolbarlist,
		frozenColumns:[[
			{field:'ck',checkbox:true}
		]],
		columns:[[
			{field:'unit_name',title:'单位名称',width:150},
			{field:'create_time',title:'成立时间',width:150,sortable:true},
			{field:'administration_level',title:'行政级别',width:150,align:'center'},
			{field:'pay_from',title:'经费来源',width:150,align:'right'},
			{field:'is_gov',title:'是否政府序列',width:140,align:'center'},
			{field:'org_level',title:'机构类别',width:140,align:'center'},
			{field:'id',title:'操作',width:150,align:'center',
			formatter:function(value,rec){
				var str =  "<a href=\"javascript:void(0);\" onclick=\"editorganman('" + value + "')\" >编辑</a>&nbsp;&nbsp";
				str +=  "<a href=\"javascript:void(0);\" onclick=\"delSingle('" + value + "')\" >删除</a>&nbsp;&nbsp";
				return str;
			}}
		]]
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

