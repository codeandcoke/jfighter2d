package org.sfsoft.jfighter2d;

import static org.sfsoft.jfighter2d.util.Constants.HEIGHT;
import static org.sfsoft.jfighter2d.util.Constants.WIDTH;

import java.awt.Font;
import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.sfsoft.jfighter2d.characters.Ship;
import org.sfsoft.jfighter2d.managers.ConfigurationManager;
import org.sfsoft.jfighter2d.managers.LevelManager;
import org.sfsoft.jfighter2d.managers.ResourceManager;
import org.sfsoft.jfighter2d.managers.SpriteManager;
import org.sfsoft.jfighter2d.menu.MenuGame;

public class JFighter2D extends BasicGame {
	
	//  Estados del juego
	public enum GameState {
		MENU, PLAY, CREDITS, QUIT
	}
	
	private int level;
	private Input input;
	public static GameState gameState;
	private MenuGame menuGame;
	private SpriteManager spriteManager;
	private LevelManager levelManager;
	private ConfigurationManager configurationManager;

	public JFighter2D() {
		super("JFighter2D");
		
		level = 1;
	}

	@Override
	public void init(GameContainer gc) throws SlickException{
		
		gc.setShowFPS(false);
		input = gc.getInput();
		
		// Carga de todos los recursos del juego (gráficos, sonidos, . . .)
		ResourceManager.loadAllResources();		
		// Inicializa los elementos del juego
		spriteManager = new SpriteManager();
		// Inicializa la configuración del juego
		configurationManager = new ConfigurationManager();
		// Inicializa el creador de niveles
		levelManager = new LevelManager(spriteManager);
		
		// Inicializa la ventana del menú y fija el estado inicial del juego
		menuGame = new MenuGame();
		gameState = GameState.MENU;
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		
		// Pinta en pantalla todos los elementos del juego
		spriteManager.draw();
		
		drawOnscreenText(g);
		
		if (gameState == GameState.CREDITS) {
			g.drawString("Game Over", WIDTH / 2, HEIGHT / 2);
		}
	}
	
	/**
	 * Muestra la información de juego del personaje
	 * @param g
	 */
	private void drawOnscreenText(Graphics g) {
		
		Ship ship = spriteManager.getShip();
		
		// Muestra la puntuación y nivel del jugador
		g.drawString("PUNTOS: " + ship.getScore(), 15, 10);
		g.drawString("NIVEL: " + level, 130, 10);

		g.drawImage(ResourceManager.getImage("live"), 15, 35);
		g.drawString("X " + spriteManager.getShip().getLives(), 50, 30);
		
		g.drawImage(ResourceManager.getImage("bomb_score"), 15, 52);
		g.drawString("X " + spriteManager.getShip().getBombs(), 50, 50);
		
		g.drawImage(ResourceManager.getImage("missile_score"), 15, 75);
		g.drawString("X " + spriteManager.getShip().getMissiles(), 50, 70);

		/*for (int i = 1; i <= ship.getLives(); i++)
		g.drawImage(ResourceManager.getImage("live"), 75 + 15 * i, 35);
		
		// Muestra las bombas de la nave
		g.drawString("BOMBAS ", 15, 50);
		for (int i = 1; i <= ship.getBombs(); i++) 
			g.drawImage(ResourceManager.getImage("bomb_score"), 75 + 15 * i, 50);
		
		// Muestra los misiles disponibles
		g.drawString("MISILES ", 15, 70);
		for (int i = 1; i <= ship.getMissiles(); i++)
			g.drawImage(ResourceManager.getImage("missile_score"), 75 + 15 * i, 70);
		*/
		// Muestra la protección actual del escudo protector de la nave (si está activo)
		if (ship.getShieldTime() > 2000) {
			g.drawOval(ship.getX(), ship.getY(), 65, 30);
			g.drawOval(ship.getX() - 5, ship.getY() - 5, 75, 40);
			g.drawOval(ship.getX() - 10, ship.getY() - 10, 85, 50);
		}
		else if ((ship.getShieldTime() > 1000) && (ship.getShieldTime() <= 2000)) { 
			g.drawOval(ship.getX(), ship.getY(), 65, 30);
			g.drawOval(ship.getX() - 5, ship.getY() - 5, 75, 40);
		}
		else if ((ship.getShieldTime() > 0) && (ship.getShieldTime() <= 1000))
			g.drawOval(ship.getX(), ship.getY(), 65, 30);
	}

	@Override
	public void update(GameContainer gc, int dt) throws SlickException {
		
		if (gameState == GameState.MENU) {
			
			if (!menuGame.isVisible()) {
				menuGame.setVisible(true);
			}
		}
		else if (gameState == GameState.PLAY) {
			
			levelManager.generateLevel(level, dt);
			spriteManager.update(dt);
			handleKeyboard(dt);
			
			if (spriteManager.getShip().getLives() == 0) {
				gameState = GameState.CREDITS;
			}
		}
		else if (gameState == GameState.CREDITS) {
			
		}
		else if (gameState == GameState.QUIT) {
			System.exit(0);
		}
	}
	
	/**
	 * Gestiona la entrada por teclado
	 * @param dt
	 * @throws SlickException
	 */
	private void handleKeyboard(float dt) throws SlickException {
		
		spriteManager.getShip().update(dt, input, spriteManager);
		
		// Si se pulsa la tecla ESCAPE se muestra el menú de juego
		if (input.isKeyPressed(Input.KEY_LSHIFT)) {

			gameState = GameState.MENU;
		}
	}

	public static void main(String args[]) throws SlickException {
		
		AppGameContainer game = new AppGameContainer(new JFighter2D());
		game.setDisplayMode(WIDTH, HEIGHT, false);
		game.start();
	}
}
