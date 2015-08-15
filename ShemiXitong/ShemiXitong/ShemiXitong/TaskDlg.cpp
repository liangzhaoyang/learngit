// TaskDlg.cpp : 实现文件
//

#include "stdafx.h"
#include "ShemiXitong.h"
#include "TaskDlg.h"
#include "afxdialogex.h"
//#include <altbase.h>

typedef  void(__stdcall *Dllfun)  (int, char**);
//typedef  void (*pOCRTxt) (CString, CString);
// CTaskDlg 对话框

IMPLEMENT_DYNAMIC(CTaskDlg, CDialogEx)

CTaskDlg::CTaskDlg(CWnd* pParent /*=NULL*/)
	: CDialogEx(CTaskDlg::IDD, pParent)
	, m_fileName(_T(""))
	, m_queryStr(_T(""))
{

}

CTaskDlg::~CTaskDlg()
{
}

void CTaskDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
	DDX_Control(pDX, IDC_SEARCHLIST, m_searchList);
	DDX_Control(pDX, IDC_RESULTLIST, m_resultList);
	DDX_Text(pDX, IDC_SEARCHSPECIFIC, m_fileName);
	DDX_Text(pDX, IDC_KEYWORD, m_queryStr);
}


BEGIN_MESSAGE_MAP(CTaskDlg, CDialogEx)
	ON_BN_CLICKED(IDC_DETECTBUT, &CTaskDlg::OnBnClickedDetect)
	ON_NOTIFY(NM_CLICK, IDC_SEARCHLIST, &CTaskDlg::OnNMClickSearchlist)
	ON_NOTIFY(NM_CLICK, IDC_RESULTLIST, &CTaskDlg::OnNMClickResultlist)
	ON_BN_CLICKED(IDC_ADDBUT, &CTaskDlg::OnBnClickedAddInfo)
	ON_BN_CLICKED(IDC_DELETEBUT, &CTaskDlg::OnBnClickedDeleteInfo)
	ON_BN_CLICKED(IDC_SEARCHBUT, &CTaskDlg::OnBnClickedSearch)
END_MESSAGE_MAP()


// CTaskDlg 消息处理程序


BOOL CTaskDlg::OnInitDialog()
{
	CDialogEx::OnInitDialog();

	// TODO:  在此添加额外的初始化
	//设置ListCtrl控件
	m_searchList.SetExtendedStyle(LVS_EX_FLATSB|LVS_EX_FULLROWSELECT|LVS_EX_HEADERDRAGDROP|LVS_EX_ONECLICKACTIVATE|LVS_EX_GRIDLINES);
	m_searchList.InsertColumn(0,_T("序号"),LVCFMT_LEFT,50,0);
	m_searchList.InsertColumn(1,_T("文件名"),LVCFMT_LEFT,100,0);

	m_resultList.SetExtendedStyle(LVS_EX_FLATSB|LVS_EX_FULLROWSELECT|LVS_EX_HEADERDRAGDROP|LVS_EX_ONECLICKACTIVATE|LVS_EX_GRIDLINES);
	m_resultList.InsertColumn(0,_T("序号"),LVCFMT_LEFT,50,0);
	m_resultList.InsertColumn(1,_T("文件名"),LVCFMT_LEFT,100,0);
	//初始化
	
	return TRUE;  // return TRUE unless you set the focus to a control
	// 异常: OCX 属性页应返回 FALSE
}


void CTaskDlg::OnBnClickedDetect()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(TRUE);
	
	//获取检测结果
	int result=DetectSecretInfo();		
	//AfxMessageBox(_T("检测完成！"));
}

