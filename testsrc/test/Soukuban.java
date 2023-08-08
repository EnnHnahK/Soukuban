package test;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Soukuban extends JFrame{
	
//	private final int SIZE = 30;
	
	public final int WIDTH = 800 , HEIGHT = 600;
	
	public Soukuban() {
		initUI();
	}

	private void initUI() {
		
		Board_Test boa = new Board_Test();
		add(boa); 
		
		
		
		
		setTitle("Soukuban");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
//		JButton back = new JButton("Back");
//		back.setLayout(null);
//		add(back);
		
	}

	public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            
        	Soukuban game = new Soukuban();
            game.setVisible(true);
        });
    }
	
}
