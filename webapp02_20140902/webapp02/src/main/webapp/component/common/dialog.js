/**
 * 关闭窗体
 */
function closeWin(winName){
	if(winName == null) {
		winName = "";
	}
	$("#win_div_"+winName).window('close');
	$("#win_frame_"+winName).remove();
	$("#win_div_"+winName).remove();
}
function callback(){
	
}
/**
 * 打开窗体
 * URL:地址
 * winName:窗体名称
 */
function openWin(URL, winName){
	if (arguments[2] && arguments[3]){
		openWinWidthHeight(URL,winName,arguments[2],arguments[3]);
	} else {
		openWinWidthHeight(URL,winName,600,400);
	}
}
/**
 * 打开窗体
 * URL:地址
 * winName:窗体名称
 * width:宽度
 * height：高度
 */
function openWinWidthHeight(URL, winName, width, height){
  	if(winName == null) {
		winName = "";
	}
	//alert(URL);
	if($("#win_div_"+winName).size() > 0) {
		$("#win_div_"+winName).window('close');
		$("#win_frame_"+winName).remove();
		$("#win_div_"+winName).remove();
	}
	var boardDiv = "<div id=\"win_div_"+winName+"\" class=\"easyui-window\" title=\"My Window\" style=\"width:"+width+"px;height:"+height+"px;padding:5px;background: #fafafa;\"></div>"; 
	$(document.body).append(boardDiv);
	var boardFrame = "<div id=\"win_frame_"+winName+"\"></div>";
	$("#win_div_"+winName).html(boardFrame);
	$("#win_div_"+winName).window({title:winName,shadow: true, modal: true, inline: false,
		onClose:function(){
			$("#win_frame_"+winName).remove();
			$("#win_div_"+winName).remove();
		}
	});

	$("#win_frame_"+winName).load(URL, {}, function(){
		$.parser.parse();
		callback();
	});
}
