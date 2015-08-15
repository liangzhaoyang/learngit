#pragma once


// CEditDInfoDlg 对话框

class CEditDInfoDlg : public CDialogEx
{
	DECLARE_DYNAMIC(CEditDInfoDlg)

public:
	CEditDInfoDlg(CWnd* pParent = NULL);   // 标准构造函数
	virtual ~CEditDInfoDlg();
	CString GetSenseWord();
// 对话框数据
	enum { IDD = IDD_EDITINFO };

protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

	DECLARE_MESSAGE_MAP()
public:
	CString m_senseWord;
	afx_msg void OnBnClickedOk();
	afx_msg void OnBnClickedCancel();
};
