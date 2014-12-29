package com.example.flappybird;

import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameLoop extends SurfaceView implements Runnable {

	SurfaceHolder holder;
	Thread thread = null;
	boolean gameOn = true;
	Bird bird;
	Obstacles obstacles;
	Bitmap imgBird, imgObstacleBot, imgObstacleTop;
	Bitmap background;
	int screenHeight, screenWidth;

	public GameLoop(Context context) {
		super(context);
		holder = getHolder();
		thread = new Thread(this);
		background = BitmapFactory.decodeResource(getResources(),
				R.drawable.bground);
		bird = new Bird();
		imgBird = BitmapFactory.decodeResource(getResources(), R.drawable.bird);
		imgObstacleBot = BitmapFactory.decodeResource(getResources(),
				R.drawable.obstaclebot);
		imgObstacleTop = BitmapFactory.decodeResource(getResources(),
				R.drawable.obstacletop);
		thread.start();
		screenHeight = getResources().getDisplayMetrics().heightPixels;
		screenWidth = getResources().getDisplayMetrics().widthPixels;
		obstacles = new Obstacles(screenHeight, screenWidth);
	}

	@Override
	public void run() {
		while (gameOn) {
			if (!holder.getSurface().isValid()) {
				continue;
			}
			Canvas canvas = holder.lockCanvas();
			canvas.drawBitmap(background, 0, 0, null);
			canvas.drawBitmap(imgBird, bird.getX(), bird.getY(), null);
			List<Obstacle> list = obstacles.getList();
			Iterator<Obstacle> iterator = list.iterator();
			while (iterator.hasNext()) {
				Obstacle obstacle = iterator.next();
				canvas.drawBitmap(imgObstacleBot, obstacle.getX(),
						obstacle.getY(), null);
				canvas.drawBitmap(imgObstacleTop, obstacle.getX(),
						obstacle.getY() - 1100, null);
			}
			holder.unlockCanvasAndPost(canvas);
			bird.fly();
			obstacles.step();
			gameOn = bird.checkCollision(obstacles, screenHeight);

			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Game Over!!");
	}

	public void touch() {
		bird.touch();
	}

}
