package practicedatadriven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;

public class readfromdatabase {

	public static void main(String[] args) throws SQLException {
		String expectedname="Henry Wilson";
		boolean flag=false;
		//step 1
				/*
				 * Driver dbDriver=new Driver();
				 * 
				 * DriverManager.registerDriver(dbDriver);
				 */
				
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/advanceselenium","root","root");
				Statement statement=connection.createStatement();
				ResultSet result=statement.executeQuery("select * from Employees;");
				while(result.next()) {
					String actname=result.getString(2);
					
				if(expectedname.equals(actname)) {
					flag=true;
					System.out.println(expectedname+" is available===PASS");
				}
				}
				if(flag==false) {
					System.out.println(expectedname+" is available===FAIL");
					Assert.fail();
				}	

		connection.close();
		System.out.println("closed the connection");
	}
	}
	

