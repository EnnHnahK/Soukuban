package core;

import java.awt.Graphics;

public class GameState extends State{
	
	private Level level;
	
	/*-- Create Class Constructor */
	public GameState(MainScreen mScreen) {
		super(mScreen);
	}

	/*-- Method Update */
	@Override
	public void update() {
		level.update();
	}

	/*-- Render Graphics */
	@Override
	public void render(Graphics g) {
		level.render(g);
	}
	
	/*-- Method Set Level */
	public void setLevel(Level level){
		this.level = level;
	}

}
