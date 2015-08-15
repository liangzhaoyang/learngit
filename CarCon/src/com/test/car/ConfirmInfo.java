package com.test.car;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class ConfirmInfo extends Activity{
	private Button mBtnConfirm;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyApplication app=(MyApplication)getApplication();
		app.addActivity(this);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.confirmorder);
		
		mBtnConfirm=(Button)this.findViewById(R.id.order_confirm);
		mBtnConfirm.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(ConfirmInfo.this, Mileage.class);
				startActivity(intent);
				finish();
			}
        });
	}
}
