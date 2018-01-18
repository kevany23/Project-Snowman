package p1;

import processing.core.*;
import java.util.*;

public class Snowman extends Enemy implements Gravity {

	private static final int DEFAULT_SIZE = 30;
	private static final int DEFAULT_ARM_LENGTH = 50;
	private static final float DEFAULT_SPEED = 2;
	private float x1;
	private float y1;
	
	private HitBox hitbox;

	private float x2;
	private float y2;
	private float x3;
	private float y3;
	int radius = DEFAULT_SIZE/2;
	
	private int eyeSpaceY = 6;
	private int eyeSpaceX = 4;
	private int noseLength = 8;
	private int noseHeight = 2;
	
	private int damage = 10;
	private int numPoints = 100;
	
	private float yVelocity = 0;
	private float yAcceleration = -1.2f;
	private boolean falling = true;
	private float ground = 1500;
	
	private Platform landing;
	
	private float xVelocity = 1;
	
	private int health = 200;
	private boolean alive = true;
	
	public Snowman(PApplet p, float x, float y) {
		screen = p;
		x1 = x;
		y1 = y;
		x2 = x1;
		x3 = x1;
		y2 = y1 + DEFAULT_SIZE;
		y3 = y1 + 2*DEFAULT_SIZE;
		hitbox = new HitBox(x1, y1, 3*DEFAULT_SIZE, DEFAULT_SIZE);
		Random r = new Random();
		float dir = r.nextFloat();
		if(dir > .5) {
			xVelocity = 1;
		}
		else {
			xVelocity = -1;
		}
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub

		if(falling) {
			y3 = y3 - yVelocity;
			yVelocity = yVelocity + yAcceleration;
			if(y3 > ground) {
				y3 = clamp(y3, DEFAULT_SIZE, 0, ground);
				falling = false;
			}
			y1 = y3 - 2* DEFAULT_SIZE;
			y2 = y3 - DEFAULT_SIZE;
		}
		x1 = x1 + xVelocity;
		x2 = x2 + xVelocity;
		x3 = x3 + xVelocity;
		
		if(x1 > screen.width - DEFAULT_SIZE || x1 < 0) {
			xVelocity = xVelocity * -1;
		}
		
		x1 = clamp(x1, DEFAULT_SIZE, 0, screen.width);
		x2 = clamp(x2, DEFAULT_SIZE, 0, screen.width);
		x3 = clamp(x3, DEFAULT_SIZE, 0, screen.width);

		/*y1 = clamp(y1, DEFAULT_SIZE, 0, ground - (y3 - y1));
		y2 = clamp(y2, DEFAULT_SIZE, 0, ground - (y3 - y2));
		y3 = clamp(y3, DEFAULT_SIZE, 0, ground);*/
		hitbox.setLocation(x1, y1);
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		if(alive) {
			screen.stroke(0);
			screen.fill(255);
			screen.ellipse(x1 + radius, y1 + radius, DEFAULT_SIZE, DEFAULT_SIZE);
			float y2 = y1 + DEFAULT_SIZE;
			screen.ellipse(x2 + radius, y2 + radius, DEFAULT_SIZE, DEFAULT_SIZE);
			screen.ellipse(x3 + radius, y3 + radius, DEFAULT_SIZE, DEFAULT_SIZE);
			if(xVelocity > 0) {
				screen.triangle(x1 + radius, y1 + radius + noseHeight, x1 + radius, y1 - noseHeight + radius,
						x1 + noseLength + radius, y1 + radius);
			}
			else if (xVelocity < 0) {
				screen.triangle(x1 + radius, y1 + radius + noseHeight, x1 + radius, y1 - noseHeight + radius,
						x1 + radius - noseLength, y1 + radius);
			}
			screen.line(x2 + DEFAULT_SIZE, y2 + radius, x2 + DEFAULT_SIZE + 10, y2 + radius + DEFAULT_SIZE);
			screen.line(x2, y2 + radius, x2 - 10, y2 + radius + DEFAULT_SIZE);
			screen.line(x1 + radius - eyeSpaceX, y1 - noseHeight + radius - eyeSpaceY,
					x1 + radius - eyeSpaceX, y1 - noseHeight + radius - eyeSpaceY + 4);
			screen.line(x1 + radius + eyeSpaceX, y1 - noseHeight + radius - eyeSpaceY,
					x1 + radius + eyeSpaceX, y1 - noseHeight + radius - eyeSpaceY + 4);
		}

	}
	
	public HitBox hitBox() {
		return hitbox;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void takeDamage(int d) {
		health = health - d;
		if(health <= 0) {
			alive = false;
		}
	}
	
	public int getHealth() {
		return health;
	}
	
	public void delete() {
		active = false;
		alive = false;
		hitbox = null;
	}
	
	public float getGround() {
		return ground;
	}
	
	public void setGround(float g) {
		ground = g;
	}
	
	public int getPoints() {
		return numPoints;
	}
	
	@Override
	public Platform getPlatform() {
		// TODO Auto-generated method stub
		return landing;
	}

	@Override
	public void setPlatform(Platform p) {
		// TODO Auto-generated method stub
		landing = p;
	}


}
