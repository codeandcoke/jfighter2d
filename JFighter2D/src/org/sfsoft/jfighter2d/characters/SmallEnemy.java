package org.sfsoft.jfighter2d.characters;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.sfsoft.jfighter2d.managers.ResourceManager;

/**
 * Enemigo estándar, de pequeñas dimensiones y poca movilidad
 * @author Santiago Faci
 *
 */
public class SmallEnemy extends Enemy {

	public SmallEnemy(float x, float y, float speed) throws SlickException {
		super(x, y, speed);
		
		this.animation = ResourceManager.getAnimation("SmallEnemy");
		
		setRect(new Rectangle(x, y, this.animation.getWidth(), this.animation.getHeight()));
		setValue(5);
		setLives(1);
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
