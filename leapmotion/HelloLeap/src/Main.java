import java.io.IOException;
import javax.swing.JFrame;
import com.leapmotion.leap.*;

public class Main {
    LoginView loginView;
	public static void main(String[] args) {
		
		//UI
        Main main = new Main();
        main.loginView = new LoginView(); // �α���â ���̱�
        main.loginView.setMain(main); // �α���â���� ���� Ŭ����������
	}

}