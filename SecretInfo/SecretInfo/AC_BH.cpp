// 头文件
//#define _CRT_SECURE_NO_WARNINGS
#include "stdafx.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "AC_BH.h"
#include <ctype.h>
#include <Windows.h>
#include <iostream>
#include <fstream>
using namespace std;
// 宏定义
//#define CASE_SENSITIVE
//#define DEBUG_SEARCH
int ACtree_build(pattern_tree *ptree, pattern_data *patterns, int npattern)//传入参数为指针型，不用返回值
{
	int i;
	pattern_tree_node *root = NULL, *parent = NULL;
	unsigned char ch;
	int max_pattern_len = 0, min_pattern_len = PATTERN_LEN;
	if (NULL == ptree || NULL == patterns || npattern < 0)
	{
		goto err;
	}

	root = (pattern_tree_node *)malloc(sizeof (pattern_tree_node));//为根节点分配空间
	if (NULL == root)
	{
		goto err;
	}
	memset(root, 0, sizeof (pattern_tree_node));//系统函数：填充root指向的空间的头sizeof (pattern_tree_node)个字符为全0
	root->label = -2;    // 树根标志
	root->depth = 0;     // 树根深度
	// 对输入的字串循环处理添加进ACTree
	for (i = 0; i < npattern; i++)
	{
		int pat_len;
		int ch_i;
		pat_len = (patterns + i)->len;//第i个模式串的长度pat_len
		if (pat_len == 0)//当模式串长度为0时跳过本次循环
		{
			continue;
		}
		else
		{
			if (pat_len > PATTERN_LEN)//当模式串长度大于预定义的最大值时，截取模式串（也是为了排错）
			{
				pat_len = PATTERN_LEN;
			}
			if (pat_len > max_pattern_len)//获取最大模式串长度
			{
				max_pattern_len = pat_len;
			}
			if (pat_len < min_pattern_len)//获取最短模式串长度
			{
				min_pattern_len = pat_len;
			}
			parent = root;//开始建立节点，指针指向根节点
			for (ch_i = 0; ch_i < pat_len; ch_i++)//从根节点出发，搜索模式树直到出现叶子节点
			{
				ch = ((patterns + i)->data)[ch_i];
#ifndef CASE_SENSITIVE
				ch = tolower(ch);//#####################为什么呀使用tolower函数（转小写）
#endif
				// 对应的字符索引为NULL
				if (NULL == parent->childs[ch])
				{
					break;
				}
				parent = parent->childs[ch];
			}
			if (ch_i < pat_len)
			{
				// 在父节点下添加新节点
				for (; ch_i < pat_len; ch_i++)
				{
					pattern_tree_node *node = NULL;
					ch = ((patterns + i)->data)[ch_i];
#ifndef CASE_SENSITIVE
					ch = tolower(ch);
#endif
					node = (pattern_tree_node *)malloc(sizeof (pattern_tree_node));//为新节点分配空间
					if (node == NULL)
					{
						goto err;
					}
					memset(node, 0, sizeof(pattern_tree_node));//初始化新节点
					//填充新节点
					node->depth = ch_i + 1;
					node->ch = ch;
					node->label = -1;
					// 将新节点添加到父节点的对应字符索引指针
					parent->childs[ch] = node;//相当于一个简单的Hash索引childs[ch]:由ch的值确定是哪个孩子
					// 不考虑大小写的分别
#ifndef CASE_SENSITIVE
					if ((ch >= 'a') && (ch <= 'z'))
					{
						parent->childs[ch - 32] = node;
					}
#endif
					parent->nchild++;
					parent->one_child = ch;
					node->parent = parent;
					parent = node;
				}
			}
		}
		// lable 记录字串来自于第几个输入字串，即表示是第几个模式串的尾字节
		parent->label = i;
	}
	//模式树构建完毕，填充模式树结构
	ptree->pattern_list = patterns;
	ptree->pattern_count = npattern;
	ptree->root = root;
	ptree->max_depth = max_pattern_len;
	ptree->min_pattern_size = min_pattern_len;
	return 0;
err:
	// 出错处理,释放申请的空间
	if (ptree->root != NULL)
	{
		_clean_tree(ptree->root);
		free(ptree->root);
		ptree->root = NULL;
	}
	return -1;
}

