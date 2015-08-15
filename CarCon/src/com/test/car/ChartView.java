package com.test.car;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class ChartView extends View {

	private int[] data_total ;
	private String [] xlabel;
	private int flag ;
	private int margin ;

	private Chart chart ;
	private Paint paint ;

	public ChartView(Context context,String str[],int data[]) {
		super(context) ;
		margin = 0 ;
		chart = new Chart() ;
		xlabel=str;
		data_total=data;
		paint = new Paint() ;
		paint.setAntiAlias(true) ;
	}

	public void drawAxis(Canvas canvas) {
		paint.setColor(Color.GRAY) ;
		paint.setStrokeWidth(2) ;
		canvas.drawLine(80, 300, 500, 300, paint) ;
		canvas.drawLine(80, 20, 80, 300, paint) ;
		canvas.drawText("七日里程数(km)",100,30,paint);
		int x = 90 ;
		int y = 250 ;

		for (int i = 0; i < 7; i++) {
			canvas.drawText(xlabel[i], x, 320, paint) ;
			x += 60 ;
		}
		
		for (int i = 0; i < 5; i++) {
			canvas.drawText(50 * (i + 1) + "", 80, y, paint) ;
			y -= 50 ;
		}
		
	}

	public void drawChart(Canvas canvas) {
			paint.setColor(Color.argb(255, 0, 0xca, 0xc9)) ;
			int temp = 40 ;
			for (int i = 0; i < 7; i++) {
				chart.setH(data_total[i]) ;
				chart.setX(temp + 20 * 2 + margin) ;
				chart.drawSelf(canvas, paint) ;
				margin = 20 ;
				temp = chart.getX() ;
			}
	}

	public void drawHighLines(Canvas canvas) {
		int[][] highPoints = new int[4][2] ;
		highPoints[0][0] = 90 ;
		highPoints[0][1] = data_total[0] ;
		highPoints[1][0] = 150 ;
		highPoints[1][1] = data_total[1] ;
		highPoints[2][0] = 210 ;
		highPoints[2][1] = data_total[2] ;
		highPoints[3][0] = 270 ;
		highPoints[3][1] = data_total[3] ;
		paint.setColor(Color.RED) ;
		for (int i = 0; i < 7; i++) {
			canvas.drawPoint(highPoints[i][0], 300 - highPoints[i][1], paint) ;
			canvas.drawText(data_total[i] + "", highPoints[i][0] - 10, 300 - highPoints[i][1] - 10,
					paint) ;
		}

		float[] pts = new float[16] ;
		for (int i = 0; i < 12; i++) {
			pts[0] = 32 ;
			pts[1] = 300 - highPoints[0][1] ;
			pts[2] = highPoints[0][0] ;
			pts[3] = 300 - highPoints[0][1] ;
			pts[4] = highPoints[0][0] ;
			pts[5] = 300 - highPoints[0][1] ;
			pts[6] = highPoints[1][0] ;
			pts[7] = 300 - highPoints[1][1] ;
			pts[8] = highPoints[1][0] ;
			pts[9] = 300 - highPoints[1][1] ;
			pts[10] = highPoints[2][0] ;
			pts[11] = 300 - highPoints[2][1] ;
			pts[12] = highPoints[2][0] ;
			pts[13] = 300 - highPoints[2][1] ;
			pts[14] = highPoints[3][0] ;
			pts[15] = 300 - highPoints[3][1] ;
		}
		canvas.drawLines(pts, paint) ;
	}

	@Override
	public void onDraw(Canvas canvas) {
		canvas.drawColor(Color.WHITE) ;
		drawAxis(canvas) ;
		drawChart(canvas) ;
	}
}
