// WordNetDlg.cpp : 实现文件
//

#include "stdafx.h"
#include "SecretInfo.h"
#include "WordNetDlg.h"
#include "afxdialogex.h"


// CWordNetDlg 对话框

IMPLEMENT_DYNAMIC(CWordNetDlg, CDialogEx)

CWordNetDlg::CWordNetDlg(CWnd* pParent /*=NULL*/)
	: CDialogEx(CWordNetDlg::IDD, pParent)
{

}

CWordNetDlg::~CWordNetDlg()
{
}

void CWordNetDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
	DDX_Control(pDX, IDC_WORDNET_LIST, m_grid);
}


BEGIN_MESSAGE_MAP(CWordNetDlg, CDialogEx)
	ON_BN_CLICKED(IDC_BUTADD, &CWordNetDlg::OnBnClickedAdd)
	ON_BN_CLICKED(IDC_BUTUPDATE, &CWordNetDlg::OnBnClickedUpdate)
	ON_BN_CLICKED(IDC_BUTDELETE, &CWordNetDlg::OnBnClickedDelete)
END_MESSAGE_MAP()


// CWordNetDlg 消息处理程序

//添加涉密词
void CWordNetDlg::OnBnClickedAdd()
{
	// TODO: 在此添加控件通知处理程序代码
	//弹出添加涉密词对话框
	CEditDInfoDlg dlg;
	INT_PTR nResponse = dlg.DoModal();
	if (nResponse == IDOK)
	{
		// TODO: 在此放置处理何时用
		//  “确定”来关闭对话框的代码
		//获取添加的涉密词，并添入数据库
		try{
			UpdateData(TRUE);
			//获取涉密词，并判断是否为空
			CString m_name=dlg.GetSenseWord();	
			if(m_name.IsEmpty())
			{
				MessageBox(_T("涉密词不能为空！"),_T("编辑涉密词"),MB_ICONERROR);
				return;
			}
			
			//插入数据库，并更新显示
			ADOConn m_AdoConn;
			m_AdoConn.OnInitADOConn();
			_bstr_t sql;
			sql="select* from dict";
			_RecordsetPtr  m_pRecordset;
			m_pRecordset=m_AdoConn.GetRecordSet(sql);

			try{
				//判断是否已经存在，若存在，则不插入，否则插入
				while(m_AdoConn.m_pRecordset->adoEOF==0)
				{
					CString strName=(LPCTSTR)(_bstr_t)m_pRecordset->GetCollect(_T("name"));
					if(strName.Trim()==m_name.Trim()){
						MessageBox(_T("涉密词已存在！"),_T("编辑涉密词"),MB_ICONWARNING);
						m_AdoConn.ExitConnect();

						return;
					}
					m_pRecordset->MoveNext();
				}
				//插入
				m_pRecordset->AddNew();
				m_pRecordset->put_Collect(_variant_t(_T("name")),_variant_t(m_name));
				m_pRecordset->Update();
				m_AdoConn.ExitConnect();
			}
			catch(...)
			{
				AfxMessageBox(_T("操作失败"));
				return;
			}
			AfxMessageBox(_T("添加成功"));

			m_grid.DeleteAllItems();//删除ListContrl控件中的数据
			AddToGrid();

		}catch(...)
		{
			MessageBox(_T("添加涉密词失败！"),_T("编辑涉密词"),MB_ICONERROR);
		}
	}
	else if (nResponse == IDCANCEL)
	{
		// TODO: 在此放置处理何时用
		//  “取消”来关闭对话框的代码
	}
}

//修改涉密词
void CWordNetDlg::OnBnClickedUpdate()
{
	// TODO: 在此添加控件通知处理程序代码
	//判断是否已有选中
	POSITION pos=m_grid.GetFirstSelectedItemPosition();
	 if(pos==NULL)
	 {
		 MessageBox(_T("请至少选择一项！"),_T("编辑涉密词"),MB_ICONERROR);
		 return;
	 }

	 //得到行号，通过POSITION转化
	int nId=(int)m_grid.GetNextSelectedItem(pos);
	 //得到列中的内容（0表示第一列，同理1,2,3...表示第二，三，四...列）
	 CString name=m_grid.GetItemText(nId,1);

	//弹出修改涉密词对话框
	CEditDInfoDlg dlg;
	INT_PTR nResponse = dlg.DoModal();
	if (nResponse == IDOK)
	{
		// TODO: 在此放置处理何时用
		//  “确定”来关闭对话框的代码
		//获取修改的涉密词，并添入数据库
		try{
			UpdateData(TRUE);
			//获取涉密词，并判断是否为空
			CString m_name=dlg.GetSenseWord();	
			if(m_name.IsEmpty())
				MessageBox(_T("涉密词不能为空！"),_T("编辑涉密词"),MB_ICONERROR);
			
			//插入数据库，并更新显示
			ADOConn m_AdoConn;
			m_AdoConn.OnInitADOConn();
			_bstr_t sql;
			sql="select* from dict";
			_RecordsetPtr  m_pRecordset;
			m_pRecordset=m_AdoConn.GetRecordSet(sql);

			try{
				while(m_AdoConn.m_pRecordset->adoEOF==0)
				{
					CString strName=(LPCTSTR)(_bstr_t)m_pRecordset->GetCollect(_T("name"));
					if(strName.Trim()==name.Trim()){
						m_pRecordset->put_Collect(_variant_t(_T("name")),_variant_t(m_name));
						break;
					}
					m_pRecordset->MoveNext();
				}
				//
				m_pRecordset->Update();
				m_AdoConn.ExitConnect();
			}
			catch(...)
			{
				AfxMessageBox(_T("操作失败"));
				return;
			}
			AfxMessageBox(_T("修改成功"));
			//删除ListContrl控件中的数据
			m_grid.DeleteAllItems();
			AddToGrid();

		}catch(...)
		{
			MessageBox(_T("修改涉密词失败！"),_T("编辑涉密词"),MB_ICONERROR);
		}
	}
	else if (nResponse == IDCANCEL)
	{
		// TODO: 在此放置处理何时用
		//  “取消”来关闭对话框的代码
	}
}