/*--------------------------------------------------------------------------
* 函数名:
*    _print_tree
*
* 函数功能:
*    打印当前节点及其所有后缀节点
*
* 参数说明:
*    pattern_tree_node *root : 当前的节点的指针
*
* 返回值类型:  void
*    无
----------------------------------------------------------------------------*/
void _print_tree(pattern_tree_node *root)
{
	int i;
	if (NULL == root)
		return;
	printf("ch:%2c   GSshift:%2d   label:%2d   depth:%2d   childs:", root->ch, root->GSshift, root->label, root->depth);
	for (i = 0; i < 256; i++)
	{
#ifndef CASE_SENSITIVE
		if ((i >= 'A') && (i <= 'Z'))
		{
			continue;
		}
#endif
		if (NULL != root->childs[i])
		{
			printf("%c ", (root->childs[i])->ch);
		}
	}
	printf("\n");
	// 递归打印本节点的所有后缀节点信息
	for (i = 0; i < 256; i++)
	{
		if (NULL != root->childs[i])
		{
			_print_tree(root->childs[i]);
		}
	}
	return;
}

/*--------------------------------------------------------------------------
* 函数名:
*    ACtree_print_tree
*
* 函数功能:
*    打印整个树ACTree的所有节点字符信息
*
*  参数说明:
*    pattern_tree *ptree    : 指向ACTree模式树的指针
*
* 返回值类型:  void
*    无
----------------------------------------------------------------------------*/
//#if 0
void ACtree_print_tree(pattern_tree *ptree)
{
	printf("--- ACTree information---\n");
	if (NULL == ptree)
	{
		return;
	}
	if (NULL != ptree->root)
	{
		_print_tree(ptree->root);
	}
	return;
}
//#endif

/*--------------------------------------------------------------------------
* 函数名:
*    ACtree_compute_BCshifts
*
* 函数功能:
*    设置ptree的BCshift
*
*  参数说明:
*    pattern_tree *ptree    : 指向ACTree模式树的指针
*
* 返回值:  int
*    0 : 处理成功
----------------------------------------------------------------------------*/
int ACtree_compute_BCshifts(pattern_tree *ptree)//遍历模式树，找到所有单个字符在所有模式串中的最左出现并记录在BCshift[256]中
{
	int i, j = 0;
	for (i = 0; i < 256; i++) //初始化BCshift
		ptree->BCshift[i] = ptree->min_pattern_size;
	for (i = ptree->min_pattern_size - 1; i > 0; i--)
	{
		for (j = 0; j < ptree->pattern_count; j++)
		{
			unsigned char ch;
			ch = (ptree->pattern_list + j)->data[i];

#ifndef CASE_SENSITIVE
			ch = tolower(ch);
#endif
			ptree->BCshift[ch] = i;
#ifndef CASE_SENSITIVE
			if ((ch > 'a') && (ch <'z'))
			{
				ptree->BCshift[ch - 32] = i;
			}
#endif
		}
	}
	return 0;
}

/*--------------------------------------------------------------------------
* 函数名:
*    ACtree_compute_BCBshifts
*
* 函数功能:
*    设置ptree的BCBshift
*
*  参数说明:
*    pattern_tree *ptree    : 指向ACTree模式树的指针
*
* 返回值:  int
*    0 : 处理成功
----------------------------------------------------------------------------*/
int ACtree_compute_BCBshifts(pattern_tree *ptree)
{
	int i, j = 0;

	for (i = 0; i < 256; i++)
		ptree->BCBshift[i] = ptree->min_pattern_size;

	for (i = ptree->min_pattern_size - 1; i >= 0; i--)
	{
		for (j = 0; j < ptree->pattern_count; j++)
		{
			unsigned char ch;

			ch = (ptree->pattern_list + j)->data[i];

#ifndef CASE_SENSITIVE
			ch = tolower(ch);
#endif

			ptree->BCBshift[ch] = i;

#ifndef CASE_SENSITIVE
			if ((ch > 'a') && (ch <'z'))
			{
				ptree->BCBshift[ch - 32] = i;
			}
#endif
		}
	}
	return 0;
}


/*--------------------------------------------------------------------------
* 函数名:
*    set_GSshift
*
* 函数功能:
*    设置ACTree中关键字pat1的GCshift
*
* 参数说明:
*    pattern_tree *ptree    : 指向ACTree模式树的指针
*    unsigned char *pat     : 关键字pat1
*    int depth              : 要改变GSshift字符的深度为depth
*    int shift              : 拟进行的变化
*
* 返回值:  int
*    0 : 处理成功
*   -1 : 出错
----------------------------------------------------------------------------*/
int set_GSshift(pattern_tree *ptree, unsigned char *pat, int depth, int shift)
{
	int i;
	pattern_tree_node *node;
	if (NULL == ptree || NULL == ptree->root)
	{
		goto err;
	}
	node = ptree->root;
	for (i = 0; i < depth; i++)
	{
		node = node->childs[pat[i]];
		if (NULL == node)
		{
			goto err;
		}
	}

	// 取小位移
	node->GSshift = node->GSshift < shift ? node->GSshift : shift;
	return 0;
err:
	return -1;
}

