// FileDetectDlg.cpp : 实现文件
//

#include "stdafx.h"
#include "SecretInfo.h"
#include "FileDetectDlg.h"
#include "afxdialogex.h"

typedef  void(__stdcall *Dllfun)  (int, char**);
// CFileDetectDlg 对话框

IMPLEMENT_DYNAMIC(CFileDetectDlg, CDialogEx)

CFileDetectDlg::CFileDetectDlg(CWnd* pParent /*=NULL*/)
	: CDialogEx(CFileDetectDlg::IDD, pParent)
{

}

CFileDetectDlg::~CFileDetectDlg()
{
}

void CFileDetectDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
	DDX_Control(pDX, IDC_LIST_FILE, m_resultList);
}


BEGIN_MESSAGE_MAP(CFileDetectDlg, CDialogEx)
	ON_BN_CLICKED(IDC_FILE_ADD, &CFileDetectDlg::OnBnClickedFileAdd)
	ON_BN_CLICKED(IDC_FILE_DELETE, &CFileDetectDlg::OnBnClickedFileDelete)
	ON_BN_CLICKED(IDOK, &CFileDetectDlg::OnBnClickedFileDetect)
END_MESSAGE_MAP()


// CFileDetectDlg 消息处理程序

//添加待检查文件
void CFileDetectDlg::OnBnClickedFileAdd()
{
	// TODO: 在此添加控件通知处理程序代码
	//选择需要添加的文件
	CString filters = _T("文件(*.txt)|*.txt|文件(*.pdf)|*.pdf|所有文件(*.*) |*.*||"); 
	try{
		CFileDialog dlg(TRUE, NULL, NULL, OFN_ALLOWMULTISELECT, filters);
		DWORD MAXFILE = 4000;
		dlg.m_ofn.nMaxFile = MAXFILE;
		TCHAR* pc = new TCHAR[MAXFILE];
		dlg.m_ofn.lpstrFile = pc;
		dlg.m_ofn.lpstrFile[0] = NULL;
		int iReturn = dlg.DoModal();
		//将文件添加到列表
		if(iReturn == IDOK)
		{
			int nCount = 0;
			CString strName;
			POSITION pos = dlg.GetStartPosition();
			CString strCode;
			while (pos != NULL)
			{
				strName=dlg.GetNextPathName(pos);
				 //将选中的添加到检查列
				 m_resultList.InsertItem(0,_T(""));
				 strCode.Format(_T("%d"),count++);
				 m_resultList.SetItemText(0,0,strCode);
				 m_resultList.SetItemText(0,1,strName);
			}
			
		}
	
		delete []pc;
	}catch(...)
	{
		AfxMessageBox(_T("加载文件异常！"));
	}
}

//删除待检查文件
void CFileDetectDlg::OnBnClickedFileDelete()
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

//检查文件是否涉密
void CFileDetectDlg::OnBnClickedFileDetect()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(TRUE);
	
	//获取检测结果
	int result=DetectSecretInfo();	
	
}

//初始化对话框
BOOL CFileDetectDlg::OnInitDialog()
{
	CDialogEx::OnInitDialog();

	// TODO:  在此添加额外的初始化
	//设置ListCtrl控件
	m_resultList.SetExtendedStyle(LVS_EX_FLATSB|LVS_EX_FULLROWSELECT|LVS_EX_HEADERDRAGDROP|LVS_EX_ONECLICKACTIVATE|LVS_EX_GRIDLINES);
	m_resultList.InsertColumn(0,_T("序号"),LVCFMT_LEFT,50,0);
	m_resultList.InsertColumn(1,_T("文件名"),LVCFMT_LEFT,500,0);
	//
	count=1;

	return TRUE;  // return TRUE unless you set the focus to a control
	// 异常: OCX 属性页应返回 FALSE
}


//获取后缀名
CString CFileDetectDlg::GetExtName(CString fileName)
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
int CFileDetectDlg::DetectSecretInfo()
{
	//更新信息
	UpdateData(TRUE);
	//遍历所有行
	int len=m_resultList.GetItemCount();
	int i;

	//判断是否已经选择了文件
	if(len==0)
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
		//将结果写入数据库
		WriteDetect2DB(m_resultList.GetItemText(i,1));
	}
	return result;
}
//返回检测结果
int CFileDetectDlg::GetDetectResult(CString filename)
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
int CFileDetectDlg::GetExtType(CString fileExtName)
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

