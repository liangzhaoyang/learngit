// ModifyWordNet.cpp : 实现文件
//

#include "stdafx.h"
#include "ShemiXitong.h"
#include "ModifyWordNet.h"
#include "afxdialogex.h"


// CModifyWordNet 对话框

IMPLEMENT_DYNAMIC(CModifyWordNet, CDialogEx)

CModifyWordNet::CModifyWordNet(CWnd* pParent /*=NULL*/)
	: CDialogEx(CModifyWordNet::IDD, pParent)
	, m_name(_T(""))
	, m_weight(_T(""))
{

}

CModifyWordNet::~CModifyWordNet()
{
}

void CModifyWordNet::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
	DDX_Control(pDX, IDC_LIST, m_grid);
	DDX_Text(pDX, IDC_SENTWORD, m_name);
	DDX_Text(pDX, IDC_WGT, m_weight);
}


BEGIN_MESSAGE_MAP(CModifyWordNet, CDialogEx)
	ON_NOTIFY(NM_CLICK, IDC_LIST, &CModifyWordNet::OnNMClickList)
	ON_BN_CLICKED(IDC_BUTADD, &CModifyWordNet::OnBnClickedAdd)
	ON_BN_CLICKED(IDC_BUTUPDATE, &CModifyWordNet::OnBnClickedUpdate)
	ON_BN_CLICKED(IDC_BUTDELETE, &CModifyWordNet::OnBnClickedDelete)
END_MESSAGE_MAP()


// CModifyWordNet 消息处理程序


void CModifyWordNet::OnNMClickList(NMHDR *pNMHDR, LRESULT *pResult)
{
	LPNMITEMACTIVATE pNMItemActivate = reinterpret_cast<LPNMITEMACTIVATE>(pNMHDR);
	// TODO: 在此添加控件通知处理程序代码
	*pResult = 0;

	CString str;
	 int nId;
	 //首先得到点击的位置
	POSITION pos=m_grid.GetFirstSelectedItemPosition();
	 if(pos==NULL)
	 {
		 AfxMessageBox(_T("请至少选择一项"));
	 return;
	 }
	 //得到行号，通过POSITION转化
	nId=(int)m_grid.GetNextSelectedItem(pos);
	 //得到列中的内容（0表示第一列，同理1,2,3...表示第二，三，四...列）
	 rowId=_ttoi(m_grid.GetItemText(nId,0));
	 m_name=m_grid.GetItemText(nId,1);
	 m_weight=m_grid.GetItemText(nId,2);

	 str.Format(_T("%d %s %s"),rowId,m_name,m_weight);
	// AfxMessageBox(str);
	 //刷新控件值
	 UpdateData(FALSE); 
}


void CModifyWordNet::OnBnClickedAdd()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(true);
	if(m_name.IsEmpty())
	{
		AfxMessageBox(_T("敏感词不能为空"));
		return;
	}
	if(m_weight.IsEmpty())
	{
		AfxMessageBox(_T("权值不能为空"));
		return;
	}
	ADOConn m_AdoConn;
	m_AdoConn.OnInitADOConn();
	_bstr_t sql;
	sql="select* from dict";
	_RecordsetPtr  m_pRecordset;
	m_pRecordset=m_AdoConn.GetRecordSet(sql);

	try{
		m_pRecordset->AddNew();
		m_pRecordset->put_Collect(_variant_t(_T("name")),_variant_t(m_name));
		m_pRecordset->put_Collect(_variant_t(_T("weight")),_variant_t(m_weight));
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
}


void CModifyWordNet::OnBnClickedUpdate()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(true);
	if(m_name.IsEmpty())
	{
		AfxMessageBox(_T("敏感词不能为空"));
		return;
	}
	if(m_weight.IsEmpty())
	{
		AfxMessageBox(_T("权值不能为空"));
		return;
	}
	ADOConn m_AdoConn;
	m_AdoConn.OnInitADOConn();
	_bstr_t sql;
	sql="select* from dict";
	_RecordsetPtr  m_pRecordset;
	m_pRecordset=m_AdoConn.GetRecordSet(sql);
	//
	int curPos;
	while(m_AdoConn.m_pRecordset->adoEOF==0)
	{
		int pos;
		//pos== m_pRecordset->GetFields()->GetItem((long)0).Value;
		pos=_ttoi((_bstr_t)m_pRecordset->GetCollect(_T("id")));
		if(rowId==pos){
			/*
			CString str;
			str.Format(_T("%d %d"),rowId,m_pRecordset->CursorLocation);
			AfxMessageBox(str);*/
			curPos=m_pRecordset->CursorLocation-2;
			break;
		}
		m_pRecordset->MoveNext();
	}

	try{
		m_pRecordset->MoveFirst();//把光标移动到开始位置
		m_pRecordset->Move((long)curPos,vtMissing);//移动光标到目标位置
		m_pRecordset->put_Collect(_variant_t(_T("name")),_variant_t(m_name));
		m_pRecordset->put_Collect(_variant_t(_T("weight")),_variant_t(m_weight));
		m_pRecordset->Update();
		m_AdoConn.ExitConnect();
	}
	catch(...)
	{
		AfxMessageBox(_T("操作失败"));
		return;
	}
	AfxMessageBox(_T("修改成功"));
	m_grid.DeleteAllItems();//删除ListContrl控件中的数据
	AddToGrid();
}


