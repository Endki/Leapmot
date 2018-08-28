package testset;

import java.awt.*;
import java.awt.event.KeyEvent;

public class test {

	public static void main(String[] args) {
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			System.out.println("123");
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			System.out.println("456");
		
			
			
			
	}

}
