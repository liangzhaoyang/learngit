#pragma once
#include "afxcmn.h"


// CImageDetectDlg 对话框

class CImageDetectDlg : public CDialogEx
{
	DECLARE_DYNAMIC(CImageDetectDlg)

public:
	CImageDetectDlg(CWnd* pParent = NULL);   // 标准构造函数
	virtual ~CImageDetectDlg();
	CString GetExtName(CString fileName);//获取后缀名
	int DetectSecretInfo();//检测涉密情况
	int GetExtType(CString fileExtName);//获取文件类型
	void PIC2TXT(CString filename);//将图片识别为TXT
	void UpdateInfo(CString filename,int level);//更新检测信息
	int GetDetectResult(CString filename);
	void Split(CString source, CString divKey, CStringArray& dest);
// 对话框数据
	enum { IDD = IDD_IMAGE };
private:
	int count;
protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

	DECLARE_MESSAGE_MAP()
public:
	CListCtrl m_resultList;
	afx_msg void OnBnClickedImageAdd();
	afx_msg void OnBnClickedImageDelete();
	afx_msg void OnBnClickedDetect();
	virtual BOOL OnInitDialog();
};
