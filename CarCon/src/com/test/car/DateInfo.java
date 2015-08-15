package com.test.car;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.GestureDetector.OnGestureListener;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;

public class DateInfo extends Activity implements OnTouchListener,OnGestureListener{
	GestureDetector gd;
	final int FLING_MIN_DISTANCE = 100, FLING_MIN_VELOCITY = 200; 
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyApplication app=(MyApplication)getApplication();
		app.addActivity(this);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.datainfo);
		
		LinearLayout viewSnsLayout=(LinearLayout)findViewById(R.id.dataview);
		//保证能够触发触屏事件
		viewSnsLayout.setOnTouchListener(this);
		viewSnsLayout.setLongClickable(true);
		//定义一个手势识别类对象
		gd = new GestureDetector((OnGestureListener)this);
		
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
        if(e1.getY() - e2.getY() > FLING_MIN_DISTANCE&&Math.abs(velocityX) > FLING_MIN_VELOCITY)  
        {  
            Intent intent = new Intent(DateInfo.this,MileView.class);  
            startActivity(intent);  
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
             //Toast.makeText(this, "向左手势", Toast.LENGTH_SHORT).show();   
  
        }  
        else
        if (e2.getX()-e1.getX() > FLING_MIN_DISTANCE && Math.abs(velocityX) >FLING_MIN_VELOCITY) {  
              
            //切换Activity  
            Intent intent = new Intent(DateInfo.this, Mileage.class);  
            startActivity(intent);  
            //Toast.makeText(this, "向右手势", Toast.LENGTH_SHORT).show();  
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
}
