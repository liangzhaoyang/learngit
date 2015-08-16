package com.hurry.client;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.sasl.SASLMechanism.AuthMechanism;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.hurry.client.util.XmppTool;

public class MyService extends Service {  
  
    public static final String TAG = "MyService";  
    private final String FRIEND_ID="friend_id";
    private final String FRIEND_MESSAGE="friend_message";
    //定义广播信息
    private final String BROADCAST_ADD_FRIEND="action.hurry.addFriend";
    private final String BROADCAST_FRIEND_LIST_INFO="action.hurry.friendListInfo";
    private final String BROADCAST_HURRY_INFO="action.hurry.info";
    //定义催促信息指令
  	public final static String HURRY_INFO="##hurry##";
  	public final static String NO_HURRY_INFO="##no hurry##";
  	public final static String HURRY_HEAD_INFO="##head is clicked##";
    private Roster roster;
    
    @Override  
    public void onCreate() {  
        super.onCreate();  
        Log.d(TAG, "onCreate() executed");
        
      //消息监听
	ChatManager cm = XmppTool.getConnection().getChatManager();
	cm.addChatListener(new ChatManagerListener() {
		@Override
		public void chatCreated(Chat chat, boolean able) 
		{
			chat.addMessageListener(new MessageListener() {
				@Override
				public void processMessage(Chat chat2, Message message)
				{
					//
					//updateList(message.getFrom(),message.getBody());
					String info=message.getBody();
					// 广播通知
			         Intent intent = new Intent();
			         intent.putExtra(FRIEND_ID, message.getFrom());
			         intent.putExtra(FRIEND_MESSAGE, message.getBody());
			         if(info.equals(HURRY_INFO)||info.equals(NO_HURRY_INFO)||info.equals(HURRY_HEAD_INFO))
			         {
			        	 intent.setAction(BROADCAST_HURRY_INFO);
			         }
			         else
			        	 intent.setAction(BROADCAST_FRIEND_LIST_INFO);
			         sendBroadcast(intent);
				}
			});
		}
	});
	//
	roster=XmppTool.getConnection().getRoster();
	PacketListener myListener = new PacketListener() {      
        public void processPacket(Packet packet) {  
        	 Log.i("Presence", "PresenceService------" + packet.toXML());  
             //看API可知道   Presence是Packet的子类  
             if (packet instanceof Presence) {  
                 Log.i("Presence", packet.toXML());  
                 Presence presence = (Presence) packet;  
                 //Presence还有很多方法，可查看API   
                 String from = presence.getFrom();//发送方  
                 String to = presence.getTo();//接收方  
                 //Presence.Type有7中状态  
                 if (presence.getType().equals(Presence.Type.subscribe)) {//好友申请  
                	 String []user=from.split("@");
                	// 广播通知
			         Intent intent = new Intent();
			         intent.putExtra(FRIEND_ID, user[0]);
			        // intent.putExtra(FRIEND_MESSAGE, message.getBody());
			         intent.setAction(BROADCAST_ADD_FRIEND);
			         sendBroadcast(intent);
			         
                 } else if (presence.getType().equals(  
                         Presence.Type.subscribed)) {//同意添加好友  
                       
                 } else if (presence.getType().equals(  
                         Presence.Type.unsubscribe)) {//拒绝添加好友     
                	        
                 } else if (presence.getType().equals(//删除好友 
                         Presence.Type.unsubscribed)){  
                 } else if (presence.getType().equals(  
                         Presence.Type.unavailable)) {//好友下线   要更新好友列表，可以在这收到包后，发广播到指定页面   更新列表  
                       
                 } else {//好友上线  
                       
                 }  
             }  
        }
      };

    }  
  
    @Override  
    public int onStartCommand(Intent intent, int flags, int startId) {  
        Log.d(TAG, "onStartCommand() executed");  
        return super.onStartCommand(intent, flags, startId);  
    }  
      
    @Override  
    public void onDestroy() {  
        super.onDestroy();  
        Log.d(TAG, "onDestroy() executed");  
    }  
  
    @Override  
    public IBinder onBind(Intent intent) {  
        return null;  
    }  
}
