package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import snake.Snake;

public class UI {
	
	GamePanel gp;
	Font arial_40;
	Font arial_30;
	BufferedImage keyImage;
	public boolean gameOver;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_30 = new Font("Arial", Font.PLAIN, 30);
	}
	
	public void draw(Graphics2D g2) {
		
		int x;
		int y;
		String text;
		int textLength;
		
		// SCORE
		g2.setFont(arial_30);
		g2.setColor(Color.white);
		
		text = "Score";
		textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		
		x = gp.tileSize;
		y = gp.tileSize;
		g2.drawString(text, x, y);
		
		text = "" + gp.score;
		textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		x = gp.tileSize*2;
		y = gp.tileSize*3;
		g2.drawString(text, x, y);
		
		
		// GAME OVER
		if(gameOver == true && gp.score == 360)
		{
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			
			
			
			text = "Congratulations! You Win!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenLength/2 - textLength/2;
			g2.drawString(text, x, y);
			
			gp.gameThread = null;
			
		} else if(gameOver == true) {
			
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			
			text = "You Lose! You scored " + gp.score + " points!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenLength/2;
			g2.drawString(text, x, y);
			
			gp.gameThread = null;
			
		}
	}
	
}
