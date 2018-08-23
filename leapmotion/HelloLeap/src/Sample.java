/******************************************************************************\
* Copyright (C) 2012-2016 Leap Motion, Inc. All rights reserved.               *
* Leap Motion proprietary and confidential. Not for distribution.              *
* Use subject to the terms of the Leap Motion SDK Agreement available at       *
* https://developer.leapmotion.com/sdk_agreement, or another agreement         *
* between Leap Motion and you, your company or other organization.             *
\******************************************************************************/

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
    }

    public void onDisconnect(Controller controller) {
        //Note: not dispatched when running in a debugger.
        System.out.println("Disconnected");
    }

    public void onExit(Controller controller) {
        System.out.println("Exited");
    }

    public void onFrame(Controller controller) {
        // Get the most recent frame and report some basic information
        Frame frame = controller.frame();
        System.out.println(", hands: " + frame.hands().count()      // 핸드 수 리턴 
                         + ", fingers: " + frame.fingers().count()); // 핑거 수 리턴 근데 한손일 때 5 두손일 때 10 

        //Get hands
        for(Hand hand : frame.hands()) {
            String handType = hand.isLeft() ? "Left hand" : "Right hand"; // 오른손 왼손 가능
            System.out.println("  " + handType);

            // Get the hand's normal vector and direction
            Vector normal = hand.palmNormal();
            Vector direction = hand.direction();

            // Calculate the hand's pitch, roll, and yaw angles
            System.out.println("  pitch: " + Math.toDegrees(direction.pitch()) + " degrees, " //손바닥에서 손가락으로의 방향
                             + "roll: " + Math.toDegrees(normal.roll()) + " degrees, "
                             + "yaw: " + Math.toDegrees(direction.yaw()) + " degrees");
            // Get fingers
/*            for (Finger finger : hand.fingers()) {
            	
            	Finger.Type fingerType=finger.type();
            
            	Type typ=finger.type();
            	Enum a=typ;
            	String str=a.toString();
            	
            	if(str=="TYPE_INDEX"&&finger.length()>10) {
            		System.out.println("1");
            	}else if(str=="TYPE_INDEX"&&str=="TYPE_MIDDLE") {
            		System.out.println("2");
            	}*/
            
            
            for (Finger finger : hand.fingers()) {
              	Type fingertyp=finger.type();
            	Enum fingerenum=fingertyp;
            	String fingerstr=fingerenum.toString();
            	com.leapmotion.leap.Bone.Type[] bonetyp=Bone.Type.values();
            	
            	
            	if(fingerstr=="TYPE_INDEX") {  //검지 추출
            	System.out.println("    " + finger.type());
                //Get Bones
                for(int i=0;i<1;i++) {
                    Bone bone = finger.bone(Bone.Type.TYPE_DISTAL); // TYPE_DISTAL=손가락 끝 뼈
                    System.out.println("      " + bone.type()
                                     + ", end: " + bone.nextJoint()
                                     + ", direction: " + bone.direction());
                }
            }
            	if(fingerstr=="TYPE_MIDDLE") {  //중지 추출
                	System.out.println("    " + finger.type());
                    //Get Bones
                    for(int i=0;i<1;i++) {
                    	
                        Bone bone = finger.bone(Bone.Type.TYPE_DISTAL);
                        System.out.println("      " + bone.type()
                                         + ", end: " + bone.nextJoint()
                                         + ", direction: " + bone.direction());
                    }
                }
        }}

        if (!frame.hands().isEmpty()) {
            System.out.println();
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
