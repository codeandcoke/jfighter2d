package org.sfsoft.jfighter2d.managers;

import static org.sfsoft.jfighter2d.util.Constants.ENEMY_RATE;
import static org.sfsoft.jfighter2d.util.Constants.POWERUP_TIME;
import static org.sfsoft.jfighter2d.util.Constants.HEIGHT;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.SlickException;
import org.sfsoft.jfighter2d.characters.Enemy;
import org.sfsoft.jfighter2d.characters.PursuerEnemy;
import org.sfsoft.jfighter2d.characters.ShooterBullet.BulletDirection;
import org.sfsoft.jfighter2d.characters.ShooterEnemy;
import org.sfsoft.jfighter2d.characters.Enemy.EnemyType;
import org.sfsoft.jfighter2d.powerups.Powerup;
import org.sfsoft.jfighter2d.powerups.Powerup.PowerupType;

public class LevelManager {

	private enum SquadronAlignment {
		DIAGONAL_1, DIAGONAL_2, CROSS, DIAMOND, ONE, TWO_H, TWO_V, SCRUM_1, SCRUM_2
	}
	
	private SpriteManager spriteManager;
	private float enemyTime;
	private float powerupTime;
	private float globalTime;
	private Random generator;
	
	public LevelManager(SpriteManager spriteManager) {
		
		this.spriteManager = spriteManager;
		this.enemyTime = 0;
		this.powerupTime = 0;
		this.globalTime = 0;
		this.generator = new Random();
	}
	
	/**
	 * Obtiene el tiempo total de juego
	 * @return
	 */
	public float getGlobalTime() {
		return globalTime;
	}
	
	public void generateLevel(int level, float dt) throws SlickException {
		
		int i = 0;
		List<Enemy> enemies = spriteManager.getEnemies();
		List<Powerup> powerups = spriteManager.getPowerups();
		
		enemyTime += dt;
		powerupTime += dt;
		globalTime += dt;
		
		if (globalTime == 4000) {
			
			enemies.addAll(generateSquadron(1000f, 100f, SquadronAlignment.DIAMOND, EnemyType.SMALL_ENEMY));
			enemies.addAll(generateSquadron(1000f, 500, SquadronAlignment.DIAMOND, EnemyType.SMALL_ENEMY));
			
			enemies.addAll(generateSquadron(1200f, 200, SquadronAlignment.TWO_V, EnemyType.BIG_ENEMY));
		}
		else if (globalTime == 7000) {
				
			enemies.addAll(generateSquadron(1000f, 200f, SquadronAlignment.TWO_H, EnemyType.SHOOTER_ENEMY));
			enemies.addAll(generateSquadron(1000f, 400f, SquadronAlignment.TWO_V, EnemyType.SHOOTER_ENEMY));
			
			enemies.addAll(generateSquadron(900f, 400f, SquadronAlignment.ONE, EnemyType.STATIC_SHOOTER_ENEMY));
			enemies.addAll(generateSquadron(950f, 400f, SquadronAlignment.ONE, EnemyType.STATIC_SHOOTER_ENEMY));
			
			//Block block = new Block(1000, HEIGHT, 2, 3);
			//block.setSpeed(-0.1f);
			//blocks.add(block);
		}
		else if (globalTime == 1400) {
			
			
		}
			
		// Genera un powerup de forma aleatoria
		if (powerupTime >= POWERUP_TIME) {
			
			i = generator.nextInt(PowerupType.values().length);
			
			Powerup powerup = PowerupSpawner.createPowerup(PowerupType.values()[i]);
			powerups.add(powerup);
			powerupTime = 0;
		}	
	}
	
