package com.imorph.wordgame;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Game extends Activity {
	String test;
	List<String> alpha;
	ArrayList<String> dict;
	ArrayList<Button> abc;
	int score;
	TextView timer;
	int size;
	int round = 0;
	int dictionary_state;
	boolean intoGame = false;
	ProgressDialog dialog;
	ArrayList<Button> getbutton() {
		ArrayList<Button> buttons = new ArrayList<Button>();
		buttons.add((Button) findViewById(R.id.letter1));
		buttons.add((Button) findViewById(R.id.letter2));
		buttons.add((Button) findViewById(R.id.letter3));
		buttons.add((Button) findViewById(R.id.letter4));
		buttons.add((Button) findViewById(R.id.letter5));
		buttons.add((Button) findViewById(R.id.letter6));
		buttons.add((Button) findViewById(R.id.letter7));
		buttons.add((Button) findViewById(R.id.letter8));
		buttons.add((Button) findViewById(R.id.letter9));
		buttons.add((Button) findViewById(R.id.letter10));
		buttons.add((Button) findViewById(R.id.letter11));
		buttons.add((Button) findViewById(R.id.letter12));
		buttons.add((Button) findViewById(R.id.letter13));
		buttons.add((Button) findViewById(R.id.letter14));
		buttons.add((Button) findViewById(R.id.letter15));
		buttons.add((Button) findViewById(R.id.letter16));
		return buttons;

	}

	List<String> generate() {
		List<String> temp = new ArrayList<String>();
		temp.add("A");
		temp.add("B");
		temp.add("C");
		temp.add("D");
		temp.add("E");
		temp.add("F");
		temp.add("G");
		temp.add("H");
		temp.add("I");
		temp.add("J");
		temp.add("K");
		temp.add("L");
		temp.add("M");
		temp.add("N");
		temp.add("O");
		temp.add("P");
		temp.add("Q");
		temp.add("R");
		temp.add("S");
		temp.add("T");
		temp.add("U");
		temp.add("V");
		temp.add("W");
		temp.add("X");
		temp.add("Y");
		temp.add("Z");
		Calendar cal = Calendar.getInstance();
		Random a = new Random(cal.getTimeInMillis());
		Collections.shuffle(temp, a);
		return temp;
	}

	void transition() {
		LinearLayout name = (LinearLayout) findViewById(R.id.NameLayout);
		ObjectAnimator animation1 = ObjectAnimator.ofFloat(name, "alpha", 0);
		animation1.setDuration(300);
		animation1.start();
		animation1.addListener(new AnimatorListener() {

			public void onAnimationStart(Animator animation) {
				// TODO Auto-generated method stub

			}

			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub

			}

			public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub
				LinearLayout name = (LinearLayout) findViewById(R.id.NameLayout);
				name.setVisibility(View.GONE);
				RelativeLayout game = (RelativeLayout) findViewById(R.id.TheGame);
				game.setVisibility(View.VISIBLE);
				ObjectAnimator animation2 = ObjectAnimator.ofFloat(game, "alpha", 1);
				animation2.setDuration(300);
				animation2.start();
			}

			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub

			}
		});

	}

	void widthSetter() {
		Display display = getWindowManager().getDefaultDisplay();
		@SuppressWarnings("deprecation")
		int width = display.getWidth();
		int i;
		for (i = 0; i < 16; i++) {
			abc.get(i).setWidth(width / size);
		}
	}
	void setGenerated()
	{
		int i;
		for (i = 0; i < 16; i++) {
			abc.get(i).setText(alpha.get(i));
		}
	}
	void setListerners() {
		int i;
		OnClickListener onc = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int id = v.getId();
				int i = 0;
				while (abc.get(i).getId() != id) {
					i++;
				}
				TextView dot = (TextView) findViewById(R.id.one);
				dot.setText(dot.getText().toString() + abc.get(i).getText());
			}
		};
		for (i = 0; i < 16; i++) {
			abc.get(i).setOnClickListener(onc);
		}
		OnClickListener sub = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TextView dot = (TextView) findViewById(R.id.one);
				String input = dot.getText().toString();
				int i;
				boolean isthere = false;
				for (i = 0; i < dict.size(); i++) {
					if (input.compareToIgnoreCase(dict.get(i)) == 0) {
						isthere = true;
					}
				}
				
				if (isthere) {
					if(input.length() <= 3)
						 score+=5;
					else if(input.length() == 4)
						 score+=10;
					if(input.length() == 5)
						 score+=20;
					if(input.length() == 6)
						 score+=50;
					if(input.length() >= 7)
						 score+=100;
					TextView sco = (TextView) findViewById(R.id.textView3);
					sco.setText(Integer.valueOf(score).toString());
				} else {
					Toast.makeText(Game.this, "Word is not there",
							Toast.LENGTH_SHORT).show();
				}
				dot.setText("");
			}
		};
		findViewById(R.id.button1).setOnClickListener(sub);
	}
	void changeText()
	{
		RelativeLayout game = (RelativeLayout) findViewById(R.id.TheGame);
		ObjectAnimator animation2 = ObjectAnimator.ofFloat(game, "alpha", 0);
		animation2.setDuration(300);
		animation2.start();
		animation2.addListener(new AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub
				alpha = generate();
				int i;
				for (i = 0; i < 16; i++) {
					abc.get(i).setText(alpha.get(i));
				}
				RelativeLayout game = (RelativeLayout) findViewById(R.id.TheGame);
				ObjectAnimator animation2 = ObjectAnimator.ofFloat(game, "alpha", 1);
				animation2.setDuration(300);
				animation2.start();
				
			}
			
			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	void startTimer()
	{
		new CountDownTimer(getTime(), 1000) {

			public void onTick(long millisUntilFinished) {
				// mTextField.setText("seconds remaining: " +
				// millisUntilFinished / 1000);
				timer.setText(Long.valueOf(millisUntilFinished / 1000)
						.toString()+" s");
			}

			public void onFinish() {
				TextView dot = (TextView) findViewById(R.id.one);
				dot.setText("");
				round++;
				if(round<getRounds())
				{
					changeText();
					startTimer();
				}
				else
				{
					//Toast.makeText(getBaseContext(), "GAME OVER!!", Toast.LENGTH_LONG).show();
					Intent intent = new Intent(getApplicationContext(),
							HiScore.class);
					finish();
					startActivity(intent);
				}
			}
		}.start();
	}
	@SuppressLint({ "WorldWriteableFiles", "WorldReadableFiles" })
	void initGame()
	{
		SharedPreferences myPrefs = getSharedPreferences(
				"highScorePref", MODE_WORLD_READABLE);
		SharedPreferences myPrefs2 = getSharedPreferences(
				"highScorePref", MODE_WORLD_WRITEABLE);
		SharedPreferences.Editor prefsEditor = myPrefs2.edit();
		if(myPrefs.getInt("no_of_rounds", -1) == -1)
		{
			prefsEditor.putInt("no_of_rounds", 2);
		}
		if(myPrefs.getLong("time_per_round", -1) == -1)
		{
			prefsEditor.putLong("time_per_round", 120000);
		}
		prefsEditor.commit();
	}
	@SuppressLint("WorldReadableFiles")
	int getRounds()
	{
		SharedPreferences myPrefs = getSharedPreferences(
				"highScorePref", MODE_WORLD_READABLE);
		return myPrefs.getInt("no_of_rounds", -1);
	}
	@SuppressLint("WorldReadableFiles")
	long getTime()
	{
		SharedPreferences myPrefs = getSharedPreferences(
				"highScorePref", MODE_WORLD_READABLE);
		return myPrefs.getLong("time_per_round", -1);
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initGame();
		setContentView(R.layout.activity_game);
		size = 4;
		abc = getbutton();
		timer = (TextView) findViewById(R.id.textView5);
		widthSetter();
		setListerners();
		new Thread() {
			public void run() {
			// dismiss the progress dialog
			initdict();
			}
			}.start();
		findViewById(R.id.but1).setOnClickListener(new OnClickListener() {

			@SuppressLint({ "NewApi", "NewApi", "NewApi", "NewApi",
					"WorldWriteableFiles" })
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				EditText abc = (EditText) findViewById(R.id.editText1);
				if (!abc.getText().toString().equals("")) {
					intoGame=true;
					transition();
					EditText name_field = (EditText)findViewById(R.id.editText1);
					getApplicationContext();
					InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
						imm.hideSoftInputFromWindow(name_field.getWindowToken(), 0);
					alpha = generate();
					setGenerated();
					SharedPreferences myPrefs = getSharedPreferences(
							"highScorePref", MODE_WORLD_WRITEABLE);
					SharedPreferences.Editor prefsEditor = myPrefs.edit();
					prefsEditor.putString("name", abc.getText().toString());
					prefsEditor.commit();
					startTimer();
				} else {
					Toast.makeText(getApplicationContext(),
							"Please enter a name", Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if(intoGame==false)
		getMenuInflater().inflate(R.menu.activity_game, menu);
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item)
	{
		Intent intent = new Intent(getApplicationContext(),
				Settings.class);
		startActivity(intent);
		return true;
	}
	void initdict() {
		dict = new ArrayList<String>();
		dictionary_state = 0;
		/*class goasync extends AsyncTask<String, Integer, Long>{

			@Override
			protected Long doInBackground(String... a) {
				// TODO Auto-generated method stub*/
				Scanner sc = new Scanner(getResources().openRawResource(
						R.raw.definitions));
				//ProgressDialog dialog = null;
				String temp;
				while (sc.hasNextLine()) {
					//if(intoGame == true && dialog == null)
						//dialog = ProgressDialog.show(Game.this, "","Loading the dictionary. Please wait...", true);
					temp = sc.nextLine(); 
					dict.add(temp);
				}
				dictionary_state = 1;
				//if(dialog!=null)
				//{
					//dialog.dismiss();
				//}
				//return null;
			//}
		     
		//}
		//new goasync().execute("");
	}
}
