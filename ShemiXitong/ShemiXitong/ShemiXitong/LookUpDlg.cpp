// LookUpDlg.cpp : 实现文件
//

#include "stdafx.h"
#include "ShemiXitong.h"
#include "LookUpDlg.h"
#include "afxdialogex.h"


// CLookUpDlg 对话框

IMPLEMENT_DYNAMIC(CLookUpDlg, CDialogEx)

CLookUpDlg::CLookUpDlg(CWnd* pParent /*=NULL*/)
	: CDialogEx(CLookUpDlg::IDD, pParent)
{

}

CLookUpDlg::~CLookUpDlg()
{
}

void CLookUpDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
	DDX_Control(pDX, IDC_COMBO, m_secretLevel);
	DDX_Control(pDX, IDC_LIST, m_queryResult);
	DDX_Control(pDX, IDC_DATETIMEPICKER1, m_dateStart);
	DDX_Control(pDX, IDC_DATETIMEPICKER2, m_dateStop);
}


BEGIN_MESSAGE_MAP(CLookUpDlg, CDialogEx)
	ON_BN_CLICKED(IDC_QUERY, &CLookUpDlg::OnBnClickedQuery)
	ON_NOTIFY(NM_RCLICK, IDC_LIST, &CLookUpDlg::OnNMRClickList)
	ON_WM_RBUTTONDOWN()
	ON_COMMAND(ID_32771, &CLookUpDlg::OnMenuItemDelete)
	//ON_COMMAND(ID_MENUITEM, &CLookUpDlg::OnMenuItemTest)
END_MESSAGE_MAP()


// CLookUpDlg 消息处理程序


BOOL CLookUpDlg::OnInitDialog()
{
	CDialogEx::OnInitDialog();

	// TODO:  在此添加额外的初始化
	//为串口波特率Combox赋值
	m_secretLevel.InsertString(0,_T("Level-0"));
	m_secretLevel.InsertString(1,_T("Level-1"));
	m_secretLevel.InsertString(2,_T("Level-2"));
	m_secretLevel.InsertString(3,_T("Level-3"));
	m_secretLevel.InsertString(4,_T("Level-4"));
	m_secretLevel.InsertString(5,_T("Level-5"));
	m_secretLevel.InsertString(6,_T("ALL"));
	m_secretLevel.SetCurSel(6);
	//
	m_queryResult.SetExtendedStyle(LVS_EX_FLATSB|LVS_EX_FULLROWSELECT|LVS_EX_HEADERDRAGDROP|LVS_EX_ONECLICKACTIVATE|LVS_EX_GRIDLINES);
	m_queryResult.InsertColumn(0,_T("序号"),LVCFMT_LEFT,50,0);
	m_queryResult.InsertColumn(1,_T("文件名"),LVCFMT_LEFT,100,0);
	m_queryResult.InsertColumn(2,_T("涉密等级"),LVCFMT_LEFT,100,0);
	m_queryResult.InsertColumn(3,_T("检测日期"),LVCFMT_LEFT,100,0);
	//	
	UpdateGridInfo();

	return TRUE;  // return TRUE unless you set the focus to a control
	// 异常: OCX 属性页应返回 FALSE
}

void CLookUpDlg::AddToGrid(CString sql)
{
	//连接数据库
	ADOConn m_AdoConn;
	m_AdoConn.OnInitADOConn();
	
	_RecordsetPtr  m_pRecordset;
	m_pRecordset=m_AdoConn.GetRecordSet((_bstr_t)sql);
	//删除ListContrl控件中的数据
	m_queryResult.DeleteAllItems();

	//重新绑定listcontrol控件中的数据
	int count=1;
	CString serialNum,strLevel;
	while(m_AdoConn.m_pRecordset->adoEOF==0)
	{
		serialNum.Format(_T("%d"),count++);
		int level=atoi((char*)(_bstr_t)m_pRecordset->GetCollect(_T("f_level")));
		strLevel.Format(_T("Level-%d"),level);
		//更新信息
		m_queryResult.InsertItem(0,_T(""));
		m_queryResult.SetItemText(0,0,serialNum);
		m_queryResult.SetItemText(0,1,(LPCTSTR)(_bstr_t)m_pRecordset->GetCollect(_T("f_filename")));
		m_queryResult.SetItemText(0,2,strLevel);
		m_queryResult.SetItemText(0,3,(LPCTSTR)(_bstr_t)m_pRecordset->GetCollect(_T("f_date")));

		m_pRecordset->MoveNext();
	}
	m_AdoConn.ExitConnect();
}
//更新list控件显示内容
void CLookUpDlg::UpdateGridInfo()
{
	CString sql;
	sql.Format(_T("select* from t_info"));
	AddToGrid(sql);
}

