package jayslabs.stepdefinition;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import java.io.IOException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import jayslabs.pojo.AddPlace;
import jayslabs.pojo.AddPlaceRs;
import jayslabs.pojo.DeletePlace;
import jayslabs.pojo.GetPlaceRs;
import jayslabs.resources.APIResources;
import jayslabs.resources.FrameworkUtil;
import jayslabs.resources.TestDataBuild;

public class PlaceValidationsSD extends FrameworkUtil{

	static RequestSpecification rqspec;
	ResponseSpecification rsspec = null;
	Response resp = null;
	TestDataBuild td = new TestDataBuild();
	static AddPlaceRs rsobj;
	GetPlaceRs getAPIrsobj = new GetPlaceRs();
	DeletePlace dp;
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String lang, String addr) throws IOException {
		rqspec = getRequestSpecification(); 

		AddPlace place = td.getAddPlacePayload(name, lang, addr);
		rqspec = given().log().all().spec(rqspec).body(place);
	}

	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String apistr, String method) throws IOException {

		rsspec = getResponseSpecification();
		rqspec = rqspec.when();

		if (method.equalsIgnoreCase("POST")) {
			resp = rqspec
					.post(APIResources.valueOf(apistr).getResource())
					.then()
						.log().all()
						.spec(rsspec)
						//.body("scope",equalTo("APP"))
						.extract().response();	
			rsobj = resp.as(AddPlaceRs.class);
		} else if (method.equalsIgnoreCase("GET")) {
			rqspec = given().log().all();
			resp = rqspec
			
			.baseUri(getGlobalValue("baseURL"))
			.queryParam("place_id", rsobj.getPlace_id())
			.queryParam("key", "qaclick123")
			.filter(RequestLoggingFilter.logRequestTo(getLog()))
			.filter(ResponseLoggingFilter.logResponseTo(getLog()))
			.get(APIResources.valueOf(apistr).getResource())
			.then()
				.log().all()
				.spec(rsspec)
				.extract().response();	
			
			String name = getValFromJsonPath(resp,"name");
			getAPIrsobj.setName(name);
			
		} else if (method.equalsIgnoreCase("DELETE")) {
			dp = td.getDeletePlacePayload(rsobj.getPlace_id());
			rqspec = given().log().all().spec(rqspec).body(dp);

			resp = rqspec
			.post(APIResources.valueOf(apistr).getResource())
			.then()
				.log().all()
				.extract().response();	
		} else {
			fail("invalid method call or API not found...");
		}
	}

	@Then("the API call is a successful with status code {int}")
	public void the_api_call_is_a_successful_with_status_code(Integer status) {
		assertEquals(resp.getStatusCode(),status.intValue());
	}

	@Then("verify place_id created maps to {string} using getPlaceAPI")
	public void verify_place_id_created_maps_to_using_getPlaceAPI(String expname) throws IOException{

		user_calls_with_http_request("getPlaceAPI","GET");
		assertEquals(getAPIrsobj.getName(),expname);
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String expectedVal) {
		
		String actual = getValFromJsonPath(resp,key);
		assertEquals(actual, expectedVal);
	}
	
	@Given("DeletePlace Payload")
	public void delete_place_payload() {
		dp = td.getDeletePlacePayload(rsobj.getPlace_id());

	}

}
