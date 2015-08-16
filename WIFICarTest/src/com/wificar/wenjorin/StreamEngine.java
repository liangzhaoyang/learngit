package com.wificar.wenjorin;

import java.io.PrintWriter;
import java.net.Socket;

import android.util.Log;

public class StreamEngine
{
  public String HexString;

  private static String StrToBinstr(String paramString)
  {
	  //数据以二进制的形式传输
    char[] arrayOfChar = paramString.toCharArray();
    String str = "";
    for (int i = 0; ; i++)
    {
      if (i >= arrayOfChar.length)
        return str;
      str = str + Integer.toBinaryString(arrayOfChar[i]) + " ";
    }
  }

  public void sendMsg(Socket paramSocket, String paramString, int paramInt)
  {
    if (paramInt == 0)
    	{    	
    	HexString = StrToBinstr(paramString);
	        try
	        {
	          PrintWriter localPrintWriter1 = new PrintWriter(paramSocket.getOutputStream());
	          localPrintWriter1.println(this.HexString);
	          localPrintWriter1.flush();
	          Log.v("提示", "发送成功！" + paramString);
	        }
	        catch (Exception e)
	        {
	        	Log.v("提示", "发送失败！");
	        }
    	}
    else
    {
      try
      {
        PrintWriter localPrintWriter2 = new PrintWriter(paramSocket.getOutputStream());
        localPrintWriter2.println(paramString);
        localPrintWriter2.flush();
        Log.v("提示", "发送成功！" + paramString);       
      }
      catch (Exception localException2)
      {
        Log.v("提示", "发送失败！");       
      }
      
    }
  }
}
