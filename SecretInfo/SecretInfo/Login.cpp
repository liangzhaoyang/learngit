// Login.cpp : 实现文件
//

#include "stdafx.h"
#include "SecretInfo.h"
#include "Login.h"
#include "afxdialogex.h"


// CLogin 对话框

IMPLEMENT_DYNAMIC(CLogin, CDialogEx)

CLogin::CLogin(CWnd* pParent /*=NULL*/)
	: CDialogEx(CLogin::IDD, pParent)
	, m_username(_T(""))
	, m_psw(_T(""))
{

}

CLogin::~CLogin()
{
}

void CLogin::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
	DDX_Text(pDX, IDC_USERNAME, m_username);
	DDV_MaxChars(pDX, m_username, 255);
	DDX_Text(pDX, IDC_PWD, m_psw);
	DDV_MaxChars(pDX, m_psw, 255);
}


BEGIN_MESSAGE_MAP(CLogin, CDialogEx)
	ON_BN_CLICKED(IDOK, &CLogin::OnBnClickedLogin)
END_MESSAGE_MAP()


// CLogin 消息处理程序


void CLogin::OnBnClickedLogin()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(TRUE);

	bool flag=false;
	//判断是否为空
	if(m_username.IsEmpty()||m_psw.IsEmpty())
	{
		MessageBox(_T("用户名或者密码不能为空！"),_T("登陆"),MB_ICONWARNING);
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
			EndDialog(0);

			//弹出控制界面
			CWordNetDlg dlg;
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
		MessageBox(_T("用户名或者密码不正确！"),_T("登陆"),MB_ICONERROR);
	}
}


BOOL CLogin::PreTranslateMessage(MSG* pMsg)
{
	// TODO: 在此添加专用代码和/或调用基类
	//设置回车键为登陆快捷键
	if(VK_RETURN == pMsg->wParam)
	{
		OnBnClickedLogin();

		return TRUE;	
	}

	return CDialogEx::PreTranslateMessage(pMsg);
}
