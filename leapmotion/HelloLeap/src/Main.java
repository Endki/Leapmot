import java.io.IOException;
import javax.swing.JFrame;
import com.leapmotion.leap.*;

public class Main {
    LoginView loginView;
	public static void main(String[] args) {
		
		//UI
        Main main = new Main();
        main.loginView = new LoginView(); // 로그인창 보이기
        main.loginView.setMain(main); // 로그인창에게 메인 클래스보내기
	}

}
