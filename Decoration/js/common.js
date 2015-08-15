var themeStr=new Array("美式乡村","欧式风格","田园风格"," 现代风格");
function change(id)
{
//	var testdiv = document.getElementById("jiazhuangTab");
//	testdiv.innerHTML="<p>I inserted <em>this</em> content.</p>";
	if(id==1)
		alert("1");
	else if(id==2)
		alert("2");
	else if(id==3)
		alert("3");
}

function getSelect()
{
	var obj = document.getElementById("pageSelected"); //��λid
	var index = obj.selectedIndex; // ѡ������
	//var text = obj.options[index].text; // ѡ���ı�
	var value = obj.options[index].value; // ѡ��ֵ
	//
	var hiddenObj=document.getElementById("pagePath");
	var path=hiddenObj.value;
	
	//修改页码
//	document.getElementById("inputTagName").value=value;
//	fm1.submit();
//	alert(value);
//	//修改标签值
//	var pageNum=document.getElementById("pageNum");
//	pageNum.value=value;

	var testdiv = document.getElementById("jiazhuangTab");
	
	var content=' <TABLE border=0 cellSpacing=0 cellPadding=0 width="100%" align=center>  <TBODY>    <TR>      <TD height=10></TD></TR>'+
   ' <TR>   <TD style="FONT-SIZE: 15px" class=txt01 vAlign=top    align=left>        <P></P></TD></TR>    <TR>      <TD>'+
     '   <DIV style="DISPLAY: none">���� 48 ����Ʒ</DIV></TD></TR>    <TR>      <TD height=10></TD></TR>    <TR>      <TD class=txt01 height=454 vAlign=top>'+
      '  <TABLE border=0 cellSpacing=0 cellPadding=0         width="100%">          <TBODY>          <TR>';
	var contentEnd='   <TR></TR></TBODY></TABLE></TD></TR>  </TBODY></TABLE></DIV>';
	var picStart=(parseInt(value-1))*12;
	var themeIndex=parseInt(index);
	var i=0;
	var picIndex='';
	for(i=1;i<13;i++)
		{
		//picIndex=i+picStart;
//		if((i+picStart)>42)
//			break;
		//
		if((i+picStart)<10)
			picIndex="201412120"+String(i+picStart)+".gif";
		else if((i+picStart)>9 && (i+picStart)<40)
			picIndex="20141212"+String(i+picStart)+".gif";
		else
			picIndex="20141212"+String(i+picStart)+".jpg";
		//
		var imgSrc='images/'+picIndex;
		var temp="setImg('"+imgSrc+"')";
		if(i%4==1)
			{
			
			content+="<TR>";
			content+='<TD vAlign=top align=middle> <TABLE border=0 cellSpacing=4 cellPadding=0      width=130>      <TBODY>      <TR>   <TD width="100%" align=middle>'+
            '<DIV><A class=pic   href="#" onclick="'+temp+'"><IMG    class=img_1 border=0   src="'+'images/'+picIndex+'"'+
            '  width=140   height=105></A></DIV></TD></TR>      <TR>       <TD align=middle>   <TABLE border=0 cellSpacing=4 cellPadding=0 '+
           ' width="90%">    <TBODY>      <TR> <TD class=txt01 height=25 align=middle><A   href="">'+themeStr[themeIndex]+
           '</A></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD>';
			}
		else if(i%4==0)
			{
			content+='<TD vAlign=top align=middle> <TABLE border=0 cellSpacing=4 cellPadding=0      width=130>      <TBODY>      <TR>   <TD width="100%" align=middle>'+
            '<DIV><A class=pic   href="#" onclick="'+temp+'"><IMG  class=img_1 border=0   src="'+'images/'+picIndex+'"'+
            '  width=140   height=105></A></DIV></TD></TR>      <TR>       <TD align=middle>   <TABLE border=0 cellSpacing=4 cellPadding=0 '+
           ' width="90%">    <TBODY>      <TR> <TD class=txt01 height=25 align=middle><A   href="">'+themeStr[themeIndex]+
           '</A></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD>';
			content+="</TR>";
			}
		else 
			{
			 content+='<TD vAlign=top align=middle> <TABLE border=0 cellSpacing=4 cellPadding=0      width=130>      <TBODY>      <TR>   <TD width="100%" align=middle>'+
             '<DIV><A class=pic   href="#" onclick="'+temp+'"><IMG    class=img_1 border=0   src="'+'images/'+picIndex+'"'+
             '  width=140   height=105></A></DIV></TD></TR>      <TR>       <TD align=middle>   <TABLE border=0 cellSpacing=4 cellPadding=0 '+
            ' width="90%">    <TBODY>      <TR> <TD class=txt01 height=25 align=middle><A   href="">'+themeStr[themeIndex]+
            '</A></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD>';
            }
		}
	content+=contentEnd;
	
	testdiv.innerHTML=content;
		//"<IMG border=0   src='"+path+"/images/2014121201.gif'/>";
	
}

