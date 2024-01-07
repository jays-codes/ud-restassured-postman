package jayslabs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import jayslabs.pojo.AddPlace;
import jayslabs.pojo.Location;

public class SpecBuilderTester {

	@Test
	public void addPlace() {
		
		RequestSpecification rqspec = new RequestSpecBuilder()
			.setBaseUri("https://rahulshettyacademy.com")
			.addQueryParam("key", "qaclick123")
			.setContentType(ContentType.JSON)
			.build();
		
		ResponseSpecification rsspec = new ResponseSpecBuilder()
			.expectStatusCode(200)
			.expectContentType(ContentType.JSON)
			.build();
		//RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		AddPlace place = new AddPlace();
		
		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		place.setLocation(loc);
		place.setAccuracy(50);
		place.setName("Backline House");
		place.setPhone_number("(+91) 123 456 7891");
		place.setAddress("29 front layout, blah 11");
		List<String> types = new ArrayList<String>();
		types.add("school");
		types.add("kitchen");
		place.setTypes(types);
		place.setWebsite("http://google.com");
		place.setLanguage("French-IN");
		
		RequestSpecification res = given()
				.log().all()
				.spec(rqspec)
				.body(place);
			//when - specify resource and http method
		Response resp = res.when()
			.post("/maps/api/place/add/json")
			.then()
				.log().all()
				.spec(rsspec)
//				.body("scope",equalTo("APP"))
//				.header("server", "Apache/2.4.52 (Ubuntu)")
				.extract().response();
		
		String rsstr = resp.asString();
		System.out.println("response: " + rsstr);
	}
	
//	public void runEndToEnd() {
//		
//	}

}
