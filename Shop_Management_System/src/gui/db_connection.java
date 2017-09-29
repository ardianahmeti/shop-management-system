package gui;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;


public class db_connection 
{
		Connection conn=null;
		
		public static Connection connectDB()
		{
			try 
			{
	
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn=DriverManager.
				getConnection("jdbc:mysql://localhost/shop_db?verifyServerCertificate=false&useSSL=true","root","805198");
				
				
				return conn;	
			} 
			catch (Exception e) 
			{
				JOptionPane.showMessageDialog(null, "Error :"+e.getMessage());
				return null;
		}
	}
	
	
}