/*--------------------------------------------------------------------------
* 函数名:
*    compute_GSshift
*
* 函数功能:
*    调整ACTree中关键字pat1因pat2而发生变化的GSshift
*
* 参数说明:
*    pattern_tree *ptree    : 指向ACTree的指针
*    unsigned char *pat1    : 关键字pat1
*    int pat1_len           : 关键字pat1的字串长度
*    unsigned char *pat2    : 关键字pat2
*    int pat2_len           : 关键字pat2的字串长度
*
* 返回值:  int
*    0 : 处理成功 或 未出错
*   -1 : 出错
----------------------------------------------------------------------------*/
int compute_GSshift(pattern_tree *ptree,
	unsigned char *pat1,
	int pat1_len,
	unsigned char *pat2,
	int pat2_len)
{
	unsigned char first_char;
	int i;
	int pat1_index, pat2_index, offset;
	if (NULL == pat1 || NULL == pat2 || pat1_len < 0 || pat2_len < 0)
	{
		goto err;
	}
	if (pat1_len == 0 || pat2_len == 0)
	{
		return 0;
	}
	first_char = pat1[0];

#ifndef CASE_SENSITIVE
	first_char = tolower(first_char);
#endif
	// 从pat2第二个字符开始考虑, 为什么不是第一个字符？？ 因为第一个比较没有意义。这段代码主要作用于寻找
	//前缀在字符串中重复出现？为什么只考虑第二位？如果第二位不同直接跳转，如果在第三位出现不是找不到了？
	for (i = 1; i < pat2_len; i++)
	{
#ifndef CASE_SENSITIVE
		if (tolower(pat2[i]) != first_char)
#else 
		if (pat2[i] != first_char)
#endif
			break;
	}
	set_GSshift(ptree, pat1, 1, i);   //计算每个模式串第一个字符的GSshift
	//ACtree_print_tree (ptree) ;   //测试
	i = 1;               //为什么又重新负值1？？
	while (1)                       //i用来的循环，用途？？
	{
		// 在pat2中寻找与pat1首字符相同字符的首出现位置，
#ifndef CASE_SENSITIVE
		while (i < pat2_len && tolower(pat2[i]) != first_char)
#else
		while (i < pat2_len && pat2[i] != first_char)          //找到与模式串第一位相等的地方
#endif
			i++;

		// pat2剩余字符中未发现有pat1首字符相同字符,结束函数
		if (i == pat2_len)
		{
			break;
		}

		// 仍然发现有首字符相同字符
		pat2_index = i;
		pat1_index = 0;
		offset = i;                         //移动的距离？？

		// 如果偏移反而大于最短关键字长度,不予考虑,因为最大的允许跳转就是min_pattern_size
		if (offset > ptree->min_pattern_size)
		{
			break;
		}

		while (pat2_index < pat2_len && pat1_index < pat1_len) // 重复循环直至两关键字中字符首次不同
		{
#ifndef CASE_SENSITIVE
			if (tolower(pat1[pat1_index]) != tolower(pat2[pat2_index]))
#else
			if (pat1[pat1_index] != pat2[pat2_index])
#endif
				break;
			pat1_index++;               //是比较位的字符的深度
			pat2_index++;
		}

		if (pat2_index == pat2_len) // 关键字pat1前缀是关键字pat2后缀
		{
			int j;                    //j的用途？
			for (j = pat1_index; j < pat1_len; j++)
			{
				set_GSshift(ptree, pat1, j + 1, offset);
				//ACtree_print_tree (ptree) ;   //测试
			}
		}
		else // pat1的前缀是pat2的子串,被pat2包含
		{
			set_GSshift(ptree, pat1, pat1_index + 1, offset);          //字符所在的深度和序号差一位
			//ACtree_print_tree (ptree) ;   //测试
		}
		i++;
	}
	return 0;

err:
	return -1;
}

