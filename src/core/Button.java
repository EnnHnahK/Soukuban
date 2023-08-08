/*-- Button Class*/
package core;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Button {
		private String text;
		private int x, y;
		private FontMetrics fm;
		private Rectangle rt;
		private boolean hovering;
		private Click click;
		private Font font;
		
		/*-- Create Class Constructor */
		public Button(String text, int x, int y, Click click, Font font){
			this.text = text;
			this.x = x;
			this.y = y;
			this.click = click;
			hovering = false;
			this.font = font;
		}
		
		/*-- Method Update */
		public void update(){		
			/*-- Hover Event */
			if(rt != null && rt.contains(MouseInput.x, MouseInput.y)){
				hovering = true;
				/*-- Left Click Event */
				if(MouseInput.left)
					click.onClick();
			}else
				hovering = false;
		}
		
		/*-- Render Graphics */
		public void render(Graphics g){
			/*-- Set Font */
			g.setFont(font);	
			fm = g.getFontMetrics();
			if(hovering)
				Text.drawString(g, text, x, y, true, Color.RED);
			else
				Text.drawString(g, text, x, y, true, Color.BLUE);
			/*--Area Button*/
			rt = new Rectangle(x - fm.stringWidth(text)/2, y - fm.getHeight()/2, fm.stringWidth(text), fm.getHeight());
		}
}
