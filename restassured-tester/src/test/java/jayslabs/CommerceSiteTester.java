package jayslabs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import jayslabs.pojo.AddProduct;
import jayslabs.pojo.CreateOrder;
import jayslabs.pojo.CreateOrderResp;
import jayslabs.pojo.Login;
import jayslabs.pojo.LoginResponse;
import jayslabs.pojo.OrderDetail;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

public class CommerceSiteTester {

	@Test
	public void endToEnd() {
		
		RequestSpecification rqspec = new RequestSpecBuilder()
				.setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON)
				.build();
		
		//Serialize
		Login login = new Login();
		login.setUserEmail("jayeboh@gmail.com");
		login.setUserPassword("Hello123@");
		
		RequestSpecification rqspec2 = 
				given().relaxedHTTPSValidation().log().all()
				.spec(rqspec)
				.body(login);
		
		ResponseSpecification rsspec = new ResponseSpecBuilder()
			.expectStatusCode(200)
			.expectContentType(ContentType.JSON)
			.build();
		
		//login
		Response resp = rqspec2.when()
				.post("/api/ecom/auth/login")
				.then()
					.log().all()
					.spec(rsspec)
					.extract().response();
		
		//Deserialize
		LoginResponse loginrs = resp.as(LoginResponse.class);
		String token = loginrs.getToken();
		
		System.out.println("Message: " + loginrs.getMessage());
		
		//Add Product
		RequestSpecification addProdRqSpec = new RequestSpecBuilder()
				.setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token)
				.build();
		
		RequestSpecification addProdRqSpec2= given().log().all()
		.spec(addProdRqSpec)
		.param("productName", "Magic Beans 5")
		.param("productAddedBy", loginrs.getUserId())
		.param("productCategory","fashion")
		.param("productSubCategory","undies")
		.param("productPrice","575")
		.param("productDescription","Jacks Beanstalk")
		.param("productFor","giants")
		.multiPart("productImage",new File("productImage_1650649561327.jpg"));
		
		resp = addProdRqSpec2
			.when().post("/api/ecom/product/add-product")
			.then().log().all()
//			.spec(rsspec)
			.extract().response();

		AddProduct prod = resp.as(AddProduct.class);
		String prodId = prod.getProductId();
		System.out.println("prod id: " + prodId);
		
		//Create OrderDetail
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setCountry("Philippines");
		orderDetail.setProductOrderedId(prodId);
		List<OrderDetail> list = new ArrayList<OrderDetail>();
		list.add(orderDetail);

		CreateOrder orders = new CreateOrder();
		orders.setOrders(list);
		
		//Add Product
		RequestSpecification createOrderRqSpec = new RequestSpecBuilder()
				.setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token)
				.setContentType(ContentType.JSON)
				.build();
		
		RequestSpecification createOrderRqSpec2 = given().log().all()
			.spec(createOrderRqSpec).body(orders);
		
		resp = createOrderRqSpec2
			.when().post("/api/ecom/order/create-order")
			.then().log().all()
			//.spec(rsspec.)
			.extract().response();
		
		CreateOrderResp neworder = resp.as(CreateOrderResp.class);
		System.out.println("Create OrderDetail: " + neworder.getMessage());
		
		//Delete Product
		RequestSpecification delprodRqSpec = new RequestSpecBuilder()
				.setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token)
				.build();

		RequestSpecification delprodRqSpec2 = given().log().all()
				.spec(delprodRqSpec).pathParam("prod", prodId);
		
		resp = delprodRqSpec2
				.when().delete("/api/ecom/product/delete-product/{prod}")
				.then().log().all()
				.extract().response();

	}
	
}
