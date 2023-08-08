package test;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Wall extends Action{
	
	private Image image;
	
	public Wall(int x, int y) {
		super(x, y);
		
		initWall();
	}

	private void initWall() {
		
		ImageIcon iimg = new ImageIcon("src/res/blocks/redBrick.png");
		image = iimg.getImage();
		setImage(image);
	}
}
