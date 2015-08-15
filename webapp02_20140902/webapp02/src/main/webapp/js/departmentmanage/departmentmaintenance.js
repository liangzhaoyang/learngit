var xingzhengjibeiList = [ {
	"value" : "3",
	"text" : "正厅局级"
}, {
	"value" : "4",
	"text" : "副厅局级"
}, {
	"value" : "5",
	"text" : "正县处级"
}, {
	"value" : "6",
	"text" : "副县处级"
}, {
	"value" : "7",
	"text" : "正乡科级"
}, {
	"value" : "8",
	"text" : "副乡科级"
} ];
var jichusheshileibieList = [ {
	"value" : "1",
	"text" : "宣传教育基地"
}, {
	"value" : "2",
	"text" : "销毁中心"
}, {
	"value" : "3",
	"text" : "测评（分）中心"
}, {
	"value" : "4",
	"text" : "保密技术科研单位"
}, {
	"value" : "5",
	"text" : "其他"
} ];

/** 工具栏* */
var xzbmtoolbar = [
		{
			text : '添加',
			iconCls : 'icon-add',
			handler : function() {
				// $('#tbxingzhengbumen').datagrid('insertRow', {
				// index : 0,
				// row : {}
				// });
				// $('#tbxingzhengbumen').datagrid('beginEdit', 0);
				$('#tbxingzhengbumen').datagrid('appendRow', {
					row : {}
				});
				lastIndex = $('#tbxingzhengbumen').datagrid('getRows').length - 1;
				$('#tbxingzhengbumen').datagrid('selectRow', lastIndex);
				$('#tbxingzhengbumen').datagrid('beginEdit', lastIndex);
			}
		},
		'-',
		{
			text : '修改',
			iconCls : 'icon-remove',
			handler : function() {
				var row = $('#tbxingzhengbumen').datagrid('getSelected');
				if (row == null || row.length == 0) {
					$.messager.alert('提示', "请先选择要操作的行", 'info');
					return;
				} else {
					var rowIndex = $("#tbxingzhengbumen").datagrid(
							'getRowIndex', row);
					$('#tbxingzhengbumen').datagrid('beginEdit', rowIndex);

				}
			}
		},
		'-',
		{
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				var rows = $("#tbxingzhengbumen").datagrid('getSelections');
				if (rows == null || rows.length == 0) {
					$.messager.alert('提示', "请先选择要操作的行", 'info');
					return;
				} else {
					$.each(rows, function(i, row) {
						var rowIndex = $("#tbxingzhengbumen").datagrid(
								'getRowIndex', row);
						$('#tbxingzhengbumen').datagrid('deleteRow', rowIndex);
					});
				}
			}
		} ];
//
var gljgtoolbar = [
		{
			text : '添加',
			iconCls : 'icon-add',
			handler : function() {
				var editRow = undefined;
				if (editRow != undefined) {
					$('#tbguanlijigou').datagrid('endEdit', editRow);
				} else {
					$('#tbguanlijigou').datagrid('appendRow', {
						row : {}
					});
					lastIndex = $('#tbguanlijigou').datagrid('getRows').length - 1;
					$('#tbguanlijigou').datagrid('selectRow', lastIndex);
					$('#tbguanlijigou').datagrid('beginEdit', lastIndex);
				}
			}
		},
		'-',
		{
			text : '修改',
			iconCls : 'icon-remove',
			handler : function() {
				var editRow = undefined;
				if (editRow != undefined) {
					var editRow = undefined;
					$('#tbguanlijigou').datagrid('endEdit', editRow);
				} else {
					var row = $('#tbguanlijigou').datagrid('getSelected');
					editRow = row;
					if (row == null || row.length == 0) {
						$.messager.alert('提示', "请先选择要操作的行", 'info');
						return;
					} else {
						var rowIndex = $("#tbguanlijigou").datagrid(
								'getRowIndex', row);
						$('#tbguanlijigou').datagrid('beginEdit', rowIndex);

					}

				}
			}
		},
		'-',
		{
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				var rows = $("#tbguanlijigou").datagrid('getSelections');
				if (rows == null || rows.length == 0) {
					$.messager.alert('提示', "请先选择要操作的行", 'info');
					return;
				} else {
					$.each(rows, function(i, row) {
						var rowIndex = $("#tbguanlijigou").datagrid(
								'getRowIndex', row);
						$('#tbguanlijigou').datagrid('deleteRow', rowIndex);
					});
				}

			}
		} ];
