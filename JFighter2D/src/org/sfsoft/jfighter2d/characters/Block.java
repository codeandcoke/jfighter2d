package org.sfsoft.jfighter2d.characters;

import org.newdawn.slick.SpriteSheet;
import org.sfsoft.jfighter2d.managers.ResourceManager;

import static org.sfsoft.jfighter2d.util.Constants.BLOCK_WIDTH;
import static org.sfsoft.jfighter2d.util.Constants.BLOCK_HEIGHT;

public class Block extends Character {

	private SpriteSheet spriteSheet;
	private int width;
	private int height;
	
	public Block(float x, float y, int width, int height) {
		super(x, y);
		
		this.spriteSheet = ResourceManager.getSpriteSheet("blocks");
		this.width = width;
		this.height = height;
	}
	
	public void update(float dt) {
		
		setX(getX() + getSpeed() * dt);
	}
	
	@Override
	public void draw() {
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				
				spriteSheet.getSprite(14, 5).draw(x + BLOCK_WIDTH * i, y - BLOCK_HEIGHT * (j + 1));		
			}
		}
		
	}
	
	
}
