
import com.leapmotion.leap.Bone;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Finger.Type;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.GestureList;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Vector;



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

		// ���� ���� ���
		for (Hand hand : frame.hands()) {
			Vector normal = hand.palmNormal();
			Vector direction = hand.direction();
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			/*
			 * try { Thread.sleep(2000); } catch (InterruptedException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }
			 */
			
			
			
			for (Finger finger : hand.fingers()) {
				Type fingertyp = finger.type();
				Enum fingerenum = fingertyp;
				String fingerstr = fingerenum.toString();
				com.leapmotion.leap.Bone.Type[] bonetyp = Bone.Type.values();

				double direction_z = finger.direction().getZ();
				double direction_c = frame.fingers().get(0).direction().getZ();

				
				System.out.println(frame.fingers().get(0).type().toString() +" "+ frame.fingers().get(0).direction().getZ());
				System.out.println(frame.fingers().get(1).type().toString()+" "+ frame.fingers().get(1).direction().getZ());
				System.out.println(frame.fingers().get(2).type().toString()+" "+frame.fingers().get(2).direction().getZ());
				System.out.println(frame.fingers().get(3).type().toString()+" "+frame.fingers().get(3).direction().getZ());
				System.out.println(frame.fingers().get(4).type().toString()+" "+frame.fingers().get(4).direction().getZ());
				System.out.println(" ");
				// 1����
				if (frame.fingers().get(0).type().toString() == "TYPE_INDEX"
//						&& frame.fingers().get(1).type().toString() == "TYPE_THUMB"
//						(frame.fingers().get(0).type().toString() == "TYPE_INDEX"
//						&&frame.fingers().get(0).type().toString() == "TYPE_THUMB"
								

						// 5�� �հ��� ���� �����ְ�
						// �հ����� ���⺤�Ͱ� ������ 1�� ����Ѵ�.
						&& frame.fingers().get(0).direction().getZ() < 0
						&& frame.fingers().get(1).direction().getZ() <-0.7
						&& frame.fingers().get(2).direction().getZ() >0
						&& frame.fingers().get(3).direction().getZ() >0
						&& frame.fingers().get(4).direction().getZ() >0) {
					System.out.println("  1  ");
					break;
				}
				//2����
				else if(frame.fingers().get(0).type().toString() == "TYPE_INDEX"
						&& frame.fingers().get(1).type().toString() == "TYPE_MIDDLE"

		            	//5�� �հ��� ���� �����ְ�
		            	//�հ����� ���⺤�Ͱ� ������ 2�� ����Ѵ�.
		            	&& frame.fingers().get(0).direction().getZ()<-0.80
		            	&& frame.fingers().get(1).direction().getZ()<-0.80
		            	&& frame.fingers().get(2).direction().getZ()>-0.7
		            	&& frame.fingers().get(3).direction().getZ()>-0.7
		            	&& frame.fingers().get(4).direction().getZ()>-0.7
		            	) {  
		            	System.out.println("  2  ");
		            	break;
		                }
		    	//3����
				else if(frame.fingers().get(0).type().toString() =="TYPE_THUMB"
		            	&& frame.fingers().get(1).type().toString() =="TYPE_INDEX"
		            	&& frame.fingers().get(2).type().toString() =="TYPE_MIDDLE"
		            	//5�� �հ��� ���� �����ְ�
		            	//�հ����� ���⺤�Ͱ� ������ 3�� ����Ѵ�.
		            	&& frame.fingers().get(0).direction().getZ()>-0.7
		            	&& frame.fingers().get(1).direction().getZ()<-0.7
		            	&& frame.fingers().get(2).direction().getZ()<-0.7
		            	&& frame.fingers().get(3).direction().getZ()<-0.7
		            	&& frame.fingers().get(4).direction().getZ()>-0.7
		            	) {  
		            	System.out.println("  3  ");
		            	break;
		                }
		    	//4����
				else if(frame.fingers().get(0).type().toString() =="TYPE_THUMB"
		            	&& frame.fingers().get(1).type().toString() =="TYPE_INDEX"
		            	&& frame.fingers().get(2).type().toString() =="TYPE_MIDDLE"		
		            	&& frame.fingers().get(3).type().toString() =="TYPE_RING"
		            	&& frame.fingers().get(4).type().toString() =="TYPE_PINKY"
		            	//5�� �հ��� ���� �����ְ�
		            	//�հ����� ���⺤�Ͱ� ������ 4�� ����Ѵ�.
		            	&& frame.fingers().get(0).direction().getZ()>-0.8
		            	&& frame.fingers().get(1).direction().getZ()<-0.8
		            	&& frame.fingers().get(2).direction().getZ()<-0.8
		            	&& frame.fingers().get(3).direction().getZ()<-0.8
		            	&& frame.fingers().get(4).direction().getZ()<-0.8) {
			
		            System.out.println("  4  ");
		            break;
		        }
				// 5����
				else if (frame.fingers().get(0).type().toString() == "TYPE_THUMB"
						&& frame.fingers().get(1).type().toString() == "TYPE_INDEX"
						&& frame.fingers().get(2).type().toString() == "TYPE_MIDDLE"
						&& frame.fingers().get(3).type().toString() == "TYPE_RING"
						&& frame.fingers().get(4).type().toString() == "TYPE_PINKY"
						// 5�� �հ��� ���� �����ְ�
						// �հ����� ���⺤�Ͱ� ������ 5�� ����Ѵ�.
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

		if (frame.hands().isEmpty()) {
			System.out.println("is empty"); // �ڵ尡 ������� ���� ���
		}
		/////////////////////////////////////////////////////////////////
		// ����
		/*
		 * try { Thread.sleep(2000); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		/*
		 * //�� �ν� �ڵ� for(int i=0;i<gestures.count(); i++) { Gesture gesture
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
		 * } //���⿡ ���� �νĵǴ°� �����ϰ� ������� break �ϰ��ϴ� ���� ������ ������ System.out.println(
		 * "count: "+gestures.count()+ " Circle ID: "+circle.id()
		 * +" State: "+circle.state() +" Progress: "+circle.progress()
		 * +" Radius: "+circle.radius() +" Angle: "+Math.toDegrees(sweptAngle) +
		 * " "+clockwiseness); break; } }
		 */

		// ���⺤�� ���

	}
}
