package p1;

import processing.core.*;

public class Button extends GameObject{

	String text;
	int x;
	int y;
	int width;
	int height;
	
	public Button(String t, int nx, int ny, int w, int h, PApplet p) {
		text = t;
		x = nx;
		y = ny;
		width = w;
		height = h;
		screen = p;
	}
	
	public boolean contains(int x2, int y2) {
		if(x2 < x || x2 > x+width || y2 < y || y2 > y+height) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public void onClick() {
		
	}
	
	public void render() {
		screen.fill(255);
		screen.rect(x, y, width, height);
		screen.fill(0);
		screen.textSize(width/4);
		screen.text(text, x + width/4, y + height/2);
	}
	
	public void tick() {
		
	}
	
}
