package com.hurry.client;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hurry.client.util.MyApplication;
import com.hurry.client.util.SharedPreferencesHelper;
import com.hurry.client.util.XmppTool;


public class HurryLogin extends Activity implements OnClickListener{
	//定义UI变量
	private EditText useridText, pwdText;
	private CheckBox saveInfo;
	private LinearLayout layout1, layout2;
	//保存每次登录的信息
	private SharedPreferencesHelper sp;
	public final static String USER_ACCOUNT="account";
	public final static String USER_PSD="password";
	//定义注册字符
	private String s="No access? Sign Up";
	private TextView mClickableText;
	//
	private MyApplication app;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.hurry_login);
		this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
		//获取Application对象,添加活动到列表中
		app=(MyApplication)getApplication();
		app.addActivity(this);
		
		//检查是否网络已经开启
		checkNetwork();
		//获取用户和密码
		this.useridText = (EditText) findViewById(R.id.login_edit_account);
		this.pwdText = (EditText) findViewById(R.id.login_edit_pwd);
		saveInfo=(CheckBox)findViewById(R.id.login_cb_savepwd);
		//获取保存的密码和用户名
		sp=new SharedPreferencesHelper(this,"userInfo");
		useridText.setText(sp.getValue(USER_ACCOUNT));
		pwdText.setText(sp.getValue(USER_PSD));				
		
		//正在登录
		this.layout1 = (LinearLayout) findViewById(R.id.login_layout1);
		//登录界面
		this.layout2 = (LinearLayout) findViewById(R.id.login_layout2);
		//为登陆按钮添加监听事件
		Button btLogin = (Button) findViewById(R.id.login_btn_login);
		btLogin.setOnClickListener(this);
		//为“注册”文本添加监听事件
		mClickableText=(TextView)this.findViewById(R.id.login_signUp);
		mClickableText.setText(getClickableSpan());
		mClickableText.setMovementMethod(LinkMovementMethod.getInstance());
	}
	
	@Override
	public void onClick(View v) {
		//根据ID来进行提交或者取消
		switch (v.getId()) {
		case R.id.login_btn_login:
			//取得填入的用户和密码
			final String USERID = this.useridText.getText().toString();
			final String PWD = this.pwdText.getText().toString();
			//保存当前登录的用户名和密码
			if(saveInfo.isChecked())
			{
				sp.putValue(USER_ACCOUNT, USERID);
				sp.putValue(USER_PSD, PWD);
				Log.i("login", "sharepreference保存成功");
			}
			
			Thread t=new Thread(new Runnable() {				
				public void run() {
					//sendEmptyMessage:发送一条消息
					handler.sendEmptyMessage(1);
					try {
						//连接
						XmppTool.getConnection().login(USERID, PWD);
						Log.i("XMPPClient", "Logged in as " + XmppTool.getConnection().getUser());
						
						//状态
						Presence presence = new Presence(Presence.Type.available);
						XmppTool.getConnection().sendPacket(presence);
						//保存当前用户的ID值到application
						app.setUserID(USERID);
						//跳转到主页面
						Intent intent = new Intent();
						intent.setClass(HurryLogin.this, HurryMainActivity.class);
						intent.putExtra("USERID", USERID);						
						HurryLogin.this.startActivity(intent);
						HurryLogin.this.finish();
					}
					catch (XMPPException e) 
					{
						XmppTool.closeConnection();
						
						handler.sendEmptyMessage(2);
						app.exit();
					}					
				}
			});
			t.start();
			break;
		}
	}
	
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg)
		{
			
			if(msg.what==1)
			{
				layout1.setVisibility(View.VISIBLE);
				layout2.setVisibility(View.GONE);
			}
			else if(msg.what==2)
			{
				layout1.setVisibility(View.GONE);
				layout2.setVisibility(View.VISIBLE);
				Toast.makeText(HurryLogin.this, "登录失败！",Toast.LENGTH_SHORT).show();
			}
		};
	};
	//定义注册字符点击跳转事件
	 private SpannableString getClickableSpan() {
		  View.OnClickListener l = new View.OnClickListener() {
		   //如下定义自己的动作
		   public void onClick(View v) {
		    //
		    Intent intent=new Intent();
			intent.setClass(HurryLogin.this, HurrySignUp.class);
			startActivity(intent);
		   }
		  };
	  // s=s.replaceAll("Sign Up", "<a  style=\"color:#ff0000;\"  href='Sign Up'>"  
		//	    + "Sign Up" + "</a>");
	  SpannableString spanableInfo = new SpannableString(s);
	  int start = 11;
	  int end = spanableInfo.length();
	  spanableInfo.setSpan(new Clickable(l), start, end,
	    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
	  return spanableInfo;
	 }
	 //定义点击类
	 class Clickable extends ClickableSpan implements OnClickListener {
		 private final View.OnClickListener mListener;

		 public Clickable(View.OnClickListener l) {
		  mListener = l;
		 }
		 public void onClick(View v) {
			  mListener.onClick(v);
			 }
	}
	 
	//后退按钮响应事件
		public boolean onKeyDown(int keyCode, KeyEvent event) 
	    {	
			if ( event.getKeyCode() == KeyEvent.KEYCODE_BACK){
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("退出应用");
				builder.setMessage("准备退出...");
				//builder.setIcon(R.drawable.stat_sys_warning);
				builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent startMain = new Intent(Intent.ACTION_MAIN);
						startMain.addCategory(Intent.CATEGORY_HOME); 
						startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
						startActivity(startMain);
						app.exit();
						XmppTool.closeConnection();
						//System.exit(0);
					}
				});
				builder.setNegativeButton("取消",new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}
				});
				builder.show();
			}
			return super.onKeyDown(keyCode, event);
		}
		
		/** 判断是否有网络 */
		private void checkNetwork() {
			boolean flag = false;
			ConnectivityManager cwjManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			if (cwjManager.getActiveNetworkInfo() != null)
				flag = cwjManager.getActiveNetworkInfo().isAvailable();
			if (!flag) {
				Builder b = new AlertDialog.Builder(this).setTitle("没有可用的网络").setMessage("请开启GPRS或WIFI网络连接");
				b.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						Intent intent=null;
		                //判断手机系统的版本  即API大于10 就是3.0或以上版本 
		                if(android.os.Build.VERSION.SDK_INT>10){
		                    intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
		                }else{
		                    intent = new Intent();
		                    ComponentName component = new ComponentName("com.hurry.client","com.hurry.client.WirelessSettings");
		                    intent.setComponent(component);
		                    intent.setAction("android.intent.action.VIEW");
		                }
		                startActivity(intent);
		            }
				}).setNeutralButton("取消", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						dialog.cancel();
					}
				}).create();
				b.show();
			}

			//return flag;
		}
	 /*
	 protected void onDestroy() {  
	        super.onDestroy();  
	        XmppTool.closeConnection();
	        app.exit();
	    }  
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hurry_login, menu);
		return true;
	}
	 @Override
	    public boolean onMenuItemSelected(int featureId, MenuItem item) {
	        switch (item.getItemId()) {
	        case com.hurry.client.R.id.about:
	            Toast.makeText(getApplicationContext(), "快来是一款地图定位手机交友app，用于情侣，旅游团，亲子，社团交流、户外活动等使用，快速精准定位对方位置（经对方许可），使使用双方不会因为等待而无聊和焦虑。", Toast.LENGTH_SHORT).show();
	            break;  
	    }
	        return false;
	 }
}
