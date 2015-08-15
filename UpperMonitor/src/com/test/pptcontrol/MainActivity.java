package com.test.pptcontrol;
/****
 * 定义一个PPT操作界面，可以使用手动控制和语音控制
 */
import java.io.IOException;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iflytek.cloud.speech.RecognizerResult;
import com.iflytek.cloud.speech.SpeechConstant;
import com.iflytek.cloud.speech.SpeechError;
import com.iflytek.cloud.speech.SpeechListener;
import com.iflytek.cloud.speech.SpeechUser;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.msc.util.JsonParser;

public class MainActivity extends Activity implements OnTouchListener,OnGestureListener{

	private Button start;
	private Button escape;
	private Button forward;
	private Button back;
	private Button iatButton;
	ImageButton settingButton;
	
	private final static int RIGHT = 1;
	private final static int LEFT = 2;
	private final static int SHIFTF5 = 0;
	private final static int ESC = 3;
	
	private Handler mHandler;
	//private MyApplication app;
	//
	//日志TAG.
	private static final String TAG = "IatDemoActivity";
	// Tip
	private Toast mToast;
	//识别结果显示
	private String mResultText;
	private String ipAddress;
	private String mPort;
	//识别窗口
	private RecognizerDialog iatDialog;
	//缓存，保存当前的引擎参数到下一次启动应用程序使用.
	private SharedPreferences mSharedPreferences;
	//
	GestureDetector gd;
	final int FLING_MIN_DISTANCE = 100, FLING_MIN_VELOCITY = 200; 
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//用户自定义窗体显示状态
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.main);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);
		//获取Application对象
		//app=(MyApplication)getApplication();
		((TextView) findViewById(R.id.titlebar_text)).setText(R.string.text_iat_demo);
		
		LinearLayout viewSnsLayout=(LinearLayout)findViewById(R.id.viewSnsLayout);
		//保证能够触发触屏事件
		viewSnsLayout.setOnTouchListener(this);
		viewSnsLayout.setLongClickable(true);
		//
		new CustomThread().start();//新建并启动CustomThread实例
		//检查是否有网络
		checkNetwork();
		//用户登录
		SpeechUser.getUser().login(MainActivity.this, null, null
				, "appid=" + getString(R.string.app_id), listener);
		//定义PPT控制的四个按钮
		 start = (Button)this.findViewById(R.id.start);
        escape = (Button)this.findViewById(R.id.escape);
        forward = (Button)this.findViewById(R.id.froward);
        back = (Button)this.findViewById(R.id.back);
        //
		mToast = Toast.makeText(this, "", Toast.LENGTH_LONG);	
		//初始化缓存对象.
		mSharedPreferences = getSharedPreferences(getPackageName(),
						MODE_PRIVATE);
		//定义一个手势识别类对象
		gd = new GestureDetector((OnGestureListener)this);
		//“识别”按钮初始化
		iatButton = (Button) findViewById(R.id.speech);	
		//听写结果设置
		//mResultText = (EditText) findViewById(R.id.txt_result);
		iatButton.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
			//显示语音听写Dialog.
			showIatDialog();			
			
			}
        });
		iatButton.setText(R.string.text_iat);
		//
		//“设置”按钮初始化
		settingButton=(ImageButton)findViewById(R.id.tts_setting_btn);
		settingButton.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
			//跳转到设置界面.
			Intent localIntent = new Intent();
			localIntent.setClass(MainActivity.this, SpeechControl.class);
			startActivity(localIntent);				
			}
        });
		//初始化听写Dialog,如果只使用有UI听写功能,无需创建SpeechRecognizer
		iatDialog =new RecognizerDialog(this);
		//为PPT播放按钮添加监听事件
        start.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				String str ="start";
	            Log.d("Test", "MainThread is ready to send msg:" + str);
	            mHandler.obtainMessage(SHIFTF5, str).sendToTarget();//发送消息到CustomThread实例  
			}
        	
        });
        //为PPT退出按钮添加监听事件
        escape.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				//mHandler=app.getHandler();
				String str ="esc";
	            Log.d("Test", "MainThread is ready to send msg:" + str);
	            mHandler.obtainMessage(ESC, str).sendToTarget();//发送消息到CustomThread实例  
			}
        });
        //为PPT下翻按钮添加监听事件
        forward.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				//mHandler=app.getHandler();
				String str ="RIGHT";
	            Log.d("Test", "MainThread is ready to send msg:" + str);
	            mHandler.obtainMessage(RIGHT, str).sendToTarget();//发送消息到CustomThread实例  
				
			}
        	
        });
      //为PPT上翻按钮添加监听事件
        back.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				//mHandler=app.getHandler();
				String str ="LEFT";
	            Log.d("Test", "MainThread is ready to send msg:" + str);
	            mHandler.obtainMessage(LEFT, str).sendToTarget();//发送消息到CustomThread实例  
			}
        });
    }
	
	/*********************************
	 * 添加触屏函数
	 */
	public boolean onTouch(View v, MotionEvent event) {  
        // TODO Auto-generated method stub  
        return gd.onTouchEvent(event);  
    }
	/***********
	 * 定义手势识别函数
	 */
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,  
            float velocityY) {  
        // TODO Auto-generated method stub  
        if(e1.getX() - e2.getX() > FLING_MIN_DISTANCE&&Math.abs(velocityX) > FLING_MIN_VELOCITY)  
        {  
            Intent intent = new Intent(MainActivity.this,Music.class);  
            startActivity(intent);  
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
             //Toast.makeText(this, "向左手势", Toast.LENGTH_SHORT).show();   
  
        }  
        /*else
        if (e2.getX()-e1.getX() > FLING_MIN_DISTANCE && Math.abs(velocityX) >FLING_MIN_VELOCITY) {  
              
            //切换Activity  
            Intent intent = new Intent(MainActivity.this, Music.class);  
            startActivity(intent);  
            //Toast.makeText(this, "向右手势", Toast.LENGTH_SHORT).show();  
        }  
          */
        return false;  
    }  
	/**
	 * 显示听写对话框.
	 * @param
	 */
	public void showIatDialog()
	{
		if(null == iatDialog) {
		//初始化听写Dialog	
		iatDialog =new RecognizerDialog(this);
		}
		//清空Grammar_ID，防止识别后进行听写时Grammar_ID的干扰
		iatDialog.setParameter(SpeechConstant.CLOUD_GRAMMAR, null);
		//设置听写Dialog的引擎
		iatDialog.setParameter(SpeechConstant.DOMAIN, "iat");
		//设置采样率参数，支持8K和16K 
		String rate =mSharedPreferences.getString(
				getString(R.string.preference_key_iat_rate),
				getString(R.string.preference_default_iat_rate));
				
		if(rate.equals("rate8k"))
		{
			iatDialog.setParameter(SpeechConstant.SAMPLE_RATE, "8000");
		}
		else 
		{
			iatDialog.setParameter(SpeechConstant.SAMPLE_RATE, "16000");
		}
		//iatDialog.setParameter(SpeechConstant.SAMPLE_RATE, "8000");
		//清空结果显示框.
		mResultText="";
		//显示听写对话框
		iatDialog.setListener(recognizerDialogListener);
		iatDialog.show();
		showTip(getString(R.string.text_iat_begin));
	}
	//推送消息
	private void showTip(String str)
	{
		if(!TextUtils.isEmpty(str))
		{
			mToast.setText(str);
			mToast.show();
		}
	}
	//
	/**
	 * 识别回调监听器
	 */
	RecognizerDialogListener recognizerDialogListener=new RecognizerDialogListener()
	{
		@Override
		public void onResult(RecognizerResult results, boolean isLast) {
			String text = JsonParser.parseIatResult(results.getResultString());
			mResultText=text.trim();
			mResultText.replaceAll("\\p{P}", "");
			if(!mResultText.equals(""))
			{
			showTip(mResultText);
			//mHandler=app.getHandler();
			if(mResultText.indexOf("播放")>-1)
				mHandler.obtainMessage(SHIFTF5, mResultText).sendToTarget();
			else if(mResultText.indexOf("退出")>-1)
				mHandler.obtainMessage(ESC, mResultText).sendToTarget();
			else if(mResultText.indexOf("上一页")>-1)
				mHandler.obtainMessage(LEFT, mResultText).sendToTarget();
			else if(mResultText.indexOf("下一页")>-1)
				mHandler.obtainMessage(RIGHT, mResultText).sendToTarget();
			else
				showTip("输入指令错误，请重新输入！");
			}
			//mResultText.append(text);
			//mResultText.setSelection(mResultText.length());
		}

		/**
		 * 识别回调错误.
		 */
		public void onError(SpeechError error) {
			
		}
		
	};
	//
	/**
	 * 重写Activity的onStart方法
	 * @param
	 */
		@Override
	protected void onStop() {
		mToast.cancel();
		if (null != iatDialog) {
			iatDialog.cancel();
		}
		super.onStop();
	}
	
	/**
	 * 用户登录回调监听器.
	 */
	private SpeechListener listener = new SpeechListener()
	{

		@Override
		public void onData(byte[] arg0) {
		}

		@Override
		public void onCompleted(SpeechError error) {
			if(error != null) {
				Toast.makeText(MainActivity.this, getString(R.string.text_login_fail)
						, Toast.LENGTH_SHORT).show();
				
			}			
		}

		@Override
		public void onEvent(int arg0, Bundle arg1) {
		}		
	};
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
					System.exit(0);
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
	//设定网络连接优先级
	public void setPreferredNetwork(int networkType) {
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		if (networkType == ConnectivityManager.TYPE_WIFI) {//设为2G/3G网络优先，就算wifi连接到AP，系统仍然通过2G/3G访问网络
			connMgr.setNetworkPreference(0);
		} else if (networkType == ConnectivityManager.TYPE_MOBILE) {
			connMgr.setNetworkPreference(1);
		}
	}
	//
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
	                    ComponentName component = new ComponentName("com.test.pptcontrol","com.test.pptcontrol.WirelessSettings");
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
	//

	 class CustomThread extends Thread {
			 //
	         String str="";
	        @Override
	        public void run() {

	            //建立消息循环的步骤

	            Looper.prepare();//1、初始化Looper

	            mHandler = new Handler(){//2、绑定handler到CustomThread实例的Looper对象

	                public void handleMessage (Message msg) {//3、定义处理消息的方法

	                    switch(msg.what) {

	                    case SHIFTF5:
	                        Log.d("Test", "CustomThread receive msg:" + (String) msg.obj);
	                        str=""+SHIFTF5;
	                        break;
	                    case RIGHT:
	                        Log.d("Test", "CustomThread receive msg:" + (String) msg.obj);
	                        str=""+RIGHT;
	                        break;
	                    case LEFT:
	                        Log.d("Test", "CustomThread receive msg:" + (String) msg.obj);
	                        str=""+LEFT;
	                        break;
	                    case ESC:
	                        Log.d("Test", "CustomThread receive msg:" + (String) msg.obj);
	                        str=""+ESC;
	                        break;
	                    }	                    
                        //
                        DatagramSocket ds;
                        ipAddress=mSharedPreferences.getString(
                				getString(R.string.set_ip),
                				getString(R.string.ip_default));
                        mPort=mSharedPreferences.getString(
                				getString(R.string.set_port),
                				getString(R.string.port_default));
                        try {
                            //套接字
                            ds = new DatagramSocket();                           
                            //构建数据报（内容+地址）
                            DatagramPacket dp = new DatagramPacket(str.getBytes(),str.length(),InetAddress.getByName(ipAddress),Integer.parseInt(mPort));//"192.168.185.1"
                            //把数据报发送出去
                            ds.send(dp);
                            //关闭套接字
                            ds.close();
                            
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

	                }

	            };

	            Looper.loop();//4、启动消息循环

	        }

	    }
	    
	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
