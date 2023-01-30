package learnrestbasics;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.PayLoads;
import io.restassured.path.json.JsonPath;

public class SumValidation {

	@Test
	public void SumOfCoursePeice()
	{
		int sum= 0;
		JsonPath js = new JsonPath(PayLoads.CouresePrice());
		int count = js.getInt("courses.size()");
		System.out.println("total number of courses : "+count);
		
		for (int i = 0; i < count; i++) 
		{
			int price = js.getInt("courses["+i+"].price");
			int copies = js.getInt("courses["+i+"].copies");
			
			int amount = price*copies;
			
			 sum = sum+amount;
			
			System.out.println(amount);
			
		
		}
		
		System.out.println(sum);
		int purchaseamount = js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(sum, purchaseamount);
		
	}
}
