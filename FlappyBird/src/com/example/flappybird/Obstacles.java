package com.example.flappybird;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Obstacles {
	List<Obstacle> obs;

	public Obstacles(int height, int width) {
		obs = new ArrayList<Obstacle>();
		Obstacle obstacle = new Obstacle(height, width, width);
		obs.add(obstacle);
		obstacle = new Obstacle(height, width, (float) (1.5 * width + 40));
		obs.add(obstacle);
	}

	public void step() {
		Iterator<Obstacle> iterator = obs.iterator();
		while (iterator.hasNext()) {
			Obstacle obstacle = iterator.next();
			obstacle.step();
		}
	}

	public List<Obstacle> getList() {
		return obs;
	}
}
