package jayslabs;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;


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

		resp =
		given()
			.queryParam("access_token", token)
		.when()
			.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
		.then()
			.log().all()
			.extract().response().asString();
		
		System.out.println("RESPONSE: " + resp);
		 
//		jspath = new JsonPath(resp);
//		int crgrpcount = jspath.getInt("courses.size()");
//		
//		for (int i=0;i<crgrpcount;i++) {
//			int crcount = jspath.getInt("courses[" + i + "].size()");
//			for (int j=0;j<crcount;j++) {
//				System.out.println(
//					jspath.getString("courses[" + i + "].courseTitle[" + j + "]")
//				);
//			}
//		}
	}
}
