package jayslabs;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RestAPIAutomationPractice1 {

	public static void main(String[] args) {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
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
		
			System.out.println("Response: \n" + resp);
			
			//add place -> update place with new address -> get place to 
			//validate if new address is present in resp
		
		JsonPath jspath = new JsonPath(resp);
		String placeid = jspath.get("place_id");
	}

}
