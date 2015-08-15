package com.test.car;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MileView extends Activity{
	private LinearLayout layout_chart ;
	private Button start;
	private ChartView chartView ;
    private String str[]={"4-12","4-13","4-14","4-15","4-16","4-17","4-18"};
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState) ;
		MyApplication app=(MyApplication)getApplication();
		app.addActivity(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE) ;
		setContentView(R.layout.mileview) ;

		layout_chart = (LinearLayout) findViewById(R.id.chartArea) ;
		int data[] = new int[] {90, 65, 80, 115,20,50,40 } ;
		chartView = new ChartView(this, str,data) ;
		layout_chart.removeAllViews() ;
		layout_chart.addView(chartView) ;
		//
		start=(Button)this.findViewById(R.id.chartBtn);
		//
		start.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(MileView.this, MapInfo.class);
				startActivity(intent);	
				finish();
			}
        });
	}


}
