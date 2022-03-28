package mosaic;

import images.ImageUtils;
import processing.core.PApplet;
import processing.core.PImage;

public class Mosaic {
	private final PApplet pApplet;
	private final PImage image;
	private final TileFactory tileFactory;

	public Mosaic(PApplet pApplet, PImage image, TileFactory tileFactory) {
		this.pApplet = pApplet;
		this.image = image;
		this.tileFactory = tileFactory;
	}
			
	/**
	 * Constructs and returns the mosaic.
	 * 
	 * The mosaic always consists of an integer number of tiles; if the target
	 * image is not exactly as wide or as high as a multiple of the tile width/height,
	 * the mosaic is truncated to be exactly a multiple of the tile width/height.
	 * 
	 * @return the mosaic
	 */
	public PImage buildMosaic() {
		image.loadPixels();
		return image;
	}
}
