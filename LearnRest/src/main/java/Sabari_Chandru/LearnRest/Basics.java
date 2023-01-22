package Sabari_Chandru.LearnRest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import files.PayLoads;
/**
 * Hello world!
 *
 */
public class Basics 
{
    public static void main( String[] args )
    {
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
    	
    	JsonPath js = new JsonPath(response); // for parsing Jsn
    	
    	String place_id = js.getString("place_id");  
    	
    	System.out.println("*******************************************");
    	System.out.println();
    	
    	System.out.println(place_id);
    	
    }
}
