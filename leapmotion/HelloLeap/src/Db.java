

import java.sql.*;

public class Db {
	
	String dburl = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";	//test->�� DB�� �ٲ����
	String dbid = "root";
	String dbpass = "qawsed123";
	
	Connection con;
	PreparedStatement pstmt;	//sql���� �� ��ɾ� ����
	ResultSet rs;	//DB ��� ��� ��ü
	Client client;
	boolean bool;
	
	public Db() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");	//JDBC����̹� �ε�
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
			//db ����
			
			String sql = "insert into user values(?,?,?)";
      		//DB�� ���� SQL�� ?���� �Ʒ� pstmt��ü�� setString���� ���� ���� ������� �����Ѵ�.
			
			String userid = client.getId(); //client��ü�� �ν��Ͻ� ������ private�� ����Ǿ��ֱ� ������ getID,getPW�� ���� �޾Ƽ� ����
			String userpw = client.getPw();
			String username = client.getName();
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, userid);
			pstmt.setString(3, userpw);
			i = pstmt.executeUpdate();
			System.out.println("Sign in Succeeded.");
			bool=true;
			//pstmt�� ������Ʈ�Ǹ� i���� ����.
			
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
			//pstmt�� ������� �� pstmt��ü�� �ݰ� ������ �ȵ����� �� con��ü�� �ݴ´�.
		}
		return i;
	}
	
	
	public boolean signinCheck() {
		return bool;
	}
	
	
	//Ÿ���� �Ŀ� �����ڵ� ���� ����
	public Client carry(String userid, String userpw) {
		String sql="SELECT* FROM user WHERE userid=? and userpw=?";
		try {
			con = DriverManager.getConnection(dburl, dbid, dbpass);

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, userpw);
			//select������ �޾ƿ��� ���� Resultset������ rs��ü �ϳ� ����
			rs = pstmt.executeQuery();

			if (rs.next()) {
				 client = new Client(rs.getString("username"),rs.getString("userid"),rs.getString("userpw"));
			}
			else {
				client = new Client("a","a","a");
			}
      //rs.next�� ���̺������� ������ Client Ŭ�������� ������ �Ű������� �ִ� �����ڸ� ��ü�� �����Ͽ� �޾ƿ� ������ ���� client������ ��ü�ϳ��� �����Ѵ�.
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


