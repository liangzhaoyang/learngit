package com.wificar.wenjorin;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback
{
private static int mScreenHeight;
private static int mScreenWidth;
public static String str = "WIFI Robot 提示viking";
private int ScreenH;
private int ScreenW;
Bitmap bmp;
private Canvas canvas;
HttpURLConnection conn;
InputStream inputstream = null;
private Bitmap mBitmap;
private int move_x = 2;
private Paint p;
private Paint paint;
private SurfaceHolder sfh;
public String url = "http://192.168.1.1:8080/?action=snapshot";
URL videoUrl;
private int x = 80;

public MySurfaceView(Context paramContext, AttributeSet paramAttributeSet)
{
  super(paramContext, paramAttributeSet);
  initialize();
  this.p = new Paint();
  this.p.setAntiAlias(true);
  this.sfh = getHolder();
  this.sfh.addCallback(this);
  setKeepScreenOn(true);
  setFocusable(true);
  this.ScreenW = getWidth();
  this.ScreenH = getHeight();
}

private void initialize()
{
  DisplayMetrics localDisplayMetrics = getResources().getDisplayMetrics();
  mScreenWidth = localDisplayMetrics.widthPixels;
  mScreenHeight = localDisplayMetrics.heightPixels;
}

public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
{
  return super.onKeyDown(paramInt, paramKeyEvent);
}

public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1, int paramInt2, int paramInt3)
{
}

public void surfaceCreated(SurfaceHolder paramSurfaceHolder)
{
  new DrawVideo().start();
}

public void surfaceDestroyed(SurfaceHolder paramSurfaceHolder)
{
}

class DrawVideo extends Thread
{
  public DrawVideo()
  {
  }

  public void run()
  {
    while (true)
      try
      {
        MySurfaceView.this.videoUrl = new URL(MySurfaceView.this.url);
        MySurfaceView.this.conn = ((HttpURLConnection)MySurfaceView.this.videoUrl.openConnection());
        Log.v("提示", "视频地址" + MySurfaceView.this.url);
        MySurfaceView.this.conn.connect();
        Log.v("提示", "连接成功");
        Log.v("提示", "正在获取视频流！");
        MySurfaceView.this.inputstream = MySurfaceView.this.conn.getInputStream();
        Log.v("提示", "视频流获取成功！");
        MySurfaceView.this.bmp = BitmapFactory.decodeStream(MySurfaceView.this.inputstream);
        Log.v("提示", "图像获取成功！");
        MySurfaceView.this.mBitmap = Bitmap.createScaledBitmap(MySurfaceView.this.bmp, MySurfaceView.mScreenWidth, MySurfaceView.mScreenHeight, true);
        MySurfaceView.this.canvas = MySurfaceView.this.sfh.lockCanvas();
        MySurfaceView.this.canvas.drawColor(-1);
        MySurfaceView.this.canvas.drawBitmap(MySurfaceView.this.mBitmap, 0.0F, 0.0F, null);
        MySurfaceView.this.sfh.unlockCanvasAndPost(MySurfaceView.this.canvas);
        continue;
      }
      catch (Exception localException)
      {
        Log.v("提示", "视频流出错！");
        localException.printStackTrace();
      }
  }
}
}
