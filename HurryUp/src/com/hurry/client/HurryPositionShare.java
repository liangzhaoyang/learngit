package com.hurry.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.RosterGroup;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TabActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.hurry.client.util.MyApplication;
import com.hurry.client.util.TimeRender;
import com.hurry.client.util.XmppTool;


public class HurryPositionShare extends TabActivity{
	private TabHost myTabhost;
	private MyApplication app;
	//定义与数据添加有关的变量
	private ListView friendList,applicationList;
	private String pUSERID;
	private List<Map<String, Object>> listdata;
	private ArrayList<HashMap<String, Object>> applicationData;
	private SimpleAdapter useradapter;
	private MyApplicationAdapter applicationAdapter;
	
	private Roster roster;
	//定义好友列表的相关广播信息变量
	private final String FRIEND_ID="friend_id";
    private final String FRIEND_MESSAGE="friend_message";
    private final String BROADCAST_FRIEND_INFO="action.hurry.friendListInfo";
    private final String BROADCAST_ADD_FRIEND="action.hurry.addFriend";
    private final String BROADCAST_MINUS_FRIEND="action.hurry.minusFriend";
    //定义UI控件变量
    private ImageButton friendAdd;
    
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		app=(MyApplication)getApplication();
		app.addActivity(this);
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); 
		//更改窗体样式
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.share_bar);
		//获取用户名
		this.pUSERID = app.getUserID();
		
		//
		myTabhost = this.getTabHost();  
        final TabWidget tabWidget = myTabhost.getTabWidget();     
        LayoutInflater.from(this).inflate(R.layout.hurry_positionshare,  
                myTabhost.getTabContentView(), true);  
        myTabhost.setBackgroundColor(Color.argb(150, 0, 0, 0));  
  
        myTabhost.addTab(myTabhost.newTabSpec("One")// make a new Tab  
                .setIndicator("最新申请")  
                // set the Title  
                .setContent(R.id.widget_layout_application));  
        // set the layout  
  
        myTabhost.addTab(myTabhost  
                .newTabSpec("Two")  
                // make a new Tab  
                .setIndicator("我的好友")  
                // set the Title   
                .setContent(R.id.widget_layout_friend));  
        // set the layout  
  
        myTabhost.addTab(myTabhost  
                .newTabSpec("Three")  
                // make a new Tab  
                .setIndicator("附近的人")  
                // set the Title and Icon  
                .setContent(R.id.widget_layout_around)); 
        
        // 初始化Tab选项卡背景  
        for (int i = 0; i < tabWidget.getChildCount(); i++) {  
  
            View vvv = tabWidget.getChildAt(i);  
            if (i== 0) {  
                vvv.setBackgroundDrawable(getResources().getDrawable(  
                        R.drawable.btn_bg_pressed));  
            } else {  
                vvv.setBackgroundDrawable(getResources().getDrawable(  
                        R.drawable.btn_bg_focused));  
            }  
 
        }   
        // /  
        /** 
         * 当点击tab选项卡的时候，更改当前的背景，并装载对应的 menu 
         */  
        myTabhost.setOnTabChangedListener(new OnTabChangeListener() {  
            @Override  
            public void onTabChanged(String tagString) {  
                // TODO Auto-generated method stub  
                for (int i = 0; i < tabWidget.getChildCount(); i++) {  
                    View view = tabWidget.getChildAt(i);  
                    if (myTabhost.getCurrentTab() == i) {  
                        view.setBackgroundDrawable(getResources().getDrawable(  
                                R.drawable.btn_bg_pressed));  
                    } else {  
                        view.setBackgroundDrawable(getResources().getDrawable(  
                                R.drawable.btn_bg_focused));  
                    }  
                }  
               
            }  
        });
        //设置第一项为默认项
        getTabHost().setCurrentTab(0);
        
      //更改窗体样式
      getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.share_bar);
      		
        this.friendList=(ListView)this.findViewById(R.id.friend_list);
        this.applicationList=(ListView)this.findViewById(R.id.application_list);
        this.friendAdd=(ImageButton)this.findViewById(R.id.share_friend_add);
        this.friendAdd.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				showDialog(0);				
			}
        });
        
        roster=XmppTool.getConnection().getRoster();
        /*
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
						updateList(message.getFrom(),message.getBody());
					}
				});
			}
		});
		*/
        /*
        try {
        	roster.createGroup("friend");
        	roster.setSubscriptionMode(Roster.SubscriptionMode.accept_all); 
        	roster.createEntry("kkk"+"@hnu-pc", "BigMan", new String[]{"friend"});
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        */
		//
		initApplicationList();
		initFriendList();
		
		//注册一个广播
		IntentFilter intentFilter = new IntentFilter();  
	      intentFilter.addAction(BROADCAST_FRIEND_INFO);
	      intentFilter.addAction(BROADCAST_ADD_FRIEND);
	      registerReceiver(mRefreshBroadcastReceiver, intentFilter); 
    }
	/**
	 * 初始化好友信息列表
	 */
	public void initFriendList()
	{
		//Toast.makeText(HurryPositionShare.this, pUSERID, Toast.LENGTH_LONG).show();
		//获取用户朋友信息列表		
		roster=XmppTool.getConnection().getRoster();
		listdata = new ArrayList<Map<String, Object>>();
		Map<String, Object> map;
		 Collection<RosterGroup> entriesGroup = roster.getGroups();  
	     for(RosterGroup group: entriesGroup){  
	         Collection<RosterEntry> entries = group.getEntries();  
	         Log.i("---", group.getName());  
	         for (RosterEntry entry : entries) { 
	        	 map = new HashMap<String, Object>();
	        		
	 			map.put("img", R.drawable.h001);
	 			map.put("account", entry.getName());
	 			if(entry.getStatus()==null)
	 				map.put("state", "(离线)");
	 			else
	 				map.put("state", "(在线)");
	 			
	 			listdata.add(map);
	 			
	 			//同时将所有当前用户表存入list
	 			app.addUserList(entry.getName());
	             //Presence presence = roster.getPresence(entry.getUser());  
	             //Log.i("---", "user: "+entry.getUser());  
	             Log.i("---", "name: "+entry.getName());  
	             //Log.i("---", "tyep: "+entry.getType());  
	             //Log.i("---", "status: "+entry.getStatus());  
	             //Log.i("---", "groups: "+entry.getGroups());  
	         }  
	     }  
		
		  //添加adapter适配器
		useradapter = new SimpleAdapter(this, listdata,
				R.layout.user_list, new String[] { "img", "account","state","info" },
				new int[] { R.id.head_img,R.id.user_id, R.id.user_state,R.id.user_info
						});
		friendList.setAdapter(useradapter);
		//为listview的每一项添加监听事件
		friendList.setOnItemClickListener(new OnItemClickListener()
		{
		       @Override
		      public void onItemClick(AdapterView<?> parent, View view, int position, long id)
		      {
		    	   ListView listView = (ListView)parent;
		    	    HashMap<String, String> map = (HashMap<String, String>) listView.getItemAtPosition(position);
		    	    //获取表项中的值
		    	    String accountStr = map.get("account");
		    	    String []temp=accountStr.split("@");
		    	    app.currentTalkObj=temp[0];
		    	   // Toast.makeText(HurryPositionShare.this, accountStr, Toast.LENGTH_LONG).show();
		    	    Intent intent = new Intent();
					intent.setClass(HurryPositionShare.this, HurryTalkOne.class);
					intent.putExtra("USERID", pUSERID);
					intent.putExtra("COUNTERPART", accountStr);
					startActivity(intent);
		    }
		});
	}
	/*
	 * 定义好友添加dialog
	 */
	protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        AlertDialog customDialog;
        
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.friend_add, null);
        builder.setTitle("好友添加");
        builder.setView(layout);
       
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                finish();
            }
        });
        customDialog = builder.create();
        
        return customDialog;
    }
	/*
	 * 初始化申请人列表
	 */
	public void initApplicationList()
	{
		applicationData = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map;
		/*String userTemp[]={"Lily","Bob","Mary","John"};
		for(int i=0;i<4;i++)
		{
			map = new HashMap<String, Object>();
			map.put("img", R.drawable.h001);
			map.put("name", userTemp[i]);
			map.put("distance", "100m以内");
			map.put("time", "6-29");
			applicationData.add(map);
		}
		*/
		  //添加adapter适配器
		applicationAdapter= new MyApplicationAdapter(this, applicationData,
				R.layout.application_list, new String[] { "img", "name","distance", "time"},
				new int[] { R.id.application_img,R.id.application_nickname, R.id.application_distance_value,R.id.application_time_value
						});
		applicationList.setAdapter(applicationAdapter);
	}
	
	//更新用户列表数据
	private void updateList(String usrId,String info)
	{
		//获取用户的ID
		String str[]=usrId.split("@");
		for(Map<String, Object> map : listdata){
			 if(map.get("account").equals(str[0]))
			 {
				 map.put("info", info);
				 break;
			 }
		}
		useradapter.notifyDataSetChanged();
	}
	//更新好友请求列表数据
	private void updateFriendApplication(String user,String opera)
	{
		if(opera.equals("yes"))
		{	
			HashMap<String, Object> map;
			map = new HashMap<String, Object>();
			map.put("img", R.drawable.h001);
			map.put("name", user);
			map.put("distance", "100m以内");
			map.put("time", TimeRender.getDate());
			applicationData.add(map);
		}
		else
		{
			for(HashMap<String, Object> map : applicationData){
				 if(map.get("name").equals(user))
				 {
					 applicationData.remove(map);
					 break;
				 }
			}
			//
			// 广播通知
			app.currentApplicationNum--;
	         Intent intent = new Intent();
	        // intent.putExtra(FRIEND_MESSAGE, message.getBody());
	         intent.setAction(BROADCAST_MINUS_FRIEND);
	         sendBroadcast(intent);
		}
		
		applicationAdapter.notifyDataSetChanged();
	}
	
	 @Override
	protected void onDestroy() {
	        super.onDestroy();
	        unregisterReceiver(mRefreshBroadcastReceiver);
	    }
	 
	/*
	 * 自定义adapter适配器
	 */
	public class MyApplicationAdapter extends BaseAdapter {
	    private class MyViewHolder {
	        ImageView headIcon;
	        TextView name,distance,time;
	        Button acceptBtn,refuseBtn,talkBtn;
	    }
	    
	    private ArrayList<HashMap<String, Object>> mAppList;
	    private LayoutInflater mInflater;
	    private Context mContext;
	    private String[] keyString;
	    private int[] valueViewID;
	    private MyViewHolder holder;
	    
	    public MyApplicationAdapter(Context c, ArrayList<HashMap<String, Object>> appList, int resource,
	            String[] from, int[] to) {
	        mAppList = appList;
	        mContext = c;
	        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        keyString = new String[from.length];
	        valueViewID = new int[to.length];
	        System.arraycopy(from, 0, keyString, 0, from.length);
	        System.arraycopy(to, 0, valueViewID, 0, to.length);
	    }
	    
	    @Override
	    public int getCount() {
	        return mAppList.size();
	    }

	    @Override
	    public Object getItem(int position) {
	        return mAppList.get(position);
	    }

	    @Override
	    public long getItemId(int position) {
	        return position;
	    }

	    public void removeItem(int position){
	        mAppList.remove(position);
	        this.notifyDataSetChanged();
	    }
	    
	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        if (convertView != null) {
	            holder = (MyViewHolder) convertView.getTag();
	        } else {
	            convertView = mInflater.inflate(R.layout.application_list, null);
	            holder = new MyViewHolder();
	            holder.headIcon = (ImageView)convertView.findViewById(R.id.application_img);
	            holder.name = (TextView)convertView.findViewById(R.id.application_nickname);
	            holder.distance = (TextView)convertView.findViewById(R.id.application_distance_value);
	            holder.time = (TextView)convertView.findViewById(R.id.application_time_value);
	            //
	            holder.acceptBtn = (Button)convertView.findViewById(R.id.application_accept);
	            holder.refuseBtn = (Button)convertView.findViewById(R.id.application_refuse);
	          //  holder.talkBtn = (Button)convertView.findViewById(R.id.application_talk);
	            convertView.setTag(holder);
	        }
	        
	        HashMap<String, Object> appInfo = mAppList.get(position);
	        if (appInfo != null) {
	        	//"img", "name","distance", "time"
	        	int imageId=(Integer)appInfo.get(keyString[0]);
	        	String name = (String) appInfo.get(keyString[1]);
	            String distance = (String) appInfo.get(keyString[2]);
	            String time = (String) appInfo.get(keyString[3]);
	            //将值映射给对应的项
	            holder.headIcon.setImageDrawable(holder.headIcon.getResources().getDrawable(imageId));
	            holder.name.setText(name);
	            holder.distance.setText(distance);
	            holder.time.setText(time);
	            //为按钮添加tag，以便知道是哪一行的那个按钮
	            holder.acceptBtn.setTag(position);
	           // holder.talkBtn.setTag(position);
	            holder.refuseBtn.setTag(position);
	           //
	            holder.acceptBtn.setOnClickListener(new MyButtonListener(position,name));
	            holder.refuseBtn.setOnClickListener(new MyButtonListener(position,name));
	           // holder.talkBtn.setOnClickListener(new MyButtonListener(position));
	        }        
	        return convertView;
	    }
	    /*
	     * 处理按钮点击事件
	     */
	    class MyButtonListener implements OnClickListener {
	        private int position;
	        private String userId;

	        MyButtonListener(int pos,String user) {
	            position = pos;
	            userId=user;
	        }
	        
	        @Override
	        public void onClick(View v) {
	            int vid=v.getId();
	            if (vid == holder.acceptBtn.getId())
	            {
	            	Toast.makeText(HurryPositionShare.this, "添加成功!" ,Toast.LENGTH_LONG).show();
	            	//更新申请列表
	            	updateFriendApplication(userId,"no");
	            	//添加好友到分组
	            	try {
						XmppTool.getConnection().getRoster().createEntry(userId+"@hnu-pc", userId, new String[]{"同学"});
					} catch (XMPPException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	            else if(vid == holder.refuseBtn.getId())
	            {
		            Toast.makeText(HurryPositionShare.this, "您拒绝了对方的添加请求!" ,Toast.LENGTH_LONG).show();
	            }
	            else
	            {
	            	Toast.makeText(HurryPositionShare.this, "error" ,Toast.LENGTH_LONG).show();
	            	updateFriendApplication(userId,"no");
	            }
	        }
	    }
	}
	
	// 监听是否数据库数据发生变化，刷新页面  
	  private BroadcastReceiver mRefreshBroadcastReceiver = new BroadcastReceiver() {  
	  
	      @Override  
	      public void onReceive(Context context, Intent intent) {  
	          String action = intent.getAction();  
	          app.currentTalkObj=intent.getStringExtra(FRIEND_ID);
	          
	          if (action.equals(BROADCAST_FRIEND_INFO))  
	          {  
	        	  updateList(intent.getStringExtra(FRIEND_ID),intent.getStringExtra(FRIEND_MESSAGE));
	        	  //Toast.makeText(HurryPositionShare.this, intent.getStringExtra(FRIEND_ID)+","+intent.getStringExtra(FRIEND_MESSAGE) ,Toast.LENGTH_LONG).show();
	          } 
	          else if(action.equals(BROADCAST_ADD_FRIEND))
	          {
	        	  updateFriendApplication(intent.getStringExtra(FRIEND_ID),"yes");
	        	  app.currentApplicationNum++;
	          }
	      }  
	  };  
}

