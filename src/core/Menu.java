package core;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Menu extends State{
	
		private ArrayList<Button> buttons = new ArrayList<Button>();
			
		/*-- Create Class Constructor */
		public Menu(MainScreen mScreen){
			super(mScreen);
				buttons.add(new Button("PLAY", MainScreen.WIDTH/2, MainScreen.HEIGHT/2, new Click(){
					
					@Override
					public void onClick() {
						State.currentState = mScreen.getListLevel();
					}}, LoadingRes.font48));
				
				buttons.add(new Button("EXIT", MainScreen.WIDTH/2, MainScreen.HEIGHT/2 + 100, new Click(){
		
					@Override
					public void onClick() {
						System.exit(1);
					}}, LoadingRes.font48));
			}
		
		@Override
		public void update() {
			for(int i = 0; i < buttons.size(); i++)
				buttons.get(i).update();	
		}
	
		@Override
		public void render(Graphics g) {
			g.setFont(LoadingRes.font48);
			Text.drawString(g, "SOUKUBAN", MainScreen.WIDTH/2, MainScreen.HEIGHT/2 - 200, true, Color.DARK_GRAY);
			g.setFont(LoadingRes.font22);
			if(mScreen.getUser() != null)
			Text.drawString(g, "WELCOME TO SOUKUBAN: "  + mScreen.getUser(), 10, MainScreen.HEIGHT/2 + 250, false, Color.LIGHT_GRAY);

			for(int i = 0; i < buttons.size(); i++)
				buttons.get(i).render(g);
			
		}

}
