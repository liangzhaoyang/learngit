function showandhide(h_id,hon_class,hout_class,c_id,totalnumber,activeno) {
    var h_id,hon_id,hout_id,c_id,totalnumber,activeno;
    for (var i=1;i<=totalnumber;i++) {
        document.getElementById(c_id+i).style.display='none';
        document.getElementById(h_id+i).className=hout_class;
    }
    document.getElementById(c_id+activeno).style.display='block';
    document.getElementById(h_id+activeno).className=hon_class;
}
var tips; 
var theTop = 100;
var old = theTop;
function initFloatTips() 
{ 
	tips = document.getElementById('divQQbox');
	moveTips();
}
function moveTips()
{
	 	  var tt=50; 
		  if (window.innerHeight) 
		  {
		pos = window.pageYOffset  
		  }else if (document.documentElement && document.documentElement.scrollTop) {
		pos = document.documentElement.scrollTop  
		  }else if (document.body) {
		    pos = document.body.scrollTop;  
		  }
		  //http:
		  pos=pos-tips.offsetTop+theTop; 
		  pos=tips.offsetTop+pos/10; 
		  if (pos < theTop){
			 pos = theTop;
		  }
		  if (pos != old) { 
			 tips.style.top = pos+"px";
			 tt=10;//alert(tips.style.top);  
		  }
		  old = pos;
		  setTimeout(moveTips,tt);
}
initFloatTips();
	if(typeof(HTMLElement)!="undefined")//��firefox����contains()������ie�²�������
		{  
		  HTMLElement.prototype.contains=function (obj)  
		  {  
			  while(obj!=null && typeof(obg.tagName)!="undefined")
				{
				if(obj==this)
					return true;
				obj=obj.parentNode;
				}
			return false;
		  }
	}
function show()
{
	document.getElementById("meumid").style.display="none"
	document.getElementById("contentid").style.display="block"
}
	function hideMsgBox(theEvent){
	  if (theEvent){
		var browser=navigator.userAgent;
		if (browser.indexOf("Firefox")>0){//Firefox
		    if (document.getElementById("contentid").contains(theEvent.relatedTarget)) {
				return
			}
		}
		if (browser.indexOf("MSIE")>0 || browser.indexOf("Presto")>=0){
	        if (document.getElementById('contentid').contains(event.toElement)) {
			    return;//����ʽ
		    }
		}
	  }
	  document.getElementById("meumid").style.display = "block";
	  document.getElementById("contentid").style.display = "none";
 	}
	
