package org.sfsoft.jfighter2d.characters;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.sfsoft.jfighter2d.managers.ResourceManager;

/**
 * Tipo de enemigo que es capaz de perseguir al personaje del juego
 * @author Santiago Faci
 *
 */
public class PursuerEnemy extends Enemy {

	private Ship ship;
	
	public PursuerEnemy(float x, float y, float speed) throws SlickException {
		super(x, y, speed);
		
		this.animation = ResourceManager.getAnimation("PursuerEnemy");
		
		setRect(new Rectangle(x, y, this.animation.getWidth(), this.animation.getHeight()));
		setValue(10);
		setLives(1);
	}
	
	public void setShip(Ship ship) {
		this.ship = ship;
	}

	@Override
	public void update(float dt) {
		
		setX(getX() + getSpeed() * dt);
		
		// Si están por delante del personaje, le seguirán en el eje Y mientras se acerca a él
		if (getX() > ship.getX()) {
			if (ship.getY() > getY()) {
				setY(getY() + (Math.abs(getSpeed()) * dt));
			}
			else if (ship.getY() < getY()) {
				setY(getY() - (Math.abs(getSpeed()) * dt));
			}
		}
		
		setRectX(getX());
		setRectY(getY());
	}
	
	@Override
	public void draw() {
		this.animation.draw(getX(), getY());
	}

}