CString CTaskDlg::GetExtName(CString fileName)//获取后缀名
{
	int pos=fileName.Find(_T(".")); //获取 . 的位置
	if(pos==-1){ //如果没有找到，直接返回该字符串
		return fileName; 
		}
	else{
		return GetExtName(fileName.Mid(pos+1)); //找到了的话，往深层遍历，直到最底层
	}
}
//检测涉密情况
int CTaskDlg::DetectSecretInfo()
{
	//更新信息
	UpdateData(TRUE);
	//遍历所有行
	int len=m_resultList.GetItemCount();
	int i;

	//判断是否已经选择了文件
	if(m_fileName=="" && len==0)
	{
		AfxMessageBox(_T("请选择文件！"));
		return -1;
	}
	//对文件进行检测
	int result=-1;
	//模糊查找
	for(i=0;i<len;i++)
	{
		//AfxMessageBox(m_resultList.GetItemText(i,1));
		result=GetDetectResult(m_resultList.GetItemText(i,1));
	}
	//精确查找
	if(!(m_fileName==""))
	{
		result=GetDetectResult(m_fileName);
	}

	return result;
}
//返回检测结果
int CTaskDlg::GetDetectResult(CString filename)
{
	CString str;
	//获取后缀名
	CString fileExtName=GetExtName(filename);
	int extType=GetExtType(fileExtName);

	//对文件名进行字符转换
	USES_CONVERSION;
	char* buf = T2A(filename);

	//匹配成功的字符串数
	int result=-1;
	//对于不同后缀名格式文件分别进行处理
	switch(extType)
	{
	case TXTEXTNAME://若为TXT格式文件
		
		result = last_search(buf,"pattern.txt");
		//str.Format(_T("匹配成功的字符串数：%d"),result);
		//AfxMessageBox(str);
		break;
	case PDFEXTNAME://若为PDF格式文件
		//将pdf转为txt文件
		Pdf2TXT(buf);
		result = last_search("tmp.txt","pattern.txt");
		//str.Format(_T("匹配成功的字符串数：%d"),result);
		//AfxMessageBox(str);
		break;
	case WORDEXTNAME://若为WORD格式文件
		//将word转为txt文件
		Word2TXT(filename);
		result = last_search("tmp.txt","pattern.txt");
		break;
	case EXCELEXTNAME://若为EXCEL格式文件
		//将word转为txt文件
		Excel2TXT(filename);
		result = last_search("tmp.txt","pattern.txt");
		break;
	case PPTEXTNAME://若为PPT格式文件
		//将word转为txt文件
		PPT2TXT(filename);
		result = last_search("tmp.txt","pattern.txt");
		break;
	case PICTUREEXTNAME://若为jpg,bmp,png,tif图片格式
		PIC2TXT(filename);
		result = last_search("tmp.txt","pattern.txt");
		break;
	case ERROREXTNAME:
		AfxMessageBox(_T("输入的文件格式不能识别！"));
		break;
	default:
		AfxMessageBox(_T("输入的文件格式不能识别！"));
	}

	//判断检测情况
	if(result==-1)
	{
		//AfxMessageBox(_T("打开文件出现错误！"));
		return result;
	}

	//
	int level=result/SECRETLEVEL;
	//更新数据库信息
	CString fileName=filename.Mid(filename.ReverseFind('\\')+1);
	//AfxMessageBox(fileName);
	if(level>=5)
		level=5;
	UpdateInfo(fileName,level);
	//判断涉密等级
	switch(level)
	{
	case 0:
		str.Format(_T("%s未涉密！"),fileName);
		AfxMessageBox(str);
		break;
	case 1:
		str.Format(_T("%s涉密等级为1！"),fileName);
		AfxMessageBox(str);
		//AfxMessageBox(_T("涉密等级为1！"));
		break;
	case 2:
		str.Format(_T("%s涉密等级为2！"),fileName);
		AfxMessageBox(str);
		//AfxMessageBox(_T("涉密等级为2！"));
		break;
	case 3:
		str.Format(_T("%s涉密等级为3！"),fileName);
		AfxMessageBox(str);
		//AfxMessageBox(_T("涉密等级为3！"));
		break;
	case 4:
		str.Format(_T("%s涉密等级为4！"),fileName);
		AfxMessageBox(str);
		//AfxMessageBox(_T("涉密等级为4！"));
		break;
	default:
		str.Format(_T("%s涉密等级为5！"),fileName);
		AfxMessageBox(str);
		//AfxMessageBox(_T("涉密等级为5！"));
	}

	return result;
}
//返回文件格式类型
int CTaskDlg::GetExtType(CString fileExtName)
{
	if((fileExtName=="txt")||(fileExtName=="TXT"))
	{
		return TXTEXTNAME;
	}
	else if((fileExtName=="pdf")||(fileExtName=="PDF"))
	{
		return PDFEXTNAME;
	}
	else if((fileExtName=="doc")||(fileExtName=="DOC")||(fileExtName=="docx")||(fileExtName=="DOCX"))
	{
		return WORDEXTNAME;
	}
	else if((fileExtName=="xls")||(fileExtName=="XLS")||(fileExtName=="xlsx")||(fileExtName=="XLSX"))
	{
		return EXCELEXTNAME;
	}
	else if((fileExtName=="ppt")||(fileExtName=="PPT")||(fileExtName=="pptx")||(fileExtName=="PPTX"))
	{
		return PPTEXTNAME;
	}
	else if((fileExtName=="png")||(fileExtName=="PNG")||(fileExtName=="JPEG")||(fileExtName=="jpeg")
		||(fileExtName=="jpg")||(fileExtName=="JPG")||(fileExtName=="bmp")||(fileExtName=="BMP")
		||(fileExtName=="tif")||(fileExtName=="TIF"))
	{
		return PICTUREEXTNAME;
	}
	else
	{
		return ERROREXTNAME;
	}
}

