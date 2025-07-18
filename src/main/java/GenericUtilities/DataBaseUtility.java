package GenericUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DataBaseUtility {
	public Connection con;

	/**
	 * This method is used to fetch the data from data base
	 * @param url
	 * @param un
	 * @param pwd
	 * @param query
	 * @return ResultSet
	 * @throws SQLException
	 */
	public ResultSet fetchingDataFromDataBaseTest(String url, String un, String pwd,String query) throws SQLException {
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		con=DriverManager.getConnection(url,un,pwd);		
		Statement stmt=con.createStatement();
		ResultSet result=stmt.executeQuery(query);
		return result;
		
	}
	
	/**
	 * This method is used to fetch data from database
	 * @param query
	 * @return ResultSet
	 * @throws SQLException
	 */
	public ResultSet fetchingDataFromDataBaseTest(String query) throws SQLException {
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/advanceselenium","root","root");		
		Statement stmt=con.createStatement();
		ResultSet result=stmt.executeQuery(query);
		return result;
		
	}
	
	/**
	 * Using this method we can write back the data to database
	 * @param query
	 * @return int
	 * @throws SQLException
	 */
	public int writeBackDataToDataBase(String query) throws SQLException {
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/advanceselenium","root","root");		
		Statement stmt=con.createStatement();		
		int result=stmt.executeUpdate(query);
		return result;
	}
	
	/**
	 * this method is used to close the data base connection
	 * @throws SQLException
	 */
	public void closeDataBase() throws SQLException {
		con.close();
	}
}
