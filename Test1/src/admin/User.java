package admin;

public class User {
	
	// public String type;
	public String username;
	public String password;
//	public String usernameStaff;
//	public String passwordStaff;;
	
	public User()
	{
		
	}
	
	public User(String sUserName, String sPassword, String sRole)
	{
		switch (sRole) {
		
//		case "staff":	
//			this.usernameStaff = sUserName;
//			this.passwordStaff = sPassword;
//			break;		
		default:
			this.username = sUserName;
			this.password = sPassword;
			break;
		}
	}
}