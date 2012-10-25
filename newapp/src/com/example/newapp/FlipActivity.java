package com.example.newapp;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;


public class FlipActivity extends Activity implements OnClickListener{
	
	int[] clr = new int[4];
	ImageView[][] cards, backSide;
	int count_y= 4, count_x = 2;
	int size = 100;
	RelativeLayout myLayout;
	boolean isFirstImage[] = new boolean[50];
	int ident = 0;
	ImageView card1Front, card2Front, card1Back, card2Back, im1, im2, firstFront, firstBack;
	boolean opencount = true;
	int firstIdent;
	int[][] arindex = new int[count_x][count_y];
	Chronometer myChronometer;
	int win = 0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flip);
        clr[0] = Color.BLUE;
        clr[1] = Color.GREEN;
        clr[2] = Color.YELLOW;
        clr[3] = Color.GRAY;
        myChronometer = (Chronometer) findViewById(R.id.chr);
        myChronometer.setVisibility(View.GONE);
        myLayout = (RelativeLayout) findViewById(R.id.Rlay1);
        RelativeLayout.LayoutParams cardParam[][] = new RelativeLayout.LayoutParams[count_x][count_y];
        RelativeLayout cardBox[][] = new RelativeLayout[count_x][count_y];
        cards = new ImageView[count_x][count_y];
        backSide = new ImageView[count_x][count_y];
        int inst_x = 50;
        int x=50, y=50;
        int id = 0;
        for(int i=1; i<=count_x*count_y; i++)
        	isFirstImage[i] = true;
        for(int i=0; i<count_x; i++)
        {
        	for(int j=0; j<count_y; j++)
        	{
        		arindex[i][j] = 0;
        		id++;
        		cardParam[i][j] = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        		cardParam[i][j].width = size;
        		cardParam[i][j].height = size;
        		cardParam[i][j].leftMargin = x;
        		cardParam[i][j].topMargin = y;
        		cardBox[i][j] = new RelativeLayout(this);
        		myLayout.addView(cardBox[i][j]);
        		cardBox[i][j].addView(cards[i][j] = new ImageView(this),cardParam[i][j]);
        		cardBox[i][j].addView(backSide[i][j] = new ImageView(this),cardParam[i][j]);
        		cards[i][j].setBackgroundColor(Color.GREEN);
        		backSide[i][j].setBackgroundColor(Color.RED);
        		cards[i][j].setId(id);
        		backSide[i][j].setId(id*100);
        		cards[i][j].setVisibility(View.GONE);
        		backSide[i][j].bringToFront();
        		cards[i][j].setOnClickListener(this);
        		backSide[i][j].setOnClickListener(this);
        		x += size + 5;
        	}
        	y+=size+5;
        	x = inst_x;
        }
        int i = 0, z=1;
        arindex[0][0] = 0;
        Random r = new Random();
        while (i<count_x*count_y)
        {
        	int q=0,w=0;
        	while(arindex[q][w]!=0)
        	{
        		q = r.nextInt(count_x);
        		w = r.nextInt(count_y);
        	}
        	arindex[q][w]=z;
        	cards[q][w].setBackgroundColor(clr[z-1]);
        	if ((i % 2)!=0)
        		z++;
        	i++;
        }
    }
    
  
    
    public void onClick(View view) {
    	ident = view.getId();
    	if (ident>=100) 
    		ident = (int) ident/100;
    	im1 = (ImageView) findViewById(ident);
		im2 = (ImageView) findViewById(ident*100);
    	for (int i = 0; i < count_x; i++)
    		for(int j = 0; j< count_y; j++)
    		{
    			cards[i][j].setClickable(false);
    			backSide[i][j].setClickable(false);
    		}
    	
    	final Timer t0 = new Timer();
    	TimerTask stoped = new TimerTask(){
    		public void run()
    		{
    			for (int i = 0; i < count_x; i++)
    	    		for(int j = 0; j< count_y; j++)
    	    		{
    	    			cards[i][j].setClickable(true);
    	    			backSide[i][j].setClickable(true);
    	    		}
    			t0.cancel();
    		}
    	};
    	
        if (opencount) 
        {
        	isFirstImage[ident] = false;
        	opencount = false;
        	applyRotation(im1, im2, 0, -90);
        	firstIdent = ident;
        	card1Front = im1;
        	card1Back = im2;
        	t0.schedule(stoped, 100);
        	firstFront = im1;
        	firstBack = im2;
        } else if ((view.getId()!=firstBack.getId())&&(view.getId()!=firstFront.getId()))
        {
        	
        	isFirstImage[ident] = false;
        	opencount = true;
        	applyRotation(im1, im2, 0, -90);
        	isFirstImage[ident] = true;
        	isFirstImage[firstIdent] = true;
        	
        	Chronometer.OnChronometerTickListener listen = new Chronometer.OnChronometerTickListener() {
    			
        		public void onChronometerTick(Chronometer chronometer) {
    				long myElapsedMillis = SystemClock.elapsedRealtime()
    						- myChronometer.getBase();
    				if (myElapsedMillis >= 50)   // ������� ���������� �������� �������� (������� ��������)
    				{   
    					int m1 = card1Front.getId();
    					int m3 = im1.getId();
    					if (arindex[(m1-1) / count_y][(m1-1) % count_y] != arindex[(m3-1) / count_y][(m3-1) % count_y])
    					{
    						applyRotation(card1Front, card1Back, 0, 90);
    						applyRotation(im1, im2, 0, 90);
    					} else
    					{
    						card1Front.setVisibility(View.GONE);
    						im1.setVisibility(View.GONE);
    						im2.setVisibility(View.GONE);
    						card1Back.setVisibility(View.GONE);
    						win++;
    						if (win==4)
    						{
    							//myLayout.setBackgroundResource(0);
    							FlipActivity.this.finish();
    							startActivity(new Intent(FlipActivity.this, PuzzleBack.class));
    							overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
    						}
    					}
            	    	myChronometer.stop();
            	    	
    				}
    			}
    		};
        	myChronometer.setOnChronometerTickListener(listen);
        	myChronometer.setBase(SystemClock.elapsedRealtime());
        	myChronometer.start();
        	t0.schedule(stoped, 1000); //����� ������� ��������
        }
        else
        	t0.schedule(stoped, 150); //����� ������� ��������
    	    	
        }
    
    public void applyRotation(ImageView image1, ImageView image2, float start, float end) {
    	final float centerX = size / 2;
    	final float centerY = size / 2;
    	 
    	final Flip3dAnimation rotation =
    	new Flip3dAnimation(start, end, centerX, centerY);
    	rotation.setDuration(50);  // ������� ���� ��������
    	rotation.setFillAfter(true);
    	rotation.setInterpolator(new AccelerateInterpolator());
    	rotation.setAnimationListener(new DisplayNextView(isFirstImage[ident], image1, image2, size));
    	 
    	if (isFirstImage[ident])
    	{
    	image1.startAnimation(rotation);
    	} else {
    	image2.startAnimation(rotation);
    	}
    	 
    	}
    
    protected void onStop() {
	    super.onStop();
	    Log.d("LIFECYCLE", "2: onStop()");
	    for(int i=0; i<count_x; i++)
        {
        	for(int j=0; j<count_y; j++)
        	{
        		cards[i][j].setImageBitmap(null);
        		backSide[i][j].setImageBitmap(null);
        	}
        }
	    myLayout.setBackgroundResource(0);
        	
	  }
    
	protected void onResume() {
	    super.onResume();
	    Log.d("LIFECYCLE", "2: onResume()");
	    myLayout.setBackgroundResource(R.drawable.bg_games2);
	  }
}

