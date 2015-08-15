package com.test.car;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class LocationSet extends Activity{
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyApplication app=(MyApplication)getApplication();
		app.addActivity(this);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.registration);
	}
}