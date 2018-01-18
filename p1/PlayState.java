package p1;

import processing.core.*;
import utilities.Timer;

public class PlayState extends State{
	
	private Player player;
	private Snowman snowman;
	private Handler handler;
	private Platform ground;
	private HUD hud;
	private Timer timer;
	
	// for background photo
	PImage background;
	
	public PlayState(PApplet p) {
		screen = p;
		player = new Player(p);
		handler = new Handler(screen, player);
		hud = new HUD(screen);
		timer = new Timer();
		handler.insert(hud);
		handler.addTimer(timer);
		nextState = GState.play;
		ground = new Platform(screen, 1, screen.height, screen.width, 50);
		handler.insert(ground);
		//handler.insert(new Platform(screen, 1, screen.height- 50, screen.width/2, 50));
		background = screen.loadImage("winter_background_1.jpg");
		timer.start();
	}
	
	public void render() {
		// photo
		//screen.image(background, 0, 0, screen.width, screen.height);
		screen.background(255);
		handler.render();
	}
	
	public void tick() {
		//player.tick();
		timer.start();
		handler.tick();
		if(player.getHealth() <= 0) {
			player = new Player(screen);
			handler = new Handler(screen, player);
			hud = new HUD(screen);
			timer = new Timer();
			handler.insert(hud);
			handler.addTimer(timer);
			nextState = GState.play;
			ground = new Platform(screen, 1, screen.height, screen.width, 50);
			handler.insert(ground);
			background = screen.loadImage("winter_background_1.jpg");
			timer.start();
			nextState = GState.menu;
		}
	}
	
	public void mousePressed() {
		nextState = GState.play;
		//handler.insert(new Snowman(screen, screen.mouseX, screen.mouseY));
	}
	
	public void keyPressed() {
		if(screen.key == 10) {
			System.out.println("Game Paused");
			timer.stop();
			nextState = GState.pause;
		}
		else {
			player.keyPressed();
			if(screen.key == 'z') {
				handler.insert(player.shoot());
			}
			nextState = GState.play;
		}
	}
	
	public GState nextState() {
		return nextState;
	}
}
