package test;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Point extends Action {
	Image image;
	
	public Point(int x, int y) {
		super(x, y);
		
		initPoint();
	}

	private void initPoint() {
		ImageIcon iimg = new ImageIcon("src/res/blocks/spot.png");
		image = iimg.getImage();
		setImage(image);
	}
	
	
}
