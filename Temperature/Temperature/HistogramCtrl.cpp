// HistogramCtrl.cpp : 实现文件
//

#include "stdafx.h"
#include "Temperature.h"
#include "HistogramCtrl.h"


// CHistogramCtrl

IMPLEMENT_DYNAMIC(CHistogramCtrl, CWnd)

CHistogramCtrl::CHistogramCtrl()
{
	m_nFirstLinePos = 13;

	m_pMemDC = NULL;
	m_pList = NULL;
	m_uSpeed = IDLE;

	m_crBackGround = RGB(0, 0, 0);
	m_crGrids = RGB(0, 130, 66);

	SetPen(1, RGB(0, 255, 0));
	SetRange(1, 100);
}

CHistogramCtrl::~CHistogramCtrl()
{
	if(m_pMemDC)
		delete m_pMemDC;

	if(m_pList)
		delete m_pList;
}


BEGIN_MESSAGE_MAP(CHistogramCtrl, CWnd)
	ON_WM_TIMER()
	ON_WM_PAINT()
END_MESSAGE_MAP()



// CHistogramCtrl 消息处理程序
BOOL CHistogramCtrl::Create(DWORD dwStyle, const RECT &rect, CWnd *pParentWnd, UINT uID)
{
	//Postcondition:
	//	Creates a window within the "rect" region of the client screen
	//	Returns TRUE if the function creates the control successfully
	//	or FALSE if it fails.

	BOOL bRet = CWnd::CreateEx(WS_EX_CLIENTEDGE, 
								AfxRegisterWndClass(CS_HREDRAW | CS_VREDRAW),
								NULL,
								dwStyle,
								rect.left,
								rect.top,
								rect.right - rect.left,
								rect.bottom - rect.top,
								pParentWnd->GetSafeHwnd(),
								(HMENU)uID);

	if(!bRet)
		return FALSE;

	m_pMemDC = new CDC;
	if(!m_pMemDC)
		return FALSE;

	m_pList = new CList<UINT, UINT&>;
	if(!m_pList)
		return FALSE;

	GetClientRect(m_rcClient);

	if(!InvalidateCtrl())
		return FALSE;

	SetSpeed(CHistogramCtrl::LOW_SPEED);
	
	return TRUE;
}

void CHistogramCtrl::SetRange(UINT uLower, UINT uUpper)
{
	//ASSERT(uLower && uUpper && uLower < uUpper);
	//Postcondition:
	//	Sets the upper and lower (limits) range
	m_uUpper = uUpper - uLower + 1;
	m_uLower =0;
	m_uOffset = uLower - 1;
}

BOOL CHistogramCtrl::InvalidateCtrl()
{
	//Postcondition:
	//	Paints the entire client area of the control
	//	Returns TRUE if it's done successfuly or FALSE if it fails

	CClientDC dc(this);

	if(m_pMemDC->GetSafeHdc())
		return FALSE;

	if(!m_pMemDC->CreateCompatibleDC(&dc))
		return FALSE;

	CBitmap bmp;
	if(!bmp.CreateCompatibleBitmap(&dc, m_rcClient.Width(), m_rcClient.Height()))
		return FALSE;

	if(!m_pMemDC->SelectObject(bmp))
		return FALSE;

	//Set the background color of the control
	CBrush bkBrush;
	if(!bkBrush.CreateSolidBrush(m_crBackGround))
		return FALSE;
	
	m_pMemDC->FillRect(m_rcClient, &bkBrush);

	//Select a specified pen to the device context to draw background lines
	CPen bkLinesPen;
	if(!bkLinesPen.CreatePen(PS_SOLID, 1, m_crGrids))
		return FALSE;

	if(!m_pMemDC->SelectObject(bkLinesPen))
		return FALSE;

	//Draw background lines
	for(int i = m_rcClient.left - 1; i < m_rcClient.right; i += 13)
	{
		m_pMemDC->MoveTo(i, m_rcClient.top);
		m_pMemDC->LineTo(i, m_rcClient.bottom);
	}
	//int temp=0;
	for(int j = m_rcClient.top - 1; j < m_rcClient.bottom; j += 13)
	{
		m_pMemDC->MoveTo(m_rcClient.left, j);
		//
		/*
		CString strGrid;
		strGrid.Format(_T("%f.1"),(float)(j-m_rcClient.top+1)/13);
		m_pMemDC->TextOutW(m_rcClient.left, j,strGrid);
		*/
		m_pMemDC->LineTo(m_rcClient.right, j);
	}

	m_yPreviousPos = m_yPos = m_rcClient.bottom + 1;

	InvalidateRect(m_rcClient);

	return TRUE;
}

