package basePackage;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;

public class BaseClass {
	public static String shopperId;
	public static String jwtToken;
	public static int productId=52;
	public static int quantity;
	public static int itemId;
	
	@BeforeClass
	public void loginTest() {
		HashMap<String,Object> map = new HashMap<>();
		map.put("email", "subhangip2005@gmail.com");
		map.put("password", "Subhangi23@1");
		map.put("role", "SHOPPER");
		
		Response res=given()
				.contentType("application/json")
				.relaxedHTTPSValidation() //ignore SSL certification validation
				.body(map)
				.when().
				post("https://www.shoppersstack.com/shopping/users/login");
		
		res.then().log().all();
		
		shopperId=res.jsonPath().getString("data.userId");
		System.out.println("shopperId: "+shopperId);
		
		jwtToken=res.jsonPath().getString("data.jwtToken");
		System.out.println("Bearer Token: "+jwtToken);
	
	}
	
}