	/**
	 * Genera un escuadrón de enemigos
	 * @param x Coordenada x del enemigo situado más a la izquierda del escuadrón
	 * @param y Coordenada y del enemigo situado más a la izquierda del escuadrón
	 * @param alignment Alineación del escuadrón
	 * @param enemyType Tipo de unidad enemiga
	 * @return El escuadrón formado
	 * @throws SlickException
	 */
	private List<Enemy> generateSquadron(float x, float y, SquadronAlignment alignment, EnemyType enemyType) throws SlickException {
		
		List<Enemy> squadron = new ArrayList<Enemy>();
		
		switch (alignment) {
			case DIAGONAL_1:
				for (int i = 0; i < 4; i++) {
					squadron.add(EnemySpawner.createEnemy(enemyType, x + i * 100, y + i * 30, spriteManager));
					
				}
				break;
			case DIAGONAL_2:
				for (int i = 0; i < 4; i++) {
					squadron.add(EnemySpawner.createEnemy(enemyType, x + i * 100, y - i * 30, spriteManager));
				}
				break;
			case CROSS:
				
				squadron.add(EnemySpawner.createEnemy(enemyType, x + 50, y - 50, spriteManager));
				for (int i = 0; i < 3; i++) {
					squadron.add(EnemySpawner.createEnemy(enemyType, x + i * 50, y, spriteManager));
				}
				squadron.add(EnemySpawner.createEnemy(enemyType, x + 50, y + 50, spriteManager));
				break;
			case DIAMOND:
				squadron.add(EnemySpawner.createEnemy(enemyType, x - 40, y, spriteManager));
				squadron.add(EnemySpawner.createEnemy(enemyType, x + 40, y, spriteManager));
				squadron.add(EnemySpawner.createEnemy(enemyType, x, y + 40, spriteManager));
				squadron.add(EnemySpawner.createEnemy(enemyType, x, y - 40, spriteManager));
				break;
			case ONE:
				squadron.add(EnemySpawner.createEnemy(enemyType, x, y, spriteManager));
				break;
			case TWO_H:
				squadron.add(EnemySpawner.createEnemy(enemyType, x, y, spriteManager));
				squadron.add(EnemySpawner.createEnemy(enemyType, x + 40, y, spriteManager));
				break;
			case TWO_V:
				squadron.add(EnemySpawner.createEnemy(enemyType, x, y, spriteManager));
				squadron.add(EnemySpawner.createEnemy(enemyType, x, y + 40, spriteManager));
				break;
			case SCRUM_1:
				int count = generator.nextInt(7) + 2;
				int padding = 0;
				
				for (int i = 0; i < count / 3; i++) {
					for (int j = 0; j < count / 2; j++) {
						padding = generator.nextInt(15) + 30;
						
						squadron.add(EnemySpawner.createEnemy(enemyType, x + j * padding, y - i * padding, spriteManager));
						squadron.add(EnemySpawner.createEnemy(enemyType, x - j * padding, y + i * padding, spriteManager));
					}
				}
				break;
			case SCRUM_2:
				count = generator.nextInt(7) + 2;
				padding = 0;
				
				for (int i = 0; i < count / 2; i++) {
					for (int j = 0; j < count / 2; j++) {
						padding = generator.nextInt(15) + 30;
						
						squadron.add(EnemySpawner.createEnemy(enemyType, x + j * padding, y - i * padding, spriteManager));
						squadron.add(EnemySpawner.createEnemy(enemyType, x - j * padding, y + i * padding, spriteManager));
					}
				}
				break;
			default:
				break;
		}

		return squadron;
	}
	
	/**
	 * Genera el nivel de forma totalmente aleatoria
	 * @throws SlickException
	 */
	public void generateRandomLevel(float dt) throws SlickException {
		
		Enemy enemy = null;
		
		enemyTime += dt;
		powerupTime += dt;
		
		if (enemyTime > ENEMY_RATE) {
			
			// Genera los enemigos aleatoriamente
			int i = generator.nextInt(EnemyType.values().length);
			
			enemy = EnemySpawner.createEnemy(EnemyType.values()[i], spriteManager);
			
			spriteManager.getEnemies().add(enemy);
			enemyTime = 0;
		}
		
		if (powerupTime >= POWERUP_TIME) {
			
			int i = generator.nextInt(PowerupType.values().length);
			
			Powerup powerup = PowerupSpawner.createPowerup(PowerupType.values()[i]);
			spriteManager.getPowerups().add(powerup);
			powerupTime = 0;
		}	
	}
}