void CLookUpDlg::OnBnClickedQuery()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(TRUE);
	//获取设置的时间范围
	CTime timeStart,timeStop;
	m_dateStart.GetTime(timeStart);
	m_dateStop.GetTime(timeStop);
	CString strTimeFrom=timeStart.Format("%Y-%m-%d");
	CString strTimeTo=timeStop.Format("%Y-%m-%d");
	strTimeFrom.Format(_T("' %s '"),strTimeFrom);//注意时间前面要加单引号
	strTimeTo.Format(_T("' %s '"),strTimeTo);//注意时间前面要加单引号
	//AfxMessageBox(strTimeFrom);
	//获取设置的涉密等级
	short level=(short)m_secretLevel.GetCurSel();
	CString strLevel=_T("");
	if(level<=5)
		strLevel.Format(_T("and f_level=%d"),level);

	CString sql;
	//根据日期，设置合理的sql语句
	if(timeStart>timeStop)
	{
		AfxMessageBox(_T("设置的日期有误，开始时间不能比结束时间大!"));
		return ;
	}
	else if(timeStart==timeStop)
	{
		sql.Format(_T("select* from t_info where f_date >= %s %s"),strTimeTo,strLevel);
	}
	else
	{
		sql.Format(_T("select* from t_info where f_date >= %s and f_date <= %s %s"),strTimeFrom,strTimeTo,strLevel);
	}
	//
	AddToGrid(sql);
}


void CLookUpDlg::OnNMRClickList(NMHDR *pNMHDR, LRESULT *pResult)
{
	LPNMITEMACTIVATE pNMItemActivate = reinterpret_cast<LPNMITEMACTIVATE>(pNMHDR);
	// TODO: 在此添加控件通知处理程序代码
	*pResult = 0;

	 CMenu   menu;   //定义下面要用到的cmenu对象
	 menu.LoadMenu(IDR_MENU); //装载自定义的右键菜单 
	 CMenu   *pContextMenu=menu.GetSubMenu(0); //获取第一个弹出菜单，所以第一个菜单必须有子菜单 
	 pContextMenu->EnableMenuItem(ID_32771,MF_BYCOMMAND|MF_ENABLED); //允许菜单项使用
	 
	 CPoint point1;//定义一个用于确定光标位置的位置  
	 GetCursorPos(&point1);//获取当前光标的位置，以便使得菜单可以跟随光标  
	// ScreenToClient(&point1);
	 pContextMenu->TrackPopupMenu(TPM_LEFTALIGN|TPM_RIGHTBUTTON,point1.x,point1.y,this); //在指定位置显示弹出菜单
	 pContextMenu->Detach();
	// menu.DestroyMenu();
}


void CLookUpDlg::OnRButtonDown(UINT nFlags, CPoint point)
{
	// TODO: 在此添加消息处理程序代码和/或调用默认值

}


void CLookUpDlg::OnMenuItemDelete()
{
	// TODO: 在此添加命令处理程序代码
	//AfxMessageBox(_T("删除"));

	 //首先得到点击的位置
	int nId;
	POSITION pos=m_queryResult.GetFirstSelectedItemPosition();
	 if(pos==NULL)
	 {
		 AfxMessageBox(_T("请至少选择一项"));
		return;
	 }
	 else
	 {
		 if(MessageBox(_T("是否删除所选中项？"),_T("删除检查记录") ,MB_ICONEXCLAMATION|MB_OKCANCEL)==IDOK)
			{
				//AfxMessageBox(_T("a"));
				//删除选中信息
				 while (pos)
				{
					 //得到行号，通过POSITION转化
					 nId=(int)m_queryResult.GetNextSelectedItem(pos);
					 UpdateDectectInfo(m_queryResult.GetItemText(nId,1));
					 //AfxMessageBox(m_queryResult.GetItemText(nId,1));
			 
				 }
			 }
			 else
			{
				//AfxMessageBox(_T("b"));
				return;
			}
		 
	 }
}

void CLookUpDlg::UpdateDectectInfo(CString filename)
{
	ADOConn m_AdoConn;
	m_AdoConn.OnInitADOConn();
	CString sql;
	sql.Format(_T("select* from t_info"));
	_RecordsetPtr  m_pRecordset;
	m_pRecordset=m_AdoConn.GetRecordSet((_bstr_t)sql);
	//查找该记录所在位置，删除掉
	//
	int curPos;
	CString file;
	while(m_AdoConn.m_pRecordset->adoEOF==0)
	{
		file=(LPCTSTR)(_bstr_t)m_pRecordset->GetCollect(_T("f_filename"));
		//如果匹配成功
		if(file.Trim()==filename.Trim())
		{
			m_pRecordset->Delete(adAffectCurrent);
			m_pRecordset->Update();
			m_AdoConn.ExitConnect();
			break;
		}

		m_pRecordset->MoveNext();
	}

	//更新信息
	AfxMessageBox(_T("删除成功"));
	UpdateGridInfo();
}

BOOL CLookUpDlg::OnCommand(WPARAM wParam, LPARAM lParam)
{
	// TODO: 在此添加专用代码和/或调用基类
	int menuID = LOWORD(wParam);   

	return CDialogEx::OnCommand(wParam, lParam);
}


BOOL CLookUpDlg::PreTranslateMessage(MSG* pMsg)
{
	// TODO: 在此添加专用代码和/或调用基类
	/*
	if(WM_RBUTTONUP ==pMsg->message) 
    {
       AfxMessageBox(_T("hahha"));
     }
	*/
	return CDialogEx::PreTranslateMessage(pMsg);
}
