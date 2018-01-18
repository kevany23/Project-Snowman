package p1;

import processing.core.*;

public abstract class Enemy extends GameObject implements Collider {
	
	private HitBox hitbox;
	// idea: enemy projectiles are classified as enemies
	public abstract int getDamage();

}
