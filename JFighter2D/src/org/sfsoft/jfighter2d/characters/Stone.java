package org.sfsoft.jfighter2d.characters;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.sfsoft.jfighter2d.managers.ResourceManager;

/**
 * Representa a rocas y piedras que pueden aparecer durante el juego en la pantalla
 * @author Santiago Faci
 * @version 1.0
 *
 */
public class Stone extends Enemy {

	public Stone(float x, float y, float speed) throws SlickException {
		super(x, y, speed);
	
		this.animation = ResourceManager.getAnimation("Stone");
		
		setRect(new Rectangle(x, y, this.animation.getWidth(), this.animation.getHeight()));
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
