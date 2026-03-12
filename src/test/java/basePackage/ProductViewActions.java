package basePackage;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class ProductViewActions extends BaseClass{
	public static int productId;
	@Test
	public void fetchAllProducts() {
		Response res=given()
				.relaxedHTTPSValidation() //ignore SSL certification validation
				.contentType("application/json")
				.auth().oauth2(jwtToken)
				.baseUri("https://www.shoppersstack.com/shopping")
				.when()
				.get("/products/alpha");
//		productId = res.jsonPath().getInt("data[0].productId");
//		res.prettyPrint();
		
		List<Integer> productIds=res.jsonPath().getList("data.productId");
		int productId=productIds.get(1);
		System.out.println("pdt id is:"+productId);
	}
}
