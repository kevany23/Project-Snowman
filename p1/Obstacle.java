package p1;

/*
 * Needs to have a collider
 */

public interface Obstacle {

	public void blockMovement();
	public boolean willLand(float dx, float dy, int dw, int dh, float g);
}
