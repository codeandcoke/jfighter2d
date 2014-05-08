package org.sfsoft.jfighter2d.managers;

import static org.sfsoft.jfighter2d.util.Constants.HEIGHT;
import static org.sfsoft.jfighter2d.util.Constants.WIDTH;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.sfsoft.jfighter2d.characters.Block;
import org.sfsoft.jfighter2d.characters.Bullet;
import org.sfsoft.jfighter2d.characters.Enemy;
import org.sfsoft.jfighter2d.characters.Ship;
import org.sfsoft.jfighter2d.characters.ShooterEnemy;
import org.sfsoft.jfighter2d.characters.StaticShooterEnemy;
import org.sfsoft.jfighter2d.effects.Explosion;
import org.sfsoft.jfighter2d.powerups.Powerup;
import org.sfsoft.jfighter2d.util.Constants;

/**
 * Genera y actualiza todos los elementos que se dibujan en la pantalla
 * @author Santiago Faci
 * @version 1.0
 */
public class SpriteManager {

	private Image background;
	private Ship ship;
	private List<Enemy> enemies;
	private List<Bullet> enemyBullets;
	private List<Explosion> explosions;
	private List<Powerup> powerups;
	private List<Block> blocks;
	
	public SpriteManager() throws SlickException {
	
		background = ResourceManager.getImage("background");
		
		ship = new Ship(50, 50, 0.3f);
		ship.setLives(Constants.SHIP_LIVES);
		
		enemies = new ArrayList<Enemy>();
		enemyBullets = new ArrayList<Bullet>();
		explosions = new ArrayList<Explosion>();
		powerups = new ArrayList<Powerup>();
		blocks = new ArrayList<Block>();
	}
	
	public Ship getShip() {
		return ship;
	}
	
	public List<Enemy> getEnemies() {
		return enemies;
	}
	
	public List<Bullet> getEnemyBullets() {
		return enemyBullets;
	}
	
	public List<Explosion> getExplosions() {
		return explosions;
	}
	
	public List<Powerup> getPowerups() {
		return powerups;
	}
	
	public List<Block> getBlocks() {
		return blocks;
	}
	
	/**
	 * Actualiza la posición de los elementos de la pantalla
	 * @param dt
	 */
	public void update(float dt) throws SlickException {
		
		updateEnemies(dt);
		updateBlocks(dt);
		updateShipBullets(dt);
		updatePowerups(dt);
		updateExplosions(dt);
		
		checkCollisions(dt);
	}
	
	/**
	 * Actualiza el estado de los enemigos
	 * @param dt
	 * @throws SlickException
	 */
	private void updateEnemies(float dt) throws SlickException {
		
		// Desplaza todos los enemigos por la pantalla
		for (int i = enemies.size() - 1; i >= 0; i--) {
			
			// Desplaza al enemigo por la pantalla
			Enemy enemy = enemies.get(i);
			enemy.update(dt);
			
			if (enemy.getClass().getSimpleName().equals("ShooterEnemy"))
				((ShooterEnemy) enemy).shoot(dt);
			
			if (enemy.getClass().getSimpleName().equals("StaticShooterEnemy"))
				((StaticShooterEnemy) enemy).shoot(dt);
			
			// Si algún enemigo sale de la pantalla se hace desaparecer
			if ((enemy.getX() < 0) || (enemy.getY() < 0)) {
				enemies.remove(i);
			}			
		}
	}
	
	/**
	 * Actualiza el estado de los bloques de tierra
	 * @param dt
	 */
	private void updateBlocks(float dt) {
		
		for (Block block : blocks)
			block.update(dt);
	}
	
	/**
	 * Actualiza el estado de los proyectiles del personaje
	 * @param dt
	 */
	private void updateShipBullets(float dt) {
		
		List<Bullet> bullets = ship.getBullets();
		Bullet bullet = null;
		
		for (int i = bullets.size() - 1; i >= 0; i--) {
			
			bullet = bullets.get(i);
			bullet.update(dt);
			
			// Si el proyectil sale de la pantalla se elimina
			if ((bullet.getX() < 0) || (bullet.getX() > WIDTH) || (bullet.getY() < 0) || (bullet.getY() > HEIGHT))
				bullets.remove(i);
		}
	}
	
	/**
	 * Actualiza el estado de los powerups en pantalla
	 * @param dt
	 */
	private void updatePowerups(float dt) {
		
		Powerup item = null;
		
		for (int i = powerups.size() - 1; i >= 0; i--) {
			item = powerups.get(i);
			item.update(dt);
			
			if (item.getX() < 0)
				powerups.remove(i);
		}
	}
	
	/**
	 * Actualiza el estado de las explosiones en pantalla
	 * @param dt
	 */
	private void updateExplosions(float dt) {
		
		Explosion explosion = null;
		
		for (int i = explosions.size() - 1; i >= 0; i--) {
			
			explosion = explosions.get(i);
			explosion.update(dt);
			if (explosion.mustDie())
				explosions.remove(i);
		}
	}
	
