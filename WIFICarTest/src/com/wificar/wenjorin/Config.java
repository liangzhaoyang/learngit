package com.wificar.wenjorin;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Config extends Activity
{
  public static final String PREFS_NAME = "WifiRobot";
  public String ControlIP = "";
  //private String DEFULT_PRES = "http://192.168.1.1:8080/?action=snapshot";
  private Button MbuttonCancle;
  private Button MbuttonOK;
  private EditText MeditPort;
  private EditText MeditTextControl;
  private EditText MeditTextVideo;
  public String Port = "";
  public String VideoIP = "";

  private void onSave()
  {
	  if (!TextUtils.isEmpty(this.MeditTextVideo.getText().toString()))
	      setPreferences("VideoIP", this.MeditTextVideo.getText().toString());
	  if (!TextUtils.isEmpty(this.MeditTextControl.getText().toString()))
	      setPreferences("ControlIP", this.MeditTextControl.getText().toString());
	  if (!TextUtils.isEmpty(this.MeditPort.getText().toString()))
	  	  setPreferences("Port", this.MeditPort.getText().toString());
  }

  private void onShow()
  {
    SharedPreferences localSharedPreferences = getSharedPreferences("WifiRobot", 0);
    String str1 = localSharedPreferences.getString("VideoIP", null);
    this.MeditTextVideo.setText(str1);
    String str2 = localSharedPreferences.getString("ControlIP", null);
    this.MeditTextControl.setText(str2);
    String str3 = localSharedPreferences.getString("Port", null);
    this.MeditPort.setText(str3);
  }

  private void setPreferences(String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = getSharedPreferences("WifiRobot", 0).edit();
    localEditor.putString(paramString1, paramString2);
    localEditor.commit();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.configlayout);
    this.MbuttonOK = ((Button)findViewById(R.id.networksave));
    this.MbuttonCancle = ((Button)findViewById(R.id.networkcancel));
    this.MeditTextVideo = ((EditText)findViewById(R.id.videoAdressSite));
    this.MeditTextControl = ((EditText)findViewById(R.id.Controlsite));
    this.MeditPort = ((EditText)findViewById(R.id.Controlport));
    onShow();
    this.VideoIP = this.MeditTextVideo.getText().toString();
    this.ControlIP = this.MeditTextControl.getText().toString();
    this.Port = this.MeditPort.getText().toString();
    this.MbuttonOK.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        Config.this.onSave();
        Intent localIntent = new Intent();
        localIntent.setClass(Config.this, Welcome.class);
        Config.this.startActivity(localIntent);
        Config.this.finish();
      }
    });
    this.MbuttonCancle.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        Intent localIntent = new Intent();
        localIntent.setClass(Config.this, Welcome.class);
        Config.this.startActivity(localIntent);
        Config.this.finish();
      }
    });
  }
}
