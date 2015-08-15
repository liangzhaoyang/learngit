var showWestTab = true;
 $(function(){
	 	   /*var easyuiTheme = "default";
            var cookieTheme = "metro-gray";
            var href = $("#easyuiTheme").attr("href").replace(easyuiTheme, cookieTheme);
            $("#easyuiTheme").attr("href", href);*/
            
		/**
		 *缩放一级菜单 
		 **/
		$("#firstMenu_zoom").click(function(){
			alert("bbb");
			 $("#bodydiv").layout('collapse','north'); 
		});
	
	    
	    /**设定一级菜单事件**/
		$("#firstMenu li a").click(function(){
			alert("firstMenu li a click");
			var id = this.id; //一级菜单id
			if (id === undefined || id == null || id.length<=0)
			{
				return ;
			}
			$("#leftdiv").children().hide();
			$("div[parent='"+id+"']").show();
			westTab();
		});
			
}); 

/**
 * 显示二级菜单
 */
function  westTab()
{  
	if(!showWestTab)
	{
		$("#bodydiv").layout('expand','west'); 
	} 
	showWestTab = true;
}
/**
 * 增加tab页签
 * title1:tab唯一标识
 * url:地址
 **/
function addtabs(title1,url)
{  
	if($('#divtabs').tabs('exists',title1)){ 
	   $('#divtabs').tabs('select',title1); 
	}else{
		$('#divtabs').tabs('add',{
			title: title1,
			content: '<div style="height:100%;"><iframe id="contentFrame" name="contentFrame" scrolling="yes" frameborder="0" src="'+url+'"   style="width:100%;height:100%;"></iframe></div>',
			closable: true
		});
	}  
}
/**
 * 跳转到页签
 */	
function gotoPage(title,url){
	addtabs(title, url);
} 