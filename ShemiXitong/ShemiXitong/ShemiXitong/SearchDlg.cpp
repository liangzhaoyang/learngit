// SearchDlg.cpp : 实现文件
//

#include "stdafx.h"
#include "ShemiXitong.h"
#include "SearchDlg.h"
#include "afxdialogex.h"

UINT ThreadProc(LPVOID pParam);
// CSearchDlg 对话框

IMPLEMENT_DYNAMIC(CSearchDlg, CDialogEx)

CSearchDlg::CSearchDlg(CWnd* pParent /*=NULL*/)
	: CDialogEx(CSearchDlg::IDD, pParent)
{

}

CSearchDlg::~CSearchDlg()
{
}

void CSearchDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
	DDX_Control(pDX, IDC_PROGRESS, m_progress);
}


BEGIN_MESSAGE_MAP(CSearchDlg, CDialogEx)
	ON_BN_CLICKED(IDOK, &CSearchDlg::OnBnClickedOk)
	ON_WM_TIMER()
	ON_MESSAGE(WM_MY_SEARCH, OnMySearchMessage)
	ON_MESSAGE(WM_END_SEARCH, OnEndSearchMessage)
END_MESSAGE_MAP()


// CSearchDlg 消息处理程序


void CSearchDlg::OnBnClickedOk()
{
	// TODO: 在此添加控件通知处理程序代码
	stopFlag=true;

	//CDialogEx::OnOK();
}


BOOL CSearchDlg::OnInitDialog()
{
	CDialogEx::OnInitDialog();

	// TODO:  在此添加额外的初始化
	//
	stopFlag=false;
	count=0;
	searchEndFlag=false;

	//初始化进度条
	m_progress.SetRange(0,PROGRESS_MAX_LENGTH);
	m_progress.SetStep(1);
	m_progress.SetPos(0);
	m_progress.StepIt();

	//AfxMessageBox(findStr);
	PostMessage(WM_MY_SEARCH);

	return TRUE;  // return TRUE unless you set the focus to a control
	// 异常: OCX 属性页应返回 FALSE
}

//设置查找关键词
void CSearchDlg::SetSearchValue(CString value)
{
	//findStr=value;
	//设置成正则表达式
	findStr.Format(_T(".*%s.*"),value);
}
//
CString CSearchDlg::GetSearchValue()
{
	return findStr;
}

//
bool CSearchDlg::GetEndSearchFlag()
{
	return searchEndFlag;
}
//
void CSearchDlg::SetEndSearchFlag(bool flag)
{
	searchEndFlag=flag;
}
//递归遍历函数
void CSearchDlg::TraversFile(CString csPath)
{
	try{
		 CString csPrePath = csPath;
		CString csNextPath = csPath;
		CFileFind ff;
		csPath += _T("*.*");//遍历这一级全部的目录
		int nResult = ff.FindFile(csPath);
		while(nResult &&(!stopFlag))
		{
			nResult = ff.FindNextFileW();
			if(ff.IsDirectory() && !ff.IsDots())
			{
			   // wcout << (LPCTSTR)ff.GetFilePath() << endl;		

				csNextPath += ff.GetFileName();
				csNextPath += _T("\\");
				TraversFile(csNextPath);
			}
			else if(!ff.IsDirectory() && !ff.IsDots())
			{
				CString str=(LPCTSTR)ff.GetFileName();
				std::string strStl,strTmp;
				//对文件名进行字符转换
				USES_CONVERSION;
				strStl= T2A(findStr);
				strTmp=T2A(str);
				//如果匹配不成功，则继续下一次循环,否则写入文件
				if (!std::regex_match (strTmp, std::regex(strStl)))  
					 continue;
				CFile   file;
				file.Open(_T("queryfile.txt"),CFile::modeCreate|CFile::modeWrite|CFile::modeNoTruncate);
				file.SeekToEnd();
				CString writeInfo;
				writeInfo.Format(_T("%s\%s"),csPrePath,str);
				file.Write(writeInfo,wcslen(writeInfo)*sizeof(wchar_t));
				file.Write(_T("\r\n"),4);
				//关闭文档
				file.Flush();
				file.Close();
			}
			csNextPath = csPrePath;
		}
	}catch(...)
	{
		AfxMessageBox(_T("查找文件异常！"));
	}
}

//查找文件
void CSearchDlg::SearchFile()
{
	//
	CFile   file;
	file.Open(_T("queryfile.txt"),CFile::modeCreate|CFile::modeWrite);
	WORD unicode = 0xFEFF;
	file.Write(&unicode,2);	
	//关闭文档
	file.Flush();
	file.Close();

	 //添加定时器，每隔0.5s监测
	SetTimer(TIME_COUNT,500,NULL);//第一个参数表示计时器的ID；
    //开始搜索
	int hThread;
	DWORD ThreadID;
	//调用createthread创建线程
	hThread=(int)CreateThread(NULL,0,(LPTHREAD_START_ROUTINE)ThreadProc,(void*)this,0,&ThreadID);

	//while(!searchEndFlag);
	
}

void CSearchDlg::OnTimer(UINT_PTR nIDEvent)
{
	// TODO: 在此添加消息处理程序代码和/或调用默认值
	switch(nIDEvent)
	{
	case TIME_COUNT:
		//更新进度条状态
		if(count<PROGRESS_MAX_LENGTH-1){
			count++;
			m_progress.SetPos(count);
			m_progress.StepIt();
		}
		/*else
		{
			m_progress.SetPos(PROGRESS_MAX_LENGTH);
			m_progress.StepIt();
		}*/
		break;
	}
	CDialogEx::OnTimer(nIDEvent);
}

UINT ThreadProc(LPVOID pParam)
{
	CSearchDlg *dlg=(CSearchDlg*)pParam;
	CString queryStr=dlg->GetSearchValue();
	try{

		//CString cs = _T("F:\\");
		//获取所有的逻辑磁盘分区
		DWORD diskSign=GetLogicalDrives();

		int i;
		CString str;	
		//遍历每个磁盘文件
		for(i=2;i<25;i++)
			if((diskSign>>i)&0x1==0x1)
			{
				str.Format(_T("%c:\\"),i+0x41);
				//AfxMessageBox(str);
				 //开始搜索
				dlg->TraversFile(str);
			}
		//开始搜索
		//dlg->TraversFile(cs);

		dlg->SendMessage(WM_END_SEARCH,0x0,0x0);

	}catch(...)
	{

		AfxMessageBox(_T("读取磁盘文件失败！"));

	}
	//
	dlg->SendMessage(WM_END_SEARCH,0x0,0x0);

	return 0;
}

LRESULT  CSearchDlg::OnMySearchMessage(WPARAM wParam, LPARAM lParam)
{
	SearchFile();

	return 0;
}

LRESULT  CSearchDlg::OnEndSearchMessage(WPARAM wParam, LPARAM lParam)
{
	//
	try{
		m_progress.SetPos(PROGRESS_MAX_LENGTH);
		m_progress.StepIt();
		//关闭定时器
		KillTimer(TIME_COUNT);
		//Sleep(2000);
		//退出当前对话框
		CDialogEx::OnOK();
	}catch(...)
	{
		AfxMessageBox(_T("搜索停止异常！"));
	}
	return 0;
}

