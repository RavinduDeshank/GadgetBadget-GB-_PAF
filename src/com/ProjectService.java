package com;

import model.Project;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document; 

@Path("/Projects")
public class ProjectService {

	Project projectObj = new Project();
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readProjects() 
	 { 
	 return projectObj.readProjects(); 
	 }

	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertProject(@FormParam("projectCode") String projectCode, 
	 @FormParam("projectTitle") String projectTitle, 
	 @FormParam("projectManager") String projectManager, 
	 @FormParam("projectCategory") String projectCategory) 
	{ 
	 String output = projectObj.insertProject(projectCode, projectTitle, projectManager, projectCategory); 
	return output; 
	}

	
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateProject(String projectData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject projectObject = new JsonParser().parse(projectData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String projectID = projectObject.get("projectID").getAsString(); 
	 String projectCode = projectObject.get("projectCode").getAsString(); 
	 String projectTitle = projectObject.get("projectTitle").getAsString(); 
	 String projectManager = projectObject.get("projectManager").getAsString(); 
	 String projectCategory = projectObject.get("projectCategory").getAsString(); 
	 String output = projectObj.updateProject(projectID, projectCode, projectTitle, projectManager, projectCategory); 
	return output; 
	}

	
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteProject(String projectData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(projectData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String projectID = doc.select("projectID").text(); 
	 String output = projectObj.deleteProject(projectID); 
	return output; 
	}


	
}
