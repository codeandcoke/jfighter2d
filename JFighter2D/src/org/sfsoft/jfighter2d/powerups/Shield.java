package org.sfsoft.jfighter2d.powerups;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.sfsoft.jfighter2d.managers.ResourceManager;

public class Shield extends Powerup{
	
	private Image image;
	
	public Shield(float x, float y, float speed) throws SlickException {
		
		super(x, y, speed);
		this.image = ResourceManager.getImage("Shield");
		setRect(new Rectangle(x, y, this.image.getWidth(), this.image.getHeight()));
	}

	@Override
	public void draw() {

		this.image.draw(getX(), getY());
	}
}
