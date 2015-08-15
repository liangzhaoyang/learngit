#pragma once
#include "afxcmn.h"
#include "AC_BH.h"
#include "ADOConn.h"
#include "EzWordAutomation.h"
#include "SearchDlg.h"
#include <tchar.h>
#include "OCR.h"
//
#include "CApplication.h"
#include "CDocument0.h"
#include "CDocuments.h"
#include "CRange.h"
//excel
#include "CApplication0.h"
#include "CRange0.h"
#include "CWorkbook.h"
#include "CWorkbooks.h"
#include "CWorksheet.h"
#include "CWorksheets.h"
//ppt
#include "CApplication1.h"
#include "CPresentation.h"
#include "CPresentations.h"
#include "CSlide.h"
#include "CSlides.h"
#include "CShape.h"
#include "CShapes.h"
#include "CTextFrame.h"
#include "CTextRange.h"

#pragma comment(lib,"OCR.lib")
// CTaskDlg 对话框

class CTaskDlg : public CDialogEx
{
	DECLARE_DYNAMIC(CTaskDlg)

public:
	CTaskDlg(CWnd* pParent = NULL);   // 标准构造函数
	virtual ~CTaskDlg();
	CString GetExtName(CString fileName);//获取后缀名
	int DetectSecretInfo();//检测涉密情况
	int GetExtType(CString fileExtName);//获取文件类型
	void Pdf2TXT(char* filename);//将PDF转为TXT
	void Word2TXT(CString filename);//将word转为TXT
	void PIC2TXT(CString filename);//将图片识别为TXT
	void Excel2TXT(CString filename);//将excel转为TXT
	void PPT2TXT(CString filename);//将ppt转为TXT
	void UpdateInfo(CString filename,int level);//更新检测信息
	int GetDetectResult(CString filename);
	void Split(CString source, CString divKey, CStringArray& dest);
	void ReadWord(CString filename);//读取word中的内容
	void ReadExcel(CString filename);//读取excel中的内容
	void ReadPowerPoint(CString filename);//读取PPT中的内容
// 对话框数据
	enum { IDD = IDD_TASK };
private:
	//word
	CApplication  wordApp;  
     CDocuments  docs;  
     CDocument0  doc;  
     CRange  wordRange;  
	 //excel
	CApplication0 excelApp;
    CWorkbooks books;
    CWorkbook book;
    CWorksheets sheets;
    CWorksheet sheet;
    CRange0 excelRange;
	//ppt
	CApplication1  pptApp; 
	CPresentations presentations;
	CPresentation presentation;
	CSlides        slides; 
	CSlide        slide;
	CShapes shapes;
	CShape shape;
	CTextFrame textFrame;
	CTextRange textRange;
protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

	DECLARE_MESSAGE_MAP()
public:
	CListCtrl m_searchList;
	CListCtrl m_resultList;
	virtual BOOL OnInitDialog();
	afx_msg void OnBnClickedDetect();
	CString m_fileName;
	afx_msg void OnNMClickSearchlist(NMHDR *pNMHDR, LRESULT *pResult);
	afx_msg void OnNMClickResultlist(NMHDR *pNMHDR, LRESULT *pResult);
	afx_msg void OnBnClickedAddInfo();
	afx_msg void OnBnClickedDeleteInfo();
	afx_msg void OnBnClickedSearch();
	CString m_queryStr;
};
