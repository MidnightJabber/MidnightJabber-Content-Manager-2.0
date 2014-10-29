

public class MidnightJabber{
	public static void main(String[] args){
		
		//Establishing DB Connection
		DBConnect connect = new DBConnect();
		
		//Creating object for the initial Login Authorization
		LoginForm authorization = new LoginForm();
		//Displaying the initial Authorization Window
		authorization.display();
		
		
	}
	
}
