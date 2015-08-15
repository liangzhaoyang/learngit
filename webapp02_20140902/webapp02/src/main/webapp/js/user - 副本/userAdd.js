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
