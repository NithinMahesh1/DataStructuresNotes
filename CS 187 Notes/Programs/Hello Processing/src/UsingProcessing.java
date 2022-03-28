import processing.core.PApplet;

public class UsingProcessing extends PApplet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("UsingProcessing");
	}
	
	public void settings() {
		  size(600, 400);
	}
	
	public void setup() {
		  noStroke();
		  background(255);
	}

	public void draw() {
		  if (mousePressed) {
		    fill(136, 28, 28);
		  } else {
		    noFill();
		  }
		  ellipse(mouseX, mouseY, 30, 30);
	}

	public void keyPressed() {
		  if (key == ' ') { // this is a single space character
		  background(255);
		  }
	}
}
