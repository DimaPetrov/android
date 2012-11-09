package com.example.newapp;

import java.util.Random;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class Other extends GameSpace{

	public void startGame()
	{
		int collect[] = new int[20];
        collect[0] = R.drawable.o_album_en;
        for(int i =1; i<20; i++)
        	collect[i] = collect[i-1] +1;
        RelativeLayout.LayoutParams objParam[] = new RelativeLayout.LayoutParams[4];
        imageArr = new ImageView[4];
        int instx=200, insty=100;
        Random r = new Random();
        int image = collect[r.nextInt(20)];
        int res = r.nextInt(4);
        for (int i=0; i<4; i++)
        {
        	if (i == 2)
        	{
        		instx = 200;
        		insty = 240;
        	}
        	objParam[i] = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        	objParam[i].width = size;
            objParam[i].height = size;
            objParam[i].leftMargin = instx;
        	objParam[i].topMargin = insty;
        	imageArr[i] = new ImageView(cont);
        	if (i == res)
        	{
        		objParam[i].leftMargin = instx-15;
        		objParam[i].topMargin = insty-15;
        		objParam[i].width = 130;
        		objParam[i].height = 130;
        		imageArr[i].setId(0x666);
        	}
        	r = new Random();
        	imageArr[i].setBackgroundResource(image);
        	myLayout.addView(imageArr[i],objParam[i]);
        	instx += 140;
        	imageArr[i].setOnClickListener(otherListener);
        }
        GameSpace.setHeader("Выбери объект, который отличается от других");
        myLayout.startAnimation(animin);
        myLayout.bringToFront();
	}
	
	OnClickListener otherListener = new OnClickListener() {
	       public void onClick(final View v) 
	       {
	    	   Log.v("Touch", String.valueOf(v.getId()));
	    	   	if (v.getId() == 0x666)
	   			{	
	    	   		for (int i=0; i<4; i++)
	    	   			imageArr[i].setClickable(false);
	   				Animation anim = AnimationUtils.loadAnimation(cont, R.anim.shapewin);
	   				anim.setRepeatCount(3);
	   				anim.setRepeatMode(Animation.REVERSE);
	   				anim.setAnimationListener(new AnimationListener(){

						public void onAnimationEnd(Animation animation) {
							nextgame();
							
						}

						public void onAnimationRepeat(Animation animation) {
							// TODO Auto-generated method stub
							
						}

						public void onAnimationStart(Animation animation) {
							// TODO Auto-generated method stub
							
						}
	   					
	   				});
	   				v.startAnimation(anim);
	   				
	   			}
	   			else
	   			{
	   				Animation anim = AnimationUtils.loadAnimation(cont, R.anim.alphanimout);
	   				anim.setAnimationListener(new AnimationListener(){

						public void onAnimationEnd(Animation animation) {
							v.setVisibility(View.GONE);
							
						}

						public void onAnimationRepeat(Animation animation) {
							// TODO Auto-generated method stub
							
						}

						public void onAnimationStart(Animation animation) {
							// TODO Auto-generated method stub
							
						}
	   					
	   				});
	   				v.startAnimation(anim);
	   			}
	      }
	 };
	
}
