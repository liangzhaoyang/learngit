package com.test.car;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TabActivity;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

public class MapShow extends TabActivity implements OnDateSetListener,OnTimeSetListener, OnClickListener{
	
	// 地图相关
	private ZoomControls zoomControls;
	private MapView mMapView = null; // 地图View
	private BaiduMap  mBaiduMap=null;
	private LocationClient mLocationClient = null,mLocClient;
	public MyLocationListenner myListener = new MyLocationListenner();
	boolean isFirstLoc = true;
	private PoiSearch  mPoiSearch;;
	private LatLng nearPos;
	
	private TabHost myTabhost;
	private TabWidget tabWidget;
	
	private SharedPreferencesHelper sp;
	public final static String POS_LONGITUDE="longitude";
	public final static String POS_LATITUDE="latitude";
	public final static String MAP_DATE="date";
	public final static String MAP_TIME="time";
	private final String LIST_STORE_INFO="list_store_info"; 
	
	private MyApplication app;
	
	private ImageView searchImg,orderConfirm,selectDes;
	private RelativeLayout maplayout,maplayout1;
	private TextView placeName,placeTel,orderBack;
	private EditText date,time,orderName,orderTel,placeStart,placeDes;
	
	private String mapShop="",mapTel="";
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		app=(MyApplication)getApplication();
		app.addActivity((Activity)this);
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		SDKInitializer.initialize(getApplicationContext());  
		initTab();		
		
		placeName=(TextView)this.findViewById(R.id.map_info_up);
		placeTel=(TextView)this.findViewById(R.id.map_info_down);
		orderBack=(TextView)this.findViewById(R.id.map_back);
		//
		orderName=(EditText)this.findViewById(R.id.map_account);
		orderTel=(EditText)this.findViewById(R.id.map_tel);
		date=(EditText)this.findViewById(R.id.map_date);
		time=(EditText)this.findViewById(R.id.map_time);
		placeStart=(EditText)this.findViewById(R.id.map_source);
		placeDes=(EditText)this.findViewById(R.id.map_des);
		//
		orderConfirm=(ImageView)this.findViewById(R.id.map_confirm);
		selectDes=(ImageView)this.findViewById(R.id.map_info_img);
				
		maplayout=(RelativeLayout)this.findViewById(R.id.map_info_layout);
		maplayout1=(RelativeLayout)this.findViewById(R.id.map_info_layout1);
		//获取保存的密码和用户名
		sp=new SharedPreferencesHelper(this,"positionInfo");
		
		// 地图初始化
		mMapView = (MapView) findViewById(R.id.bmapsView1);
		mBaiduMap=mMapView.getMap();
		zoomControls = (ZoomControls) mMapView.getChildAt(2); 
		zoomControls.hide();
		//调整缩放控件的位置  
        //zoomControls.setPadding(0, 0, 0, 100);  
        //zoomControls.setOrientation(1);
		// 开启定位图层
		mBaiduMap.setMyLocationEnabled(true);
		