void CTaskDlg::Pdf2TXT(char* filename)//将PDF转为TXT
{
	Dllfun pdfToTxt;
	HINSTANCE hdll;
	hdll = LoadLibrary(_T("DLLXpdf.dll"));// D:\\Develop\\Projects\\VS2013\\xpdf - 3.02\\xpdf\\Debug\\DLLXpdf.dll");
	if (hdll == NULL)
	{
		FreeLibrary(hdll);
	}

	pdfToTxt = (Dllfun)GetProcAddress(hdll, "_PdfToTxt@8");// MAKEINTRESOURCE(1));// "PdfToTxt");
	if (pdfToTxt == NULL)
	{
		FreeLibrary(hdll);
	}
	else
	{
		int argsCount = 5;
		// 5个参数，前面三个固定，第四个是pdf文件的完整路径，第五个是输出文件的路径
		//USES_CONVERSION;
		//char* buf = T2A(m_fileName);

		char* temp[5]={"a", 
			"-enc", "UTF-8", 
			filename,
			//"基于自学习的特定人识别的语音驾驶助手.pdf", 
			"tmp.txt" };
		char** args=temp; 

		pdfToTxt(argsCount, args);
	}
}
void CTaskDlg::PIC2TXT(CString filename)
{
	OCRTxt(filename,_T("tmp.txt"));
}
//将word转为txt文件
void CTaskDlg::Word2TXT(CString filename)
{
	ReadWord(filename);
}

//将excel转为txt文件
void CTaskDlg::Excel2TXT(CString filename)
{
	ReadExcel(filename);
}
//将ppt转为txt文件
void CTaskDlg::PPT2TXT(CString filename)
{
	ReadPowerPoint(filename);
}

void CTaskDlg::UpdateInfo(CString filename,int level)
{
	//连接数据库
	ADOConn m_AdoConn;
	m_AdoConn.OnInitADOConn();
	CString sql;
	sql.Format(_T("select* from t_info"));
	_RecordsetPtr  m_pRecordset;
	m_pRecordset=m_AdoConn.GetRecordSet((_bstr_t)sql);
	//判断该文本是否已经检查过了
	CString file;
	while(m_AdoConn.m_pRecordset->adoEOF==0)
	{
		file=(LPCTSTR)(_bstr_t)m_pRecordset->GetCollect(_T("f_filename"));
		if(file.Trim()==filename)
		{
			AfxMessageBox(_T("该文件已经经过涉密检查了！"));
			return;
		}
		m_pRecordset->MoveNext();
	}
	//插入新的数据
	if(filename.GetLength()>80)
	{
		AfxMessageBox(_T("该文件名过长，无法插入数据库！"));
		return;
	}

	CTime curTime=CTime::GetCurrentTime();
	CString sEndtime=curTime.Format("%Y-%m-%d %H:%M:%S");
	try{
		m_pRecordset->AddNew();
		m_pRecordset->put_Collect(_variant_t(_T("f_filename")),_variant_t(filename));
		m_pRecordset->put_Collect(_variant_t(_T("f_level")),_variant_t(level));
		m_pRecordset->put_Collect(_variant_t(_T("f_date")),_variant_t(sEndtime));
		m_pRecordset->Update();
		m_AdoConn.ExitConnect();
	}
	catch(...)
	{
		AfxMessageBox(_T("操作失败"));
		return;
	}
}

