package com.example.newapp;

import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
 
public final class SwapViews implements Runnable {
private boolean mIsFirstView;
ImageView image1;
ImageView image2;
int size;
 
public SwapViews(boolean isFirstView, ImageView image1, ImageView image2, int size) {
mIsFirstView = isFirstView;
this.image1 = image1;
this.image2 = image2;
this.size = size;
}
 
public void run() {
final float centerX = size / 2;
final float centerY = size / 2;
Flip3dAnimation rotation;
 
if (mIsFirstView) {
image1.setVisibility(View.GONE);
image2.setVisibility(View.VISIBLE);
//image2.requestFocus();
image2.bringToFront();
 
rotation = new Flip3dAnimation(-90, 0, centerX, centerY);
} else {
	image2.setVisibility(View.GONE);
	image1.setVisibility(View.VISIBLE);
	image1.bringToFront();
//image1.requestFocus();

 
rotation = new Flip3dAnimation(90, 0, centerX, centerY);
}
 
rotation.setDuration(200);
rotation.setFillAfter(true);
rotation.setInterpolator(new DecelerateInterpolator());
 
if (mIsFirstView) {
image2.startAnimation(rotation);
} else {
image1.startAnimation(rotation);
}
}
}