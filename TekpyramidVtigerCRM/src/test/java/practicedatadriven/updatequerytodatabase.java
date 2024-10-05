package practicedatadriven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class updatequerytodatabase {

	public static void main(String[] args) throws SQLException {
		//step 1
		/*
		 * Driver dbDriver=new Driver();
		 * 
		 * DriverManager.registerDriver(dbDriver);
		 */
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/advanceselenium","root","root");
		Statement statement=connection.createStatement();
		int result=statement.executeUpdate("INSERT INTO Employees VALUES (NULL, 'Jane Smith', 'Project Manager', 90000.00);");
		System.out.println(result);
		connection.close();
	}

}
