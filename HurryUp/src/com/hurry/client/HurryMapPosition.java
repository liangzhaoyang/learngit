package com.hurry.client;

import java.util.Timer;
import java.util.TimerTask;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.XMPPException;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.InfoWindow.OnInfoWindowClickListener;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.hurry.client.util.MyApplication;
import com.hurry.client.util.SharedPreferencesHelper;
import com.hurry.client.util.XmppTool;



public class HurryMapPosition extends Activity{
	// 地图相关
	private MapView mMapView = null; // 地图View
	private BaiduMap  mBaiduMap;
	private LocationClient mLocationClient = null;
	LatLng nearPos=null;
	//LocationData locData = null;
	boolean isFirstLoc = true;
	//数据存储相关变量
	private SharedPreferencesHelper sp;
	public final static String POS_LONGITUDE="longitude";
	public final static String POS_LATITUDE="latitude";
	
	private LayoutInflater inflater=null;
	private View view=null;
	private MyApplication app;
	private MediaPlayer mediaPlayer;
	private Button hurryBtn,easyBtn;
	//定义催促信息指令
	public final static String HURRY_INFO="##hurry##";
	public final static String NO_HURRY_INFO="##no hurry##";
	public final static String HURRY_HEAD_INFO="##head is clicked##";
	
	private final String BROADCAST_HURRY_INFO="action.hurry.info";
	private final String FRIEND_ID="friend_id";
	private final String FRIEND_MESSAGE="friend_message";
	    
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		app=(MyApplication)getApplication();
		app.addActivity(this);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		SDKInitializer.initialize(getApplicationContext());  
		setContentView(R.layout.hurry_map);
		
		//注册一个广播
		IntentFilter intentFilter = new IntentFilter();  
	      intentFilter.addAction(BROADCAST_HURRY_INFO);  
	      registerReceiver(mRefreshBroadcastReceiver, intentFilter);
		//定义存储当前位置的变量
		sp=new SharedPreferencesHelper(this,"positionInfo");
		
		//绘制当前用户的信息窗
        inflater=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
		view=inflater.inflate(R.layout.map_windowinfo, null);
		view.setMinimumHeight(150);
		view.setMinimumWidth(200);
		