function setSelect(page)
{
	//alert(page);
	if(page=="firstPage")
	{
		var obj = document.getElementById("pageSelected"); //��λid
		obj.options[0].selected=true; // ѡ��
//		setPage(1);
	}
	else if(page=="nextPage")
		{
		var obj = document.getElementById("pageSelected"); //��λid
		var index = parseInt(obj.selectedIndex); // ѡ������
		index++;
		if(index==4)
		{
			alert("已经是最后一页了！");
			return;
		}
		obj.options[index].selected=true; // ѡ��
//		setPage(index+1);
		}
	else if(page=="prePage")
		{
		var obj = document.getElementById("pageSelected"); //��λid
		var index = parseInt(obj.selectedIndex); // ѡ������
		index--;
		if(index==-1)
		{	
			alert("已经是第一页了！");
			return;}
		obj.options[index].selected=true; // ѡ��
//		setPage(index+1);
		}
	else if(page=="last")
		{
		var obj = document.getElementById("pageSelected"); //��λid
		obj.options[3].selected=true; // ѡ��
//		setPage(4);
		}
	getSelect();
}

function setPage(num)
{
//	alert(num);
	var value = num; // ѡ��ֵ
	//
	var testdiv = document.getElementById("jiazhuangTab");
	
	var content=' <TABLE border=0 cellSpacing=0 cellPadding=0 width="100%" align=center>  <TBODY>    <TR>      <TD height=10></TD></TR>'+
   ' <TR>   <TD style="FONT-SIZE: 15px" class=txt01 vAlign=top    align=left>        <P></P></TD></TR>    <TR>      <TD>'+
     '   <DIV style="DISPLAY: none">���� 48 ����Ʒ</DIV></TD></TR>    <TR>      <TD height=10></TD></TR>    <TR>      <TD class=txt01 height=454 vAlign=top>'+
      '  <TABLE border=0 cellSpacing=0 cellPadding=0         width="100%">          <TBODY>          <TR>';
	var contentEnd='   <TR></TR></TBODY></TABLE></TD></TR>  </TBODY></TABLE></DIV>';
	var picStart=(parseInt(value)-1)*12;
//	alert(picStart);
	var themeIndex=parseInt(index);
	var i=0;
	var picIndex='';
	for(i=1;i<13;i++)
		{
		if((i+picStart)<10)
			picIndex="201412120"+String(i+picStart)+".gif";
		else if((i+picStart)>9 && (i+picStart)<40)
			picIndex="20141212"+String(i+picStart)+".gif";
		else
			picIndex="20141212"+String(i+picStart)+".jpg";
		//
		var imgSrc='images/'+picIndex;
		var temp="setImg('"+imgSrc+"')";
//		alert("fafasfs");
		if(i%4==1)
			{
			
			content+="<TR>";
			content+='<TD vAlign=top align=middle> <TABLE border=0 cellSpacing=4 cellPadding=0      width=130>      <TBODY>      <TR>   <TD width="100%" align=middle>'+
            '<DIV><A class=pic   href="#" onclick="'+temp+'"><IMG    class=img_1 border=0   src="'+'images/'+picIndex+'"'+
            '  width=140   height=105></A></DIV></TD></TR>      <TR>       <TD align=middle>   <TABLE border=0 cellSpacing=4 cellPadding=0 '+
           ' width="90%">    <TBODY>      <TR> <TD class=txt01 height=25 align=middle><A   href="">'+themeStr[themeIndex]+
           '</A></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD>';
			}
		else if(i%4==0)
			{
			content+='<TD vAlign=top align=middle> <TABLE border=0 cellSpacing=4 cellPadding=0      width=130>      <TBODY>      <TR>   <TD width="100%" align=middle>'+
            '<DIV><A class=pic   href="#" onclick="'+temp+'"><IMG  class=img_1 border=0   src="'+'images/'+picIndex+'"'+
            '  width=140   height=105></A></DIV></TD></TR>      <TR>       <TD align=middle>   <TABLE border=0 cellSpacing=4 cellPadding=0 '+
           ' width="90%">    <TBODY>      <TR> <TD class=txt01 height=25 align=middle><A   href="">'+themeStr[themeIndex]+
           '</A></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD>';
			content+="</TR>";
			}
		else 
			{
			 content+='<TD vAlign=top align=middle> <TABLE border=0 cellSpacing=4 cellPadding=0      width=130>      <TBODY>      <TR>   <TD width="100%" align=middle>'+
             '<DIV><A class=pic   href="#" onclick="'+temp+'"><IMG    class=img_1 border=0   src="'+'images/'+picIndex+'"'+
             '  width=140   height=105></A></DIV></TD></TR>      <TR>       <TD align=middle>   <TABLE border=0 cellSpacing=4 cellPadding=0 '+
            ' width="90%">    <TBODY>      <TR> <TD class=txt01 height=25 align=middle><A   href="">'+themeStr[themeIndex]+
            '</A></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD>';
            }
		}
	content+=contentEnd;
	
	testdiv.innerHTML=content;
}

function setImg(imgSrc)
{
	var testdiv = document.getElementById("jiazhuangTab");
	testdiv.innerHTML="<IMG src='"+imgSrc+"' />";
}
//function getShow(obj)
//{
//	var url = document.getElementById(obj).href;
//	alert(url);
//}
