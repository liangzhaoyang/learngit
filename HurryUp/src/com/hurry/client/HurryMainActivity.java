package com.hurry.client;

import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.sasl.SASLMechanism.AuthMechanism;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.hurry.client.util.MyApplication;
import com.hurry.client.util.XmppTool;

public class HurryMainActivity extends Activity{
	 private final String BROADCAST_ADD_FRIEND="action.hurry.addFriend";
	 private final String BROADCAST_MINUS_FRIEND="action.hurry.minusFriend";
	 
	private MyApplication app;
	//为UI添加变量
	private Button positionBtn;
	private TextView requestInfo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.hurry_main);
		this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
		
		//获取Application对象,添加活动到列表中
		app=(MyApplication)getApplication();
		app.addActivity(this);
		//启动service，进行后台信息侦听
		Intent startIntent = new Intent(HurryMainActivity.this, MyService.class);  
        startService(startIntent);  

		//为用户添加提示信息加监听事件
		requestInfo=(TextView)this.findViewById(R.id.main_info);
		requestInfo.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
				 //
			    Intent intent=new Intent();
				intent.setClass(HurryMainActivity.this, HurryPositionShare.class);
				startActivity(intent);
			}
        });
		//为位置按钮添加监听器
		positionBtn=(Button)findViewById(R.id.main_position);
		positionBtn.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
				 //
			    Intent intent=new Intent();
				intent.setClass(HurryMainActivity.this, HurryMapPosition.class);
				startActivity(intent);
			}
        });
		
		//注册一个广播
		IntentFilter intentFilter = new IntentFilter();  
	      intentFilter.addAction(BROADCAST_ADD_FRIEND);
	      intentFilter.addAction(BROADCAST_MINUS_FRIEND);
	      registerReceiver(mRefreshBroadcastReceiver, intentFilter); 
	}
	/*
	String tag="";
	PacketListener myListener = new PacketListener() {      
        public void processPacket(Packet packet) {  
         if(packet instanceof Message) {
          Message message = (Message) packet;
          String type = message.getType().toString();
          if(type.equals("chat")) {
           //接收消息
	           if(message.getBody()!=null)
	           	{
	            //保存信息到客户端数据库
	               }
             }  
          }
  //       }
        else if(packet instanceof Presence) 
        {
	          Presence presence = (Presence) packet;
	          String type = presence.getType().toString();
	          boolean b= this.roster.contains(packet.getFrom());
	          Log.i(tag, b+"");
	 
	          Roster roster = connection.getRoster();
	      	  RosterEntry rosterEntry = roster.getEntry(presence.getFrom());         
	      	  String itemType="";
	       
		       if(rosterEntry!=null)
		       {
			        Log.e(tag, "PacketListener Presence: rosterEntry.getName()= "+rosterEntry.getName()+
			          " - rosterEntry.getUser()="+rosterEntry.getUser()+
			          " - rosterEntry.getStatus()="+rosterEntry.getStatus()+
			          "  -rosterEntry.getType()= "+rosterEntry.getType());
			        itemType=rosterEntry.getType().toString();
		       }
		       Log.e(tag, "itemType=="+itemType);
		       
	          if(type.equals("subscribe"))
		          {//我收到对方的加好友的请求
		           //添加好友请求
		           FriendRelationParam param = new FriendRelationParam();
		           TalkCallback callback = new TalkCallback();
		           callback.from = getUId(presence.getFrom());
		           callback.to = getUId(presence.getTo());
		           try {
						   param.callbackStr = callback.toJsonObject().toString();
						  } catch (Exception e) {
						   e.printStackTrace();
						  }
		           param.userId = Long.parseLong(callback.to);
		           param.friendUid = Long.parseLong(callback.from);
		           
		           startFriendRelation(param);
	           
		           if("none".equals(itemType)){//收到对方的加好友的请求
		            Log.i(tag, "type="+type+"  我收到对方的加好友的请求");
		           	}
	           
		           if("to".equals(itemType)){//我加对方好友后,对方同意，给我发回的交友请求
		            Log.i(tag, "type="+type+"  我加对方好友后,对方同意，给我发回的交友请求");
		           	}                    
		          }
	          	else if("subscribed".equals(type))
	          	{//对方同意加我为好友          	           
		           if("both".equals(itemType)){//双方互为好友关系建立
		            Log.i(tag, "type="+type+"  双方互为好友关系建立!");
		           }
		           Log.e("TalkService"+Thread.currentThread().getName(), presence.getFrom()+"同意了我["+packet.getTo()+"]的加好友请求 ");
		          }
	          	else if(type.equals("unsubscribe")) {//对方发起了把我删除了||或者拒绝    //拒绝的时候只会走这A
	           
		           if("none".equals(itemType)){//拒绝
		            Log.i(tag, "type="+type+"  我被拒绝!!!");
		           }
		           if("to".equals(itemType)){//我收到被对方删除
		            Log.i(tag, "type="+type+"  我收到被对方删除");
		           }             
		          }else if(type.equals("unsubscribed")) {//对方把我删除了   //删除的时候 会走A,同时再走一次这
		           
		           if("none".equals(itemType)){// 我被删除  ,双方关系解除**************************
		            Log.i(tag, "type="+type+"  我被删除的!!!");
		           }
		          }else if(type.equals("available")) {//对方告诉我他在线
		//           presence = new Presence(Presence.Type.available);
		//           presence.setTo(presence.getFrom());
		//           connection.sendPacket(presence);
		          }
         		}
        else if(packet instanceof AuthMechanism) {
          
          
         }
         
        }      
    }; 
    */
	
	//断开服务器连接，销毁所有activity
	protected void onDestroy() {  
        super.onDestroy();  
        XmppTool.closeConnection();
        //
        unregisterReceiver(mRefreshBroadcastReceiver);
        //关闭service
        Intent stopIntent = new Intent(this, MyService.class);  
        stopService(stopIntent); 
        app.exit();
    }  
	
	// 监听是否数据库数据发生变化，刷新页面  
	  private BroadcastReceiver mRefreshBroadcastReceiver = new BroadcastReceiver() {  
	  
	      @Override  
	      public void onReceive(Context context, Intent intent) {  
	          String action = intent.getAction();
	          //更新当前页面显示的申请人数
	        if(action.equals(BROADCAST_ADD_FRIEND)||action.equals(BROADCAST_MINUS_FRIEND))
	          {
	        	  //updateFriendApplication(intent.getStringExtra(FRIEND_ID),"yes");
	        	if(app.currentApplicationNum!=0)
	        		requestInfo.setText("目前有"+app.currentApplicationNum+"人申请位置共享");
	        	else
	        		requestInfo.setText("目前没有人申请位置共享");
	          }
	      }  
	  };  
}
