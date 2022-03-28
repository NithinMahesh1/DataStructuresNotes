package mosaic;


import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import images.ImageUtils;
import processing.core.PApplet;
import processing.core.PImage;

public class TileFactory {
	public final int tileWidth;
	public final int tileHeight;
	private final PApplet pApplet;
	// TODO: you will probably NOT be keeping this array in your final code; see writeup
	private final Map<Integer, List<PImage>> hues = new HashMap<>();
	private List hueList;
	
	/**
	 * 
	 * @param pApplet a reference to the parent PApplet
	 * @param colors the palette of RGB colors for this TileFactory
	 * @param tileWidth width (in pixels) for each tile
	 * @param tileHeight height (in pixels) for each tile
	 */
	public TileFactory(PApplet pApplet, int[] colors, int tileWidth, int tileHeight) {
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.pApplet = pApplet;
		// TODO: when you replace the int[] hues, be sure to replace this code, as well
		for (int i = 0; i < colors.length; i++) {
			hues.put(Math.round(pApplet.hue(colors[i])), new ArrayList()); //ask about the list for imageUtils
		}
	}
	
	/**
	 * Returns the distance between two hues on the circle [0,256).
	 * 
	 * @param hue1 
	 * @param hue2
	 * @return the distance between two hues.
	 */
	static int hueDistance(int hue1, int hue2) {
		// TODO
		int dist = 0;
		
		if (hue1 > 128 && hue2 > 128) {
			dist = Math.abs(hue1 - hue2);
		}
		if (hue1 < 128 && hue2 <= 128) {
			dist = Math.abs(hue1 - hue2);
		}
		if (hue1 > 128 && hue2 < 128) {
			dist = Math.abs((256 - hue1) + hue2);
		}
		if (hue1 < 128 && hue2 > 128) {
			dist = Math.abs(hue1 + (256 - hue2));
		}
		return dist;
	}
	
	/**
	 * Returns the closest hue from the fixed palette this TileFactory contains.
	 * @param hue
	 * @return the closest hue from the palette
	 */
	Integer closestHue(int hue) {
		// TODO
		int closestHue = 0;
		Set<Integer> hueKey = hues.keySet();
		Iterator<Integer> iter = hueKey.iterator();
		
		int index = 0;
		while (iter.hasNext()) {
			index = iter.next();
			hueDistance(hue, index);
			if (hueDistance(hue, closestHue) > hueDistance(hue, index)) {
				closestHue = index;
			}
		}
		
		return closestHue;
	}
	
	/**
	 * Adds an image to this TileFactory for later use.
	 * @param image the image to add
	 */
	public void addImage(PImage image) {
		image.resize(tileWidth, tileHeight);
		image.loadPixels();
				
		int avgHue = ImageUtils.averageHue(image.pixels, pApplet);
		
		// TODO: add the image to the appropriate place in your map from hues to lists of images

		hues.get(closestHue(avgHue)).add(image);
	}
	
	/**
	 * Returns the next tile from the list associated with the hue most closely matching the input hue
	 * @param hue the color to match
	 * @return a tile matching hue
	 */
	public PImage getTile(int hue) {
		// TODO: don't create a new image; instead return an appropriate one 
		// from your map of hues to lists of images; see writeup for details
		
		List<PImage> rotate = hues.get(closestHue(hue));
		PImage image = rotate.get(0);
		Collections.rotate(rotate, -1);
		return image;
		
	}
}
