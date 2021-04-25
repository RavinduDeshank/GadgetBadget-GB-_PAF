package com;
import model.User; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/user") 
public class UserService {

	User userObj = new User(); 
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readUsers() 
	{ 
		return userObj.readUsers(); 
	} 
	
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertUser(@FormParam("userType") String userType, 
	 @FormParam("userName") String userName, 
	 @FormParam("userEmail") String userEmail, 
	 @FormParam("userPassword") String userPassword,
	 @FormParam("userPhone") String userPhone, 
	 @FormParam("userAddress") String userAddress)
	
	
	{ 
		String output = userObj.insertUser(userType, userName, userEmail, userPassword, userPhone, userAddress); 
		return output; 
	}
//	
//	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateUser(String userData) 
	{ 
		//Convert the input string to a JSON object 
		JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
		//Read the values from the JSON object
		 String userID = userObject.get("userID").getAsString(); 
		 String userType = userObject.get("userType").getAsString(); 
		 String userName = userObject.get("userName").getAsString(); 
		 String userEmail = userObject.get("userEmail").getAsString(); 
		 String userPassword = userObject.get("userPassword").getAsString(); 
		 String userPhone = userObject.get("userPhone").getAsString(); 
		 String userAddress = userObject.get("userAddress").getAsString(); 
		 String output = userObj.updateUser(userID, userType, userName, userEmail, userPassword, userPhone, userAddress); 
		 return output; 
	}

	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteUser(String userData) 
	{ 
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(userData, "", Parser.xmlParser()); 
		 
		//Read the value from the element <userID>
		 String userID = doc.select("userID").text(); 
		 String output = userObj.deleteUser(userID); 
		 return output; 
	}

	
	

}


