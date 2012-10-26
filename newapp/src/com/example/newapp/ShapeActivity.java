package com.example.newapp;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class ShapeActivity extends Activity implements OnClickListener {
	
	RelativeLayout myLayout;
	int obj_count = 6;
	ImageView[] imageArr;
	int size = 100;
	ImageView rabbit;
	int right_ind;
	int right_id;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.shape);
	        myLayout = (RelativeLayout) findViewById(R.id.shapelay);
	        
	        rabbit = (ImageView) findViewById(R.id.rabbit);
	        
	        RelativeLayout.LayoutParams objParam[] = new RelativeLayout.LayoutParams[obj_count];
	        imageArr = new ImageView[obj_count];
	        
	        int collect[] = new int[6];
	        collect[0] = R.drawable.p_games_shapecircle;
	        for(int i =1; i<6; i++)
	        	collect[i] = collect[i-1] +1;
	        Random r = new Random();
	        right_ind = r.nextInt(6);
	        TextView text = (TextView) findViewById(R.id.textView1);
	        
	        int instx=200, insty=100;
	        for (int i=0; i<obj_count; i++)
	        {	
	        	if (i == 3)
	        	{
	        		insty+=size+5;
	        		instx = 200;
	        	}
	        	objParam[i] = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	        	objParam[i].width = size;
	            objParam[i].height = size;
	            objParam[i].leftMargin = instx;
	        	objParam[i].topMargin = insty;
	        	imageArr[i] = new ImageView(this);
	        	r = new Random();
	        	int q = r.nextInt(6);
	        	while (collect[q]==0)
	        		q = r.nextInt(6);
	        	imageArr[i].setId(i+1);
	        	imageArr[i].setBackgroundResource(collect[q]);
	        	if (q == right_ind)
	        		right_id = imageArr[i].getId();
	        	collect[q] = 0;
	        	myLayout.addView(imageArr[i],objParam[i]);
	        	imageArr[i].setOnClickListener(this);
	        	instx += objParam[i].width+5;
	        }
	        switch (right_ind)
	        {
	        	case 0:
	        		text.setText("Circle");
	        		break;
	        	case 1:
	        		text.setText("Diamond");
	        		break;
	        	case 2:
	        		text.setText("Heart");
	        		break;
	        	case 3:
	        		text.setText("Square");
	        		break;
	        	case 4:
	        		text.setText("Star");
	        		break;
	        	case 5:
	        		text.setText("Triangle");
	        		break;
	        }
	        //text.setText(text.getText() + String.valueOf(right_ind) + String.valueOf(right_id));
	        instx = 0;
	 }

	public void onClick(View v) {
		
		if (v.getId() == right_id)
		{
			rabbit.setBackgroundResource(R.drawable.p_games_charactertrue);
			Animation anim = AnimationUtils.loadAnimation(this, R.anim.shapewin);
			v.startAnimation(anim);
			this.finish();
			startActivity(new Intent(this, ShapeActivity.class));
			overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
		}
		else
		{
			rabbit.setBackgroundResource(R.drawable.p_games_characterfalse);
			Animation anim = AnimationUtils.loadAnimation(this, R.anim.slide_right_in);
			anim.setRepeatCount(3);
			anim.setRepeatMode(Animation.REVERSE);
			v.startAnimation(anim);
			
			
		}
		
		
	}
	protected void onStop() {
	    super.onStop();
	    Log.d("LIFECYCLE", "5: onStop()");
	    for (int i=0; i<obj_count; i++)
	    {
	    	imageArr[i].setImageBitmap(null);
	    }
	    myLayout.setBackgroundResource(0);
	  }
	
	protected void onResume() {
	    super.onResume();
	    Log.d("LIFECYCLE", "5: onResume()");
	    myLayout.setBackgroundResource(R.drawable.bg_games4);
	  }

}
