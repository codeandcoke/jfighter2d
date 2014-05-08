package org.sfsoft.jfighter2d.effects;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.sfsoft.jfighter2d.managers.ResourceManager;

public class Explosion {

	private final int DURATION = 1500;
	
	private Animation animation;
	private long timeToLive;
	private long life;
	private boolean mustDie;
	
	private float x;
	private float y;
	
	public Explosion(float x, float y) throws SlickException {
		
		this.x = x;
		this.y = y;
		this.timeToLive = DURATION;
		this.life = 0;
		
		animation = ResourceManager.getAnimation("Explosion");
	}
	
	public void restart() {
		animation.restart();
	}
	
	public boolean mustDie() {
		return mustDie;
	}
	
	public void draw() {
		
		animation.draw(x, y - 10, animation.getWidth() / 3, animation.getHeight() / 3);
	}
	
	public void update(float dt) {
		
		life += dt;
		if (life >= timeToLive) 
			mustDie = true;
	}
}
