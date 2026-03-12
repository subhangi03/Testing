package Shopper;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.response.Response;
//import io.restassured.RestAssured;

public class HashmapUsage {
	String shopperId,jwtToken;
	@Test
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
		
		shopperId=res.jsonPath().getString("data.userId");
		System.out.println("shopperId: "+shopperId);
		
		jwtToken=res.jsonPath().getString("data.jwtToken");
		System.out.println("Bearer Token: "+jwtToken);
	
	}

	@Test(dependsOnMethods="loginTest")
	public void fetchData() {
//		given().relaxedHTTPSValidation().contentType("application/json").auth().oauth2(
//				"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdWJoYW5naXAyMDA1QGdtYWlsLmNvbSBTSE9QUEVSIiwiZXhwIjoxNzczMjM4NDUyLCJpYXQiOjE3NzMyMDI0NTJ9.uBmLJLsWpdbAGfBKUS8Eo76RoHlhWWnD08sIeBGD93U")
//				.pathParam("shopperID", "363297")
//
//				.when().get("https://www.shoppersstack.com/shopping/shoppers/{shopperID}").then().assertThat()
//				.statusCode(200).log().all();
		Response res=given()
				.relaxedHTTPSValidation() //ignore SSL certification validation
				.contentType("application/json")
				.auth().oauth2("jwtToken")
				.pathParam("shopperId",shopperId)
				.baseUri("https://www.shoppersstack.com/shopping")
				.when()
				.get("/shoppers/{shopperId}");
		System.out.println(res.prettyPrint());
	}
}