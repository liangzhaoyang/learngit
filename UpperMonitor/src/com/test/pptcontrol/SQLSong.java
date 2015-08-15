package com.test.pptcontrol;
/**
 * 定义一个数据库增、删、改、查操作界面
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SQLSong extends Activity{
	//DBAdapter db;
	
	private Button SQLadd;
	private Button SQLmodify;
	private Button SQLdelete;
	private EditText singer;
	private EditText name;
	private EditText id;
	
	private Toast mToast;
	private MyApplication app;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.song);	
		//更改窗体样式
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.songbar);
		app=(MyApplication)getApplication();
		//
		mToast = Toast.makeText(this, "", Toast.LENGTH_LONG);	
		// 定义三个按钮：添加、更改和删除
		 SQLadd=(Button)findViewById(R.id.sql_add);
		 SQLmodify=(Button)findViewById(R.id.sql_modify);
		 SQLdelete=(Button)findViewById(R.id.sql_delete);
		 //定义三个EditText
		 singer=(EditText)findViewById(R.id.sql_singer);
		 name=(EditText)findViewById(R.id.sql_name);
		 id=(EditText)findViewById(R.id.sql_id);
		 //为添加按钮添加监听事件
		 SQLadd.setOnClickListener(new Button.OnClickListener(){

				@Override
				public void onClick(View v) {
					 String singerstr=singer.getText().toString(); 
					 String namestr=name.getText().toString(); 
					 //showTip(""+singerstr+","+namestr);
					 //String idstr=id.getText().toString();
					 if(app.insertSong(singerstr,namestr))
					 {
						 Toast.makeText(SQLSong.this, "添加成功！" ,Toast.LENGTH_LONG).show();
						 //showTip("添加成功！");
					 }
					 else
					 {
						 showTip("添加失败！");
					 }
					// 广播通知
			         Intent intent = new Intent();
			         intent.setAction("action.refreshList");
			         sendBroadcast(intent);
				}
	        	
	        });
		//为更改按钮添加监听事件
		 SQLmodify.setOnClickListener(new Button.OnClickListener(){

				@Override
				public void onClick(View v) {
					 String singerstr=singer.getText().toString(); 
					 String namestr=name.getText().toString(); 
					 String idstr=id.getText().toString();
					 if(idstr.equals(""))
					 {
						 Toast.makeText(SQLSong.this, "列表位置值不能为空，请重新输入！" ,Toast.LENGTH_LONG).show();
						 //showTip("列表位置值不能为空，请重新输入！");
					 }
					 else
					 {
						 idstr=""+app.getIndex(Integer.parseInt(idstr));
						 if(app.updataSong(idstr, singerstr, namestr))
						 {
							 Toast.makeText(SQLSong.this, "修改成功！" ,Toast.LENGTH_LONG).show();
							 //showTip("修改成功！");
						 }
						 else
						 {
							 showTip("修改失败！");
						 }
					 }
					// 广播通知
			         Intent intent = new Intent();
			         intent.setAction("action.refreshList");
			         sendBroadcast(intent);
				}
	        	
	        });
		//为删除按钮添加监听事件
		 SQLdelete.setOnClickListener(new Button.OnClickListener(){

				@Override
				public void onClick(View v) {
					 //String singerstr=singer.getText().toString(); 
					 //String namestr=name.getText().toString(); 
					 String idstr=id.getText().toString();
					 if(idstr.equals(""))
					 {
						 //showTip("列表位置值不能为空，请重新输入！");
						 Toast.makeText(SQLSong.this, "列表位置值不能为空，请重新输入！" ,Toast.LENGTH_LONG).show();
					 }
					 else
					 {
						 idstr=""+app.getIndex(Integer.parseInt(idstr));
						 if(app.deleteSong(idstr))
						 {
							 //showTip("删除成功！");
							 Toast.makeText(SQLSong.this, "删除成功！" ,Toast.LENGTH_LONG).show();
						 }
						 else
						 {
							 showTip("删除失败！");
						 }
					 }
					// 广播通知
			         Intent intent = new Intent();
			         intent.setAction("action.refreshList");
			         sendBroadcast(intent);
				}
	        	
	        });
	}
	//推送消息
	private void showTip(String str)
	{
		if(TextUtils.isEmpty(str))
		{
			mToast.setText(str);
			mToast.show();
		}
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) 
    {	
		if ( event.getKeyCode() == KeyEvent.KEYCODE_BACK){
			
		}
		return super.onKeyDown(keyCode, event);
	}
}