		// 地图初始化
		mMapView = (MapView) findViewById(R.id.bmapsView);
		mBaiduMap=mMapView.getMap();
		//实现位置的定位			
		mLocationClient = new LocationClient(this); 
        mBaiduMap = mMapView.getMap();
		// 开启定位图层
		mBaiduMap.setMyLocationEnabled(true);
        LocationClientOption option = new LocationClientOption(); 
        option.setOpenGps(true); 
        option.setAddrType("all");
        option.setCoorType("bd09ll");                           //设置坐标类型为bd09ll 
        option.setPoiExtraInfo(true);
        option.setPriority(LocationClientOption.NetWorkFirst);  //设置网络优先 
        //option.setProdName("locSDKDemo2");                      //设置产品线名称 
        option.setScanSpan(5000);                               //定时定位，每隔5秒钟定位一次。 
        mLocationClient.setLocOption(option); 
        mLocationClient.registerLocationListener(new BDLocationListener() { 
            @Override 
            public void onReceiveLocation(BDLocation location) { 
            	
            	if (location == null || mMapView == null)
    				return;
    			MyLocationData locData = new MyLocationData.Builder()
    					.accuracy(location.getRadius())
    					// 此处设置开发者获取到的方向信息，顺时针0-360
    					.direction(100).latitude(location.getLatitude())
    					.longitude(location.getLongitude()).build();
    			mBaiduMap.setMyLocationData(locData);
    			if (isFirstLoc) {
    				isFirstLoc = false;
    				String citystr=location.getAddrStr();
    				sp.putValue(POS_LONGITUDE, location.getLongitude()+"");
	    			sp.putValue(POS_LATITUDE, location.getLatitude()+"");
	            	Toast.makeText(HurryMapPosition.this, citystr, Toast.LENGTH_LONG)
					.show();
	            	//定位到当前所在位置
	            	LatLng ll = new LatLng(location.getLatitude(),
    						location.getLongitude());
	    			MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
    				mBaiduMap.animateMapStatus(u);
    				
    				//nearPos=ll;
    				/*
    				//绘制当前用户的信息窗
    		        inflater=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
    				view=inflater.inflate(R.layout.map_windowinfo, null);
    				view.setMinimumHeight(150);
    				view.setMinimumWidth(200);
    				initPositionInfo(view,ll);
    				*/
    				//
    				LatLng objPos = new LatLng(location.getLatitude()+0.1,
    						location.getLongitude()+0.1);
    				//initPositionInfo(view,objPos);
    		
    				nearPos=objPos;
    				//构建Marker图标
    		        BitmapDescriptor bitmap = BitmapDescriptorFactory
    		            .fromResource(R.drawable.icon_marka);
    		        //构建MarkerOption，用于在地图上添加Marker
    		        OverlayOptions option1 = new MarkerOptions()
    		            .position(objPos)
    		            .icon(bitmap);
    		        //在地图上添加Marker，并显示
    		        mBaiduMap.addOverlay(option1);
    		       
    				
    			}
    			
            } 
             
            public void onReceivePoi(BDLocation location){ 
                //return ; 
            	String citystr=location.getCity()+location.getProvince()+location.getCityCode();
            	Toast.makeText(HurryMapPosition.this, citystr, Toast.LENGTH_LONG)
				.show();
            } 
        }); 
        mLocationClient.start();
        //为marker标签添加监听事件
        mBaiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {
            public boolean onMarkerClick(Marker arg0) {
            	/*Toast.makeText(HurryMapPosition.this, "hello", Toast.LENGTH_LONG)
				.show();
            	*/
            	setPositionInfo(arg0.getPosition());
		       // arg0.setIcon(bitmap);
            	//arg0.setTitle("A");
                return true;
            }
        });
        
        //为快来按钮添加监听事件
        hurryBtn=(Button)this.findViewById(R.id.map_hurry);
        hurryBtn.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
				 //
				sendHurryInfo(HURRY_INFO);
			}
        });
        easyBtn=(Button)this.findViewById(R.id.map_nohurry);
        easyBtn.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
				 //
				sendHurryInfo(NO_HURRY_INFO);
			}
        });
        
	}
	//设置好友所在位置
	public void setPositionInfo(LatLng postion)
	{
		if(view==null)
		{
			//绘制当前用户的信息窗
	        inflater=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
			view=inflater.inflate(R.layout.map_windowinfo, null);
			view.setMinimumHeight(150);
			view.setMinimumWidth(200);
		}
		((LinearLayout)this.findViewById(R.id.map_userinfo_layout)).setVisibility(View.VISIBLE);
		((TextView)this.findViewById(R.id.map_username)).setText(app.currentTalkObj);
		//创建InfoWindow  
        InfoWindow mInfoWindow = new InfoWindow(view, postion, listener);  
        //显示InfoWindow  
        mBaiduMap.showInfoWindow(mInfoWindow); 
	}
	
	//创建InfoWindow的点击事件监听者  
    private OnInfoWindowClickListener listener = new OnInfoWindowClickListener() {  
        public void onInfoWindowClick() {  
        //添加点击后的事件响应代码  
        	sendHurryInfo(HURRY_HEAD_INFO);
        	if(nearPos!=null)
        		setPositionInfo(nearPos);
        	/*
        Toast.makeText(HurryMapPosition.this, "hello", Toast.LENGTH_LONG)
			.show();
        	*/
        }  
    };  
    //
    public void sendHurryInfo(String info)
    {
    	//创建与当前点击人员会话
    	Chat newchat = XmppTool.getConnection().getChatManager().createChat(app.currentTalkObj+"@hnu-pc", null);
    	try {
			newchat.sendMessage(info);
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	//获取所有用户的位置信息
	public void getUserPosition()
	{
		
	}
	
	@Override
	protected void onPause() {
		mMapView.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		mMapView.onResume();
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		mMapView.onDestroy();
		unregisterReceiver(mRefreshBroadcastReceiver);
		super.onDestroy();
	}
	
	// 监听是否数据库数据发生变化，刷新页面  
	protected BroadcastReceiver mRefreshBroadcastReceiver = new BroadcastReceiver() {  
	  
	      @Override  
	      public void onReceive(Context context, Intent intent) {  
	          String action = intent.getAction();
	          String userName=intent.getStringExtra(FRIEND_ID);
	          String[] userId=userName.split("@");
	          String info=intent.getStringExtra(FRIEND_MESSAGE);
	          
	          if (action.equals(BROADCAST_HURRY_INFO))  
	          {  
	        	  //updateList(intent.getStringExtra(FRIEND_ID),intent.getStringExtra(FRIEND_MESSAGE));
	        	  	Log.v("--tags--", "--tags-form--"+intent.getStringExtra(FRIEND_ID));
					Log.v("--tags--", "--tags-message--"+intent.getStringExtra(FRIEND_MESSAGE));
					if(info.equals(HURRY_INFO))
					{
						//语音推送
						mediaPlayer = MediaPlayer.create(HurryMapPosition.this,R.raw.hurry);
						mediaPlayer.start();
					}
					else if(info.equals(NO_HURRY_INFO))
					{
						//语音推送
						mediaPlayer = MediaPlayer.create(HurryMapPosition.this,R.raw.nohurry);
						mediaPlayer.start();
					}
					else if(info.equals(HURRY_HEAD_INFO))
					{
						if(view!=null)
						{	
							((ImageView)view.findViewById(R.id.map_window_head)).setImageDrawable(getResources().getDrawable(R.drawable.profile));
							//重绘
							setPositionInfo(nearPos);
						
						}
					}
					setMyTimer(10);
	          }  
	      }  
	  }; 
	  //
	  private Timer  timer;
	  public void setMyTimer(int second)
	  {
		  // 定义计时器   
			timer = new Timer();   
          // 定义计划任务，根据参数的不同可以完成以下种类的工作：在固定时间执行某任务，在固定时间开始重复执行某任务，重复时间间隔可控，在延迟多久后执行某任务，在延迟多久后重复执行某任务，重复时间间隔可控   
          timer.schedule(new TimerTask() {   
              // TimerTask 是个抽象类,实现的是Runable类   
              @Override  
              public void run() {   
                  Log.i("TIMER:", Thread.currentThread().getName());   
                  //定义一个消息传过去   
                  Message msg = new Message();   
                  msg.what = 1;   
                  handler.sendMessage(msg);   
              }   

          }, second*1000, 200);
	  }
	// 定义Handler   
      final Handler handler = new Handler() {   

          @Override  
          public void handleMessage(Message msg) {   
              super.handleMessage(msg);   
              //handler处理消息   
              ((ImageView)view.findViewById(R.id.map_window_head)).setImageDrawable(getResources().getDrawable(R.drawable.h001));
              setPositionInfo(nearPos);
              ((LinearLayout)HurryMapPosition.this.findViewById(R.id.map_userinfo_layout)).setVisibility(View.GONE);
              timer.cancel(); 
          }   
      };   
}
