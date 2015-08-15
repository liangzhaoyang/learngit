package com.test.car;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends Activity{
	
	private EditText usr;
	private EditText psd;
	private EditText psdcheck;
	private EditText tel;
	private Button signUpBtn;
	//
	private MyApplication app;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.registration);
		
		//获取Application对象
		app=(MyApplication)getApplication();
		app.addActivity(this);
		
		usr=(EditText)findViewById(R.id.signName);
		psd=(EditText)findViewById(R.id.signPsd);
		psdcheck=(EditText)findViewById(R.id.signComfirPsd);
		tel=(EditText)findViewById(R.id.signTel);
		signUpBtn=(Button)findViewById(R.id.signUpbtn);
		//
		signUpBtn.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
				String nameStr=usr.getText().toString();
				String psdStr=psd.getText().toString();
				String psdcomStr=psdcheck.getText().toString();
				String telStr=tel.getText().toString();
				if(nameStr.equals("")||psdStr.equals("")||psdcomStr.equals("")||(!psdcomStr.equals(psdStr)))
				{
					Toast.makeText(Registration.this,"注册输入的信息有误，请重新注册！", Toast.LENGTH_LONG).show(); 
				}
				else
					{
					if(app.insertUser(nameStr, psdStr,telStr))
					{
						Toast.makeText(Registration.this,"注册成功！", Toast.LENGTH_LONG).show();  
						//
					    Intent intent=new Intent();
						intent.setClass(Registration.this, Login.class);
						startActivity(intent); 
						finish();
					}
					else
					{
						Toast.makeText(Registration.this,"注册失败，请重新注册！", Toast.LENGTH_LONG).show(); 
					}
				}
			}
        });
	}
}
