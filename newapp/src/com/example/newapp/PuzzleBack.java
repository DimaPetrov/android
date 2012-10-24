package com.example.newapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class PuzzleBack extends Activity implements OnTouchListener, AnimationListener {
	
	LayoutParams localparams;
	int obj_count = 3;
	RelativeLayout myLayout;
	RelativeLayout.LayoutParams lp;
	int x0,y0, hiding;
	int x_cord, y_cord;
	int x_in=0, y_in=0;
	ImageView[] boxArr, imageArr;
	int[][] instArr = new int[obj_count][2];
	ImageView localBox;
	int half, size =100;
	boolean drag = false;
	boolean x = false, agree = true;
	int action = 0, pointerId = 0;
	int win = 0;
	Animation anim;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puzzleback); 

        
        half = (int) size/2;
        localBox = new ImageView(this);
        myLayout = (RelativeLayout) findViewById(R.id.Rlay2); 
        
        RelativeLayout.LayoutParams objParam[] = new RelativeLayout.LayoutParams[obj_count];
        imageArr = new ImageView[obj_count];
        RelativeLayout.LayoutParams boxParam[] = new RelativeLayout.LayoutParams[obj_count];
        boxArr = new ImageView[obj_count];
        
        
        int instx=5, insty=100;
        for (int i=0; i<obj_count; i++)
        {	
        	//instArr[i][0] = instx;
        	//instArr[i][1] = insty;
        	objParam[i] = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            objParam[i].leftMargin = instx;
        	objParam[i].topMargin = insty;
        	
        	imageArr[i] = new ImageView(this);
        	imageArr[i].setId(i+1);
        	myLayout.addView(imageArr[i],objParam[i]);
        	imageArr[i].setOnTouchListener(this);
        	
        	boxParam[i] = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        	boxArr[i] = new ImageView(this);
        	boxArr[i].setId((i+1)*10);
        	myLayout.addView(boxArr[i],boxParam[i]);
        	
        	instx += objParam[i].width+5;
        	//insty += 51;
        }
        imageArr[0].setBackgroundResource(R.drawable.o_toothbrush_en);
        imageArr[1].setBackgroundResource(R.drawable.o_chair_en);
        imageArr[2].setBackgroundResource(R.drawable.o_bed_en);
        objParam[0].width = 70;
        objParam[0].height = 70;
        objParam[1].width = 90;
        objParam[1].height = 90;
        objParam[2].width = 105;
        objParam[2].height = 105;
        objParam[0].leftMargin = 100;
        objParam[0].topMargin = 100;
        objParam[1].leftMargin = 205;
        objParam[1].topMargin = 100;
        objParam[2].leftMargin = 100;
        objParam[2].topMargin = 205;
        
        boxParam[0].width = 70;
        boxParam[0].height = 70;
        boxParam[1].width = 90;
        boxParam[1].height = 90;
        boxParam[2].width = 105;
        boxParam[2].height = 105;
        boxParam[0].leftMargin = 500-112;
        boxParam[0].topMargin = 100+88;
        boxParam[1].leftMargin = 605-100;
        boxParam[1].topMargin = 100+66;
        boxParam[2].leftMargin = 500+147;
        boxParam[2].topMargin = 205-59;
        
        for (int i=0; i<obj_count; i++)
        {	
        	instArr[i][0] = objParam[i].leftMargin;
        	instArr[i][1] = objParam[i].topMargin;
        }
    }

	
	public boolean onTouch(View v, MotionEvent event) {
		if (agree)
		{
		
		Log.v("touch", String.valueOf(event.getPointerId(0)));
		Log.v("touch2", String.valueOf(event.getActionIndex()));
		Log.v("touch3", String.valueOf(event.getPointerCount()));
		//if (event.getPointerId(0)==0)
		switch(event.getAction())
	      {
	      case MotionEvent.ACTION_DOWN:
	    	  pointerId = event.getPointerId(0);
	    	  action = 1;
	    	  lp = (LayoutParams) v.getLayoutParams();
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
	    	  if (agree)
	    	  {
	    		 if (action == 1)
	    		 {
	    		 Log.v("moving", String.valueOf(x_cord)+" "+String.valueOf(y_cord));
	    		 x_cord = (int)event.getRawX()-x0;
	    		 y_cord = (int)event.getRawY()-y0;
	    		 x_cord-=x_in;
	    		 y_cord-=y_in;
	    		 //if (!((x_cord+half>localBox.getLeft())&&(x_cord+half<localBox.getRight())&&(y_cord+half>localBox.getTop())&&(y_cord+half<localBox.getBottom())))
	    		 //	 localBox.setBackgroundColor(clr[v.getId()-1]);
	    		 //else
	    		 //localBox.setBackgroundColor(Color.RED);
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
	    	  }
	          break;
	      case MotionEvent.ACTION_POINTER_UP:
	    	  if (event.getPointerId(event.getActionIndex()) == pointerId)
	    	  {
	    		  pointerId = 999;  
		    	  drag = false;
	    		  Log.v("Animation", String.valueOf(localBox.getLeft()+" "+String.valueOf(localBox.getTop())));
	    		  Log.v("Animation", String.valueOf(event.getRawX()-x_in-x0)+" "+ String.valueOf(event.getRawY()-y_in-y0));
	    		  Log.v("Bug", String.valueOf(localBox.getLeft()-event.getRawX()+x_in+x0)+" "+String.valueOf(localBox.getTop()-event.getRawY()+y_in+y0));
		    	  if ((x_cord+half>localBox.getLeft())&&(x_cord+half<localBox.getRight())&&(y_cord+half>localBox.getTop())&&(y_cord+half<localBox.getBottom()))
		    	  {
		    		  x = true;
		    		  localparams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		    		  localparams = (LayoutParams) v.getLayoutParams();
		    		  localparams.leftMargin = (int) (localBox.getLeft());
		    		  localparams.topMargin = (int) (localBox.getTop());
		    		  anim = new TranslateAnimation(0, localBox.getLeft()-x_cord, 0, localBox.getTop()-y_cord);
		    		  anim.setDuration(200);
		    		  anim.setFillEnabled(true);
		    		  //anim.setFillAfter(true);
		    		  hiding = v.getId();
		    		  anim.setAnimationListener(this);
		    		  agree = false;
		    		  v.startAnimation(anim);
		    		  anim = null;
		    		  v.setOnTouchListener(null);
		    		  imageArr[v.getId()-1] = new ImageView(this);
		    		  
		    	  }
		    	  else
		    	  {
		    		  x = false;
		    		  anim = new TranslateAnimation(0, instArr[v.getId()-1][0]-x_cord, 0, instArr[v.getId()-1][1]-y_cord);
		    		  anim.setDuration(200);
		    		  anim.setFillEnabled(true);
		    		  //anim.setFillAfter(true);
		    		  hiding = v.getId();
		    		  localparams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		    		  localparams = (LayoutParams) v.getLayoutParams();
		    		  localparams.leftMargin = (int) (instArr[v.getId()-1][0]);
		    		  localparams.topMargin = (int) (instArr[v.getId()-1][1]);
		    		  agree = false;
		    		  anim.setAnimationListener(this);
		    		  for (int i=0; i<obj_count; i++)
		    			  imageArr[i].setOnTouchListener(null);
		    		  anim.reset();
		    		  v.startAnimation(anim);
		    		  v.setOnTouchListener(this);
		    		  v.bringToFront();
		    	  }
	    	  }
	    	  break;
	      case MotionEvent.ACTION_UP:
	    	  if (event.getPointerId(event.getActionIndex()) == pointerId)
	    	  {
	    	  pointerId = 999;  
	    	  drag = false;
    		  Log.v("Animation", String.valueOf(localBox.getLeft()+" "+String.valueOf(localBox.getTop())));
    		  Log.v("Animation", String.valueOf(event.getRawX()-x_in-x0)+" "+ String.valueOf(event.getRawY()-y_in-y0));
    		  Log.v("Bug", String.valueOf(localBox.getLeft()-event.getRawX()+x_in+x0)+" "+String.valueOf(localBox.getTop()-event.getRawY()+y_in+y0));
	    	  if ((x_cord+half>localBox.getLeft())&&(x_cord+half<localBox.getRight())&&(y_cord+half>localBox.getTop())&&(y_cord+half<localBox.getBottom()))
	    	  {
	    		  x = true;
	    		  localparams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	    		  localparams = (LayoutParams) v.getLayoutParams();
	    		  localparams.leftMargin = (int) (localBox.getLeft());
	    		  localparams.topMargin = (int) (localBox.getTop());
	    		  anim = new TranslateAnimation(0, localBox.getLeft()-x_cord, 0, localBox.getTop()-y_cord);
	    		  anim.setDuration(200);
	    		  anim.setFillEnabled(true);
	    		  //anim.setFillAfter(true);
	    		  hiding = v.getId();
	    		  anim.setAnimationListener(this);
	    		  agree = false;
	    		  v.startAnimation(anim);
	    		  anim = null;
	    		  v.setOnTouchListener(null);
	    		  imageArr[v.getId()-1] = new ImageView(this);
	    		  
	    	  }
	    	  else
	    	  {
	    		  x = false;
	    		  anim = new TranslateAnimation(0, instArr[v.getId()-1][0]-x_cord, 0, instArr[v.getId()-1][1]-y_cord);
	    		  anim.setDuration(200);
	    		  anim.setFillEnabled(true);
	    		  //anim.setFillAfter(true);
	    		  hiding = v.getId();
	    		  localparams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	    		  localparams = (LayoutParams) v.getLayoutParams();
	    		  localparams.leftMargin = (int) (instArr[v.getId()-1][0]);
	    		  localparams.topMargin = (int) (instArr[v.getId()-1][1]);
	    		  agree = false;
	    		  anim.setAnimationListener(this);
	    		  for (int i=0; i<obj_count; i++)
	    			  imageArr[i].setOnTouchListener(null);
	    		  anim.reset();
	    		  v.startAnimation(anim);
	    		  v.setOnTouchListener(this);
	    		  v.bringToFront();
	    	  }
	    	  }
	    	  break;
	             default : action = 0; break;
	      }
		}
		return true;
	}
	

	public void onAnimationEnd(Animation animation) {
		
		ImageView localimage = (ImageView) findViewById(hiding);
        localimage.setLayoutParams(localparams);
        localimage.requestLayout();
    	Log.v("END!", "!!!");
    	for (int i=0; i<obj_count; i++)
			  imageArr[i].setOnTouchListener(this);
    	if (x)
    	{
    	win++;
		  if (win == obj_count)
		  {myLayout.setBackgroundResource(0);
			  PuzzleBack.this.finish();
			  startActivity(new Intent(PuzzleBack.this, Count.class));
			  overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
		  }
    	}
    	anim = null;
    	agree = true;
	  }

	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		
		
	}

	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		
	}
	
	protected void onStop() {
	    super.onStop();
	    Log.d("LIFECYCLE", "MainActivity: onStop()");
	    for (int i=0; i<obj_count; i++)
	    {
	    	imageArr[i].setImageBitmap(null);
	    }
	    myLayout.setBackgroundResource(0);
	  }
	protected void onResume() {
	    super.onResume();
	    Log.d("LIFECYCLE", "MainActivity: onResume()");
	    myLayout.setBackgroundResource(R.drawable.bg_games3);
	    
	  }
	
}
