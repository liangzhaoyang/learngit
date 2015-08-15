package com.test.car;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;

public class Comment extends Activity implements
OnRatingBarChangeListener{
	
	private RatingBar mRatingBar1,mRatingBar2,mRatingBar3,mRatingBar4;
	private float rate1,rate2,rate3,rate4;
	private Button complete;
	private String shopValue;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//MyApplication app=(MyApplication)getApplication();
		//app.addActivity(this);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.comment);
		
		shopValue=this.getIntent().getStringExtra("shop");
		
		mRatingBar1=(RatingBar)this.findViewById(R.id.ratingbar1);
		mRatingBar1.setOnRatingBarChangeListener(this);
		//
		mRatingBar2=(RatingBar)this.findViewById(R.id.ratingbar2);
		mRatingBar2.setOnRatingBarChangeListener(this);
		//
		mRatingBar3=(RatingBar)this.findViewById(R.id.ratingbar3);
		mRatingBar3.setOnRatingBarChangeListener(this);
		//
		mRatingBar4=(RatingBar)this.findViewById(R.id.ratingbar4);
		mRatingBar4.setOnRatingBarChangeListener(this);
		
		complete=(Button)this.findViewById(R.id.comment_complete);
		complete.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
				
				Intent intent=new Intent();
				intent.setClass(Comment.this, MyOrder.class);
				//startActivity(intent);
				intent.putExtra("info", ""+(rate1+rate2+rate3+rate4)/4);
				intent.putExtra("shop", shopValue);
				setResult(RESULT_OK, intent); //intent为A传来的带有Bundle的intent，当然也可以自己定义新的Bundle
				finish();					
			}
        });
	}
	
	@Override
	 public void onRatingChanged(RatingBar ratingBar, float rating,
	   boolean fromUser) {
		switch(ratingBar.getId())
		{
		case R.id.ratingbar1:
			if(rate1!=rating)
				rate1=rating;
			break;
		case R.id.ratingbar2:
			if(rate2!=rating)
				rate2=rating;
			break;
		case R.id.ratingbar3:
			if(rate3!=rating)
				rate3=rating;
			break;
		case R.id.ratingbar4:
			if(rate4!=rating)
				rate4=rating;
			break;
		}
	}
}
