package files;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import org.testng.Assert;

public class Dynamic_json 
{
	@Test
	public void addBook()
	{
		String Response = RestAssured.baseURI ="http://216.10.245.166";
		given().log().all().header("Content-Type","application/json").
		body(PayLoads.AddBook()).
		when()
		.post("/Library/Addbook.php")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js = ReUsableMethods.rawToJson(Response);
		String ID = js.get("ID");
		System.out.println(ID);
	}

}
