package learnrestbasics;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import org.testng.Assert;

import files.PayLoads;
/**
 * Hello world!
 *
 */
public class Basics 
{
    public static void main( String[] args )
    {
    	//Add Place
    	//validate if add place api is working as expected
        //given - all inputs details
    	//when - submit the API - resource and http method
    	//Then - validate the response
    	RestAssured.baseURI = "https://rahulshettyacademy.com";
    	String response = given().log().all().queryParam("key", "qaclick123").header("Contetn-Type","application/json")
    	.body(PayLoads.AddPlace())
    	.when().post("maps/api/place/add/json")
    	.then().assertThat().statusCode(200)
    	//.body("scope", equalTo("APP"));
    	.header("server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
    	
    	
    	System.out.println("*******************************************");
    	
    	
    	System.out.println(response);
    	//add place _>update place  with new address -> get place to validate if new address i present in response
    	//it involve 3 apis
    	
    	JsonPath js = new JsonPath(response); // for parsing Json
    	
    	String placeid = js.getString("place_id");  
    	
    	System.out.println("*******************************************");
    	System.out.println();
    	
    	System.out.println(placeid);
    	System.out.println("***********************************************");
    	System.out.println();
    
    	//update PLace
    	String newaddress = "Summer Walk, Africa";
    	
        given().log().all().queryParam("key", "qaclick").header("Contetn-Type","application/json")
        .body("{\r\n"
        		+ "\"place_id\":\""+placeid+"\",\r\n"
        		+ "\"address\":\""+newaddress+"\",\r\n"
        		+ "\"phone_number\":\"(+91) 983 893 3937\",\r\n"
        		+ " \"key\":\"qaclick123\"\r\n"
        		+ "}")
        	
        .when().put("maps/api/place/update/json")
        .then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
	 
        System.out.println();
        System.out.println("*****************************************************");
        
        //GetPlace
        
        String getplaceresponse = given().log().all().queryParam("key", "qaclick123")
        .queryParam("place_id", placeid)
        .when().get("maps/api/place/get/json")
        .then().assertThat().log().all().statusCode(200).extract().response().asString();
        
        JsonPath js1 = new JsonPath(getplaceresponse); // for parsing Json
        
       String actualaddress =  js1.get(getplaceresponse);
       
       Assert.assertEquals(actualaddress, newaddress);
       
       System.out.println(actualaddress);
       
       //Cucumber  Junit and testng
    }
    

  
}



	