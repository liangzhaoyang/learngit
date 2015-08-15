#pragma once
#include "ModifyWordNet.h"
#include "MD5.h"
#include "ADOConn.h"

// CWordNetDlg 对话框

class CWordNetDlg : public CDialogEx
{
	DECLARE_DYNAMIC(CWordNetDlg)

public:
	CWordNetDlg(CWnd* pParent = NULL);   // 标准构造函数
	virtual ~CWordNetDlg();
	void ProducePwdFile();//生成密文函数
// 对话框数据
	enum { IDD = IDD_WORDNET };
private:
	MD5 md5;
protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

	DECLARE_MESSAGE_MAP()
public:
	CString m_username;
	CString m_psw;
	afx_msg void OnBnClickedOk();
};
