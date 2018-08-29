import com.leapmotion.leap.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.FileReader;
//import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;


public class Insert extends javax.swing.JFrame {
    
    private JButton btnPass1;
    private JButton btnPass2;
    private JButton btnOk;
    private JButton btnCancel;
    private JPasswordField passText1;
    private JPasswordField passText2;
    //private JTextField passText1;
    //private JTextField passText2;
    private JTextField nameText;
    private JTextField idText;
    //private boolean bPassCheck;

    
    public Insert() {
        setTitle("sign in");
        setSize(400, 250); //280,200 
        setResizable(false);
        setLocation(800, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // panel
        JPanel panel = new JPanel();
        placeSigninPanel(panel);
        
        // add
        add(panel);
        
        // visiible
        setVisible(true);
    }
    
    
    public void placeSigninPanel(JPanel panel){
        panel.setLayout(null);   
        
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(10, 10, 80, 25);
        panel.add(nameLabel);
        
        JLabel idLabel = new JLabel("ID");
        idLabel.setBounds(10, 40, 80, 25);
        panel.add(idLabel);
        
        JLabel passLabel1 = new JLabel("Pass");
        passLabel1.setBounds(10, 80, 80, 25);
        panel.add(passLabel1);
        
        JLabel passLabel2 = new JLabel("Pass check");
        passLabel2.setBounds(10, 120, 80, 25);
        panel.add(passLabel2);
        
        nameText = new JTextField(20);
        nameText.setBounds(100, 10, 160, 25);
        panel.add(nameText);
        nameText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Save();
            }
        });
        
        idText = new JTextField(20);
        idText.setBounds(100, 40, 160, 25);
        panel.add(idText);
        idText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Save();
            }
        });
        
        passText1 = new JPasswordField(20);
        //passText1 = new JTextField(20);
        passText1.setBounds(100, 80, 160, 25);
        passText1.setText(null);
        panel.add(passText1);
        passText1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Save();
            }
        });
        
        passText2 = new JPasswordField(20);
        //passText2 = new JTextField(20);
        passText2.setBounds(100, 120, 160, 25);
        panel.add(passText2);
        passText2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Save();
            }
        });
        
        btnPass1 = new JButton("입력");
        btnPass1.setBounds(270, 80, 100, 25); //100,80,100,25
        panel.add(btnPass1);
        btnPass1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
	SampleListener listener = new SampleListener();
            	
        		for(listener.passnum=0;listener.passnum<4;) {
            		Controller controller = new Controller();
            		controller.addListener(listener);
            		listener.onInit(controller);
        			listener.onConnect(controller);
        			while(!listener.passnumIsChanged) {
        				
        				listener.onFrame(controller);
        			}
        			listener.onDisconnect(controller);
        			listener.onExit();
        		
        			//System.out.println("Loginview Password"+listener.PassWord());
        				passText1.setText(listener.PassWord());
        				if(listener.passnumIsChanged) {
        					JOptionPane.showMessageDialog(null, "입력성공");
        				}
        			listener.onInit(controller);
        			controller.removeListener(listener);
        		}
            }
        });
        
        btnPass2 = new JButton("입력");
        btnPass2.setBounds(270, 120, 100, 25); //100,80,100,25
        panel.add(btnPass2);
        btnPass2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
	SampleListener listener = new SampleListener();
            	
        		for(listener.passnum=0;listener.passnum<4;) {
            		Controller controller = new Controller();
            		controller.addListener(listener);
            		listener.onInit(controller);
        			listener.onConnect(controller);
        			while(!listener.passnumIsChanged) {
        				
        				listener.onFrame(controller);
        			}
        			listener.onDisconnect(controller);
        			listener.onExit();
        		
        			//System.out.println("Loginview Password"+listener.PassWord());
        				passText2.setText(listener.PassWord());
        				if(listener.passnumIsChanged) {
        					JOptionPane.showMessageDialog(null, "입력성공");
        				}
        			listener.onInit(controller);
        			controller.removeListener(listener);
        		}
            }
        });
        
        
        
        btnOk = new JButton("저장");
        btnOk.setBounds(150, 170, 100, 25);
        panel.add(btnOk);
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Save();
            }
        });
        
        btnCancel = new JButton("취소");
        btnCancel.setBounds(10, 170, 100, 25);
        panel.add(btnCancel);
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               dispose();
            }
        });
    }
    
    
    
    
    public void PassCheck(JPasswordField txt){
    	   // Create a sample listener and controller
   		SampleListener listener = new SampleListener();
   		Controller controller = new Controller();

   		// Have the sample listener receive events from the controller
   		controller.addListener(listener);

   		// Keep this process running until Enter is pressed
   		
   		try {
   			System.in.read();
   		} catch (IOException eception) {
   			eception.printStackTrace();
   		}
   		
   		
   		txt.setText(listener.PassWord());

   		// Remove the sample listener when done
   		controller.removeListener(listener);
   		
   		
    }
     
    
    public void Save() {
    	
    	//비밀번호 2개 같은지 확인, 안 쓴 textfield 있는지 확인
    	if((passText1.getText().equals(passText2.getText()))
    					&&(passText1.getText().length()!=0)
    					&&(passText2.getText().length()!=0)
    					&&(nameText.getText().length()!=0)
    					&&(idText.getText().length()!=0)) {
    		
    		String username = nameText.getText();	//원래 nameText
    		String userid = idText.getText();		//원래 idText
			String userpw = passText1.getText();	
			Client client = new Client(username, userid, userpw);
			Db db = new Db();
			db.add(client);
			
			//db에 정보저장 완료시 성공
			if(db.signinCheck()) {
			JOptionPane.showMessageDialog(null, "회원가입 되었습니다.");
			dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "회원가입에 실패하였습니다.\n이미 존재하는 ID");
        		idText.setText("");
        		nameText.setText("");
        		passText1.setText("");
        		passText2.setText("");
			}
    	}else{
			if(!passText1.getText().equals(passText2.getText())){
				JOptionPane.showMessageDialog(null, "회원가입에 실패하였습니다.\n비밀번호 불일치");
			}
			else {
				JOptionPane.showMessageDialog(null, "회원가입에 실패하였습니다.\n정보를 모두 입력해주십시오.");
			}
    		idText.setText("");
    		nameText.setText("");
    		passText1.setText("");
    		passText2.setText("");
    	}
    	
    }
    
    
    
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Insert.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Insert.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Insert.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Insert.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Insert().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    // End of variables declaration                   
}
