//加载
$(function(){
	$.get("zbrymx.do", function(data, status) {
		if(status == "success") {
			$('#input102').numberspinner('setValue',data.data102);
			$('#input103').numberspinner('setValue',data.data103);
			$('#input104').numberspinner('setValue',data.data104);
			$('#input105').numberspinner('setValue',data.data105);
			
			$('#input106').numberspinner('setValue',data.data106);
			$('#input107').numberspinner('setValue',data.data107);
			$('#input108').numberspinner('setValue',data.data108);
			$('#input109').numberspinner('setValue',data.data109);
			$('#input110').numberspinner('setValue',data.data110);
			$('#input111').numberspinner('setValue',data.data111);
			$('#input112').numberspinner('setValue',data.data112);
			$('#input113').numberspinner('setValue',data.data113);
			$('#input114').numberspinner('setValue',data.data114);
			$('#input115').numberspinner('setValue',data.data115);
			$('#input116').numberspinner('setValue',data.data116);
			$('#input117').numberspinner('setValue',data.data117);
			$('#input118').numberspinner('setValue',data.data118);
		}
	});
});	
function rybzmxbc1() {
	$('#zbrymxbc').form('submit', {
		onSubmit : function() {
//			if (!checkInput()) {
//				return false;
//			}
			return true;
		},
		success : function(data) {

			if (data == "success") {
				$('#zbrymxbc').datagrid('reload');
			} else {
				alert(data);

			}
		}
	});
}
