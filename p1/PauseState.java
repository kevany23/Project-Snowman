package p1;

import processing.core.*;

public class PauseState extends State{
	
	public PauseState(PApplet p) {
		screen = p;
		nextState = GState.pause;
	}

	public void render() {
		screen.background(255);
		screen.fill(0);
		screen.text("Paused", screen.width/2, screen.height/2);
	}
	public void tick() {
		
	}
	public void mousePressed() {
		nextState = GState.pause;
	}
	public void keyPressed() {
		if(screen.key == 10) {
			System.out.println("Game Resumed");
			nextState = GState.play;
		}
		else {
			nextState = GState.pause;
		}
	}
	
	public GState nextState() {
		return nextState;
	}
}
