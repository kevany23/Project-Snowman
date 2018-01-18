package p1;

import processing.core.*;

public class LaserGun extends Weapon {
	
	private float x;
	private float y;
	
	private static final int DEFAULT_HEIGHT = 2;
	private static final int DEFAULT_WIDTH = 20;
	
	private int speed = 16;
	private int fireRate;
	
	private int damage = 50;
	
	public LaserGun(PApplet p, float nx, float ny, int d) {
		screen = p;
		x = nx;
		y = ny;
		damage = d;
	}

	@Override
	public int getDamage() {
		// TODO Auto-generated method stub
		return damage;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		// TODO Auto-generated method stub

	}
	
	public void setLocation(float nx, float ny) {
		x = nx;
		y = ny;
	}
	
	public Projectile shoot(float direction) {
		if(direction > 0) {
			return new Projectile(screen, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, speed, damage);
		}
		else {
			return new Projectile(screen, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, -1 *speed, damage);
		}
		//return null;
	}

}
