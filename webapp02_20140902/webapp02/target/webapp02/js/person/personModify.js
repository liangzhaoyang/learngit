function checkInput() {
	var bRtn = true;
	
	if($("#name").val() == null || $("#name").val() == "") {
	//	$("#nameMsg").html("�û�����Ϊ�գ�");
		bRtn = false;
	} else {
		$("#nameMsg").html("");
	}

	if($("#chushengnianyue").val() == null || $("#chushengnianyue").val() == "") {
	//	$("#chushengnianyueMsg").html("������Ϊ�գ�");
		bRtn = false;
	} else {
		$("#chushengnianyueMsg").html("");
	}

	if($("#zhuanye").val() == null || $("#zhuanye").val() == "") {
	//	$("#zhuanyeMsg").html("������Ϊ�գ�");
		bRtn = false;
	} else {
		$("#zhuanyeMsg").html("");
	}
	
	if($("#renmingdanwei").val() == null || $("#renmingdanwei").val() == "") {
	//	$("#renmingdanweiMsg").html("������Ϊ�գ�");
		bRtn = false;
	} else {
		$("#renmingdanweiMsg").html("");
	}
	
	if($("#jianrenqitazhiwu").val() == null || $("#jianrenqitazhiwu").val() == "") {
	//	$("#jianrenqitazhiwuMsg").html("������Ϊ�գ�");
		bRtn = false;
	} else {
		$("#jianrenqitazhiwuMsg").html("");
	}
	
	if($("#kaishiriqi").val() == null || $("#kaishiriqi").val() == "") {
	//	$("#kaishiriqiMsg").html("������Ϊ�գ�");
		bRtn = false;
	} else {
		$("#kaishiriqiMsg").html("");
	}
	
	if($("#suoshubumen").val() == null || $("#suoshubumen").val() == "") {
		//$("#suoshubumenMsg").html("������Ϊ�գ�");
		bRtn = false;
	} else {
		$("#suoshubumenMsg").html("");
	}
	if($("#danweimingcheng").val() == null || $("#danweimingcheng").val() == "") {
	//	$("#danweimingchengMsg").html("������Ϊ�գ�");
		bRtn = false;
	} else {
		$("#danweimingchengMsg").html("");
	}
	
	if($("#tel").val() == null || $("#tel").val() == "") {
	//	$("#telMsg").html("������Ϊ�գ�");
		bRtn = false;
	} else {
		$("#telMsg").html("");
	}
	
	if($("#mobilenum").val() == null || $("#mobilenum").val() == "") {
	//	$("#mobilenumMsg").html("������Ϊ�գ�");
		bRtn = false;
	} else {
		$("#mobilenumMsg").html("");
	}
	
	return bRtn;
}


function submitModifyForm() {
		
	//alert("fafajl");
		$('#frmryXg').form('submit', {
			onSubmit : function() {
//				if (!checkInput()) {
//					return false;
//				}
				return true;
			},
			success : function(data) {
				if (data == "success") {
					window.parent.$("#personDialog").dialog("close");
				} else {
					alert(data);
					alert(typeof (data));
				}
			}
		});
	}

	function cancelModifyClick() {
		window.parent.$("#personDialog").dialog("close");
	}