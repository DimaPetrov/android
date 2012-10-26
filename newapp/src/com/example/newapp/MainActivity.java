package com.example.newapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	
	RelativeLayout myLayout;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myLayout = (RelativeLayout) findViewById(R.id.mainlay);
        TextView puzzlebtn = (TextView) findViewById(R.id.game1);
        puzzlebtn.setOnClickListener(this);
        Button btn1 = (Button) findViewById(R.id.button1);
        btn1.setOnClickListener(this);
        Button btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(this);
        Button btn3 = (Button) findViewById(R.id.button3);
        btn3.setOnClickListener(this);
        Button btn4 = (Button) findViewById(R.id.button4);
        btn4.setOnClickListener(this);
        Button btn5 = (Button) findViewById(R.id.button5);
        btn5.setOnClickListener(this);
        
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
				this.finish();
				startActivity(new Intent(this, PuzzleActivity.class));
				overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
				break;
			case R.id.button1:
				this.finish();
				startActivity(new Intent(this, PuzzleActivity.class));
				overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
				break;
			case R.id.button2:
				this.finish();
				startActivity(new Intent(this, FlipActivity.class));
				overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
				break;
			case R.id.button3:
				this.finish();
				startActivity(new Intent(this, PuzzleBack.class));
				overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
				break;
			case R.id.button4:
				this.finish();
				startActivity(new Intent(this, Count.class));
				overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
				break;
			case R.id.button5:
				this.finish();
				startActivity(new Intent(this, ShapeActivity.class));
				overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
				break;
		}
	}
	
	protected void onStop() {
	    super.onStop();
	    Log.d("LIFECYCLE", "MainActivity: onStop()");
	    myLayout.setBackgroundResource(0);
	  }
	protected void onResume() {
	    super.onResume();
	    Log.d("LIFECYCLE", "MainActivity: onResume()");
	    myLayout.setBackgroundResource(R.drawable.bg_games_test);
	  }

}
