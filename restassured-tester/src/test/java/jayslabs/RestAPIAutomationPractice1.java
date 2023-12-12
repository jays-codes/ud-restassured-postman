package jayslabs;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

public class RestAPIAutomationPractice1 {

	public static void main(String[] args) {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		//add place -> update place with new address -> get place to 
		//validate if new address is present in resp

		
		//given() - all input details
		String resp = given()
			.log().all()
			.queryParam("key", "qaclick123")
			.header("Content-Type","application/json")
			.body(Payload.getBody_addPlace())
		//when - specify resource and http method
		.when()
			.post("/maps/api/place/add/json")
		.then()
			.assertThat()
			.statusCode(200)
			.body("scope",equalTo("APP"))
			.header("server", "Apache/2.4.52 (Ubuntu)")
			.extract().response().asString();
		
			System.out.println("POST Response: \n" + resp);
			
		
		JsonPath jspath = new JsonPath(resp);
		String placeid = jspath.get("place_id");
		
		//update place api with new address
		resp = given()
				.log().all()
				.queryParam("key", "qaclick123")
				.header("Content-Type","application/json")
				.body(Payload.getBody_updatePlace(placeid))
			//when - specify resource and http method
			.when()
				.put("/maps/api/place/update/json")
			.then()
				.assertThat()
				.statusCode(200)
				.body("msg",equalTo("Address successfully updated"))
				.header("server", "Apache/2.4.52 (Ubuntu)")
				.extract().response().asString();
			
				System.out.println("PUT Response: \n" + resp);
				
//get place
		String expAddr = "70 summer walk, CA";
		resp = given()
				.log().all()
				.queryParam("key", "qaclick123")
				.queryParam("place_id", placeid)
			.when()
				.get("/maps/api/place/get/json")
			.then()
				.assertThat()
				.statusCode(200)
				.body("address",equalTo("70 summer walk, USA"))
				.header("server", "Apache/2.4.52 (Ubuntu)")
				.extract().response().asString();
				
				
			jspath = new JsonPath(resp);
			String addr = jspath.get("address");
			
			Assert.assertEquals(addr, expAddr);
			System.out.println("GET Response address: \n" + addr);
		
			
	}

}
