package org.sfsoft.jfighter2d.managers;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.BigImage;
import org.newdawn.slick.Image;
import org.newdawn.slick.Renderable;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

/**
 * Gestiona todos los recursos del juego (imágenes, animaciones, sonidos, . . .)
 * En el caso de este juego, al comienzo del desarrollo, reducía en más de un 100% el uso de CPU con respecto a cargar los recursos
 * cada vez que se necesitaban
 * @author Santiago Faci
 * @version 1.0
 *
 */
public class ResourceManager {

	private static Map<String,Renderable> renderables = new HashMap<String, Renderable>();
	private static Map<String, Sound> sounds = new HashMap<String, Sound>();
	
	public static void loadAllResources() throws SlickException {
		
		// Imágenes
		ResourceManager.loadResource("background", new Image("res/backgrounds/farback.png"));
		ResourceManager.loadResource("ShipBullet", new Image("res/ship/bullet.png"));
		ResourceManager.loadResource("ShooterBullet", new Image("res/enemy/bullet.png"));
		ResourceManager.loadResource("Bomb", new Image("res/items/bomb.png"));
		ResourceManager.loadResource("Shield", new Image("res/items/shield.png"));
		
		// Onscreen information
		ResourceManager.loadResource("live", new Image("res/ship/live.png"));
		ResourceManager.loadResource("bomb_score", new Image("res/items/bomb_score.png"));
		ResourceManager.loadResource("missile_score", new Image("res/items/missile_score.png"));
		
		// Animaciones
		ResourceManager.loadResource("Ship", new Animation(new Image[]{
				new Image("res/ship/f1.png"), new Image("res/ship/f2.png"), new Image("res/ship/f3.png"), new Image("res/ship/f4.png")}, 100));
		ResourceManager.loadResource("SmallEnemy", new Animation(new Image[]{
				new Image("res/enemy/e_f1.png"), new Image("res/enemy/e_f2.png"), new Image("res/enemy/e_f3.png"), new Image("res/enemy/e_f4.png"),
				new Image("res/enemy/e_f5.png"), new Image("res/enemy/e_f6.png")}, 100));
		ResourceManager.loadResource("Stone", new Animation(new Image[]{
				new Image("res/enemy/stone1.png"), new Image("res/enemy/stone2.png"), new Image("res/enemy/stone3.png"), new Image("res/enemy/stone4.png"),
				new Image("res/enemy/stone5.png"), new Image("res/enemy/stone6.png"), new Image("res/enemy/stone7.png"), new Image("res/enemy/stone8.png"),
				new Image("res/enemy/stone9.png"), new Image("res/enemy/stone10.png"), new Image("res/enemy/stone11.png"), new Image("res/enemy/stone12.png"),
				new Image("res/enemy/stone13.png"), new Image("res/enemy/stone14.png"), new Image("res/enemy/stone15.png"), new Image("res/enemy/stone16.png")}, 100));
		ResourceManager.loadResource("PursuerEnemy", new Animation(new SpriteSheet(new Image("res/enemy/pursuer.png"), 40, 30), 0, 0, 0, 5, false, 100, true));
		ResourceManager.loadResource("ShooterEnemy", new Animation(new SpriteSheet(new Image("res/enemy/shooter.png"), 40, 30), 0, 0, 0, 5, false, 100, true));
		ResourceManager.loadResource("BigEnemy", new Animation(new SpriteSheet(new Image("res/enemy/big.png"), 80, 60), 0, 0, 0, 5, false, 100, true));
		ResourceManager.loadResource("Explosion", new Animation(new Image[]{
				new Image("res/explosion/explosion0000.png"), new Image("res/explosion/explosion0001.png"), new Image("res/explosion/explosion0002.png"), 
				new Image("res/explosion/explosion0003.png"), new Image("res/explosion/explosion0004.png"), new Image("res/explosion/explosion0005.png"), 
				new Image("res/explosion/explosion0006.png"), new Image("res/explosion/explosion0007.png"), new Image("res/explosion/explosion0008.png"), 
				new Image("res/explosion/explosion0009.png"), new Image("res/explosion/explosion0010.png"), new Image("res/explosion/explosion0011.png"), 
				new Image("res/explosion/explosion0012.png"), new Image("res/explosion/explosion0013.png"), new Image("res/explosion/explosion0014.png"), 
				new Image("res/explosion/explosion0015.png"), new Image("res/explosion/explosion0016.png"), new Image("res/explosion/explosion0017.png"), 
				new Image("res/explosion/explosion0018.png"), new Image("res/explosion/explosion0019.png"), new Image("res/explosion/explosion0020.png"),
				new Image("res/explosion/explosion0021.png"), new Image("res/explosion/explosion0022.png"), new Image("res/explosion/explosion0023.png"),
				new Image("res/explosion/explosion0024.png"), new Image("res/explosion/explosion0025.png"), new Image("res/explosion/explosion0026.png"),
				new Image("res/explosion/explosion0027.png"), new Image("res/explosion/explosion0028.png"), new Image("res/explosion/explosion0029.png"),
				new Image("res/explosion/explosion0030.png")}, 100));
		
		// SpriteSheets
		ResourceManager.loadResource("blocks", new SpriteSheet(new Image("res/items/blocks1.png"), 32, 32, 2, 2));
		ResourceManager.loadResource("Missile", new Animation(new SpriteSheet(new Image("res/items/missile.png"), 92, 16, 0, 0), 0, 0, 0, 9, false, 100, true));
		
		// Sonidos
		ResourceManager.loadResource("disparo", new Sound("res/sounds/disparo.wav"));
		ResourceManager.loadResource("explosion", new Sound("res/sounds/explosion.wav"));
		ResourceManager.loadResource("item", new Sound("res/sounds/item.wav"));
		ResourceManager.loadResource("buzz", new Sound("res/sounds/buzz.wav"));
	}
	
	/**
	 * Carga un recurso de imagen en memoria
	 * @param name
	 * @param resource
	 */
	public static void loadResource(String name, Renderable resource) {
		
		renderables.put(name, resource);
	}
	
	/**
	 * Carga un recurso de sonido en memoria
	 * @param name
	 * @param sound
	 */
	public static void loadResource(String name, Sound sound) {
		
		sounds.put(name, sound);
	}
	
	/**
	 * Obtiene un recurso de imagen de memoria
	 * @param name
	 * @return
	 */
	public static Renderable getResource(String name) {
		
		return renderables.get(name);
	}
	
	public static Image getImage(String name) {
		
		return (Image) renderables.get(name);
	}
	
	public static SpriteSheet getSpriteSheet(String name) {
		
		return (SpriteSheet) renderables.get(name);
	}
	
	public static Animation getAnimation(String name) {
		
		return ((Animation) renderables.get(name)).copy();
	}
	
	public static BigImage getBigImage(String name) {
		
		return (BigImage) renderables.get(name);
	}
	
	/**
	 * Obtiene un recurso de sonido de memoria
	 * @param name
	 * @return
	 */
	public static Sound getSound(String name) {
		
		return sounds.get(name);
	}
}
