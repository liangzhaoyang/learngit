package com.test.car;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class Welcome extends Activity{
	private Button startBtn;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyApplication app=(MyApplication)getApplication();
		app.addActivity(this);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.welcome);
		Log.i("welcome","sussce");
		startBtn=(Button)findViewById(R.id.startbtn);
		//ÎªµÇÂ½°´Å¥Ìí¼Ó¼àÌýÆ÷
		startBtn.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(Welcome.this, MileShow.class);
				startActivity(intent);	
				finish();
			}
        });
	}
}
