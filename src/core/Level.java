package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Arrays;

public class Level {
	
	public static final int SIZE = 48;
	
	private int[][] matrix;
	
	private int[][] copy;
	
	private int playerRow, playerCol;
	
	private Image img;
	
	private int xOffset, yOffset;
	
	private long time, lastTime;
	
	private final int DELAY = 150;
	
	private Button restart, back;
	
	private boolean confirm;
	
	private int playerStartRow, playerStartCol;
	
	private ListLevel listLevel;
	
	public static int ID = 0;
	private int id;
	private int turn;
	private long seconds, minutes, miliSec;
//	private long start, end;
	
	
	private int[] tempkey = new int[4];
		
	/*-- Create Class Constructor */
	public Level(int[][] matrix, int playerRow, int playerCol, ListLevel listLevel){

		this.listLevel = listLevel;
		this.matrix = matrix;
		ID ++;
		id = ID;
		copy = new int[matrix.length][matrix[0].length];
		for(int row = 0; row < matrix.length; row++){
			for(int col = 0; col < matrix[row].length; col ++) {
				copy[row][col] = matrix[row][col];}
		playerStartRow = playerRow;
		playerStartCol = playerCol;
		this.playerRow = playerRow;
		this.playerCol = playerCol;
		if(ID == 1)
			confirm = true;
		else
			confirm = false;
		xOffset = (MainScreen.WIDTH - matrix[0].length*SIZE)/2;
		yOffset = (MainScreen.HEIGHT - matrix.length*SIZE + 50)/2;
		img = LoadingRes.PlayerFront;
		restart = new Button("RESTART", MainScreen.WIDTH - 100, MainScreen.HEIGHT - 150, new Click(){

			@Override
			public void onClick() {
				reset();
				
			}}, LoadingRes.font30);
		back = new Button("BACK", MainScreen.WIDTH - 100, MainScreen.HEIGHT - 100, new Click(){

			@Override
			public void onClick() {
				State.currentState = listLevel;
				
			}}, LoadingRes.font30);
		}
		
	}
	
	private void reset(){
		for(int row = 0; row < matrix.length; row++)
			for(int col = 0; col < matrix[row].length; col ++)
				matrix[row][col] = copy[row][col];
		
		playerRow = playerStartRow;
		playerCol = playerStartCol;
		img = LoadingRes.PlayerFront;
	}
	
	
	public void update(){
		
		time += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();

		if(KeyInput.UP && time > DELAY){
			move(-1, 0);
			img = LoadingRes.playerBack;
			Arrays.fill(tempkey, 0);
			tempkey[0]++;
		}
		if(KeyInput.LEFT && time > DELAY){
			move(0, -1);
			img = LoadingRes.playerLeft;
			Arrays.fill(tempkey, 0);
			tempkey[1]++;
		}
		if(KeyInput.DOWN && time > DELAY){
			move(1, 0);
			img = LoadingRes.PlayerFront;
			Arrays.fill(tempkey, 0);
			tempkey[2]++;
		}
		if(KeyInput.RIGHT && time > DELAY){
			move(0, 1);
			img = LoadingRes.playerRight;
			Arrays.fill(tempkey, 0);
			tempkey[3]++;
		}
		if(KeyInput.R) {
			reset();
		}
		if(KeyInput.ROLLBACK) {
			rollBack();
		}
		
		restart.update();
		back.update();
		
		for(int row = 0; row < matrix.length; row++)
			for(int col = 0; col < matrix[row].length; col ++)
			if(matrix[row][col] == 2)
					return;
		
		listLevel.getLevels()[id].setStatus(true);
		State.currentState = listLevel;
		

	}
	
	
	private void move(int row, int col){
		if(matrix[playerRow + row][playerCol + col] != 1) {
			if(matrix[playerRow + row][playerCol + col] == 2 || matrix[playerRow + row][playerCol + col] == 4) {
				if(matrix[playerRow + row*2][playerCol + col*2] == 1 || matrix[playerRow + row*2][playerCol + col*2] == 2 || matrix[playerRow + row*2][playerCol + col*2] == 4) {
					return;
				}
				if(matrix[playerRow + row][playerCol + col] == 2) {
					if(matrix[playerRow + row*2][playerCol + col*2] == 3) {
						matrix[playerRow + row*2][playerCol + col*2] = 4;
						matrix[playerRow + row][playerCol + col] = 0;
					}
					else {
						matrix[playerRow + row*2][playerCol + col*2] = 2;
						matrix[playerRow + row][playerCol + col] = 0;
					}
				}
				else {
					if(matrix[playerRow + row*2][playerCol + col*2] == 3) {
						matrix[playerRow + row*2][playerCol + col*2] = 4;
						matrix[playerRow + row][playerCol + col] = 3;
					}
					else {
						if(matrix[playerRow + row*2][playerCol + col*2] == 0) {
							matrix[playerRow + row*2][playerCol + col*2] = 2;
							matrix[playerRow + row][playerCol + col] = 3;
						}
					}
				}
			}
			playerRow += row;
			playerCol += col;
			turn++;
		}
		time = 0;
	}
	
