/* -- Load Resource From File */
package core;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LoadingRes {
	
	/* -- Image Variable */
	public static Image PlayerFront, playerBack, playerLeft, playerRight;
	
	public static Image wall, boxCorr, boxUncorr, point, backGround;
	
	/* -- Font Variable */
	public static Font font22; 
	public static Font font30;
	public static Font font48;
	
	public static void init(){
		
		/* -- Load Image From File to Variable */
		playerLeft  = loadImage("/player/left.png").getScaledInstance(Level.SIZE, Level.SIZE, BufferedImage.SCALE_DEFAULT);
		playerBack  = loadImage("/player/back.png").getScaledInstance(Level.SIZE, Level.SIZE, BufferedImage.SCALE_DEFAULT);
		PlayerFront = loadImage("/player/front.png").getScaledInstance(Level.SIZE, Level.SIZE, BufferedImage.SCALE_DEFAULT);
		playerRight = loadImage("/player/right.png").getScaledInstance(Level.SIZE, Level.SIZE, BufferedImage.SCALE_DEFAULT);
		
		wall 		= loadImage("/blocks/redBrick.png").getScaledInstance(Level.SIZE, Level.SIZE, BufferedImage.SCALE_DEFAULT);
		boxCorr  	= loadImage("/blocks/boxOn.png").getScaledInstance(Level.SIZE, Level.SIZE, BufferedImage.SCALE_DEFAULT);
		boxUncorr   = loadImage("/blocks/boxOff.png").getScaledInstance(Level.SIZE, Level.SIZE, BufferedImage.SCALE_DEFAULT);
		point 		= loadImage("/blocks/spot.png").getScaledInstance(Level.SIZE, Level.SIZE, BufferedImage.SCALE_DEFAULT);
		backGround  = loadImage("/blocks/4.jpg").getScaledInstance(Level.SIZE, Level.SIZE, BufferedImage.SCALE_DEFAULT);

		/* -- Load Font From File to Variable */
		font22 		= loadFont("src/fonts/Broken.ttf", 22);
		font30 		= loadFont("src/fonts/Broken.ttf", 30);
		font48 		= loadFont("src/fonts/Broken.ttf", 48);
		
	}
	
	/* -- Method LoadImage */
	public static BufferedImage loadImage(String path){
		try {
			return ImageIO.read(LoadingRes.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/* -- Method LoadFont */
	public static Font loadFont(String path, int size){
		try {
			return Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(Font.PLAIN, size);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
