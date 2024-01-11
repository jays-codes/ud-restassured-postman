package jayslabs.stepdefinition;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import jayslabs.pojo.AddPlace;
import jayslabs.pojo.Location;

public class PlaceValidationsSD {

	RequestSpecification rqspec = null;
	ResponseSpecification rsspec = null;
	Response resp = null;

	@Given("Add Place Payload")
	public void add_place_payload() {
		System.out.println("running test...");
		rqspec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();

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

		rqspec = given().log().all().spec(rqspec).body(place);
	}

	@When("User calls {string} API with POST http request")
	public void user_calls_api_with_post_http_request(String apipath) {
		rsspec = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.build();

		String postpath = "";
		
		switch(apipath) {
			case "AddPlace" : postpath = "/maps/api/place/add/json"; break;
			default: postpath=""; break;
		}
		
		resp = rqspec.when()
				.post(postpath)
				.then()
					.log().all()
					.spec(rsspec)
					.body("scope",equalTo("APP"))
					.extract().response();	
	}

	@Then("the API call is a successful with status code {int}")
	public void the_api_call_is_a_successful_with_status_code(Integer status) {
		assertEquals(resp.getStatusCode(),status.intValue());
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String expectedVal) {
		String respstr = resp.asString();
		JsonPath jspath = new JsonPath(respstr);
		
		assertEquals(jspath.get(key), expectedVal);
		
	}
}
