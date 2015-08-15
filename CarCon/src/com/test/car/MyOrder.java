package com.test.car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MyOrder extends Activity{
	private Button mBtnSubmit;
	private ListView list;
	private SimpleAdapter useradapter;
	private List<Map<String, Object>> listdata;
	private TextView back;
	
	private final String LIST_STORE_INFO="list_store_info"; 
	
	private SharedPreferencesHelper sp;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyApplication app=(MyApplication)getApplication();
		app.addActivity(this);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.myorder);
		
		//获取保存的密码和用户名
		sp=new SharedPreferencesHelper(this,"positionInfo");
				
		back=(TextView)this.findViewById(R.id.order_back);
		back.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(MyOrder.this, MileShow.class);
				startActivity(intent);
				finish();
			}
        });
		
		list=(ListView) findViewById(R.id.users);
		listdata = new ArrayList<Map<String, Object>>();
		Map<String, Object> map;
		if(sp.getValue(LIST_STORE_INFO).equals(""))
		{
			map = new HashMap<String, Object>();
			map.put("img", R.drawable.list_bar1);
			map.put("shop", "上海华星大众4S店");
			map.put("tel", "021 6362 7055");
			map.put("comment", R.drawable.ucomment);
			map.put("flag", "0");
			listdata.add(map);
			
			map = new HashMap<String, Object>();
			map.put("img", R.drawable.list_bar);
			map.put("shop", "上海大众汽车有限公司");
			map.put("tel", "021 6362 7055");
			map.put("comment", R.drawable.comment);
			map.put("flag", "3");
			listdata.add(map);
			
			map = new HashMap<String, Object>();
			map.put("img", R.drawable.list_bar1);
			map.put("shop", "上海大众汽车特约维修店");
			map.put("tel", "021 6362 7055");
			map.put("comment", R.drawable.comment);
			map.put("flag", "4.5");
			listdata.add(map);
			//
			storeList();
		}
		else
		{
			int i,j;
			String[] userItem=sp.getValue(LIST_STORE_INFO).split("#");
			for(i=0;i<userItem.length;i++)
			{
				if(!userItem[i].equals(""))
				{
					String[] userInfo=userItem[i].split(",");
					
					map = new HashMap<String, Object>();
					if(i%2==0)
						map.put("img", R.drawable.list_bar1);
					else
						map.put("img", R.drawable.list_bar);
					map.put("shop", userInfo[0]);
					map.put("tel", userInfo[1]);
					map.put("flag", userInfo[2]);
					if(userInfo[2].equals("0"))
						map.put("comment", R.drawable.ucomment);
					else
						map.put("comment", R.drawable.comment);
					listdata.add(map);
				}
			}
		}
		/*
		for (int i = 0; i < 10; i++) {
			map = new HashMap<String, Object>();
	
			map.put("img", R.drawable.list_bar);
			map.put("shop", "芒果店"+i);
			map.put("tel", "1367890"+i);
			map.put("comment", R.drawable.comment);
			
			listdata.add(map);
		}
		*/
		  //添加adapter适配器
		useradapter = new SimpleAdapter(this, listdata,
				R.layout.user, new String[] { "img", "shop","tel","comment","flag" },
				new int[] { R.id.custom_img,R.id.list_shop, R.id.list_tel,R.id.list_comment,R.id.list_flag
						});
		list.setAdapter(useradapter);
		
		list.setOnItemClickListener(new OnItemClickListener()
		{
		       @Override
		      public void onItemClick(AdapterView<?> parent, View view, int position, long id)
		      {
		    	   storeList();
		    	   //
		    	   ListView listView = (ListView)parent;
		    	    HashMap<String, String> map = (HashMap<String, String>) listView.getItemAtPosition(position);
		    	    //获取表项中的值
		    	    String flag = map.get("flag");
		    	    Intent intent=new Intent();
		    	   
		    	    try{
		    	    	if(flag.equals("0"))
		    	    	{
		    	    		intent.putExtra("shop", map.get("shop"));
		 					intent.setClass(MyOrder.this, Comment.class);
		 					//startActivity(intent);
		 					startActivityForResult(intent, 0);
		    	    		//Toast.makeText(MyOrder.this, "click" ,Toast.LENGTH_LONG).show();
		    	    		Log.i("order", "click");
		    	    	}
		    	    	else
		    	    	{
		    	    		intent.putExtra("shop", map.get("shop"));
		    	    		intent.putExtra("flag", map.get("flag"));
		 					intent.setClass(MyOrder.this, CommentShow.class);
		 					//startActivity(intent);
		 					startActivity(intent);
		    	    	}
		    	    }catch(Exception e)
		    	    {
		    	    	e.printStackTrace();
		    	    	Log.i("order", "error");
		    	    }
		    	    
		    	   /*
		    	   // Toast.makeText(HurryPositionShare.this, accountStr, Toast.LENGTH_LONG).show();
		    	    Intent intent = new Intent();
					intent.setClass(HurryPositionShare.this, HurryTalkOne.class);
					intent.putExtra("USERID", pUSERID);
					intent.putExtra("COUNTERPART", accountStr);
					startActivity(intent);
					*/
		    }
		});
	}
	
	//更新用户列表数据
	private void updateList(String usrId,String info)
	{
		//获取用户的ID
		for(Map<String, Object> map : listdata){
			 if(map.get("shop").equals(usrId))
			 {
				 map.put("flag", info);
				 map.put("comment", R.drawable.comment);
			 }
		}
		useradapter.notifyDataSetChanged();
	}
	//
	private void storeList()
	{
		String str="";
		//获取用户的ID
		for(Map<String, Object> map : listdata){
			str=str+map.get("shop")+","+map.get("tel")+","+map.get("flag")+"#";
		}
		//
		sp.putValue(LIST_STORE_INFO, str);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
         case RESULT_OK:
        	 updateList(data.getStringExtra("shop"),data.getStringExtra("info"));          
             /*Toast.makeText(MyOrder.this,data.getStringExtra("info"), Toast.LENGTH_LONG)
				.show();
             */
                      break;
        default:
               break;
        }
    }
	
}
