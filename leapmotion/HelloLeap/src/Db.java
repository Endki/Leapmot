

import java.sql.*;

public class Db {
	
	String dburl = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";	//test->쓸 DB로 바꿔야함
	String dbid = "root";
	String dbpass = "qawsed123";
	
	Connection con;
	PreparedStatement pstmt;	//sql문에 쓸 명령어 생성
	ResultSet rs;	//DB 결과 담는 객체
	Client client;
	boolean bool;
	
	public Db() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");	//JDBC드라이버 로드
			con = DriverManager.getConnection(dburl,dbid,dbpass);
			System.out.println("Mysql Server Connected.\n");
			
		}
		catch(Exception e) {
			System.out.println("Mysql Server Connection Failed.\n");
			e.printStackTrace();
		}
		
	}
	
	
	public int add(Client client) {
		int i = 0;
		try {
			con = DriverManager.getConnection(dburl, dbid, dbpass);
			//db 연결
			
			String sql = "insert into user values(?,?,?)";
      		//DB에 날릴 SQL문 ?에는 아래 pstmt객체의 setString으로 넣을 값을 순서대로 세팅한다.
			
			String userid = client.getId(); //client객체의 인스턴스 변수가 private로 선언되어있기 떄문에 getID,getPW로 값을 받아서 저장
			String userpw = client.getPw();
			String username = client.getName();
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, userid);
			pstmt.setString(3, userpw);
			i = pstmt.executeUpdate();
			System.out.println("Sign in Succeeded.");
			bool=true;
			//pstmt가 없데이트되면 i값이 증가.
			
		} catch (SQLException e) {
			System.out.println("Sign in Failed in DB.");
			bool=false;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			//pstmt가 비어있을 때 pstmt객체를 닫고 연결이 안되있을 때 con객체를 닫는다.
		}
		return i;
	}
	
	
	public boolean signinCheck() {
		return bool;
	}
	
	
	//타입은 후에 메인코드 따라서 변경
	public Client carry(String userid, String userpw) {
		String sql="SELECT* FROM user WHERE userid=? and userpw=?";
		try {
			con = DriverManager.getConnection(dburl, dbid, dbpass);

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, userpw);
			//select문으로 받아오기 위해 Resultset형태의 rs객체 하나 생성
			rs = pstmt.executeQuery();

			if (rs.next()) {
				 client = new Client(rs.getString("username"),rs.getString("userid"),rs.getString("userpw"));
			}
			else {
				client = new Client("a","a","a");
			}
      //rs.next로 테이블형태의 정보를 Client 클래스에서 정의한 매개변수가 있는 생성자를 객체로 생성하여 받아온 정보를 가진 client형태의 객체하나를 생성한다.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return client;
		
	}
}


