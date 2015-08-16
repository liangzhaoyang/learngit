package com.wificar.wenjorin;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ControlConfig extends Activity
{
  public static final String PREFS_NAME = "WifiRobot";
  private String DEFULT_PRES = "0";
  private EditText MDown;
  private EditText MEngineDown;
  private EditText MEngineUp;
  private EditText MLeft;
  private EditText MRight;
  private EditText MStop;
  private EditText MUp;
  private Button MbuttonCancle;
  private Button MbuttonOK;
  private String temp;

  private String StrToBinstr(String paramString)
  {
    char[] arrayOfChar = paramString.toCharArray();
    String str = "";
    for (int i = 0; ; i++)
    {
      if (i >= arrayOfChar.length)
        return str;
      str = str + Integer.toBinaryString(arrayOfChar[i]);
    }
  }

  private void onSave()
  {
	  if (TextUtils.isEmpty(this.MUp.getText().toString()))	   
	      {
		  String str1 = this.MUp.getText().toString();
	      Log.v("a", str1);
		  setPreferences("Up", str1);
	      }
	  if (!TextUtils.isEmpty(this.MDown.getText().toString()))	       
	      {
		  String str2 = this.MDown.getText().toString();
	      Log.v("b", str2);
	      setPreferences("Down", str2);
	      }
	  if (!TextUtils.isEmpty(this.MLeft.getText().toString()))	        
	      {
		  String str3 = this.MLeft.getText().toString();
	      Log.v("c", str3);
	      setPreferences("Left", str3);
	      }
	  if (!TextUtils.isEmpty(this.MRight.getText().toString()))
	      {
		  String str4 = this.MRight.getText().toString();
	      Log.v("c", str4);
	      setPreferences("Right", str4);
	      }
	  if (!TextUtils.isEmpty(this.MStop.getText().toString()))
	      {
		  String str5 = this.MStop.getText().toString();
	      Log.v("4", str5);
	      setPreferences("Stop", str5);
	      }
	  if (!TextUtils.isEmpty(this.MEngineUp.getText().toString()))
	      {
		  String str6 = this.MEngineUp.getText().toString();
	      Log.v("4", str6);
	      setPreferences("EngineUp", str6);
	      }
	  if (!TextUtils.isEmpty(this.MEngineDown.getText().toString()))
	      {
		  String str7 = this.MEngineDown.getText().toString();
	      Log.v("4", str7);
	      setPreferences("EngineDown", str7);
	      }
  }

  private void onShow()
  {
    SharedPreferences localSharedPreferences = getSharedPreferences("WifiRobot", 0);
    String str1 = localSharedPreferences.getString("Up", this.DEFULT_PRES);
    this.MUp.setText(str1);
    String str2 = localSharedPreferences.getString("Down", this.DEFULT_PRES);
    this.MDown.setText(str2);
    String str3 = localSharedPreferences.getString("Left", this.DEFULT_PRES);
    this.MLeft.setText(str3);
    String str4 = localSharedPreferences.getString("Right", this.DEFULT_PRES);
    this.MRight.setText(str4);
    String str5 = localSharedPreferences.getString("Stop", this.DEFULT_PRES);
    this.MStop.setText(str5);
    String str6 = localSharedPreferences.getString("EngineUp", this.DEFULT_PRES);
    this.MEngineUp.setText(str6);
    String str7 = localSharedPreferences.getString("EngineDown", this.DEFULT_PRES);
    this.MEngineDown.setText(str7);
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
    setContentView(R.layout.configforcontrol);
    this.MbuttonOK = ((Button)findViewById(R.id.controlsave));
    this.MbuttonCancle = ((Button)findViewById(R.id.controlcancel));
    this.MUp = ((EditText)findViewById(R.id.up));
    this.MDown = ((EditText)findViewById(R.id.down));
    this.MLeft = ((EditText)findViewById(R.id.left));
    this.MRight = ((EditText)findViewById(R.id.right));
    this.MStop = ((EditText)findViewById(R.id.pause));
    this.MEngineUp = ((EditText)findViewById(R.id.engineup));
    this.MEngineDown = ((EditText)findViewById(R.id.enginedown));
    onShow();
    this.temp = StrToBinstr(this.MUp.getText().toString());
    this.MbuttonOK.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        ControlConfig.this.onSave();
        Intent localIntent = new Intent();
        localIntent.setClass(ControlConfig.this, Welcome.class);
        ControlConfig.this.startActivity(localIntent);
        ControlConfig.this.finish();
      }
    });
    this.MbuttonCancle.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        Intent localIntent = new Intent();
        localIntent.setClass(ControlConfig.this, Welcome.class);
        ControlConfig.this.startActivity(localIntent);
        ControlConfig.this.finish();
      }
    });
  }
}
