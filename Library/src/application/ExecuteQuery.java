package application;
import java.sql.*;
public class ExecuteQuery {
	public ResultSet query(String q)
	{
		try {
			Class.forName("java.sql.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost/library","root","password");
			ResultSet r=c.createStatement().executeQuery(q);
			return r;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return null;
		}
	}
		public void update(String q)
		{
			try {
				Class.forName("java.sql.Driver");
				Connection c=DriverManager.getConnection("jdbc:mysql://localhost/library","root","password");
				c.createStatement().executeUpdate(q);
				c.close();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			
	}
}
