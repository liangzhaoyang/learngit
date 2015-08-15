package com.test.car;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

public class Suggestion extends Activity{
	private ImageButton confirm;
	private TextView back;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyApplication app=(MyApplication)getApplication();
		app.addActivity(this);
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.suggestion);
		this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.suggestiontitlebar);
		
		//
		confirm=(ImageButton)this.findViewById(R.id.suggestion_confirm);
		confirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(Suggestion.this, MileShow.class);
				startActivity(intent);
				finish();
			}
		});
		//
		back=(TextView)this.findViewById(R.id.suggestion_back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(Suggestion.this, MileShow.class);
				startActivity(intent);	
				finish();
			}
		});
	}
}
