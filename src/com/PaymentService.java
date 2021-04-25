

package com;

import model.Payment;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Payments")
public class PaymentService {
	Payment paymentObj = new Payment();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayments()
	{
		return paymentObj.readPayments();
		//return "hello";
		
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(@FormParam("customerID") String customerID,
	@FormParam("customerName") String customerName,
	@FormParam("projectCode") String projectCode,
	@FormParam("paymentAmount") String paymentAmount,
	@FormParam("paymentdate") String paymentdate
	)
	
	{
	String output = paymentObj.insertPayment(customerID,customerName,projectCode,paymentAmount,paymentdate);
	return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(String paymentData)
	{
	//Convert the input string to a JSON object
	JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject();
	//Read the values from the JSON object
	String paymentID = paymentObject.get("paymentID").getAsString();
	String customerID = paymentObject.get("customerID").getAsString();
	String customerName = paymentObject.get("customerName").getAsString();
	String projectCode = paymentObject.get("projectCode").getAsString();
	String paymentAmount = paymentObject.get("paymentAmount").getAsString();
	String paymentdate = paymentObject.get("paymentdate").getAsString();
	
	String output = paymentObj.updatePayment(paymentID,customerID, customerName, projectCode, paymentAmount,paymentdate);
	return output;
	}
	
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFunding(String paymentData)
		{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());
		//Read the value from the element <funding id>
		String paymentID = doc.select("paymentID").text();
		String output = paymentObj.deleteFunding(paymentID);
		return output;
		}

	
}

