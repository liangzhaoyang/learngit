#pragma once
#include "afxcmn.h"
#include "ADOConn.h"
#include "EditDInfoDlg.h"
// CWordNetDlg 对话框

class CWordNetDlg : public CDialogEx
{
	DECLARE_DYNAMIC(CWordNetDlg)

public:
	CWordNetDlg(CWnd* pParent = NULL);   // 标准构造函数
	virtual ~CWordNetDlg();
	void AddToGrid();//将数据绑定到ListCtrl
// 对话框数据
	enum { IDD = IDD_WORDNET };

protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

	DECLARE_MESSAGE_MAP()
public:
	afx_msg void OnBnClickedAdd();
	afx_msg void OnBnClickedUpdate();
	afx_msg void OnBnClickedDelete();
	CListCtrl m_grid;
	virtual BOOL OnInitDialog();
	virtual BOOL DestroyWindow();
};
