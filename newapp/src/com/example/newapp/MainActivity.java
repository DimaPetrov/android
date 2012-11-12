package com.example.newapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
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
        
       }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	public void onClick(View v) {
				this.finish();
				startActivity(new Intent(this, GameSpace.class));
				overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
	}
	
	protected void onStop() {
	    super.onStop();
	    Log.d("LIFECYCLE", "MainActivity: onStop()");
	    myLayout.setBackgroundResource(0);
	  }
	protected void onResume() {
	    super.onResume();
	    Log.d("LIFECYCLE", "MainActivity: onResume()");
	    myLayout.setBackgroundResource(R.drawable.bg_games1);
	  }

}
