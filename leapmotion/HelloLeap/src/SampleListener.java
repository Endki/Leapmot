

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.lang.Math;

import javax.swing.JOptionPane;

import com.leapmotion.leap.*;
import com.leapmotion.leap.Finger.Type;
import com.leapmotion.leap.Gesture.State;

public class SampleListener extends Listener  {
	
	int i, j=0, re2=0, ps;
	int arr1[] = new int [100];
	int arr2[] = new int [40];
	float re1 =0;
	public String pass="";
	public int passnum=0;
	public boolean passnumIsChanged;
	
	
	public String PassWord() {
		return pass;
	}
	
	
	
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
		
		passnumIsChanged=false;
		Frame frame = controller.frame();
		GestureList gestures = frame.gestures();

		//서클,스와이프
		for (int i = 0; i < gestures.count(); i++) {
			Gesture gesture = gestures.get(i);
			
			switch (gesture.type()) {
			case TYPE_CIRCLE:
				CircleGesture circle = new CircleGesture(gesture);

				String clockwiseness;
				if (circle.pointable().direction().angleTo(circle.normal()) <= Math.PI / 4) {
					clockwiseness = "clockwise";
				} else {
					clockwiseness = "counter-clockwise";
				}

				double sweptAngle = 0;
				if (circle.state() != State.STATE_START) {
					CircleGesture previous = new CircleGesture(controller.frame(1).gesture(circle.id()));
					sweptAngle = (circle.progress() - previous.progress()) * 2 * Math.PI;

				} // 여기에 이제 인식되는거 가정하고 넣으면됨 break 하게하는 것을 넣으면 좋을듯
				System.out.println(
						  "count: "+gestures.count()+ " Circle ID: "+circle.id()
						  +" State: "+circle.state() +" Progress: "+circle.progress()
						  +" Radius: "+circle.radius() +" Angle: "+Math.toDegrees(sweptAngle) +
						  " "+clockwiseness);
				break;
				
			case TYPE_SWIPE:
				SwipeGesture swipe = new SwipeGesture(gesture);
				System.out.println(
						"이것이 스와이프입니다.          "+
						
						"Swipe ID: "+swipe.id()
									+ " Stete: "+swipe.state()
									+ " Swipe_Position: "+ swipe.position()
									+ " Direction: "+swipe.direction()
									+ " Speed: "+swipe.speed());
				i=31;
				Main.TF=false;
				
				break;
			}
		}
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 방향 벡터 사용
		for (Hand hand : frame.hands()) {
			
			Vector normal = hand.palmNormal();
			Vector direction = hand.direction();

			for (Finger finger : hand.fingers()) {
				Type fingertyp = finger.type();
				Enum fingerenum = fingertyp;
				String fingerstr = fingerenum.toString();
				com.leapmotion.leap.Bone.Type[] bonetyp = Bone.Type.values();
/*				if(frame.hands().count()==2) {

//					robot.keyPress(KeyEvent.VK_ENTER);
//					robot.keyRelease(KeyEvent.VK_ENTER);

					onExit();
				}*/
//				double direction_z = finger.direction().getZ();
//				double direction_c = frame.fingers().get(0).direction().getZ();
				/*
				 * System.out.println("    " + finger.type() // + "direction " +
				 * finger.direction() + " direction z " + finger.direction().getZ() );
				 */

				
//				System.out.println(frame.fingers().get(0).type().toString() +" "+ frame.fingers().get(0).direction().getZ());
//				System.out.println(frame.fingers().get(1).type().toString()+" "+ frame.fingers().get(1).direction().getZ());
//				System.out.println(frame.fingers().get(2).type().toString()+" "+frame.fingers().get(2).direction().getZ());
//				System.out.println(frame.fingers().get(3).type().toString()+" "+frame.fingers().get(3).direction().getZ());
//				System.out.println(frame.fingers().get(4).type().toString()+" "+frame.fingers().get(4).direction().getZ());
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
					System.out.println("1");
					//strbff.append("1");
					arr1[i]=1;
					i++;
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
					System.out.println("2");
					//strbff.append("2");
					arr1[i]=2;
					i++;
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
					System.out.println("3");
					//strbff.append("3");
					arr1[i]=3;
					i++;
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
					System.out.println("4");
					//strbff.append("4");
					arr1[i]=4;
					i++;
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
					//strbff.append("5");
					arr1[i]=5;
					i++;
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
		
//        System.out.println(strbff.toString());
        System.out.println("i="+i);
        
        
        if (i>=30) {
        	try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        	i=0;
        	//setPaused(True);
        	
        	
        	for (i=5;i<25;i++) {
        		j=0;
        		arr2[j]=arr1[i];
        		
        		re1+=arr2[j];
        		re2+=arr2[j];
        		j++;
        	}
        	re1/=20;
        	re2/=20;
        	re1-=re2;
        	
        	if(re1<0.3) {
        		ps=re2;
        		pass+=String.valueOf(ps);
        		passnum++;
        		passnumIsChanged=true;
            	
        	}else if((re1>=0.3)&&(re1<0.7)) {
        		//다시 입력 받기
        		passnumIsChanged=false;
        	}else {
        		ps=re2+1;
        		pass+=String.valueOf(ps);
        		passnum++;
        		passnumIsChanged=true;
        	}	
        	
        	i=0;
        	re1=0;
        	re2=0;
        	
        		try {
        			Thread.sleep(1000);
        		} catch (InterruptedException e1) {
        			// TODO Auto-generated catch block
        			e1.printStackTrace();
        		}
        		
        		
        	
        	
        	//System.out.println("password="+ps);
        	//System.out.println("passwordString="+pass);
        	
        	//setPaused(False);
        	
        }
        
        
        
        
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