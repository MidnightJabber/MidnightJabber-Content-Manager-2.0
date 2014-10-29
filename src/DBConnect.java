import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Name: User
 *
 */
class User{
	
	int id;
	String name;
	String pass;
	String type;
	
	public User(){
		
	}
	
};

public class DBConnect {
	private Connection con;
	private static Statement st;	//for queries
	private static ResultSet rs;	//this will hold results from queries
	ArrayList<User> users = new ArrayList<User>();
	
	public DBConnect(){
		try{
			//Register Driver
			//returns the Class object associated with the class or interface with the given string name.
			Class.forName("com.mysql.jdbc.Driver");
			
			String URL = "jdbc:mysql://(REMOTE-IP)/(DB_NAME)";
			String USER = "-DB-USER-NAME-";
			String PASS = "-DB-PASSWORD-";
			con = DriverManager.getConnection(URL, USER, PASS);
			
			//Connection Successful
			System.out.println("Database connection established");
			
			st = con.createStatement();
		
		}catch(Exception e){
			System.out.println("ERROR: "+e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Name: grabAllData
	 */
	public void grabAllData(){
		try{
			String query;
			query = "SELECT * FROM Contributors;";
			rs = st.executeQuery(query);
			while(rs.next()){
				User temp = new User();
				String name = rs.getString("name");
				String pass = rs.getString("password");
				String type = rs.getString("type");
				temp.name = name;
				temp.pass = pass;
				temp.type = type;
				users.add(temp);
			}
		}catch(Exception e){
			System.out.println("ERROR: "+e);
		}
	}
	
	public void displayDBOnConsole(){
		grabAllData();
		for(int i = 0; i < users.size(); i++){
			System.out.println("Name: "+users.get(i).name+" Password: "+users.get(i).pass+" Type: "+users.get(i).type);
		}
	}

	//static function (does'nt need an object to call)
	public boolean verifyUser(String userEntry, String passEntry, String typeEntry){
		grabAllData();
		try{
			//Finding a match
			for(int i = 0; i < users.size(); i++){			
				if(users.get(i).name.equalsIgnoreCase(userEntry) && users.get(i).pass.equals(passEntry) && users.get(i).type.equalsIgnoreCase(typeEntry)){
					return true;
				}
			}
			
			return false;
		}catch(Exception e){
			System.out.println("ERROR: "+e);
			return false;
		}
	}
};

