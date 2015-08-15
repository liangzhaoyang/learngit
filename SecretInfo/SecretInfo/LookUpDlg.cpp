// LookUpDlg.cpp : 实现文件
//

#include "stdafx.h"
#include "SecretInfo.h"
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
	DDX_Control(pDX, IDC_DATE_START, m_dateStart);
	DDX_Control(pDX, IDC_DATE_STOP, m_dateStop);
	DDX_Control(pDX, IDC_LIST1, m_queryResult);
}


BEGIN_MESSAGE_MAP(CLookUpDlg, CDialogEx)
	ON_BN_CLICKED(IDOK, &CLookUpDlg::OnBnClickedOk)
	ON_NOTIFY(NM_RCLICK, IDC_LIST1, &CLookUpDlg::OnNMRClickList1)
	ON_COMMAND(ID_LOOLUP_DELETE, &CLookUpDlg::OnMenuItemDelete)
	ON_COMMAND(ID_LOOKUP_EMPTY, &CLookUpDlg::OnMenuItemEmpty)
	ON_COMMAND(ID_LOOKUP_EXPORT, &CLookUpDlg::OnMenuItemExport)
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
	//初始化LISTCTRL
	m_queryResult.SetExtendedStyle(LVS_EX_FLATSB|LVS_EX_FULLROWSELECT|LVS_EX_HEADERDRAGDROP|LVS_EX_ONECLICKACTIVATE|LVS_EX_GRIDLINES);
	m_queryResult.InsertColumn(0,_T("序号"),LVCFMT_LEFT,50,0);
	m_queryResult.InsertColumn(1,_T("文件名"),LVCFMT_LEFT,200,0);
	m_queryResult.InsertColumn(2,_T("涉密等级"),LVCFMT_LEFT,100,0);
	m_queryResult.InsertColumn(3,_T("检测日期"),LVCFMT_LEFT,200,0);
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
}

//查询
void CLookUpDlg::OnBnClickedOk()
{
	// TODO: 在此添加控件通知处理程序代码
	//CDialogEx::OnOK();
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


void CLookUpDlg::OnNMRClickList1(NMHDR *pNMHDR, LRESULT *pResult)
{
	LPNMITEMACTIVATE pNMItemActivate = reinterpret_cast<LPNMITEMACTIVATE>(pNMHDR);
	// TODO: 在此添加控件通知处理程序代码
	*pResult = 0;

	CMenu   menu;   //定义下面要用到的cmenu对象
	 menu.LoadMenu(IDR_MENU); //装载自定义的右键菜单 
	 CMenu   *pContextMenu=menu.GetSubMenu(0); //获取第一个弹出菜单，所以第一个菜单必须有子菜单 
	// pContextMenu->EnableMenuItem(ID_32771,MF_BYCOMMAND|MF_ENABLED); //允许菜单项使用
	 
	 CPoint point1;//定义一个用于确定光标位置的位置  
	 GetCursorPos(&point1);//获取当前光标的位置，以便使得菜单可以跟随光标  

	 pContextMenu->TrackPopupMenu(TPM_LEFTALIGN|TPM_RIGHTBUTTON,point1.x,point1.y,this); //在指定位置显示弹出菜单
	 pContextMenu->Detach();
}

//弹跳菜单删除事件
void CLookUpDlg::OnMenuItemDelete()
{
	// TODO: 在此添加命令处理程序代码
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
				 for(int i=0; i<m_queryResult.GetItemCount(); i++)
				  {
					   if( m_queryResult.GetItemState(i, LVIS_SELECTED) == LVIS_SELECTED )
					   {
							UpdateDectectInfo(m_queryResult.GetItemText(i,1));
							//AfxMessageBox(m_queryResult.GetItemText(i,1));
					   }
				  }
				 //更新信息
				AfxMessageBox(_T("删除成功"));
				UpdateGridInfo();
			
			 }
			 else
			{
				return;
			}
		 
	 }
}