//删除涉密词
void CWordNetDlg::OnBnClickedDelete()
{
	// TODO: 在此添加控件通知处理程序代码
	//判断是否已有选中
	POSITION pos=m_grid.GetFirstSelectedItemPosition();
	 if(pos==NULL)
	 {
		 MessageBox(_T("请至少选择一项！"),_T("编辑涉密词"),MB_ICONERROR);
		 return;
	 }

	 //得到行号，通过POSITION转化
	int nId=(int)m_grid.GetNextSelectedItem(pos);
	 //得到列中的内容（0表示第一列，同理1,2,3...表示第二，三，四...列）
	 CString name=m_grid.GetItemText(nId,1);

	 try{
		  //删除数据库记录，并更新显示
			ADOConn m_AdoConn;
			m_AdoConn.OnInitADOConn();
			_bstr_t sql;
			sql="select* from dict";
			_RecordsetPtr  m_pRecordset;
			m_pRecordset=m_AdoConn.GetRecordSet(sql);
			//遍历查找
			while(m_AdoConn.m_pRecordset->adoEOF==0)
			{
				CString strName=(LPCTSTR)(_bstr_t)m_pRecordset->GetCollect(_T("name"));
				if(strName.Trim()==name.Trim()){
					m_pRecordset->Delete(adAffectCurrent);
					break;
				}
				m_pRecordset->MoveNext();
			}
			//
			m_pRecordset->Update();
			m_AdoConn.ExitConnect();
		}
		catch(...)
		{
			MessageBox(_T("删除涉密词失败！"),_T("编辑涉密词"),MB_ICONERROR);
			return;
		}
		AfxMessageBox(_T("删除成功"));
		//删除ListContrl控件中的数据
		m_grid.DeleteAllItems();
		AddToGrid();
}

//将数据绑定到ListCtrl
void CWordNetDlg::AddToGrid()
{
	//连接数据库
	ADOConn m_AdoConn;
	m_AdoConn.OnInitADOConn();
	CString sql;
	sql.Format(_T("select* from dict"));
	_RecordsetPtr  m_pRecordset;
	m_pRecordset=m_AdoConn.GetRecordSet((_bstr_t)sql);
	//绑定数据
	int count=1;
	CString strCode;
	while(m_AdoConn.m_pRecordset->adoEOF==0)
	{
		m_grid.InsertItem(0,_T(""));
		strCode.Format(_T("%d"),count++);
		m_grid.SetItemText(0,0,strCode);
		m_grid.SetItemText(0,1,(LPCTSTR)(_bstr_t)m_pRecordset->GetCollect(_T("name")));
		
		m_pRecordset->MoveNext();
	}
	m_AdoConn.ExitConnect();
}

//初始化对话框
BOOL CWordNetDlg::OnInitDialog()
{
	CDialogEx::OnInitDialog();

	// TODO:  在此添加额外的初始化
	//初始化ListCtrl控件
	m_grid.SetExtendedStyle(LVS_EX_FLATSB|LVS_EX_FULLROWSELECT|LVS_EX_HEADERDRAGDROP|LVS_EX_ONECLICKACTIVATE|LVS_EX_GRIDLINES);
	m_grid.InsertColumn(0,_T("序号"),LVCFMT_LEFT,50,0);
	m_grid.InsertColumn(1,_T("敏感词"),LVCFMT_LEFT,500,1);
	//绑定数据
	AddToGrid();

	return TRUE;  // return TRUE unless you set the focus to a control
	// 异常: OCX 属性页应返回 FALSE
}

//销毁对话框，将涉密词写入文本文件
BOOL CWordNetDlg::DestroyWindow()
{
	// TODO: 在此添加专用代码和/或调用基类
	//连接数据库
	ADOConn m_AdoConn;
	m_AdoConn.OnInitADOConn();
	CString sql;
	sql.Format(_T("select* from dict"));
	_RecordsetPtr  m_pRecordset;
	m_pRecordset=m_AdoConn.GetRecordSet((_bstr_t)sql);

	//打开词库文件	
	CFile   file;
	file.Open(_T("pattern.txt"),CFile::modeCreate|CFile::modeWrite);
	WORD unicode = 0xFEFF;
	file.Write(&unicode,2);
	
	//更新词库文件
	CString name,weight;
	while(m_AdoConn.m_pRecordset->adoEOF==0)
	{
		name=(LPCTSTR)(_bstr_t)m_pRecordset->GetCollect(_T("name"));
		//AfxMessageBox(name);
		name=name.Trim();
		file.Write(name,wcslen(name)*sizeof(wchar_t));
		file.Write(_T("\r\n"),4);	
		m_pRecordset->MoveNext();
	}
	m_AdoConn.ExitConnect();
	file.Flush();
	file.Close();
	return CDialogEx::DestroyWindow();
}
