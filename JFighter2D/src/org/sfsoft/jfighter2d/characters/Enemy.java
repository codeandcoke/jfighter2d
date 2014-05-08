package org.sfsoft.jfighter2d.characters;

import org.newdawn.slick.Animation;

/**
 * Clase base de todos los enemigos del personaje en el juego
 * @author Santiago Faci
 *
 */
public abstract class Enemy extends Character {
	
	public enum EnemyType {
		SMALL_ENEMY, STONE, SHOOTER_ENEMY, PURSUER_ENEMY, BIG_ENEMY, STATIC_SHOOTER_ENEMY
	}
	
	private int value;
	
	public Enemy(float x, float y) {
		super(x, y);
	}
	
	public Enemy(float x, float y, float speed) {
		super(x, y, speed);
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * Calcula cuál será el movimiento del enemigo y lo desplaza
	 * @param dt
	 */
	public abstract void update(float dt);
}
