#pragma once
#include "afxwin.h"
#include "mscomm.h"
class CShow;
/*
当两个类相互调用时，注意不要直接相互引入头文件，必须采取下列举例方式，否则会报错；
//#include "Show.h" //用下面的方式替代  
class CShow;//注意：只是告诉编译器,需要这个类,其他功能结构等都没 
*/
// CSerial 对话框

class CSerial : public CDialog
{
	DECLARE_DYNAMIC(CSerial)

public:
	CSerial(CWnd* pParent = NULL);   // 标准构造函数
	virtual ~CSerial();
	BOOL OnInitDialog();
// 对话框数据
	enum { IDD = IDD_SERIAL };

protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

	DECLARE_MESSAGE_MAP()
public:
	CComboBox m_comPort;
	CComboBox m_comBaud;
	CComboBox m_comData;
	CComboBox m_comCheck;
	CComboBox m_comStop;
	CButton m_butOpen;
	CString m_revval;
	CString m_sendval;
	CString m_portval;
	CString m_baudval;
	CString m_dataval;
	CString m_stopval;
	CMscomm m_mscomm;
	bool isOpen;
	afx_msg void OnBnClickedButopen();
	afx_msg void OnBnClickedButsend();
	DECLARE_EVENTSINK_MAP()
	void OnCommMscomm();
public:
	void SendAlarm(CString str);
	
public:
	CShow* m_showData;
};
