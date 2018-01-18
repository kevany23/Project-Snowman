package p1;
import processing.core.*;

//TODO update hitbox

public class Player extends GameObject implements Collider{
	
	private static final int DEFAULT_WIDTH = 35;
	private static final int DEFAULT_HEIGHT = 60;
	private static final float DEFAULT_SPEED = 5;
	private static final float DEFAULT_JUMP = 20;
	
	private float x = 500;
	private float y = 600;
	private float xVelocity = 0;
	
	private float direction;
	
	private float jumpVelocity = 50;
	private float yVelocity = 0;
	private float yAcceleration = -1.2f;
	private boolean falling = true;
	
	private float ground = 1500;
	
	private HitBox hitbox;
	
	private int health = 1000;
	private int score = 0;
	
	private LaserGun gun;
	
	public Player(PApplet p) {
		screen = p;
		hitbox = new HitBox(x, y, DEFAULT_HEIGHT, DEFAULT_WIDTH);
		gun = new LaserGun(p, x, y, 50);
	}
	
	public void render() {
		screen.fill(0);
		screen.rect(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	public void tick() {
		if(screen.keyPressed) {
			x = x + xVelocity;
		}
		else {
			xVelocity = 0;
		}
		
		if(falling) {
			y = y - yVelocity;
			yVelocity = yVelocity + yAcceleration;
			if(y > ground) {
				breakFall(ground);
			}
		}
		x = clamp(x, DEFAULT_WIDTH, 0, screen.width);
		//y = clamp(y, DEFAULT_HEIGHT, 0, screen.height); old
		//y = clamp(y, DEFAULT_HEIGHT, 0, ground);
		hitbox.setLocation(x, y);
		gun.setLocation(x, y);
	}
	
	public void setVelocity(float x, float y) {
		
	}
	
	public void setLocation(float nx, float ny) {
		x = nx;
		y = ny;
		hitbox.setLocation(nx, ny);
	}
	
	public void keyPressed() {
		if(screen.key == screen.CODED ) {
			if(screen.keyCode == screen.LEFT) {
				xVelocity = -1 * DEFAULT_SPEED;
				direction = xVelocity;
			}
			else if(screen.keyCode == screen.RIGHT) {
				xVelocity = DEFAULT_SPEED;
				direction = xVelocity;
			}
			else {
			}
		}
		else {
			//xVelocity = 0;
			if(screen.key == ' ' && !falling) {
				yVelocity = DEFAULT_JUMP;
				falling = true;
			}
		}
	}
	
	public HitBox hitBox() {
		return hitbox;
	}
	
	public boolean contains(Collider c) {
		return hitbox.contains(c.hitBox());
	}
	
	public int getHealth() {
		return health;
	}
	
	public void takeDamage(int dmg) {
		health = health - dmg;
	}
	
	public void breakFall(float ny) {
		falling = false;
		yVelocity = 0;
		y = ny;
	}
	
	public void setGround(float ny) {
		ground = ny - DEFAULT_HEIGHT;
	}
	
	public int getHeight() {
		return DEFAULT_HEIGHT;
	}
	
	public int getWidth() {
		return DEFAULT_WIDTH;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getGround() {
		return ground;
	}
	
	public Projectile shoot() {
		return gun.shoot(direction);
	}
	
	public int getScore() {
		return score;
	}
	
	public void addScore(int x) {
		score = score + x;
	}
}
