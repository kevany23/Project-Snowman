package p1;

import processing.core.*;
import utilities.Timer;
import java.util.*;

public class Handler {
	private Player player;
	private LinkedList<GameObject> objectList;
	private LinkedList<Enemy> enemyList;
	private LinkedList<Platform> platformList;
	private LinkedList<Projectile> playerLaserList;
	private PApplet screen;
	private HUD hud;
	private Timer timer;
	private SnowmanSpawner snowSpawner;
	
	private Random random;
	
	public Handler(PApplet p, Player pl) {
		screen = p;
		player = pl;
		objectList = new LinkedList<GameObject>();
		enemyList = new LinkedList<Enemy>();
		platformList = new LinkedList<Platform>();
		playerLaserList = new LinkedList<Projectile>();
		snowSpawner = new SnowmanSpawner(screen, timer);
		objectList.add(snowSpawner);
		random = new Random();
		
	}
	
	public void render() {
		player.render();
		GameObject curr;
		ListIterator<GameObject> itr = objectList.listIterator();
		while(itr.hasNext()) {
			curr = itr.next();
			if(curr.shouldStay()) {
				curr.render();
			}
			else {
				itr.remove();
			}
		}
		hud.render();	
	}
	public void tick() {
		player.tick();
		ListIterator<GameObject> itr = objectList.listIterator();
		while(itr.hasNext()) {	
			itr.next().tick();
		}
		handlePlayerEnemies();
		handlePlatforms();
		handleHUD();
		handleSpawn();
		handlePlayerLaser();
	}
	
	public void insert(GameObject g) {
		objectList.add(g);
	}
	
	public void insert(Enemy e) {
		objectList.add(e);
		enemyList.add(e);
	}
	
	public void insert(Platform p) {
		objectList.add(p);
		platformList.add(p);
	}
	
	public void insert(HUD h) {
		hud = h;
	}
	
	public void insert(Projectile p) {
		objectList.add(p);
		playerLaserList.add(p);
	}
	
	
	public void addTimer(Timer t) {
		timer = t;
	}
	
	private boolean contains(Collider c1, Collider c2) {
		return c1.hitBox().contains(c2.hitBox());
	}
	
	private void handlePlayerEnemies() {
		ListIterator<Enemy> itr = enemyList.listIterator();
		Enemy curr = null;
		while(itr.hasNext()) {
			
			curr = itr.next();
			if(contains(player, curr)) {
				player.takeDamage(curr.getDamage());
			}
		}
	}
	
	private void handlePlatforms() {
		ListIterator<Platform> itr = platformList.listIterator();
		Platform curr = null;
		while(itr.hasNext()) {
			curr = itr.next();
			if(curr.willLand(player.getX(), player.getY(), player.getWidth(),
					player.getHeight(), player.getGround())) {
				player.setGround(curr.getY());
			}
			// enemies and platforms
			ListIterator<Enemy> itr2 = enemyList.listIterator();
			Enemy currE = null;
			HitBox hb = null;
			while(itr2.hasNext()) {
				currE = itr2.next();
				hb = currE.hitBox();
				if(curr.willLand(hb.getX(), hb.getY(), hb.getWidth(), hb.getHeight(),
						((Snowman)currE).getGround())) {
					System.err.println(((Snowman)currE).getGround());
					((Snowman)currE).setGround(curr.getY());
				}
			}
		}
	}
	
	private void handleHUD() {
		hud.setHealth(player.getHealth());
		hud.setTime(timer.getTime());
		hud.setScore(player.getScore());
	}
	
	private void handleSpawn() {
		int x = 100;
		int y = 100;
		if(snowSpawner.canSpawn()) {
			snowSpawner.spawn();
			insert(new Snowman(screen, snowSpawner.getX(), snowSpawner.getY()));
		}
	}
	
	private void handlePlayerLaser() {
		ListIterator<Projectile> itr = playerLaserList.listIterator();
		Projectile curr = null;
		while(itr.hasNext()) {
			
			curr = itr.next();
			if(!curr.shouldStay()) {
				continue;
			}
			ListIterator<Enemy> itr2 = enemyList.listIterator();
			Enemy currE = null;
			while(itr2.hasNext()) {
				currE = itr2.next();
				if(contains(curr, currE)) {
					((Snowman)currE).takeDamage(curr.getDamage());
					if(((Snowman)currE).getHealth() <= 0 ) {
						player.addScore(((Snowman)currE).getPoints());
						((Snowman)currE).delete();
						itr2.remove();
					}
					curr.remove();
					itr.remove();
					break;
				}
			}
		}
	}
	
	public void delete() {
		
	}
}