void CLookUpDlg::OnMenuItemEmpty()
{
	// TODO: 在此添加命令处理程序代码
	try{
		//清空数据库内容
		ADOConn m_AdoConn;
		m_AdoConn.OnInitADOConn();
		CString sql;
		sql.Format(_T("delete from t_info"));
		//执行SQL命令
		if(m_AdoConn.ExecuteSQL((_bstr_t)sql))
			MessageBox(_T("清空涉密文件成功！"),_T("浏览涉密信息"),MB_ICONWARNING);
		else
			MessageBox(_T("清空涉密文件失败！"),_T("浏览涉密信息"),MB_ICONWARNING);
		m_AdoConn.ExitConnect();

		//更新Listctrl显示
		UpdateGridInfo();
	}catch(...)
	{
		MessageBox(_T("清空涉密文件异常！"),_T("浏览涉密信息"),MB_ICONWARNING);
	}
}


void CLookUpDlg::Write2Excel()
{
	  //弹出对话框，用于设置保存路径
	  CFileDialog dlg(FALSE,_T("文件(*.xls)"),NULL,OFN_HIDEREADONLY |OFN_OVERWRITEPROMPT, 
	_T("文件(*.xls)|*.xls|文件(*.xlsx)|*.xlsx||"),NULL);

	if (dlg.DoModal() == IDOK)
	{
		//获取路径
		CString strFileName=dlg.GetPathName();

	  //定义变量
	  CApplication0 objApp;
		CWorkbooks objBooks;
		CWorkbook objBook;
		CWorksheets objSheets;
		CWorksheet objSheet;
		CRange0 objRange,usedRange;

		LPDISPATCH lpDisp;
		COleVariant vResult;
		COleVariant covOptional((long)DISP_E_PARAMNOTFOUND,VT_ERROR);

		try{
			 if(!objApp.CreateDispatch(_T("Excel.Application"),NULL))
			 {
					   AfxMessageBox(_T("创建Excel服务失败!"));
					   return;
			 }
			//设置不可见
			 objApp.put_Visible(FALSE);
			 //设置excel操作对象
			 objBooks.AttachDispatch(objApp.get_Workbooks(),TRUE);
			 objBook=objBooks.Add(covOptional);      
			 objSheets.AttachDispatch(objBook.get_Sheets(),TRUE);//加载Sheet页面
 
			 //把第一个Sheet页面的名字改变为TestSheet
			 objSheet.AttachDispatch(objSheets.get_Item(COleVariant((long)1)),TRUE);
			 objSheet.put_Name(_T("涉密检测报告"));
 
			 ///////合并第一行单元格A1至D1//////
			 //加载要合并的单元格
			 objRange.AttachDispatch(objSheet.get_Range(COleVariant(_T("A1")),COleVariant(_T("B1"))),TRUE);
			 objRange.Merge(COleVariant((long)0));
 
			 ////////设置表格内容////////
 
			 objRange.AttachDispatch(objSheet.get_Cells(),TRUE);//加载所有单元格
			//这里的_variant_t可以使用COleVariant代替
			 //首先得到点击的位置
			int nId,index=2;
			POSITION pos=m_queryResult.GetFirstSelectedItemPosition();
			 if(pos==NULL)
			 {
				 AfxMessageBox(_T("请至少选择一项"));
				return;
			 }
			 else
			 {
				 if(MessageBox(_T("是否导出所选中项？"),_T("导出检查记录") ,MB_ICONEXCLAMATION|MB_OKCANCEL)==IDOK)
					{
						//删除选中信息
						 while (pos)
						{
							 //得到行号，通过POSITION转化
							 nId=(int)m_queryResult.GetNextSelectedItem(pos);
							 ADOConn m_AdoConn;
							m_AdoConn.OnInitADOConn();
							CString sql;
							sql.Format(_T("select* from t_report where f_filename like '%s%%'"),m_queryResult.GetItemText(nId,1).Trim().Trim());
							_RecordsetPtr  m_pRecordset;
							m_pRecordset=m_AdoConn.GetRecordSet((_bstr_t)sql);

							//查找该记录所在位置
							//设置标题
							 objRange.put_Item(COleVariant((long)1),COleVariant((long)1),COleVariant(m_queryResult.GetItemText(nId,1)));
							 objRange.put_Item(_variant_t((long)index),_variant_t((long)1),_variant_t(_T("涉密词")));
							 objRange.put_Item(_variant_t((long)index++),_variant_t((long)2),_variant_t(_T("出现次数")));
							 //设置表格内容
							CString file,level;
							bool flag=true;
							while(m_AdoConn.m_pRecordset->adoEOF==0)
							{
								file=(LPCTSTR)(_bstr_t)m_pRecordset->GetCollect(_T("f_word"));
								level=(LPCTSTR)(_bstr_t)m_pRecordset->GetCollect(_T("f_repeat"));
								//如果匹配成功
								//AfxMessageBox(file);
								if(flag)
								{
									flag=false;
									m_pRecordset->MoveNext();
									continue;
								}
								//
								if(!file.IsEmpty())
									objRange.put_Item(_variant_t((long)index),_variant_t((long)1),_variant_t(file));
								if(!level.IsEmpty())
									objRange.put_Item(_variant_t((long)index),_variant_t((long)2),_variant_t(level));
								index++;
								
								m_pRecordset->MoveNext();
							}			 
						 }
					 }
					 else
					{
						return;
					}
		 
			 }
			 objRange.AttachDispatch(objSheet.get_UsedRange());//加载已使用的单元格
 
			 objRange.put_WrapText(_variant_t((long)1));//设置单元格内的文本为自动换行
        
			objRange.put_HorizontalAlignment(_variant_t((long)-4108));
			objRange.put_VerticalAlignment(_variant_t((long)-4108));
 
	//      //////////////为表格设置边框/////////////
		   CRange0 UnitRge;
		   CString CellName;  
		   for(int i=1;i<index;i++)
		   {
					 for(int j=1;j<=EXPORT_MAX_LENGTH;j++)
					 {    
						 CellName.Format(_T("%c%d"),j+64,i);//单元格的名称
						 UnitRge.AttachDispatch(objRange.get_Range(_variant_t(CellName),_variant_t(CellName)));//加载单元格
 
					 //LineStyle=线型Weight=线宽ColorIndex=线的颜色(-4105为自动)
					UnitRge.BorderAround(_variant_t((long)1),_variant_t((long)2),_variant_t((long)-4105),vtMissing,vtMissing);//设置边框
					 }
		   }
			//保存表格
			objBook.SaveAs(COleVariant(strFileName),covOptional,covOptional,
			covOptional,covOptional,covOptional,0,
			covOptional,covOptional,covOptional,covOptional,covOptional); 
			objBook.Close (covOptional,COleVariant(strFileName),covOptional);

			AfxMessageBox(_T("导出信息成功！"));
			 //释放对象（相当重要！）
			 objRange.ReleaseDispatch();
			 objSheet.ReleaseDispatch();
			 objSheets.ReleaseDispatch();
			 objBook.ReleaseDispatch();
			 objBooks.ReleaseDispatch();
			 //m_ExlApp一定要释放，否则程序结束后还会有一个Excel进程驻留在内存中，而且程序重复运行的时候会出错
			 objApp.ReleaseDispatch();
			 //退出程序	
		}catch(...)
		{
			MessageBox(_T("导出检测报告异常！"),_T("导出涉密信息"),MB_ICONERROR);
		}
		}
}

void CLookUpDlg::OnMenuItemExport()
{
	// TODO: 在此添加命令处理程序代码
	Write2Excel();
}

void CLookUpDlg::ExportDetectInfo(CString filename)
{
	
}

//获取文件名
CString CLookUpDlg::GetFileName(CString path)
{
	CString str;
	 int pos=-1;

	 for(int i=path.GetLength();i>0;i--)
	 {
	  pos=path.Find(_T("\\"),i);
	  if(pos>0)
	   break;
	 }
	 str=path.Right(pos-1);//获取路径名

	 return str;
}