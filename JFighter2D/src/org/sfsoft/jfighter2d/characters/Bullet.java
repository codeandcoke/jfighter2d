package org.sfsoft.jfighter2d.characters;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 * Representa los misiles disparados por la nave del personaje
 * @author Santiago Faci
 * @version 1.0
 *
 */
public abstract class Bullet extends Character {

	protected Image image;
	
	public Bullet(float x, float y, float speed) throws SlickException {
		super(x, y, speed);
	}
	
	public void draw() {
		
		image.draw(getX(), getY());
	}
	
	public abstract void update(float dt);

}
