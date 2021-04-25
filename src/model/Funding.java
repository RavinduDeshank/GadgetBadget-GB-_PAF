package model;

import java.sql.*;

public class Funding
	{ 	//A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?serverTimezone=UTC", "root", "");
		}
		catch (Exception e)
		{e.printStackTrace();}
		return con;
	}
	
	
public String insertFunding(String Scode, String Sname, String Pcode, String Pname, String Amount, String Status)
{
	String output = "";
	try
	{
		Connection con = connect();
		
		if (con == null)
		{return "Error while connecting to the database for inserting."; }
		
		// create a prepared statement
		String query = " insert into fundings (`fundingID`,`sponserCode`,`sponserName`,`projectCode`,`projectName`,`fundingAmount`,`fundingStatus`)"
						+ " values (?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		// binding values
		preparedStmt.setInt(1, 0);
		preparedStmt.setString(2, Scode);
		preparedStmt.setString(3, Sname);
		preparedStmt.setString(4, Pcode);
		preparedStmt.setString(5, Pname);
		preparedStmt.setDouble(6, Double.parseDouble(Amount));
		preparedStmt.setString(7, Status);
		
		// execute the statement

		preparedStmt.execute();
		con.close();
		output = "Inserted successfully";
	}
	catch (Exception e)
	{
		
		output = "Error while inserting the funding.";
		System.err.println(e.getMessage());
	}
	return output;
}

public String readFundings()
{
	String output = "";
	try
	{
		
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for reading."; }

		// Prepare the html table to be displayed
		output = "<table border='1'><tr><th>Sponser Code</th><th>Sponser Name</th>" +
				"<th>Project Code</th>" +
				"<th>Project Name</th>" +
				"<th>Amount </th>" +
				"<th>Funding Status</th>" +
				"<th>Update</th><th>Remove</th></tr>";
		
		String query = "select * from fundings";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		// iterate through the rows in the result set
		while (rs.next())
		{

				String fundingID = Integer.toString(rs.getInt("fundingID"));
				String sponserCode = rs.getString("sponserCode");
				String sponserName = rs.getString("sponserName");
				String projectCode = rs.getString("projectCode");
				String projectName = rs.getString("projectName");
				String fundingAmount = Double.toString(rs.getDouble("fundingAmount"));
				String fundingStatus = rs.getString("fundingStatus");

				// Add into the html table
				output += "<tr><td>" + sponserCode + "</td>";
				output += "<td>" + sponserName + "</td>";
				output += "<td>" + projectCode + "</td>";
				output += "<td>" + projectName + "</td>";
				output += "<td>" + fundingAmount + "</td>";
				output += "<td>" + fundingStatus + "</td>";

				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' "+"class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='fundings.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
						+ "<input name='fundingID' type='hidden' value='" + fundingID
						+ "'>" + "</form></td></tr>";
		}
		con.close();
			
		// Complete the html table
		output += "</table>";
	}
	catch (Exception e)
	{
		output = "Error while reading the Fundings.";
		System.err.println(e.getMessage());
	}
	return output;
}
public String updateFunding(String ID, String Scode, String Sname, String Pcode, String Pname, String Amount, String Status)

{
	String output = "";
	try
	{
		Connection con = connect();
		
		if (con == null)
		{return "Error while connecting to the database for updating."; }

		// create a prepared statement
		String query = "UPDATE fundings SET sponserCode=?,sponserName=?,projectcode=?,projectName=? ,fundingAmount=? ,fundingStatus=? WHERE fundingID=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);

		// binding values
		preparedStmt.setString(1, Scode);
		preparedStmt.setString(2, Sname);
		preparedStmt.setString(3, Pcode);
		preparedStmt.setString(4, Pname);
		preparedStmt.setDouble(5, Double.parseDouble(Amount));
		preparedStmt.setString(6, Status);
		preparedStmt.setInt(7, Integer.parseInt(ID));

		// execute the statement
		preparedStmt.execute();
		con.close();
		
		output = "Updated successfully";
	}
	catch (Exception e)
	{
		output = "Error while updating the funding.";
		System.err.println(e.getMessage());
	}
	return output;
}

public String deleteFunding(String fundingId)
{
	String output = "";
	try
	{
		Connection con = connect();
		
		if (con == null)
		{return "Error while connecting to the database for deleting."; }

		// create a prepared statement
		String query = "delete from fundings where fundingID=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		// binding values
		preparedStmt.setInt(1, Integer.parseInt(fundingId));

		// execute the statement
		preparedStmt.execute();
		con.close();
		
		output = "Deleted successfully";
	}
	catch (Exception e)
	{
		output = "Error while deleting the funding.";
		System.err.println(e.getMessage());
	}
	return output;
}


}