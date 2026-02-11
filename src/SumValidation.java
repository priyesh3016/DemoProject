import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {
  @Test
  public void sumOfCourses()
  {
	  int sum = 0;
	  JsonPath js =new JsonPath(payload.coursePrize());
	 int count = js.getInt("courses.size()");
	  for(int i=0;i<count;i++) {
		  //get courses 
		 int price =  js.getInt("courses["+i+"].price");
		 // get  copies 
		 int copies =   js.getInt("courses["+i+"].copies");
		 int amount = price * copies;
		   sum = sum + amount;  
	  }
	  
	  System.out.println(sum);
	  int purchaseAmount = js.getInt("dashboard.purchaseAmount");
	  Assert.assertEquals(sum, purchaseAmount);
  }
 
}
