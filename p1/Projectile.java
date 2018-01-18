package p1;

import processing.core.*;

public class Projectile extends GameObject implements Collider {
	
	private float x;
	private float y;
	private int width;
	private int height;
	private int xVelocity;
	
	private int damage;
	
	private HitBox hitbox;
	
	public Projectile(PApplet p, float nx, float ny, int w, int h, int xv, int d) {
		screen = p;
		x = nx;
		y = ny;
		width = w;
		height = h;
		xVelocity = xv;
		damage = d;
		hitbox = new HitBox(nx, ny, w, h);
	}

	@Override
	public HitBox hitBox() {
		// TODO Auto-generated method stub
		return hitbox;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		if(active) {
			x = x + xVelocity;
			hitbox.setLocation(x, y);
			if(x + width < 0 || x > screen.width) {
				active = false;
			}
		}
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		if(active) {
			screen.noStroke();
			screen.fill(255, 0, 0);
			screen.rect(x, y, width, height);		
		}

	}
	
	public int getDamage() {
		return damage;
	}
	
	public void remove() {
		active = false;
		hitbox = null;
		damage = 0;
	}

}
