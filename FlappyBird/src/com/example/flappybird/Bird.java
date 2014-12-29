package com.example.flappybird;

import java.util.Iterator;
import java.util.List;

import android.R.string;

public class Bird {
	private float x;
	private float y;
	private float dy;

	public Bird() {
		x = 10;
		y = 350;
		dy = 0;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void touch() {
		dy = 20;
	}

	public void fly() {
		y -= dy;
		if (dy > -10) {
			dy -= 4;
		}
	}

	public boolean checkCollision(Obstacles obstacles, float screenHeight) {
		boolean notCollided = true;
		if ((y < 0) || (y - 60 > screenHeight)) {
			System.out.println("out of screen");
			notCollided = false;
		}
		List<Obstacle> obs = obstacles.getList();
		Iterator<Obstacle> iterator = obs.iterator();
		while (iterator.hasNext()) {
			Obstacle obstacle = iterator.next();
			if ((obstacle.getX() > x) && (obstacle.getX() < x + 60)) {
				System.out.println(obstacle.getX() + x);
				if ((obstacle.getY() < y + 60) || (obstacle.getY() - 170 > y)) {
					if (obstacle.getY() < y + 60) {
						System.out.println("1");
					}
					if (obstacle.getY() + 150 > y) {
						System.out.println("2");
					}
					notCollided = false;
				}
			}
		}
		return notCollided;
	}
}
