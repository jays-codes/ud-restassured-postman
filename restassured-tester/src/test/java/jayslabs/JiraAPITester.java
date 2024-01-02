package jayslabs;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import io.restassured.filter.session.SessionFilter;


public class JiraAPITester {

	/*
	 * 1. Login to jira and create session using Login API
	 * 2. Add comment to existing issue via Add Comment API
	 * 3. Add attachment to existing issue via Add Attachment API
	 * 4. Get issue details and verify if added comment and attachment exists via Get Issue API
	 * */
	
	@Test
	public void login() {
		SessionFilter sfilter = new SessionFilter();
		
		RestAssured.baseURI = "http://localhost:8090";
		
		String resp = 
		given()
			.header("Content-Type", "application/json")
			.body("{\r\n"
					+ "    \"username\": \"zaimenorca\",\r\n"
					+ "    \"password\": \"Kamusta@123\"\r\n"
					+ "}")
			.log().all()
			.filter(sfilter)
		.when()
			.post("/rest/auth/1/session")
		.then()
			.log().all().assertThat().statusCode(200).extract().response()
			.asString();
		
		//JiraAPITester.sessFilter = sfilter;
	}
	
	@Test
	public void addComment() {
		
		RestAssured.baseURI = "http://localhost:8090";

		SessionFilter sessFilter = new SessionFilter();

		
		//String resp = 
				given()
					.header("Content-Type", "application/json")
					.body("{\r\n"
							+ "    \"username\": \"zaimenorca\",\r\n"
							+ "    \"password\": \"Kamusta@123\"\r\n"
							+ "}")
					.log().all().filter(sessFilter)
				.when()
					.post("/rest/auth/1/session")
				.then()
					.log().all().assertThat().statusCode(200);
//					.extract().response()
//					.asString();
		
		//10004 is issue id
//		resp = 
		given()
			.log().all()
			.header("Content-Type", "application/json")
			//.header("Cookie","JSESSIONID=8D692F02B042B27B6980B8D3C0BB3093;")
			.pathParam("id", "10004").body("{\r\n"
					+ "    \"update\": {\r\n"
					+ "        \"comment\": [\r\n"
					+ "            {\r\n"
					+ "                \"add\": {\r\n"
					+ "                    \"body\": \"hoi hoi hoi. #3 updated cookie 2-jan-2024 !!!\"\r\n"
					+ "                }\r\n"
					+ "            }\r\n"
					+ "        ]\r\n"
					+ "    }\r\n"
					+ "}")
		.filter(sessFilter)	
		.when()
			.put("/rest/api/2/issue/{id}")
		.then()
			.log().all().assertThat().statusCode(204);
//			.extract().response()
//			.asString();
	}
	
	public void addAttachment() {
		RestAssured.baseURI = "http://localhost:8090";

		SessionFilter sessFilter = new SessionFilter();

		String resp = 
				given()
					.header("Content-Type", "application/json")
					.body("{\r\n"
							+ "    \"username\": \"zaimenorca\",\r\n"
							+ "    \"password\": \"Kamusta@123\"\r\n"
							+ "}")
					.log().all().filter(sessFilter)
				.when()
					.post("/rest/auth/1/session")
				.then()
					.log().all().assertThat().statusCode(200).extract().response()
					.asString();
	}
}
