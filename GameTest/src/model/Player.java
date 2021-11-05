package model;

public class Player implements Fichavel {
	private String name, login,email,password;
	private int id;
	
	
	public Player() {}
	
	public Player(String name, String login, String email, String password) {
		super();
		this.name = name;
		this.login = login;
		this.email = email;
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String formatado() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getIdentificadorUnido() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
