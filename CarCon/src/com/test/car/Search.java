package com.test.car;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

public class Search extends Activity{
	private AutoCompleteTextView searchDes;
	private Button searchBtn;
	private TextView back;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.search);
		this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.searchtitlebar);
		
		searchDes=(AutoCompleteTextView)this.findViewById(R.id.search_des);
		searchBtn=(Button)this.findViewById(R.id.search_btn);
		searchBtn.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(Search.this, MapShow.class);
				//startActivity(intent);
				intent.putExtra("searchInfo", searchDes.getText().toString());
				setResult(RESULT_OK, intent); //intent为A传来的带有Bundle的intent，当然也可以自己定义新的Bundle
				finish();
			}
        });
		//
		back=(TextView)this.findViewById(R.id.search_back);
		back.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(Search.this, MapShow.class);
				startActivity(intent);
				finish();
			}
        });
		//
		 ArrayAdapter<?> aAdapter = ArrayAdapter.createFromResource(this, R.array.search_place,android.R.layout.simple_spinner_dropdown_item);
		 aAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		 searchDes.setAdapter(aAdapter);
	}
}