/*--------------------------------------------------------------------------
* 函数名:
*    ACtree_compute_GSshifts
*
* 函数功能:
*    计算整个ACTree的GSshifts
*
* 参数说明:
*    pattern_tree *ptree    : 指向ACTree的指针
*
* 返回值:  int
*    0 : 无意义
----------------------------------------------------------------------------*/
int ACtree_compute_GSshifts(pattern_tree *ptree)
{
	int pat_i = 0, pat_j = 0;

	for (pat_i = 0; pat_i < ptree->pattern_count; pat_i++)
	{
		for (pat_j = 0; pat_j < ptree->pattern_count; pat_j++)
		{
			unsigned char *ppat_i = (ptree->pattern_list + pat_i)->data;
			int patlen_i = (ptree->pattern_list + pat_i)->len;
			unsigned char *ppat_j = (ptree->pattern_list + pat_j)->data;
			int patlen_j = (ptree->pattern_list + pat_j)->len;
			//只需要计算ppat_i就可以算出每个字符的GSshift；通过set_GSshift计算。
			compute_GSshift(ptree, ppat_i, patlen_i, ppat_j, patlen_j);
			//   printf ("\n函数 ACtree_compute_GSshifts---------------") ; // 测试使用 
			//   printf ("\n----调用 compute_GSshift-------------------\n") ; // 测试使用
			//   ACtree_print_tree(ptree) ;  // 测试使用
		}
	}
	return 0;
}

/*--------------------------------------------------------------------------
* 函数名:
*    _init_GSshifts
*
* 函数功能:
*    初始化当前节点及其所有后续节点的的GSshifts为参数shift
*
* 参数说明:
*    pattern_tree_node *root    : 指向当前节点的指针
*    int shift                  : 初始化值
*
*　返回值:  int
*    0 : 无意义
----------------------------------------------------------------------------*/
int _init_GSshifts(pattern_tree_node *root, int shift)
{
	int i;
	if (root->label != -2)
		root->GSshift = shift;

	for (i = 0; i < 256; i++)
	{
#ifndef CASE_SENSITIVE
		if ((i >= 'A') && (i <= 'Z'))
		{
			continue;
		}
#endif
		if (NULL != root->childs[i])
		{
			_init_GSshifts(root->childs[i], shift);
		}
	}
	return 0;
}

/*--------------------------------------------------------------------------
* 函数名:
*    ACtree_init_GSshifts
*
*　函数功能:
*    初始化整个ACtree模式树的GSshifts
*
*  参数说明:
*    pattern_tree *ptree : 指向ACTree的指针
*
* 返回值:  int
*    0 : 无意义
----------------------------------------------------------------------------*/
int ACtree_init_GSshifts(pattern_tree *ptree)
{
	_init_GSshifts(ptree->root, ptree->min_pattern_size);
	return 0;
}

/*--------------------------------------------------------------------------
* 函数名:
*    ACtree_compute_shifts
*
* 函数功能:
*    计算并调整ACtree的BCshift和GSshift
*
* 参数说明:
*    pattern_tree *ptree    : 指向ACTree的指针
*
* 返回值:  int
*    0 : 无意义
----------------------------------------------------------------------------*/
int ACtree_compute_shifts(pattern_tree *ptree)
{
	ACtree_compute_BCshifts(ptree);
	ACtree_compute_BCBshifts(ptree);
	return 0;
}

/*--------------------------------------------------------------------------
* 函数名:
*    acbm_init
*
* 函数功能:
*    创建 ac_bm模式树,同时计算并调整BCshift和GSshift
*
* 参数说明:
*    pattern_data *patterns    : 指向关键字字串数组的指针
*    int npattern              : 关键字字串个数
*
* 返回值:  pattern_tree *
*    ptree : 处理成功,返回ptree
*    NULL  : 处理失败,返回空指针
----------------------------------------------------------------------------*/
pattern_tree *acbm_init(pattern_data *patterns, int npattern)
{
	//定义模式树结构并分配空间
	pattern_tree *ptree = NULL;
	ptree = (pattern_tree *)malloc(sizeof (pattern_tree));
	if (NULL == ptree)
	{
		goto err;
	}

	memset(ptree, 0, sizeof(pattern_tree));//系统函数：设置ptree指向的空间的头sizeof(pattern_tree)个字符为全0
	ACtree_build(ptree, patterns, npattern);
	// printf ("\n函数 acbm_init----------------------------") ;  // 测试使用
	// printf ("\n----调用 ACtree_build---------------------\n") ; // 测试使用
	// ACtree_print_tree(ptree) ; // 测试使用
	ACtree_compute_shifts(ptree);
	// printf ("\n函数 acbm_init----------------------------") ; // 测试使用 
	// printf ("\n----调用 ACtree_compute_shifts------------\n") ; // 测试使用
	// ACtree_print_tree(ptree) ;  // 测试使用
	return ptree;
err:
	return NULL;
}
/*--------------------------------------------------------------------------
* 函数名:
*    _clean_tree
*
* 函数功能:
*    释放当前节点及其所有后续节点申请的存储空间
*
* 参数说明:
*    pattern_tree_node *root : 指向当前节点的指针
*
* 返回值:  void
*     无
----------------------------------------------------------------------------*/
void _clean_tree(pattern_tree_node *root)
{
	int i;
	for (i = 0; i < 256; i++)
	{
#ifndef CASE_SENSITIVE
		if ((i >= 'A') && (i <= 'Z'))
		{
			continue;
		}
#endif
		if (NULL != root->childs[i])
		{
			_clean_tree(root->childs[i]);
			free(root->childs[i]);
			root->childs[i] = NULL;
		}
	}
	return;
}

