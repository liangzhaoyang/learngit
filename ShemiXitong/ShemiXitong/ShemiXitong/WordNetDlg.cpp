// WordNetDlg.cpp : 实现文件
//

#include "stdafx.h"
#include "ShemiXitong.h"
#include "WordNetDlg.h"
#include "afxdialogex.h"


// CWordNetDlg 对话框

IMPLEMENT_DYNAMIC(CWordNetDlg, CDialogEx)

CWordNetDlg::CWordNetDlg(CWnd* pParent /*=NULL*/)
	: CDialogEx(CWordNetDlg::IDD, pParent)
	, m_username(_T(""))
	, m_psw(_T(""))
{

}

CWordNetDlg::~CWordNetDlg()
{
}

void CWordNetDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
	DDX_Text(pDX, IDC_USERNAME, m_username);
	DDX_Text(pDX, IDC_PASSWORD, m_psw);
}


BEGIN_MESSAGE_MAP(CWordNetDlg, CDialogEx)
	ON_BN_CLICKED(IDOK, &CWordNetDlg::OnBnClickedOk)
END_MESSAGE_MAP()


// CWordNetDlg 消息处理程序


void CWordNetDlg::OnBnClickedOk()
{
	// TODO: 在此添加控件通知处理程序代码
	//CDialogEx::OnOK();
	//更新变量信息
	UpdateData(TRUE);

	bool flag=false;
	//判断是否为空
	if(m_username.IsEmpty()||m_psw.IsEmpty())
	{
		AfxMessageBox(_T("用户名或者密码不能为空！"));
		return;
	}
	
	//将字符转为MD5加密的形式
	m_psw=m_psw.Trim();
	md5.update(m_psw.GetBuffer(),m_psw.GetLength());
	m_psw=md5.toString().c_str();
	md5.reset();
	//ProducePwdFile();

	//验证用户名和密码是否正确
	ADOConn m_AdoConn;
	m_AdoConn.OnInitADOConn();
	CString sql;
	sql.Format(_T("select* from t_user"));
	_RecordsetPtr  m_pRecordset;
	m_pRecordset=m_AdoConn.GetRecordSet((_bstr_t)sql);
	//去掉字符的空白字符
	m_username=m_username.TrimLeft().TrimRight();
	m_psw=m_psw.TrimLeft().TrimRight();
	//AfxMessageBox(m_username+m_psw);
	while(m_AdoConn.m_pRecordset->adoEOF==0)
	{
		CString name,pwd;
		name=(LPCTSTR)(_bstr_t)m_pRecordset->GetCollect(_T("f_username"));
		pwd=(LPCTSTR)(_bstr_t)m_pRecordset->GetCollect(_T("f_pwd"));
		name=name.TrimLeft().TrimRight();
		pwd=pwd.TrimLeft().TrimRight();
		//AfxMessageBox(name+pwd);
		if((m_username.Trim()==name.Trim())&&(m_psw.Trim()==pwd.Trim()))
		{
			flag=true;

			break;
		}		
		m_pRecordset->MoveNext();
	}
	m_AdoConn.ExitConnect();

	//如果用户名和密码正确
	if(flag)
	{
			//关闭当前对话框
			//EndDialog(0);

			//弹出控制界面
			CModifyWordNet dlg;
			INT_PTR nResponse = dlg.DoModal();
			if (nResponse == IDOK)
			{
				// TODO: 在此放置处理何时用
				//  “确定”来关闭对话框的代码
			}
			else if (nResponse == IDCANCEL)
			{
				// TODO: 在此放置处理何时用
				//  “取消”来关闭对话框的代码
			}
		
	}
	else
	{
		//提示重新输入
		AfxMessageBox(_T("用户名或者密码不正确！"));
	}
}

//生成密文文件
void CWordNetDlg::ProducePwdFile()
{
	CFile   file;  
	 CString str1,str2,str3,test;
	 str1=_T("admin");
	 str2=_T("root");
	 str3=_T("123456");

	 file.Open(_T("pwd.txt"),CFile::modeCreate|CFile::modeWrite|CFile::modeNoTruncate);
	 md5.update(str1.GetBuffer(),str1.GetLength());
	test=md5.toString().c_str();
	file.Write(test.GetBuffer(test.GetLength()),test.GetLength()*sizeof(TCHAR)); 
	 file.Write(_T("\r\n"),2);
	 md5.reset();
	 //
	  md5.update(str2.GetBuffer(),str2.GetLength());
	test=md5.toString().c_str();
	file.Write(test.GetBuffer(test.GetLength()),test.GetLength()*sizeof(TCHAR)); 
	 file.Write(_T("\r\n"),2);
	 md5.reset();
	 //
	  md5.update(str3.GetBuffer(),str3.GetLength());
	test=md5.toString().c_str();
	file.Write(test.GetBuffer(test.GetLength()),test.GetLength()*sizeof(TCHAR)); 
	 file.Write(_T("\r\n"),2);
	 md5.reset();
	 //
	 file.Flush();
	 file.Close();
}