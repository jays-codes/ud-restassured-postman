package jayslabs;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JsonDataProvider {

	@Test(dataProvider = "BooksData")
	public void addBook(String isbn, String aisle) throws IOException {

		RestAssured.baseURI = "http://216.10.245.166";
		String resp = 
			given().
				header("Content-Type", "application/json").
//				body(Payload.getBody_addBook(isbn, aisle)).
				body(new String(Files.readAllBytes(Paths.get("")))).
			when().
				post("/Library/Addbook.php").
			then().assertThat().statusCode(200).
				extract().response().asString();

		JsonPath js = new JsonPath(resp);
		String id = js.get("ID");
		System.out.println(id);
	}

	@DataProvider(name="BooksData")
	public Object[][]  getData(){
	//array=collection of elements

	//multidimensional array= collection of arrays
		Object[][] objarr = null;
		objarr = new Object[][] 
		{
			{"ojfwty","9363"},
			{"cwetee","4253"},
			{"okmfet","5334"} 
		};

		return objarr; 
	}	
	
}
