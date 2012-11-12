package com.example.newapp;

import java.util.Random;

import android.graphics.Color;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class Count extends GameSpace{
	
	int[] colors = new int[5];
	int x;
	
	public void startGame()
	{
		x = 0;
		
		colors[0] = Color.RED;
		colors[1] = Color.GREEN;
		colors[2] = Color.BLUE;
		colors[3] = Color.MAGENTA;
		colors[4] = Color.CYAN;
		
		GameSpace.setHeader("�������� �������� ���������");
		Random r = new Random();
        obj_count ++;
        LayoutParams countParam = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        countParam.leftMargin = notif.getLeft();
        countParam.topMargin = notif.getTop();
        nText.setText("0");
        nText.setVisibility(View.GONE);
        RelativeLayout.LayoutParams objParam[] = new RelativeLayout.LayoutParams[obj_count];
        imageArr = new ImageView[obj_count];
        
        int collect[] = new int[20];
        collect[0] = R.drawable.o_album_en;
        for(int i =1; i<20; i++)
        	collect[i] = collect[i-1] +1;
        
        display = cont.getWindowManager().getDefaultDisplay();
        int workWidth = display.getWidth() - rabbit.getWidth();
        int workHeight = (int) (display.getHeight() - header.getTextSize());
        
        int border = 0;
        int secondStrBorder = 0;
        int localSize = size;
        
        Log.v("WorkSize", String.valueOf(rabbit.getWidth()));
        Log.v("WorkSize", String.valueOf(header.getTextSize()));
        Log.v("Obj_count", String.valueOf(obj_count));
        
        int instx = rabbit.getWidth(), insty = (int) header.getTextSize();
        int inst = 0;
        
        switch (obj_count)
        {
        	case 3:
        		localSize = (int) (min(workWidth, workHeight) / 3);
        		border = 4;
        		inst = (int) rabbit.getWidth() +((workWidth - 3*localSize - 2*5)/2);
        		insty = (int) (header.getTextSize() + (workHeight-localSize)/2);
        		break;
        	case 4:
        		localSize = (int) (min(workWidth, workHeight) / 2);
        		border = 2;
        		inst = (int) rabbit.getWidth() + ((workWidth - 2*localSize - 5)/2);
        		insty = (int) (header.getTextSize() + (workHeight-2*localSize)/2);
        		break;
        	case 5:
        		localSize = (int) (min(workWidth, workHeight) / 3);
        		border = 3;
        		inst = (int) rabbit.getWidth() +((workWidth - 3*localSize - 2*5)/2);
        		insty = (int) (header.getTextSize() + (workHeight-2*localSize)/2);
        		break;
        }
        
        Log.v("INST", String.valueOf(inst-rabbit.getWidth()));
        
        instx = inst;
        for (int i=0; i<obj_count; i++)
        {	
        	if (i == border)
        	{
        		instx = inst;
        		insty += localSize;
        	}
        	objParam[i] = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        	objParam[i].width = localSize;
            objParam[i].height = localSize;
            objParam[i].leftMargin = instx;
        	objParam[i].topMargin = insty;
        	imageArr[i] = new ImageView(cont);
        	r = new Random();
        	int q = r.nextInt(20);
        	while (collect[q]==0)
        		q = r.nextInt(20);
        	imageArr[i].setBackgroundResource(collect[q]);
        	collect[q] = 0;
        	imageArr[i].setId(i+1);
        	myLayout.addView(imageArr[i],objParam[i]);
        	imageArr[i].setOnClickListener(countListener);
        	instx += localSize+5;
        }
        myLayout.startAnimation(animin);
        myLayout.bringToFront();
	}
	 OnClickListener countListener = new OnClickListener(){

			public void onClick(final View v) 
			{			
				nText.setVisibility(View.VISIBLE);
				Random clId = new Random();
				v.setOnClickListener(null);
				Animation anim = AnimationUtils.loadAnimation(cont, R.anim.alphanimout);
				anim.setDuration(500);
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
				x = Integer.parseInt((String) nText.getText());
				x++;
				nText.setText(String.valueOf(x));
				nText.setTextColor(colors[clId.nextInt(5)]);
				if (x == obj_count)
				  {
					  nextgame();
				  }
			}
			 
		 };
}
