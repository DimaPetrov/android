package com.example.newapp;

import java.util.Random;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

public class Shape extends GameSpace {

	public void startGame()
	{
		size = 100;
		RelativeLayout.LayoutParams objParam[] = new RelativeLayout.LayoutParams[6];
        imageArr = new ImageView[6];
        
        int collect[] = new int[6];
        collect[0] = R.drawable.p_games_shapecircle;
        for(int i =1; i<6; i++)
        	collect[i] = collect[i-1] +1;
        Random r = new Random();
        right_ind = r.nextInt(6);
        //text.setLeft(400);
        //text.setTop(20);
        int instx=200, insty=100;
        for (int i=0; i<6; i++)
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
        	imageArr[i] = new ImageView(cont);
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
        	imageArr[i].setOnClickListener(shapeListener);
        	instx += objParam[i].width+5;
        }
        String s = new String();
        switch (right_ind)
        {
        	case 0:
        		s = "����";
        		break;
        	case 1:
        		s = "����";
        		break;
        	case 2:
        		s = "��������";
        		break;
        	case 3:
        		s = "�������";
        		break;
        	case 4:
        		s = "������";
        		break;
        	case 5:
        		s = "�����������";
        		break;
        }
        GameSpace.setHeader("������ " + s);
        instx = 0; 
        myLayout.startAnimation(animin);
        myLayout.bringToFront();
	}
	
	
	OnClickListener shapeListener = new OnClickListener() {
	       public void onClick(final View v) 
	       {
	    	   Log.v("Touch", String.valueOf(v.getId()));
	    	   	if (v.getId() == right_id)
	   			{	
	    	   		for (int i=0; i<6; i++)
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
	   				v.setOnClickListener(null);
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
