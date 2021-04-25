package model;

import java.sql.*; 

public class Project {

	//A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");

			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
		}
        catch (Exception e)
			{e.printStackTrace();}
			return con;
		}
	
	public String insertProject(String code, String title, String manager,  String category)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for inserting."; 
			}


			// create a prepared statement
			String query = " insert into projects(`projectID`,`projectCode`,`projectTitle`,`projectManager`,`projectCategory` )" + " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, code);
			preparedStmt.setString(3, title);
			preparedStmt.setString(4, manager);
			preparedStmt.setString(5, category);



			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting the researcher.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	public String readProjects() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Project Code</th><th>Project Title</th>" +
	 "<th>Project Manager</th>" + 
	 "<th>Project Category</th>" +
	 "<th>Update</th><th>Remove</th></tr>"; 
	 
	 String query = "select * from projects"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String projectID = Integer.toString(rs.getInt("projectID")); 
	 String projectCode = rs.getString("projectCode"); 
	 String projectTitle = rs.getString("projectTitle"); 
	 String projectManager = rs.getString("projectManager"); 
	 String projectCategory = rs.getString("projectCategory"); 
	 // Add into the html table
	 output += "<tr><td>" + projectCode + "</td>"; 
	 output += "<td>" + projectTitle + "</td>"; 
	 output += "<td>" + projectManager + "</td>"; 
	 output += "<td>" + projectCategory + "</td>"; 
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" + "<td><form method='post' action='items.jsp'>"
	 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 + "<input name='itemID' type='hidden' value='" + projectID + "'>" + "</form></td></tr>"; 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the items."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	
	public String updateProject(String ID, String code, String title, String manager, String category)
	{ 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 // create a prepared statement
		 String query = "UPDATE projects SET projectCode=?,projectTitle=?,projectManager=?,projectCategory=? WHERE projectID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, code); 
		 preparedStmt.setString(2, title); 
		 preparedStmt.setString(3, manager); 
		 preparedStmt.setString(4, category); 
		 preparedStmt.setInt(5, Integer.parseInt(ID)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while updating the item."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
	
		public String deleteProject(String projectID) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for deleting."; } 
		 // create a prepared statement
		 String query = "delete from projects where projectID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(projectID)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Deleted successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while deleting the item."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
}
