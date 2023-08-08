/*-- KeyBoard Input Class*/
package core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener{
	
private boolean[] keys;
	
	public static boolean UP, LEFT, RIGHT, DOWN, R, ROLLBACK, ESC, ENTER;
	
	public static boolean LEVEL1, LEVEL2, LEVEL3, LEVEL4, LEVEL5;
	
	/*-- Create Class Constructor */
	public KeyInput(){
		keys 	 = new boolean[256]; /*-- 8 bit */
		UP 	 	 = false;
		DOWN   	 = false;
		RIGHT 	 = false;
		LEFT 	 = false;
		R	   	 = false;
		ROLLBACK = false;
		ESC		 = false;
		ENTER	 = false;
		LEVEL1	 = false;
		LEVEL2	 = false;
		LEVEL3	 = false;
		LEVEL4	 = false;
		LEVEL5	 = false;
	}
	
	/*-- Set Constant Key */
	public void update(){
		UP 	  	 = keys[KeyEvent.VK_UP];
		LEFT  	 = keys[KeyEvent.VK_LEFT];
		RIGHT 	 = keys[KeyEvent.VK_RIGHT];
		DOWN  	 = keys[KeyEvent.VK_DOWN];
		R 	  	 = keys[KeyEvent.VK_R];
		ROLLBACK = keys[KeyEvent.VK_BACK_SPACE];
		ESC		 = keys[KeyEvent.VK_ESCAPE];
		ENTER	 = keys[KeyEvent.VK_ENTER];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		/*-- Key Pressed Set True */
		keys[e.getKeyCode()] = true;
	}
	
	
	@Override
	public void keyReleased(KeyEvent e) {
		/*-- Key Released Set False */
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		/*-- NULL */ 
	}

}
