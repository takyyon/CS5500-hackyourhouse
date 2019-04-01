package hackyourhouse.model;

public class User {
	protected String UserName;
	protected String Password;
	protected String FirstName;
	protected String LastName;
	protected String Email;
	protected String Type;
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public User(String userName, String password, String firstName, String lastName, String email, String type) {
		super();
		UserName = userName;
		Password = password;
		FirstName = firstName;
		LastName = lastName;
		Email = email;
		Type = type;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	
	
	
}
