package com.test.car;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

public class Manager extends Activity{
	private ImageButton centerBtn,setupBtn,infoBtn,orderBtn;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyApplication app=(MyApplication)getApplication();
		app.addActivity(this);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.manager);
		
		centerBtn=(ImageButton)this.findViewById(R.id.manager_center);
		centerBtn.setOnClickListener(new ImageButton.OnClickListener(){
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(Manager.this, PersonalCenter.class);
				startActivity(intent);					
			}
        });
		//
		setupBtn=(ImageButton)this.findViewById(R.id.manager_setup);
		setupBtn.setOnClickListener(new ImageButton.OnClickListener(){
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(Manager.this, PersonalSetup.class);
				startActivity(intent);					
			}
        });
		//
		infoBtn=(ImageButton)this.findViewById(R.id.manager_about);
		infoBtn.setOnClickListener(new ImageButton.OnClickListener(){
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(Manager.this, PersonalInfo.class);
				startActivity(intent);					
			}
        });
		//
		orderBtn=(ImageButton)this.findViewById(R.id.manager_order);
		orderBtn.setOnClickListener(new ImageButton.OnClickListener(){
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(Manager.this, MyOrder.class);
				startActivity(intent);					
			}
        });
	}
}
