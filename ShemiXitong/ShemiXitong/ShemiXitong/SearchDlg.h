#pragma once
#include "afxcmn.h"
#include <string>
#include <regex>
using namespace std;

// CSearchDlg 对话框

class CSearchDlg : public CDialogEx
{
	DECLARE_DYNAMIC(CSearchDlg)

public:
	CSearchDlg(CWnd* pParent = NULL);   // 标准构造函数
	virtual ~CSearchDlg();
	void SetSearchValue(CString value);//设置搜索值
	CString GetSearchValue();//获取搜索值
	void TraversFile(CString csPath);//遍历文件
	void SearchFile();//开始搜索
	bool GetEndSearchFlag();//获取结束搜索标志
	void SetEndSearchFlag(bool flag);
// 对话框数据
	enum { IDD = IDD_SEARCH };
private:
	CString findStr;
	int count;
	bool stopFlag;//停止搜索
	bool searchEndFlag;
protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

	DECLARE_MESSAGE_MAP()
public:
	afx_msg void OnBnClickedOk();
	CProgressCtrl m_progress;
	virtual BOOL OnInitDialog();
	afx_msg void OnTimer(UINT_PTR nIDEvent);
	afx_msg LRESULT OnMySearchMessage(WPARAM wParam, LPARAM lParam);
	afx_msg LRESULT OnEndSearchMessage(WPARAM wParam, LPARAM lParam);
};