/*--------------------------------------------------------------------------
* 函数名:
*    acbm_clean
*
*  函数功能:
*    释放整个ac_bm模式树申请的空间
*
* 参数说明:
*    pattern_tree *ptree   : 指向ACTree的指针
*
* 返回值:  void
*     无
----------------------------------------------------------------------------*/
void acbm_clean(pattern_tree *ptree)
{
	if (NULL == ptree)
	{
		return;
	}
	if (NULL != ptree->root)
	{
		_clean_tree(ptree->root);
		free(ptree->root);
		ptree->root = NULL;
	}
	free(ptree);
	return;
}

/*--------------------------------------------------------------------------
* 函数名:
*    acbm_search
*
* 函数功能:
*    搜索算法,搜索输入的文本text中包含的关键字的个数,并将搜索到的关键字
*    在关键字数组patterns中的index位置以及在待匹配文本text中的偏移值按搜
*    索到的先后顺序依次保存到数组matched_items.
*
* 参数说明:
*    pattern_tree *ptree   : 指向ACTree的指针
*    unsigned char *text   : 带匹配的文本
*    int text_len          : 带匹配文本的长度
*    matched_info_t matched_items[] : 保存搜索结果信息的数组
*    int nmax_index        : 可保存匹配结果的最多个数
cur_index             : 用来匹配移动右移
*   base_index            : 用来不匹配移动左移
* 返回值:  int
*    nmatched  : 匹配到的关键字的个数
nmatched+= acbm_search (ptree, text, text_len, matched_items, nmax_index) ;
----------------------------------------------------------------------------*/
int acbm_search(pattern_tree *ptree, unsigned char *text, int text_len, matched_info_t matched_items[], int nmax_index)
{
	int nmatched = 0;
	register int base_index = 0, cur_index = 0;//register声明一个存放在寄存器中的变量以加快存取速度，通常用来声明一个经常使用的变量
	register int real_shift = 0, gs_shift = 0, bc_shift = 0, bcb_shift = 0;//bcb_shift为当前匹配开始点前一个字符的坏字符转移量
	pattern_tree_node *node = NULL;

	if (text_len < ptree->min_pattern_size)//待测文本长度小于最短模式串长度，则直接结束
	{
		goto ret;
	}

	base_index = text_len - ptree->min_pattern_size;//初始化文本偏移值，即当前窗口到文本头的距离

	while (base_index >= 0)
	{
		cur_index = base_index;//初始化文本当前的比较位
		node = ptree->root;//比较指针指向跟节点

#ifdef DEBUG_SEARCH
		printf("Checking pattern tree at %d...", base_index);
#endif

		while (NULL != node->childs[text[cur_index]]) //当得到匹配之后还要继续向后匹配，因为也许有更长的模式串会匹配
		{
			node = node->childs[text[cur_index]];

			// 匹配到一个关键字,保存到matched_items中
			if (node->label >= 0)
			{
#ifdef DEBUG_SEARCH
				printf("Matched(%d) ", node->label);
#endif
				matched_items[nmatched].pattern_i = node->label;
				matched_items[nmatched].offset = base_index;
#ifdef DEBUG_SEARCH
				printf("%s\n", text + matched_items[nmatched].offset);
#endif
				nmatched++;
				if (nmatched == nmax_index)
				{
					goto ret;
				}
			}
			cur_index++;
			if (cur_index >= text_len)
			{
				break;
			}
		}

		//发现node->childs[text[cur_index]]==NULL，分两种情况：node->nchild>0说明失配，要进行移动；node->nchild=0，说明已经到达叶子节点，匹配也是成功的
		if (node->nchild > 0)
		{
			// 跳字符,由GSshift和BCshift决定跳字符的位数
			if (cur_index < text_len)
			{
				bc_shift = ptree->BCshift[text[cur_index]] + base_index - cur_index;
				//BCshift在不匹配时看的是文本的那个不匹配字符，它的含义是不匹配的那个字符在树中离根节点的距离,
				//cur_index-base_index文本中不匹配位与根节点的相对距离
				//bc_shift得到的就是文本中不匹配的那个字符移动到树中这个字符对应位置需要移动的距离

				bcb_shift = ptree->BCBshift[text[base_index - 1]] + 1;

			}
			else
			{
				bc_shift = 1;
			}

			if (bc_shift <= 0)bc_shift = 1;                          //leo gai
			real_shift = bcb_shift > bc_shift ? bcb_shift : bc_shift; // 取大者跳转leo gai
			//skip++;
			base_index -= real_shift;
#ifdef DEBUG_SEARCH
			printf("Failed, BCBSshift:%d, BCshift:%d Realshift%d", bcb_shift, bc_shift, real_shift);
#endif
		}
		else
		{
			// 1个匹配成功结束后,继续往前面处理
			base_index--;
			//skip++;
#ifdef DEBUG_SEARCH
			printf("Matched, shift %d", 1);
#endif
		}
#ifdef DEBUG_SEARCH
		printf("\n");
#endif
	}

ret:
	return  nmatched;
}
/*--------------------------------------------------------------------------
* 函数名:
*    match_resualt_printf
*
* 函数功能:
*    按次序打印搜索到的关键字结果
*
* 参数说明:
*    unsigned char *text    : 输入的待搜索的文本
*    pattern_data *patterns : 关键字数组
*    int npattern           : 关键字个数
*    matched_info_t matched_items[] : 保存搜索结果信息的数组
*    int nmatched        : 搜索到的关键字个数
*
* 返回值:  int
*    0 : 安全返回,无意义
----------------------------------------------------------------------------*/
int match_resualt_printf(unsigned char *text, pattern_data *patterns, int npattern, matched_info_t matched_items[], int nmatched)
{
	FILE * log;
	fopen_s(&log, "log.txt", "wb");
	//fwrite("查到的涉密词有：", 16, 1, log);
	char *temp = ",";
	int i = 0;
	printf("\n--- key worlds ---\n");
	for (i = 0; i < npattern; i++)
	{
		(patterns + i)->count = 0;
		if ((i + 1) % 6 == 0)
		{
			printf("\n");
		}
		printf("%2d %s\n", i + 1, (patterns + i)->data);
	}
	int a = 0;
	for (i = 0; i < nmatched; i++)
	{
		(patterns + matched_items[i].pattern_i)->count = 0;
		for (int j = 0; j < nmatched; j++)
		{
			if (matched_items[i].pattern_i == matched_items[j].pattern_i)
			{
				(patterns + matched_items[i].pattern_i)->count++;
			}
		}
	}
	for (i = 0; i < npattern; i++)
	{
		if ((patterns + i)->count != 0)
		{
			a = (patterns + matched_items[i].pattern_i)->len;
			char *str = new char[a + 1];
			for (int j = 0; j < a; j++)
			{
				str[j] = (patterns + i)->data[j];
			}
			fwrite(str, a, 1, log);
			//fwrite("\t", 1, 1, log);
			fwrite("\r\n", 2, 1, log);
			fprintf(log, "%d\n", (patterns + i)->count);
			fwrite("\r\n", 2, 1, log);
			delete str;
		}
	}
	fclose(log);
	asciiToUnicode("log.txt", "SecretReport.txt");
	return 0;
}
//调用的函数
int asciiToUnicode(char* asciiTxt, char * unicodeTxt)
{
	FILE * unicodeFile;
	fopen_s(&unicodeFile, unicodeTxt, "wb");
	FILE * asciiFile;
	fopen_s(&asciiFile, asciiTxt, "rb");
	void * buffer;    // 缓冲区指针
	int size;    // 源文件大小

	fseek(asciiFile, 0, SEEK_END);    // 将源文件的指针移动到末尾
	int asciiFileSize = ftell(asciiFile);    // 获取源文件大小,这里要剪掉unicode文件开头2位的标识符

	buffer = malloc(asciiFileSize);    // 申请缓冲区
	fseek(asciiFile, 0, SEEK_SET);    // 将源文件的指针移动到开头, 通上，移2位

	size_t readSize = fread(buffer, asciiFileSize, 1, asciiFile);    // 读取

	int dstBufferSize;
	wchar_t *dstBuffer;

	dstBufferSize = MultiByteToWideChar(CP_ACP, 0, (LPCCH)buffer, asciiFileSize, NULL, NULL);
	dstBuffer = new wchar_t[dstBufferSize];
	ZeroMemory(dstBuffer, dstBufferSize * sizeof(wchar_t));

	int convertSize = MultiByteToWideChar(CP_ACP, 0, (LPCCH)buffer, asciiFileSize, dstBuffer, dstBufferSize);

	char head[2] = { 0xFF, 0xFE }; // windows默认按小端保存
	//{ 0xFE, 0xFF }; // 按大端保存

	fwrite(head, 2, 1, unicodeFile);
	fwrite(dstBuffer, convertSize * 2, 1, unicodeFile);

	delete[] dstBuffer;

	fclose(unicodeFile);    // 关闭源文件
	fclose(asciiFile);    // 关闭目标文件
	free(buffer);    // 释放缓冲区
	return 1;
}

