package model;
import java.sql.*;
public class User {

		private Connection connect()
		 {
		 Connection con = null;
		 try
		 {
		 Class.forName("com.mysql.jdbc.Driver");

		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
		 }
		 catch (Exception e)
		 {e.printStackTrace();}
		 return con;
		 }
		public String insertUser(String Type, String username, String Email, String Password, String phone, String address)
	 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
		 // create a prepared statement
		 String query = " insert into user(`userID`,`userType`,`userName`,`userEmail`,`userPassword`,`userPhone`,`userAddress`)"
		 + " values (?, ?, ?, ?, ?, ?, ?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
	 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(2, Type);
		 preparedStmt.setString(3, username);
		 preparedStmt.setString(4, Email);
		 preparedStmt.setString(5, Password);
		 preparedStmt.setString(6, phone);
		 preparedStmt.setString(7, address);
		// execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "User successfully registered";
		 }
		 catch (Exception e)
		 {
		 output = "Error while inserting user.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		public String readUsers()
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for reading."; }
		 
		 output = "<table border='1'><tr><th>User Type</th><th>User Name</th>" +
		 "<th>User Email</th>" +
		 "<th>User Password</th>" +
		 "<th>User Phone</th>"+
		 "<th>User Address</th>"+
		 "<th>Update</th><th>Remove</th></tr>";

		 String query = "select * from user";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
		 String userID = Integer.toString(rs.getInt("userID"));
		 String userType = rs.getString("userType");
		 String userName = rs.getString("userName");
		 String userEmail = rs.getString("userEmail");
		 String userPassword = rs.getString("userPassword");
		 String userPhone = rs.getString("userPhone");
		 String userAddress = rs.getString("userAddress");
		 

		 output += "<tr><td>" + userType + "</td>";
		 output += "<td>" + userName + "</td>";
		 output += "<td>" + userEmail + "</td>";
		 output += "<td>" + userPassword + "</td>";
		 output += "<td>" + userPhone + "</td>";
		 output += "<td>" + userAddress + "</td>";
		 
		 
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
		 + "<td><form method='post' action='user.jsp'>"+ 
				 "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
		 + "<input name='userID' type='hidden' value='" + userID
		 + "'>" + "</form></td></tr>";
		 }
		 con.close();

		 output += "</table>";
		 }
		 catch (Exception e)
		 {
		 output = "Error while reading the users.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		public String updateUser(String ID, String Type, String username, String Email, String Password, String phone, String address)
		{
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
			 // create a prepared statement
			 String query = "UPDATE user SET userType=?,userName=?,userEmail=?,userPassword=?,userPhone=?,userAddress=? WHERE userID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setString(1, Type);
			 preparedStmt.setString(2, username);
			 preparedStmt.setString(3, Email);
			 preparedStmt.setString(4, Password);
			 preparedStmt.setString(5, phone);
			 preparedStmt.setString(6, address);
			 preparedStmt.setInt(7, Integer.parseInt(ID));
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
		
		
			public String deleteUser(String userID)
			 {
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for deleting."; }
			 // create a prepared statement
			 String query = "delete from user where userID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(userID));
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "User deleted successfully";
			 }
			 catch (Exception e)
			 {
			 output = "Error while deleting the user.";
			 System.err.println(e.getMessage());
			 }
			 return output;
			 }
			} 
