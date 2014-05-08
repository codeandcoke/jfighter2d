package org.sfsoft.jfighter2d.powerups;

import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.sfsoft.jfighter2d.managers.ResourceManager;

public class Bomb extends Powerup {
	
	private Image image;
	
	public Bomb(float x, float y, float speed) throws SlickException {
		
		super(x, y, speed);
		
		image = ResourceManager.getImage("Bomb");
		setRect(new Rectangle(x, y, this.image.getWidth(), this.image.getHeight()));
	}

	@Override
	public void draw() {

		this.image.draw(getX(), getY());
	}
}
