
// SecretInfoView.h : CSecretInfoView 类的接口
//

#pragma once


class CSecretInfoView : public CView
{
protected: // 仅从序列化创建
	CSecretInfoView();
	DECLARE_DYNCREATE(CSecretInfoView)

// 特性
public:
	CSecretInfoDoc* GetDocument() const;

// 操作
public:
	CBrush m_brushBackground;
// 重写
public:
	virtual void OnDraw(CDC* pDC);  // 重写以绘制该视图
	virtual BOOL PreCreateWindow(CREATESTRUCT& cs);
protected:
	virtual BOOL OnPreparePrinting(CPrintInfo* pInfo);
	virtual void OnBeginPrinting(CDC* pDC, CPrintInfo* pInfo);
	virtual void OnEndPrinting(CDC* pDC, CPrintInfo* pInfo);

// 实现
public:
	virtual ~CSecretInfoView();
#ifdef _DEBUG
	virtual void AssertValid() const;
	virtual void Dump(CDumpContext& dc) const;
#endif

protected:

// 生成的消息映射函数
protected:
	afx_msg void OnFilePrintPreview();
	afx_msg void OnRButtonUp(UINT nFlags, CPoint point);
	afx_msg void OnContextMenu(CWnd* pWnd, CPoint point);
	DECLARE_MESSAGE_MAP()
public:
	afx_msg BOOL OnEraseBkgnd(CDC* pDC);
};

#ifndef _DEBUG  // SecretInfoView.cpp 中的调试版本
inline CSecretInfoDoc* CSecretInfoView::GetDocument() const
   { return reinterpret_cast<CSecretInfoDoc*>(m_pDocument); }
#endif

