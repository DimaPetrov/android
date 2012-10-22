package com.example.newapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class PuzzleActivity extends Activity implements OnTouchListener, AnimationListener {
	
	int[] clr = new int[4];
	int obj_count = 4, hiding;
	RelativeLayout myLayout;
	RelativeLayout.LayoutParams lp;
	LayoutParams localparams;
	int x0,y0;
	int x_cord, y_cord;
	int x_in=0, y_in=0;
	ImageView[] boxArr;
	ImageView localBox;
	int half, size =100;
	boolean drag = false;
	int win = 0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puzzle); 
        
        clr[0] = Color.BLUE;
        clr[1] = Color.GREEN;
        clr[2] = Color.YELLOW;
        clr[3] = Color.GRAY;
        
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
        	imageArr[i].setBackgroundColor(clr[i]);
        	imageArr[i].setId(i+1);
        	myLayout.addView(imageArr[i],objParam[i]);
        	imageArr[i].setOnTouchListener(this);
        	
        	boxParam[i] = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        	boxParam[i].width = size;
            boxParam[i].height = size;
            boxParam[i].leftMargin = instx;
        	boxParam[i].topMargin = insty+500;
        	boxArr[i] = new ImageView(this);
        	boxArr[i].setBackgroundColor(clr[i]-100);
        	clr[i] -= 100;
        	boxArr[i].setId((i+1)*10);
        	myLayout.addView(boxArr[i],boxParam[i]);
        	
        	instx += objParam[i].width+5;
        	//insty += 51;
        }
        clr[3] = Color.DKGRAY;
        boxArr[3].setBackgroundColor(Color.DKGRAY);
        objParam[0].leftMargin = 100;
        objParam[0].topMargin = 100;
        objParam[1].leftMargin = 205;
        objParam[1].topMargin = 100;
        objParam[2].leftMargin = 100;
        objParam[2].topMargin = 205;
        objParam[3].leftMargin = 205;
        objParam[3].topMargin = 205;
        
        boxParam[0].leftMargin = 500;
        boxParam[0].topMargin = 100;
        boxParam[1].leftMargin = 605;
        boxParam[1].topMargin = 100;
        boxParam[2].leftMargin = 500;
        boxParam[2].topMargin = 205;
        boxParam[3].leftMargin = 605;
        boxParam[3].topMargin = 205;
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
	    			 localBox.setBackgroundColor(clr[v.getId()-1]);
	    		 else
	    		 localBox.setBackgroundColor(Color.RED);
	    		 if (x_cord<x0)
	    			 x_cord = x0;
	    		 if (x_cord+(int)(v.getWidth())>myLayout.getWidth())
	    			 x_cord = myLayout.getWidth() - v.getWidth();
	    		 if (y_cord<0)
	    			 y_cord = 0;
	    		 if (y_cord+(int)(v.getHeight())>myLayout.getHeight())
	    			 y_cord = myLayout.getHeight()-v.getHeight();
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
	    		  hiding = v.getId();
	    		  //localparams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	    		  //localparams = (LayoutParams) v.getLayoutParams();
	    		  //localparams.leftMargin = (int) (localBox.getLeft());
	    		  //localparams.topMargin = (int) (localBox.getTop());
	    		  Animation anim = new TranslateAnimation(0, localBox.getLeft()-x_cord, 0, localBox.getTop()-y_cord);
	    		  anim.setAnimationListener(this);
	    		  anim.setDuration(200);
	    		  anim.setFillEnabled(true);
	    		  anim.setFillAfter(true);
	    		  v.startAnimation(anim);
	    		  v.setOnTouchListener(null);
	    		  anim = null;
	    	  }
	    	  break;
	             default : break;
	      }
		return true;
	}
	public void onAnimationEnd(Animation animation) {
    	//ImageView localimage = (ImageView) findViewById(hiding);
		//localimage.setLayoutParams(localparams);
		win++;
		if (win == 4)
		{
			
			Intent intent = new Intent(this, FlipActivity.class);  
		    startActivity(intent);
		    PuzzleActivity.this.finish();
			overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
		}
		
	  }

	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		
	}

	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		
	}

}
