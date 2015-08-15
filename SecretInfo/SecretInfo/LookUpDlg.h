#pragma once
#include "afxwin.h"
#include "afxdtctl.h"
#include "afxcmn.h"
#include "ADOConn.h"

//excel
#include "CApplication0.h"
#include "CRange0.h"
#include "CWorkbook.h"
#include "CWorkbooks.h"
#include "CWorksheet.h"
#include "CWorksheets.h"

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
	void Write2Excel();//导出涉密信息到Excel表
	void ExportDetectInfo(CString filename);
	CString GetFileName(CString path);
// 对话框数据
	enum { IDD = IDD_LOOKUP };

protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

	DECLARE_MESSAGE_MAP()
public:
	CComboBox m_secretLevel;
	CDateTimeCtrl m_dateStart;
	CDateTimeCtrl m_dateStop;
	CListCtrl m_queryResult;
	virtual BOOL OnInitDialog();
	afx_msg void OnBnClickedOk();
	afx_msg void OnNMRClickList1(NMHDR *pNMHDR, LRESULT *pResult);
	afx_msg void OnMenuItemDelete();
	afx_msg void OnMenuItemEmpty();
	afx_msg void OnMenuItemExport();
};