		// 定位初始化
		mLocClient = new LocationClient(getApplicationContext());
		mLocClient.registerLocationListener(myListener);
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);// 打开gps
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setScanSpan(1000);
		option.setPoiExtraInfo(true);
		//查询地址信息必须使能的一个设置
		option.setAddrType("all");
		mLocClient.setLocOption(option);
		mLocClient.start();
		
		//搜索按钮跳转事件
		searchImg=(ImageView)this.findViewById(R.id.start_search);
		searchImg.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(MapShow.this, Search.class);
				//startActivity(intent);
				startActivityForResult(intent, 0);
			}
        });
		
		//
		date.setOnClickListener(this);
		time.setOnClickListener(this);
		orderBack.setOnClickListener(this);
		orderConfirm.setOnClickListener(this);
		//
		initDate();
		initAccount();
	}
	
	public void onClick(View v) {//普通按钮事件
		switch(v.getId())
		{
		case R.id.map_time:
			Calendar c = Calendar.getInstance(Locale.CHINA);
			//创建一个日历引用d，通过静态方法getInstance() 从指定时区 Locale.CHINA 获得一个日期实例
			int hour = c.get(Calendar.HOUR_OF_DAY);
			int minute = c.get(Calendar.MINUTE);
			
			//获得日历中的 year month day
			TimePickerDialog dlg1=new TimePickerDialog(this,this,hour,minute,true);
			//新建一个DatePickerDialog 构造方法中         
//			     （设备上下文，OnDateSetListener时间设置监听器，默认年，默认月，默认日）
			dlg1.show();
			//让DatePickerDialog显示出来
			break;
		case R.id.map_date:
			Calendar d = Calendar.getInstance(Locale.CHINA);
			//创建一个日历引用d，通过静态方法getInstance() 从指定时区 Locale.CHINA 获得一个日期实例
			Date myDate=new Date();
			//创建一个Date实例
			d.setTime(myDate);
			//设置日历的时间，把一个新建Date实例myDate传入
			int year=d.get(Calendar.YEAR);
			int month=d.get(Calendar.MONTH);
			int day=d.get(Calendar.DAY_OF_MONTH);
			//获得日历中的 year month day
			DatePickerDialog dlg=new DatePickerDialog(this,this,year,month,day);
			//新建一个DatePickerDialog 构造方法中         
//			     （设备上下文，OnDateSetListener时间设置监听器，默认年，默认月，默认日）
			dlg.show();
			//让DatePickerDialog显示出来
			break;
		case R.id.map_back:
			Intent intent=new Intent();
			intent.setClass(MapShow.this, MileShow.class);
			startActivity(intent);
			finish();
			break;
		case R.id.map_confirm:
			//存储日期和时间
			sp.putValue(MAP_DATE, date.getText().toString());
			sp.putValue(MAP_TIME, time.getText().toString());
			//更新订单信息
			String str=mapShop+","+mapTel+","+"0"+"#"+sp.getValue(LIST_STORE_INFO);
			sp.putValue(LIST_STORE_INFO, str);
			//存储用户和电话
			app.updateUserInfo(orderName.getText().toString(), orderTel.getText().toString());
			Intent intent1=new Intent();
			intent1.setClass(MapShow.this, MileShow.class);
			startActivity(intent1);
			finish();
			break;
					
		}
	}
	
	public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth){
		//DatePickerDialog 中按钮Set按下时自动调用
		
		date.setText(Integer.toString(year) + "-" +
		Integer.toString(monthOfYear+1) + "-" +
		Integer.toString(dayOfMonth));
		//设置text
		}
	
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		// Do something with the time chosen by the user
			time.setText(""+hourOfDay+":"+minute);
		}
	
	public void initTab()
	{
		myTabhost = this.getTabHost();  
        tabWidget = myTabhost.getTabWidget();     
        LayoutInflater.from(this).inflate(R.layout.mapshow,  
                myTabhost.getTabContentView(), true);  
        //myTabhost.setBackgroundColor(Color.argb(80, 80, 0, 0));  
        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.maptitlebar);
        
        myTabhost.addTab(myTabhost.newTabSpec("One")// make a new Tab  
                .setIndicator("地点")  
                // set the Title  
                .setContent(R.id.widget_layout_place));  
        // set the layout  
  
        myTabhost.addTab(myTabhost  
                .newTabSpec("Two")  
                // make a new Tab  
                .setIndicator("时间")  
                // set the Title   
                .setContent(R.id.widget_layout_time));  
        // set the layout  
  
        myTabhost.addTab(myTabhost  
                .newTabSpec("Three")  
                // make a new Tab  
                .setIndicator("账户")  
                // set the Title and Icon  
                .setContent(R.id.widget_layout_account)); 
        
        int count = tabWidget.getChildCount();//TabHost中有一个getTabWidget()的方法
        for (int i = 0; i < count; i++) {
           View view = tabWidget.getChildTabViewAt(i);   
           view.getLayoutParams().height = 80; //tabWidget.getChildAt(i)
           view.setBackgroundDrawable(getResources().getDrawable(  
                   R.drawable.tab_bg)); 
           //设置字体的颜色
           final TextView tv = (TextView) view.findViewById(android.R.id.title);
             tv.setTextColor(this.getResources().getColorStateList(
             android.R.color.white));
        }
        //
        
        //设置第一项为默认项
        getTabHost().setCurrentTab(0);
	}
	
	public void initAccount()
	{
		//String name="",tel="";
		orderName.setText(app.getUserAccount());
		orderTel.setText(app.getUserTel());

	}
	
	public void initDate()
	{
		date.setText(sp.getValue(MAP_DATE));
		time.setText(sp.getValue(MAP_TIME));
	}
	/*
	public void initPosition()
	{
		 	mLocationClient = new LocationClient(getApplicationContext()); 
	       // locData = new LocationData();
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
	    				//autoPostion.setChecked(false);
	    				String citystr=location.getAddrStr();
	    				sp.putValue(POS_LONGITUDE, location.getLongitude()+"");
		    			sp.putValue(POS_LATITUDE, location.getLatitude()+"");
		            	Toast.makeText(MapShow.this, citystr, Toast.LENGTH_LONG)
						.show();
		            	//定位到当前所在位置
		            	LatLng ll = new LatLng(location.getLatitude(),
	    						location.getLongitude());
		    			MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
	    				mBaiduMap.animateMapStatus(u);
	    			
	    				
	    			}
	    			
	            } 
	             
	            public void onReceivePoi(BDLocation location){ 
	                //return ; 
	            	String citystr=location.getCity()+location.getProvince()+location.getCityCode();
	            	Toast.makeText(MapShow.this, citystr, Toast.LENGTH_LONG)
					.show();
	            } 
	        }); 
	        mLocationClient.start();
	}
	*/
	public void findNearBy(String searchStr)
	{
		 //附近位置搜索
        mPoiSearch = PoiSearch.newInstance();
        nearPos=new LatLng(Float.parseFloat(sp.getValue(POS_LATITUDE)),Float.parseFloat(sp.getValue(POS_LONGITUDE)));
		mPoiSearch.searchNearby((new PoiNearbySearchOption()).keyword(searchStr).location(nearPos).pageNum(10).radius(5000));
		//
        OnGetPoiSearchResultListener poiListener = new OnGetPoiSearchResultListener(){  
            public void onGetPoiResult(PoiResult result){  
            //获取POI检索结果  
            	if (result == null
        				|| result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
        			return;
        		}
        		if (result.error == SearchResult.ERRORNO.NO_ERROR) {
        			mBaiduMap.clear();
        			PoiOverlay overlay = new MyPoiOverlay(mBaiduMap);
        			mBaiduMap.setOnMarkerClickListener(overlay);
        			//显示第一个查询结果        		
    				for (PoiInfo poi : result.getAllPoi()) {  
    					placeName.setText(poi.name);
        				placeTel.setText(poi.address+"       "+poi.phoneNum);
        				break;
    			        //sb.append("名称：").append(poiInfo.name).append("/n");  
    			        //sb.append("地址：").append(poiInfo.address).append("/n");  
    			        //sb.append("经度：").append(poiInfo.pt.getLongitudeE6() / 1000000.0f).append("/n");  
    			        //sb.append("纬度：").append(poiInfo.pt.getLatitudeE6() / 1000000.0f).append("/n");  
    			
    				/*
    				PoiInfo poi=(PoiInfo)result.getAllPoi().get(0);
    				Toast.makeText(MapShow.this, ((PoiInfo)result.getAllPoi().get(0)).name+"", Toast.LENGTH_LONG)
					.show();
    				placeName.setText(poi.name);
    				placeTel.setText(poi.address+" "+poi.phoneNum);
    				*/
    			}
        			overlay.setData(result);
        			overlay.addToMap();
        			overlay.zoomToSpan();
        			return;
        		}
        		if (result.error == SearchResult.ERRORNO.AMBIGUOUS_KEYWORD) {

        			// 当输入关键字在本市没有找到，但在其他城市找到时，返回包含该关键字信息的城市列表
        			String strInfo = "在";
        			for (CityInfo cityInfo : result.getSuggestCityList()) {
        				strInfo += cityInfo.city;
        				strInfo += ",";
        			}
        			strInfo += "找到结果";
        			Toast.makeText(MapShow.this, strInfo, Toast.LENGTH_LONG)
        					.show();
        		}
            	//Toast.makeText(MapShow.this, result.getAllPoi().toString(), 5).show();
            }  
            public void onGetPoiDetailResult(PoiDetailResult result){  
            //获取Place详情页检索结果  
            	if (result.error != SearchResult.ERRORNO.NO_ERROR) {
        			Toast.makeText(MapShow.this, "抱歉，未找到结果", Toast.LENGTH_SHORT)
        					.show();
        		} else {
        			Toast.makeText(MapShow.this, "成功，查看详情页面", Toast.LENGTH_SHORT)
        					.show();
        		}
            	//Toast.makeText(MapShow.this, result.toString(), 5).show();
            }  
        }; 
        mPoiSearch.setOnGetPoiSearchResultListener(poiListener);
   
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
         case RESULT_OK:
            Bundle b=data.getExtras();  //data为B中回传的Intent
             String str=b.getString("searchInfo");//str即为回传的值
             //maplayout.setVisibility(View.GONE);
             maplayout1.setVisibility(View.VISIBLE);
             findNearBy(str);
            
             /*Toast.makeText(MapShow.this,str, Toast.LENGTH_LONG)
				.show();
             */
                      break;
        default:
               break;
        }
    }
	
	public class MyLocationListenner implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// map view 销毁后不在处理新接收的位置
			if (location == null || mMapView == null)
				return;
			MyLocationData locData = new MyLocationData.Builder()
					.accuracy(location.getRadius())
					// 此处设置开发者获取到的方向信息，顺时针0-360
					.direction(100).latitude(location.getLatitude())
					.longitude(location.getLongitude()).build();
			mBaiduMap.setMyLocationData(locData);
			//
			placeStart.setText(location.getAddrStr());
			if (isFirstLoc) {
				isFirstLoc = false;
			
				String citystr=location.getAddrStr();
				placeStart.setText(citystr);
				//
				sp.putValue(POS_LONGITUDE, location.getLongitude()+"");
    			sp.putValue(POS_LATITUDE, location.getLatitude()+"");
            	/*Toast.makeText(MapShow.this, location.getCity(), Toast.LENGTH_LONG)
				.show();
            	*/
				LatLng ll = new LatLng(location.getLatitude(),
						location.getLongitude());
				MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
				mBaiduMap.animateMapStatus(u);
				nearPos=ll;
			}
		}

		public void onReceivePoi(BDLocation poiLocation) {
		}
	}
	//
	private class MyPoiOverlay extends PoiOverlay {

		public MyPoiOverlay(BaiduMap baiduMap) {
			super(baiduMap);
		}

		@Override
		public boolean onPoiClick(int index) {
			super.onPoiClick(index);
			PoiInfo poi = getPoiResult().getAllPoi().get(index);
			placeName.setText(poi.name);
			placeTel.setText(poi.address+"     "+poi.phoneNum);
			//
			placeDes.setText(poi.name);
			mapShop=poi.name;
			mapTel=poi.phoneNum;
			
			//info.setText(poi.address+","+poi.name+","+poi.phoneNum);
			//Toast.makeText(MapShow.this, "我是"+index+"我草，我被点了", Toast.LENGTH_LONG)
			//.show();
			/*if (poi.hasCaterDetails) {
				mPoiSearch.searchPoiDetail((new PoiDetailSearchOption())
						.poiUid(poi.uid));
			}
			*/
			return true;
		}
		
	}
	
	 @Override 
	    public void onDestroy(){ 
	        if (mLocationClient != null && mLocationClient.isStarted()){ 
	            mLocationClient.stop(); 
	            mLocationClient = null; 
	        } 
	        super.onDestroy(); 
	    } 
}
