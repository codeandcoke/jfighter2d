package org.sfsoft.jfighter2d.managers;

import static org.sfsoft.jfighter2d.util.Constants.CHARACTER_HEIGHT;
import static org.sfsoft.jfighter2d.util.Constants.HEIGHT;

import java.util.Random;

import org.newdawn.slick.SlickException;
import org.sfsoft.jfighter2d.JFighter2D;
import org.sfsoft.jfighter2d.characters.BigEnemy;
import org.sfsoft.jfighter2d.characters.Enemy;
import org.sfsoft.jfighter2d.characters.PursuerEnemy;
import org.sfsoft.jfighter2d.characters.ShooterBullet.BulletDirection;
import org.sfsoft.jfighter2d.characters.ShooterEnemy;
import org.sfsoft.jfighter2d.characters.SmallEnemy;
import org.sfsoft.jfighter2d.characters.StaticShooterEnemy;
import org.sfsoft.jfighter2d.characters.Stone;
import org.sfsoft.jfighter2d.characters.Enemy.EnemyType;

/**
 * Clase donde se gestiona la generación de enemigos de diferentes tipos
 * @author Santiago Faci
 * @version 1.0
 *
 */
public class EnemySpawner {

	/**
	 * Crea un enemigo en la parte derecha de la pantalla y a un altura aleatoria
	 * @param type
	 * @return
	 * @throws SlickException
	 */
	public static Enemy createEnemy(EnemyType type, SpriteManager spriteManager) throws SlickException {
		
		Enemy enemy = null;
		
		switch (type) {
		
			case SMALL_ENEMY:
				enemy = new SmallEnemy(1000, new Random().nextInt(HEIGHT - CHARACTER_HEIGHT), -0.1f);
				break;
			case SHOOTER_ENEMY:
				int location = new Random().nextInt(2);
				
				if (location == 0) { 
					location = 20;
					enemy = new ShooterEnemy(1000, location, -0.3f, BulletDirection.DOWN);
				}
				else {
					location = HEIGHT - CHARACTER_HEIGHT - 20;
					enemy = new ShooterEnemy(1000, location, -0.3f, BulletDirection.UP);
				}
				((ShooterEnemy) enemy).setEnemies(spriteManager.getEnemies());
				
				break;
			case STONE:
				enemy = new Stone(1000, new Random().nextInt(HEIGHT - CHARACTER_HEIGHT), -0.1f);
				break;
			case BIG_ENEMY:
				enemy = new BigEnemy(1000, new Random().nextInt(HEIGHT - CHARACTER_HEIGHT * 2), -0.2f);
				break;
			case PURSUER_ENEMY:
				enemy = new PursuerEnemy(1000, new Random().nextInt(HEIGHT - CHARACTER_HEIGHT), -0.2f);
				((PursuerEnemy) enemy).setShip(spriteManager.getShip());
				break;
			case STATIC_SHOOTER_ENEMY:
				enemy = new StaticShooterEnemy(900, new Random().nextInt(HEIGHT - CHARACTER_HEIGHT), -0.2f, BulletDirection.LEFT);
				((StaticShooterEnemy) enemy).setEnemies(spriteManager.getEnemies());
			default:
				break;
		}
		
		return enemy;
	}
	
	/**
	 * Crea un enemigo en un lugar determinado de la pantalla
	 * @param type
	 * @param x
	 * @param y
	 * @return
	 * @throws SlickException
	 */
	public static Enemy createEnemy(EnemyType type, float x, float y, SpriteManager spriteManager) throws SlickException {
		
		Enemy enemy = null;
		
		switch (type) {
		
		case SMALL_ENEMY:
			enemy = new SmallEnemy(x, y, -0.1f);
			break;
		case SHOOTER_ENEMY:
			enemy = new ShooterEnemy(x, y, -0.3f, BulletDirection.UP);
			((ShooterEnemy) enemy).setEnemies(spriteManager.getEnemies());
			break;
		case STONE:
			enemy = new Stone(x, y, -0.1f);
			break;
		case BIG_ENEMY:
			enemy = new BigEnemy(x, y, -0.2f);
			break;
		case PURSUER_ENEMY:
			enemy = new PursuerEnemy(x, y, -0.2f);
			((PursuerEnemy) enemy).setShip(spriteManager.getShip());
			break;
		case STATIC_SHOOTER_ENEMY:
			enemy = new StaticShooterEnemy(x, y, -0.2f, BulletDirection.LEFT);
			((StaticShooterEnemy) enemy).setEnemies(spriteManager.getEnemies());
		default:
			break;
		}
		
		return enemy;
	}
	
	/**
	 * Crea un enemigo que aparece en la parte derecha de la pantalla
	 * @param type
	 * @param y
	 * @return
	 * @throws SlickException
	 */
	public static Enemy createEnemy(EnemyType type, float y, SpriteManager spriteManager) throws SlickException  {
		
		return createEnemy(type, 1000, y, spriteManager);
	}
}