	/**
	 * Pinta en pantalla todos los elementos del juego
	 */
	public void draw() {
		
		background.draw(0, 0);
		
		ship.draw();
		
		for (Enemy enemy : enemies) {
			enemy.draw();
		}
		
		/*for (Bullet bullet : enemyBullets)
			bullet.draw();*/
		
		for (Bullet bullet : ship.getBullets()) {
			bullet.draw();
		}
		
		for (Explosion explosion : explosions) {
			explosion.draw();
		}
		
		for (Powerup  item : powerups) {
			item.draw();
		}
		
		for (Block block : blocks) {
			block.draw();
		}
	}
	
	/**
	 * Elimina todos los enemigos de la pantalla
	 * @throws SlickException
	 */
	public void killAllEnemies() throws SlickException {
		
		Enemy enemy = null;
		
		for (int i = enemies.size() - 1; i >= 0; i--) {
			enemy = enemies.get(i);
			if (!enemy.getClass().getSimpleName().equals("Stone")) {
				Explosion explosion = new Explosion(enemy.getX(), enemy.getY());
				explosions.add(explosion);
				if (ConfigurationManager.SOUND)
					ResourceManager.getSound("explosion").play();
				enemies.remove(i);
				
			}
		}
	}
	
	/**
	 * Comprueba las colisiones entre proyectiles y personaje/enemigos
	 * @param dt
	 * @throws SlickException
	 */
	private void checkCollisions(float dt) throws SlickException {
		
		Bullet bullet = null;
		Enemy enemy = null;
		Powerup powerup = null;
		List<Bullet> bullets = ship.getBullets();
		
		// Comprueba si los proyectiles del personaje han alcanzado a algún enemigo
		for (int i = enemies.size() - 1; i >= 0; i--) {
			enemy = enemies.get(i);
			for (int j = bullets.size() - 1; j >= 0; j--) {
				bullet = bullets.get(j);
				
				if (bullet.getRect().intersects(enemy.getRect())) {
					
					// Si el enemigo no es meteorito ni bala enemiga se explosiona y se elimina
					if ((!enemy.getClass().getSimpleName().equals("Stone")) && (!enemy.getClass().getSimpleName().equals("ShooterBullet"))) {
						
						enemy.hit();
						if (enemy.getLives() == 0) {
							ship.addScore(enemy.getValue());
							
							Explosion explosion = new Explosion(enemy.getX() - 30, enemy.getY());
							explosions.add(explosion);
							enemies.remove(i);
							
							if (ConfigurationManager.SOUND)
								ResourceManager.getSound("explosion").play();
						}
						
					}
					// Si se dispara contra la bala de un enemigo, el proyectil no desaparece
					// Si se dispara un misil, éste tampoco desaparece hasta que no llega al final de la pantalla,
					// aunque haya acertado a algún enemigo
					if ((!enemy.getClass().getSimpleName().equals("ShooterBullet")) && 
							(!bullet.getClass().getSimpleName().equals("Missile")))
						bullets.remove(j);
				}
			}
		}
		
		// Comprueba si la nave colisiona con algún enemigo
		if (ship.getShieldTime() <= 0) {
			for (int i = enemies.size() - 1; i >= 0; i--) {
				enemy = enemies.get(i);
				
				if (enemy.getRect().intersects(ship.getRect())) {
					
					enemy.hit();
					if (enemy.getLives() == 0) {
						ship.addScore(enemy.getValue());
						
						Explosion explosion = new Explosion(enemy.getX(), enemy.getY());
						explosions.add(explosion);
						enemies.remove(i);
						
						if (ConfigurationManager.SOUND)
							ResourceManager.getSound("explosion").play();
					}
					
					if (enemy.getClass().getSimpleName().equals("Stone")) {
						break;
						// TODO Game Over
					}
					
					ship.hit();
					if (ConfigurationManager.SOUND)
						ResourceManager.getSound("buzz").play();
					
					if (ship.getLives() == 0) {
						break;
						// TODO Game Over
					}
				}
			}
		}
		
		// Comprueba si el personaje ha cogido algún item
		for (int i = powerups.size() - 1; i >= 0; i--) {
			powerup = powerups.get(i);
			
			if (powerup.getRect().intersects(ship.getRect())) {
				
				if (ConfigurationManager.SOUND)
					ResourceManager.getSound("item").play();
				
				if (powerup.getClass().getSimpleName().equals("Bomb"))
					ship.setBombs(ship.getBombs() + 1);
				
				if (powerup.getClass().getSimpleName().equals("Shield"))
					ship.setShieldTime(ship.getShieldTime() + Constants.SHIELD_TIME);
				
				powerups.remove(i);
			}
		}
	}
	
}
