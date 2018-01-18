package p1;

import processing.core.*;
import utilities.Timer;

public class HUD extends GameObject {

	private static final float UPPER_Y = 30;
	
	private int playerHealth;
	private int score;
	
	private long startTime;
	private long time;

	public HUD(PApplet p) {
		screen = p;
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		screen.textSize(24);
		screen.fill(0);
		screen.text("Health: " + playerHealth, screen.width/3, UPPER_Y);
		screen.text("Time: " + time/1000, 0, UPPER_Y);
		screen.text("Score: " + score, 2*screen.width/3, UPPER_Y);
		

	}
	
	public void setHealth(int h) {
		playerHealth = h;
	}
	
	public void setTime(long t) {
		time = t;
	}
	
	public void setScore(int s) {
		score = s;
	}

}
