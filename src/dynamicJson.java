import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReUsableMethods;
import files.payload;


public class dynamicJson {

	@Test(dataProvider="BooksData")
	public void  addBook(String isbn,String aisle) {
		
		RestAssured.baseURI="http://216.10.245.166";
		String Response =given().log().all().header("Content-Type","application/json")
				//dynamically build json payload with external data  input  json
		.body((payload.addBook(isbn,aisle)))
		.when()
		.post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js = ReUsableMethods.rawToJson(Response);
		String id = js.get("ID");
		System.out.println(id);	
	}
	
	@DataProvider(name="BooksData")
	public   Object[][] getData()
	{
	//array =collection of  elements
		//multidimessional  array =collection if  arryas 
		return new  Object[][] {{"sadsd","1233"},{"sadyysd","71233"},{"uytt","12363"}};
		
	}
	
	
}
