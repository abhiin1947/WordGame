package com.imorph.wordgame;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class HiScore extends Activity{
	@SuppressLint("WorldReadableFiles")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hisc);
		TextView a1 = (TextView)findViewById(R.id.textV2);
		TextView a2 = (TextView)findViewById(R.id.textV4);
		SharedPreferences myPrefs = getSharedPreferences(
				"highScorePref", MODE_WORLD_READABLE);
	    a1.setText(myPrefs.getString("hiscore_name", "NO NAME"));
	    a2.setText(myPrefs.getString("hiscore", "NO SCORE"));
	    
	    findViewById(R.id.playFromHiScore).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						Game.class);
				startActivity(intent);
				finish();
			}
		});
	}

}
