function choose()
{
	$('#personDialog').dialog({
		title : '人员选择',
		width : 600,
		height : 400,
		closed : false,
		cache : false,
		href : "showDialog.do?url='ryxz.do'",
		modal : true,
		minimizable : true,
		resizable : true,
		onClose : function() {
			//$('#personDialog').datagrid('reload');
		}
	});
}

function setId(id)
{
	alert("aaaa");
//	$('#xingbie').val(xingbie);
	$.get("rycxById.do"+"?id="+id, function(data, status) {
		if(status == "success") {
			alert(data.name);
			$('#panduan').val(data.id);
			
			var a=window.parent.setPanduan(data.id);
//			alert("acasvsgvbvfdbv"+a);
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
//			
			alert("aaaacccccccccc");
			$('#personDialog').datagrid('reload');
			}
			//alert(""+data.tianbiaoren+data.tianbiaoriqi);
		}
	});
}
//function setXingbie(xingbie,name)
//{
//	
//	$('#xingbie').val(xingbie);
//	$('#name').val(name);
//}
function checkInput() {
	var bRtn = true;

	if ($("#name").val() == null || $("#name").val() == "") {
		// $("#nameMsg").html("�û�����Ϊ�գ�");
		bRtn = false;
	} else {
		$("#nameMsg").html("");
	}

	if ($("#chushengnianyue").val() == null
			|| $("#chushengnianyue").val() == "") {
		// $("#chushengnianyueMsg").html("������Ϊ�գ�");
		bRtn = false;
	} else {
		$("#chushengnianyueMsg").html("");
	}

	if ($("#zhuanye").val() == null || $("#zhuanye").val() == "") {
		// $("#zhuanyeMsg").html("������Ϊ�գ�");
		bRtn = false;
	} else {
		$("#zhuanyeMsg").html("");
	}

	if ($("#renmingdanwei").val() == null || $("#renmingdanwei").val() == "") {
		// $("#renmingdanweiMsg").html("������Ϊ�գ�");
		bRtn = false;
	} else {
		$("#renmingdanweiMsg").html("");
	}

	if ($("#jianrenqitazhiwu").val() == null
			|| $("#jianrenqitazhiwu").val() == "") {
		// $("#jianrenqitazhiwuMsg").html("������Ϊ�գ�");
		bRtn = false;
	} else {
		$("#jianrenqitazhiwuMsg").html("");
	}

	if ($("#kaishiriqi").val() == null || $("#kaishiriqi").val() == "") {
		// $("#kaishiriqiMsg").html("������Ϊ�գ�");
		bRtn = false;
	} else {
		$("#kaishiriqiMsg").html("");
	}

	if ($("#suoshubumen").val() == null || $("#suoshubumen").val() == "") {
		// $("#suoshubumenMsg").html("������Ϊ�գ�");
		bRtn = false;
	} else {
		$("#suoshubumenMsg").html("");
	}
	if ($("#danweimingcheng").val() == null
			|| $("#danweimingcheng").val() == "") {
		// $("#danweimingchengMsg").html("������Ϊ�գ�");
		bRtn = false;
	} else {
		$("#danweimingchengMsg").html("");
	}

	if ($("#tel").val() == null || $("#tel").val() == "") {
		// $("#telMsg").html("������Ϊ�գ�");
		bRtn = false;
	} else {
		$("#telMsg").html("");
	}

	if ($("#mobilenum").val() == null || $("#mobilenum").val() == "") {
		// $("#mobilenumMsg").html("������Ϊ�գ�");
		bRtn = false;
	} else {
		$("#mobilenumMsg").html("");
	}

	return bRtn;
}

function submitFormPersonAdd() {
	alert("aaa");
	$('#frmRyTj').form('submit', {
		onSubmit : function() {
			alert("bbb");
//			if (!checkInput()) {
//				alert("check false");
//				return false;
//			}
			alert("check true");
			return true;
		},
		success : function(data) {
//			smryAdd();
			alert("aaaaa");
//			smryAdd();
			if (data == "success") {
				window.parent.$("#personDialog").dialog("close");
			} else {
				alert(data);
				alert(typeof (data));
			}
		}
	});
}
//function smryAdd(){
//	url:'smryAdd.do';
//}
function cancelClick() {
	window.parent.$("#personDialog").dialog("close");
	
}