package com.test.car;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class PersonalSetup extends Activity{
	MyApplication app;
	private TextView  quit;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		app=(MyApplication)getApplication();
		app.addActivity(this);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.personalsetup);
		
		quit=(TextView)this.findViewById(R.id.setup_quit);
		quit.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(PersonalSetup.this, Login.class);
				startActivity(intent);
				finish();
			}
        });
	}
	
}