	private void rollBack() {
		for(int i = 0; i < 4; i++) {
			if(tempkey[i] != 0) {
				if(tempkey[0] == 1) {
					reMove(-1, 0);
					img = LoadingRes.PlayerFront;
				}
				if(tempkey[1] == 1) {
					reMove(0, -1);
					img = LoadingRes.PlayerFront;
				}
				if(tempkey[2] == 1) {
					reMove(1, 0);
					img = LoadingRes.PlayerFront;
				}
				if(tempkey[3] == 1){
					reMove(0, 1);
					img = LoadingRes.PlayerFront;
				}
			}
			tempkey[i] = 0;
		}
	}
	
	private void reMove(int row, int col) {
		if(matrix[playerRow + row][playerCol + col] == 2 || matrix[playerRow + row][playerCol + col] == 4) {
				if(matrix[playerRow + row][playerCol + col] == 4) {
					if(matrix[playerRow][playerCol] == 3) {
						matrix[playerRow][playerCol] = 4;
					}
					else {
						matrix[playerRow][playerCol] = 2;
					}
					matrix[playerRow + row][playerCol + col] = 3;
				}else {
					if(matrix[playerRow][playerCol] == 3) {
						matrix[playerRow][playerCol] = 4;
					}
					else {
						matrix[playerRow][playerCol] = 2;
					}
					matrix[playerRow + row][playerCol + col] = 0;
				}
		}
		playerRow -= row;
		playerCol -= col;			
	}
	
	public long time() {
		long start = System.currentTimeMillis();
		long  end   = System.currentTimeMillis();
		return end - start;
	}
	
	public void render(Graphics g){
		
		restart.render(g);
		back.render(g);
		
		miliSec = time;
		if(miliSec % 1000 >= 997.5) {
			seconds++;
			if(seconds == 60) {
				minutes++;
				seconds = 0;
			}
		}
		
		Text.drawString(g, "Move " + turn, 100, 100, true, Color.DARK_GRAY);
		Text.drawString(g, "Level " + id, MainScreen.WIDTH/2, 100, true, Color.DARK_GRAY);
		Text.drawString(g, "Time: " + minutes + ":" + seconds , 10, 30, false, Color.DARK_GRAY);
		
		for(int row = 0; row < matrix.length; row++){
			for(int col = 0; col < matrix[row].length; col ++){
//				g.drawImage(LoadingRes.backGroud2, xOffset + col*TILESIZE, yOffset + row*TILESIZE, null);
				if(matrix[row][col] == 1)
					g.drawImage(LoadingRes.wall, xOffset + col*SIZE, yOffset + row*SIZE, null);
				if(matrix[row][col] == 2)
					g.drawImage(LoadingRes.boxUncorr, xOffset + col*SIZE, yOffset + row*SIZE, null);
				if(matrix[row][col] == 3)
					g.drawImage(LoadingRes.point, xOffset + col*SIZE, yOffset + row*SIZE, null);
				if(matrix[row][col] == 4)
					g.drawImage(LoadingRes.boxCorr, xOffset + col*SIZE, yOffset + row*SIZE, null);
			}
		}
		
		g.drawImage(img, xOffset + playerCol*SIZE, yOffset + playerRow*SIZE, null);
		
		
	}
	
	public boolean getStatus(){
		return confirm;
	}
	
	public void setStatus(boolean bool){
		confirm = bool;
	}
}
