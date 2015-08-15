package com.test.pptcontrol;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Application;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.util.Log;

public class MyApplication extends Application{
	private DBAdapter db;
	private int index[];
    
	public void onCreate() {
        super.onCreate();
        db= new DBAdapter(this);
        Log.i("create", "success!");
        /*
      //初始化缓存对象.
  		mSharedPreferences = getSharedPreferences(getPackageName(),
  						MODE_PRIVATE);
  		ipAddress=mSharedPreferences.getString(
   				getString(R.string.set_ip),
   				getString(R.string.ip_default));
       mPort=mSharedPreferences.getString(
			getString(R.string.set_port),
			getString(R.string.port_default));
      */
  	//新建并启动CustomThread实例
  		//new CustomThread().start();
	}
	//返回数据库
	public DBAdapter getDataBase()
	{
		return db;
	}
	//插入数据库中的项
	public boolean insertSong(String singer,String name)
	{
		db.open();
		long id;
		if(db.insertSong(singer,name)<0)
		 {
			 db.close();
			 Log.e("insert", "insertfail!");
			 return false;
		 }
	 db.close();
	 return true;
	}
	//更新数据库中的项
	public boolean updataSong(String id,String singer,String name)
	{
		db.open();
		if(!db.updateSong(Integer.parseInt(id),singer,name ))
		 {
			db.close();
			 Log.e("update", "updatefail!");
			 return false;
		 }
	 db.close();
	 return true;
	}
	//删除数据库中的项
	public boolean deleteSong(String id)
	{
		db.open();
		if(!db.deleteSong(Integer.parseInt(id)))
		 {
			db.close();
			 Log.e("delete", "deletefail!");
			 return false;
		 }
		db.close();
	 return true;
	}
	//获取数据库的值
	public List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		db.open();
		Cursor cur = db.getAllSong();
		while (cur.moveToNext()) {
			index=new int [cur.getCount()];
			for (int i = 0; i < cur.getCount(); i++) {
				cur.moveToPosition(i);
				String code=""+i;//cur.getString(0);
				String singer = cur.getString(1);
				String name = cur.getString(2);

				map = new HashMap<String, Object>();

				map.put("img", R.drawable.user);
				map.put("code", code);
				map.put("singer", singer);
				map.put("name", name);
				list.add(map);
				//
				index[i]=Integer.parseInt(cur.getString(0));
			}
		}
		Log.i("query", "query success!");
		db.close();
		return list;
	}
	//
	public int getIndex(int x)
	{
		return index[x];
	}
}
