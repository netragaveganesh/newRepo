package DDT_HandlingDataBase;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class FetchingDataFrom_DataBase {

	public static void main(String[] args) throws SQLException {
		
		// Create driver
		Driver driver = new Driver();
		
		//Register driver
		DriverManager.registerDriver(driver);
		
		//Connect to daatbase
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/advanceselenium","root","root");
		
		//Create statement
		Statement stmt=con.createStatement();
		
		//Fectch data from database using commands
		ResultSet result=stmt.executeQuery("select * from seldata;");
		
		while(result.next()){
		System.out.println(result.getString(1)+"\t"+result.getString(2)+"\t"+result.getString(3));
		
		}
		
		con.close();

	}

}
