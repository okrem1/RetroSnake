package obj;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Apple extends OBJ {
	
	public Apple() {
		
		name = "Apple";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/apple.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
