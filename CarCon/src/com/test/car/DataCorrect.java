package com.test.car;


import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class DataCorrect extends Activity implements OnDateSetListener, OnClickListener{
	private EditText mile;
	private Button checkData,resetBtn;
	private TextView date,correct_back;
	
	private SharedPreferencesHelper sp;
	final static String TOTAL_MILE="totalMile";
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyApplication app=(MyApplication)getApplication();
		app.addActivity(this);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.datacorrect);
		
		mile=(EditText)this.findViewById(R.id.mile_total);
		checkData=(Button)this.findViewById(R.id.mile_check);
		resetBtn=(Button)this.findViewById(R.id.mile_reset);
		date=(TextView)this.findViewById(R.id.correct_date);
		correct_back=(TextView)this.findViewById(R.id.correct_back);
		//设置ProgressBar当前值
		sp=new SharedPreferencesHelper(this,"mile");
		//
		mile.setText(sp.getValue(TOTAL_MILE));
		
		checkData.setOnClickListener(this);
		resetBtn.setOnClickListener(this);
		date.setOnClickListener(this);
		correct_back.setOnClickListener(this);
		/*
		checkData.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
				sp.putValue(TOTAL_MILE, mile.getText().toString());
				//
				Intent intent=new Intent();
				intent.setClass(DataCorrect.this, MileShow.class);
				startActivity(intent);
				finish();
			}
        });
		//
		resetBtn.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
				mile.setText("0");
			}
        });
		//
		date.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
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
//				     （设备上下文，OnDateSetListener时间设置监听器，默认年，默认月，默认日）
				dlg.show();
				//让DatePickerDialog显示出来
			}
        });
		*/
	}
	
	public void onClick(View v) {//普通按钮事件
		switch(v.getId())
		{
		case R.id.mile_check:
			sp.putValue(TOTAL_MILE, mile.getText().toString());
			//
			Intent intent=new Intent();
			intent.setClass(DataCorrect.this, MileShow.class);
			startActivity(intent);
			finish();
			break;
		case R.id.correct_back:
			//
			Intent intent1=new Intent();
			intent1.setClass(DataCorrect.this, MileShow.class);
			startActivity(intent1);
			finish();
			break;
		case R.id.mile_reset:
			mile.setText("0");
			date.setText("");
			break;
		case R.id.correct_date:
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
		}
		
	}
	
	public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth){
		//DatePickerDialog 中按钮Set按下时自动调用
		
		date.setText(Integer.toString(year) + "-" +
		Integer.toString(monthOfYear+1) + "-" +
		Integer.toString(dayOfMonth));
		//设置text
		}
	/*
	@Override
	public void onScrollStateChange(NumberPicker view, int scrollState) {
		// TODO Auto-generated method stub
		switch (scrollState) {  
        case OnScrollListener.SCROLL_STATE_FLING:  
            Toast.makeText(this, "scroll state fling", Toast.LENGTH_LONG)  
                    .show();  
            break;  
        case OnScrollListener.SCROLL_STATE_IDLE:  
            Toast.makeText(this, "scroll state idle", Toast.LENGTH_LONG).show();  
            break;  
        case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:  
            Toast.makeText(this, "scroll state touch scroll", Toast.LENGTH_LONG)  
                    .show();  
            break;  
        }  
	}

	@Override
	public String format(int value) {
		// TODO Auto-generated method stub
		String tmpStr = String.valueOf(value);  
        if (value < 10) {  
            tmpStr = "0" + tmpStr;  
        }  
        return tmpStr; 
	}

	@Override
	public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
		// TODO Auto-generated method stub
		Log.i("tag", "oldValue:" + oldVal + "   ; newValue: " + newVal);  
        Toast.makeText(  
                this,  
                "number changed --> oldValue: " + oldVal + " ; newValue: "  
                        + newVal, Toast.LENGTH_SHORT).show(); 
	}
	*/
}