void CHistogramCtrl::DrawLine()
{
	//Postcondition:
	//	Draws the histogram within the client area of the control

	if(!m_pMemDC->GetSafeHdc())
		return;

	CRect bkRect(m_rcClient.right - 3, m_rcClient.top, m_rcClient.right, m_rcClient.bottom);

	CBrush bkBrush;
	bkBrush.CreateSolidBrush(m_crBackGround);
	m_pMemDC->FillRect(bkRect, &bkBrush);

	m_pMemDC->BitBlt(0, 0, m_rcClient.Width(), m_rcClient.Height(), m_pMemDC, 3, 0, SRCCOPY);

	CPen myPen;
	myPen.CreatePen(PS_SOLID, 1, m_crGrids);
	m_pMemDC->SelectObject(myPen);

	m_nFirstLinePos -= 3;
	if(m_nFirstLinePos < 0)
		m_nFirstLinePos += 13;

	int nX = m_rcClient.right - ((m_rcClient.right - m_nFirstLinePos) % 13) - 1;
	m_pMemDC->MoveTo(nX, m_rcClient.top);
	m_pMemDC->LineTo(nX, m_rcClient.bottom);


	for(int j = m_rcClient.top - 1; j < m_rcClient.bottom; j += 13)
	{
		m_pMemDC->MoveTo(bkRect.left - 3, j);
		CString strGrid;
		strGrid.Format(_T("%.1f"),(float)(m_rcClient.bottom-j)*100/(m_rcClient.bottom-m_rcClient.top+1));
		//m_pMemDC->SetBkMode(TRANSPARENT);
		//m_pMemDC->SetTextColor(RGB(255,255,255));
		m_pMemDC->TextOutW(m_rcClient.left+7, j,strGrid);
		m_pMemDC->LineTo(bkRect.right, j);
	}

	UINT uAverage = GetAverage();
	TRACE("uAverage: %u\r\n", uAverage);
	if(uAverage)
		m_yPos = m_rcClient.bottom - (uAverage * m_rcClient.bottom / m_uUpper);

	if(m_yPreviousPos == (unsigned)m_rcClient.bottom + 1)
		m_yPreviousPos = m_yPos;
	
	m_pMemDC->SelectObject(m_colorPen);
	m_pMemDC->MoveTo(m_rcClient.right - 11 - 3, m_yPreviousPos);
	m_pMemDC->LineTo(m_rcClient.right - 11, m_yPos);
	
	m_yPreviousPos = m_yPos;

	Invalidate();
}

void CHistogramCtrl::SetPos(UINT uPos)
{
	uPos -= m_uOffset;
	//ASSERT(uPos <= m_uUpper && uPos >= m_uLower);

	//Postcondition:
	//	Adds the specified point to a list, so that we
	//	would be able to draw the histogram within the
	//	specified intervals.
	m_pList->AddHead(uPos);
}


void CHistogramCtrl::OnTimer(UINT_PTR nIDEvent)
{
	// TODO: 在此添加消息处理程序代码和/或调用默认值
	DrawLine();

	CWnd::OnTimer(nIDEvent);
}

void CHistogramCtrl::OnPaint()
{
	CPaintDC dc(this); // device context for painting
	// TODO: 在此处添加消息处理程序代码
	// 不为绘图消息调用 CWnd::OnPaint()
	if(m_pMemDC->GetSafeHdc())
		dc.BitBlt(0, 0, m_rcClient.Width(), m_rcClient.Height(), m_pMemDC, 0, 0, SRCCOPY);
}

CHistogramCtrl::SPEED CHistogramCtrl::SetSpeed(enum SPEED uSpeed)
{
	enum SPEED oldSpeed = m_uSpeed;
	m_uSpeed = uSpeed;

	KillTimer(0);

	if(uSpeed != CHistogramCtrl::IDLE)
		SetTimer(0, m_uSpeed, NULL);

	return oldSpeed;
}

void CHistogramCtrl::SetPen(int nWidth, COLORREF crColor)
{
	m_colorPen.DeleteObject();
	m_colorPen.CreatePen(PS_SOLID, nWidth, crColor);
}

BOOL CHistogramCtrl::SetBkColor(COLORREF cr)
{
	BOOL bRet = FALSE;
	enum SPEED oldSpeed = SetSpeed(CHistogramCtrl::IDLE);

	m_crBackGround = cr;

	CClientDC dc(this);

	int oldPos = m_nFirstLinePos;
	m_nFirstLinePos = 13;
	if(m_pMemDC->DeleteDC())
		if(InvalidateCtrl())
			bRet = TRUE;

	if(!bRet)
		m_nFirstLinePos = oldPos;

	SetSpeed(oldSpeed);

	return bRet;
}

BOOL CHistogramCtrl::SetGridsColor(COLORREF cr)
{
	BOOL bRet = FALSE;
	enum SPEED oldSpeed = SetSpeed(CHistogramCtrl::IDLE);

	m_crGrids = cr;

	CClientDC dc(this);

	int oldPos = m_nFirstLinePos;
	m_nFirstLinePos = 13;
	if(m_pMemDC->DeleteDC())
		if(InvalidateCtrl())
			bRet = TRUE;

	if(!bRet)
		m_nFirstLinePos = oldPos;

	SetSpeed(oldSpeed);

	return bRet;
}

UINT CHistogramCtrl::GetAverage()
{
	UINT uCounter = 0, uSum = 0;
	while(!m_pList->IsEmpty())
	{
		uSum += m_pList->RemoveTail();
		uCounter++;
	}

	return uCounter ? uSum / uCounter : 0;
}