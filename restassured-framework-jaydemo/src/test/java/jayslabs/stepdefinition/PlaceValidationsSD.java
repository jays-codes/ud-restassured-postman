package jayslabs.stepdefinition;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
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
import jayslabs.resources.FrameworkUtil;
import jayslabs.resources.TestDataBuild;

public class PlaceValidationsSD extends FrameworkUtil{

	RequestSpecification rqspec = null;
	ResponseSpecification rsspec = null;
	Response resp = null;
	TestDataBuild td = new TestDataBuild();
	
	
	@Given("Add Place Payload")
	public void add_place_payload() throws FileNotFoundException {
		System.out.println("running test...");
		rqspec = getRequestSpecification(); 

		AddPlace place = td.getAddPlacePayload();
		rqspec = given().log().all().spec(rqspec).body(place);
	}

	@When("User calls {string} API with POST http request")
	public void user_calls_api_with_post_http_request(String apipath) {
		
		rsspec = getResponseSpecification();

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
