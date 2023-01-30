package learnrestbasics;

import files.PayLoads;
import io.restassured.path.json.JsonPath;

public class Complex_Json_parse {

	public static void main(String[] args) {
		JsonPath js =new JsonPath(PayLoads.CouresePrice());
		//print count of courses
		int count = js.getInt("courses.size()");
		System.out.println("total number of courses : "+count);
		
		//print the total amount
		
		int totalAmount  = js.getInt("dashboard.purchaseAmount");
		System.out.println("total purcase amount : "+totalAmount);
		
		//print the title of the first course
		
		String course_title = js.get("courses[0].title");
		System.out.println("Title of the course is : " +course_title);
		
		//dynamic array
		
		for (int i = 0; i < count; i++) {
			
			String coursetitles = js.get("courses["+i+"].title");
			System.out.println("The couese titles is :"+coursetitles);

			String courseprice =js.get("courses["+i+"].price").toString();
			
			System.out.println("The couese prcices is :"+courseprice);
			
		}
		
		
		//to get a specific value even if the tag changes due to developer change in the future
		
		System.out.println("Print No of copies sold by RPA course");
		
		
		for (int i = 0; i < count; i++) 
		{
			String coursetitles = js.get("courses["+i+"].title");
			if(coursetitles.equalsIgnoreCase("RPA"))
					{
						int copies = js.get("courses["+i+"].copies");
						System.out.println(copies);
						break;
					}
		}
	}
	
}
