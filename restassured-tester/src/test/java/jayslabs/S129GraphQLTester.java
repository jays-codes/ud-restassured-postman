package jayslabs;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;


public class S129GraphQLTester {
	
	@Test
	public void testCreateGQL() {
		//create character for RSA graphql api
		String resp = 
				given().log().all()
				.contentType(ContentType.JSON)
				.body("{\r\n"
						+ "  \"query\": \"mutation{\\n  createCharacter(character:{\\n    name:\\\"Tatsumaki 2\\\",\\n    type:\\\"Hero\\\",\\n    species:\\\"Human\\\",\\n    gender:\\\"male\\\",\\n    status:\\\"Active\\\",\\n    originId:1000,\\n    locationId:1000,\\n    image:\\\"Saikyo Hero!\\\"\\n  }) {\\n    id\\n  } \\n}\",\r\n"
						+ "  \"variables\": {\r\n"
						+ "    \"characterId\": 5205\r\n"
						+ "  }\r\n"
						+ "}")
				.when().post("https://rahulshettyacademy.com/gq/graphql")
				.then()
					.log().all()
					.extract().response().asString();
		
		JsonPath jspath = new JsonPath(resp);
		String str = jspath.getString("data.createCharacter.id");
		System.out.println("char id: " + str);
	}
	
}
