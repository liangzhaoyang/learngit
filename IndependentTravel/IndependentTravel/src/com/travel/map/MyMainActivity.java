package com.travel.map;

import java.util.List;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.LocationListener;
import com.baidu.mapapi.MKAddrInfo;
import com.baidu.mapapi.MKBusLineResult;
import com.baidu.mapapi.MKDrivingRouteResult;
import com.baidu.mapapi.MKPoiResult;
import com.baidu.mapapi.MKSearch;
import com.baidu.mapapi.MKSearchListener;
import com.baidu.mapapi.MKSuggestionResult;
import com.baidu.mapapi.MKTransitRouteResult;
import com.baidu.mapapi.MKWalkingRouteResult;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapController;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.MyLocationOverlay;
import com.baidu.mapapi.Overlay;
import com.baidu.mapapi.Projection;
import com.travel.R;
import com.travel.util.MapApplication;

public class MyMainActivity extends MapActivity implements
		LocationListener, OnClickListener {

	MapController mMapController;// 得到mMapView的控制权,可以用它控制和驱动平移和

	GeoPoint geoPoint;

	MKSearch mSearch;

	
	LocationListener mLocationListener;

	MapView mMapCon;// 地图控件

	ImageButton showMap;

	LinearLayout top;

	TextView searchNear;
	
	TextView searchNear1;

	TextView searchRoute;

	TextView shangchuan;
	
	String tag;

	String startAdd;

	String endAdd;

	SharedPreferences currentMsg;

	SharedPreferences.Editor editor;

	private String currentLocAddress;

	Bitmap bitmap;

	ProgressDialog processDialog;

	private BMapManager mapManager = null;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		// full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_mymain);

		super.initMapActivity(MapApplication.getInstance().getMapManager());

		findViews();
		mapManager = MapApplication.getInstance().getMapManager();
		if (mapManager != null) {
			mapManager.start();

			initMap();
		}
	}

	public void findViews() {
		mMapCon = (MapView) findViewById(R.id.bmapsView);
		showMap = (ImageButton) findViewById(R.id.showMap);
		searchRoute = (TextView) findViewById(R.id.searchRoute);
		searchNear = (TextView) findViewById(R.id.searchPoi);
		searchNear1 = (TextView) findViewById(R.id.searchPoi_1);
		shangchuan = (TextView) findViewById(R.id.jingdian);
		searchRoute.setText(R.string.menu_search_route);
		searchNear.setText(R.string.menu_poi_search);
		searchNear1.setText(R.string.menu_poi_search1);
		shangchuan.setText(R.string.menu_poi_jingdian);

		top = (LinearLayout) findViewById(R.id.top);
		showMap.setOnClickListener(this);
		searchRoute.setOnClickListener(this);
		searchNear.setOnClickListener(this);
		searchNear1.setOnClickListener(this);
		shangchuan.setOnClickListener(this);

	}

	public void initMap() {
		processDialog = new ProgressDialog(MyMainActivity.this);
		processDialog
				.setMessage("正在定位");
		processDialog.show();
		mMapController = mMapCon.getController();
		mMapCon.setBuiltInZoomControls(true); // 设置启用内置的缩放控件
		mMapCon.setDrawOverlayWhenZooming(true);
		mMapCon.setSatellite(false);// 设置卫星图
		mMapCon.setDragMode(0);// 设置0：拖拽有动画
		mMapController.setZoom(16); // 设置地图zoom级别
		// mMapView.setTraffic(true);// 实时交通信息

		mSearch = new MKSearch();
		mSearch.init(MapApplication.getInstance().getMapManager(),
				new MySearchListener());
		mLocationListener = new LocationListener() {

			public void onLocationChanged(Location loc) {
				if (loc != null) {
					if (processDialog != null && processDialog.isShowing()) {
						processDialog.dismiss();
					}
					geoPoint = new GeoPoint((int) (loc.getLatitude() * 1E6),
							(int) (loc.getLongitude() * 1E6));

					mSearch.reverseGeocode(geoPoint);
					mMapController.animateTo(geoPoint);
					mMapController.setCenter(geoPoint); // 设置地图中心点

					mMapCon.getOverlays().clear();

					addOverLay();

				}

			}
		};
		currentMsg = getSharedPreferences("currentMsg", 0);
		editor = currentMsg.edit();

	}

	//停止
	@Override
	protected void onPause() {

		if (mapManager != null) {
			mapManager.getLocationManager().removeUpdates(mLocationListener);
			mapManager.stop();
		}
		super.onPause();
	}

	//重启
	@Override
	protected void onResume() {

		if (mapManager != null) {
			mapManager.getLocationManager().requestLocationUpdates(
					mLocationListener);
			mapManager.start();
		}
		super.onResume();
	}

	public class MySearchListener implements MKSearchListener {

		public void onGetAddrResult(MKAddrInfo info, int error) {
			if (error != 0) {
				String str = String.format("错误号：%d", error);
				Log.e("error", str);
				return;
			}
			editor.putInt("lat", info.geoPt.getLatitudeE6());
			editor.putInt("lon", info.geoPt.getLongitudeE6());
			editor.putString("city", info.addressComponents.city);
			editor.putString("district", info.addressComponents.district);
			editor.putString("street", info.addressComponents.street);
			editor.putString("completeAdd", info.addressComponents.city
					+ info.addressComponents.district
					+ info.addressComponents.street);
			editor.commit();
			currentLocAddress = String.format("位置：%s\r\n",
					info.addressComponents.city
							+ info.addressComponents.district
							+ info.addressComponents.street);
			Toast.makeText(MyMainActivity.this, currentLocAddress,
					Toast.LENGTH_LONG).show();

		}

		public void onGetBusDetailResult(MKBusLineResult arg0, int arg1) {
			// TODO Auto-generated method stub

		}

		public void onGetDrivingRouteResult(MKDrivingRouteResult arg0, int arg1) {
			// TODO Auto-generated method stub

		}

		public void onGetPoiResult(MKPoiResult arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		public void onGetRGCShareUrlResult(String arg0, int arg1) {
			// TODO Auto-generated method stub

		}

		public void onGetSuggestionResult(MKSuggestionResult arg0, int arg1) {
			// TODO Auto-generated method stub

		}

		public void onGetTransitRouteResult(MKTransitRouteResult result,
				int error) {
		}

		public void onGetWalkingRouteResult(MKWalkingRouteResult arg0, int arg1) {
			// TODO Auto-generated method stub

		}
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	public void onLocationChanged(Location location) {
		location.getLatitude();
		location.getLongitude();

	}

	/*
	 * VISIBLE:0 意思是可见的 INVISIBILITY:4 意思是不可见的，但还占着原来的空间 GONE:8
	 * 意思是不可见的，不占用原来的布局空间
	 */

	public void onClick(View v) {
		Intent i;
		switch (v.getId()) {
		case R.id.showMap: {
			if (top.getVisibility() == 0) {
				showMap.setImageResource(R.drawable.handle_down);
				top.setVisibility(View.GONE);
			} else if (top.getVisibility() == 8) {
				showMap.setImageResource(R.drawable.handle_up);
				top.setVisibility(View.VISIBLE);
			}
			break;
		}
		case R.id.searchRoute: {
			i = new Intent(MyMainActivity.this, SearchWayActivity.class);
			i.putExtra("flag", "");
			i.putExtra("startOrendDialogFlag", "");
			i.putExtra("map", "");
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
			break;
		}
		case R.id.searchPoi: {
			i = new Intent(MyMainActivity.this, NearActivity.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
			break;
				
		}

		case R.id.searchPoi_1: {
			i = new Intent(MyMainActivity.this, WeatherScreen.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
			break;
		}
		
		case R.id.jingdian: {
			i = new Intent(MyMainActivity.this, shangchuan.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
			break;
		}
		
		
		default:
			break;
		}
	}

	void addOverLay() {
		List<Overlay> overlays = mMapCon.getOverlays();
		bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pin);
		MyOverlay overlay = new MyOverlay(MyMainActivity.this, mMapCon);
		overlay.enableCompass();
		overlay.enableMyLocation();
		overlays.add(overlay);// 添加自定义overlay
	}

	public class MyOverlay extends MyLocationOverlay {

		public MyOverlay(Context arg0, MapView arg1) {
			super(arg0, arg1);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void drawMyLocation(Canvas canvas, MapView mapView,
				Location lastFix, GeoPoint myLocation, long when) {
			try {
				Projection projection = mapView.getProjection();
				Point point = new Point();
				projection.toPixels(myLocation, point);

				int x = point.x - bitmap.getWidth() / 2;
				int y = point.y - bitmap.getHeight();
				canvas.drawBitmap(bitmap, x, y, new Paint());

			} catch (Exception e) {
				super.drawMyLocation(canvas, mapView, lastFix, myLocation, when);
			}
		}
	};

}
