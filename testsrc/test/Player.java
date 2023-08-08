package test;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Player extends Action{
	
	Image image;
	
	public Player(int x, int y) {
		super(x, y);
		
		initPlayer();
	}

	private void initPlayer() {
		
		ImageIcon iimg = new ImageIcon("src/res/player/playerFace.png");
		image = iimg.getImage();
		setImage(image);
	}
	
}
