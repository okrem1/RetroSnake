package snake;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.ObjectSetter;

public class Snake {
	
	KeyHandler keyH;
	GamePanel gp;
	ObjectSetter oSetter;
	
	public int screenX;
	public int screenY;
	public int visualX;
	public int visualY;
	public float speed;
	public int moveCounter;
	public String direction;
	public BufferedImage snakeImage;
	public int snakeSize = 1;
	public List<Integer> listX = new ArrayList<>();
	public List<Integer> listY = new ArrayList<>();
	
	public Snake(GamePanel gp, KeyHandler keyH, ObjectSetter oSetter) {
		
		this.gp = gp;
		this.keyH = keyH;
		this.oSetter = oSetter;
		

		
		setDefaultValues();
		getSnakeImage();
	}
	
	public void setDefaultValues() {
														// X/Y Values for Border
		screenX = gp.tileSize * (gp.maxScreenCol/2 + 1); // 5 & 25
		screenY = gp.tileSize * (gp.maxScreenRow/2 + 1); // 1 & 21
		speed = 45; 
		direction = "";
	}
	
	public void getSnakeImage() {
		
		try {
			
			snakeImage = ImageIO.read(getClass().getResourceAsStream("/snake/snake.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true)
		{
			
			if(keyH.upPressed == true && direction != "down") {
				direction = "up";
			}
			if(keyH.downPressed == true && direction != "up") {
				direction = "down";
			}
			if(keyH.leftPressed == true && direction != "right") {
				direction = "left";
			}
			if(keyH.rightPressed == true && direction != "left") {
				direction = "right";
			}
			

			
		}
		
		
			int objIndex = gp.cChecker.checkObject(this);
			pickUpObject(objIndex);
			gp.border.checkBorder(this, gp.ui);


		
		moveCounter++;
		if(moveCounter >= gp.FPS - speed) {
			listX.add(screenX); listY.add(screenY);
			switch(direction) {
			case "up":  screenY -= gp.tileSize; break;
			case "down": screenY += gp.tileSize; break;
			case "left": screenX -= gp.tileSize; break;
			case "right": screenX += gp.tileSize; break; // SNAKE SIZE SET TO 2 FOR DEBUGGING
			}
					

			moveCounter = 0;
		}
		
		gp.cChecker.selfCheck(this, gp.ui);	

	}

	
	public void pickUpObject(int i) {
		
		if(i != 999) {
			String objectName = gp.obj[i].name;
			
			switch(objectName) {
			case "Apple":
				int newX, newY;
				boolean validPos;
				
				do {
					newX = gp.tileSize * (int)((Math.random() * 20) + 5);
					newY = gp.tileSize * (int)((Math.random() * 19) + 1);
					
					validPos = true;
					

					for(int i1 = 0; i1 < oSetter.objX.size(); i1++) {
						if (newX == oSetter.objX.get(i1) && newY == oSetter.objY.get(i1)) {
							validPos = false;
							break;
						} 
						
						for(int p = 1; p < listX.size(); p++) {
							if(newX == listX.get(listX.size() - p) && newY == listY.get(listY.size() - p)) {
								validPos = false;
								break;
							}
						}
						
//						else if(newX == listX.get(listX.size() - i1) && newY == listY.get(listY.size() - i1)) {
//							validPos = false;
//							break;
//						}
					}
						

				} while (!validPos);
				
				gp.obj[i].visualX = newX;
				gp.obj[i].visualY = newY;
				oSetter.objX.add(newX);
				oSetter.objY.add(newY);
				
				snakeSize++;
				gp.score++;
				gp.curObjCount++;
				
				break;
			}
		}
	}
	
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = snakeImage;
		visualX = screenX - gp.tileSize;
		visualY = screenY - gp.tileSize;
		
		g2.drawImage(image, visualX, visualY, gp.tileSize, gp.tileSize, null);
		
		// SNAKE SIZE
		if(snakeSize - 1 > 0) {
			for(int i = 1; i < snakeSize; i++)
			{
				
			g2.drawImage(image, listX.get(listX.size()-i) - gp.tileSize, listY.get(listY.size()-i) - gp.tileSize, gp.tileSize, gp.tileSize, null);
			
			}
		}
	}




}
