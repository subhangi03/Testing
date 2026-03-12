package basePackage;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.Test;

import ShopperWithPojo.ShopperPojo;
import io.restassured.response.Response;

public class Cart extends BaseClass {
	int productId;
	int quantity;
	int itemId;
	@Test
	public void fetchAllProducts() {
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
		System.out.println("quantity is:"+quantity);
		
		res.then().
		assertThat().statusCode(200).log().all();
	}
	@Test(dependsOnMethods="fetchAllProducts")
	public void addProduct() {
		AddProductToCartPojo pojoData=new AddProductToCartPojo(productId,quantity);
		Response res=given()
				.relaxedHTTPSValidation()
				.contentType("application/json")
				.auth().oauth2(jwtToken)
				.pathParam("SHOPPER_ID", shopperId)
				.baseUri("https://www.shoppersstack.com/shopping")
				.body(pojoData)
				.when()
				.post("/shoppers/{SHOPPER_ID}/carts");
		
		itemId=res.jsonPath().get("data.itemId");
		System.out.println("item id is:"+itemId);
		
		res.then().assertThat().statusCode(201).log().all();
		
	}
	@Test(dependsOnMethods="addProduct")
	public void updateCart() {
		AddProductToCartPojo update=new AddProductToCartPojo(productId,quantity);
		Response res=given()
				.relaxedHTTPSValidation() 
				.contentType("application/json")
				.auth().oauth2(jwtToken)
				.pathParam("SHOPPER_ID", shopperId)
				.pathParam("ITEM_ID", itemId)
				.baseUri("https://www.shoppersstack.com/shopping")
				.body(update)
				.when()
				.put("/shoppers/{SHOPPER_ID}/carts/{ITEM_ID}");
		res.then().assertThat().statusCode(200).log().all();
		
	}
	@Test(dependsOnMethods="updateCart")
	public void deleteFromCart() {
		AddProductToCartPojo pojoData=new AddProductToCartPojo(productId,quantity);
		Response res=given()
				.relaxedHTTPSValidation() 
				.contentType("application/json")
				.auth().oauth2(jwtToken)
				.pathParam("SHOPPER_ID", 363297)
				.pathParam("PRODUCT_ID", productId)
				.baseUri("https://www.shoppersstack.com/shopping")
				.body(pojoData)
				.when()
				.delete("/shoppers/{SHOPPER_ID}/carts/{PRODUCT_ID}");
		res.then().assertThat().statusCode(200).log().all();
		
	}
	
}
