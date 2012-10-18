package com.example.newapp;

import android.app.Activity;
import android.graphics.Color;
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
	int obj_count = 4;
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
	boolean agree = true;
	
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
        	instArr[i][0] = instx;
        	instArr[i][1] = insty;
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

	public boolean onTouch(View v, MotionEvent event) {
		
		lp = (LayoutParams) v.getLayoutParams();
		Log.v("touch", String.valueOf(event.getPointerId(0)));
		Log.v("touch2", String.valueOf(event.getActionIndex()));
		Log.v("touch3", String.valueOf(event.getPointerCount()));
		if (event.getPointerId(0)==0)
		switch(event.getAction())
	      {
	      case MotionEvent.ACTION_DOWN:
	    	  if (agree)
	    	  {
	    	  drag = true;
	    	  x0 = (int)(event.getRawX()) - v.getLeft() - (int)(event.getX());
	    	  y0 = (int)(event.getRawY()) - v.getTop() - (int)(event.getY());
	    	  x_in = (int) event.getX();
	    	  y_in = (int) event.getY();
	    	  localBox = (ImageView) findViewById(v.getId()*10);
	    	  Log.v("down", String.valueOf(x_in)+" "+String.valueOf(y_in));
	    	  v.bringToFront();
	    	  }
	    	  break;
	      case MotionEvent.ACTION_MOVE:
	    	  if ((event.getActionIndex()==0)&&(agree))
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
	    	  
	    	  if (agree){
	    	  drag = false;
    		  Log.v("Animation", String.valueOf(localBox.getLeft()+" "+String.valueOf(localBox.getTop())));
    		  Log.v("Animation", String.valueOf(event.getRawX()-x_in-x0)+" "+ String.valueOf(event.getRawY()-y_in-y0));
    		  Log.v("Bug", String.valueOf(localBox.getLeft()-event.getRawX()+x_in+x0)+" "+String.valueOf(localBox.getTop()-event.getRawY()+y_in+y0));
	    	  if ((x_cord+half>localBox.getLeft())&&(x_cord+half<localBox.getRight())&&(y_cord+half>localBox.getTop())&&(y_cord+half<localBox.getBottom()))
	    	  {
	    		  localparams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	    		  localparams = (LayoutParams) v.getLayoutParams();
	    		  localparams.leftMargin = (int) (localBox.getLeft());
	    		  localparams.topMargin = (int) (localBox.getTop());
	    		  Animation anim = new TranslateAnimation(0, localBox.getLeft()-event.getRawX()+x_in+x0, 0, localBox.getTop()-event.getRawY()+y_in+y0);
	    		  anim.setDuration(1000);
	    		  //anim.setFillAfter(true);
	    		  hiding = v.getId();
	    		  anim.setAnimationListener(this);
	    		  agree = false;
	    		  v.startAnimation(anim);
	    		  localBox.setBackgroundColor(Color.GREEN);
	    		  anim = null;
	    		  
	    	  }
	    	  else
	    	  {
	    		  Animation anim = new TranslateAnimation(0, instArr[v.getId()-1][0]-event.getRawX()+x_in+x0, 0, instArr[v.getId()-1][1]-event.getRawY()+y_in+y0);
	    		  anim.setDuration(1000);
	    		  //anim.setFillAfter(true);
	    		  hiding = v.getId();
	    		  localparams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	    		  localparams = (LayoutParams) v.getLayoutParams();
	    		  localparams.leftMargin = (int) (instArr[v.getId()-1][0]);
	    		  localparams.topMargin = (int) (instArr[v.getId()-1][1]);
	    		  agree = false;
	    		  anim.setAnimationListener(this);	    
	    		  
	    		  v.startAnimation(anim);
	    		  anim = null;
	    	  }
	    	  }
	    	  break;
	             default : break;
	      }
		return true;
	}
	public void onAnimationEnd(Animation animation) {
    	ImageView localimage = (ImageView) findViewById(hiding);
		localimage.setLayoutParams(localparams);
    	Log.v("END!", "!!!");
    	agree = true;
	  }

	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		
	}

	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		
	}
}
