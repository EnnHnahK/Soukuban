package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class ListLevel extends State{
	
	private final int TITLESIZE = 64;
	private Level[] levels = new Level[6];
	private int numLevel;
	private final int xOffset = (MainScreen.WIDTH - TITLESIZE*5)/2;
	private final int yOffset = (MainScreen.HEIGHT - TITLESIZE*1)/2;
	
	private Button back;
	
	private Button[] levelS = new Button[5];

	/*-- Create Class Constructor */
	public ListLevel(MainScreen mScreen) {
		super(mScreen);
		
		for(int i = 0; i < levels.length; i++)
			levels[i] = loadLevel("/levels/"+i+".txt");
		
		back = new Button("BACK", MainScreen.WIDTH/2, MainScreen.HEIGHT - 100, new Click(){

			@Override
			public void onClick() {
				State.currentState = mScreen.getMenu();
			}
			
		}, LoadingRes.font30);
		
	}


	private Level loadLevel(String path){
		
		String file = loadFileAsString(path);
		String[] numbers = file.split("\\s+");
		int cols = parseInt(numbers[0]);
		int rows = parseInt(numbers[1]);
		int playerCol = parseInt(numbers[2]);
		int playerRow = parseInt(numbers[3]);
		int[][] matrix = new int[rows][cols];
		for(int row = 0; row < rows; row++)
			for(int col = 0; col < cols; col++)
				matrix[row][col] = parseInt(numbers[(col + (row*cols))+ 4]);
		
		return new Level(matrix, playerRow, playerCol, this);
	}
	
	@Override
	public void update() {
		back.update();
	}
	@Override
	public void render(Graphics g){
		g.setFont(LoadingRes.font48);
		Text.drawString(g, "LEVEL LIST", MainScreen.WIDTH/2, MainScreen.HEIGHT/2 - 200, true, Color.DARK_GRAY);
		back.render(g);
		g.setFont(LoadingRes.font30);
		
		numLevel = 0;
		for(int i = 0; i < 1; i++){
			for(int j = 0; j < 5; j++){
				Rectangle rt = new Rectangle(xOffset + j*TITLESIZE, yOffset + i*TITLESIZE, TITLESIZE, TITLESIZE);
				if(rt.contains(MouseInput.x, MouseInput.y)){
					if(MouseInput.left && levels[numLevel].getStatus()){
						((GameState)mScreen.getGameState()).setLevel(levels[numLevel]);
						State.currentState = mScreen.getGameState();
					}
					if(levels[numLevel].getStatus()) {
						Text.drawString(g, numLevel + 1 +"", xOffset + TITLESIZE/2 + j*TITLESIZE, yOffset + TITLESIZE/2 + i*TITLESIZE, true, Color.RED);}
					else
						Text.drawString(g,"?", xOffset + TITLESIZE/2 + j*TITLESIZE,yOffset + TITLESIZE/2 + i*TITLESIZE, true, Color.RED);
				}else{
					if(levels[numLevel].getStatus())
						Text.drawString(g, numLevel + 1 +"", xOffset + TITLESIZE/2 + j*TITLESIZE, yOffset + TITLESIZE/2 + i*TITLESIZE, true, Color.GREEN);
					else
						Text.drawString(g,"?", xOffset + TITLESIZE/2 + j*TITLESIZE,yOffset + TITLESIZE/2 + i*TITLESIZE, true, Color.BLUE);
				}
				numLevel ++;
			}
		}
		
	}
	
	public static String loadFileAsString(String path){
		StringBuilder builder = new StringBuilder();
		try{
			
		InputStream in = ListLevel.class.getResourceAsStream(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
		String line;
		while((line = br.readLine()) != null){
			builder.append(line+ "\n");
		}
		br.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return builder.toString();
	}
	
	public static int parseInt(String numero){
		try{
			return Integer.parseInt(numero);
		}catch(NumberFormatException e){
			e.printStackTrace();
			return 0;
		}
	}
	
	public Level[] getLevels(){
		return levels;
	}
	
	public int getNumLevel() {
		return numLevel;
	}
	

}
