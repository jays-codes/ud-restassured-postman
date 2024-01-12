package jayslabs.resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class FrameworkUtil {

	public PrintStream getLog() {
		return log;
	}

	PrintStream log; 
	
	public RequestSpecification getRequestSpecification() throws IOException {
		log = new PrintStream(new FileOutputStream("app.log", true));
		
		RequestSpecification rqspec = new RequestSpecBuilder()
				.setBaseUri(getGlobalValue("baseURL"))
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON)
				.build();
	
		return rqspec;
	}
	
	public ResponseSpecification getResponseSpecification() {
		ResponseSpecification rsspec = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.build();
		return rsspec;
	}
	
	public String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\jay\\training\\repo\\git\\ud-restassured-postman\\restassured-framework-jaydemo\\src\\test\\java\\jayslabs\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
}
