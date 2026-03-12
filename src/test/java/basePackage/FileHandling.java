package basePackage;

import static io.restassured.RestAssured.given;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class FileHandling extends BaseClass{
	public static int productId;
	public static int quantity;
	@Test
	public void fetchAllProducts() throws IOException {
		Response res=given()
				.relaxedHTTPSValidation() //ignore SSL certification validation
				.contentType("application/json")
				.auth().oauth2(jwtToken)
				.baseUri("https://www.shoppersstack.com/shopping")
				.when()
				.get("/products/alpha");
		
		List<Integer> productIds=res.jsonPath().getList("data.productId");
		productId=productIds.get(1);
		System.out.println("pdt id is:"+productId);
		quantity=res.jsonPath().getInt("data[0].quantity");
		System.out.println("quantity is:"+ quantity);
		
		res.then().
		assertThat().statusCode(200);
		
		FileWriter file=new FileWriter("response.json");
		file.write(res.asPrettyString());
		file.close();
	}
}
