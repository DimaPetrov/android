package com.example.newapp;	

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class GameSpace extends Activity {
	
		static public GameSpace cont;
	 	static public RelativeLayout myLayout;
	 	static public Animation anim, animin, alphaanim, anim1;
	 	static public int obj_count;
	 	static public ImageView[] imageArr;
	 	static public int size = 100;
	 	static public int right_ind, right_id;
		static public TextView nText, header;
		static public ImageView back1, back2, rabbit, notif,
								card1Front, card2Front, card1Back, card2Back, im1, im2, firstFront, firstBack;
		static public boolean drag = false, x = false, agree = true,
				isFirstBack = true, opencount = true;
		static public int x0,y0, hiding, x_cord, y_cord, 
			x_in=0, y_in=0, action = 0, pointerId = 0, 
			win, changeCordsX, changeCordsY,
			count_y= 4, count_x = 2,
			half = (int) size/2,
			number = 1, cl,
			gameCounting = 0,
			ident = 0,
			firstIdent,
			scrWidth, scrHeight;
		static public LayoutParams lp, localparams;
		static public int[][] instArr, arindex = new int[count_x][count_y];
        static public Display display;
        static public int[] clr = new int[4];
        static public ImageView[][] cards, backSide;
        static public boolean isFirstImage[] = new boolean[50];
        static public Chronometer myChronometer;
        static public Typeface type; 
        static public float dx, dy, d;
		
	@Override 
	    public void onCreate(Bundle savedInstanceState) {
			cont = this;
			type = Typeface.createFromAsset(this.getAssets(), "fonts/font.ttf");
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.game_space);
	        display = getWindowManager().getDefaultDisplay();
	        dx = (display.getWidth()/800);
	        dy = (display.getHeight()/480);
	        obj_count = 2;
	        if (dx>dy)
	        	d = dy;
	        else
	        	d = dx;
	        Log.v("Display", String.valueOf(display.getWidth())+" "+String.valueOf(display.getHeight()));
	        rabbit = (ImageView) findViewById(R.id.imageView1);
	        notif = (ImageView) findViewById(R.id.imageView2);
	        LayoutParams rabbitParams = (LayoutParams) rabbit.getLayoutParams();
	        rabbitParams.width = (int) (rabbitParams.width * d);
	        rabbitParams.height = (int) (rabbitParams.height * d);
	        
	        
	        
	        animin = AnimationUtils.loadAnimation(this, R.anim.alphaanimin);
	        alphaanim = AnimationUtils.loadAnimation(this, R.anim.alphanimout);
	        anim = AnimationUtils.loadAnimation(this, R.anim.slide_left_out);
			anim1 = AnimationUtils.loadAnimation(this, R.anim.slide_left_in);
			nText = (TextView) findViewById(R.id.ntext);
	        layouts();
	        Count size = new Count();
	        size.startGame();
	       
	   }
	
	
	public static int min(int x, int y)
	{
		if (x>y)
			return(y);
		else 
			return(x);
	}
	
	public static void setHeader(String s)
	{
		header = new TextView(cont);
        header.setTypeface(type);
		header.setTextColor(Color.rgb(0xFF, 0xFF, 0xFF));
		header.setShadowLayer(4, 0, 4, Color.rgb(0x66, 0x33, 0x33));
		header.setTextSize(30);
		header.setText(s);
		LayoutParams headerParam = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		myLayout.addView(header, headerParam);
		header.setGravity(Gravity.CENTER_HORIZONTAL);
		
		
		nText.setTypeface(type);
		nText.setTextColor(Color.rgb(0xFF, 0xFF, 0xFF));
		nText.setShadowLayer(4, 0, 4, Color.rgb(0x66, 0x33, 0x33));
		nText.setTextSize(50);
		nText.setText("");
	}
	
	
	public void nextgame()
	{
		myLayout.startAnimation(alphaanim);
		Random r = new Random();
		gameCounting = 1;
		number++;
		anim.setAnimationListener(new AnimationListener(){
			
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				myLayout.removeAllViews();
				switch (gameCounting % 9)
				{
				case 0:
					Shape shapeGame = new Shape();
			        shapeGame.startGame();
					break;
				case 1:
					Count countGame = new Count();
					countGame.startGame();
					break;
				case 2:
					Size sizeGame = new Size();
					sizeGame.startGame();
					break;
				case 3:
					Flip flipGame = new Flip();
					flipGame.startGame();
					break;
				case 4:
					ColorG colorGame = new ColorG();
					colorGame.startGame();
					break;
				case 5:
					Letter letter = new Letter();
					letter.startGame();
					break;
				case 6:
					Groups groups = new Groups();
					groups.startGame();
					break;
				case 7:
					Other other = new Other();
					other.startGame();
					break;
				case 8:
					Puzzle puzzle = new Puzzle();
					puzzle.startGame();
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
			nText.bringToFront();
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
			nText.bringToFront();
			back1.setBackgroundResource(R.drawable.bg_games1+number % 3);
			back1.startAnimation(anim1);
			back2.startAnimation(anim);
			isFirstBack = true;
		}
		
	}
	
	
	public void layouts()
	{
		myLayout = (RelativeLayout) findViewById(R.id.gameLay);
		back1 = (ImageView) findViewById(R.id.backLay1);
		back2 = (ImageView) findViewById(R.id.backLay2);
		myLayout.bringToFront();
		back1.setBackgroundResource(R.drawable.bg_games1);
		
		
	}
	
}
