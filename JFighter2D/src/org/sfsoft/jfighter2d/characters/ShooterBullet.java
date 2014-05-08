package org.sfsoft.jfighter2d.characters;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.sfsoft.jfighter2d.managers.ResourceManager;

public class ShooterBullet extends Enemy {

	public enum BulletDirection {
		UP, LEFT, RIGHT, DOWN
	}
	
	private final float fallingSpeed = 0.1f;
	private BulletDirection bulletDirection;
	
	private Image image;
	
	public ShooterBullet(float x, float y, float speed, BulletDirection bulletDirection)
			throws SlickException {
		super(x, y, speed);
		
		this.bulletDirection = bulletDirection;
		this.image = ResourceManager.getImage("ShooterBullet");
		setRect(new Rectangle(x, y, image.getWidth(), image.getHeight()));
	}

	public void update(float dt) {
		
		switch (bulletDirection) {
			case UP:
				setY(getY() - fallingSpeed * dt);
				setX(getX() - getSpeed() * dt);
				break;
			case DOWN:
				setY(getY() + fallingSpeed * dt);
				setX(getX() - getSpeed() * dt);
				break;
			case LEFT:
				setX(getX() - getSpeed() * dt);
				break;
			case RIGHT:
				setX(getX() + getSpeed() * dt);
				break;
			default:
				break;
		}			
		
		setRectX(getX());
		setRectY(getY());
	}
	
	@Override
	public void draw() {
		this.image.draw(getX(), getY());
	}
}
