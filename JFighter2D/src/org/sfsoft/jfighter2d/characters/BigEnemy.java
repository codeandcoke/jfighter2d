package org.sfsoft.jfighter2d.characters;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.sfsoft.jfighter2d.managers.ResourceManager;

/**
 * Tipo de enemigo de grandes dimensiones y mayor resistencia
 * @author Santiago Faci
 *
 */
public class BigEnemy extends Enemy {
	
	public BigEnemy(float x, float y, float speed) throws SlickException {
		super(x, y, speed);
		
		this.animation = ResourceManager.getAnimation("BigEnemy");
		
		setRect(new Rectangle(x, y, this.animation.getWidth(), this.animation.getHeight()));
		setValue(5);
		setLives(4);
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
}
