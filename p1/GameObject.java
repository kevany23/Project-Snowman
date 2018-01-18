package p1;
import processing.core.*;

public abstract class GameObject {
	protected boolean active = true;
	public PApplet screen;
	public abstract void tick();
	public abstract void render();
	
	public boolean shouldStay() {
		return active;
	}
	
	public static float clamp(float x, int width, float x1, float x2) {
		float r;
		if (x < x1) {
			r = x1;
		}
		else if(x > x2 - width) {
			r = x2 - width;
		}
		else {
			r = x;
		}
		return r;
	}
}
