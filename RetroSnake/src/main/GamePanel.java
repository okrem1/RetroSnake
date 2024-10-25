package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import border.Border;
import obj.OBJ;
import snake.Snake;

public class GamePanel extends JPanel implements Runnable {
	
	// SCREEN SETTINGS
	final int originalTileSize = 16;
	final int scale = 2;
	
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 29;
	public final int maxScreenRow = 21;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenLength = tileSize * maxScreenRow;
	
	
	// FPS
	public int FPS = 60;
	
	// SYSTEM
	public Thread gameThread;
	KeyHandler keyH = new KeyHandler();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public ObjectSetter oSetter = new ObjectSetter(this);
	public UI ui = new UI(this);
	
	// SNAKE
	public Snake snake = new Snake(this, keyH, oSetter);
	public OBJ obj[] = new OBJ[100];
	public Border border = new Border(this, ui);
	public int curObjCount = 0;
	public int maxObjCount = 5;
	public int score;
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenLength));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
		
	}
	
	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null)
		{
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			
			if(delta >= 1) {
				
				update();
				repaint();
				
				delta--;
			}
			
		}
	}
	
	public void update() {
		
		oSetter.setObject();
		snake.update();
		

		

		//System.out.println(obj[curObjCount].visualY);
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		for(int i = 0; i < maxObjCount; i++) {
			if(obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		
		// DRAWING BORDER
		border.draw(g2, this);
		
		snake.draw(g2);
		
		ui.draw(g2);
		
		g2.dispose();
	}
}
