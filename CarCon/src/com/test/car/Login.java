package com.test.car;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity{
	
	public final static String USER_NAME="account";
	public final static String USER_PSD="password";
	
	private EditText usr;
	private EditText psd;
	private CheckBox checkInfo;
	private Button loginBtn;
	private TextView mClickableText;
	private SharedPreferencesHelper sp;
	//
	private MyApplication app;
	private String s="No access? Sign Up";
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		
		//获取Application对象
		app=(MyApplication)getApplication();
		app.addActivity(this);
		
		usr=(EditText)findViewById(R.id.edtuser);
		psd=(EditText)findViewById(R.id.edtpsd);
		checkInfo=(CheckBox)findViewById(R.id.rememberpsd);
		loginBtn=(Button)findViewById(R.id.loginbtn);
		mClickableText=(TextView)this.findViewById(R.id.signUp);
		//获取保存的密码和用户名
		sp=new SharedPreferencesHelper(this,"userInfo");
		usr.setText(sp.getValue(USER_NAME));
		psd.setText(sp.getValue(USER_PSD));
		//
		mClickableText.setText(getClickableSpan());
		mClickableText.setMovementMethod(LinkMovementMethod.getInstance());
		Log.i("login", "数据库操作成功");
		//Spannable sp1 = (Spannable) Html.fromHtml(s);		
		//为登陆按钮添加监听器
		loginBtn.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
				String nameStr=usr.getText().toString();
				String psdStr=psd.getText().toString();
				if(app.getUserCheck(nameStr,psdStr ))
				{
					if(checkInfo.isChecked())
					{
						sp.putValue(USER_NAME, nameStr);
						sp.putValue(USER_PSD, psdStr);
						Log.i("login", "sharepreference保存成功");
					}
					//
					Intent intent=new Intent();
					intent.setClass(Login.this, Welcome.class);
					startActivity(intent);	
					finish();
				}
				else
				{
					Toast.makeText(Login.this,"用户名或密码错误，请重新输入！", Toast.LENGTH_LONG).show();  
				}
			
			}
        });
	}
	//
	 private SpannableString getClickableSpan() {
		  View.OnClickListener l = new View.OnClickListener() {
		   //如下定义自己的动作
		   public void onClick(View v) {
		    //
		    Intent intent=new Intent();
			intent.setClass(Login.this, Registration.class);
			startActivity(intent);
		   }
		  };
		  
		 // s=s.replaceAll("Sign Up", "<a  style=\"color:#ff0000;\"  href='Sign Up'>"  
			//	    + "Sign Up" + "</a>");
		  SpannableString spanableInfo = new SpannableString(s);
		  int start = 11;
		  int end = spanableInfo.length();
		  spanableInfo.setSpan(new Clickable(l), start, end,
		    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		  return spanableInfo;
		 }

	class Clickable extends ClickableSpan implements OnClickListener {
		 private final View.OnClickListener mListener;

		 public Clickable(View.OnClickListener l) {
		  mListener = l;
		 }
		 public void onClick(View v) {
			  mListener.onClick(v);
			 }
	}
}
