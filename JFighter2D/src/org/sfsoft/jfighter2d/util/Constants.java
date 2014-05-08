package org.sfsoft.jfighter2d.util;

public interface Constants {

	// Ancho y alto de la pantalla de juego
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 600;
	
	// Límite de enemigos al mismo tiempo
	public static final int ENEMY_LIMIT = 30;
	// Pausa de tiempo en la generación de enemigos (actualmente un enemigo por segundo)
	public static final int ENEMY_RATE = 400;
	// Velocidad de generación de items bombas
	public static final int POWERUP_TIME = 15000;
	public static final int ITEM_HEIGHT = 30;
	
	// Tamaño de los personajes estándar (excepto la nave del personaje)
	public static final int CHARACTER_HEIGHT = 30;
	public static final int CHARACTER_WIDTH = 40;
	
	// Tamaño de los bloques
	public static final int BLOCK_WIDTH = 32;
	public static final int BLOCK_HEIGHT = 32;
	
	public static final int SHIP_LIVES = 3;
	public static final float SHIELD_TIME = 6000;
}
