package com.example.newapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class PuzzleActivity extends Activity implements OnTouchListener {
	
	int obj_count = 4;
	RelativeLayout myLayout;
	RelativeLayout.LayoutParams lp;
	int x0,y0;
	int x_cord, y_cord;
	int x_in=0, y_in=0;
	ImageView[] boxArr;
	ImageView localBox;
	int half, size =100;
	boolean drag = false;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puzzle); 

        half = (int) size/2;
        localBox = new ImageView(this);
        myLayout = (RelativeLayout) findViewById(R.id.Rlay); 
        
        RelativeLayout.LayoutParams objParam[] = new RelativeLayout.LayoutParams[obj_count];
        ImageView imageArr[] = new ImageView[obj_count];
        RelativeLayout.LayoutParams boxParam[] = new RelativeLayout.LayoutParams[obj_count];
        boxArr = new ImageView[obj_count];
        
        
        int instx=5, insty=100;
        for (int i=0; i<obj_count; i++)
        {	
        	objParam[i] = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        	objParam[i].width = size;
            objParam[i].height = size;
            objParam[i].leftMargin = instx;
        	objParam[i].topMargin = insty;
        	imageArr[i] = new ImageView(this);
        	imageArr[i].setBackgroundColor(Color.BLUE);
        	imageArr[i].setId(i+1);
        	myLayout.addView(imageArr[i],objParam[i]);
        	imageArr[i].setOnTouchListener(this);
        	
        	boxParam[i] = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        	boxParam[i].width = size;
            boxParam[i].height = size;
            boxParam[i].leftMargin = instx;
        	boxParam[i].topMargin = insty+500;
        	boxArr[i] = new ImageView(this);
        	boxArr[i].setBackgroundColor(Color.BLACK);
        	boxArr[i].setId((i+1)*10);
        	myLayout.addView(boxArr[i],boxParam[i]);
        	
        	instx += objParam[i].width+5;
        	//insty += 51;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	public boolean onTouch(View v, MotionEvent event) {
		lp = (LayoutParams) v.getLayoutParams();
		Log.v("touch", String.valueOf(event.getPointerId(0)));
		Log.v("touch2", String.valueOf(event.getActionIndex()));
		Log.v("touch3", String.valueOf(event.getPointerCount()));
		if (event.getPointerId(0)==0)
		switch(event.getAction())
	      {
	      case MotionEvent.ACTION_DOWN:
	    	  drag = true;
	    	  x0 = (int)(event.getRawX()) - v.getLeft() - (int)(event.getX());
	    	  y0 = (int)(event.getRawY()) - v.getTop() - (int)(event.getY());
	    	  x_in = (int) event.getX();
	    	  y_in = (int) event.getY();
	    	  localBox = (ImageView) findViewById(v.getId()*10);
	    	  Log.v("down", String.valueOf(x_in)+" "+String.valueOf(y_in));
	    	  v.bringToFront();
	    	  break;
	      case MotionEvent.ACTION_MOVE:
	    	  if (event.getActionIndex()==0)
	    	  {
	    		 Log.v("moving", String.valueOf(x_cord)+" "+String.valueOf(y_cord));
	    		 x_cord = (int)event.getRawX()-x0;
	    		 y_cord = (int)event.getRawY()-y0;
	    		 x_cord-=x_in;
	    		 y_cord-=y_in;
	    		 if (!((x_cord+half>localBox.getLeft())&&(x_cord+half<localBox.getRight())&&(y_cord+half>localBox.getTop())&&(y_cord+half<localBox.getBottom())))
	    			 localBox.setBackgroundColor(Color.BLACK);
	    		 else
	    			 localBox.setBackgroundColor(Color.RED);
	    		 
	    		 lp.leftMargin = x_cord;
	    		 lp.topMargin = y_cord;

	    		 v.setLayoutParams(lp);
	    		 v.invalidate();
	    		 v.bringToFront();
	    	  }
	          break;
	      case MotionEvent.ACTION_UP:
	    	  drag = false;
    		  Log.v("Animation", String.valueOf(localBox.getLeft()+" "+String.valueOf(localBox.getTop())));
    		  Log.v("Animation", String.valueOf(event.getRawX()-x_in-x0)+" "+ String.valueOf(event.getRawY()-y_in-y0));
    		  Log.v("Bug", String.valueOf(localBox.getLeft()-event.getRawX()+x_in+x0)+" "+String.valueOf(localBox.getTop()-event.getRawY()+y_in+y0));
	    	  if ((x_cord+half>localBox.getLeft())&&(x_cord+half<localBox.getRight())&&(y_cord+half>localBox.getTop())&&(y_cord+half<localBox.getBottom()))
	    	  {
	    		  Animation anim = new TranslateAnimation(0, localBox.getLeft()-event.getRawX()+x_in+x0, 0, localBox.getTop()-event.getRawY()+y_in+y0);
	    		  anim.setDuration(2000);
	    		  anim.setFillAfter(true);
	    		  v.startAnimation(anim);
	    		  localBox.setBackgroundColor(Color.GREEN);

	    		  
	    	  }
	    	  break;
	             default : break;
	      }
		return true;
	}
}
