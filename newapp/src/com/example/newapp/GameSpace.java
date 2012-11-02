package com.example.newapp;

import java.lang.reflect.Array;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class GameSpace extends Activity {
	
		public static Context cont;
		int obj_count;
	 	RelativeLayout myLayout;
		ImageView[] imageArr;
		int size = 100;
		int right_ind;
		int right_id;
		TextView count;
		int x0,y0, hiding;
		int x_cord;
		int y_cord;
		int x_in=0, y_in=0;
		boolean drag = false;
		boolean x = false;
		boolean agree = true;
		int action = 0;
		int pointerId = 0;
		int win;
		Animation anim;
		int changeCordsX;
		int changeCordsY;
		LayoutParams lp;
		LayoutParams localparams;
		int[][] instArr;
		int half = (int) size/2;
        ImageView localBox, back1, back2;
        Display display;
        boolean isFirstBack = true;
		int number = 1;
		Animation animin;
		ImageView rabbit, notif;
		int cl;
		TextView header;
		int gameCounting = 0;
		int[] clr = new int[4];
		ImageView[][] cards, backSide;
		int count_y= 4, count_x = 2;
		boolean isFirstImage[] = new boolean[50];
		int ident = 0;
		ImageView card1Front, card2Front, card1Back, card2Back, im1, im2, firstFront, firstBack;
		boolean opencount = true;
		int firstIdent;
		int[][] arindex = new int[count_x][count_y];
		Chronometer myChronometer;
		
	@Override 
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.game_space);
	        cont = GameSpace.this;
	        animin = AnimationUtils.loadAnimation(this, R.anim.alphaanimin);
	        layouts();
	        gameShapeStart();
	        display = getWindowManager().getDefaultDisplay();
	        Log.v("Display", String.valueOf(display.getWidth())+" "+String.valueOf(display.getHeight()));
	        
	        rabbit = (ImageView) findViewById(R.id.imageView1);
	        notif = (ImageView) findViewById(R.id.imageView2);
	        
	   }
	
	
	
	
	
	public void nextgame()
	{
		Animation alphaanim = AnimationUtils.loadAnimation(this, R.anim.alphanimout);
		myLayout.startAnimation(alphaanim);
		Random r = new Random();
		gameCounting++;
		number++;
		Animation anim = AnimationUtils.loadAnimation(this, R.anim.slide_left_out);
		Animation anim1 = AnimationUtils.loadAnimation(this, R.anim.slide_left_in);
		anim.setAnimationListener(new AnimationListener(){
			
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				myLayout.removeAllViews();
				switch (gameCounting % 5)
				{
				case 0:
					gameShapeStart();
					break;
				case 1:
					gameCountStart();
					break;
				case 2:
					gameSizeStart();
					break;
				case 3:
					gameFlipStart();
					break;
				case 4:
					gameColorStart();
					break;
				}
			}

			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}

			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
		});
		if (isFirstBack)
		{
			back2.bringToFront();
			myLayout.bringToFront();
			rabbit.bringToFront();
			notif.bringToFront();
			back2.setBackgroundResource(R.drawable.bg_games1+number % 3);
			back1.startAnimation(anim);
			back2.startAnimation(anim1);
			isFirstBack = false;
		}
		else
		{   
			back1.bringToFront();
			myLayout.bringToFront();
			rabbit.bringToFront();
			notif.bringToFront();
			back1.setBackgroundResource(R.drawable.bg_games1+number % 3);
			back1.startAnimation(anim1);
			back2.startAnimation(anim);
			isFirstBack = true;
		}
		
	}
	
	ModImageView[][] collect;
	
	public void ColorObj()
	{
		collect = new ModImageView[8][2];
		for(int i = 0; i<8; i++)
			for(int j = 0; j<2; j++)
			{
		collect[i][j] = new ModImageView();
		collect[i][j].image = new ImageView(this);
			}
		collect[0][0].image.setBackgroundResource(R.drawable.o_cup_en);
		collect[0][1].image.setBackgroundResource(R.drawable.o_plate_en);
		collect[1][0].image.setBackgroundResource(R.drawable.o_toothbrush_en);
		collect[1][1].image.setBackgroundResource(R.drawable.o_toothpaste_en);
		collect[2][0].image.setBackgroundResource(R.drawable.o_lamp_en);
		collect[2][1].image.setBackgroundResource(R.drawable.o_doll_en);
		collect[3][0].image.setBackgroundResource(R.drawable.o_fork_en);
		collect[3][1].image.setBackgroundResource(R.drawable.o_gloves_en);
		collect[4][0].image.setBackgroundResource(R.drawable.o_pants_en);
		collect[4][1].image.setBackgroundResource(R.drawable.o_tvset_en);
		collect[5][0].image.setBackgroundResource(R.drawable.o_album_en);
		collect[5][1].image.setBackgroundResource(R.drawable.o_train_en);
		collect[6][0].image.setBackgroundResource(R.drawable.o_spoon_en);
		collect[6][1].image.setBackgroundResource(R.drawable.o_phone_en);
		collect[7][0].image.setBackgroundResource(R.drawable.o_umbrella_en);
		collect[7][1].image.setBackgroundResource(R.drawable.o_bed_en);
		collect[0][0].color = "�����";
		collect[0][1].color = "�����";
		collect[1][0].color = "�������";
		collect[1][1].color = "�������";
		collect[2][0].color = "�������";
		collect[2][1].color = "�������";
		collect[3][0].color = "������";
		collect[3][1].color = "������";
		collect[4][0].color = "����������";
		collect[4][1].color = "����������";
		collect[5][0].color = "�������";
		collect[5][1].color = "�������";
		collect[6][0].color = "�����";
		collect[6][1].color = "�����";
		collect[7][0].color = "���������";
		collect[7][1].color = "���������";
	}
	
	public void layouts()
	{
		myLayout = (RelativeLayout) findViewById(R.id.gameLay);
		back1 = (ImageView) findViewById(R.id.backLay1);
		back2 = (ImageView) findViewById(R.id.backLay2);
		myLayout.bringToFront();
		back1.setBackgroundResource(R.drawable.bg_games1);
		
		
	}
	
	//Games starting
	
	public void gameFlipStart()
	{
			win = 0;
	        clr[0] = R.drawable.o_album_en;
	        clr[1] = R.drawable.o_bed_en;
	        clr[2] = R.drawable.o_lamp_en;
	        clr[3] = R.drawable.o_train_en;
	        myChronometer = new Chronometer(this);
	        myLayout.addView(myChronometer);
	        myChronometer.setVisibility(View.GONE);
	        RelativeLayout.LayoutParams cardParam[][] = new RelativeLayout.LayoutParams[count_x][count_y];
	        RelativeLayout cardBox[][] = new RelativeLayout[count_x][count_y];
	        cards = new ImageView[count_x][count_y];
	        backSide = new ImageView[count_x][count_y];
	        int inst_x = 200;
	        int x=200, y=50;
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
	        		backSide[i][j].setBackgroundResource(R.drawable.p_games_matchcardback);
	        		cards[i][j].setId(id);
	        		backSide[i][j].setId(id*100);
	        		cards[i][j].setVisibility(View.GONE);
	        		backSide[i][j].bringToFront();
	        		cards[i][j].setOnClickListener(flipListener);
	        		backSide[i][j].setOnClickListener(flipListener);
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
	        	cards[q][w].setImageResource(clr[z-1]);
	        	cards[q][w].setBackgroundResource(R.drawable.p_games_matchcardfront);
	        	if ((i % 2)!=0)
	        		z++;
	        	i++;
	        }
	        myLayout.startAnimation(animin);
	        myLayout.bringToFront();
	}
	
	public void gameCountStart()
	{
		
		Random r = new Random();
        obj_count = 3+r.nextInt(6);
        count = new TextView(this);
        count.setText("0");
        myLayout.addView(count);  
        count.setTextSize(30);
        count.setTextColor(Color.BLACK);
        RelativeLayout.LayoutParams objParam[] = new RelativeLayout.LayoutParams[obj_count];
        imageArr = new ImageView[obj_count];
        
        int collect[] = new int[20];
        collect[0] = R.drawable.o_album_en;
        for(int i =1; i<20; i++)
        	collect[i] = collect[i-1] +1;
        
        int instx=200, insty=100;
        for (int i=0; i<obj_count; i++)
        {	
        	if (i == 3)
        	{
        		instx = 200;
        		insty = 205;
        	}
        	if (i == 6)
        	{
        		instx = 200;
        		insty = 310;
        	}
        	objParam[i] = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        	objParam[i].width = size;
            objParam[i].height = size;
            objParam[i].leftMargin = instx;
        	objParam[i].topMargin = insty;
        	imageArr[i] = new ImageView(this);
        	r = new Random();
        	int q = r.nextInt(20);
        	while (collect[q]==0)
        		q = r.nextInt(20);
        	imageArr[i].setBackgroundResource(collect[q]);
        	collect[q] = 0;
        	imageArr[i].setId(i+1);
        	myLayout.addView(imageArr[i],objParam[i]);
        	imageArr[i].setOnClickListener(countListener);
        	instx += objParam[i].width+5;
        }
        myLayout.startAnimation(animin);
        myLayout.bringToFront();
	}
	
	public void gameShapeStart()
	 {
		
	        RelativeLayout.LayoutParams objParam[] = new RelativeLayout.LayoutParams[6];
	        imageArr = new ImageView[6];
	        
	        int collect[] = new int[6];
	        collect[0] = R.drawable.p_games_shapecircle;
	        for(int i =1; i<6; i++)
	        	collect[i] = collect[i-1] +1;
	        Random r = new Random();
	        right_ind = r.nextInt(6);
	        TextView text = new TextView(this);
	        //text.setLeft(400);
	        //text.setTop(20);
	        myLayout.addView(text);
	        text.setTextSize(30);
	        text.setTextColor(Color.BLACK);
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
	        	imageArr[i] = new ImageView(this);
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
	        switch (right_ind)
	        {
	        	case 0:
	        		text.setText("Circle");
	        		break;
	        	case 1:
	        		text.setText("Diamond");
	        		break;
	        	case 2:
	        		text.setText("Heart");
	        		break;
	        	case 3:
	        		text.setText("Square");
	        		break;
	        	case 4:
	        		text.setText("Star");
	        		break;
	        	case 5:
	        		text.setText("Triangle");
	        		break;
	        }
	        //text.setText(text.getText() + String.valueOf(right_ind) + String.valueOf(right_id));
	        instx = 0; 
	        myLayout.startAnimation(animin);
	        myLayout.bringToFront();
	 }
	
	public void gameColorStart()
	{
		RelativeLayout.LayoutParams objParam[][] = new RelativeLayout.LayoutParams[2][4];
		ImageView[][] localItems = new ImageView[2][4];
		boolean res = true;
		header = new TextView(this);
		ColorObj();
		Random r = new Random();
		cl = r.nextInt(7);
		header.setText("������ "+collect[cl][r.nextInt(2)].color+" ������");
		myLayout.addView(header);
		header.setTextSize(30);
		header.setTextColor(Color.BLACK);
		int x = r.nextInt(4);
		int y = r.nextInt(2);
		int instx = 200;
		int insty = 100;
		int id;
		for (int i = 0; i < 2; i++)
		{
			for (int j = 0; j < 4; j++)
			{	res = true;
				localItems[i][j] = new ImageView(this);
				if ((y==i)&&(x==j))
				{
					int qw = r.nextInt(2);
					localItems[i][j] = collect[cl][qw].image;
					collect[cl][qw].image.setId(0x666);
				}else
				{
					while (res)
					{
						int newcl = r.nextInt(7);
						id = r.nextInt(2);
						if ((newcl == cl)||(collect[newcl][id].image==null))
							res = true;
						else
						{
							localItems[i][j] = collect[newcl][id].image;
							collect[newcl][id].image = null;
							res = false;
						}
					}
				}
				objParam[i][j] = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				objParam[i][j].width = 100;
				objParam[i][j].height = 100;
				objParam[i][j].leftMargin = instx;
				objParam[i][j].topMargin = insty;
				myLayout.addView(localItems[i][j], objParam[i][j]);
				localItems[i][j].setOnClickListener(colorListener);
				instx+=105;
			}
			insty+=105;
			instx = 200;
		}
	}
	
	public void gameSizeStart()
	{
		win = 0;
		
		instArr = new int[3][2];
		imageArr = new ImageView[3];
		int collect[] = new int[20];
        collect[0] = R.drawable.o_album_en;
        for(int i =1; i<20; i++)
        	collect[i] = collect[i-1] +1;
        RelativeLayout.LayoutParams objParam[] = new RelativeLayout.LayoutParams[3];
        RelativeLayout.LayoutParams boxParam[] = new RelativeLayout.LayoutParams[3];
        ImageView[] boxArr = new ImageView[3];
        int instx=5, insty=100;
        for (int i=0; i<3; i++)
        {	
        	//instArr[i][0] = instx;
        	//instArr[i][1] = insty;
        	objParam[i] = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            objParam[i].leftMargin = instx;
        	objParam[i].topMargin = insty;
        	
        	imageArr[i] = new ImageView(this);
        	Random r = new Random();
        	int q = r.nextInt(20);
        	while (collect[q]==0)
        		q = r.nextInt(20);
        	imageArr[i].setBackgroundResource(collect[q]);
        	collect[q] = 0;
        	imageArr[i].setId(i+1);
        	myLayout.addView(imageArr[i],objParam[i]);
        	imageArr[i].setOnTouchListener(sizeListener);
        	
        	boxParam[i] = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        	boxArr[i] = new ImageView(this);
        	boxArr[i].setId((i+1)*10);
        	//boxArr[i].setBackgroundColor(Color.BLACK);
        	myLayout.addView(boxArr[i],boxParam[i]);
        	
        	instx += objParam[i].width+5;
        	//insty += 51;
        	
        }
        int q = 70;
        ImageView mainBox = new ImageView(GameSpace.this);
        myLayout.addView(mainBox);
        LayoutParams mainBoxParam = (LayoutParams) mainBox.getLayoutParams();
        mainBox.setBackgroundResource(R.drawable.p_games_sortslots);
        mainBoxParam.height = 190;
        mainBoxParam.width = (int) (mainBoxParam.height * 2.5);
        mainBoxParam.topMargin = (int) (display.getHeight()/2) - 95-q;
        mainBoxParam.leftMargin = display.getWidth()-mainBoxParam.width;
        
        boxParam[0].width =  92;
        boxParam[0].height = 92;
        boxParam[1].width = 110;
        boxParam[1].height = 110;
        boxParam[2].width = 140;
        boxParam[2].height = 140;
        boxParam[0].leftMargin = mainBoxParam.leftMargin + 40 -10;
        boxParam[0].topMargin = mainBoxParam.topMargin + 82  -10;
        boxParam[1].leftMargin = mainBoxParam.leftMargin + 163  -10;
        boxParam[1].topMargin = mainBoxParam.topMargin + 58  -10;
        boxParam[2].leftMargin = mainBoxParam.leftMargin + 313  -15;
        boxParam[2].topMargin = mainBoxParam.topMargin + 33  -15;

        objParam[0].width = 80;
        objParam[0].height = 80;
        objParam[1].width = 105;
        objParam[1].height = 105;
        objParam[2].width = 130;
        objParam[2].height = 130;
        objParam[0].leftMargin = boxParam[1].leftMargin;
        objParam[0].topMargin = 300;
        objParam[1].leftMargin = boxParam[2].leftMargin;
        objParam[1].topMargin = 300;
        objParam[2].leftMargin = boxParam[0].leftMargin+10;
        objParam[2].topMargin = 300;
        
        for (int i=0; i<3; i++)
        {	
        	instArr[i][0] = objParam[i].leftMargin;
        	instArr[i][1] = objParam[i].topMargin;
        }
        myLayout.startAnimation(animin);
        myLayout.bringToFront();
	}
	
	
	
	
	//LISTENERS:
	
	
	
	
	OnClickListener colorListener = new OnClickListener(){
		public void onClick(View v)
		{
			if (v.getId() == 0x666)
			{
				Animation anim = AnimationUtils.loadAnimation(GameSpace.this, R.anim.shapewin);
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
   				v.setOnClickListener(null);
   				v.startAnimation(anim);
				
			}
			else
   			{
   				Animation anim = AnimationUtils.loadAnimation(GameSpace.this, R.anim.slide_right_in);
   				anim.setRepeatCount(3);
   				anim.setRepeatMode(Animation.REVERSE);
   				v.startAnimation(anim);
   			}
		}
	};
	
	OnClickListener flipListener = new OnClickListener(){

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
	    				if (myElapsedMillis >= 100)   // ������� ���������� �������� �������� (������� ��������)
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
	    							nextgame();
	    						}
	    					}
	            	    	myChronometer.stop();
	            	    	
	    				}
	    			}
	    		};
	        	myChronometer.setOnChronometerTickListener(listen);
	        	myChronometer.setBase(SystemClock.elapsedRealtime());
	        	myChronometer.start();
	        	t0.schedule(stoped, 1100); //����� ������� ��������
	        }
	        else
	        	t0.schedule(stoped, 150); //����� ������� ��������
	    	    	
			
		}
		
	};
	
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
	
	 final AnimationListener animListener = new AnimationListener(){

			public void onAnimationEnd(Animation animation) {
				ImageView localimage = (ImageView) findViewById(hiding);
		        localimage.setLayoutParams(localparams);
		        localimage.requestLayout();
		    	Log.v("END!", "!!!");
		    	for (int i=0; i<3; i++)
					  imageArr[i].setOnTouchListener(sizeListener);
		    	if (x)
		    	{
		    	win++;
				  if (win == 3)
				  {
					  nextgame();
				  }
		    	}
		    	anim = null;
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
	
	 OnClickListener shapeListener = new OnClickListener() {
	       public void onClick(View v) 
	       {
	    	   Log.v("Touch", String.valueOf(v.getId()));
	    	   	if (v.getId() == right_id)
	   			{	
	    	   		for (int i=0; i<6; i++)
	    	   			imageArr[i].setClickable(false);
	   				Animation anim = AnimationUtils.loadAnimation(GameSpace.this, R.anim.shapewin);
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
	   				Animation anim = AnimationUtils.loadAnimation(GameSpace.this, R.anim.slide_right_in);
	   				anim.setRepeatCount(3);
	   				anim.setRepeatMode(Animation.REVERSE);
	   				v.startAnimation(anim);
	   			}
	      }
	 };
	 
	
	 
	 OnClickListener countListener = new OnClickListener(){

		public void onClick(View v) 
		{			
			v.setVisibility(View.GONE);
			int x = Integer.parseInt((String) count.getText());
			x++;
			count.setText(String.valueOf(x));
			if (x == obj_count)
			  {
				  
				  nextgame();
			  }
		}
		 
	 };
	 
	 OnTouchListener sizeListener = new OnTouchListener(){

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
			    	  if ((event.getPointerId(event.getActionIndex()) == pointerId)&&(changeCordsX!=(int) event.getRawX())&&(changeCordsY!=(int) event.getRawY()))
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
				    		  localparams.leftMargin = (int) (localBox.getLeft())+5;
				    		  localparams.topMargin = (int) (localBox.getTop())+5;
				    		  anim = new TranslateAnimation(0, localBox.getLeft()-x_cord+5, 0, localBox.getTop()-y_cord+5);
				    		  anim.setDuration(200);
				    		  anim.setFillEnabled(true);
				    		  hiding = v.getId();
				    		  anim.setAnimationListener(animListener);
				    		  agree = false;
				    		  v.startAnimation(anim);
				    		  anim = null;
				    		  v.setOnTouchListener(null);
				    		  imageArr[v.getId()-1] = new ImageView(GameSpace.this);
				    		  
				    	  }
				    	  else
				    	  {
				    		  x = false;
				    		  anim = new TranslateAnimation(0, instArr[v.getId()-1][0]-x_cord, 0, instArr[v.getId()-1][1]-y_cord);
				    		  anim.setDuration(200);
				    		  anim.setFillEnabled(true);
				    		  hiding = v.getId();
				    		  localparams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				    		  localparams = (LayoutParams) v.getLayoutParams();
				    		  localparams.leftMargin = (int) (instArr[v.getId()-1][0]);
				    		  localparams.topMargin = (int) (instArr[v.getId()-1][1]);
				    		  agree = false;
				    		  anim.setAnimationListener(animListener);
				    		  for (int i=0; i<3; i++)
				    			  imageArr[i].setOnTouchListener(null);
				    		  anim.reset();
				    		  v.startAnimation(anim);
				    		  v.setOnTouchListener(this);
				    		  v.bringToFront();
				    	  }
			    	  }
			    	  break;
			      case MotionEvent.ACTION_UP:
			    	  if ((event.getPointerId(event.getActionIndex()) == pointerId)&&(changeCordsX!=(int) event.getRawX())&&(changeCordsY!=(int) event.getRawY()))
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
			    		  localparams.leftMargin = (int) (localBox.getLeft())+5;
			    		  localparams.topMargin = (int) (localBox.getTop())+5;
			    		  anim = new TranslateAnimation(0, localBox.getLeft()-x_cord+5, 0, localBox.getTop()-y_cord+5);
			    		  anim.setDuration(200);
			    		  anim.setFillEnabled(true);
			    		  hiding = v.getId();
			    		  anim.setAnimationListener(animListener);
			    		  agree = false;
			    		  v.startAnimation(anim);
			    		  anim = null;
			    		  v.setOnTouchListener(null);
			    		  imageArr[v.getId()-1] = new ImageView(GameSpace.this);
			    		  
			    	  }
			    	  else
			    	  {
			    		  x = false;
			    		  anim = new TranslateAnimation(0, instArr[v.getId()-1][0]-x_cord, 0, instArr[v.getId()-1][1]-y_cord);
			    		  anim.setDuration(200);
			    		  anim.setFillEnabled(true);
			    		  hiding = v.getId();
			    		  localparams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			    		  localparams = (LayoutParams) v.getLayoutParams();
			    		  localparams.leftMargin = (int) (instArr[v.getId()-1][0]);
			    		  localparams.topMargin = (int) (instArr[v.getId()-1][1]);
			    		  agree = false;
			    		  anim.setAnimationListener(animListener);
			    		  for (int i=0; i<3; i++)
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
     	
     };
	 
}
