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
	$.get("jigouchaxunriqi.do", function(data, status) {
		if(status == "success") {
			$('#tianbiaoren').val(data.tianbiaoren);
			$('#tianbiaoriqi').val(data.tianbiaoriqi);
			//alert(""+data.tianbiaoren+data.tianbiaoriqi);
		}
	});
	//
	$("#move-animate-left").tabso({
		cntSelect:"#leftcon",
		tabEvent:"click",
		tabStyle:"move-animate",
		direction : "left"
	});
});	

function submitForm() {
	$('#jgshForm').form('submit', {
		onSubmit : function() {
			if (!checkInput()) {
				return false;
			}
			return true;
		},
		success : function(data) {
			if (data == "success") {				
				window.parent.$("#shenheDialog").dialog("close");
			} else {
				alert(data);
				alert(typeof (data));
			}	
		}
	});
}

function checkInput() {
	var bRtn = true;
	
	if($("#shenheren").val() == null || $("#shenheren").val()=="") {
		alert("审核人信息不能为空");
		bRtn = false;
	} 

	return bRtn;
}