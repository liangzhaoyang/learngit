package com.wificar.wenjorin;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends Activity
implements View.OnClickListener
{
public static final String PREFS_NAME = "WifiRobot";
String Controlsite = "";
private String DEFULT_PRES = "0";
public String HexString;
private StreamEngine IOEngine;
private MySurfaceView MSurfaceView;
private MySurfaceView2 MSurfaceView2;
String Videosite = "";
private Button btnBackward;
private Button btnConfig;
private Button btnEngineDown;
private Button btnEngineUp;
private Button btnForward;
private Button btnLeft;
private Button btnRight;
private Button btnStop;
public int cmdFlag = 0;
public int cmdStyle = 0;
private String down;
private String left;

public CheckBox mCheckbox;
public RadioGroup mRadioGroup1;
public RadioButton mRadioHex;
public RadioButton mRadioStr;
public int port;
private String right;
private String scrolldown;
private String scrollup;
private SensorManager sensorMgr;
private Socket socket;
private String stop;
private String up;
private float x;
private float y;
private float z;

private RadioGroup.OnCheckedChangeListener mChangeRadio = new RadioGroup.OnCheckedChangeListener()
{
  public void onCheckedChanged(RadioGroup paramRadioGroup, int paramInt)
  {
    if (paramInt == mRadioStr.getId())
    	cmdFlag = 0;
    if (paramInt == MainActivity.this.mRadioHex.getId())
    {
    	cmdFlag = 1;      
    }    
  }};

private void onShow()
{//获得设置好的数据
  SharedPreferences localSharedPreferences = getSharedPreferences("WifiRobot", 0);
  this.Videosite = localSharedPreferences.getString("VideoIP", this.DEFULT_PRES);
  this.Controlsite = localSharedPreferences.getString("ControlIP", this.DEFULT_PRES);
  this.port = Integer.parseInt(localSharedPreferences.getString("Port", this.DEFULT_PRES));
  this.up = localSharedPreferences.getString("Up", this.DEFULT_PRES);
  this.down = localSharedPreferences.getString("Down", this.DEFULT_PRES);
  this.left = localSharedPreferences.getString("Left", this.DEFULT_PRES);
  this.right = localSharedPreferences.getString("Right", this.DEFULT_PRES);
  this.stop = localSharedPreferences.getString("Stop", this.DEFULT_PRES);
  this.scrollup = localSharedPreferences.getString("EngineUp", this.DEFULT_PRES);
  this.scrolldown = localSharedPreferences.getString("EngineDown", this.DEFULT_PRES);
}

public void BackTOWelcome()
{
  mCheckbox.setChecked(false);
  Intent localIntent = new Intent();
  localIntent.setClass(this, Welcome.class);
  startActivity(localIntent);
  finish();
}

public void InitSocket()
{
  try
  {
    this.socket = new Socket(this.Controlsite, this.port);
    
  }
  catch (UnknownHostException localUnknownHostException)
  {    
  }
  catch (IOException localIOException)
  {   
  }
}

public void SetMSurfaceView2()
{
  MSurfaceView2 = ((MySurfaceView2)findViewById(R.id.MySurfaceView2));
  //MSurfaceView2.setZOrderOnTop(true);
  //MSurfaceView2.set
  MSurfaceView2.getHolder().setFormat(PixelFormat.TRANSPARENT);
  MSurfaceView2.up = up;
  MSurfaceView2.down = down;
  MSurfaceView2.left = left;
  MSurfaceView2.right = right;
  MSurfaceView2.down = down;
  MSurfaceView2.stop =stop;
  MSurfaceView2.Controlsite = Controlsite;
  MSurfaceView2.Videosite =Videosite;
  MSurfaceView2.port = port;
  MSurfaceView2.socket2 = socket;
}

public void closeSocket()
{
  try
  {
    this.socket.close();  
  }
  catch (IOException localIOException)
  {    
      localIOException.printStackTrace();
  }
}

public void onClick(View paramView)
{
  if ((paramView != this.btnForward) && (paramView != this.btnBackward) && (paramView != this.btnLeft) && (paramView != this.btnRight));
}

public void onCreate(Bundle paramBundle)
{
  super.onCreate(paramBundle);
  requestWindowFeature(1);
  getWindow().setFlags(1024, 1024);
  setContentView(R.layout.main);
  
  IOEngine = new StreamEngine();
  onShow();
  InitSocket();
  SetMSurfaceView2();
  
  mCheckbox = ((CheckBox)findViewById(R.id.gravityTouch));
  btnConfig = ((Button)findViewById(R.id.setup));
  btnConfig.setOnClickListener(new View.OnClickListener()
  {
    public void onClick(View paramView)
    {
      Intent localIntent = new Intent();
      localIntent.setClass(MainActivity.this, Welcome.class);
      MainActivity.this.startActivity(localIntent);
      MainActivity.this.finish();
    }
  });
  
  this.btnEngineUp = ((Button)findViewById(R.id.engineup1));
  this.btnEngineUp.setOnClickListener(new View.OnClickListener()
  {
    public void onClick(View paramView)
    {//设置发送数据的格式
      MainActivity.this.IOEngine.sendMsg(socket, scrollup, cmdFlag);
    }
  });
  
  this.btnEngineDown = ((Button)findViewById(R.id.engineup1));
  this.btnEngineDown.setOnClickListener(new View.OnClickListener()
  {
    public void onClick(View paramView)
    {
      IOEngine.sendMsg(socket, scrolldown, cmdFlag);
    }
  });
  
  this.mCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
  {
    public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
    {
      mCheckbox.isChecked();
    }
  });
  
  this.MSurfaceView = ((MySurfaceView)findViewById(R.id.MySurfaceView));
  this.MSurfaceView.url = this.Videosite;
  this.sensorMgr = ((SensorManager)getSystemService("sensor"));
  Sensor localSensor = this.sensorMgr.getDefaultSensor(1);
  SensorEventListener localSe = new SensorEventListener()
  {
    public void onAccuracyChanged(Sensor paramSensor, int paramInt)
    {
    }

    public void onSensorChanged(SensorEvent paramSensorEvent)
    {
      MainActivity.this.x = paramSensorEvent.values[0];
      MainActivity.this.y = paramSensorEvent.values[1];
      MainActivity.this.z = paramSensorEvent.values[2];
      	if (mCheckbox.isChecked())
      		cmdStyle = 0;      	
        if (x >= 2.0F)          
        	cmdStyle = 1;        
        if (x > 7.0F)
            cmdStyle = 2;              
        if (y < -1.0F)
            cmdStyle = 3;       
        if (y > 1.0F)
            cmdStyle = 4;         
            
        switch (cmdStyle)
        {       
        case 0:IOEngine.sendMsg(socket,stop,cmdFlag);break;
        case 1:IOEngine.sendMsg(socket,up,cmdFlag);break;
        case 2:IOEngine.sendMsg(socket,down,cmdFlag);break;
        case 3:IOEngine.sendMsg(socket,left,cmdFlag);break;
        case 4:IOEngine.sendMsg(socket,right,cmdFlag);break;
        default:
        } 
    }  };
  
  this.sensorMgr.registerListener(localSe, localSensor, 1);
  this.mRadioGroup1 = ((RadioGroup)findViewById(R.id.radioGroup1));
  this.mRadioGroup1.setOnCheckedChangeListener(this.mChangeRadio);
  this.mRadioStr = ((RadioButton)findViewById(R.id.characterControl));
  this.mRadioHex = ((RadioButton)findViewById(R.id.instructedControl));
}

protected void onDestroy()
{
  super.onDestroy();
}

public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
{
  this.IOEngine.sendMsg(this.socket, this.stop, this.cmdFlag);
  return super.onKeyUp(paramInt, paramKeyEvent);
}

public boolean onCreateOptionsMenu(Menu paramMenu)
{
  getMenuInflater().inflate(R.menu.option_menu, paramMenu);
  return true;
}

public boolean onOptionsItemSelected(MenuItem paramMenuItem)
{
  int i;
  super.onOptionsItemSelected(paramMenuItem);
  switch (paramMenuItem.getItemId())
  {  
  case R.id.new_game:
	  startActivity(new Intent(this, Welcome.class));
	    finish();	    
	  return true;
  case R.id.help://路径必须指定完全
	  android.os.Process.killProcess(android.os.Process.myPid());	    
	  return true;
  default:
	  return super.onOptionsItemSelected(paramMenuItem);
  }
}
}