void CTaskDlg::OnNMClickSearchlist(NMHDR *pNMHDR, LRESULT *pResult)
{
	LPNMITEMACTIVATE pNMItemActivate = reinterpret_cast<LPNMITEMACTIVATE>(pNMHDR);
	// TODO: 在此添加控件通知处理程序代码
	*pResult = 0;

}


void CTaskDlg::OnNMClickResultlist(NMHDR *pNMHDR, LRESULT *pResult)
{
	LPNMITEMACTIVATE pNMItemActivate = reinterpret_cast<LPNMITEMACTIVATE>(pNMHDR);
	// TODO: 在此添加控件通知处理程序代码
	*pResult = 0;
}


void CTaskDlg::OnBnClickedAddInfo()
{
	// TODO: 在此添加控件通知处理程序代码
	CString str,m_name;
	int nId,rowId;
	 //首先得到点击的位置
	POSITION pos=m_searchList.GetFirstSelectedItemPosition();
	 if(pos==NULL)
	 {
		 AfxMessageBox(_T("请至少选择一项"));
		return;
	 }
	 else
	 {
		 while (pos)
		{
			 //得到行号，通过POSITION转化
			 nId=(int)m_searchList.GetNextSelectedItem(pos);
			 //得到列中的内容（0表示第一列，同理1,2,3...表示第二，三，四...列）

			 //将选中的添加到检查列
			 m_resultList.InsertItem(0,_T(""));
			 m_resultList.SetItemText(0,0,m_searchList.GetItemText(nId,0));
			 m_resultList.SetItemText(0,1,m_searchList.GetItemText(nId,1));
		 }
	 }
	 //AfxMessageBox(str);
	 //刷新控件值
	 UpdateData(FALSE); 
}


void CTaskDlg::OnBnClickedDeleteInfo()
{
	// TODO: 在此添加控件通知处理程序代码

	int nItem;
		 //首先得到点击的位置
	POSITION pos=m_resultList.GetFirstSelectedItemPosition();
	 if(pos==NULL)
	 {
		 AfxMessageBox(_T("请至少选择一项"));
		return;
	 }
	 else
	 {
		while (pos)
		{
			nItem = m_resultList.GetNextSelectedItem(pos);
			m_resultList.DeleteItem(nItem);
		}
	 }

	  //刷新控件值
	 UpdateData(FALSE); 
}


void CTaskDlg::OnBnClickedSearch()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(TRUE);

	//判断输入字符是否为空，如为空，则直接返回
	if(m_queryStr.IsEmpty())
	{
		MessageBox(_T("输入的内容不能为空！"),_T("查询"), MB_ICONERROR); 
		return ;
	}

	//弹出控制界面
	CSearchDlg dlg;
	dlg.SetSearchValue(m_queryStr);
	INT_PTR nResponse = dlg.DoModal();

	if (nResponse == IDOK)
	{
		// TODO: 在此放置处理何时用
		//  “确定”来关闭对话框的代码
		//读出查找的结果
		CFile pfile;
		if(!pfile.Open(_T("queryfile.txt"),CFile::modeRead))
			return;
		TCHAR* pBuf;
		int dwFileLen;
		dwFileLen = pfile.GetLength();
		pBuf=new TCHAR[dwFileLen+1];
		memset(pBuf,0,dwFileLen+1);
		pfile.Read(pBuf,dwFileLen);
		pBuf[dwFileLen]='\0';
		CString str(pBuf);
		CStringArray arr;
		Split(str,_T("\r\n"),arr);
		int nSize = arr.GetSize(); 
		//将结果插入list控件中
		CString code;
		for (int i = 0; i < nSize-1; i++)
		{
			//str+=arr.GetAt(i);
			code.Format(_T("%d"),i+1);
			m_searchList.InsertItem(0,_T(""));//插入行
			m_searchList.SetItemText(0,0,code);
			m_searchList.SetItemText(0,1,arr.GetAt(i));
		}

		//AfxMessageBox(str);
		pfile.Close();
		delete [] pBuf;
		AfxMessageBox(_T("搜索完毕！"));
	}
}
//分割字符串
void CTaskDlg::Split(CString source, CString divKey, CStringArray& dest)
{
	dest.RemoveAll();
     int pos = 0;
     int pre_pos = 0;
     while( -1 != pos ){
         pre_pos = pos;
         pos = source.Find(divKey,(pos+1));
         dest.Add(source.Mid(pre_pos,(pos-pre_pos)));
     }
}

