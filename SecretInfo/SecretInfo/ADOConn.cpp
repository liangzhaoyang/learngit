#include "StdAfx.h"
#include "ADOConn.h"


ADOConn::ADOConn(void)
{
}


ADOConn::~ADOConn(void)
{
}

void ADOConn::OnInitADOConn()//初始化连接
{
	::CoInitialize(NULL);
	HRESULT hr;
	try
	{
		
		//
		hr=m_pConnection.CreateInstance("ADODB.Connection");
		//判断连接是否成功
		 if (SUCCEEDED(hr))
		 {
		_bstr_t strConnect=("driver={SQL Server};Server=(local)\\sqlexpress;DATABASE=WordNet;UID=login;PWD=123456");
		//	 _bstr_t strConnect=("driver={SQL Server};Server=169.254.53.48,1433;DATABASE=WordNet;UID=login;PWD=123456");
			m_pConnection->Open(strConnect,"","",adModeUnknown);
		 }
	}
	catch(_com_error e)
	{
		AfxMessageBox(e.Description());
	}
}

void ADOConn::ExitConnect()//退出连接
{
	if(m_pConnection->State)
		m_pConnection->Close();
	::CoUninitialize();
}
_RecordsetPtr& ADOConn::GetRecordSet(_bstr_t bstrSQL)//获取记录集
{
	try{
		if(m_pConnection==NULL)
			OnInitADOConn();
		//
		m_pRecordset.CreateInstance(__uuidof(Recordset));
		//
		m_pRecordset->Open(bstrSQL,m_pConnection.GetInterfacePtr(),adOpenDynamic,adLockOptimistic,adCmdText);
	}
	catch(_com_error e)
	{
		AfxMessageBox(e.Description());
	}
	return m_pRecordset;
}

BOOL ADOConn::ExecuteSQL(_bstr_t bstrSQL)//执行SQL命令
{
	_variant_t  RecordAffected;
	try
	{
		if(m_pConnection==NULL)
			OnInitADOConn();
		m_pConnection->Execute(bstrSQL,NULL,adCmdText);
		return true;
	}
	catch(_com_error e)
	{
		AfxMessageBox(e.Description());
		return false;
	}
}