function setHandlet(index)
{
	var pos=parseInt(index);
//	alert(pos);
	var hiddenObj=document.getElementById("pagePath");
	var path=hiddenObj.value;
	//
	var testdiv = document.getElementById("handletTab");
	var content="";
	switch(pos)
	{
	case 0:
		content='<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%" '+
            ' align=center>'+
              ' <TBODY>'+
               '<TR align=left>'+
                ' <TD height=25>'+
                '   <TABLE border=0 cellSpacing=0 cellPadding=0 '+
                '   width="100%">'+
                '     <TBODY>'+
                 '    <TR>'+
                '     <TD height=20></TD></TR>'+
                 '    <TR>'+
                 '    <TD style="FONT-SIZE: 16px" height=37 vAlign=top '+
                  '   align=middle><B>大师牌乳胶漆全系列装饰用漆 '+
                  '   </B></TD></TR>'+
                 '    <TR>'+
                  '   <TD class=line02 align=middle><B> '+
                 '    </B></TD></TR></TBODY></TABLE></TD></TR>'+
              ' <TR align=left>'+
              '   <TD height=408 vAlign=top align=left><BR>'+
                   
               '    <P>　　大师牌漆是PPG公司出品的全系列建筑装饰用漆，主要成分包括颜料、合成树脂、溶剂和特殊添加剂等。产品有近百个不同品种，主要应用于建筑物内外墙表面，包括内墙、外墙、木质和金属等系列产品。?美国原产性能不凡产品在美国生产，具有良好的抗酸碱、抗静电、抗沾污、抗强紫外线、防霉和阻燃性能，遮盖率高，透气性好，附着力强，不易剥落。'+
			
                 '  </P><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="images/121.jpg" align="middle"/><br/>'+
'</P>'+
                 '  <P>　100%绿色环保漆?产品无毒，其品质通过美国联邦医学学会(FDA)食品工业安全质量认证，并达到我国国家标准GB/T9756-1995一等品指标，是真正的绿色环保漆。万种色彩?产品色彩丰富，使用美国引进的专业调色系统及通用色浆，可供现场调色的备选色彩逾万种；同一系列产品有多种光泽可供选择，如内墙分为：哑光、蛋壳光、中光及高光等。?施工简便?产品流平性好，毋须添加任何稀释剂即可使用。采用PPG专用滚筒或喷涂作业，无刷痕，施工简便。使用其粉刷后的墙面手感细腻滑爽，更易清洗。2000年，大师牌乳胶漆在中国市场的销量达3,000吨，以45%的市场占有率。　</P>'+

                  ' <BR></P><BR></TD></TR>'+
              ' <TR>'+
                ' <TD height=25 align=middle>【<A '+
                  ' href="javascript:window.print()">打印</A>】&nbsp;【<A '+
                '   href="javascript:history.go(-1)">返回</A>】 </TD></TR>'+
             '  <TR>'+
              '   <TD '+
         'height=1></TD></TR></TBODY></TABLE>';
		break;
	case 1:
		content='<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%" '+
           ' align=center>'+
             ' <TBODY>'+
             ' <TR align=left>'+
               ' <TD height=25>'+
                 ' <TABLE border=0 cellSpacing=0 cellPadding=0 '+
                '  width="100%">'+
                  '  <TBODY>'+
                 '   <TR>'+
                  '  <TD height=20></TD></TR>'+
                  ' <TR>'+
                  '  <TD style="FONT-SIZE: 16px" height=37 vAlign=top '+
                   ' align=middle><B>全友天天门业实木门、复合门  '+
                  '  </B></TD></TR>'+
                  '  <TR>'+
                  '  <TD class=line02 align=middle><B> '+
                  '  </B></TD></TR></TBODY></TABLE></TD></TR>'+
              '<TR align=left>'+
               ' <TD height=408 vAlign=top align=left><BR>'+
       ' <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="images/122.jpg" align="middle"/><br/>'+
'</P>'+
                 ' <P>　天天门业主领产品烤漆室内门、实木门、实木复合门、门套、窗套、家具烤漆等系列产品，与众不同的款式、风格，独特的流程工艺其优良的品质、创新的门庭设计、精湛的制造工艺设备，让你尽显王者风范。产品规格齐全、坚固不变形、色彩多样、高档适用，是办公、别墅、公寓、居家的理想选择，天天门业坚持质量第一、信誉至上的原则，打造一流的品牌。始终以客户服务为中心，满意为标准，愿与您携手共进，共铸辉煌。　</P>'+

                '  <BR></P><BR></TD></TR>'+
             ' <TR>'+
               ' <TD height=25 align=middle>【<A '+
                 'href="javascript:window.print()">打印</A>】&nbsp;【<A '+
                '  href="javascript:history.go(-1)">返回</A>】 </TD></TR>'+
             ' <TR>'+
               ' <TD '+
        'height=1></TD></TR></TBODY></TABLE>';
		break;
	case 2:
		content='<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%" '+
            ' align=center>'+
             '  <TBODY>'+
             '  <TR align=left>'+
               '  <TD height=25>'+
                 '  <TABLE border=0 cellSpacing=0 cellPadding=0 '+
                  ' width="100%">'+
                  '   <TBODY>'+
                 '    <TR>'+
                  '   <TD height=20></TD></TR>'+
                  '   <TR>'+
                  '   <TD style="FONT-SIZE: 16px" height=37 vAlign=top '+
                  '   align=middle><B> 德华兔宝宝木业装饰新材料 '+
                  '   </B></TD></TR>'+
                  '   <TR>'+
                   '  <TD class=line02 align=middle><B> '+
                 '    </B></TD></TR></TBODY></TABLE></TD></TR>'+
            '   <TR align=left>'+
               '  <TD height=408 vAlign=top align=left><BR>'+
       '  <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="images/123.jpg" align="middle"/><br/>'+
'</P>'+
                  ' <P>　兔宝宝木业引自世界领先的北美原木研制生产技术，立足中国力求创新，致力于为中国市场提供最先进的原木材技术和产品。技术优势：所有产品都先在工厂进行标准化的加工，全程服务：为客户提供售前、售中、售后全程优质的服务和完善及时的客户保障制度。现场设计及安装一条龙服务，公司一直奉行“万变的环境，不变的品质”的宗旨!以质量为基石，诚信为根本，客户满意为宗旨，创富朗新品牌！　</P>'+

                 '  <BR></P><BR></TD></TR>'+
           '    <TR>'+
               '  <TD height=25 align=middle>【<A '+
                 '  href="javascript:window.print()">打印</A>】&nbsp;【<A '+
                '   href="javascript:history.go(-1)">返回</A>】 </TD></TR>'+
              ' <TR>'+
               '  <TD '+
        ' height=1></TD></TR></TBODY></TABLE>';
		break;
	case 3:
		content='<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%" '+
           ' align=center>'+
             ' <TBODY>'+
             ' <TR align=left>'+
               ' <TD height=25>'+
                '  <TABLE border=0 cellSpacing=0 cellPadding=0 '+
               '   width="100%">'+
                  '  <TBODY>'+
                  '  <TR>'+
                   ' <TD height=20></TD></TR>'+
                  '  <TR>'+
                   ' <TD style="FONT-SIZE: 16px" height=37 vAlign=top '+
                  '  align=middle><B> 佛山“日丰水管”—铝塑复合管'+
                  '  </B></TD></TR>'+
                  '  <TR>'+
                  '  <TD class=line02 align=middle><B> '+
                  '  </B></TD></TR></TBODY></TABLE></TD></TR>'+
             ' <TR align=left>'+
               ' <TD height=408 vAlign=top align=left><BR>'+
       ' <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="images/124.jpg" align="middle"/><br/>'+
'</P>'+
                  '<P>　目前公司主管品牌是日丰水电管材，品种齐全，质量保证。主要产品有：光华漩流降噪排水管，日丰PVC排水管，日丰PP-R给水管，日丰PVC电工管及线槽，PVC-U给水管，PE给水管，UPVC波纹管，HDPE排水管，PE安全燃气管，钢带增强PR螺旋波纹管，PE-RT地暖管，PE-RT给水管，PB环保给水管及地暖管，铝塑等。　</P>'+

                  '<BR></P><BR></TD></TR>'+
            '  <TR>'+
               ' <TD height=25 align=middle>【<A '+
                 ' href="javascript:window.print()">打印</A>】&nbsp;【<A '+
                '  href="javascript:history.go(-1)">返回</A>】 </TD></TR>'+
            '  <TR>'+
              '  <TD '+
       ' height=1></TD></TR></TBODY></TABLE>';
		break;
	case 4:
		  content='<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%" '+
              'align=center>'+
               ' <TBODY>'+
               ' <TR align=left>'+
                 ' <TD height=25>'+
                  '  <TABLE border=0 cellSpacing=0 cellPadding=0 '+
                 '   width="100%">'+
                  '    <TBODY>'+
                  '    <TR>'+
                   '   <TD height=20></TD></TR>'+
                    '  <TR>'+
                    '  <TD style="FONT-SIZE: 16px" height=37 vAlign=top '+
                    '  align=middle><B> 江西瑞金“金字牌”电线、电缆 '+
                    '  </B></TD></TR>'+
                    '  <TR>'+
                    '  <TD class=line02 align=middle><B> '+
                    '  </B></TD></TR></TBODY></TABLE></TD></TR>'+
               ' <TR align=left>'+
                '  <TD height=408 vAlign=top align=left><BR>'+
         ' <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="images/125.jpg" align="middle"/><br/>'+
'</P>'+
                  '  <P>　专业生产各种电力电缆、控制电缆、架空绝缘电缆、架空绞线、塑胶电线、航空用线缆、铜母（绞）线、烟花点火线、数字网络线、电视电缆等“金字牌”系列产品。民用电缆取得国家生产许可认证，军用电缆取得国家首批颁发的武器装备科研生产许可证。连续十年被省地授予“重合同、守信用”企业.　</P>'+

                    '<BR></P><BR></TD></TR>'+
               ' <TR>'+
                 ' <TD height=25 align=middle>【<A '+
                   ' href="javascript:window.print()">打印</A>】&nbsp;【<A '+
                  '  href="javascript:history.go(-1)">返回</A>】 </TD></TR>'+
              '  <TR>'+
                 ' <TD '+
         ' height=1></TD></TR></TBODY></TABLE>';
		break;
	case 5:
		content= '<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%"       align=center>      <TBODY>'+
        '<TR align=left>    <TD height=25>       <TABLE border=0 cellSpacing=0 cellPadding=0    width="100%">'+
         '     <TBODY>         <TR>       <TD height=20></TD></TR>  <TR>  <TD style="FONT-SIZE: 16px" height=37 vAlign=top '+
          '    align=middle><B> 樱花集成吊顶、扣板系列  </B></TD></TR>      <TR>    <TD class=line02 align=middle><B> '+
          '    </B></TD></TR></TBODY></TABLE></TD></TR>'+
        '<TR align=left>'+
         ' <TD height=408 vAlign=top align=left><BR>'+
 ' <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="images/126.jpg" align="middle"/><br/>'+
'</P>          <P>樱花集成吊顶有限公司以国际化的视野和因专业而领先的精神，将日本崭新理念和全新生活方式带入中国，一场吊顶革命将拉开厨卫“后极简主义”的经典生活演绎，新浪漫、创新精神、经典等风格炯异、特点鲜明的厨、卫集成吊顶展现了当国际家居流行的最新风尚，充分展现国际最时尚家居的不凡魅力。</P>'+
     '       <BR></P><BR></TD></TR>      <TR>      <TD height=25 align=middle>【<A '+
         '   href="javascript:window.print()">打印</A>】&nbsp;【<A '+
          '  href="javascript:history.go(-1)">返回</A>】</TD></TR>'+
       ' <TR>       <TD   height=1></TD></TR></TBODY></TABLE>';
		break;
	}
	testdiv.innerHTML=content;
}