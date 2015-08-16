// Serial.cpp : 实现文件
//

#include "stdafx.h"
#include "Temperature.h"
#include "Serial.h"
#include "TemperatureDlg.h"

// CSerial 对话框

IMPLEMENT_DYNAMIC(CSerial, CDialog)

CSerial::CSerial(CWnd* pParent /*=NULL*/)
	: CDialog(CSerial::IDD, pParent)
	, m_revval(_T(""))
	, m_sendval(_T(""))
	, m_portval(_T(""))
	, m_baudval(_T(""))
	, m_dataval(_T(""))
	, m_stopval(_T(""))
	, isOpen(false)
{

}

CSerial::~CSerial()
{
}
BOOL CSerial::OnInitDialog()
{
	CDialog::OnInitDialog();
	//
	m_comStop.InsertString(0,_T("1"));
	m_comStop.InsertString(1,_T("2"));
	m_comStop.SetCurSel(0);
	//	
	m_comPort.InsertString(0,_T("COM1"));
	m_comPort.InsertString(1,_T("COM2"));
	m_comPort.InsertString(2,_T("COM3"));
	m_comPort.InsertString(3,_T("COM4"));
	m_comPort.InsertString(4,_T("COM5"));
	m_comPort.InsertString(5,_T("COM6"));
	m_comPort.InsertString(6,_T("COM7"));
	m_comPort.InsertString(7,_T("COM8"));
	m_comPort.InsertString(8,_T("COM9"));
	m_comPort.InsertString(9,_T("COM10"));
	m_comPort.SetCurSel(0);
	//
	m_comData.InsertString(0,_T("5"));
	m_comData.InsertString(1,_T("6"));
	m_comData.InsertString(2,_T("7"));
	m_comData.InsertString(3,_T("8"));
	m_comData.SetCurSel(3);
	//
	m_comCheck.InsertString(0,_T("None"));
	m_comCheck.InsertString(1,_T("Odd"));
	m_comCheck.InsertString(2,_T("Even"));
	m_comCheck.SetCurSel(0);
	//
	m_comBaud.InsertString(0,_T("4800"));
	m_comBaud.InsertString(1,_T("9600"));
	m_comBaud.InsertString(2,_T("19200"));
	m_comBaud.InsertString(3,_T("56000"));
	m_comBaud.InsertString(4,_T("57600"));
	m_comBaud.InsertString(5,_T("115200"));
	m_comBaud.SetCurSel(1);
	//
	isOpen=FALSE;
	//
	//m_mscomm.put__CommPort(8);
	m_mscomm.put_InputMode(1);
	m_mscomm.put_InBufferSize(1024);
	//
	m_mscomm.put_OutBufferSize(512);
	m_mscomm.put_Settings(_T("9600,n,8,1"));
	//if(m_ctrlComm.get_PortOpen())
	//	m_ctrlComm.put_PortOpen(TRUE);
	//
	m_mscomm.put_RThreshold(1);
	m_mscomm.put_InputLen(0);
	m_mscomm.get_Input();

	return TRUE;
}
void CSerial::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
	DDX_Control(pDX, IDC_COMPORT, m_comPort);
	DDX_Control(pDX, IDC_COMBAUD, m_comBaud);
	DDX_Control(pDX, IDC_COMDATA, m_comData);
	DDX_Control(pDX, IDC_COMCHECK, m_comCheck);
	DDX_Control(pDX, IDC_COMSTOP, m_comStop);
	DDX_Control(pDX, IDC_BUTOPEN, m_butOpen);
	DDX_Text(pDX, IDC_EDITREV, m_revval);
	DDX_Text(pDX, IDC_EDITSEND, m_sendval);
	DDX_CBString(pDX, IDC_COMPORT, m_portval);
	DDX_CBString(pDX, IDC_COMBAUD, m_baudval);
	DDX_CBString(pDX, IDC_COMDATA, m_dataval);
	DDX_CBString(pDX, IDC_COMSTOP, m_stopval);
	DDX_Control(pDX, IDC_MSCOMM, m_mscomm);
}


BEGIN_MESSAGE_MAP(CSerial, CDialog)
	ON_BN_CLICKED(IDC_BUTOPEN, &CSerial::OnBnClickedButopen)
	ON_BN_CLICKED(IDC_BUTSEND, &CSerial::OnBnClickedButsend)
END_MESSAGE_MAP()


// CSerial 消息处理程序

void CSerial::OnBnClickedButopen()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(TRUE);
	CString str;
	str.Format(_T("%s,n,%s,%s"),m_baudval,m_dataval,m_stopval);//m_comPort.GetCurSel()
	//AfxMessageBox(str);
	isOpen=!isOpen;
	if(isOpen)
	{
		m_butOpen.SetWindowText(_T("关闭串口"));
		if(!m_mscomm.get_PortOpen())
		{
			m_mscomm.put_CommPort((short)m_comPort.GetCurSel()+1);
			m_mscomm.put_Settings(str);
			m_mscomm.put_PortOpen(TRUE);
		}
	}
	else
	{
		m_butOpen.SetWindowText(_T("打开串口"));
		if(m_mscomm.get_PortOpen())
			m_mscomm.put_PortOpen(FALSE);
	}

}

void CSerial::OnBnClickedButsend()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(TRUE);
	//AfxMessageBox(m_sendval);
	CString str;
	str.Format(_T("%s\n"),m_sendval);
	if(this->isOpen)
		m_mscomm.put_Output(COleVariant(str));//SEND DATA

}
BEGIN_EVENTSINK_MAP(CSerial, CDialog)
	ON_EVENT(CSerial, IDC_MSCOMM, 1, CSerial::OnCommMscomm, VTS_NONE)
END_EVENTSINK_MAP()

void CSerial::OnCommMscomm()
{
	// TODO: 在此处添加消息处理程序代码
	VARIANT varitant_inp;
	COleSafeArray sagearray_inp;
	LONG len,k;
	BYTE rxdata[1024];
	CString strtemp;
	//m_revval=_T("");
	if(m_mscomm.get_CommEvent()==2)
	{
		varitant_inp=m_mscomm.get_Input();
		sagearray_inp=varitant_inp;
		len=sagearray_inp.GetOneDimSize();
		for(k=0;k<len;k++)
		{
			sagearray_inp.GetElement(&k,rxdata+k);
		}
		//
		for(k=0;k<len;k++)
		{
			BYTE bt=*(char*)(rxdata+k);
			strtemp.Format(_T("%c"),bt);
			if(strtemp==",")
			{
				//定义了一个CShow变量，通过变量调用显示函数
				m_showData->SetTemperature((float)_tstof(m_revval));
				UpdateData(FALSE);
				m_revval=_T("");
			}
			else
				m_revval+=strtemp;
		}
		//	
	}
	//m_revval += ",";
	//if(len>0)
		//m_listRev.AddString(m_listval);	
}

void CSerial::SendAlarm(CString str)
{
	//CString str;
	str.Format(_T("%s\n"),str);
	if(this->isOpen)
		m_mscomm.put_Output(COleVariant(str));//SEND DATA
}