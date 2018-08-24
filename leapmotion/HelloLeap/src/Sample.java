
import java.io.IOException;
import java.lang.Math;

import com.leapmotion.leap.*;
import com.leapmotion.leap.Finger.Type;

class SampleListener extends Listener {
	public void onInit(Controller controller) {
		System.out.println("Initialized");
	}

	public void onConnect(Controller controller) {
		System.out.println("Connected");
		controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
		controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
		controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
		controller.enableGesture(Gesture.Type.TYPE_SWIPE);
	}

	public void onDisconnect(Controller controller) {
		// Note: not dispatched when running in a debugger.
		System.out.println("Disconnected");
	}

	public void onExit(Controller controller) {
		System.out.println("Exited");
	}

	public void onFrame(Controller controller) {
		Frame frame = controller.frame();
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
						&& frame.fingers().get(1).type().toString() == "TYPE_INDEX")||
						(frame.fingers().get(0).type().toString() == "TYPE_INDEX"
						&&frame.fingers().get(0).type().toString() == "TYPE_THUMB"
								)
//						&& frame.fingers().get(2).type().toString() == "TYPE_MIDDLE"
//						&& frame.fingers().get(3).type().toString() == "TYPE_RING"
//						&& frame.fingers().get(4).type().toString() == "TYPE_PINKY"
						// 5개 손가락 순서 맞춰주고
						// 손가락의 방향벡터가 맞으면 1을 출력한다.
						&& frame.fingers().get(0).direction().getZ() < -0.80
						&& frame.fingers().get(1).direction().getZ() <-0.7
						&& frame.fingers().get(2).direction().getZ() >-0.7
						&& frame.fingers().get(3).direction().getZ() >-0.7
						&& frame.fingers().get(4).direction().getZ() >-0.7) {
					System.out.println("  1  ");
					break;
				}
				//2추출
				else if(frame.fingers().get(0).type().toString() == "TYPE_INDEX"
						&& frame.fingers().get(1).type().toString() == "TYPE_MIDDLE"

		            	//5개 손가락 순서 맞춰주고
		            	//손가락의 방향벡터가 맞으면 2를 출력한다.
		            	&& frame.fingers().get(0).direction().getZ()<-0.80
		            	&& frame.fingers().get(1).direction().getZ()<-0.80
		            	&& frame.fingers().get(2).direction().getZ()>-0.7
		            	&& frame.fingers().get(3).direction().getZ()>-0.7
		            	&& frame.fingers().get(4).direction().getZ()>-0.7
		            	) {  
		            	System.out.println("  2  ");
		            	break;
		                }
		    	//3추출
				else if(frame.fingers().get(0).type().toString() =="TYPE_THUMB"
		            	&& frame.fingers().get(1).type().toString() =="TYPE_INDEX"
		            	&& frame.fingers().get(2).type().toString() =="TYPE_MIDDLE"
		            	//5개 손가락 순서 맞춰주고
		            	//손가락의 방향벡터가 맞으면 3을 출력한다.
		            	&& frame.fingers().get(0).direction().getZ()>-0.7
		            	&& frame.fingers().get(1).direction().getZ()<-0.80
		            	&& frame.fingers().get(2).direction().getZ()<-0.80
		            	&& frame.fingers().get(3).direction().getZ()>-0.7
		            	&& frame.fingers().get(4).direction().getZ()>-0.7
		            	) {  
		            	System.out.println("  3  ");
		            	break;
		                }
		    	//4추출
				else if(frame.fingers().get(0).type().toString() =="TYPE_INDEX"
		            	&& frame.fingers().get(1).type().toString() =="TYPE_MIDDLE"
		            	&& frame.fingers().get(2).type().toString() =="TYPE_RING"		
		            	&& frame.fingers().get(3).type().toString() =="TYPE_PINKY"
		            	//5개 손가락 순서 맞춰주고
		            	//손가락의 방향벡터가 맞으면 4를 출력한다.
		            	&& frame.fingers().get(0).direction().getZ()<-0.80
		            	&& frame.fingers().get(1).direction().getZ()<-0.80
		            	&& frame.fingers().get(2).direction().getZ()<-0.80
		            	&& frame.fingers().get(3).direction().getZ()<-0.80
		            	&& frame.fingers().get(4).direction().getZ()>-0.7
		            	) {  //검지 추출
					
		            	System.out.println("  4  ");
		            	break;
		                }
				// 5추출
				else if (frame.fingers().get(0).type().toString() == "TYPE_THUMB"
						&& frame.fingers().get(1).type().toString() == "TYPE_INDEX"
						&& frame.fingers().get(2).type().toString() == "TYPE_MIDDLE"
						&& frame.fingers().get(3).type().toString() == "TYPE_RING"
						&& frame.fingers().get(4).type().toString() == "TYPE_PINKY"
						// 5개 손가락 순서 맞춰주고
						// 손가락의 방향벡터가 맞으면 5를 출력한다.
						&& frame.fingers().get(0).direction().getZ() < -0.80
						&& frame.fingers().get(1).direction().getZ() < -0.80
						&& frame.fingers().get(2).direction().getZ() < -0.80
						&& frame.fingers().get(3).direction().getZ() < -0.80
						&& frame.fingers().get(4).direction().getZ() < -0.80) {
					System.out.println("  5  ");
					break;
				}

				break;

			}
			break;
		}

		if (!frame.hands().isEmpty()) {
			System.out.println("is empty"); // 핸드가 비었으면 공백 출력
		}
		/////////////////////////////////////////////////////////////////
		// 시작
		/*
		 * try { Thread.sleep(2000); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		/*
		 * //원 인식 코드 for(int i=0;i<gestures.count(); i++) { Gesture gesture
		 * =gestures.get(i);
		 * 
		 * switch(gesture.type()) { case TYPE_CIRCLE: CircleGesture circle= new
		 * CircleGesture(gesture);
		 * 
		 * String clockwiseness;
		 * if(circle.pointable().direction().angleTo(circle.normal())<=Math.PI/4) {
		 * clockwiseness = "clockwise"; }else { clockwiseness = "counter-clockwise"; }
		 * 
		 * double sweptAngle=0; if(circle.state() != State.STATE_START) { CircleGesture
		 * previous=new CircleGesture(controller.frame(1).gesture(circle.id()));
		 * sweptAngle=(circle.progress() - previous.progress()) * 2 * Math.PI;
		 * 
		 * } //여기에 이제 인식되는거 가정하고 넣으면됨 break 하게하는 것을 넣으면 좋을듯 System.out.println(
		 * "count: "+gestures.count()+ " Circle ID: "+circle.id()
		 * +" State: "+circle.state() +" Progress: "+circle.progress()
		 * +" Radius: "+circle.radius() +" Angle: "+Math.toDegrees(sweptAngle) +
		 * " "+clockwiseness); break; } }
		 */

		// 방향벡터 사용

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
		System.out.println("Press Enter to quit...");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Remove the sample listener when done
		controller.removeListener(listener);
	}
}
