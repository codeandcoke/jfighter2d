package org.sfsoft.jfighter2d.characters;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.sfsoft.jfighter2d.managers.ResourceManager;

public class ShipBullet extends Bullet {

	public ShipBullet(float x, float y, float speed)
			throws SlickException {
		super(x, y, speed);
		
		this.image = ResourceManager.getImage("ShipBullet");
		setRect(new Rectangle(x, y, image.getWidth(), image.getHeight()));
	}

	@Override
	public void update(float dt) {
		setX(getX() + getSpeed() * dt);
		setRectX(getX());
	}
}
