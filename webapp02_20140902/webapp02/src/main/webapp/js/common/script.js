function changeRightFrame(entryPoint) {
	$.ajaxSetup ({
		   cache: false
		});
	$.get(entryPoint, function(data, status) {
		if(status == "success") {
			$("#maindiv").html(data);
		}
	});
}

//获取选中行的用户ID, ID列名必须为id
function makeParameterString4SelectedUserId(paramName, rows) {
	var paramString = "";
	paramString = '{"' + paramName + '":[' ;
	$.each(rows,function(i,row){
//		if(i == 0) {
//			paramString += "?" + paramName + "="+row.id;
//		} else {
//			paramString += "&" + paramName + "="+row.id;
//		}

		paramString += '"' + row.id + '",';
	}); 
	paramString = paramString.substring(0, paramString.length - 1);
	paramString += ']}';

	return paramString;
}

function batchOperation(operationUrl, paramName, tblObject, confirmMessage, needRefresh) {
	var rows = tblObject.datagrid('getSelections'); 
	if(rows == null || rows.length == 0) {
		$.messager.alert('提示', "请先选择要操作的行", 'info'); 
		return;
	}

	$.messager.confirm("提示",confirmMessage,function(result){  
        if (result){ 
        	var param = makeParameterString4SelectedUserId(paramName, rows);
        	
        	$.ajaxSetup({  
                contentType : 'application/json'  
            });
  	
          	$.post(operationUrl, param, function(data) {
               	$.ajaxSetup({  
                    contentType : 'application/x-www-form-urlencoded; charset=UTF-8'  
                });
        		if(needRefresh) {
        			tblObject.datagrid('reload');
        		}
        	    $.messager.alert('提示',data.message,'info');  
        	}); 
         }
	});
}
