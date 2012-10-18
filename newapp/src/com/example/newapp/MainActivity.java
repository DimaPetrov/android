package com.example.newapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView puzzlebtn = (TextView) findViewById(R.id.game1);
        TextView flipCards = (TextView) findViewById(R.id.game2);
        TextView puzzle2 = (TextView) findViewById(R.id.game3);
        TextView count = (TextView) findViewById(R.id.game4);
        puzzlebtn.setOnClickListener(this);
        flipCards.setOnClickListener(this);
        puzzle2.setOnClickListener(this);
        count.setOnClickListener(this);
       }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	public void onClick(View v) {
		Intent intent;
		switch(v.getId())
		{
		case R.id.game1:
			intent = new Intent(this, PuzzleActivity.class);
			startActivity(intent);
			break;
		case R.id.game2:
			intent = new Intent(this, FlipActivity.class);
			startActivity(intent);
			break;
		case R.id.game3:
			intent = new Intent(this, PuzzleBack.class);
			startActivity(intent);
			break;
		case R.id.game4:
			intent = new Intent(this, Count.class);
			startActivity(intent);
			break;
		}
	}

}
