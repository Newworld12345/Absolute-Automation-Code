package restwebservice.tests.testscript;

import org.testng.annotations.Test;

import com.jayway.jsonpath.Criteria;

import Utility.DataUtility;
import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONArray;

import org.testng.annotations.BeforeClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class VerifyDistanceAPIs {

	RequestSpecification httpRequest = null;
	static Response response = null;
	static String responseBody = null;

	public void TryGetCall() {
		RestAssured.baseURI = "https://maps.googleapis.com/maps/api/distancematrix";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,
				"/json?origins=Seattle&destinations=San+Francisco&key=AIzaSyBhJGoUpJI5xI6lZEeWzTPj2q4_ODhi8jc");

		responseBody = response.getBody().asString();
		System.out.println("Response Body from TryGetCall =>  " + responseBody);

	}
	
	public void VerifyStatusCode() {
		int actualStatusCode = 0;
		int expectedStatusCode = 200;
		actualStatusCode = response.getStatusCode();

		Assert.assertEquals(actualStatusCode, expectedStatusCode, "Status Code 200 is Failed....");

	}

	public void VerifyDestinationAddress() {
		JsonPath jsonPathEvaluator = response.jsonPath();

		// specified by JsonPath: destination_addresses
		ArrayList<String> al = new ArrayList<String>();
		al.add(jsonPathEvaluator.getList("destination_addresses").get(0).toString());
		System.out.println("Address received from API Response " + al);
		Object[] destination_addr = al.toArray();
		
		DataUtility dUtil = new DataUtility();
		
		try {
			for (Object obj : destination_addr) {
				System.out.print("destination addr from obj : " + obj + " ");
				// Validate the response
				Assert.assertEquals(obj, dUtil.getPageObjectByPageField("destination_addresses"), "Destination address Verification");
				//Assert.assertEquals(obj, "San Francisco, CA, USA", "Destination address Verification");
				System.out.println("Destination Address is Verified now");
			}
		} catch (Exception e) {
			  e.printStackTrace(); 
			}
		

	}

	public void VerifyDistanceValue() {
		JsonPath jsonPathEvaluator = response.jsonPath();

		ArrayList<String> al = new ArrayList<String>();

		JSONObject jsonObject = new JSONObject(responseBody);
		org.json.JSONArray jsonArray =	jsonObject.getJSONArray("rows");
		org.json.JSONArray jsonArray2 = jsonArray.getJSONObject(0).getJSONArray("elements");
		JSONObject getSth0 =  jsonArray2.getJSONObject(0);
		JSONObject getSth1 = getSth0.getJSONObject("distance");
		Object value = getSth1.get("value");

		System.out.println("value from API response is : " + value);
		
		DataUtility dUtil = new DataUtility();
		
		try {
			// Validate the response
			Assert.assertEquals(value.toString(), dUtil.getPageObjectByPageField("distance value"), "Value Verification");
			System.out.println("Value is Verified Successfully now");
		} catch (Exception e) {
			  e.printStackTrace(); 
			}			
	}


	@Test
	public void statusCodeVerification() {

		VerifyDistanceAPIs apiIntializer = new VerifyDistanceAPIs();
		apiIntializer.VerifyStatusCode();

	}

	@Test
	public void destinationAddressVerification() {

		VerifyDistanceAPIs apiIntializer = new VerifyDistanceAPIs();
		apiIntializer.VerifyDestinationAddress();

	}

	@Test
	public void distanceValueVerification() {

		VerifyDistanceAPIs apiIntializer = new VerifyDistanceAPIs();
		apiIntializer.VerifyDistanceValue();

	}

	@BeforeClass
	public void beforeClass() {
		VerifyDistanceAPIs apiIntializer = new VerifyDistanceAPIs();
		apiIntializer.TryGetCall();
	}

	@AfterClass
	public void afterClass() {

	}

}
