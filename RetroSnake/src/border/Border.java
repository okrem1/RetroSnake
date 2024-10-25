package border;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UI;
import snake.Snake;

public class Border {
	
	GamePanel gp;
	UI ui;
	
	public BufferedImage image;
	public int visualX, visualY;
	public List<Integer> borderX = new ArrayList<>();
	public List<Integer> borderY = new ArrayList<>();
	int counter;
	
	public Border(GamePanel gp, UI ui) {
		this.gp = gp;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/border/border.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void draw(Graphics2D g2, GamePanel gp) {
		
		// Line 1
		for(int i = 0; i <= 20; i++) {
			
			g2.drawImage(image, 4 * gp.tileSize, i * gp.tileSize, gp.tileSize, gp.tileSize, null);
			// ADD TO LIST FOR COLLISION CHECK
			borderX.add(4);
			borderY.add(i*gp.tileSize);
			
		}

		for(int i = 0; i <= 20; i++) {
			
			g2.drawImage(image, 24 * gp.tileSize, i * gp.tileSize, gp.tileSize, gp.tileSize, null);
			// ADD TO LIST FOR COLLISION CHECK
			borderX.add(24);
			borderY.add(i*gp.tileSize);
			
		}
		
		for(int i = 4; i <= 24; i++) {
			
			g2.drawImage(image, i * gp.tileSize, 0 * gp.tileSize, gp.tileSize, gp.tileSize, null);
			// ADD TO LIST FOR COLLISION CHECK
			borderX.add(i*gp.tileSize);
			borderY.add(0);
			
		}
		
		for(int i = 4; i <= 24; i++) {
			
			g2.drawImage(image, i * gp.tileSize, 20 * gp.tileSize, gp.tileSize, gp.tileSize, null);
			// ADD TO LIST FOR COLLISION CHECK
			borderX.add(i*gp.tileSize);
			borderY.add(20);
			
		}
	}
	
	public void checkBorder(Snake snake, UI ui) {
		switch(snake.direction) {
		case "up":
			if(snake.visualY - gp.tileSize == 0) {
				counter++;
			} else {
				counter = 0;
			}
			
			if(counter >= gp.FPS - snake.speed - 1) {
				ui.gameOver = true;
			}
			break;
		case "down":
			if(snake.visualY + gp.tileSize == 20 * gp.tileSize) {
				counter++;
			} else {
				counter = 0;
			}
			
			if(counter >= gp.FPS - snake.speed - 1) {
				ui.gameOver = true;
			}
			break;
		case "left":
			if(snake.visualX - gp.tileSize == 4 * gp.tileSize) {
				counter++;
			} else {
				counter = 0;
			}
			
			if(counter >= gp.FPS - snake.speed - 1) {
				ui.gameOver = true;
			}
			break;
			
		case "right":
			if(snake.visualX + gp.tileSize == 24 * gp.tileSize) {
				counter++;
			} else {
				counter = 0;
			}
			
			if(counter >= gp.FPS - snake.speed - 1) {
				ui.gameOver = true;
			}
			break;
		}
		
	}
}
