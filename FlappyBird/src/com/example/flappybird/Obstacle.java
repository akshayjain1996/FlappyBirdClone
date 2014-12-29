package com.example.flappybird;

import java.util.Random;

public class Obstacle {
	private float x;
	private float y;
	private float screenHeight, screenWidth;

	public Obstacle(float height, float width, float place) {
		reset(height, width, place);
	}

	private void reset(float height, float width, float place) {
		screenHeight = height;
		screenWidth = width;
		x = place;
		y = randomHeight();
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	private float randomHeight() {
		Random random = new Random();
		float point = random.nextInt((int) screenHeight - 300) + 100;
		return point;
	}

	public void step() {
		x -= (screenWidth / 120);
		if (x < -80) {
			reset(screenHeight, screenWidth, screenWidth);
		}
	}
}
