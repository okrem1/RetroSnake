package obj;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


import main.GamePanel;

public class OBJ {
	
	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public int visualX, visualY;
	
	public void draw(Graphics2D g2, GamePanel gp) {
		// WALLS ON 26 and 4
		
		g2.drawImage(image, visualX, visualY, gp.tileSize, gp.tileSize, null);
	}
}
