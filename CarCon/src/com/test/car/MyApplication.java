package com.test.car;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.database.Cursor;
import android.util.Log;

public class MyApplication extends Application{
	private List<Activity> mList = new LinkedList<Activity>();  
	 
	private DBAdapter db;
	private int userID=0,userPos=0;
	//
	//private static MyApplication instance;
	//BMapManager mBMapMan = null;
	//String mStrKey = "GLAvkyvMdIqKtCE0dKh7WnWV";
	 public void addActivity(Activity activity) {  
	        mList.add(activity);  
	    }  
	  
    public void exit() {  
        try {  
            for (Activity activity : mList) {  
                if (activity != null)  
                    activity.finish();  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            System.exit(0);  
        }  
    }  
  
    @Override  
    public void onLowMemory() {  
        super.onLowMemory();      
        System.gc();  
    }  
	    
	public void onCreate() {
        super.onCreate();
        db= new DBAdapter(this);
        Log.i("create", "success!");
        //instance = this;
		//mBMapMan = new BMapManager(this);
		//mBMapMan.init(this.mStrKey, new MyGeneralListener());
	}
	//返回数据库
	public DBAdapter getDataBase()
	{
		return db;
	}
	//插入数据库中的项
	public boolean insertUser(String account,String password,String tel)
	{
		db.open();
		long id;
		if(db.insertUser(account, password,tel)<0)
		 {
			 db.close();
			 Log.e("insert", "insertfail!");
			 return false;
		 }
	 db.close();
	 return true;
	}
	//
	/*
	public Cursor getUsrInfo()
	{
		Cursor cur;
		db.open();
		cur=db.getUser(userID);
		return cur;
	}*/
	//更新数据库中的项
	public boolean updateUser(String id,String account,
			String password,String portrait,String name,String tel,String address,
			String brand,String type,String plate)
	{
		db.open();
		if(!db.updateUser(Integer.parseInt(id),account,password,portrait,name,tel,address,brand,type,plate ))
		 {
			db.close();
			 Log.e("update", "updatefail!");
			 return false;
		 }
	 db.close();
	 return true;
	}
	//删除数据库中的项
	public boolean deleteUsr(String id)
	{
		db.open();
		if(!db.deleteUser(Integer.parseInt(id)))
		 {
			db.close();
			 Log.e("delete", "deletefail!");
			 return false;
		 }
		db.close();
	 return true;
	}
	//
	public boolean getUserCheck(String account ,String psd)
	{
		db.open();
		Cursor cur = db.getAllUser();
		while (cur.moveToNext()) {
			for (int i = 0; i < cur.getCount(); i++) {
				cur.moveToPosition(i);
				userID=i;
				userPos=Integer.parseInt(cur.getString(0));
				String accountStr = cur.getString(1);
				String psdStr = cur.getString(2);
				if(accountStr.equals(account)&&psdStr.equals(psd))
					return true;
			}
		}
		Log.i("query", "query success!");
		db.close();
		return false;
	}
	//
	public int getUserID()
	{
		return userID;
	}
	
	public String getUserAccount()
	{
		String name;
		db.open();
		Cursor cur = db.getAllUser();
		cur.moveToPosition(userID);
		name=cur.getString(4);
		Log.i("query", "get information success!");
		db.close();
		
		return name;
	}
	
	public String getUserTel()
	{
		String name;
		db.open();
		Cursor cur = db.getAllUser();
		cur.moveToPosition(userID);
		name=cur.getString(5);
		Log.i("query", "get information success!");
		db.close();
		
		return name;
	}
	
	public void updateUserInfo(String account,String tel)
	{
		db.open();
		db.updateInfo(userPos,account,tel);
		db.close();
	}
	/*
	//获取数据库的值
	public List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		db.open();
		Cursor cur = db.getAllUser();
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
	*/
	/*
	public BMapManager getMapManager() {
		return mBMapMan;
	}

	// 在初始化地图Activity时，注册一般事件监听，并实现MKGeneralListener的接口处理相应事件
	public static class MyGeneralListener implements MKGeneralListener {
		// 返回网络错误，通过错误代码判断原因，MKEvent中常量值。
		public void onGetNetworkState(int arg0) {
			Log.d("MyGeneralListener", "onGetNetworkState error is " + arg0);
			Toast.makeText(MyApplication.getInstance(), "GetNetworkState",
					Toast.LENGTH_LONG).show();
		}

		// 返回授权验证错误，通过错误代码判断原因，MKEvent中常量值。
		public void onGetPermissionState(int iError) {
			Log.d("MyGeneralListener", "onGetPermissionState error is " + iError);
			if (iError == MKEvent.ERROR_PERMISSION_DENIED) {
				Toast.makeText(MyApplication.getInstance(),
						"permissionDenied", Toast.LENGTH_LONG).show();
			}
		}

	}

	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		if (mBMapMan != null) {
			mBMapMan.destroy();
			mBMapMan = null;
		}
		super.onTerminate();
	}

	public static MyApplication getInstance() {
		return instance;
	}
	*/
}
