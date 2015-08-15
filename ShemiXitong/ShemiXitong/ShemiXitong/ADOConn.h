#ifndef _ADOCONN_H
#define _ADOCONN_H
#if !defined(AFX_ADOCONN_H__256F7B31_7156_4B81_B295_09375C61D2C2__INCLUDED_)
#define AFX_ADOCONN_H__256F7B31_7156_4B81_B295_09375C61D2C2__INCLUDED_

#import "C:\Program Files\Common Files\System\ado\msado15.dll"no_namespace\
    rename("EOF","adoEOF")rename("BOF","adoBOF")

#if _MSC_VER > 1000
#endif

#pragma once
/*
  定义一个数据库连接类
*/
class ADOConn
{
public:
	ADOConn(void);
	~ADOConn(void);
	void OnInitADOConn();//初始化连接
	void ExitConnect();//断开连接

	_RecordsetPtr& GetRecordSet(_bstr_t bstrSQL);//获取记录集
	BOOL ExecuteSQL(_bstr_t bstrSQL);//执行SQL命令
 public:
	_ConnectionPtr m_pConnection;//数据源连接变量
	_RecordsetPtr  m_pRecordset;//数据集变量
};

#endif

#endif