package p1;

import processing.core.*;

public class MenuState extends State{
	Button startButton;
	Button exitButton;
	
	public MenuState(PApplet s) {
		nextState = GState.menu;
		screen = s;
		startButton = new Button("Start", screen.width/4, screen.height/3, 600, 200, screen);
	}
	

	public void render() {
		screen.background(100);
		startButton.render();
		
	}
	
	public void tick() {
	}
	
	public void mousePressed() {
		if(startButton.contains(screen.mouseX, screen.mouseY)) {
			nextState = GState.play;
		}
		else {
			nextState = GState.menu;
		}
	}
	
	public void keyPressed() {
		nextState = GState.menu;
	}
	
	public GState nextState() {
		return nextState;
	}
	
	public void setMenu() {
		nextState = GState.menu;
	}
}