int unicodeToAscii(char * unicodeTxt, char* asciiTxt)
{
	FILE * unicodeFile;
	fopen_s(&unicodeFile, unicodeTxt, "rb");    // 只读方式打开源文件
	FILE * asciiFile;
	fopen_s(&asciiFile, asciiTxt, "wb");    // 只写方式打开目标文件
	void * buffer;    // 缓冲区指针
	//int size;    // 源文件大小

	fseek(unicodeFile, 0, SEEK_END);    // 将源文件的指针移动到末尾
	int unicodeFileSize = ftell(unicodeFile) - 2;    // 获取源文件大小,这里要剪掉unicode文件开头2位的标识符

	buffer = malloc(unicodeFileSize);    // 申请缓冲区

	fseek(unicodeFile, 2, SEEK_SET);    // 将源文件的指针移动到开头, 通上，移2位

	size_t readSize = fread(buffer, unicodeFileSize, 1, unicodeFile);    // 读取


	int dstBufferSize;
	char *dstBuffer;

	// unicodeFileSize由于上面这个值是按照ascii的长度统计出来的，而WideCharToMultiByte的参数实际是传入宽字节的数目，所以除以2,(unicodeFileSize肯定是偶数)
	dstBufferSize = WideCharToMultiByte(CP_ACP, 0, (LPCWCH)buffer, unicodeFileSize / 2, NULL, 0, NULL, NULL);
	dstBuffer = new char[dstBufferSize];
	ZeroMemory(dstBuffer, dstBufferSize);

	int convertSize = WideCharToMultiByte(CP_ACP, 0, (LPCWCH)buffer, unicodeFileSize / 2, dstBuffer, dstBufferSize, NULL, NULL);

	// 其实上面的dstBufferSize == unicodeFileSize / 2 == convertSize
	// 其实上面这句话是错的，dstBufferSize == convertSize == unicodeFileSize - 英文字符数目
	fwrite(dstBuffer, convertSize, 1, asciiFile);

	delete[] dstBuffer;

	fclose(unicodeFile);    // 关闭源文件
	fclose(asciiFile);    // 关闭目标文件
	free(buffer);    // 释放缓冲区
	return 1;
}
int GetTextEncode(char* strTxtPath)
{
	int nType = -1;//error
	//打开要判断的文件
	FILE *pFile = NULL;
	errno_t dError = fopen_s(&pFile, strTxtPath, "r");
	if (0 != dError)
	{
		fclose(pFile);
		return nType;
	}
	//这里要注意是用unsigned   char，不然的话读取到的数据会因为溢出而无法正确判断
	unsigned   char*   chFileFlag = new   unsigned   char[3];
	fread(chFileFlag, 1, 3, pFile);

	if (chFileFlag[0] == 0xEF && chFileFlag[1] == 0xBB && chFileFlag[2] == 0xBF)
		nType = 1;//UTF-8
	else if (chFileFlag[0] == 0xFF && chFileFlag[1] == 0xFE)
		nType = 2;//Unicode
	else if (chFileFlag[0] == 0xFE && chFileFlag[1] == 0xFF)
		nType = 3;//Unicode big endian text
	else
		nType = 4;//ASCII
	fclose(pFile);
	delete chFileFlag;
	return nType;
}


