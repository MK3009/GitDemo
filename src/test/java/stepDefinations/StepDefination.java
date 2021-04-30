package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.bdd.APIFramework.pojo.AddPlaceSerializePOJO;
import com.bdd.APIFramework.pojo.Location;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils{

	RequestSpecification res;
	Response response;
	TestDataBuild td = new TestDataBuild();
	public static String place_id; 

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException {
		res = given().spec(requestSpecifications()).body(td.addPlacePayload(name, language, address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_post_http_request(String resource, String method) {
		APIResources ap = APIResources.valueOf(resource);

		if(method.equalsIgnoreCase("POST")) {
			response = res.when().post(ap.getResource()).then().extract().response();
		}else if(method.equalsIgnoreCase("GET")) {
			response = res.when().get(ap.getResource()).then().extract().response();
		}else if(method.equalsIgnoreCase("DELETE")) {
			response = res.when().delete(ap.getResource()).then().extract().response();
		}
		System.out.println(response.asPrettyString());
	}

	@Then("the API call got success with Status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		int statusCode = response.getStatusCode();
		assertEquals(statusCode, 200);
		System.out.println("Status code is *****:"+ statusCode);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String expectedValue) {


		String actualValue = getJsonResponse(response, key).toString();
		assertEquals(expectedValue, actualValue);
	}
	
	@Then("verify place_id created with {string} using {string}")
	public void verify_place_id_created_with_using(String expectedName, String resource) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		place_id = getJsonResponse(response,"place_id").toString();
		res = given().spec(requestSpecifications()).queryParam("place_id", place_id);
		user_calls_with_post_http_request(resource, "GET");
		
		String actualName = getJsonResponse(response,"name").toString();
		assertEquals(expectedName, actualName);
	}
	
	@Given("DeletePlace payload")
	public void deletePlace_payload() throws IOException {
		
		res = given().spec(requestSpecifications()).body(td.deletePayload(place_id));
	}

}
