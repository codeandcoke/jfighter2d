package org.sfsoft.jfighter2d.characters;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.sfsoft.jfighter2d.characters.ShooterBullet.BulletDirection;
import org.sfsoft.jfighter2d.managers.ResourceManager;

/**
 * Un tipo de enemigo que es capaz de disparar
 * @author Santiago Faci
 *
 */
public class ShooterEnemy extends Enemy {

	private final int BULLET_RATE = 800;
	
	private List<Enemy> enemies;
	private BulletDirection bulletDirection;
	private int elapsedTimeBetweenBullets;
	
	public ShooterEnemy(float x, float y, float speed, BulletDirection bulletDirection) throws SlickException {
		super(x, y, speed);
		
		this.animation = ResourceManager.getAnimation("ShooterEnemy");
		
		setRect(new Rectangle(x, y, this.animation.getWidth(), this.animation.getHeight()));
		setValue(5);
		setLives(1);
		
		this.bulletDirection = bulletDirection;
		elapsedTimeBetweenBullets = 0;
	}

	public void setEnemies(List<Enemy> enemies) {
		this.enemies = enemies;
	}
	
	public void setBulletDirection(BulletDirection bulletDirection) {
		this.bulletDirection = bulletDirection;
	}
	
	@Override
	public void update(float dt) {
		
		setX(getX() + getSpeed() * dt);
		setRectX(getX());
	}
	
	@Override
	public void draw() {
		this.animation.draw(getX(), getY());
	}
	
	public void shoot(float dt) throws SlickException {
		
		elapsedTimeBetweenBullets += dt;
		
		if (elapsedTimeBetweenBullets >= BULLET_RATE) {
		
			elapsedTimeBetweenBullets = 0;
			Enemy bullet = new ShooterBullet(getX(), getY(), 0.2f, bulletDirection);
			enemies.add(bullet);
		}
	}
}
