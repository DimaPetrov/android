package com.example.newapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.widget.ImageView;

public class ImageConvert extends GameSpace{

	Bitmap instImage, resImage;
	Bitmap[] asset = new Bitmap[4];
	ImageView result;
	int size = 576;
	
	public ImageConvert()
	{
		Bitmap b = BitmapFactory.decodeResource(cont.getResources(), R.drawable.p_games_puzzle1);
		asset[0] = Bitmap.createScaledBitmap(b, size, size, false);
		asset[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(cont.getResources(), R.drawable.p_games_puzzle2), size, size, false);
		asset[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(cont.getResources(), R.drawable.p_games_puzzle3), size, size, false);
		asset[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(cont.getResources(), R.drawable.p_games_puzzle4), size, size, false);
	
	}
	
	public ImageView startConvert(int link, int number)
	{
		instImage = BitmapFactory.decodeResource(cont.getResources(), link);
		
		int transColor = asset[0].getPixel(10, 10);
		int darkColor = asset[0].getPixel(size-10, size-10);
		Log.v("DarkColor", String.valueOf(darkColor));
		Log.v("TransColor", String.valueOf(transColor));
		Log.v("PUZZLE", String.valueOf(instImage.getWidth()) + " " + instImage.getHeight());
		resImage = Bitmap.createBitmap(instImage.getWidth(), instImage.getHeight(), Bitmap.Config.ARGB_8888);
		for (int i = 1; i<instImage.getWidth()-1; i++)
			for(int j = 1; j<instImage.getHeight()-1; j++)
			{
				if (asset[number].getPixel(i, j) == darkColor)
					resImage.setPixel(i, j, Color.TRANSPARENT);
				else
					resImage.setPixel(i, j, instImage.getPixel(i, j));
			}
		result = new ImageView(cont);
		result.setImageBitmap(resImage);
		return (result);
	}
	
}