void CModifyWordNet::OnBnClickedDelete()
{
	// TODO: 在此添加控件通知处理程序代码
	ADOConn m_AdoConn;
	m_AdoConn.OnInitADOConn();
	_bstr_t sql;
	sql="select* from dict";
	_RecordsetPtr  m_pRecordset;
	m_pRecordset=m_AdoConn.GetRecordSet(sql);

	//
	int curPos;
	while(m_AdoConn.m_pRecordset->adoEOF==0)
	{
		int pos;
		//pos== m_pRecordset->GetFields()->GetItem((long)0).Value;
		pos=_ttoi((_bstr_t)m_pRecordset->GetCollect(_T("id")));
		if(rowId==pos){
			m_pRecordset->Delete(adAffectCurrent);
			m_pRecordset->Update();
			m_AdoConn.ExitConnect();
			break;
		}
		m_pRecordset->MoveNext();
	}
	
	AfxMessageBox(_T("删除成功"));
	m_grid.DeleteAllItems();//删除ListContrl控件中的数据
	AddToGrid();
}


BOOL CModifyWordNet::OnInitDialog()
{
	CDialogEx::OnInitDialog();

	// TODO:  在此添加额外的初始化
	m_grid.SetExtendedStyle(LVS_EX_FLATSB|LVS_EX_FULLROWSELECT|LVS_EX_HEADERDRAGDROP|LVS_EX_ONECLICKACTIVATE|LVS_EX_GRIDLINES);
	m_grid.InsertColumn(0,_T(""),LVCFMT_LEFT,0,0);
	m_grid.InsertColumn(1,_T("敏感词"),LVCFMT_LEFT,100,1);
	m_grid.InsertColumn(2,_T("权值"),LVCFMT_LEFT,100,2);
	
	rowId=0;
	AddToGrid();

	return TRUE;  // return TRUE unless you set the focus to a control
	// 异常: OCX 属性页应返回 FALSE
}

void CModifyWordNet::AddToGrid()
{
	ADOConn m_AdoConn;
	m_AdoConn.OnInitADOConn();
	CString sql;
	sql.Format(_T("select* from dict"));
	_RecordsetPtr  m_pRecordset;
	m_pRecordset=m_AdoConn.GetRecordSet((_bstr_t)sql);
	while(m_AdoConn.m_pRecordset->adoEOF==0)
	{
		m_grid.InsertItem(0,_T(""));
		m_grid.SetItemText(0,0,(LPCTSTR)(_bstr_t)m_pRecordset->GetCollect(_T("id")));
		m_grid.SetItemText(0,1,(LPCTSTR)(_bstr_t)m_pRecordset->GetCollect(_T("name")));
		m_grid.SetItemText(0,2,(LPCTSTR)(_bstr_t)m_pRecordset->GetCollect(_T("weight")));
		
		m_pRecordset->MoveNext();
	}
	m_AdoConn.ExitConnect();
}

BOOL CModifyWordNet::DestroyWindow()
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
	/*FILE *fp=fopen("pattern.txt","w+");
	if(fp!=NULL)
	{
		char *temp="\n";
		CString name,weight;
		int i=0;
		while(m_AdoConn.m_pRecordset->adoEOF==0)
		{
			name=(LPCTSTR)(_bstr_t)m_pRecordset->GetCollect(_T("name"));
			name.Trim();//去掉空白字符
			//类型转换
			USES_CONVERSION;
			char* buf = T2A(name);
			//隔行写入回车
			if (i==0)
				i++;
			else
				fwrite(temp,1,1,fp);;
			fwrite(buf,sizeof(buf),strlen(buf)/sizeof(buf),fp);
			

			m_pRecordset->MoveNext();
		}
		fclose(fp);
	}*/
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
		//weight=(LPCTSTR)(_bstr_t)m_pRecordset->GetCollect(_T("weight"));
		file.Write(_T("\r\n"),4);	
		m_pRecordset->MoveNext();
	}
	m_AdoConn.ExitConnect();
	file.Flush();
	file.Close();
	//
	return CDialogEx::DestroyWindow();
}
