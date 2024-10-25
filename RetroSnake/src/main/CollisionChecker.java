package main;

import snake.Snake;

public class CollisionChecker {
	
	GamePanel gp;
	UI ui;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public int checkObject(Snake snake) {
		int index = 999;
		
		snake.visualX = snake.screenX - gp.tileSize;
		snake.visualY = snake.screenY - gp.tileSize;
		
		for(int i = 0; i < gp.obj.length; i++) {
			if(gp.obj[i] != null) {
				if(snake.visualX == gp.obj[i].visualX && snake.visualY == gp.obj[i].visualY) {
					index = i;
					break;
				}
			}
		}
		return index;
	}
	
	public void selfCheck(Snake snake, UI ui) {
		switch(snake.direction) {
			case "up":
				for(int i = 1; i < snake.snakeSize; i++) {
					if(snake.screenY == snake.listY.get(snake.listY.size()-i) && snake.screenX == snake.listX.get(snake.listX.size()-i))
					{
						ui.gameOver = true;
					}
				}
				break;
				
				case "down":
					for(int i = 1; i < snake.snakeSize; i++) {
						if(snake.screenY == snake.listY.get(snake.listY.size()-i) && snake.screenX == snake.listX.get(snake.listX.size()-i))
						{
							ui.gameOver = true;
						}
					}break;
				case "left":
					for(int i = 1; i < snake.snakeSize; i++) {
						if(snake.screenX == snake.listX.get(snake.listX.size()-i) && snake.screenY == snake.listY.get(snake.listY.size()-i))
						{
							ui.gameOver = true;
						}
					}break;
					
				case "right":
					for(int i = 1; i < snake.snakeSize; i++) {
						if(snake.screenX == snake.listX.get(snake.listX.size()-i) && snake.screenY == snake.listY.get(snake.listY.size()-i))
						{
							ui.gameOver = true;
						}
					}break;
				}
		}
	}
	

