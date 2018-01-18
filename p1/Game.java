package p1;

import processing.core.*;

public class Game extends PApplet{
	
	private static final int GAME_WIDTH = 1200;
	private static final int GAME_HEIGHT = 800;
	private static  State curr;
	private static PlayState play;
	private static MenuState menu;
	private static PauseState pause;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main(Game.class.getName());

	}
	public void settings() {
		size(GAME_WIDTH, GAME_HEIGHT);
	}
	public void setup() {
		play = new PlayState(this);
		menu = new MenuState(this);
		pause = new PauseState(this);
		curr = menu;
		StateChanger.instance().loadStates(menu, play, pause);
	}
	public void draw() {
		//background(100);
		long time = System.currentTimeMillis();
		curr.render();
		curr.tick();
		time = System.currentTimeMillis() - time;
		double time2 = time;
		//System.out.println("FPS: " + time2);
	}
	
	public void mousePressed() {
		curr.mousePressed();
		curr = StateChanger.instance().nextState(curr.nextState());
	}

	public void keyPressed() {
		curr.keyPressed();
		curr = StateChanger.instance().nextState(curr.nextState());
	}
	
}
