
// ShemiXitongDlg.h : 头文件
//

#pragma once
#include "afxcmn.h"
#include "HelpDlg.h"
#include "LookUpDlg.h"
#include "TaskDlg.h"
#include "WordNetDlg.h"

// CShemiXitongDlg 对话框
class CShemiXitongDlg : public CDialogEx
{
// 构造
public:
	CShemiXitongDlg(CWnd* pParent = NULL);	// 标准构造函数

// 对话框数据
	enum { IDD = IDD_SHEMIXITONG_DIALOG };

	protected:
	virtual void DoDataExchange(CDataExchange* pDX);	// DDX/DDV 支持


// 实现
protected:
	HICON m_hIcon;

	// 生成的消息映射函数
	virtual BOOL OnInitDialog();
	afx_msg void OnSysCommand(UINT nID, LPARAM lParam);
	afx_msg void OnPaint();
	afx_msg HCURSOR OnQueryDragIcon();
	DECLARE_MESSAGE_MAP()
public:
	CTabCtrl m_tab;
	CHelpDlg m_helpDlg;
	CLookUpDlg m_lookUpDlg;
	CTaskDlg  m_taskDlg;
	CWordNetDlg  m_wordNetDlg;
	afx_msg void OnTcnSelchangeTab(NMHDR *pNMHDR, LRESULT *pResult);
};
