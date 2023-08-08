package test;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Board_Test extends JPanel {
	private final int OFFSET = 30;
	private final int SPACE = 64;
	
	private ArrayList<Wall> walls;
	private ArrayList<Block> baggs;
	private ArrayList<Point> areas;
	

	private Player soko;
	private int w = 0;
	private int h = 0;
	
//	private String level 	= "    ######\n"
//            				+ "    ##   #\n"
//				            + "    ##   #\n"
//				            + "  ####   ##\n"
//				            + "  ##      #\n"
//				            + "#### # ## #   ######\n"
//				            + "##   # ## #####    #\n"
//				            + "##    $            #\n"
//				            + "###### ### #@##   .#\n"
//				            + "    ##     #########\n"
//				            + "    ########\n";
//	
	private String level 	= "11111111\n"
							+ "11131111\n"
				            + "11101111\n"
				            + "11120231\n"
				            + "1302@111\n"
				            + "11112111\n"
				            + "11113111\n"
				            + "11111111\n";

	
	public Board_Test() {
		
		initBoard();
	}

	private void initBoard() {
		
		setFocusable(true);
		initWorld();
	}
	
	
	
	private void initWorld() {
		
		walls = new ArrayList<>();
        baggs = new ArrayList<>();
        areas = new ArrayList<>();

        int x = OFFSET;
        int y = OFFSET;

        Wall wall;
        Block b;
        Point a;

        
        for (int i = 0; i < level.length(); i++) {

            char item = level.charAt(i);

            switch (item) {

                case '\n':
                    y += SPACE;

                    if (this.w < x) {
                        this.w = x;
                    }

                    x = OFFSET;
                    break;

                case '1':
                    wall = new Wall(x, y);
                    walls.add(wall);
                    x += SPACE;
                    break;

                case '2':
                    b = new Block(x, y);
                    baggs.add(b);
                    x += SPACE;
                    break;

                case '3':
                    a = new Point(x, y);
                    areas.add(a);
                    x += SPACE;
                    break;

                case '@':
                    soko = new Player(x, y);
                    x += SPACE;
                    break;

                case '0':
                    x += SPACE;
                    break;

                default:
                    break;
            }

            h = y;
        }
    }
		
	private void buildWorld(Graphics g) {
		
		g.setColor(Color.WHITE);
		
		ArrayList<Action> world = new ArrayList<>();
		
		world.addAll(walls);
        world.addAll(areas);
        world.addAll(baggs);
        world.add(soko);
		
		for(int i = 0; i < world.size(); i++) {
			Action item = world.get(i);
			
			if (item instanceof Player || item instanceof Block) {
                
                g.drawImage(item.getImage(), item.x() + 2, item.y() + 2, this);
            } else {
                
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }
//
//            if (isCompleted) {
//                
//                g.setColor(new Color(0, 0, 0));
//                g.drawString("Completed", 25, 20);
//            }

			
		}
	}
	
	 @Override
	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);

	        buildWorld(g);
	    }

	public int getHeght(){
		return h;
	}
	public int getWidth(){
		return w;
	}
}
