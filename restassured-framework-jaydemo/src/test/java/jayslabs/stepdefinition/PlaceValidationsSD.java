package jayslabs.stepdefinition;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import jayslabs.pojo.AddPlace;
import jayslabs.resources.APIResources;
import jayslabs.resources.FrameworkUtil;
import jayslabs.resources.TestDataBuild;

public class PlaceValidationsSD extends FrameworkUtil{

	RequestSpecification rqspec = null;
	ResponseSpecification rsspec = null;
	Response resp = null;
	TestDataBuild td = new TestDataBuild();
	
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String lang, String addr) throws IOException {
		System.out.println("running test...");
		rqspec = getRequestSpecification(); 

		AddPlace place = td.getAddPlacePayload(name, lang, addr);
		rqspec = given().log().all().spec(rqspec).body(place);
	}

	@When("User calls {string} with POST http request")
	public void user_calls_api_with_post_http_request(String apistr) {
		
		rsspec = getResponseSpecification();

		resp = rqspec.when()
				.post(APIResources.valueOf(apistr).getResource())
				.then()
					.log().all()
					.spec(rsspec)
					.body("scope",equalTo("APP"))
					.extract().response();	
	}
	
	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String apistr, String method) {
//		rsspec = getResponseSpecification();
//
//		resp = rqspec.when()
//				.post(APIResources.valueOf(apistr).getResource())
//				.then()
//					.log().all()
//					.spec(rsspec)
//					.body("scope",equalTo("APP"))
//					.extract().response();	

		rsspec = getResponseSpecification();

		rqspec = rqspec.when();

		if (method.equalsIgnoreCase("POST")) {
			resp = rqspec.post(APIResources.valueOf(apistr).getResource());
		} else if (method.equalsIgnoreCase("GET")) {
			resp = rqspec.get(APIResources.valueOf(apistr).getResource());
		} 
		
		resp = resp
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
