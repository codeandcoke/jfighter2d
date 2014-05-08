package org.sfsoft.jfighter2d.util;

import org.newdawn.slick.Image;

/**
 * Clase con métodos de utilidad para el desarrollo del juego
 * @author Santiago Faci
 *
 */
public class Util {

	/**
	 * Scale an image
	 * @param image Image that will be scaled
	 * @param height
	 * @param width
	 * @return
	 */
	public static Image scaleImage(Image image, int height, int width) {
		
		image.setFilter(Image.FILTER_NEAREST);
		return image.getScaledCopy(height, width);
	}
}
