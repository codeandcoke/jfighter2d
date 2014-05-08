package org.sfsoft.jfighter2d.characters;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.sfsoft.jfighter2d.managers.ResourceManager;

public class Missile extends Bullet {

	private Animation animation;
	private float acceleration;
	
	public Missile(float x, float y, float speed) throws SlickException {
		super(x, y, speed);
		
		this.animation = ResourceManager.getAnimation("Missile");
		this.acceleration = 0;
		setRect(new Rectangle(x, y, animation.getWidth(), animation.getHeight()));
	}

	@Override
	public void draw() {
		
		this.animation.draw(getX(), getY());
	}

	@Override
	public void update(float dt) {
		
		setX(getX() + getSpeed() * dt);
		setY(getY() + getSpeed() * acceleration * dt);
		
		setRectX(getX());
		setRectY(getY());
		
		if (acceleration < 5f)
			acceleration += 0.005f;
	}
}
