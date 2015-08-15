// EditDInfoDlg.cpp : 实现文件
//

#include "stdafx.h"
#include "SecretInfo.h"
#include "EditDInfoDlg.h"
#include "afxdialogex.h"


// CEditDInfoDlg 对话框

IMPLEMENT_DYNAMIC(CEditDInfoDlg, CDialogEx)

CEditDInfoDlg::CEditDInfoDlg(CWnd* pParent /*=NULL*/)
	: CDialogEx(CEditDInfoDlg::IDD, pParent)
	, m_senseWord(_T(""))
{

}

CEditDInfoDlg::~CEditDInfoDlg()
{
}

void CEditDInfoDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
	DDX_Text(pDX, IDC_SENSEWORD, m_senseWord);
	DDV_MaxChars(pDX, m_senseWord, 255);
}


BEGIN_MESSAGE_MAP(CEditDInfoDlg, CDialogEx)
	ON_BN_CLICKED(IDOK, &CEditDInfoDlg::OnBnClickedOk)
	ON_BN_CLICKED(IDCANCEL, &CEditDInfoDlg::OnBnClickedCancel)
END_MESSAGE_MAP()


// CEditDInfoDlg 消息处理程序


void CEditDInfoDlg::OnBnClickedOk()
{
	// TODO: 在此添加控件通知处理程序代码
	CDialogEx::OnOK();
}


void CEditDInfoDlg::OnBnClickedCancel()
{
	// TODO: 在此添加控件通知处理程序代码
	CDialogEx::OnCancel();
}
//获取涉密词
CString CEditDInfoDlg::GetSenseWord()
{
	//UpdateData(TRUE);
	return m_senseWord;
}