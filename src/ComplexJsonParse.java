import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	
	public static void main(String[]args) {
		JsonPath  js = new  JsonPath(payload.coursePrize());
		//print number  of courses  returned by API
	
		int count = js.getInt("courses.size()");
		System.out.println(count);
		
		
		//print purchase  amount
		
		int totalAmount = js.get("dashboard.purchaseAmount");
		System.out.println(totalAmount);
		
		
		//print title  of  the  first course 
		
		String firstCourseTitle = js.get("courses[0].title");
		System.out.println(firstCourseTitle);
		
		
		//print all courses titles and  their respective  price 
		
		for(int i=0;i<count;i++) 
		{
			String courseWithTitle = js.get("courses["+i+"].title");
		    System.out.println(js.get("courses["+i+"].price").toString());
			System.out.println(courseWithTitle);
			
		}
		
		System.out.println("print no of  copies  old  by RPA course");
		
		for(int i=0;i<count;i++) 
		{
			String courseWithTitle = js.get("courses["+i+"].title");
			if(courseWithTitle.equalsIgnoreCase("RPA"))
			{
				int copies = js.get("courses["+i+"].copies");
				System.out.println(copies);
				break;
			}
		}
		
	}

}
