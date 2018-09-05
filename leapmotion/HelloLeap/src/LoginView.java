import javax.swing.*;
import com.leapmotion.leap.Controller;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {

    private Main main;
    
    private JButton btnLogin;
    private JButton btnInsert;
    private JButton btnPut;
    //private JPasswordField passText;
    private JTextField userText;
    private JTextField passText;
    
    
    public LoginView() {
        //initComponents();
        
        setTitle("login");
        setSize(400, 150);  //300,150
        setResizable(false);
        setLocation(800, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // panel
        JPanel panel = new JPanel();
        placeLoginPanel(panel);
        
        // add
        add(panel);
        
        // visiible
        setVisible(true);
    }
    
    
    public void placeLoginPanel(JPanel panel){
        panel.setLayout(null);     
        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);
        
        JLabel passLabel = new JLabel("Pass");
        passLabel.setBounds(10, 40, 80, 25);
        panel.add(passLabel);
        
        userText = new JTextField(20);
        userText.setBounds(100, 10, 160, 25);
        panel.add(userText);
        userText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isLoginCheck();
            }
        });
        
        
        //passText = new JPasswordField(20);
        passText = new JTextField(20);
        passText.setBounds(100, 40, 160, 25);
        panel.add(passText);
        passText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isLoginCheck();
            }
        });
        
        
        btnInsert = new JButton("sign in");
        btnInsert.setBounds(10, 80, 100, 25);
        panel.add(btnInsert);
        btnInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Insert insert = new Insert();
            }
        });
        
        btnLogin = new JButton("login");
        btnLogin.setBounds(160, 80, 100, 25);
        panel.add(btnLogin);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isLoginCheck();
            }
        });
        
        
        btnPut = new JButton("�Է�");
        btnPut.setBounds(280, 40, 100, 25);  //100,40,100,25
        panel.add(btnPut);
        btnPut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	LeapListener listener = new LeapListener();
            	
        		for(listener.passnum=0;listener.passnum<4;) {
        			if(Main.TF==false) {
        				break;
        			}
        			
            		Controller controller = new Controller();
            		
            		listener.onInit(controller);
        			listener.onConnect(controller);
        			while(!listener.passnumIsChanged) {
        				
        				listener.gesturecheck();
        				listener.onFrame(controller);
        				//passText.setText(listener.PassWord());
        				passText.setText(listener.PassWord());
        				
        				if(listener.passnumIsChanged) {
        					JOptionPane.showMessageDialog(null, "�Է¼���");
        					listener.passnumIsChanged = false;
        					if((listener.passnum>=4)) {
        						listener.passnumIsChanged=true;
        					}
        				}
        			}
        			controller.removeListener(listener);
        		}
        		Main.TF=true;
        		// Remove the sample listener when done
        		//controller.removeListener(listener);
            }
        });
    }
    
    


    public void isLoginCheck(){
    	
    	Db db = new Db();
    	String userid = userText.getText();
		String userpw = passText.getText();
		Client clientInfo = db.carry(userid, userpw);
		
		if(clientInfo.getId().equals(userid) && clientInfo.getPw().equals(userpw)) {
			System.out.println("login succeeded.");
			JOptionPane.showMessageDialog(null, "�α��� �Ǿ����ϴ�.");
			dispose();
		}
		else {
			System.out.println("login failed.");
			JOptionPane.showMessageDialog(null, "�α��� �����Ͽ����ϴ�.");
		}
		
		userText.setText("");
        passText.setText("");
    }

    
    
    

    public void setMain(Main main) {
        this.main = main;
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginView().setVisible(true);
            }
        });
    }
                
}
