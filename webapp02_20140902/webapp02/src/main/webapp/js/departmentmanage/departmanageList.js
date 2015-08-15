/**工具栏**/
var toolbarlist = [{
	text:'添加',
	iconCls:'icon-add',
	handler:function(){
		addDeparts();
	}
},'-',{
	text:'删除',
	iconCls:'icon-remove',
	handler:function(){
		delDeparts();
    }
}];
$(function(){
	/**表格**/
	$('#tbdeparts').datagrid({
		url:path+'/demo_data/departmanageList.json', 
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
			{field:'charge_unit',title:'主管单位',width:150},
			{field:'administration_level',title:'行政级别',width:150,align:'center'},
			{field:'create_time',title:'成立时间',width:150,align:'center',sortable:true},
			{field:'message',title:'信息',width:140,align:'center'},
			{field:'organization_category',title:'机构类别',width:140,align:'center'},
			{field:'detailed',title:'详细',width:140,align:'center'},
			{field:'id',title:'操作',width:150,align:'center',
			formatter:function(value,rec){
				var str =  "<a href=\"#\" onclick=\"editDeparts('" + value + "')\" >编辑</a>&nbsp;&nbsp";
				str +=  "<a href=\"#\" onclick=\"delSingle('" + value + "')\" >删除</a>&nbsp;&nbsp";
				return str;
			}}
		]]
	});
	
}); 

/**增加部门与机构**/
function addDeparts()
{
	//使用tab页打开
	parent.addtabs('添加部门与机构',path+'/page/departmentmanage/departmanageAdd.jsp');
}
/**编辑部门与机构**/
function editDeparts(id)
{
	//使用tab页打开
	parent.addtabs('编辑部门与机构',path+'/page/departmentmanage/departmanageAdd.jsp');
}
	
//删除多个信息系统
function delInfosys(){
	var rows = $('#tbdeparts').datagrid('getSelections');
    var ids = [];
    for(var i=0;i<rows.length;i++){
    	ids.push(rows[i].id);
    }
    var idstr = ids.join(';');
    if(idstr == "") {
    	$.messager.alert('提示信息','请选择要删除的部门与机构！','error');
    	return;
    }
	$.messager.confirm("信息确认","你确定要删除选择的部门与机构吗？", function(result) {
       	if(result == true) {
       		
		}
      });
}
	
//单一删除
function delSingle(id){
    var ids = [];
    ids.push(id);
	$.messager.confirm("信息确认","你确定要删除选择的部门与机构吗？", function(result) {
       	if(result == true) {
       		
		}
      });
}
