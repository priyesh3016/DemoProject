import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.lang.System.Logger;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import files.ReUsableMethods;
import files.payload;

public class Basics {

	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//validate  if add  API is working  as  expected 
		//given - all input details 
		//when -submit the api
		//then -validate  the  responce 
		// content of the  file  to String-> content of  the  file can  convert  into byte->Byte data  to String
		// if the pass  the path then  use Paths.get("") , adding the file path location  
	
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String responce = given().log().all().queryParam("key", "qaclick123").header("Content-type","application/json")
		//.body(payload.AddPlace())
				//38.how  to handle static  json payload
		.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\Admin\\Desktop\\addPlace.txt"))))
		.when().post("maps/api/place/add/json")
				.then().log().all().assertThat().statusCode(200)
				.body("scope",equalTo("APP")).header("Server", equalTo("Apache/2.4.52 (Ubuntu)"))
				.extract().response().asString();
		
		System.out.println(responce);
		JsonPath js = new  JsonPath(responce);//for parsing json
		String placeId = js.getString("place_id");
		System.out.println(placeId);
		
		//update  place  
		
		String newAddress= "70 Summer walk, USA";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200)
		.body("msg", equalTo("Address successfully updated"));
		
		//get place
		
	String getPlaceResponce = given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id",placeId)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
	  JsonPath js1=ReUsableMethods.rawToJson(getPlaceResponce);
      //	JsonPath js1 = ReUsableMethods.JsonPath(getPlaceResponce);
     	String actualAddress = js1.getString("address");
      	System.out.println(actualAddress);
      	Assert.assertEquals(actualAddress, newAddress);
      	
	}

}
