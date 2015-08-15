
// ShemiXitongDlg.cpp : 实现文件
//

#include "stdafx.h"
#include "ShemiXitong.h"
#include "ShemiXitongDlg.h"
#include "afxdialogex.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#endif


// 用于应用程序“关于”菜单项的 CAboutDlg 对话框

class CAboutDlg : public CDialogEx
{
public:
	CAboutDlg();

// 对话框数据
	enum { IDD = IDD_ABOUTBOX };

	protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

// 实现
protected:
	DECLARE_MESSAGE_MAP()
};

CAboutDlg::CAboutDlg() : CDialogEx(CAboutDlg::IDD)
{
}

void CAboutDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
}

BEGIN_MESSAGE_MAP(CAboutDlg, CDialogEx)
END_MESSAGE_MAP()


// CShemiXitongDlg 对话框




CShemiXitongDlg::CShemiXitongDlg(CWnd* pParent /*=NULL*/)
	: CDialogEx(CShemiXitongDlg::IDD, pParent)
{
	m_hIcon = AfxGetApp()->LoadIcon(IDR_MAINFRAME);
}

void CShemiXitongDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
	DDX_Control(pDX, IDC_TAB, m_tab);
}

BEGIN_MESSAGE_MAP(CShemiXitongDlg, CDialogEx)
	ON_WM_SYSCOMMAND()
	ON_WM_PAINT()
	ON_WM_QUERYDRAGICON()
	ON_NOTIFY(TCN_SELCHANGE, IDC_TAB, &CShemiXitongDlg::OnTcnSelchangeTab)
END_MESSAGE_MAP()


// CShemiXitongDlg 消息处理程序

BOOL CShemiXitongDlg::OnInitDialog()
{
	CDialogEx::OnInitDialog();

	// 将“关于...”菜单项添加到系统菜单中。

	// IDM_ABOUTBOX 必须在系统命令范围内。
	ASSERT((IDM_ABOUTBOX & 0xFFF0) == IDM_ABOUTBOX);
	ASSERT(IDM_ABOUTBOX < 0xF000);

	CMenu* pSysMenu = GetSystemMenu(FALSE);
	if (pSysMenu != NULL)
	{
		BOOL bNameValid;
		CString strAboutMenu;
		bNameValid = strAboutMenu.LoadString(IDS_ABOUTBOX);
		ASSERT(bNameValid);
		if (!strAboutMenu.IsEmpty())
		{
			pSysMenu->AppendMenu(MF_SEPARATOR);
			pSysMenu->AppendMenu(MF_STRING, IDM_ABOUTBOX, strAboutMenu);
		}
	}

	// 设置此对话框的图标。当应用程序主窗口不是对话框时，框架将自动
	//  执行此操作
	SetIcon(m_hIcon, TRUE);			// 设置大图标
	SetIcon(m_hIcon, FALSE);		// 设置小图标

	// TODO: 在此添加额外的初始化代码
	m_tab.InsertItem(0,_T("任务"));
	m_tab.InsertItem(1,_T("查看"));
	m_tab.InsertItem(2,_T("涉密词库"));
	m_tab.InsertItem(3,_T("帮助"));
	//关联对话框,并且将IDC_TAB控件设为父窗口
	m_taskDlg.Create(IDD_TASK,GetDlgItem(IDC_TAB));
	m_lookUpDlg.Create(IDD_LOOKUP,GetDlgItem(IDC_TAB));
	m_wordNetDlg.Create(IDD_WORDNET,GetDlgItem(IDC_TAB));
	m_helpDlg.Create(IDD_HELP,GetDlgItem(IDC_TAB));
		//获得IDC_TAB客户区大小
	 CRect rs,rc;
	 m_tab.GetClientRect(&rs);

	 //调整子对话框在父窗口中的位置
	 rs.top+=25;
	 rs.bottom+=40;
	 rs.left-=10;
	// rs.right-=2;
	 
	 //设置子对话框尺寸并移动到指定位置
	 m_lookUpDlg.MoveWindow(&rs);
	 m_taskDlg.MoveWindow(&rs);
	 m_wordNetDlg.MoveWindow(&rs);
	 m_helpDlg.MoveWindow(&rs);
	 
	 //分别设置隐藏和显示
	 m_taskDlg.ShowWindow(true);
	 m_lookUpDlg.ShowWindow(false);
	 m_wordNetDlg.ShowWindow(false);
	 m_helpDlg.ShowWindow(false);

	 //设置默认的选项卡
	 m_tab.SetCurSel(0);


	return TRUE;  // 除非将焦点设置到控件，否则返回 TRUE
}

void CShemiXitongDlg::OnSysCommand(UINT nID, LPARAM lParam)
{
	if ((nID & 0xFFF0) == IDM_ABOUTBOX)
	{
		CAboutDlg dlgAbout;
		dlgAbout.DoModal();
	}
	else
	{
		CDialogEx::OnSysCommand(nID, lParam);
	}
}

// 如果向对话框添加最小化按钮，则需要下面的代码
//  来绘制该图标。对于使用文档/视图模型的 MFC 应用程序，
//  这将由框架自动完成。

void CShemiXitongDlg::OnPaint()
{
	if (IsIconic())
	{
		CPaintDC dc(this); // 用于绘制的设备上下文

		SendMessage(WM_ICONERASEBKGND, reinterpret_cast<WPARAM>(dc.GetSafeHdc()), 0);

		// 使图标在工作区矩形中居中
		int cxIcon = GetSystemMetrics(SM_CXICON);
		int cyIcon = GetSystemMetrics(SM_CYICON);
		CRect rect;
		GetClientRect(&rect);
		int x = (rect.Width() - cxIcon + 1) / 2;
		int y = (rect.Height() - cyIcon + 1) / 2;

		// 绘制图标
		dc.DrawIcon(x, y, m_hIcon);
	}
	else
	{
		CDialogEx::OnPaint();
	}
}

//当用户拖动最小化窗口时系统调用此函数取得光标
//显示。
HCURSOR CShemiXitongDlg::OnQueryDragIcon()
{
	return static_cast<HCURSOR>(m_hIcon);
}



void CShemiXitongDlg::OnTcnSelchangeTab(NMHDR *pNMHDR, LRESULT *pResult)
{
	// TODO: 在此添加控件通知处理程序代码
	*pResult = 0;

		//获取当前选项卡索引
	 int CurSel = m_tab.GetCurSel();
	 //监听选项卡切换状态
	 switch(CurSel)
	 {
		 case 0:
			 m_taskDlg.ShowWindow(true);
			 m_lookUpDlg.ShowWindow(false);
			 m_wordNetDlg.ShowWindow(false);
			 m_helpDlg.ShowWindow(false);

			break;
		 case 1:
			 m_taskDlg.ShowWindow(false);
			 m_lookUpDlg.ShowWindow(true);
			 //更新数据显示
			 m_lookUpDlg.UpdateGridInfo();

			 m_wordNetDlg.ShowWindow(false);
			 m_helpDlg.ShowWindow(false);
			  break;
		 case 2:
			  m_taskDlg.ShowWindow(false);
			 m_lookUpDlg.ShowWindow(false);
			 //更新数据显示
			 m_wordNetDlg.ShowWindow(true);
			 m_helpDlg.ShowWindow(false);
			 break;
		 case 3:
			  m_taskDlg.ShowWindow(false);
			 m_lookUpDlg.ShowWindow(false);
			 m_wordNetDlg.ShowWindow(false);
			 m_helpDlg.ShowWindow(true);
			  break;
		 default:    
					;
		}  
}
