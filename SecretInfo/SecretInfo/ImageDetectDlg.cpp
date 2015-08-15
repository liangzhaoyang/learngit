// ImageDetectDlg.cpp : 实现文件
//

#include "stdafx.h"
#include "SecretInfo.h"
#include "ImageDetectDlg.h"
#include "afxdialogex.h"
#include "AC_BH.h"
#include "ADOConn.h"
#include "OCR.h"

#pragma comment(lib,"OCR.lib")
// CImageDetectDlg 对话框

IMPLEMENT_DYNAMIC(CImageDetectDlg, CDialogEx)

CImageDetectDlg::CImageDetectDlg(CWnd* pParent /*=NULL*/)
	: CDialogEx(CImageDetectDlg::IDD, pParent)
{

}

CImageDetectDlg::~CImageDetectDlg()
{
}

void CImageDetectDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
	DDX_Control(pDX, IDC_LIST_IMAGE, m_resultList);
}


BEGIN_MESSAGE_MAP(CImageDetectDlg, CDialogEx)
	ON_BN_CLICKED(IDC_IMAGE_ADD, &CImageDetectDlg::OnBnClickedImageAdd)
	ON_BN_CLICKED(IDC_IMAGE_DELETE, &CImageDetectDlg::OnBnClickedImageDelete)
	ON_BN_CLICKED(IDOK, &CImageDetectDlg::OnBnClickedDetect)
END_MESSAGE_MAP()


// CImageDetectDlg 消息处理程序


void CImageDetectDlg::OnBnClickedImageAdd()
{
	// TODO: 在此添加控件通知处理程序代码
	//选择需要添加的文件
	CString filters = _T("位图文件(*.bmp)|*.bmp|PNG文件(*.png)|*.png|所有文件(*.*) |*.*||"); 
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


void CImageDetectDlg::OnBnClickedImageDelete()
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


void CImageDetectDlg::OnBnClickedDetect()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(TRUE);
	
	//获取检测结果
	int result=DetectSecretInfo();	

}


BOOL CImageDetectDlg::OnInitDialog()
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
CString CImageDetectDlg::GetExtName(CString fileName)
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
int CImageDetectDlg::DetectSecretInfo()
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
		result=GetDetectResult(m_resultList.GetItemText(i,1));
	}
	return result;
}
//返回检测结果
int CImageDetectDlg::GetDetectResult(CString filename)
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

void CImageDetectDlg::PIC2TXT(CString filename)
{
	OCRTxt(filename,_T("tmp.txt"));
}

void CImageDetectDlg::UpdateInfo(CString filename,int level)
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


//分割字符串
void CImageDetectDlg::Split(CString source, CString divKey, CStringArray& dest)
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

//返回文件格式类型
int CImageDetectDlg::GetExtType(CString fileExtName)
{
	 if((fileExtName=="png")||(fileExtName=="PNG")||(fileExtName=="JPEG")||(fileExtName=="jpeg")
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