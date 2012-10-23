package com.example.newapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView puzzlebtn = (TextView) findViewById(R.id.game1);
        puzzlebtn.setOnClickListener(this);
        Settings.System.putInt(getContentResolver(),Settings.System.TRANSITION_ANIMATION_SCALE,1);
        Settings.System.putInt(getContentResolver(), Settings.System.WINDOW_ANIMATION_SCALE, 1);
        Button btn1 = (Button) findViewById(R.id.button1);
        btn1.setOnClickListener(this);
        Button btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(this);
        Button btn3 = (Button) findViewById(R.id.button3);
        btn3.setOnClickListener(this);
        Button btn4 = (Button) findViewById(R.id.button4);
        btn4.setOnClickListener(this);
       }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	public void onClick(View v) {
		switch (v.getId())
		{
			case R.id.game1:
				startActivity(new Intent(this, PuzzleActivity.class));
				this.finish();
				overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
				break;
			case R.id.button1:
				startActivity(new Intent(this, PuzzleActivity.class));
				this.finish();
				overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
				break;
			case R.id.button2:
				startActivity(new Intent(this, FlipActivity.class));
				this.finish();
				overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
				break;
			case R.id.button3:
				startActivity(new Intent(this, PuzzleBack.class));
				this.finish();
				overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
				break;
			case R.id.button4:
				startActivity(new Intent(this, Count.class));
				this.finish();
				overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
				break;
		}
	}

}
