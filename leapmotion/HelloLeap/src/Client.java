public class Client {
	
	private String name;
	private String id;
	private String pw;
	

	public Client(String name, String id, String pw) {
		this.name = name;
		this.id = id;
		this.pw = pw;
	}
	
  //DB에서 정보를 객체로 받아오기위해서 Client 생성자를 만든다.
	public Client() {

	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String toString() {
		return "id : " +id ;
	}
}
