package jayslabs;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import jayslabs.pojo.Course;
import jayslabs.pojo.GetCourse;

import static io.restassured.RestAssured.*;

import java.util.List;


public class OAuthTester {

	@Test
	public void getOAuthToken() {
		//RestAssured.baseURI = "https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token";

		String resp = 
		given()
			.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
			.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
			.formParam("grant_type", "client_credentials")
			.formParam("scope", "trust")
			.log().all()
		.when()
			.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
		.then()
			.log().all()
			.assertThat().statusCode(200)
			.extract().response().asString();
		
		JsonPath jspath = new JsonPath(resp);
		String token = jspath.getString("access_token");
		System.out.println("token: " + token);
	}
	
	@Test
	public void getCourseDetails() {
		String resp = 
		given()
			.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
			.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
			.formParam("grant_type", "client_credentials")
			.formParam("scope", "trust")
		.when()
			.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
		.then()
			.log().all()
			.assertThat().statusCode(200)
			.extract().response().asString();
		
		JsonPath jspath = new JsonPath(resp);
		String token = jspath.getString("access_token");

		GetCourse rsobj =
		given()
			.queryParam("access_token", token)
		.when()
			.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
		.then()
			.log().all()
			.extract().response()
			//deserialize response
			.as(GetCourse.class);
		
		System.out.println("linkedin: " + rsobj.getLinkedIn());
		 
		List<Course> crs = rsobj.getCourses().getWebAutomation();
		crs.forEach(c -> System.out.println(c.getCourseTitle()));
		
	}
}