//
var sydwtoolbar = [
		{
			text : '添加',
			iconCls : 'icon-add',
			handler : function() {
				var editRow = undefined;
				if (editRow != undefined) {
					$('#tbshiyedanwei').datagrid('endEdit', editRow);
				} else {
					// $('#tbshiyedanwei').datagrid('insertRow', {
					// index : 0,
					// row : {}
					// });
					// $('#tbshiyedanwei').datagrid('beginEdit', 0);
					$('#tbshiyedanwei').datagrid('appendRow', {
						row : {}
					});
					lastIndex = $('#tbshiyedanwei').datagrid('getRows').length - 1;
					$('#tbshiyedanwei').datagrid('selectRow', lastIndex);
					$('#tbshiyedanwei').datagrid('beginEdit', lastIndex);
				}
			}
		},
		'-',
		{
			text : '修改',
			iconCls : 'icon-remove',
			handler : function() {
				var editRow = undefined;
				if (editRow != undefined) {
					var editRow = undefined;
					$('#tbshiyedanwei').datagrid('endEdit', editRow);
				} else {
					var row = $('#tbshiyedanwei').datagrid('getSelected');
					editRow = row;
					if (row == null || row.length == 0) {
						$.messager.alert('提示', "请先选择要操作的行", 'info');
						return;
					} else {
						var rowIndex = $("#tbshiyedanwei").datagrid(
								'getRowIndex', row);
						$('#tbshiyedanwei').datagrid('beginEdit', rowIndex);

					}

				}
			}
		},
		'-',
		{
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				var rows = $("#tbshiyedanwei").datagrid('getSelections');
				if (rows == null || rows.length == 0) {
					$.messager.alert('提示', "请先选择要操作的行", 'info');
					return;
				} else {
					$.each(rows, function(i, row) {
						var rowIndex = $("#tbshiyedanwei").datagrid(
								'getRowIndex', row);
						$('#tbshiyedanwei').datagrid('deleteRow', rowIndex);
					});
				}

			}
		} ];
//
var jcsstoolbar = [
		{
			text : '添加',
			iconCls : 'icon-add',
			handler : function() {
				var editRow = undefined;
				if (editRow != undefined) {
					$('#tbjichusheshi').datagrid('endEdit', editRow);
				} else {
					$('#tbjichusheshi').datagrid('appendRow', {
						row : {}
					});
					lastIndex = $('#tbjichusheshi').datagrid('getRows').length - 1;
					$('#tbjichusheshi').datagrid('selectRow', lastIndex);
					$('#tbjichusheshi').datagrid('beginEdit', lastIndex);
				}
			}
		},
		'-',
		{
			text : '修改',
			iconCls : 'icon-remove',
			handler : function() {
				var editRow = undefined;
				if (editRow != undefined) {
					var editRow = undefined;
					$('#tbjichusheshi').datagrid('endEdit', editRow);
				} else {
					var row = $('#tbjichusheshi').datagrid('getSelected');
					editRow = row;
					if (row == null || row.length == 0) {
						$.messager.alert('提示', "请先选择要操作的行", 'info');
						return;
					} else {
						var rowIndex = $("#tbjichusheshi").datagrid(
								'getRowIndex', row);
						$('#tbjichusheshi').datagrid('beginEdit', rowIndex);

					}

				}
			}
		},
		'-',
		{
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				var rows = $("#tbjichusheshi").datagrid('getSelections');
				if (rows == null || rows.length == 0) {
					$.messager.alert('提示', "请先选择要操作的行", 'info');
					return;
				} else {
					$.each(rows, function(i, row) {
						var rowIndex = $("#tbjichusheshi").datagrid(
								'getRowIndex', row);
						$('#tbjichusheshi').datagrid('deleteRow', rowIndex);
					});
				}

			}
		} ];

