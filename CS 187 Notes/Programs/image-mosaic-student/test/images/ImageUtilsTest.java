package images;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;


import processing.core.PApplet;


public class ImageUtilsTest {
	@Rule
	public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds

	private PApplet pApplet;
	private static final String RESOURCE_DIR = "test" + File.separator + "resources" + File.separator;
	
	@Before
	public void setup() throws InterruptedException {
		pApplet = new PApplet();
		PApplet.runSketch(new String[] {"PApplet"}, pApplet);
		
		// avoid the race condition of an uninitialized pApplet; 
		// no blocking method that I can find so this is the best I can do
		Thread.sleep(200);
	}
		
	@Test
	public void testLoadOne() {
		assertEquals(1, ImageUtils.loadFromDirectory(new File(RESOURCE_DIR).getAbsoluteFile(), pApplet).size());
	}

	@Test
	public void testLoadThree() {
		assertEquals(3, ImageUtils.loadFromDirectory(new File(RESOURCE_DIR + "green").getAbsoluteFile(), pApplet).size());
	}
	
	@Test
	public void testAverageHue() {
		int[] pixels = new int[1024];
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = i;
		}
		assertEquals(168, ImageUtils.averageHue(pixels, pApplet));
	}
}
