package p1;

/*
 * Rectangular hitbox
 */

public class HitBox {
	private float x;
	private float y;
	private int height;
	private int width;
	
	public HitBox(float nx, float ny, int h, int w) {
		x = nx;
		y = ny;
		height = h;
		width = w;
	}
	
	public HitBox(int h, int w) {
		height = h;
		width = w;
	}
	public void setLocation(float nx, float ny) {
		x = nx;
		y = ny;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public boolean contains(HitBox h) {
		
		boolean result = false;
		
		float x2 = h.x + h.width;
		float y2 = h.y + h.height;
		
		if(contains(h.x, h.y) || contains(h.x, y2) || contains(x2, h.y) || contains(x2, y2)) {
			result = true;
		}
		else if (h.contains(x, y) || h.contains(x+width, y) || h.contains(x, y+height) ||
				h.contains(x+width, y+height)) {
			result = true;
		}
		return result;
	}
	
	private boolean contains(float x2, float y2) {
		if(x2 < x || x2 > x+width || y2 < y || y2 > y+height) {
			return false;
		}
		else {
			return true;
		}
	}
}
