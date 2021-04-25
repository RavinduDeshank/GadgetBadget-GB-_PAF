package model;

import java.sql.*;

public class Payment
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
	
	
public String insertPayment(String CusID,String CusName, String Pcode, String Amount, String date)
{
	String output = "";
	try
	{
		Connection con = connect();
		
		if (con == null)
		{return "Error while connecting to the database for inserting."; }
		
		// create a prepared statement
		String query = " insert into payments (`paymentID`,`customerID`,`customerName`,`projectCode`,`paymentAmount`,`paymentdate`)"
						+ " values (?, ?, ?, ?, ?, ?)";
		
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		// binding values
		preparedStmt.setInt(1, 0);
		preparedStmt.setString(2, CusID);
		preparedStmt.setString(3, CusName);
		preparedStmt.setString(4, Pcode);
		preparedStmt.setDouble(5, Double.parseDouble(Amount));
		preparedStmt.setString(6, date);
		
		// execute the statement

		preparedStmt.execute();
		con.close();
		output = "Inserted successfully";
	}
	catch (Exception e)
	{
		
		output = "Error while inserting the Payment.";
		System.err.println(e.getMessage());
	}
	return output;
}


public String readPayments()
{
	String output = "";
	try
	{
		
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for reading."; }

		// Prepare the html table to be displayed
		output = "<table border='1'><tr><th>Customer Id</th><th>Customer Name</th>" +
				"<th>Project Code</th>" +
				"<th>Payment Amount</th>" +
				"<th>Payment Date</th>" +
				"<th>Update</th><th>Remove</th></tr>";
		
		String query = "select * from payments";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		// iterate through the rows in the result set
		while (rs.next())
		{

				String paymentID = Integer.toString(rs.getInt("paymentID"));
				String customerID = rs.getString("customerID");
				String customerName = rs.getString("customerName");
				String projectCode = rs.getString("projectCode");
				String paymentAmount = Double.toString(rs.getDouble("paymentAmount"));
				String paymentdate = rs.getString("paymentdate");

				// Add into the html table
				output += "<tr><td>" + customerID + "</td>";
				output += "<td>" + customerName + "</td>";
				output += "<td>" + projectCode + "</td>";
				output += "<td>" + paymentAmount + "</td>";
				output += "<td>" + paymentdate + "</td>";
				

				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' "+"class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='payment.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
						+ "<input name='fundingID' type='hidden' value='" + paymentID
						+ "'>" + "</form></td></tr>";
		}
		con.close();
			
		// Complete the html table
		output += "</table>";
	}
	catch (Exception e)
	{
		output = "Error while reading the payments.";
		System.err.println(e.getMessage());
	}
	return output;
}
public String updatePayment(String ID, String CusID, String CusName, String Pcode, String Amount, String Date)

{
	String output = "";
	try
	{
		Connection con = connect();
		
		if (con == null)
		{return "Error while connecting to the database for updating."; }

		// create a prepared statement
		String query = "UPDATE payments SET customerID=?,customerName=?,projectcode=?,paymentAmount=? ,paymentdate=?  WHERE paymentID=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);

		// binding values
		preparedStmt.setString(1, CusID);
		preparedStmt.setString(2, CusName);
		preparedStmt.setString(3, Pcode);
		preparedStmt.setDouble(4, Double.parseDouble(Amount));
		preparedStmt.setString(5, Date);
		preparedStmt.setInt(6, Integer.parseInt(ID));

		// execute the statement
		preparedStmt.execute();
		con.close();
		
		output = "Updated successfully";
	}
	catch (Exception e)
	{
		output = "Error while updating the payment.";
		System.err.println(e.getMessage());
	}
	return output;
}

public String deleteFunding(String paymentID)
{
	String output = "";
	try
	{
		Connection con = connect();
		
		if (con == null)
		{return "Error while connecting to the database for deleting."; }

		// create a prepared statement
		String query = "delete from payments where paymentID=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		// binding values
		preparedStmt.setInt(1, Integer.parseInt(paymentID));

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