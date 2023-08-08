package core;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JTextField;


public class LoadingMenu extends State{
	
	JTextField textfield;
	private String text = "";
	
	/*-- Create Class Constructor */
	public LoadingMenu(MainScreen mScreen) {
		super(mScreen);
		

	}

	@Override
	public void update() {
		State.currentState = mScreen.getMenu();
	}

	@Override
	public void render(Graphics g) {
	
		g.setFont(LoadingRes.font22);
		Text.drawString(g, text, MainScreen.WIDTH/2, MainScreen.HEIGHT/2, true, Color.WHITE);
	}

}
