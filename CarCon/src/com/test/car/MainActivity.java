package com.test.car;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	private RectProgressBar xh_ProgressBar;
	private int intCounter;
	private int progress=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyApplication app=(MyApplication)getApplication();
		app.addActivity(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.TYPE_STATUS_BAR, WindowManager.LayoutParams.TYPE_STATUS_BAR);
		setContentView(R.layout.activity_main);
		
		xh_ProgressBar = (RectProgressBar) findViewById(R.id.loadProgressBar);
		
		//通过线程来改变ProgressBar的值
		new Thread(new Runnable() {
					
					@Override
					public void run() {
						while(progress < 101){//
							progress += 1;
							//System.out.println(progress);					
							xh_ProgressBar.setProgress(progress);							
							try {
								Thread.sleep(10);
								if(progress==100)
								{
									Intent intent=new Intent();
									intent.setClass(MainActivity.this, Login.class);
									startActivity(intent);	
									finish();
								}
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						
					}
				}).start();
	}
/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
*/
}
