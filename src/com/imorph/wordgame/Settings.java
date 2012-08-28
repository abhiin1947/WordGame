package com.imorph.wordgame;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;


public class Settings extends Activity {
	ArrayList<RadioButton> rb;
	SeekBar sb;
	void unCheckAll()
	{
		rb.get(0).setChecked(false);
		rb.get(1).setChecked(false);
		rb.get(2).setChecked(false);
		rb.get(3).setChecked(false);
	}
	@SuppressLint("WorldReadableFiles")
	void initSettings()
	{
		rb = new ArrayList<RadioButton>();
		rb.add(0,(RadioButton)findViewById(R.id.radioButton1));
		rb.add(1,(RadioButton)findViewById(R.id.radioButton2));
		rb.add(2,(RadioButton)findViewById(R.id.radioButton3));
		rb.add(3,(RadioButton)findViewById(R.id.radioButton4));
		sb = (SeekBar)findViewById(R.id.seekBar1);
		SharedPreferences myPrefs = getSharedPreferences(
				"highScorePref", MODE_WORLD_READABLE);
		int no_r = myPrefs.getInt("no_of_rounds", -1);
		unCheckAll();
		if(no_r!=-1)
		{
			no_r-=2;
			rb.get(no_r).setChecked(true);
		}
		long time = myPrefs.getLong("time_per_round", -1);
		if(time!=-1)
		{
			int timeInSec = (int)(time/1000);
			sb.setProgress(timeInSec);
			TextView tv = (TextView)findViewById(R.id.tv4);
			tv.setText(timeInSec+" s");
		}
		}
	@SuppressLint({ "WorldWriteableFiles", "CommitPrefEdits" })
	void writeRounds(int r)
	{
		SharedPreferences myPrefs2 = getSharedPreferences(
				"highScorePref", MODE_WORLD_WRITEABLE);
		SharedPreferences.Editor prefsEditor = myPrefs2.edit();
		prefsEditor.putInt("no_of_rounds", r);
		prefsEditor.commit();
	}
	@SuppressLint("WorldWriteableFiles")
	void writeTime(int t)
	{
		long time = t*1000;
		SharedPreferences myPrefs2 = getSharedPreferences(
				"highScorePref", MODE_WORLD_WRITEABLE);
		SharedPreferences.Editor prefsEditor = myPrefs2.edit();
		prefsEditor.putLong("time_per_round", time);
		prefsEditor.commit();
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		initSettings();
		RadioGroup abc = (RadioGroup)findViewById(R.id.radioRounds);
		abc.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if(checkedId == rb.get(0).getId())
				{
					writeRounds(2);
				}
				else if(checkedId == rb.get(1).getId())
				{
					writeRounds(3);
				}
				else if(checkedId == rb.get(2).getId())
				{
					writeRounds(4);
				}
				else if(checkedId == rb.get(3).getId())
				{
					writeRounds(5);
				}
			}
		});
		sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				TextView tv = (TextView)findViewById(R.id.tv4);
				tv.setText(progress+" s");
				writeTime(progress);
			}
		});
		
	}
}