void CFileDetectDlg::Pdf2TXT(char* filename)//将PDF转为TXT
{
	try{
		Dllfun pdfToTxt;
		HINSTANCE hdll;
		hdll = LoadLibrary(_T("DLLXpdf.dll"));// 
		if (hdll == NULL)
		{
			FreeLibrary(hdll);
		}

		pdfToTxt = (Dllfun)GetProcAddress(hdll, "_PdfToTxt@8");//
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
	}catch(...)
	{
		MessageBox(_T("pdf文件打开失败！"),_T("PDF文件读取"),MB_ICONERROR);
	}
}
void CFileDetectDlg::PIC2TXT(CString filename)
{
	OCRTxt(filename,_T("tmp.txt"));
}
//将word转为txt文件
void CFileDetectDlg::Word2TXT(CString filename)
{
	ReadWord(filename);
}

//将excel转为txt文件
void CFileDetectDlg::Excel2TXT(CString filename)
{
	ReadExcel(filename);
}
//将ppt转为txt文件
void CFileDetectDlg::PPT2TXT(CString filename)
{
	ReadPowerPoint(filename);
}

void CFileDetectDlg::UpdateInfo(CString filename,int level)
{
	//获取当前日期信息
	CTime curTime=CTime::GetCurrentTime();
	CString sEndtime=curTime.Format("%Y-%m-%d %H:%M:%S");

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
			m_pRecordset->put_Collect(_variant_t(_T("f_filename")),_variant_t(filename));
			m_pRecordset->put_Collect(_variant_t(_T("f_level")),_variant_t(level));
			m_pRecordset->put_Collect(_variant_t(_T("f_date")),_variant_t(sEndtime));
			m_pRecordset->Update();
			m_AdoConn.ExitConnect();

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


//分割字符串
void CFileDetectDlg::Split(CString source, CString divKey, CStringArray& dest)
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

void CFileDetectDlg::ReadWord(CString filename)
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

void CFileDetectDlg::ReadExcel(CString filename)
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

void CFileDetectDlg::ReadPowerPoint(CString filename)
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

void CFileDetectDlg::WriteDetect2DB(CString filename)
{
	//获取文件名
	CString filenameTmp=GetFileName(filename);
	//写入数据库
	try{
		//读取文件内容
		CFile file(_T("SecretReport.txt"),CFile::modeRead); 
		wchar_t *pBuf; 
		DWORD dwFlielen;
		dwFlielen=file.GetLength();
		pBuf=new wchar_t[dwFlielen+1];
		pBuf[dwFlielen]=0;
		file.Read(pBuf,dwFlielen);
		//
		CString source=pBuf;
		CStringArray dest,subDest;
		Split(source,_T("\r\n"),dest);
		int i,j,count;

		//连接数据库
		ADOConn m_AdoConn;
		m_AdoConn.OnInitADOConn();
		//删除已存在记录
		CString sql,str;
		sql.Format(_T("delete from t_report where f_filename like '%s%%'"),filenameTmp.Trim());
		m_AdoConn.ExecuteSQL((_bstr_t)sql);
		//添加新纪录
		sql.Format(_T("select* from t_report"));
		_RecordsetPtr  m_pRecordset;
		m_pRecordset=m_AdoConn.GetRecordSet((_bstr_t)sql);

		//分割字符串
		for(i=0;i<dest.GetSize()-1;i=i+2)
		{
			if(!dest.GetAt(i).IsEmpty()&&(!dest.GetAt(i+1).IsEmpty()))
			{
				//AfxMessageBox(dest.GetAt(i));
				//写入数据库		
				//将每行的涉密词和出现次数分开				
				try{
					m_pRecordset->AddNew();
					//str=dest.GetAt(i);
					//AfxMessageBox(str);
					m_pRecordset->put_Collect(_variant_t(_T("f_filename")),_variant_t(filenameTmp));
					if(i==0)
						m_pRecordset->put_Collect(_variant_t(_T("f_word")),_variant_t(_T("")));
					else
						m_pRecordset->put_Collect(_variant_t(_T("f_word")),_variant_t(dest.GetAt(i)));
					//m_pRecordset->put_Collect(_variant_t(_T("f_word")),_variant_t(dest.GetAt(i)));
					count=_ttoi(dest.GetAt(i+1));
					m_pRecordset->put_Collect(_variant_t(_T("f_repeat")),_variant_t(count));
					m_pRecordset->Update();

				}
				catch(...)
				{
					AfxMessageBox(_T("操作失败"));
					return;
				}			
			}
		}
		file.Close();
		m_AdoConn.ExitConnect();
		//AfxMessageBox(pBuf);

	}catch(...)
	{
		AfxMessageBox(_T("将涉密检测结果写入数据库失败！"));  
	}
}
//获取文件名
CString CFileDetectDlg::GetFileName(CString path)
{
	CString str;
	 int pos=-1;

	 for(int i=path.GetLength();i>0;i--)
	 {
	  pos=path.Find(_T("\\"),i);
	  if(pos>0)
	   break;
	 }
	 str=path.Right(pos-1);//获取路径名

	 return str;
}