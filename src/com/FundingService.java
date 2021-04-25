

package com;

import model.Funding;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Fundings")
public class FundingService {
	Funding fundingObj = new Funding();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readFundings()
	{
		return fundingObj.readFundings();
		//return "hello";
		
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertFunding(@FormParam("sponserCode") String sponserCode,
	@FormParam("sponserName") String sponserName,
	@FormParam("projectCode") String projectCode,
	@FormParam("projectName") String projectName,
	@FormParam("fundingAmount") String fundingAmount,
	@FormParam("fundingStatus") String fundingStatus
	)
	
	{
	String output = fundingObj.insertFunding(sponserCode,sponserName,projectCode,projectName,fundingAmount,fundingStatus);
	return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateFunding(String fundingData)
	{
	//Convert the input string to a JSON object
	JsonObject fundingObject = new JsonParser().parse(fundingData).getAsJsonObject();
	//Read the values from the JSON object
	String fundingID = fundingObject.get("fundingID").getAsString();
	String sponserCode = fundingObject.get("sponserCode").getAsString();
	String sponserName = fundingObject.get("sponserName").getAsString();
	String projectCode = fundingObject.get("projectCode").getAsString();
	String projectName = fundingObject.get("projectName").getAsString();
	String fundingAmount = fundingObject.get("fundingAmount").getAsString();
	String fundingStatus = fundingObject.get("fundingStatus").getAsString();
	
	String output = fundingObj.updateFunding(fundingID,sponserCode, sponserName, projectCode, projectName, fundingAmount, fundingStatus);
	return output;
	}
	
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFunding(String fundingData)
		{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(fundingData, "", Parser.xmlParser());
		//Read the value from the element <funding id>
		String fundingID = doc.select("fundingID").text();
		String output = fundingObj.deleteFunding(fundingID);
		return output;
		}

	
}

