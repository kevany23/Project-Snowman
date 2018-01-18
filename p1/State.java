package p1;

import processing.core.*;

public abstract class State {
	protected PApplet screen;
	protected GState nextState;
	
	public abstract void render();
	public abstract void tick();
	public abstract void mousePressed();
	public abstract void keyPressed();
	// makes game switch states when needed
	public abstract GState nextState();
}
