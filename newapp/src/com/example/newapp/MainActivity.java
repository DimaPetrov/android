package com.example.newapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
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
        puzzlebtn.setOnClickListener(this);
        Settings.System.putInt(getContentResolver(),Settings.System.TRANSITION_ANIMATION_SCALE,1);
        Settings.System.putInt(getContentResolver(), Settings.System.WINDOW_ANIMATION_SCALE, 1);
        
       }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	public void onClick(View v) {
			startActivity(new Intent(this, PuzzleActivity.class));
			this.finish();
			overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
	}

}
