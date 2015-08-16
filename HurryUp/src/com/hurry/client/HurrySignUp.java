package com.hurry.client;

import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketIDFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Registration;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hurry.client.util.MyApplication;
import com.hurry.client.util.XmppTool;


public class HurrySignUp extends Activity{
	private MyApplication app;
	private Handler mHandler;
	//用户UI变量
	private EditText account;
	private EditText psd;
	private EditText psdcomfirm;
	private Button signUpBtn;
	//
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.hurry_signup);
		this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
		
		//获取Application对象,添加活动到列表中
		app=(MyApplication)getApplication();
		app.addActivity(this);
		//
		new CustomThread().start();
		
		account=(EditText)findViewById(R.id.signAccount);
		psd=(EditText)findViewById(R.id.signPsd);
		psdcomfirm=(EditText)findViewById(R.id.signComfirPsd);
		signUpBtn=(Button)findViewById(R.id.signUpbtn);
		//
		signUpBtn.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
				mHandler.obtainMessage(0, "有注册申请").sendToTarget();
				/*
				final String accountStr=account.getText().toString();
				final String psdStr=psd.getText().toString();
				String psdcomStr=psdcomfirm.getText().toString();
				if(accountStr.equals("")||psdStr.equals("")||psdcomStr.equals("")||(!psdcomStr.equals(psdStr)))
				{
					Toast.makeText(HurrySignUp.this,"注册输入的信息有误，请重新注册！", Toast.LENGTH_LONG).show(); 
				}
				else
					{
					Thread t=new Thread(new Runnable() {				
						public void run() {
							try {
								//
								String info=regist(accountStr,psdStr);
								if(info.equals("0"))
								{
									Toast.makeText(HurrySignUp.this,"服务期没有响应，注册失败！", Toast.LENGTH_LONG).show(); 
								}
								else if(info.equals("1"))
								{
									Toast.makeText(HurrySignUp.this,"注册成功！", Toast.LENGTH_LONG).show(); 
								}
								else if(info.equals("2"))
								{
									Toast.makeText(HurrySignUp.this,"这个账号已存在，请直接登录或重新注册！", Toast.LENGTH_LONG).show(); 
								}
								else
								{
									Toast.makeText(HurrySignUp.this,"注册失败，请重新注册！", Toast.LENGTH_LONG).show(); 
								}
								
							}
							catch (Exception e) 
							{
								XmppTool.closeConnection();
								
								e.printStackTrace();
							}	
						}
					});
					t.start();
					
				}*/
			}
        });
		
	}
	//注册函数，return 0、服务器没有返回结果 1、注册成功 2、这个账号已经存在3、注册失败
	public String regist(String account,String password) {  
	    
	    Registration reg = new Registration();  
	    reg.setType(IQ.Type.SET);  
	    reg.setTo(XmppTool.getConnection().getServiceName());  
	    reg.setUsername(account);// 注意这里createAccount注册时，参数是username，不是jid，是“@”前面的部分。  
	    reg.setPassword(password);  
	    reg.addAttribute("android", "geolo_createUser_android");// 这边addAttribute不能为空，否则出错。所以做个标志是android手机创建的吧！！！！！  
	    PacketFilter filter = new AndFilter(new PacketIDFilter(  
	            reg.getPacketID()), new PacketTypeFilter(IQ.class));  
	    PacketCollector collector = XmppTool.getConnection()  
	            .createPacketCollector(filter);  
	    XmppTool.getConnection().sendPacket(reg);  
	    IQ result = (IQ) collector.nextResult(SmackConfiguration  
	            .getPacketReplyTimeout());  
	    // 停止查询结果 
	    collector.cancel();// 停止请求results（是否成功的结果）  
	    if (result == null) {  
	        Log.e("RegistActivity", "No response from server.");  
	        return "0";  
	    } else if (result.getType() == IQ.Type.RESULT) {  
	        return "1";  
	    } else { // if (result.getType() == IQ.Type.ERROR)  
	        if (result.getError().toString().equalsIgnoreCase("conflict(409)")) {  
	            Log.e("RegistActivity", "IQ.Type.ERROR: "  
	                    + result.getError().toString());  
	            return "2";  
	        } else {  
	            Log.e("RegistActivity", "IQ.Type.ERROR: "  
	                    + result.getError().toString());  
	            return "3";  
	        }  
	    }  
	}
	//
	 class CustomThread extends Thread {
		 //
         String str="";
        @Override
        public void run() {
            //建立消息循环的步骤
            Looper.prepare();//1、初始化Looper
            mHandler = new Handler(){//2、绑定handler到CustomThread实例的Looper对象
                public void handleMessage (Message msg) {//3、定义处理消息的方法
                    Log.d("Test", "CustomThread receive msg:" + (String) msg.obj);                	                    
                    //获取用户注册信息
					String accountStr=account.getText().toString();
					String psdStr=psd.getText().toString();
					String psdcomStr=psdcomfirm.getText().toString();
					if(accountStr.equals("")||psdStr.equals("")||psdcomStr.equals("")||(!psdcomStr.equals(psdStr)))
					{
						//Toast.makeText(HurrySignUp.this,"注册输入的信息有误，请重新注册！", Toast.LENGTH_LONG).show();
						str="注册输入的信息有误，请重新注册！";
					}
					else
					{          					
						//
						String info=regist(accountStr,psdStr);
						if(info.equals("0"))
						{
							//Toast.makeText(HurrySignUp.this,"服务期没有响应，注册失败！", Toast.LENGTH_LONG).show();
							str="服务期没有响应，注册失败！";
						}
						else if(info.equals("1"))
						{
							//Toast.makeText(HurrySignUp.this,"注册成功！", Toast.LENGTH_LONG).show();
							str="注册成功！";
						}
						else if(info.equals("2"))
						{
							//Toast.makeText(HurrySignUp.this,"这个账号已存在，请直接登录或重新注册！", Toast.LENGTH_LONG).show();
							str="这个账号已存在，请直接登录或重新注册！";
						}
						else
						{
							//Toast.makeText(HurrySignUp.this,"注册失败，请重新注册！", Toast.LENGTH_LONG).show();
							str="注册失败，请重新注册！";
						}						
					}
					//
					HurrySignUp.this.runOnUiThread(new Runnable()    
			        {    
			            public void run()    
			            {    
			            	Toast.makeText(HurrySignUp.this,str, Toast.LENGTH_LONG).show();
			            }    
			    
			        }); 
                }

            };

            Looper.loop();//4、启动消息循环

        }

    }
}
