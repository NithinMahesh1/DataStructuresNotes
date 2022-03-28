package mosaic;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;


import images.ImageUtils;
import processing.core.PApplet;
import processing.core.PImage;

public class TileFactoryTest {
	@Rule
	public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds

	private PApplet pApplet;
	private TileFactory redOnlyTileFactory;
	private TileFactory threeColorTileFactory;
	private int hueRed;
	private int hueGreen;
	private int hueBlue;
	
	private static final int RGB_RED = 16711680;
	private static final int RGB_GREEN = 65280;
	private static final int RGB_BLUE = 255;
	private static final String RESOURCE_DIR = "test" + File.separator + "resources" + File.separator;

	@Before
	public void setup() throws InterruptedException {
		pApplet = new PApplet();
		PApplet.runSketch(new String[] {"PApplet"}, pApplet);
		pApplet.colorMode(PApplet.RGB);
		
		// avoid the race condition of an uninitialized pApplet; 
		// no blocking method that I can find so this is the best I can do
		Thread.sleep(200);
		
		hueRed = Math.round(pApplet.hue(RGB_RED));
		hueGreen = Math.round(pApplet.hue(RGB_GREEN));
		hueBlue = Math.round(pApplet.hue(RGB_BLUE));
		
		redOnlyTileFactory = new TileFactory(pApplet, new int[] {RGB_RED}, 10, 10);
		
		int[] palette = {RGB_RED, RGB_GREEN, RGB_BLUE};
		threeColorTileFactory = new TileFactory(pApplet, palette, 10, 10);
	}

	@Test
	public void testSimpleHueDistance() {
		assertEquals(10, TileFactory.hueDistance(10, 20));
	}

	@Test
	public void testHueDistanceWithZeroPositive() {
		assertEquals(10, TileFactory.hueDistance(0, 10));
	}

	@Test
	public void testHueDistanceWithZeroNegative() {
		assertEquals(10, TileFactory.hueDistance(0, 246));
	}

	@Test
	public void testHueDistanceAcrossZero() {
		assertEquals(20, TileFactory.hueDistance(10, 246));
	}

	@Test
	public void testHueDistanceHalfway() {
		assertEquals(128, TileFactory.hueDistance(0, 128));
	}

	@Test
	public void testHueDistanceHalfway2() {
		assertEquals(128, TileFactory.hueDistance(192, 64));
	}

	@Test
	public void testClosestHueExact() {
		assertEquals(0, (int)redOnlyTileFactory.closestHue(pApplet.color(255, 0, 0)));
	}
	
	@Test
	public void testClosestHueInexact() {
		assertEquals(0, (int)redOnlyTileFactory.closestHue(pApplet.color(200, 20, 20)));
	}
	
	@Test
	public void testClosestHue3Exact1() {
		assertEquals(hueRed, (int)threeColorTileFactory.closestHue(hueRed));
	}

	@Test
	public void testClosestHue3Exact2() {
		assertEquals(hueGreen, (int)threeColorTileFactory.closestHue(hueGreen));
	}
	
	@Test
	public void testClosestHue3Exact3() {
		assertEquals(hueBlue, (int)threeColorTileFactory.closestHue(hueBlue));
	}

	@Test
	public void testClosestHue3Inexact() {
		assertEquals(hueRed, (int)threeColorTileFactory.closestHue(240));
	}

	@Test
	public void testClosestHue3Inexact2() {
		assertEquals(hueGreen, (int)threeColorTileFactory.closestHue(43));
	}

	@Test
	public void testClosestHue3Inexact3() {
		assertEquals(hueBlue, (int)threeColorTileFactory.closestHue(180));
	}

	@Test
	public void testGetOneTile() {
		List<PImage> redImages = ImageUtils.loadFromDirectory(new File(RESOURCE_DIR + "red").getAbsoluteFile(), pApplet); 
		redOnlyTileFactory.addImage(redImages.get(0));
		assertEquals(redImages.get(0), redOnlyTileFactory.getTile(hueRed));
	}

	@Test
	public void testGetMultipleSingleTiles() {
		List<PImage> redImages = ImageUtils.loadFromDirectory(new File(RESOURCE_DIR + "red").getAbsoluteFile(), pApplet); 
		redOnlyTileFactory.addImage(redImages.get(0));
		assertEquals(redImages.get(0), redOnlyTileFactory.getTile(hueRed));
		assertEquals(redImages.get(0), redOnlyTileFactory.getTile(hueRed));
		assertEquals(redImages.get(0), redOnlyTileFactory.getTile(hueRed));
	}

	@Test
	public void testGetMultipleTiles() {
		List<PImage> redImages = ImageUtils.loadFromDirectory(new File(RESOURCE_DIR + "red").getAbsoluteFile(), pApplet);
		for (PImage tile: redImages) {
			redOnlyTileFactory.addImage(tile);
		}
		assertEquals(redImages.get(0), redOnlyTileFactory.getTile(hueRed));
		assertEquals(redImages.get(1), redOnlyTileFactory.getTile(hueRed));
		assertEquals(redImages.get(2), redOnlyTileFactory.getTile(hueRed));
	}

