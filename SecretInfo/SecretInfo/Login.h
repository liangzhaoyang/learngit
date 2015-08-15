#pragma once

#include "MD5.h"
#include "ADOConn.h"
#include "WordNetDlg.h"
// CLogin 对话框

class CLogin : public CDialogEx
{
	DECLARE_DYNAMIC(CLogin)

public:
	CLogin(CWnd* pParent = NULL);   // 标准构造函数
	virtual ~CLogin();

// 对话框数据
	enum { IDD = IDD_LOGIN };
private:
	MD5 md5;
protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

	DECLARE_MESSAGE_MAP()
public:
	CString m_username;
	CString m_psw;
	afx_msg void OnBnClickedLogin();
	virtual BOOL PreTranslateMessage(MSG* pMsg);
};