$(function() {
	/*
	 * //数据初始化 $.get("bmxzclcx.do", function(data, status) { if(status ==
	 * "success") { var xingzhengjibeiList1=data.xzjblist; //var
	 * jichusheshileibieList1=data.lblist;
	 * 
	 * xingzhengjibeiList2='['; //把常量赋给列表
	 * $.each(xingzhengjibeiList1,function(i,row){ //alert(row.code); if(i==0)
	 * xingzhengjibeiList2 +='{"value":"'+row.code+'","text":"'+row.name+'"}';
	 * else xingzhengjibeiList2
	 * +=',{"value":"'+row.code+'","text":"'+row.name+'"}';
	 * 
	 * }); xingzhengjibeiList2 +=']'; alert(xingzhengjibeiList2);
	 * xingzhengjibeiList = jQuery.parseJSON(xingzhengjibeiList2); } });
	 */
	$('#tbxingzhengbumen').datagrid({
		url : 'bmxzxzbmcx.do',
		singleSelect : false,
		striped : true,
		loadMsg : "正在加载数据，请稍候...",
		rownumbers : true,
		// sortName: 'reality_staff',
		sortOrder : 'desc',
		// pagination : true,
		checkOnSelect : true,
		// pageList:[1,2,3,4], //设置分页显示的每页显示条数
		toolbar : xzbmtoolbar,
		frozenColumns : [ [ {
			field : 'ck',
			checkbox : true
		} ] ],
		columns : [ [ {
			field : 'name',
			title : '部门名称',
			width : 150,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'bianzhirenshu',
			title : '编制人数',
			width : 150,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'shijirenshu',
			title : '实有人数',
			width : 150,
			align : 'center',
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'xingzhengjibie',
			title : '行政级别',
			width : 150,
			align : 'center',
			formatter : function(value) {
				for (var i = 0; i < xingzhengjibeiList.length; i++) {
					if (xingzhengjibeiList[i].value == value)
						return xingzhengjibeiList[i].text;
				}
				return value;
			},
			editor : {
				type : 'combobox',
				options : {
					data : xingzhengjibeiList,
					valueField : "value",
					textField : "text"
				}
			}
		}, {
			field : 'id',
			title : '操作',
			width : 150,
			align : 'center',
			hidden : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		} ] ]
	});

	$('#tbguanlijigou').datagrid({
		url : 'bmxzglbmcx.do',
		singleSelect : false,
		striped : true,
		loadMsg : "正在加载数据，请稍候...",
		rownumbers : true,
		// sortName: 'reality_staff',
		sortOrder : 'desc',
		// pagination : true,
		checkOnSelect : true,
		// pageList:[1,2,3,4], //设置分页显示的每页显示条数
		toolbar : gljgtoolbar,
		frozenColumns : [ [ {
			field : 'ck',
			checkbox : true
		} ] ],
		columns : [ [ {
			field : 'name',
			title : '部门名称',
			width : 150,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'bianzhirenshu',
			title : '编制人数',
			width : 150,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'shijirenshu',
			title : '实有人数',
			width : 150,
			align : 'center',
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'xingzhengjibie',
			title : '行政级别',
			width : 150,
			align : 'center',
			formatter : function(value) {
				for (var i = 0; i < xingzhengjibeiList.length; i++) {
					if (xingzhengjibeiList[i].value == value)
						return xingzhengjibeiList[i].text;
				}
				return value;
			},
			editor : {
				type : 'combobox',
				options : {
					data : xingzhengjibeiList,
					valueField : "value",
					textField : "text"
				}
			}
		}, {
			field : 'id',
			title : '操作',
			width : 150,
			align : 'center',
			hidden : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		} ] ]
	});

	$('#tbshiyedanwei').datagrid({
		url : 'bmxzsydwcx.do',
		singleSelect : false,
		striped : true,
		loadMsg : "正在加载数据，请稍候...",
		rownumbers : true,
		// view:scrollview,
		// sortName: 'reality_staff',
		sortOrder : 'desc',
		// pagination : true,
		checkOnSelect : true,
		toolbar : sydwtoolbar,
		frozenColumns : [ [ {
			field : 'ck',
			checkbox : true
		} ] ],
		columns : [ [ {
			field : 'name',
			title : '部门名称',
			width : 150,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'bianzhirenshu',
			title : '编制人数',
			width : 150,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'shijirenshu',
			title : '实有人数',
			width : 150,
			align : 'center',
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'xingzhengjibie',
			title : '行政级别',
			width : 150,
			align : 'center',
			formatter : function(value) {
				for (var i = 0; i < xingzhengjibeiList.length; i++) {
					if (xingzhengjibeiList[i].value == value)
						return xingzhengjibeiList[i].text;
				}
				return value;
			},
			editor : {
				type : 'combobox',
				options : {
					data : xingzhengjibeiList,
					valueField : "value",
					textField : "text"
				}
			}
		}, {
			field : 'id',
			title : '操作',
			width : 150,
			align : 'center',
			hidden : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		} ] ]
	});

	$('#tbjichusheshi').datagrid({
		url : 'bmxzjcsscx.do',
		singleSelect : false,
		striped : true,
		loadMsg : "正在加载数据，请稍候...",
		rownumbers : true,
		// view:scrollview,
		// sortName: 'depart_code',
		sortOrder : 'desc',
		// pagination : true,
		checkOnSelect : true,
		// pageList:[1,2,3,4], //设置分页显示的每页显示条数
		toolbar : jcsstoolbar,
		frozenColumns : [ [ {
			field : 'ck',
			checkbox : true
		} ] ],
		columns : [ [ {
			field : 'name',
			title : '单位名称',
			width : 150,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'leibie',
			title : '基础设施类别',
			width : 150,
			align : 'center',
			formatter : function(value) {
				for (var i = 0; i < jichusheshileibieList.length; i++) {
					if (jichusheshileibieList[i].value == value)
						return jichusheshileibieList[i].text;
				}
				return value;
			},
			editor : {
				type : 'combobox',
				options : {
					data : jichusheshileibieList,
					valueField : "value",
					textField : "text"
				}
			}
		}, {
			field : 'mianji',
			title : '占地面积(平方米)',
			width : 150,
			align : 'center',
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'touruzijin',
			title : '投入资金(万元)',
			width : 150,
			align : 'center',
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'jianshedanwei',
			title : '建设单位',
			width : 150,
			align : 'center',
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'id',
			title : '操作',
			width : 150,
			align : 'center',
			hidden : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		} ] ]
	});

	// tab页切换
	$("#move-animate-left").tabso({
		cntSelect : "#leftcon",
		tabEvent : "click",
		tabStyle : "move-animate",
		direction : "left"
	});

	$("#jigoufenlei2").hide();
	//

});

