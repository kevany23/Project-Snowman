package p1;

import processing.core.*;


public class Platform extends GameObject implements Collider, Obstacle{
	
	private float x;
	private float y;
	private int height;
	private int width;
	private HitBox hitbox;
	
	public Platform(PApplet p, float nx, float ny, int w, int h) {
		screen = p;
		x = nx;
		y = ny;
		height = h;
		width = w;
		hitbox = new HitBox(x, y, height, width);
	}

	public void tick() {
	}
	
	public void render() {
		screen.rect(x, y, width, height);
	}
	
	public HitBox hitBox() {
		return hitbox;
	}
	
	public void blockMovement() {
		
	}
	
	public float getY() {
		return y;
	}
	
	public boolean willLand(float dx, float dy, int dw, int dh, float g) {
		if(((dx > x) && (dx < x + width))|| (((dx + dw) > x) && ((dx + dw) < x + width))) {
			if(y >= dy + dh && y <= g) {
				return true;
			}
		}
		return false;
	}
}
