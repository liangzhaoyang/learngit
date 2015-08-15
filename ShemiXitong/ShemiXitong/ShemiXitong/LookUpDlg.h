#pragma once
#include "afxwin.h"
#include "afxcmn.h"
#include "ADOConn.h"
#include "afxdtctl.h"

// CLookUpDlg 对话框

class CLookUpDlg : public CDialogEx
{
	DECLARE_DYNAMIC(CLookUpDlg)

public:
	CLookUpDlg(CWnd* pParent = NULL);   // 标准构造函数
	virtual ~CLookUpDlg();
	void AddToGrid(CString sql);
	void UpdateGridInfo();
	void UpdateDectectInfo(CString filename);
// 对话框数据
	enum { IDD = IDD_LOOKUP };

protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

	DECLARE_MESSAGE_MAP()
public:
	CComboBox m_secretLevel;
	CListCtrl m_queryResult;
	virtual BOOL OnInitDialog();
	CDateTimeCtrl m_dateStart;
	CDateTimeCtrl m_dateStop;
	afx_msg void OnBnClickedQuery();
	afx_msg void OnNMRClickList(NMHDR *pNMHDR, LRESULT *pResult);
	afx_msg void OnRButtonDown(UINT nFlags, CPoint point);
	afx_msg void OnMenuItemDelete();
	//afx_msg void OnMenuItemTest();
	virtual BOOL OnCommand(WPARAM wParam, LPARAM lParam);
	virtual BOOL PreTranslateMessage(MSG* pMsg);
};
