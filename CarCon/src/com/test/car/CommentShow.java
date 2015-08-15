package com.test.car;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class CommentShow extends Activity{
	private RatingBar mRatingBar;
	private TextView  back,showShop,showComment;
	private String shop,flag;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//MyApplication app=(MyApplication)getApplication();
		//app.addActivity(this);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.comment_show);
		
		back=(TextView)this.findViewById(R.id.comment_show_back);
		back.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(CommentShow.this, MyOrder.class);
				startActivity(intent);
				finish();
			}
        });
		//
		shop=this.getIntent().getStringExtra("shop");
		flag=this.getIntent().getStringExtra("flag");
		mRatingBar=(RatingBar)this.findViewById(R.id.show_ratingbar);
		mRatingBar.setRating(Float.parseFloat(flag));
		//
		showShop=(TextView)this.findViewById(R.id.comment_show_shop);
		showShop.setText(shop);
		//
		showComment=(TextView)this.findViewById(R.id.comment_show_result);
		showComment.setText(flag+"·Ö");
		
	}
}
