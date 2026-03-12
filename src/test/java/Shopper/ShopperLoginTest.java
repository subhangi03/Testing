package Shopper;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class ShopperLoginTest {
	@Test
	public void loginTest(){
		//RestAssured.
		given()
		.contentType("application/json")
		.relaxedHTTPSValidation()
		.body("{\r\n"
				+ "  \"email\": \"subhangip2005@gmail.com\",\r\n"
				+ "  \"password\": \"Subhangi23@1\",\r\n"
				+ "  \"role\": \"SHOPPER\"\r\n"
				+ "}")
		.when()
		.post("https://www.shoppersstack.com/shopping/users/login")
		
		.then()
		.assertThat().statusCode(200)
		.log().all();
	}
	@Test
	public void fetchData(){
		//RestAssured.
		given()
		.contentType("application/json")
		.auth().oauth2("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdWJoYW5naXAyMDA1QGdtYWlsLmNvbSBTSE9QUEVSIiwiZXhwIjoxNzczMjM4MzQxLCJpYXQiOjE3NzMyMDIzNDF9.VcYv1ZR_NsXtOdLCUsGz44sKPtJnS2e8NrCLEcixn70")
		.relaxedHTTPSValidation()
		.pathParam("SHOPPER_ID", 363297)
		.when()
		.get("https://www.shoppersstack.com/shopping/shoppers/{SHOPPER_ID}")
		
		.then()
		.assertThat().statusCode(200)
		.log().all();
	}
	
	
}