function orgInfoSave() {
	// alert("fafaf");
	$('#jigouweihufrm').form('submit', {
		onSubmit : function() {
			if (!checkInput()) {
				return false;
			}
			// alert("fafa");
			jigouxinxishezhi();

			return true;
		},
		success : function(data) {
			if (data == "success") {
				// window.parent.$("#userDialog").dialog("close");
				$('#tbjichusheshi').datagrid('reload');
				$('#tbxingzhengbumen').datagrid('reload');
				$('#tbguanlijigou').datagrid('reload');
				$('#tbshiyedanwei').datagrid('reload');
				alert("机构信息保存成功!");
			} else {
				alert(data);
			}
		}
	});

}
function jigouxinxishezhi() {
	// 行政信息设置
	var xzbminsertrows = $('#tbxingzhengbumen').datagrid('getChanges',
			'inserted');
	var xzbmupdaterows = $('#tbxingzhengbumen').datagrid('getRows');
	var xzbmdeleterows = $('#tbxingzhengbumen').datagrid('getChanges',
			'deleted');
	setJigouShezhi(xzbminsertrows, '#tbxingzhengbumen', '#xzbminsert');
	setJigouShezhi(xzbmupdaterows, '#tbxingzhengbumen', '#xzbmupdate');
	setJigouShezhi(xzbmdeleterows, '#tbxingzhengbumen', '#xzbmdelete');
	// 管理信息设置
	var gljginsertrows = $('#tbguanlijigou').datagrid('getChanges', 'inserted');
	var gljgupdaterows = $('#tbguanlijigou').datagrid('getRows');
	var gljgdeleterows = $('#tbguanlijigou').datagrid('getChanges', 'deleted');
	setJigouShezhi(gljginsertrows, '#tbguanlijigou', '#gljginsert');
	setJigouShezhi(gljgupdaterows, '#tbguanlijigou', '#gljgupdate');
	setJigouShezhi(gljgdeleterows, '#tbguanlijigou', '#gljgdelete');
	// 事业单位信息设置
	var sydwinsertrows = $('#tbshiyedanwei').datagrid('getChanges', 'inserted');
	var sydwupdaterows = $('#tbshiyedanwei').datagrid('getRows');
	var sydwdeleterows = $('#tbshiyedanwei').datagrid('getChanges', 'deleted');
	setJigouShezhi(sydwinsertrows, '#tbshiyedanwei', '#sydwinsert');
	setJigouShezhi(sydwupdaterows, '#tbshiyedanwei', '#sydwupdate');
	setJigouShezhi(sydwdeleterows, '#tbshiyedanwei', '#sydwdelete');
	// 基础设施设置
	var insertrows = $('#tbjichusheshi').datagrid('getChanges', 'inserted');
	var updaterows = $("#tbjichusheshi").datagrid("getRows");
	var deleterows = $('#tbjichusheshi').datagrid('getChanges', 'deleted');
	setJichuSheshi(insertrows, '#tbjichusheshi', '#jcssinsert');
	setJichuSheshi(updaterows, '#tbjichusheshi', '#jcssupdate');
	setJichuSheshi(deleterows, '#tbjichusheshi', '#jcssdelete');
}
/*
 * function updateActions(){ var rows=$("#tbjichusheshi").datagrid("getRows");
 * if (rows.length>0) { $.each(rows,function(i,row1){ row1.editing = false;
 * //var rowIndex = $(tblObject).datagrid('getRowIndex', row1);
 * $('#tbjichusheshi').datagrid('updateRow',{ index:i, row:{} }); }); } }
 */
