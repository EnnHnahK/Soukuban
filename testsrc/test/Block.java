package test;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Block extends Action {
	Image image;
	
	public Block(int x, int y) {
		super(x, y);
		
		initBlock();
	}

	private void initBlock() {
		ImageIcon iimg = new ImageIcon("src/res/blocks/boxOff.png");
		image = iimg.getImage();
		setImage(image);
	}
}
