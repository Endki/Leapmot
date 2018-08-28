import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.lang.Math;

import com.leapmotion.leap.*;
import com.leapmotion.leap.Finger.Type;

public class SampleListener extends Listener  {
static	StringBuffer strbff=new StringBuffer("");
	public StringBuffer getStrbff() {
		return strbff;
	}

	public void setStrbff(StringBuffer strbff) {
		this.strbff = strbff;
	}

	public void onInit(Controller controller) {
		System.out.println("Initialized");
	}

	public void onDisconnect(Controller controller) {
		// Note: not dispatched when running in a debugger.
		
		System.out.println("Disconnected");
	}

	public void onConnect(Controller controller) {
		System.out.println("Connected");
		controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
		controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
		controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
		controller.enableGesture(Gesture.Type.TYPE_SWIPE);
	}

	public void onExit() {
		
		
		System.out.println("Exited");
	}

	public void onFrame(Controller controller) {
		
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Frame frame = controller.frame();
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(frame.hands().count()==2) {
			
		
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			onExit();
		}
		
		
		
		
		GestureList gestures = frame.gestures();

		// 방향 벡터 사용
		for (Hand hand : frame.hands()) {
			
			Vector normal = hand.palmNormal();
			Vector direction = hand.direction();

			for (Finger finger : hand.fingers()) {
				Type fingertyp = finger.type();
				Enum fingerenum = fingertyp;
				String fingerstr = fingerenum.toString();
				com.leapmotion.leap.Bone.Type[] bonetyp = Bone.Type.values();

				double direction_z = finger.direction().getZ();
				double direction_c = frame.fingers().get(0).direction().getZ();
//            	Finger firstFingerInList = frame.fingers().get(2);
//            	System.out.println(firstFingerInList.type());

//            	System.out.println(finger.id()%5);

				/*
				 * System.out.println("    " + finger.type() // + "direction " +
				 * finger.direction() + " direction z " + finger.direction().getZ() );
				 */

				
				System.out.println(frame.fingers().get(0).type().toString() +" "+ frame.fingers().get(0).direction().getZ());
				System.out.println(frame.fingers().get(1).type().toString()+" "+ frame.fingers().get(1).direction().getZ());
				System.out.println(frame.fingers().get(2).type().toString()+" "+frame.fingers().get(2).direction().getZ());
				System.out.println(frame.fingers().get(3).type().toString()+" "+frame.fingers().get(3).direction().getZ());
				System.out.println(frame.fingers().get(4).type().toString()+" "+frame.fingers().get(4).direction().getZ());
				// 1추출
				if ((frame.fingers().get(0).type().toString() == "TYPE_THUMB"
						&& frame.fingers().get(0).direction().getZ() <= -0.60
						&& frame.fingers().get(1).type().toString() == "TYPE_INDEX")
						&& frame.fingers().get(1).direction().getZ() > 0.60
						|| (frame.fingers().get(1).type().toString() == "TYPE_INDEX"
						&& frame.fingers().get(1).direction().getZ() <= -0.60
						&& frame.fingers().get(0).type().toString() == "TYPE_THUMB"
						&& frame.fingers().get(0).direction().getZ() > 0.60)
						
								
						&& (frame.fingers().get(2).type().toString() == "TYPE_MIDDLE"
						&& frame.fingers().get(2).direction().getZ() > - 0.60
						&& frame.fingers().get(3).type().toString() == "TYPE_RING"
						&& frame.fingers().get(3).direction().getZ() > - 0.60
						&& frame.fingers().get(4).type().toString() == "TYPE_PINKY"
						&& frame.fingers().get(4).direction().getZ() > -0.60))
						// 5개 손가락 순서 맞춰주고
						// 손가락의 방향벡터가 맞으면 1을 출력한다.
						
						 {
					strbff.append("1");
					break;
				}
				//2추출
				else if
						((frame.fingers().get(0).type().toString() == "TYPE_THUMB"
						&& frame.fingers().get(0).direction().getZ()<=-0.60
						&& frame.fingers().get(1).type().toString() == "TYPE_INDEX"
						&& frame.fingers().get(1).direction().getZ()<=-0.60)
						&& (frame.fingers().get(2).type().toString() == "TYPE_MIDDLE"
				        &&frame.fingers().get(2).direction().getZ()> -0.60
						||
						(frame.fingers().get(0).type().toString() == "TYPE_THUMB"
						&& frame.fingers().get(0).direction().getZ()<= -0.60
						&& frame.fingers().get(1).type().toString() == "TYPE_INDEX"
						&& frame.fingers().get(1).direction().getZ()<=-0.60
						&& frame.fingers().get(2).type().toString() == "TYPE_MIDDLE"
						&& frame.fingers().get(2).direction().getZ()<=-0.60)
	
		            	&& frame.fingers().get(3).type().toString() == "TYPE_RING"
		            	&& frame.fingers().get(3).direction().getZ()>-0.60
		            	&& frame.fingers().get(3).type().toString() == "TYPE_PINKY"
		            	&& frame.fingers().get(4).direction().getZ()> -0.60
		            	)) {  
					strbff.append("2");
		            	break;
		                }
						
		    	//3추출
				else if((frame.fingers().get(0).type().toString() == "TYPE_THUMB"
						&& frame.fingers().get(0).direction().getZ()<=-0.60
						&& frame.fingers().get(1).type().toString() == "TYPE_INDEX"
						&& frame.fingers().get(1).direction().getZ()<=-0.60
						&& frame.fingers().get(2).type().toString() =="TYPE_MIDDLE"
		            	&& frame.fingers().get(2).direction().getZ()<=-0.60) 
						
						&&(frame.fingers().get(3).type().toString() =="TYPE_RING"
		            	&&frame.fingers().get(3).direction().getZ()>-0.60
		            	&&frame.fingers().get(4).type().toString() =="TYPE_PINKY"	
		            	&& frame.fingers().get(4).direction().getZ()> -0.60
		            	)
//						|| 
//						(frame.fingers().get(1).type().toString() == "TYPE_INDEX"
//						&& frame.fingers().get(1).direction().getZ()<=-0.60
//						&& frame.fingers().get(2).type().toString() == "TYPE_MIDDLE"
//						&& frame.fingers().get(2).direction().getZ()<=-0.60
//		            	&& frame.fingers().get(3).type().toString() =="TYPE_RING"
//		            	&& frame.fingers().get(4).direction().getZ()<=-0.60)
		          
//						&&(frame.fingers().get(0).type().toString() =="TYPE_THUMB"
//		            	&&frame.fingers().get(0).direction().getZ()>-0.60
//		            	&&frame.fingers().get(4).type().toString() =="TYPE_PINKY"	
//		            	&& frame.fingers().get(4).direction().getZ()> -0.60
//		            	)
					) {  
					strbff.append("3");
		            	break;
		                }//5개 손가락 순서 맞춰주고
		            	//손가락의 방향벡터가 맞으면 3을 출력한다.
		    	//4추출
				else if((frame.fingers().get(1).type().toString() =="TYPE_INDEX"
						&& frame.fingers().get(1).direction().getZ()<=-0.60
		            	&& frame.fingers().get(2).type().toString() =="TYPE_MIDDLE"
		            	&& frame.fingers().get(2).direction().getZ()<=-0.60
		            	&& frame.fingers().get(3).type().toString() =="TYPE_RING"	
		            	&& frame.fingers().get(3).direction().getZ()<-0.60
		            	&& frame.fingers().get(4).type().toString() =="TYPE_PINKY"
		            	&& frame.fingers().get(4).direction().getZ()<=-0.60)
		            	//5개 손가락 순서 맞춰주고
		            	//손가락의 방향벡터가 맞으면 4를 출력한다.
		            &&(frame.fingers().get(0).type().toString() =="TYPE_THUMB"
		            	&& frame.fingers().get(0).direction().getZ()>-0.60)
					) {
					strbff.append("4");
		            	break;
		                }
				// 5추출
				else if (frame.fingers().get(0).type().toString() == "TYPE_THUMB"
						&& frame.fingers().get(0).direction().getZ() <= -0.60
						&& frame.fingers().get(1).type().toString() == "TYPE_INDEX"
						&& frame.fingers().get(1).direction().getZ() <= -0.60
						&& frame.fingers().get(2).type().toString() == "TYPE_MIDDLE"
						&& frame.fingers().get(2).direction().getZ() <= -0.60	
						&& frame.fingers().get(3).type().toString() == "TYPE_RING"
						&& frame.fingers().get(3).direction().getZ() <= -0.60
						&& frame.fingers().get(4).type().toString() == "TYPE_PINKY"
						&& frame.fingers().get(4).direction().getZ() <= -0.60)
						// 5개 손가락 순서 맞춰주고
						// 손가락의 방향벡터가 맞으면 5를 출력한다.
						 {
					
					System.out.println("5");
					strbff.append("5");
					break;
				}

				break;
			}
			break;
		}

		if (frame.hands().isEmpty()) {
			System.out.println("is empty"); // 핸드가 비었으면 공백 출력
		}else {
			System.out.println("is confirmed");
		}
        System.out.println(strbff.toString());
	}
	

}

class Sample {
	public static void main(String[] args) {
		// Create a sample listener and controller
		SampleListener listener = new SampleListener();
		Controller controller = new Controller();

		// Have the sample listener receive events from the controller
		controller.addListener(listener);


		// Keep this process running until Enter is pressed 
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Remove the sample listener when done
		controller.removeListener(listener);
	
	}
}