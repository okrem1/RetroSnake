package main;

import java.util.ArrayList;
import java.util.List;

import obj.Apple;
import snake.Snake;

public class ObjectSetter {

	GamePanel gp;
	Snake snake;
	public List<Integer> objX = new ArrayList<>();
	public List<Integer> objY = new ArrayList<>();
	
	public ObjectSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	
	public void setObject() {
		
		if(gp.curObjCount < gp.maxObjCount)
		{
			if(gp.obj[gp.curObjCount] == null) {
				gp.obj[gp.curObjCount] = new Apple();
				
				int newX, newY;
				boolean validPos;
				
				do {
					newX = gp.tileSize * (int)((Math.random() * 20) + 5);
					newY = gp.tileSize * (int)((Math.random() * 19) + 1);
					
					validPos = true;
					for(int i = 0; i < objX.size(); i++) {
						if (newX == objX.get(i) && newY == objY.get(i)) {
							validPos = false;
							break;
						}
					}
				} while (!validPos);
				
				gp.obj[gp.curObjCount].visualX = newX;
				gp.obj[gp.curObjCount].visualY = newY;
				objX.add(newX);
				objY.add(newY);
				
				gp.curObjCount++;
				
				
			}
		} 
	}
	
}
