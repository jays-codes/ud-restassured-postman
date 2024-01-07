package jayslabs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import jayslabs.pojo.AddPlace;
import jayslabs.pojo.Location;

public class GoogleMapsAPITester {

	@Test
	public void addPlace() {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
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
		
		String resp = given()
				.log().all()
				.queryParam("key", "qaclick123")
				.header("Content-Type","application/json")
				.body(place)
			//when - specify resource and http method
			.when()
				.post("/maps/api/place/add/json")
			.then()
				.log().all()
				.assertThat().statusCode(200)
				.body("scope",equalTo("APP"))
				.header("server", "Apache/2.4.52 (Ubuntu)")
				.extract().response().asString();		
	}
}
