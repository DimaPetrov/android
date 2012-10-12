package com.example.newapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class MainActivity extends Activity implements OnTouchListener {

	ImageView image,box;
	RelativeLayout myLayout;
	RelativeLayout.LayoutParams lp;
	int x0,y0;
	int x_cord, y_cord;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        myLayout = (RelativeLayout) findViewById(R.id.Rlay);
        box = (ImageView) findViewById(R.id.img2);
        image = (ImageView) findViewById(R.id.img1);
        
        image.setOnTouchListener(this);
        RelativeLayout.LayoutParams marginParams[] = new RelativeLayout.LayoutParams[10];
        ImageView imageArr[] = new ImageView[10];
        
        int instx=5, insty=90;
        for (int i=0; i<10; i++)
        {	
        	marginParams[i] = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        	marginParams[i].width = 50;
            marginParams[i].height = 50;
            marginParams[i].leftMargin = instx;
        	marginParams[i].topMargin = insty;
        	imageArr[i] = new ImageView(this);
        	imageArr[i].setBackgroundColor(Color.BLUE);
        	myLayout.addView(imageArr[i],marginParams[i]);
        	instx += 20;
        	insty += 51;
        	imageArr[i].setOnTouchListener(this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	public boolean onTouch(View v, MotionEvent event) {
		
		lp = (LayoutParams) v.getLayoutParams();
		switch(event.getAction())
	      {
	      case MotionEvent.ACTION_DOWN:
	    	  x0 = (int)(event.getRawX()) - v.getLeft() - (int)(event.getX());
	    	  y0 = (int)(event.getRawY()) - v.getTop() - (int)(event.getY());
	    	  break;
	      case MotionEvent.ACTION_MOVE:
	    	  Log.v("mytag", "moving");
	    	  x_cord = (int)event.getRawX()-x0;
	          y_cord = (int)event.getRawY()-y0;
	          if (!((x_cord>box.getLeft())&&(x_cord<box.getRight())&&(y_cord>box.getTop())&&(y_cord<box.getBottom())))
	        	  box.setBackgroundColor(Color.BLACK);
	          else
	        	  box.setBackgroundColor(Color.RED);
	          lp.leftMargin = x_cord-(v.getHeight()/2);
	          lp.topMargin = y_cord-(v.getWidth()/2);

	          v.setLayoutParams(lp);
	          v.invalidate();
	          v.bringToFront();
	          break;
	      case MotionEvent.ACTION_UP:
	    	  if ((x_cord>box.getLeft())&&(x_cord<box.getRight())&&(y_cord>box.getTop())&&(y_cord<box.getBottom()))
	    	  {
	    		  box.setBackgroundColor(Color.GREEN);
	    	  }
	    	  break;
	             default : break;
	      }
		return true;
	}
}
