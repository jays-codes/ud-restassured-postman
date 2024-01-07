package jayslabs;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

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
		
		RestAssured.baseURI = "http://localhost:8090";
		
		String resp = 
		given()
			.header("Content-Type", "application/json")
			.body("{\r\n"
					+ "    \"username\": \"zaimenorca\",\r\n"
					+ "    \"password\": \"Kamusta@123\"\r\n"
					+ "}")
			.log().all()
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
	
	@Test
	public void addAttachment() {
		RestAssured.baseURI = "http://localhost:8090";

		SessionFilter sessFilter = new SessionFilter();

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

		//add attachment
		given().header("Content-Type", "multipart/form-data")
			.header("X-Atlassian-Token", "no-check")
			.filter(sessFilter)
			.pathParam("id", "10004")
			.multiPart("file",new File("jiratest.txt"))
			.log().all()
		.when()
			.post("/rest/api/2/issue/{id}/attachments")
		.then()
			.log().all().assertThat().statusCode(200).extract().response()
			.asString();
	}
	
	@Test
	public void getIssue() {
		RestAssured.baseURI = "http://localhost:8090";

		SessionFilter sessFilter = new SessionFilter();

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

		//get issue
		String resp = given().header("Content-Type", "application/json")
			.filter(sessFilter)
			.pathParam("id", "10004")
			.queryParam("fields", "comment")
			.log().all()
		.when()
			.get("/rest/api/2/issue/{id}")
		.then()
			.log().all().assertThat().statusCode(200).extract().response()
			.asString();
	
		JsonPath jspath = new JsonPath(resp);

		int count = jspath.getInt("fields.comment.comments.size()");
		System.out.println("comment count:" + count);
		
		//print out comments
		for (int i=0;i<count;i++) {
			System.out.print(
					jspath.getString("fields.comment.comments["+i+"].body")
					+ "\n");
		}
		
	}
	
}
