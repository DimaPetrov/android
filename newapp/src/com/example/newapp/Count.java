package com.example.newapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class Count extends Activity implements OnClickListener {
	  
	int[] clr = new int[4];
	RelativeLayout myLayout;
	int obj_count = 4, size = 100;
	TextView count;
	
	@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.count);
	        
	        clr[0] = Color.BLUE;
	        clr[1] = Color.GREEN;
	        clr[2] = Color.YELLOW;
	        clr[3] = Color.GRAY;
	        
	        count = (TextView) findViewById(R.id.index);
	        myLayout = (RelativeLayout) findViewById(R.id.Rlay3); 
	        RelativeLayout.LayoutParams objParam[] = new RelativeLayout.LayoutParams[obj_count];
	        ImageView imageArr[] = new ImageView[obj_count];
	        
	        int instx=5, insty=100;
	        for (int i=0; i<obj_count; i++)
	        {	
	        	objParam[i] = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	        	objParam[i].width = size;
	            objParam[i].height = size;
	            objParam[i].leftMargin = instx;
	        	objParam[i].topMargin = insty;
	        	imageArr[i] = new ImageView(this);
	        	imageArr[i].setBackgroundColor(clr[i]);
	        	imageArr[i].setId(i+1);
	        	myLayout.addView(imageArr[i],objParam[i]);
	        	imageArr[i].setOnClickListener(this);
	        	instx += objParam[i].width+5;
	        }
	        
	        
	   }

	public void onClick(View v) {
		v.setVisibility(View.GONE);
		int x = Integer.parseInt((String) count.getText());
		x++;
		count.setText(String.valueOf(x));
	}
}
