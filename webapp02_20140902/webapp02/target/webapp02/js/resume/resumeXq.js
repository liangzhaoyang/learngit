$(function(){
	$.get("jlxq11.do", function(data, status) {
		if(status == "success") {
			//person
			$('#name').val(data.name);
			$('#xingbie').val(data.xingbie);
			$('#chushengnianyue').val(data.chushengnianyue);
			$('#zhengzhimianmao').val(data.zhengzhimianmao);
			$('#xueli').val(data.xueli);
			$('#zhuanye').val(data.zhuanye);
			$('#minzu').val(data.minzu);
			//resume
			$('#zhaopian').val(data.zhaopian);
			$('#canjiagongzuoshijian').val(data.canjiagongzuoshijian);
			$('#zhuanyejishuzhiwu').val(data.zhuanyejishuzhiwu);
			$('#zhuanchang').val(data.zhuanchang);
			$('#xianrenzhuiwu').val(data.xianrenzhuiwu);
			$('#jianli').val(data.jianli);
			$('#beizhu').val(data.beizhu);
			//xuelimingxi
			$('#biyeyuanxiao').val(data.biyeyuanxiao);
			$('#leibie').val(data.leibie);
			$('#jiangcheng').val(data.jiangcheng);
			$('#chengguo').val(data.chengguo);
//			alert (data.chengwei1);
			//jiatingchengyuan
			if(data.chengwei1!="undefined" ){
			$('#chengwei1').val(data.chengwei1);
			}
			if(data.chengwei2!="undefined" ){
				$('#chengwei2').val(data.chengwei2);
				}
			if(data.chengwei3!="undefined" ){
				$('#chengwei3').val(data.chengwei3);
				}
			if(data.chengwei4!="undefined" ){
				$('#chengwei4').val(data.chengwei4);
				}
			
			if(data.xingming1!="undefined" ){
				$('#xingming1').val(data.xingming1);
				}
			if(data.xingming2!="undefined" ){
				$('#xingming2').val(data.xingming2);
				}
			if(data.xingming3!="undefined" ){
				$('#xingming3').val(data.xingming3);
				}
			if(data.xingming4!="undefined" ){
				$('#xingming4').val(data.xingming4);
				}
			
			if(data.chushengnianfen1!="undefined" ){
				$('#chushengnianfen1').val(data.chushengnianfen1);
				}
			if(data.chushengnianfen2!="undefined" ){
				$('#chushengnianfen2').val(data.chushengnianfen2);
				}
			if(data.chushengnianfen3!="undefined" ){
				$('#chushengnianfen3').val(data.chushengnianfen3);
				}
			if(data.chushengnianfen4!="undefined" ){
				$('#chushengnianfen4').val(data.chushengnianfen4);
				}
			
			if(data.zhengzhimianmao1!="undefined" ){
				$('#zhengzhimianmao1').val(data.zhengzhimianmao1);
				}
			if(data.zhengzhimianmao2!="undefined" ){
				$('#zhengzhimianmao2').val(data.zhengzhimianmao2);
				}
			if(data.zhengzhimianmao3!="undefined" ){
				$('#zhengzhimianmao3').val(data.zhengzhimianmao3);
				}
			if(data.zhengzhimianmao4!="undefined" ){
				$('#zhengzhimianmao4').val(data.zhengzhimianmao4);
				}
			
			if(data.gongzuodanweijizhiwu1!="undefined" ){
				$('#gongzuodanweijizhiwu1').val(data.gongzuodanweijizhiwu1);
				}
			if(data.gongzuodanweijizhiwu2!="undefined" ){
				$('#gongzuodanweijizhiwu2').val(data.gongzuodanweijizhiwu2);
				}
			if(data.gongzuodanweijizhiwu3!="undefined" ){
				$('#gongzuodanweijizhiwu3').val(data.gongzuodanweijizhiwu3);
				}
			if(data.gongzuodanweijizhiwu4!="undefined" ){
				$('#gongzuodanweijizhiwu4').val(data.gongzuodanweijizhiwu4);
				}
//			alert (000000000);

		}
	});
});	