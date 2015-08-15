
// SecretInfoView.cpp : CSecretInfoView 类的实现
//

#include "stdafx.h"
// SHARED_HANDLERS 可以在实现预览、缩略图和搜索筛选器句柄的
// ATL 项目中进行定义，并允许与该项目共享文档代码。
#ifndef SHARED_HANDLERS
#include "SecretInfo.h"
#endif

#include "SecretInfoDoc.h"
#include "SecretInfoView.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#endif


// CSecretInfoView

IMPLEMENT_DYNCREATE(CSecretInfoView, CView)

BEGIN_MESSAGE_MAP(CSecretInfoView, CView)
	// 标准打印命令
	ON_COMMAND(ID_FILE_PRINT, &CView::OnFilePrint)
	ON_COMMAND(ID_FILE_PRINT_DIRECT, &CView::OnFilePrint)
	ON_COMMAND(ID_FILE_PRINT_PREVIEW, &CSecretInfoView::OnFilePrintPreview)
	ON_WM_CONTEXTMENU()
	ON_WM_RBUTTONUP()
	ON_WM_ERASEBKGND()
END_MESSAGE_MAP()

// CSecretInfoView 构造/析构

CSecretInfoView::CSecretInfoView()
{
	// TODO: 在此处添加构造代码
	CBitmap bmp;
	bmp.LoadBitmap(IDB_BITMAP); ///加载位图
   m_brushBackground.CreatePatternBrush(&bmp); ///创建位图画刷
}

CSecretInfoView::~CSecretInfoView()
{
}

BOOL CSecretInfoView::PreCreateWindow(CREATESTRUCT& cs)
{
	// TODO: 在此处通过修改
	//  CREATESTRUCT cs 来修改窗口类或样式

	return CView::PreCreateWindow(cs);
}

// CSecretInfoView 绘制

void CSecretInfoView::OnDraw(CDC* pDC)
{
	CSecretInfoDoc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);
	if (!pDoc)
		return;

	// TODO: 在此处为本机数据添加绘制代码
	/*CRect rect;
	GetClientRect(rect);///取得客户区域
   pDC->FillRect(rect,&m_brushBackground); ///用背景画刷填充区域
   */
	 CRect rctClient;
	 GetClientRect(rctClient);
	 CDC dcMem;
	 dcMem.CreateCompatibleDC(pDC);
	 CBitmap m_bmp;
	 m_bmp.LoadBitmap(IDB_BACKGROUND);
	 BITMAP bitmap;
	 m_bmp.GetBitmap(&bitmap);
	 CBitmap   *pbmpOld=dcMem.SelectObject(&m_bmp); 
	 pDC->StretchBlt(0,0,rctClient.Width(),rctClient.Height(),&dcMem,0,0, 
	  bitmap.bmWidth,bitmap.bmHeight,SRCCOPY);
}


// CSecretInfoView 打印


void CSecretInfoView::OnFilePrintPreview()
{
#ifndef SHARED_HANDLERS
	AFXPrintPreview(this);
#endif
}

BOOL CSecretInfoView::OnPreparePrinting(CPrintInfo* pInfo)
{
	// 默认准备
	return DoPreparePrinting(pInfo);
}

void CSecretInfoView::OnBeginPrinting(CDC* /*pDC*/, CPrintInfo* /*pInfo*/)
{
	// TODO: 添加额外的打印前进行的初始化过程
}

void CSecretInfoView::OnEndPrinting(CDC* /*pDC*/, CPrintInfo* /*pInfo*/)
{
	// TODO: 添加打印后进行的清理过程
}

void CSecretInfoView::OnRButtonUp(UINT /* nFlags */, CPoint point)
{
	ClientToScreen(&point);
	OnContextMenu(this, point);
}

void CSecretInfoView::OnContextMenu(CWnd* /* pWnd */, CPoint point)
{
#ifndef SHARED_HANDLERS
	theApp.GetContextMenuManager()->ShowPopupMenu(IDR_POPUP_EDIT, point.x, point.y, this, TRUE);
#endif
}


// CSecretInfoView 诊断

#ifdef _DEBUG
void CSecretInfoView::AssertValid() const
{
	CView::AssertValid();
}

void CSecretInfoView::Dump(CDumpContext& dc) const
{
	CView::Dump(dc);
}

CSecretInfoDoc* CSecretInfoView::GetDocument() const // 非调试版本是内联的
{
	ASSERT(m_pDocument->IsKindOf(RUNTIME_CLASS(CSecretInfoDoc)));
	return (CSecretInfoDoc*)m_pDocument;
}
#endif //_DEBUG


// CSecretInfoView 消息处理程序


BOOL CSecretInfoView::OnEraseBkgnd(CDC* pDC)
{
	// TODO: 在此添加消息处理程序代码和/或调用默认值
	return TRUE;
	//return CView::OnEraseBkgnd(pDC);
}
