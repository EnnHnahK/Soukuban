package test;

import java.awt.Image;

public class Action {
	
	private Image image;
	
	private int x;
	private int y;
	
	public Action (int x, int y) {
	        this.x = x;
	        this.y = y;
	    }
	public int x() {
	        
	        return x;
	    }

	 public int y() {
	        
	        return y;
	    }

	    public void setX(int x) {
	        
	        this.x = x;
	    }

	    public void setY(int y) {
	        
	        this.y = y;
	    }
		
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image img) {
		image = img;
	}
	
}
