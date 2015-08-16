package com.hurry.client.util;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;

public class MyApplication extends Application{
	private List<Activity> mList = new LinkedList<Activity>();
	private List<String> userList=new LinkedList<String>();
	private  String UserID; 
	public int currentApplicationNum=0;
	public String currentTalkObj;
	
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
	 //
	 public void setUserID(String id)
	 {
		 UserID=id;
	 }
	 
	 public String getUserID()
	 {
		 return UserID;
	 }
	 
	 //
	 public void addUserList(String user) {  
		 userList.add(user);  
	    } 
	 
	 public void removeUserList() {  
	     try {  
	         for (String user : userList) {  
	             if (user != null)  
	                 user=null;  
	         }  
	     } catch (Exception e) {  
	         e.printStackTrace();  
	     } 
	 }  
}
