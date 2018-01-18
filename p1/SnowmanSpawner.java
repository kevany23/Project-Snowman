package p1;

import utilities.Timer;
import processing.core.*;
import java.util.Random;

public class SnowmanSpawner extends GameObject implements Spawner {
	
	private static final long SPAWN_RATE = 1000;
	
	private Timer timer;
	private Random random;
	private long startTime;
	private long currTime;
	private Snowman snowman;
	
	private int x;
	private int y;
	
	private boolean toggle = true;
	
	public SnowmanSpawner(PApplet p, Timer t) {
		screen = p;
		timer = t;
		random = new Random();
		startTime = System.currentTimeMillis();
	}

	public void spawn() {
		// TODO Auto-generated method stub
		x = random.nextInt(screen.width);
		y = random.nextInt(screen.height);
		startTime = System.currentTimeMillis();
		currTime = System.currentTimeMillis();
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}

	public void toggle() {
		// TODO Auto-generated method stub
		toggle = !toggle;
		
	}
	
	public boolean canSpawn() {
		boolean b =  (currTime - startTime) > SPAWN_RATE;
		return (toggle && b);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		currTime = System.currentTimeMillis();
	}

	// spawn snowmen here
	@Override
	public void render() {
		// TODO Auto-generated method stub
		//spawn(x, y);
		
		
	}

}
