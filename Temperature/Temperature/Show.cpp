// Show.cpp : 实现文件
//

#include "stdafx.h"
#include "Temperature.h"
#include "Show.h"
#include "Serial.h"
#include<stdlib.h>
#include <time.h>
// CShow 对话框

IMPLEMENT_DYNAMIC(CShow, CDialog)

CShow::CShow(CWnd* pParent /*=NULL*/)
	: CDialog(CShow::IDD, pParent)
	, m_tempval(_T(""))
	, m_tempAlarm(_T(""))
{

}

CShow::~CShow()
{
}

void CShow::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
	DDX_Text(pDX, IDC_EDITTMPERATRUE, m_tempval);
	DDX_Text(pDX, IDC_EDITALARM, m_tempAlarm);
}


BEGIN_MESSAGE_MAP(CShow, CDialog)
	ON_EN_CHANGE(IDC_EDITTMPERATRUE, &CShow::OnEnChangeEdittmperatrue)
	ON_EN_CHANGE(IDC_EDITALARM, &CShow::OnEnChangeEditalarm)
	ON_WM_TIMER()
	ON_WM_PAINT()
	ON_EN_KILLFOCUS(IDC_EDITALARM, &CShow::OnEnKillfocusEditalarm)
	ON_EN_UPDATE(IDC_EDITALARM, &CShow::OnEnUpdateEditalarm)
END_MESSAGE_MAP()


// CShow 消息处理程序
BOOL CShow::OnInitDialog()
{
	CDialog::OnInitDialog();

	CRect rect;
	GetDlgItem(IDC_STATICSHOW)->GetWindowRect(rect);
	ScreenToClient(rect);
	m_ctrlHistogram.Create(WS_VISIBLE | WS_CHILD | WS_TABSTOP, rect, this, 1000);
	m_ctrlHistogram.SetRange(0, 100);
	m_ctrlHistogram.SetSpeed(CHistogramCtrl::NORMAL_SPEED);
	//m_ctrlHistogram.SetGridsColor(RGB(0, 0, 0));
	//m_ctrlHistogram.SetBkColor(RGB(255, 255, 255));
	//m_ctrlHistogram.SetPen(2, RGB(255, 0, 0));

	//设置定时器，产生随机数
	//srand(time(0)); /*设置种子,并生成伪随机序列*/
	//
	m_tempAlarm.Format(_T("%d"),50);
	//SetTimer(0, 3000, NULL);
	return TRUE;
}
void CShow::OnEnChangeEdittmperatrue()
{
	// TODO:  如果该控件是 RICHEDIT 控件，则它将不会
	// 发送该通知，除非重写 CDialog::OnInitDialog()
	// 函数并调用 CRichEditCtrl().SetEventMask()，
	// 同时将 ENM_CHANGE 标志“或”运算到掩码中。

	// TODO:  在此添加控件通知处理程序代码
}

void CShow::OnEnChangeEditalarm()
{
	// TODO:  如果该控件是 RICHEDIT 控件，则它将不会
	// 发送该通知，除非重写 CDialog::OnInitDialog()
	// 函数并调用 CRichEditCtrl().SetEventMask()，
	// 同时将 ENM_CHANGE 标志“或”运算到掩码中。

	// TODO:  在此添加控件通知处理程序代码
	
}

void CShow::OnTimer(UINT_PTR nIDEvent)
{
	// TODO: 在此添加消息处理程序代码和/或调用默认值
	//int nRandom = rand()%100;
	//m_ctrlHistogram.SetPos((int)nRandom);
	
	CDialog::OnTimer(nIDEvent);
}

void CShow::OnPaint()
{
	CPaintDC dc(this); // device context for painting
	// TODO: 在此处添加消息处理程序代码
	// 不为绘图消息调用 CDialog::OnPaint()
}

void CShow::SetTemperature(float temperature)
{
	m_ctrlHistogram.SetPos((int)temperature);
	//nRandom=(int)temperature;
	m_tempval.Format(_T("%.1f %cC"),temperature,0xB0);
	/*CString temp=_T("");
    if(temp!=m_tempval)
	{
     temp=m_tempval;
	 MessageBox(temp);
	}
	*/
	UpdateData(FALSE);
}

void CShow::OnEnKillfocusEditalarm()
{
	// TODO: 在此添加控件通知处理程序代码
	/*
	UpdateData(TRUE);
	if(m_serialAlarm->isOpen)
		m_serialAlarm->SendAlarm(m_tempAlarm);
	*/
}

void CShow::OnEnUpdateEditalarm()
{
	// TODO:  如果该控件是 RICHEDIT 控件，则它将不会
	// 发送该通知，除非重写 CDialog::OnInitDialog()
	// 函数，将 EM_SETEVENTMASK 消息发送到控件，
	// 同时将 ENM_UPDATE 标志“或”运算到 lParam 掩码中。

	// TODO:  在此添加控件通知处理程序代码
	/*
	UpdateData(TRUE);
	if(m_serialAlarm->isOpen)
		m_serialAlarm->SendAlarm(m_tempAlarm);
	*/
}
