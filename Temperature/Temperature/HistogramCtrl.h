#pragma once

#include "AfxTempl.h"
// CHistogramCtrl

class CHistogramCtrl : public CWnd
{
	DECLARE_DYNAMIC(CHistogramCtrl)

public:
	CHistogramCtrl();
	virtual ~CHistogramCtrl();
public:
	typedef enum {LOW_SPEED = 5000, NORMAL_SPEED = 1000, HIGH_SPEED = 500, IDLE = 0} SPEED;

public:
	BOOL Create(DWORD dwStyle, const RECT &rect, CWnd *pParentWnd, UINT uID);
	void SetRange(UINT uLower, UINT uUpper);
	//
	public:
	BOOL SetGridsColor(COLORREF cr);
	BOOL SetBkColor(COLORREF cr);
	void SetPen(int nWidth, COLORREF crColor);
	CHistogramCtrl::SPEED SetSpeed(CHistogramCtrl::SPEED uSpeed);
	void SetPos(UINT uPos);

protected:

	DECLARE_MESSAGE_MAP()

private:
	enum SPEED m_uSpeed;
	UINT m_yPos;
	UINT m_yPreviousPos;
	UINT m_uOffset;

	void DrawLine();
	BOOL InvalidateCtrl();
	UINT GetAverage();

	CDC *m_pMemDC;

	UINT m_uLower;
	UINT m_uUpper;

	CRect m_rcClient;
	CPen m_colorPen;

	int m_nFirstLinePos;

	COLORREF m_crBackGround;
	COLORREF m_crGrids;

	CList<UINT, UINT&> *m_pList;
public:
	afx_msg void OnTimer(UINT_PTR nIDEvent);
	afx_msg void OnPaint();
};