	@Test
	public void testGetMultipleTilesWrap() {
		List<PImage> redImages = ImageUtils.loadFromDirectory(new File(RESOURCE_DIR + "red").getAbsoluteFile(), pApplet);
		for (PImage tile: redImages) {
			redOnlyTileFactory.addImage(tile);
		}
		assertEquals(redImages.get(0), redOnlyTileFactory.getTile(hueRed));
		assertEquals(redImages.get(1), redOnlyTileFactory.getTile(hueRed));
		assertEquals(redImages.get(2), redOnlyTileFactory.getTile(hueRed));
		assertEquals(redImages.get(0), redOnlyTileFactory.getTile(hueRed));
		assertEquals(redImages.get(1), redOnlyTileFactory.getTile(hueRed));
		assertEquals(redImages.get(2), redOnlyTileFactory.getTile(hueRed));
	}

	@Test
	public void testGetMultipleTilesMultipleColors() {
		List<PImage> redImages = ImageUtils.loadFromDirectory(new File(RESOURCE_DIR + "red").getAbsoluteFile(), pApplet);
		List<PImage> greenImages = ImageUtils.loadFromDirectory(new File(RESOURCE_DIR + "green").getAbsoluteFile(), pApplet);
		List<PImage> blueImages = ImageUtils.loadFromDirectory(new File(RESOURCE_DIR + "blue").getAbsoluteFile(), pApplet);
		for (PImage tile: redImages) {
			threeColorTileFactory.addImage(tile);
		}
		for (PImage tile: greenImages) {
			threeColorTileFactory.addImage(tile);
		}
		for (PImage tile: blueImages) {
			threeColorTileFactory.addImage(tile);
		}
		assertEquals(redImages.get(0), threeColorTileFactory.getTile(hueRed));
		assertEquals(redImages.get(1), threeColorTileFactory.getTile(hueRed));
		assertEquals(redImages.get(2), threeColorTileFactory.getTile(hueRed));
		assertEquals(greenImages.get(0), threeColorTileFactory.getTile(hueGreen));
		assertEquals(greenImages.get(1), threeColorTileFactory.getTile(hueGreen));
		assertEquals(greenImages.get(2), threeColorTileFactory.getTile(hueGreen));
		assertEquals(blueImages.get(0), threeColorTileFactory.getTile(hueBlue));
		assertEquals(blueImages.get(1), threeColorTileFactory.getTile(hueBlue));
		assertEquals(blueImages.get(2), threeColorTileFactory.getTile(hueBlue));
	}

	@Test
	public void testGetMultipleTilesMultipleColorsWrap() {
		List<PImage> redImages = ImageUtils.loadFromDirectory(new File(RESOURCE_DIR + "red").getAbsoluteFile(), pApplet);
		List<PImage> greenImages = ImageUtils.loadFromDirectory(new File(RESOURCE_DIR + "green").getAbsoluteFile(), pApplet);
		List<PImage> blueImages = ImageUtils.loadFromDirectory(new File(RESOURCE_DIR + "blue").getAbsoluteFile(), pApplet);
		for (PImage tile: redImages) {
			threeColorTileFactory.addImage(tile);
		}
		for (PImage tile: greenImages) {
			threeColorTileFactory.addImage(tile);
		}
		for (PImage tile: blueImages) {
			threeColorTileFactory.addImage(tile);
		}
		assertEquals(redImages.get(0), threeColorTileFactory.getTile(hueRed));
		assertEquals(redImages.get(1), threeColorTileFactory.getTile(hueRed));
		assertEquals(redImages.get(2), threeColorTileFactory.getTile(hueRed));
		assertEquals(greenImages.get(0), threeColorTileFactory.getTile(hueGreen));
		assertEquals(greenImages.get(1), threeColorTileFactory.getTile(hueGreen));
		assertEquals(greenImages.get(2), threeColorTileFactory.getTile(hueGreen));
		assertEquals(blueImages.get(0), threeColorTileFactory.getTile(hueBlue));
		assertEquals(blueImages.get(1), threeColorTileFactory.getTile(hueBlue));
		assertEquals(blueImages.get(2), threeColorTileFactory.getTile(hueBlue));
		assertEquals(redImages.get(0), threeColorTileFactory.getTile(hueRed));
		assertEquals(redImages.get(1), threeColorTileFactory.getTile(hueRed));
		assertEquals(redImages.get(2), threeColorTileFactory.getTile(hueRed));
		assertEquals(greenImages.get(0), threeColorTileFactory.getTile(hueGreen));
		assertEquals(greenImages.get(1), threeColorTileFactory.getTile(hueGreen));
		assertEquals(greenImages.get(2), threeColorTileFactory.getTile(hueGreen));
		assertEquals(blueImages.get(0), threeColorTileFactory.getTile(hueBlue));
		assertEquals(blueImages.get(1), threeColorTileFactory.getTile(hueBlue));
		assertEquals(blueImages.get(2), threeColorTileFactory.getTile(hueBlue));
	}
}
