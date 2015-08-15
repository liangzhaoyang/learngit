package com.test.car;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MileShow extends Activity{
	
	final static int MILE_MAX=5000;
	private SlideLayout slideLayout;// 滑动Layout
	
	private ImageView mileMore;
	private ImageButton setup,managerCenter,managerInfo,managerSetup,managerOrder;
	private Button order;
	private RoundProgressBar mileBar;
	private int progress=0;
	//private GestureDetector gd;
	//final static int FLING_MIN_DISTANCE = 100, FLING_MIN_VELOCITY = 200; 
	
	private SharedPreferencesHelper sp;
	private String currentMile;
	final static String TOTAL_MILE="totalMile";
	private MyApplication app;
	
	//private ListView sortListView;// 排序listview
	//private String[] sortList = { "周点击", "月点击", "总点击", "全本榜" };
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		app=(MyApplication)getApplication();
		app.addActivity(this);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mileshow);
		
		slideLayout = (SlideLayout) findViewById(R.id.slidelayout);
		//LinearLayout viewSnsLayout=(LinearLayout)findViewById(R.id.mileview);
		//保证能够触发触屏事件
		//viewSnsLayout.setOnTouchListener(this);
		//viewSnsLayout.setLongClickable(true);
		//定义一个手势识别类对象
		//gd = new GestureDetector((OnGestureListener)this);
		
		setup=(ImageButton)this.findViewById(R.id.mileage_setup);
		order=(Button)this.findViewById(R.id.orderBtn);
		mileBar=(RoundProgressBar)this.findViewById(R.id.mileBar);
		//设置ProgressBar当前值
		sp=new SharedPreferencesHelper(this,"mile");
		//初始定义里程为4000KM
		//sp.putValue(TOTAL_MILE,"4000");
		//currentMile=new String("");
		currentMile=sp.getValue(TOTAL_MILE);
		if(currentMile.equals(""))
			currentMile="4000";
		//mileBar.setProgress(4000);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				int mile_value=Integer.parseInt(currentMile);
				int mileTemp=mile_value % MILE_MAX;
				mileBar.setCurMile(mile_value);
				while(progress <mileTemp ){//
					progress += 5;
					//System.out.println(progress);					
					mileBar.setProgress(progress);					
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		}).start();
		//
		order.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(MileShow.this, MapShow.class);
				startActivity(intent);					
			}
        });
		//
		setup.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(MileShow.this, DataCorrect.class);
				startActivity(intent);					
			}
        });
		
		mileMore=(ImageView)this.findViewById(R.id.mile_more);
		mileMore.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				slideLayout.snapToScreen(1, false);
			}
		});
		//
		managerCenter=(ImageButton)this.findViewById(R.id.manager_center);
		managerCenter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(MileShow.this, PersonalCenter.class);
				startActivity(intent);	
			}
		});
		//
		managerInfo=(ImageButton)this.findViewById(R.id.manager_about);
		managerInfo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(MileShow.this, PersonalInfo.class);
				startActivity(intent);	
			}
		});
		//
		managerSetup=(ImageButton)this.findViewById(R.id.manager_setup);
		managerSetup.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(MileShow.this, PersonalSetup.class);
				startActivity(intent);	
			}
		});
		//
		managerOrder=(ImageButton)this.findViewById(R.id.manager_order);
		managerOrder.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(MileShow.this, MyOrder.class);
				startActivity(intent);	
			}
		});
		/*
		sortListView = (ListView) findViewById(R.id.sort_listview);
		sortListView.setAdapter(new SortAdapter(this, sortList));
		sortListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				switch (position) {
				default:
					break;
				}
				slideLayout.snapToScreen(0, false);
			}
		});
		*/
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
		
		/*
		//--------------------------
		private class SortAdapter extends BaseAdapter {

			private Context context;
			private String[] sortList = new String[5];

			public SortAdapter(Context context, String[] sortList) {
				this.context = context;
				this.sortList = sortList;
			}

			@Override
			public int getCount() {
				return sortList.length;
			}

			@Override
			public Object getItem(int position) {
				return sortList[position];
			}

			@Override
			public long getItemId(int position) {
				return position;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				ViewHolder holder = null;
				if (convertView == null) {
					convertView = LayoutInflater.from(context).inflate(
							R.layout.sort_item, null);
					holder = new ViewHolder();
					holder.sortTypeTextView = (TextView) convertView
							.findViewById(R.id.sort_type_text);
					convertView.setTag(holder);
				} else {
					holder = (ViewHolder) convertView.getTag();
				}
				holder.sortTypeTextView.setText(sortList[position]);
				return convertView;
			}

			class ViewHolder {
				TextView sortTypeTextView;
			}

		}
		*/
}
