package org.sfsoft.jfighter2d.managers;

import static org.sfsoft.jfighter2d.util.Constants.HEIGHT;
import static org.sfsoft.jfighter2d.util.Constants.ITEM_HEIGHT;

import java.util.Random;

import org.newdawn.slick.SlickException;
import org.sfsoft.jfighter2d.powerups.Bomb;
import org.sfsoft.jfighter2d.powerups.Powerup;
import org.sfsoft.jfighter2d.powerups.Powerup.PowerupType;
import org.sfsoft.jfighter2d.powerups.Shield;

public class PowerupSpawner {

	public static Powerup createPowerup(PowerupType type) throws SlickException {
		
		Powerup powerup = null;
		
		switch (type) {
			case BOMB:
				powerup = new Bomb(1000, new Random().nextInt(HEIGHT - ITEM_HEIGHT), -0.2f);
				break;
			case SHIELD:
				powerup = new Shield(1000, new Random().nextInt(HEIGHT - ITEM_HEIGHT), -0.2f);
				break;
		}
		
		return powerup;
	}
}
