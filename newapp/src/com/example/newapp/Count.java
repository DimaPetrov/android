package com.example.newapp;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class Count extends Activity implements OnClickListener {
	  
	RelativeLayout myLayout;
	int obj_count = 4, size = 110;
	TextView count;
	ImageView[] imageArr;
	
	@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.count);
	        Random r = new Random();
	        obj_count = 3+r.nextInt(4);
	        
	        count = (TextView) findViewById(R.id.index);
	        myLayout = (RelativeLayout) findViewById(R.id.Rlay3); 
	        RelativeLayout.LayoutParams objParam[] = new RelativeLayout.LayoutParams[obj_count];
	        imageArr = new ImageView[obj_count];
	        
	        int collect[] = new int[20];
	        collect[0] = R.drawable.o_album_en;
	        for(int i =1; i<20; i++)
	        	collect[i] = collect[i-1] +1;
	        
	        int instx=5, insty=100;
	        for (int i=0; i<obj_count; i++)
	        {	
	        	objParam[i] = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	        	objParam[i].width = size;
	            objParam[i].height = size;
	            objParam[i].leftMargin = instx;
	        	objParam[i].topMargin = insty;
	        	imageArr[i] = new ImageView(this);
	        	r = new Random();
	        	int q = r.nextInt(20);
	        	while (collect[q]==0)
	        		q = r.nextInt(20);
	        	imageArr[i].setBackgroundResource(collect[q]);
	        	collect[q] = 0;
	        	imageArr[i].setId(i+1);
	        	myLayout.addView(imageArr[i],objParam[i]);
	        	imageArr[i].setOnClickListener(this);
	        	instx += objParam[i].width+5;
	        }
	        
	        //imageArr[0].setBackgroundResource(collect[r.nextInt(20)]);
	        //imageArr[1].setBackgroundResource(R.drawable.o_chair_en);
	        //imageArr[2].setBackgroundResource(R.drawable.o_socks_en);
	        //imageArr[3].setBackgroundResource(R.drawable.o_toothbrush_en);
	        
	        
	   }

	public void onClick(View v) {
		v.setVisibility(View.GONE);
		int x = Integer.parseInt((String) count.getText());
		x++;
		count.setText(String.valueOf(x));
		if (x == obj_count)
		  {
			//myLayout.setBackgroundResource(0);
			  Count.this.finish();
			  startActivity(new Intent(Count.this, PuzzleBack.class));
			  overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
		  }
	}
	
	protected void onStop() {
	    super.onStop();
	    Log.d("LIFECYCLE", "4: onStop()");
	    for (int i=0; i<obj_count; i++)
	    {
	    	imageArr[i].setImageBitmap(null);
	    }
	    myLayout.setBackgroundResource(0);
	  }
	
	protected void onResume() {
	    super.onResume();
	    Log.d("LIFECYCLE", "4: onResume()");
	    myLayout.setBackgroundResource(R.drawable.bg_games4);
	  }
}
