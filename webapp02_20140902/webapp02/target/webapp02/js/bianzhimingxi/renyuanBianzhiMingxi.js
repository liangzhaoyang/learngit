//加载
$(function() {
	$.get("rybzmx.do", function(data, status) {
		if (status == "success") {
			$('#input101').numberspinner('setValue',data.data101);
			$('#input102').numberspinner('setValue',data.data102);
			$('#input103').numberspinner('setValue',data.data103);
			$('#input104').numberspinner('setValue',data.data104);
			
			$('#input106').numberspinner('setValue',data.data106);
			$('#input107').numberspinner('setValue',data.data107);
			$('#input108').numberspinner('setValue',data.data108);
			$('#input109').numberspinner('setValue',data.data109);
			$('#input110').numberspinner('setValue',data.data110);
			
			$('#input112').numberspinner('setValue',data.data112);
			$('#input113').numberspinner('setValue',data.data113);
			$('#input114').numberspinner('setValue',data.data114);
			$('#input115').numberspinner('setValue',data.data115);
		}
	});
});
////插入
function rybzmxbc1() {
	$('#rybzmxbc').form('submit', {
		onSubmit : function() {
//			if (!checkInput()) {
//				return false;
//			}
			return true;
		},
		success : function(data) {
			if (data == "success") {
				$('#rybzmxbc').datagrid('reload');
			} else {
				alert(data);
			}
		}
	});
}