int last_search(char *text1, char *pattern1)
{
	if (GetTextEncode(text1) != 4)
	{
		unicodeToAscii(text1, "test.txt");
		//printf("TextEncode=%d", GetTextEncode(text1));
	}
	else
	{
		printf("TextEncode=%d", GetTextEncode(text1));
		rename(text1, "test.txt");
	}
	unicodeToAscii(pattern1, "patterns.txt");
	int i = 0;
	int j, n;
	int npattern; // 关键字个数,随时注意改变
	int length;
	int text_len;// 待匹配文本字串长度,动态改变
	int success = 0;
	int nmatched = 0;
	char temp;
	int xx = 1000;
	FILE *fp;

	pattern_tree *ptree;
	pattern_data *patterns;
	int nmax_index = MAX_ITEMS;
	struct _matched_info matched_items[MAX_ITEMS];
	unsigned char keyword[PATTERN_LEN]; //模式串单词
	unsigned char text[TEXT_LEN]; //待检文本 
	//***********************去除函数提前定义****************************
	int matched = 0;
	register int base_index = 0, cur_index = 0;//register声明一个存放在寄存器中的变量以加快存取速度，通常用来声明一个经常使用的变量
	register int real_shift = 0, gs_shift = 0, bc_shift = 0, bcb_shift = 0;//bcb_shift为当前匹配开始点前一个字符的坏字符转移量
	pattern_tree_node *node = NULL;
	//***************************************************************

	for (j = 0; j<TEXT_LEN; j++)
	{
		text[j] = '\0';
	}
	n = 0;//n记录模式串的数量	 
	fp = fopen("patterns.txt", "r");
	if (!fp)
	{
		printf("Open file: pattern.txt error!\n");
		exit(0);
	}
	while (xx != 0)//获取模式串的数量，即pattern.txt文件的行数
	{
		xx = fread(&temp, 1, 1, fp);
		if (temp == '\n') n++;
	}
	npattern = ++n;
	printf("模式串数量为：%d\n", npattern);
	rewind(fp);

	patterns = (pattern_data *)malloc(sizeof(pattern_data)* npattern);//为模式串数组分配空间
	if (NULL == patterns)//如果空间分配失败则报错返回
	{
		printf("error\n");
		exit(1);
	}

	printf("读入模式串,并填充模式串数组：\n");//读取模式串并填充模式串数组
	for (i = 0; i<n; i++)//第i个模式串
	{
		for (j = 0; j<PATTERN_LEN; j++)
		{
			keyword[j] = '\0';
		}
		length = 0;//length为当前模式串的长度
		//while(((temp=fgetc(fp))!='\n')&&(temp!=EOF))
		while ((xx = fread(&temp, 1, 1, fp) != 0) && (temp != '\n'))
		{
			keyword[length] = temp;
			length++;
		}
		//填充模式串数组
		(patterns + i)->len = length;
		printf("%s\n", keyword);
		memcpy((patterns + i)->data, keyword, sizeof(unsigned char)*length);
		//strcpy((patterns + i)->data, keyword);
		//		printf("模式串%d：%s  长度：%d\n",i,(patterns+i)->data,(patterns+i)->len);
	}
	fclose(fp);

	//建立自动机	 
	ptree = acbm_init(patterns, npattern);
	ACtree_compute_BCshifts(ptree);


	printf("读入待测文本：\n");
	fp = fopen("test.txt", "r");
	if (!fp)
	{
		printf("Open file: Text.txt error!\n");
		exit(0);
	}

	xx = fread(&temp, 1, 1, fp);
	while (xx != 0)
	{
		i = 0;
		while ((i < TEXT_LEN) && (xx != 0))
		{
			text[i] = temp;
			xx = fread(&temp, 1, 1, fp);
			i++;
			//printf("i:%d\n",i);
		}
		if (xx == 0)
		{
			for (j = i; j<TEXT_LEN; j++)
			{
				text[j] = '\0';
			}
		}
		if ((text_len = strlen((char*)text))>TEXT_LEN)//获得待测文本长度
			text_len = TEXT_LEN;
		nmatched = acbm_search(ptree, text, text_len, matched_items, nmax_index);
		// ACtree_print_tree (ptree) ; // 测试使用
		success = match_resualt_printf(text, patterns, npattern, matched_items, nmatched);

		acbm_clean(ptree);
		printf("%d\n", nmatched);
		//system("pause");
	}
	fclose(fp);
	return nmatched;
}