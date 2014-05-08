package org.sfsoft.jfighter2d.characters;

import static org.sfsoft.jfighter2d.util.Constants.HEIGHT;
import static org.sfsoft.jfighter2d.util.Constants.WIDTH;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;
import org.sfsoft.jfighter2d.JFighter2D;
import org.sfsoft.jfighter2d.managers.ConfigurationManager;
import org.sfsoft.jfighter2d.managers.ResourceManager;
import org.sfsoft.jfighter2d.managers.SpriteManager;
import org.sfsoft.jfighter2d.util.Constants;

/**
 * Nave del personaje del juego
 * @author Santiago Faci
 * @version 1.0
 *
 */
public class Ship extends Character {

	// Proyectiles disparados por la nave
	private List<Bullet> bullets;
	private int bombs;
	private int missiles;
	private int score;
	private float shieldTime;
	private float bulletTime;
	
	private Animation animation;
	
	public Ship(float x, float y, float speed) throws SlickException {
		super(x, y, speed);
		
		this.bullets = new ArrayList<Bullet>();
		this.bombs = 3;
		this.missiles = 10;
		this.score = 0;
		this.shieldTime = 0;
		
		this.animation = ResourceManager.getAnimation("Ship");
		setRect(new Rectangle(x, y, this.animation.getWidth(), this.animation.getHeight()));
	}
	
	public List<Bullet> getBullets() {
		return bullets;
	}
	
	public void setBullets(List<Bullet> bullets) {
		this.bullets = bullets;
	}
	
	public int getBombs() {
		return bombs;
	}
	
	public void setBombs(int bombs) {
		this.bombs = bombs;
	}
	
	public int getMissiles() {
		return missiles;
	}
	
	public void setMissiles(int missiles) {
		this.missiles = missiles;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void addScore(int score) {
		this.score += score;
	}
	
	public float getShieldTime() {
		return shieldTime;
	}
	
	public void setShieldTime(float time) {
		this.shieldTime = time;
	}
	
	@Override
	public void draw() {
		this.animation.draw(this.getX(), this.getY());
	}
	
	public void update(float dt, Input input, SpriteManager spriteManager) throws SlickException {
		
		bulletTime += dt;
		
		if (input.isKeyDown(Input.KEY_LEFT)) {
			
			if (getX() > 0)
				setX(getX() - getSpeed() * dt);
		}
		
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			
			if (getX() < WIDTH - Constants.CHARACTER_WIDTH)
				setX(getX() + getSpeed() * dt);
		}
		
		if (input.isKeyDown(Input.KEY_DOWN)) {
			
			if (getY() < HEIGHT - Constants.CHARACTER_HEIGHT)
				setY(getY() + getSpeed() * dt);
		}
		
		if (input.isKeyDown(Input.KEY_UP)) {
			
			if (getY() > 0)
				setY(getY() - getSpeed() * dt);
		}
		
		setRectX(getX());
		setRectY(getY());
		
		// Disparo estándar
		if (input.isKeyDown(Input.KEY_SPACE)) {
			
			if (bulletTime >= getBulletRate()) {
				bulletTime = 0;
				
				// Dispara un proyectil
				shoot();
			}
		}
		
		// Lanzar bomba
		if (input.isKeyPressed(Input.KEY_Z)) {
			
			if (getBombs() > 0) {
				spriteManager.killAllEnemies();
				setBombs(getBombs() - 1);
			}
		}
		
		// Lanzar misil
		if (input.isKeyPressed(Input.KEY_X)) {
			
			if (getMissiles() > 0) {
			
				setMissiles(getMissiles() - 1);
				launchMissile();
			}
		}
		
		setShieldTime(getShieldTime() - dt);
		if (getShieldTime() < 0)
			setShieldTime(0);
	}
	
	/**
	 * Dispara un proyectil
	 * @throws SlickException
	 */
	public void shoot() throws SlickException {
		
		Bullet bullet = new ShipBullet(getX() + 50, getY(), getBulletSpeed());
		getBullets().add(bullet);
		if (ConfigurationManager.SOUND)
			ResourceManager.getSound("disparo").play();
		
	}
	
	/**
	 * Lanza un misil
	 * @throws SlickException
	 */
	public void launchMissile() throws SlickException {
		
		Missile missile = new Missile(getX(), getY() + 20, getBulletSpeed() / 2);
		getBullets().add(missile);
	}
	
}
