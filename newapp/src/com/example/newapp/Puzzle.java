package com.example.newapp;

import java.util.Random;

import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class Puzzle extends GameSpace {
	
	ImageView box;
	Animation transAnim;
	
	public void startGame()
	{
		size = 200;
		ImageConvert converter = new ImageConvert();
		win = 1;
		box = new ImageView(cont);
		box.setId(1);
		imageArr = new ImageView[4];
		RelativeLayout.LayoutParams boxParam = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		boxParam.width = size;
		boxParam.height = size;
        RelativeLayout.LayoutParams objParam[] = new RelativeLayout.LayoutParams[4];
    	int insty = 100;
    	int instx = 200;
        for (int i=0; i<4; i++)
        {
        	objParam[i] = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            
        	
        	//if (i == 2)
        	//{
        	//	insty =(int) (insty + size / 4 + 5);
        	//	instx = 200;
        	//}
        	
        	objParam[i].width = size;
        	objParam[i].height = size;
        	objParam[i].leftMargin = instx;
        	objParam[i].topMargin = insty;
        	
        	imageArr[i] = new ImageView(cont);
        	imageArr[i].setId(i+2);
        	imageArr[i] = converter.startConvert(R.drawable.o_fridge_en, i);
        	myLayout.addView(imageArr[i],objParam[i]);
        	imageArr[i].setOnTouchListener(puzzleListener);
        	
        	//instx =(int) (instx + size / 4 + 5);
        	
        }
        
        GameSpace.setHeader("������ ����");
        myLayout.startAnimation(animin);
        myLayout.bringToFront();
	}

	OnTouchListener puzzleListener = new OnTouchListener(){

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
		    	  changeCordsX = (int) event.getRawX();
		    	  changeCordsY = (int) event.getRawY();
		    	  pointerId = event.getPointerId(0);
		    	  action = 1;
		    	  lp = (LayoutParams) v.getLayoutParams();
		    	  drag = true;
		    	  x0 = (int)(event.getRawX()) - v.getLeft() - (int)(event.getX());
		    	  y0 = (int)(event.getRawY()) - v.getTop() - (int)(event.getY());
		    	  x_in = (int) event.getX();
		    	  y_in = (int) event.getY();
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
		    		 //if (!((x_cord+half>box.getLeft())&&(x_cord+half<box.getRight())&&(y_cord+half>box.getTop())&&(y_cord+half<box.getBottom())))
		    		 //	 box.setBackgroundColor(clr[v.getId()-1]);
		    		 //else
		    		 //box.setBackgroundColor(Color.RED);
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
		    	  if ((v.getId()-win == 1)&&(event.getPointerId(event.getActionIndex()) == pointerId)&&(changeCordsX!=(int) event.getRawX())&&(changeCordsY!=(int) event.getRawY()))
		    	  {
		    		  pointerId = 999;  
			    	  drag = false;
		    		  Log.v("Animation", String.valueOf(box.getLeft()+" "+String.valueOf(box.getTop())));
		    		  Log.v("Animation", String.valueOf(event.getRawX()-x_in-x0)+" "+ String.valueOf(event.getRawY()-y_in-y0));
		    		  Log.v("Bug", String.valueOf(box.getLeft()-event.getRawX()+x_in+x0)+" "+String.valueOf(box.getTop()-event.getRawY()+y_in+y0));
			    	  if ((x_cord+half>box.getLeft())&&(x_cord+half<box.getRight())&&(y_cord+half>box.getTop())&&(y_cord+half<box.getBottom()))
			    	  {
			    		  x = true;
			    		  localparams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			    		  localparams = (LayoutParams) v.getLayoutParams();
			    		  localparams.leftMargin = (int) (box.getLeft())+5;
			    		  localparams.topMargin = (int) (box.getTop())+5;
			    		  transAnim = new TranslateAnimation(0, box.getLeft()-x_cord+5, 0, box.getTop()-y_cord+5);
			    		  transAnim.setDuration(200);
			    		  transAnim.setFillEnabled(true);
			    		  hiding = v.getId();
			    		  transAnim.setAnimationListener(animListener);
			    		  agree = false;
			    		  v.startAnimation(transAnim);
			    		  transAnim = null;
			    		  v.setOnTouchListener(null);
			    		  imageArr[v.getId()-1] = new ImageView(cont);
			    		  win++;
			    	  }
		    	  }
		    	  break;
		      case MotionEvent.ACTION_UP:
		    	  if ((v.getId()-win == 1)&&(event.getPointerId(event.getActionIndex()) == pointerId)&&(changeCordsX!=(int) event.getRawX())&&(changeCordsY!=(int) event.getRawY()))
		    	  {
		    	  pointerId = 999;  
		    	  drag = false;
	    		  Log.v("Animation", String.valueOf(box.getLeft()+" "+String.valueOf(box.getTop())));
	    		  Log.v("Animation", String.valueOf(event.getRawX()-x_in-x0)+" "+ String.valueOf(event.getRawY()-y_in-y0));
	    		  Log.v("Bug", String.valueOf(box.getLeft()-event.getRawX()+x_in+x0)+" "+String.valueOf(box.getTop()-event.getRawY()+y_in+y0));
		    	  if ((x_cord+half>box.getLeft())&&(x_cord+half<box.getRight())&&(y_cord+half>box.getTop())&&(y_cord+half<box.getBottom()))
		    	  {
		    		  x = true;
		    		  localparams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		    		  localparams = (LayoutParams) v.getLayoutParams();
		    		  localparams.leftMargin = (int) (box.getLeft())+5;
		    		  localparams.topMargin = (int) (box.getTop())+5;
		    		  transAnim = new TranslateAnimation(0, box.getLeft()-x_cord+5, 0, box.getTop()-y_cord+5);
		    		  transAnim.setDuration(200);
		    		  transAnim.setFillEnabled(true);
		    		  hiding = v.getId();
		    		  transAnim.setAnimationListener(animListener);
		    		  agree = false;
		    		  v.startAnimation(transAnim);
		    		  transAnim = null;
		    		  v.setOnTouchListener(null);
		    		  imageArr[v.getId()-1] = new ImageView(cont);
		    		  win++;
		    	  }
		    	  }
		    	  break;
		             default : action = 0; break;
		      }
			}
			return true;
		}
 	
 };
 
 final AnimationListener animListener = new AnimationListener(){

		public void onAnimationEnd(Animation animation) {
			ImageView localimage = (ImageView) myLayout.findViewById(hiding);
			
	        localimage.setLayoutParams(localparams);
	        localimage.requestLayout();
	    	Log.v("END!", "!!!");
	    	for (int i=0; i<3; i++)
				  imageArr[i].setOnTouchListener(puzzleListener);
	    	if (x)
	    	{
	    	win++;
			  if (win == 5)
			  {
				  nextgame();
			  }
	    	}
	    	transAnim = null;
	    	agree = true;
		}

		public void onAnimationRepeat(
				Animation animation) {
			// TODO Auto-generated method stub
			
		}

		public void onAnimationStart(Animation animation) {
			// TODO Auto-generated method stub
			
		}
		  
	  };

}
