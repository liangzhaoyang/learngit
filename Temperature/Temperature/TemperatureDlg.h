// TemperatureDlg.h : 头文件
//

#pragma once
#include "mscomm.h"
#include "afxcmn.h"
#include "Serial.h"
#include "Show.h"

// CTemperatureDlg 对话框
class CTemperatureDlg : public CDialog
{
// 构造
public:
	CTemperatureDlg(CWnd* pParent = NULL);	// 标准构造函数

// 对话框数据
	enum { IDD = IDD_TEMPERATURE_DIALOG };

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
	//CMscomm m_mscomm;
	CTabCtrl m_tab;
	CShow m_show;
	CSerial m_serial;
	afx_msg void OnTcnSelchangeTab(NMHDR *pNMHDR, LRESULT *pResult);
	//CShow GetShowDlg();
};
