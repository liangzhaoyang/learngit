function choose()
{
	$('#personDialog').window({
		title : '人员选择',
		width : 600,
		height : 400,
		closed : false,
		cache : false,
		href : "showDialog.do?url='ryxz.do'",
		modal : true,
		resizable : true,
		inline:false,
		onClose : function() {
			//$('#personDialog').datagrid('reload');
		}
	});
}

function setId(id)
{
	$.get("rycxById.do"+"?id="+id, function(data, status) {
		if(status == "success") {
			$('#panduan').val(data.id);
			var a=window.parent.setPanduan(data.id);
			if(a==0){
			$('#name').val(data.name);
			$('#xingbie').val(data.xingbie);
			$('#minzu').val(data.minzu);
			$('#zhiwu').val(data.zhiwu);
			$('#zhuanye').val(data.zhuanye);
			$('#chushengnianyue').datebox('setValue', data.chushengnianyue);
			$('#xueli').val(data.xueli);
			$('#zhengzhimianmao').val(data.zhengzhimianmao);
			$('#xingzhengjibie').val(data.xingzhengjibie);
			
			$('#jishuzhicheng').val(data.jishuzhicheng);
			$('#renmingdanwei').val(data.renmingdanwei);
			$('#shifouzhuanzhi').val(data.shifouzhuanzhi);
			$('#jianrenqitazhiwu').val(data.jianrenqitazhiwu);
			$('#kaishiriqi').datebox('setValue', data.kaishiriqi);
			$('#suoshubumen').val(data.suoshubumen);
			$('#danweimingcheng').val(data.danweimingcheng);
			$('#tel').val(data.tel);
			
			$('#mobilenum').val(data.mobilenum);
			$('#shemidengji').val(data.shemidengji);
			$('#yaohaibumengrenyuan').val(data.yaohaibumengrenyuan);
			$('#renyuanleixing').val(data.renyuanleixing);

			$('#personDialog').datagrid('reload');
			}
		}
	});
}
function checkInput() {
	var bRtn = true;

	if ($("#name").val() == null || $("#name").val() == "") {
		bRtn = false;
	} else {
		$("#nameMsg").html("");
	}

	if ($("#chushengnianyue").val() == null
			|| $("#chushengnianyue").val() == "") {
		bRtn = false;
	} else {
		$("#chushengnianyueMsg").html("");
	}

	if ($("#zhuanye").val() == null || $("#zhuanye").val() == "") {
		bRtn = false;
	} else {
		$("#zhuanyeMsg").html("");
	}

	if ($("#renmingdanwei").val() == null || $("#renmingdanwei").val() == "") {
		bRtn = false;
	} else {
		$("#renmingdanweiMsg").html("");
	}

	if ($("#jianrenqitazhiwu").val() == null
			|| $("#jianrenqitazhiwu").val() == "") {
		bRtn = false;
	} else {
		$("#jianrenqitazhiwuMsg").html("");
	}

	if ($("#kaishiriqi").val() == null || $("#kaishiriqi").val() == "") {
		bRtn = false;
	} else {
		$("#kaishiriqiMsg").html("");
	}

	if ($("#suoshubumen").val() == null || $("#suoshubumen").val() == "") {
		bRtn = false;
	} else {
		$("#suoshubumenMsg").html("");
	}
	if ($("#danweimingcheng").val() == null
			|| $("#danweimingcheng").val() == "") {
		bRtn = false;
	} else {
		$("#danweimingchengMsg").html("");
	}

	if ($("#tel").val() == null || $("#tel").val() == "") {
		bRtn = false;
	} else {
		$("#telMsg").html("");
	}

	if ($("#mobilenum").val() == null || $("#mobilenum").val() == "") {
		bRtn = false;
	} else {
		$("#mobilenumMsg").html("");
	}

	return bRtn;
}

function submitFormPersonAdd() {
	$('#frmRyTj').form('submit', {
		onSubmit : function() {
//			if (!checkInput()) {
//				alert("check false");
//				return false;
//			}
			return true;
		},
		success : function(data) {
			if (data == "success") {
				window.parent.$("#personDialog").dialog("close");
			} else {
				alert(data);
			}
		}
	});
}

function cancelClick() {
	window.parent.$("#personDialog").dialog("close");
}