// 机构基本信息设置
function setJigouShezhi(rows, tblObject, Obj) {
	if (rows.length > 0) {

		var info = "";
		//
		$.each(rows, function(i, row) {
			var rowIndex = $(tblObject).datagrid('getRowIndex', row);
			// alert(rowIndex);
			// 保存行的编辑信息
			$(tblObject).datagrid('endEdit', rowIndex);
			if ((row.id != "") || (row.name != "") || (row.bianzhirenshu != "")
					|| (row.shijirenshu != "") || (row.xingzhengjibie != "")) {
				info += row.id + ',';
				info += row.name + ',';
				info += row.bianzhirenshu + ',';
				info += row.shijirenshu + ',';
				if (row.xingzhengjibie != "")
					info += row.xingzhengjibie + ";";
				else
					info += "0;";
			}
		});
		$(Obj).val("");
		$(Obj).val(info);
		// alert(info);
	}
}
// 基础设施添加
function setJichuSheshi(rows, tblObject, insertObject) {

	if (rows.length > 0) {

		var jcss = "";
		//
		$.each(rows, function(i, row) {
			var rowIndex = $(tblObject).datagrid('getRowIndex', row);
			// 保存行的编辑信息
			$(tblObject).datagrid('endEdit', rowIndex);
			if ((row.id != "") || (row.name != "") || (row.leibie != "")
					|| (row.mianji != "") || (row.touruzijin != "")
					|| (row.jianshedanwei != "")) {
				jcss += row.id + ',';
				jcss += row.name + ',';
				jcss += row.leibie + ',';
				jcss += row.mianji + ',';
				jcss += row.touruzijin + ',';
				if (row.jianshedanwei != "")
					jcss += row.jianshedanwei + ";";
				else
					jcss += '#;';
			}

		});
		$(insertObject).val("");
		$(insertObject).val(jcss);
	}

}

//
function savejcssrow(target) {
	$('#tbjichusheshi').datagrid('endEdit', getRowIndex(target));
}

// 机构类别选择1
function selChange(value) {
	// 隐藏保密学院信息
	$("#baomixueyuan").hide();
	// 如果是“其他”，显示类别2
	if ($("#jigoufenlei1").val() == "3") {
		$("#jigoufenlei2").show();
		// 如果保密学院已被选中，则直接显示
		if ($("#jigoufenlei2").val() == "3") {
			$("#baomixueyuan").show();
		}
	} else {
		$("#jigoufenlei2").hide();
	}
	// 如果保密办被选中，显示保密办输入信息
	if ($("#jigoufenlei1").val() == "2") {
		$("#baomiban1").show();
		$("#baomiban2").show();
	} else {
		$("#baomiban1").hide();
		$("#baomiban2").hide();
	}
}
// 机构类别选择2
function selectChange() {

	if ($("#jigoufenlei2").val() == "3") {
		$("#baomixueyuan").show();
	} else {
		$("#baomixueyuan").hide();
	}
}
// 检查输入
function checkInput() {
	if ($("#orgname").val() == "") {
		alert("单位名称不能为空！");
		return false;
	}
	if ($("#suorgid").val() == "") {
		alert("主管单位不能为空！");
		return false;
	}
	if ($("#chenglishijian").combobox("getValue") == "") {
		alert("成立时间不能为空！");
		return false;
	}
	if ($("#xingzhengjibie").val() == "0") {
		alert("行政级别不能为空！");
		return false;
	}
	if ($("#jingfeilaiyuan").val() == "") {
		alert("经费来源不能为空！");
		return false;
	}
	if ($("#zhengfuxulie").val() == "0") {
		alert("政府序列为空！");
		return false;
	}
	if ($("#jigouleibie").val() == "0") {
		alert("机构类别不能为空！");
		return false;
	}
	if ($("#jigoufenlei1").val() == "0") {
		alert("机构分类为空！");
		return false;
	}
	if ($("#shengfen").val() == "0") {
		alert("省份不能为空！");
		return false;
	}
	if ($("#tianbiaoren").val() == "") {
		alert("填表人不能为空！");
		return false;
	}
	if ($("#tianbiaoriqi").combobox("getValue") == "") {
		alert("填表日期不能为空！");
		return false;
	}
	return true;
}
//导出信息
function orgInfoExport()
{
	$.get("bmxzdc.do", function(data, status) {
		if(status == "success") {
			
			//alert(""+data.tianbiaoren+data.tianbiaoriqi);
		}
	});
}