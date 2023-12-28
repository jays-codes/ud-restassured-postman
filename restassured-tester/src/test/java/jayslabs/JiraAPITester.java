package jayslabs;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;


public class JiraAPITester {

	/*
	 * 1. Login to jira and create session using Login API
	 * 2. Add comment to existing issue via Add Comment API
	 * 3. Add attachment to existing issue via Add Attachment API
	 * 4. Get issue details and verify if added comment and attachment exists via Get Issue API
	 * */
	
	public void login() {
		
	}
	
	@Test
	public void addComment() {
		RestAssured.baseURI = "http://localhost:8090";
		
		String resp = 
		given()
			.log().all()
			.header("Content-Type", "application/json")
			.header("Cookie","JSESSIONID=C10F42164CD90D15D350C3F41014D64E; atlassian.xsrf.token=B4KE-3A5J-3BAX-8MNU_e64b697e951973f49668994d19d6feb298241a8b_lin")
			.pathParam("id", "10004").body("{\r\n"
					+ "    \"update\": {\r\n"
					+ "        \"comment\": [\r\n"
					+ "            {\r\n"
					+ "                \"add\": {\r\n"
					+ "                    \"body\": \"hoi hoi hoi!!!\"\r\n"
					+ "                }\r\n"
					+ "            }\r\n"
					+ "        ]\r\n"
					+ "    }\r\n"
					+ "}")
		.when()
			.put("/rest/api/2/issue/{id}")
		.then()
			.log().all().assertThat().statusCode(204).extract().response()
			.asString();
	}
	
	
}
