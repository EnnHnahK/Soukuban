
package core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class MainScreen extends JFrame implements Runnable{
	
	public static final int WIDTH = 800, HEIGHT = 600;
	private Canvas canvas;
	private Thread thread;
	private boolean running = false;
	
	private BufferStrategy bs;
	private Graphics g; 
	
	private GameState gameState;
	private ListLevel listLevel;
	private Menu menu;
	private LoadingMenu loadingMenu;
	
	private KeyInput key;
	private MouseInput mouse;
	
//	private Database db;
	private String user;
	
	JTextField textfield;
	JFrame frame;
	
	/*-- Create Class Constructor */
	public MainScreen(){
		
		/*-- Set Frame -- */
		setTitle("SOUKUBAN");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);

	
		canvas = new Canvas();
		key = new KeyInput();
		mouse = new MouseInput();

		
		/* Canvas Set */
		canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		canvas.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		canvas.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		canvas.setFocusable(true);
	
		
		/*-- Add Component */ 
		add(canvas);
		addMouseMotionListener(mouse);
		addMouseListener(mouse);
		canvas.addMouseListener(mouse);
		canvas.addMouseMotionListener(mouse);
		canvas.addKeyListener(key);

		setVisible(true);
		userName();
	}
	
	
	/*-- Main */
	public static void main(String[] args) {
		new MainScreen().start();
	}
	
	/*-- Method Update*/
	private void update(){
		if(State.currentState instanceof GameState)
			key.update();
		
		/*-- Update Class Extends State */
		if(State.currentState != null)
			State.currentState.update();
	}

	private void draw(){
		
		bs = canvas.getBufferStrategy();
		
		if(bs == null){
			canvas.createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		for(int i = 0; i < MainScreen.WIDTH/Level.SIZE + 1; i++)
			for(int j = 0; j < MainScreen.HEIGHT/Level.SIZE + 1; j++)
				g.drawImage(LoadingRes.backGround, i*Level.SIZE, j*Level.SIZE, null);
		
		
		
		if(State.currentState != null)
			State.currentState.render(g);
	
		g.dispose();
		bs.show();
	}
	
	
	private void init(){
		
		LoadingRes.init();
		
		menu 		= new Menu(this);
		gameState   = new GameState(this);
		loadingMenu = new LoadingMenu(this);
		listLevel   = new ListLevel(this);
		State.currentState = loadingMenu;
	}
	
	private void userName() {
	
		frame = new JFrame();
		frame.setTitle("Please Enter Username !!!");
		frame.setResizable(false);
		frame.setSize(500, 100);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textfield = new JTextField();
		textfield.setBorder(null);
		frame.add(textfield);
		textfield.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
            	user = textfield.getText();
            	if(e.getKeyCode() == KeyEvent.VK_ENTER && user != ""){
            		frame.dispose();	
            	}
            }});
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
	
	@Override
	public void run(){
		init();
		
		while(running){	
			update();
			draw();
		}
	}
	
	/*-- Start Thread */
	private void start(){
		
		thread = new Thread(this);
		thread.start();
		running = true;
		
		
	}
	
	public State getGameState(){
		return gameState;
	}
	public State getListLevel(){
		return listLevel;
	}
	public State getMenu(){
		return menu;
	}
	public String getUser() {
		return user;
	}
	
}
