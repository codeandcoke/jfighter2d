package org.sfsoft.jfighter2d.characters;

import org.newdawn.slick.SlickException;
import org.sfsoft.jfighter2d.characters.ShooterBullet.BulletDirection;
import org.sfsoft.jfighter2d.util.Constants;

/**
 * Tipo de enemigo que permanace en la parte derecha de la pantalla, sube y baja y dispara hacia la izquierda
 * @author Santiago Faci
 * @version 1.0
 *
 */
public class StaticShooterEnemy extends ShooterEnemy {

	private float arc;
	private float time;
	private float y0;
	
	public StaticShooterEnemy(float x, float y, float speed, BulletDirection bulletDirection) throws SlickException {
		super(x, y, speed, bulletDirection);
		
		arc = Constants.HEIGHT / 2 - 50;
		time = 0;
		y0 = Constants.HEIGHT / 2;
	}
	
	@Override
	public void update(float dt) {
		
		time += dt;
		
		// Describe un movimiento senoidal con centro en y0 y apertura según la variable arc
		setY(arc * (float) Math.sin(time * 0.5f * Math.PI / 900) + y0);
		
		setRectY(getY());
	}
}
