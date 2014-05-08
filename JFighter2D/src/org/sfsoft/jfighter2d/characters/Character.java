package org.sfsoft.jfighter2d.characters;

import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Rectangle;

/**
 * Representa cualquier elemento animado del juego
 * @author Santiago Faci
 *
 */
public abstract class Character {

	private float speed;
	private float bulletSpeed;
	private int bulletRate;
	private int points;
	private int lives;
	
	protected float x;
	protected float y;
	private Rectangle rect;
	
	protected Animation animation;
	
	public enum Direction {
		LEFT, RIGHT
	}
	
	public Character(float x, float y) {
		
		// Default values
		this.speed = 0.2f;
		this.bulletSpeed = 0.5f;
		this.bulletRate = 200;
		this.points = 100;
		this.lives = 1;
		
		this.x = x;
		this.y = y;
	}
	
	public Character(float x, float y, float speed) {
		
		// Default values
		this.speed = speed;
		this.bulletSpeed = 0.5f;
		this.bulletRate = 200;
		this.points = 100;
		this.lives = 1;
		
		this.x = x;
		this.y = y;
	}
	
	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getBulletSpeed() {
		return bulletSpeed;
	}

	public void setBulletSpeed(float bulletSpeed) {
		this.bulletSpeed = bulletSpeed;
	}

	public int getBulletRate() {
		return bulletRate;
	}

	public void setBulletRate(int bulletRate) {
		this.bulletRate = bulletRate;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public Rectangle getRect() {
		return rect;
	}
	
	public void setRect(Rectangle rect) {
		this.rect = rect;
	}
	
	public float getRectX() {
		return rect.getX();
	}
	
	public void setRectX(float x) {
		rect.setX(x);
	}
	
	public float getRectY() {
		return rect.getY();
	}
	
	public void setRectY(float y) {
		rect.setY(y);
	}
	
	public void hit() {
		lives--;
	}
	
	public abstract void draw();
	
	public void die() {
		
	}
}
