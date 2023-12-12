package jayslabs;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class LibraryAPITester {

	@Test
	public void addBook() {
		RestAssured.baseURI = "http://216.10.245.166";

		String resp = given().log().all().header("Content-Type", "application/json")
				.body(Payload.getBody_addBook("wholetthe","dogsout"))
				.when().post("Library/Addbook.php").then().log().all().assertThat().statusCode(200).extract().response()
				.asString();

		JsonPath jspath = new JsonPath(resp);
		String msgresp = jspath.getString("Msg");
		Assert.assertEquals(msgresp, "successfully added");
	}

	@Test
	public void deleteBook() {
		RestAssured.baseURI = "http://216.10.245.166/";

		String resp = 
			given()
				.log().all().header("Content-Type", "application/json")
				.body(Payload.getBody_deleteBook("wholetthedogsout"))
			.when()
				.post("Library/DeleteBook.php")
			.then()
				.log().all().assertThat().statusCode(200).extract().response()
				.asString();

		JsonPath jspath = new JsonPath(resp);
		String msgresp = jspath.getString("msg");
		Assert.assertEquals(msgresp, "book is successfully deleted");
		
	}
}