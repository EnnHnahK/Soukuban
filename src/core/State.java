package core;

import java.awt.Graphics;

public abstract class State {
	
	public static State currentState = null;
	
	protected MainScreen mScreen;
	
	/*-- Create Class Constructor */
	public State(MainScreen mScreen){
		this.mScreen = mScreen;
	}
	
	public abstract void update();
	
	public abstract void render(Graphics g);
	
	
}
