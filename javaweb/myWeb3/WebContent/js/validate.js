


function checkDateTimeBytes(value) {
	if (value.length != 19) {
		return false;
	}
	
	var vals = value.split(" ");
	if(vals.length != 2) {
		return false;
	}
	
	var arr = vals[0].split("-");
	if (arr.length != 3) {
		return false;
	}

	if (arr[0].length != 4 || arr[1].length != 2 || arr[2].length != 2) {
		return false;
	}
	var reg = new RegExp("^[0-9]*$");
	if (!reg.test(arr[0])) {
		return false;
	}
	reg = new RegExp("^(0[1-9])|(1[0-2])$");
	if (!reg.test(arr[1])) {
		return false;
	}
	reg = new RegExp("^(0[1-9])|([1|2][0-9])|(3[0-1])$");
	if (!reg.test(arr[2])) {
		return false;
	}
	
	var t = new Date(arr[0] + "/" + arr[1] + "/" + arr[2]);
	if(t == undefined) {
		return false;
	}

	if(t.getUTCFullYear() != arr[0] || t.getMonth() != (arr[1] - 1) || t.getDate() != arr[2]) {
		return false;
	}
	
	arr = vals[1].split(":");
	var regHour = new RegExp("^([0-1][0-9])|(1[0-3])$");
	var regMinOrSec = new RegExp("^([0-5][0-9])|(60)$");
	if(!regHour.test(arr[0]) || !regMinOrSec.test(arr[1]) || !regMinOrSec.test(arr[2])) {
		return false;
	}
	
	return true;	
}

/** 表格内的条件查询* */
function query() {
	
	var beginTime = $('#beginTime').combobox("getValue");
	if(beginTime != "" && true != checkDateTimeBytes(beginTime)) {
		alert("起始日期格式不正确（YYYY-MM-DD HH:mm:ss）。");
		return;
	}
	var endTime=$("#dateTo").datebox('getValue');
	if(endTime != "" && true != checkDateTimeBytes(endTime)) {
		alert("终止日期格式不正确（YYYY-MM-DD HH:mm:ss）。");
		return;
	}
	if (beginTime != "" && endTime != "" && beginTime > endTime) {
		alert("起始日期应该小于终止日期。");
		return;
	}
}