void CTaskDlg::ReadWord(CString filename)
{
	try{
		//打开指定路径文件
		CString FileName=filename;
		if (!wordApp.CreateDispatch(_T("Word.Application"),NULL)) {
			AfxMessageBox(_T("Word failed to start!"));
			return ;
		}
		wordApp.put_Visible(FALSE);
		docs = wordApp.get_Documents();
		COleVariant vTrue((short)TRUE);    
		COleVariant vFalse((short)FALSE);
		COleVariant vOptional((long)DISP_E_PARAMNOTFOUND, VT_ERROR);
		//COleVariant vFileName(FileName + ".docx");
		COleVariant vFileName(FileName);
		doc = docs.Open(vFileName,vTrue,vTrue,vTrue,vOptional,vOptional,vOptional,vOptional,vOptional,vOptional,vOptional,vOptional,vOptional,vOptional,vOptional,vOptional);
		wordRange = doc.Range(vOptional,vOptional);

		//创建一个记事本文件
		CFile   file;
		file.Open(_T("tmp.txt"),CFile::modeCreate|CFile::modeWrite);
		WORD unicode = 0xFEFF;
		file.Write(&unicode,2);	
		//AfxMessageBox(wordRange.get_Text());
		//将提取的word内容写入文件
		CString str=wordRange.get_Text();
		file.Write(str,wcslen(str)*sizeof(wchar_t));
		//关闭文档
		file.Flush();
		file.Close();
	
		//释放资源
		doc.ReleaseDispatch();

		docs.ReleaseDispatch();
		wordApp.ReleaseDispatch();
	}catch(...)
	{
		AfxMessageBox(_T("打开文件失败！"));
	}
}

