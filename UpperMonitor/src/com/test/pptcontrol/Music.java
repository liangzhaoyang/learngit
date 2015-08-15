package com.test.pptcontrol;
/**
 * 定义一个音乐显示界面，用于显示歌曲列表和控制播放
 */
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;

import com.iflytek.cloud.speech.RecognizerResult;
import com.iflytek.cloud.speech.SpeechConstant;
import com.iflytek.cloud.speech.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.msc.util.JsonParser;
import com.test.pptcontrol.R.id;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Music extends Activity implements OnTouchListener,OnGestureListener{
	/** Called when the activity is first created. */
	//
	private GestureDetector gd;
	private MyApplication app;
	final int FLING_MIN_DISTANCE = 100, FLING_MIN_VELOCITY = 20; 
	//
	private ListView list;
	//DBAdapter db;
	private ImageButton SetUpButton;
	private ImageButton forwardButton;
	private ImageButton playButton;
	private ImageButton backButton;
	private ImageButton soundButton;
	
	private boolean playCheck;
	private boolean alarmCheck;
	private boolean lightCheck;
	//
	private Handler mHandler;
	private String ipAddress;
	private String mPort;
	//缓存，保存当前的引擎参数到下一次启动应用程序使用.
	private SharedPreferences mSharedPreferences;
	final private String song[]={"葫芦娃","茉莉花"};
	//识别窗口
	private RecognizerDialog iatDialog;
	private Toast mToast;
	private String mResultText;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.music);
		//更改窗体样式
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.musicbar);
		
		new CustomThread().start();//新建并启动CustomThread实例
		//初始化缓存对象.
		mSharedPreferences = getSharedPreferences(getPackageName(),
						MODE_PRIVATE);
		
		LinearLayout viewSnsLayout=(LinearLayout)findViewById(R.id.musicview);
		//保证能够触发触屏事件
		viewSnsLayout.setOnTouchListener(this);
		viewSnsLayout.setLongClickable(true);
		//获取Application对象
		app=(MyApplication)getApplication();
		gd = new GestureDetector((OnGestureListener)this);
		//
		mToast = Toast.makeText(this, "", Toast.LENGTH_LONG);
		list=(ListView) findViewById(R.id.users);
		 //db= new DBAdapter(this);
		
		alarmCheck=true;
		lightCheck=true;
		//注册一个广播
		IntentFilter intentFilter = new IntentFilter();  
	      intentFilter.addAction("action.refreshList");  
	      registerReceiver(mRefreshBroadcastReceiver, intentFilter);  
	      //添加adapter适配器
		SimpleAdapter useradapter = new SimpleAdapter(this, app.getData(),
				R.layout.user, new String[] { "img", "code","singer", "name" },
				new int[] { R.id.img,R.id.code, R.id.singer,
						R.id.name});
		list.setAdapter(useradapter);
		
		//“设置”按钮初始化
		SetUpButton=(ImageButton)findViewById(R.id.music_setting_btn);
		SetUpButton.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
			//跳转到设置界面.
			Intent localIntent = new Intent();
			localIntent.setClass(Music.this, SQLSong.class);
			startActivity(localIntent);				
			}
        });
		//
		forwardButton=(ImageButton)findViewById(R.id.music_forward);
		forwardButton.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
			//跳转到设置界面.
				mHandler.obtainMessage(207, "weizhi").sendToTarget();			
			}
        });
		//
		playCheck=false;
		playButton=(ImageButton)findViewById(R.id.music_start);
		playButton.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
				//跳转到设置界面.
				if(playCheck)
				{
					mHandler.obtainMessage(200, "weizhi").sendToTarget();
					playButton.setImageDrawable(getResources().getDrawable(R.drawable.play));
				}
				else
				{
					mHandler.obtainMessage(201, "weizhi").sendToTarget();
					playButton.setImageDrawable(getResources().getDrawable(R.drawable.pause));
				}
				playCheck=!playCheck;
			}
        });
		//
		backButton=(ImageButton)findViewById(R.id.music_next);
		backButton.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
			//跳转到设置界面.
				mHandler.obtainMessage(206, "weizhi").sendToTarget();			
			}
        });
		//
		//初始化听写Dialog,如果只使用有UI听写功能,无需创建SpeechRecognizer
		iatDialog =new RecognizerDialog(this);
		soundButton=(ImageButton)findViewById(R.id.music_sound);
		soundButton.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
				//显示语音听写Dialog.
				showIatDialog();		
			}
        });
		//为listview的每一项添加监听事件
		list.setOnItemClickListener(new OnItemClickListener()
		{
		       @Override
		      public void onItemClick(AdapterView<?> parent, View view, int position, long id)
		      {
		    	   ListView listView = (ListView)parent;
		    	    HashMap<String, String> map = (HashMap<String, String>) listView.getItemAtPosition(position);
		    	    String userid = map.get("code");
		    	    String singer = map.get("singer");
		    	    String name = map.get("name");
		    	    //Toast.makeText(Music.this, userid +" , "+ singer +" , "+ name ,Toast.LENGTH_LONG).show();
		    	    if(name.equals("葫芦娃"))
		    	    	mHandler.obtainMessage(1, "weizhi").sendToTarget();
		    	    else if(name.equals("茉莉花"))
		    	    	mHandler.obtainMessage(0, "weizhi").sendToTarget();
		    	    else if(name.equals("丢手绢"))
		    	    	mHandler.obtainMessage(2, "weizhi").sendToTarget();
		    	    else if(name.equals("读书郎"))
		    	    	mHandler.obtainMessage(3, "weizhi").sendToTarget();
		    	    else if(name.equals("两只老虎"))
		    	    	mHandler.obtainMessage(4, "weizhi").sendToTarget();
		    	    else if(name.equals("新年好"))
		    	    	mHandler.obtainMessage(5, "weizhi").sendToTarget();
		    	    else
		    	    	Toast.makeText(Music.this, "您所点击的歌曲单片机里面没有，请重新选择！" ,Toast.LENGTH_LONG).show();
		    }
		});
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
		//mResultText="";
		//显示听写对话框
		iatDialog.setListener(recognizerDialogListener);
		iatDialog.show();
		showTip(getString(R.string.text_iat_begin));
	}
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
				//
				if(mResultText.indexOf("葫芦娃")>-1)
	    	    	mHandler.obtainMessage(1, "weizhi").sendToTarget();
	    	    else if(mResultText.indexOf("茉莉花")>-1)
	    	    	mHandler.obtainMessage(0, "weizhi").sendToTarget();
	    	    else if(mResultText.indexOf("丢手绢")>-1)
	    	    	mHandler.obtainMessage(2, "weizhi").sendToTarget();
	    	    else if(mResultText.indexOf("读书郎")>-1)
	    	    	mHandler.obtainMessage(3, "weizhi").sendToTarget();
	    	    else if(mResultText.indexOf("两只老虎")>-1)
	    	    	mHandler.obtainMessage(4, "weizhi").sendToTarget();
	    	    else if(mResultText.indexOf("新年好")>-1)
	    	    	mHandler.obtainMessage(5, "weizhi").sendToTarget();
	    	    else if(mResultText.indexOf("上一首")>-1)
	    	    	mHandler.obtainMessage(207, "weizhi").sendToTarget();
	    	    else if(mResultText.indexOf("下一首")>-1)
	    	    	mHandler.obtainMessage(206, "weizhi").sendToTarget();
	    	    else if(mResultText.indexOf("播放")>-1)
	    	    	{
	    	    	mHandler.obtainMessage(200, "weizhi").sendToTarget();
					playButton.setImageDrawable(getResources().getDrawable(R.drawable.play));    	    	
	    	    	}
	    	    else if(mResultText.indexOf("暂停")>-1)
	    	    	{
	    	    	mHandler.obtainMessage(201, "weizhi").sendToTarget();
					playButton.setImageDrawable(getResources().getDrawable(R.drawable.pause));    	    	
	    	    	}
	    	    else
	    	    	Toast.makeText(Music.this, "您所选择的歌曲单片机里面没有，请重新选择！" ,Toast.LENGTH_LONG).show();
			}
		}

		/**
		 * 识别回调错误.
		 */
		public void onError(SpeechError error) {
			
		}
		
	};
	//推送消息
	private void showTip(String str)
	{
		if(!TextUtils.isEmpty(str))
		{
			mToast.setText(str);
			mToast.show();
		}
	}
	
	public boolean onTouch(View v, MotionEvent event) {  
        // TODO Auto-generated method stub  
        return gd.onTouchEvent(event);  
    }
	/***********
	 * 
	 */
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,  
            float velocityY) {  
        // TODO Auto-generated method stub  
        if (e2.getX()-e1.getX() > FLING_MIN_DISTANCE && Math.abs(velocityX) >FLING_MIN_VELOCITY) {  
              
            //切换Activity  
            Intent intent = new Intent(Music.this, MainActivity.class);  
            startActivity(intent);  
            //Toast.makeText(this, "向右手势", Toast.LENGTH_SHORT).show();  
        }  
          
        return false;  
    }
	//
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	 @Override
	    public boolean onMenuItemSelected(int featureId, MenuItem item) {
	        switch (item.getItemId()) {
	        case id.buzzer:
	        	if(alarmCheck)
	        		mHandler.obtainMessage(202, "weizhi").sendToTarget();
	        	else
	        		mHandler.obtainMessage(203, "weizhi").sendToTarget();
	        	alarmCheck=!alarmCheck;
	            //Toast.makeText(getApplicationContext(), string.itemAddSelect, Toast.LENGTH_SHORT).show();
	            break;
	        case id.light:
	        	if(lightCheck)
	        		mHandler.obtainMessage(204, "weizhi").sendToTarget();
	        	else
	        		mHandler.obtainMessage(205, "weizhi").sendToTarget();
	        	lightCheck=!lightCheck;
	            //Toast.makeText(getApplicationContext(), string.itemEditSelect, Toast.LENGTH_SHORT).show();
	            break;
	        case id.about:
	            //Toast.makeText(getApplicationContext(), string.itemDelSelect, Toast.LENGTH_SHORT).show();
	        	Toast.makeText(Music.this, "被骗了吧，这里没有关于，哈哈！" ,Toast.LENGTH_LONG).show();
	            break;       
	        }        
	        return false;
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
	 @Override
	protected void onDestroy() {
	        super.onDestroy();
	        unregisterReceiver(mRefreshBroadcastReceiver);
	    }
	 
	 protected void onStop() {
			mToast.cancel();
			if (null != iatDialog) {
				iatDialog.cancel();
			}
			super.onStop();
		}
	 
	 class CustomThread extends Thread {
		 //
         String str="";
        @Override
        public void run() {

            //建立消息循环的步骤

            Looper.prepare();//1、初始化Looper

            mHandler = new Handler(){//2、绑定handler到CustomThread实例的Looper对象

                public void handleMessage (Message msg) {//3、定义处理消息的方法
                	str=""+msg.what;
                	/*	
                    switch(msg.what) {

                    case 0:
                        Log.d("Test", "CustomThread receive msg:" + (String) msg.obj);
                        str=""+0;
                        break;
                    case 1:
                        Log.d("Test", "CustomThread receive msg:" + (String) msg.obj);
                        str=""+1;
                        break;
                    case 2:
                        Log.d("Test", "CustomThread receive msg:" + (String) msg.obj);
                        str=""+2;
                        break;
                    case 3:
                        Log.d("Test", "CustomThread receive msg:" + (String) msg.obj);
                        str=""+3;
                        break;
                    }
                    */
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
    
	//定义一个刷新函数
	 private void refresh() {
	        finish();
	        Intent intent = new Intent(Music.this, Music.class);
	        startActivity(intent);
	    }
	// 监听是否数据库数据发生变化，刷新页面  
	  private BroadcastReceiver mRefreshBroadcastReceiver = new BroadcastReceiver() {  
	  
	      @Override  
	      public void onReceive(Context context, Intent intent) {  
	          String action = intent.getAction();  
	          if (action.equals("action.refreshList"))  
	          {  
	        	  refresh();  
	          }  
	      }  
	  };  
	 
}