void CTaskDlg::ReadExcel(CString filename)
{
	LPDISPATCH lpDisp = NULL;
	//
	 COleVariant vResult;
	COleVariant
        covTrue((short)TRUE),
        covFalse((short)FALSE),
        vtMissing((long)DISP_E_PARAMNOTFOUND, VT_ERROR);    
 
	//打开指定路径文件
	CString FileName=filename;
	if (!excelApp.CreateDispatch(_T("Excel.Application"),NULL)) {
		AfxMessageBox(_T("Excel failed to start!"));
		return ;
	}
	//设置excel表不可见
	excelApp.put_Visible(FALSE);
	
	//创建一个文本文件
	CFile   file;
	file.Open(_T("tmp.txt"),CFile::modeCreate|CFile::modeWrite);
	WORD unicode = 0xFEFF;
	file.Write(&unicode,2);	

	try
    {
		
        //打开一个工作簿
		books.AttachDispatch(excelApp.get_Workbooks());
		
        lpDisp = books.Open(FileName, 
            vtMissing, vtMissing, vtMissing, vtMissing, vtMissing,
            vtMissing, vtMissing, vtMissing, vtMissing, vtMissing, 
            vtMissing, vtMissing, vtMissing, vtMissing);

        book.AttachDispatch(lpDisp);

		//得到Worksheets  
		sheets.AttachDispatch(book.get_Worksheets());

		 //如果有单元格正处于编辑状态中，此操作不能返回，会一直等待  
		lpDisp=book.get_ActiveSheet();  
		sheet.AttachDispatch(lpDisp); 
		
		//得到工作簿中的Sheet的容器
		// sheets.AttachDispatch(book.get_Sheets());
		 
		 //读取已经使用区域的信息，包括已经使用的行数、列数、起始行、起始列
		 CRange0 usedRange;
		 usedRange.AttachDispatch(sheet.get_UsedRange());
		 excelRange.AttachDispatch(usedRange.get_Rows());
		 long iRowNum=excelRange.get_Count();                   //已经使用的行数
 
		 excelRange.AttachDispatch(usedRange.get_Columns());
		 long iColNum=excelRange.get_Count();                   //已经使用的列数
  
		 long iStartRow=usedRange.get_Row();               //已使用区域的起始行，从1开始
		 long iStartCol=usedRange.get_Column();            //已使用区域的起始列，从1开始
		//
		 long i,j;
		 for(i=iStartRow;i<=iRowNum;i++)
			 for(j=iStartCol;j<=iColNum;j++)
			{
				 //读取第一个单元格的值
				 excelRange.AttachDispatch(sheet.get_Cells()); 
				 excelRange.AttachDispatch(excelRange.get_Item(COleVariant((long)i),COleVariant((long)j)).pdispVal );
				 COleVariant vResult =excelRange.get_Value2();
				 CString str;
				 if(vResult.vt == VT_BSTR)       //字符串
				 {
				  str=vResult.bstrVal;
				 }
				 else if (vResult.vt==VT_R8)     //8字节的数字 
				 {
				  str.Format(_T("%f"),vResult.dblVal);
				 }
				 else 
					 str="";
				 //AfxMessageBox(str);
				file.Write(str,wcslen(str)*sizeof(wchar_t));
			 }
	}
    catch(...)
    {
       AfxMessageBox(_T("打开文件失败！"));
    }

	
		//关闭文档
	file.Flush();
	file.Close();
    /*释放资源*/
    sheet.ReleaseDispatch();
    sheets.ReleaseDispatch();
    book.ReleaseDispatch();
    books.ReleaseDispatch();
    excelApp.Quit();
    excelApp.ReleaseDispatch();

	//AfxMessageBox(aRange.get_Text());
}

void CTaskDlg::ReadPowerPoint(CString filename)
{
	CString strText;
	//
	if (!pptApp.CreateDispatch(_T("Powerpoint.Application"),NULL)) {
		AfxMessageBox(_T("ppt failed to start!"));
		return ;
	}
	//设置不可见
	//pptApp.put_Visible(FALSE);
	pptApp.put_Visible((long)TRUE);
	// 如果嫌PowerPoint窗口烦人，可以把窗口移到桌面外面去如：
	//pptApp.put_Top(2000);
	//
	//CString filename=filename;
	try
	{
		//打开文件
		presentations = pptApp.get_Presentations();
        presentation = presentations.Open(filename,0,0,1);

		// 获取幻灯片数目
		int i,j;
		slides=presentation.get_Slides();
		//
		//创建一个文本文件，用来存储提取的结果
		CFile   file;
		file.Open(_T("tmp.txt"),CFile::modeCreate|CFile::modeWrite);
		WORD unicode = 0xFEFF;
		file.Write(&unicode,2);	
		//提取每一页上的文本
		for(i = 1; i <=slides.get_Count(); i++) {
			slide = slides.Range(COleVariant((long)(i)));
			shapes=slide.get_Shapes();
			for(j = 0; j < shapes.get_Count(); j ++) {
				shape=shapes.Item(COleVariant((long)(j + 1)));
				if(shape.get_HasTextFrame()) {
					textFrame = shape.get_TextFrame();
					textRange = textFrame.get_TextRange();
					CString txt = textRange.get_Text();
					if(txt.GetLength() > 0) {
						//strText += txt;
						//写入文本
						file.Write(txt,wcslen(txt)*sizeof(wchar_t));
					}
				}
			}
		}

		//关闭文档
		file.Flush();
		file.Close();
		//AfxMessageBox(strText);
		//释放资源
		presentation.Close();
		pptApp.Quit();

	}catch(...)
	{
		AfxMessageBox(_T("ppt文件打开失败!"));
	}
}