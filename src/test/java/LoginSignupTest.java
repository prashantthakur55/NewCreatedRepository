
import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class LoginSignupTest {
	JSONObject req = new JSONObject();

	@SuppressWarnings("unchecked")
	public void RegisterApi(int i) {

		req.put("full_name", "api test");
		req.put("email", "apitestpost" +i + "@test.com");
		req.put("password1", "qwerty");
		req.put("password2", "qwerty");

		given().header("Ocp-Apim-Subscription-Key", "664acb9d60144b798dbdf3ef2f33e6b7")
				.header("content-type", "application/json").
				// .header("token", "86ba53ca8bede821b2d6829c0b361357da9d1785").
				body(req.toJSONString()).post("https://pyxis-app.azure-api.net/user/auth/register/").then()
				.statusCode(201);
	}

	@Test(priority = 1)
	public void VerifyRegisterAPI() {
    
		Random r = new Random();
		int j = r.nextInt(100);
		System.out.println("**********random nummber used : --> " + j);
     	RegisterApi(j);
	}

	@SuppressWarnings("unchecked")
	@Test(priority = 2)
	public void VerifyLoginAPI() {

		req.put("email", "finaltest@gmail.com");
		req.put("password", "qwerty");

		//String body ="{email: \"finaltest@gmail.com\", password: \"qwerty\"}";
				given().header("Ocp-Apim-Subscription-Key", "664acb9d60144b798dbdf3ef2f33e6b7")
				.header("content-type", "application/json").
				// header("Host","pyxis-app.azure-api.net").header("Origin","https://app.pyxissocial.com").
				body(req.toJSONString()).post("https://pyxis-app.azure-api.net/user/auth/login/").then()
				.statusCode(200);

	}

	@Test(priority = 3)
	public void VerifyDetailsAPI() {

		given().header("Ocp-Apim-Subscription-Key", "664acb9d60144b798dbdf3ef2f33e6b7")
				.header("content-type", "application/json").header("token", "e4487bad8b782194b299b6f34a2d366aa99676d3")
				.get("https://pyxis-app.azure-api.net/user/details/").then().statusCode(200);

	}

	
	public static HashMap<String, HashMap<String, String>> VerifyQueuedScheuled() {

		String body = "{\r\n" + "    \"schedule\": {\r\n" + "        \"monday\": {\r\n"
				+ "            \"status\": \"ON\",\r\n" + "            \"timing\": [\r\n"
				+ "                \"15:00\",\r\n" + "                \"15:10\",\r\n" + "                \"16:00\",\r\n"
				+ "                \"16:35\",\r\n" + "                \"17:30\",\r\n" + "                \"00:05\",\r\n"
				+ "                \"00:20\"\r\n" + "            ]\r\n" + "        },\r\n"
				+ "        \"tuesday\": {\r\n" + "            \"status\": \"ON\",\r\n" + "            \"timing\": [\r\n"
				+ "                \"15:00\",\r\n" + "                \"15:10\",\r\n" + "                \"16:00\",\r\n"
				+ "                \"16:35\",\r\n" + "                \"17:30\",\r\n" + "                \"00:05\"\r\n"
				+ "            ]\r\n" + "        },\r\n" + "        \"wednesday\": {\r\n"
				+ "            \"status\": \"ON\",\r\n" + "            \"timing\": [\r\n"
				+ "                \"15:00\",\r\n" + "                \"15:10\",\r\n" + "                \"16:00\",\r\n"
				+ "                \"16:35\",\r\n" + "                \"17:30\",\r\n" + "                \"00:05\"\r\n"
				+ "            ]\r\n" + "        },\r\n" + "        \"thursday\": {\r\n"
				+ "            \"status\": \"ON\",\r\n" + "            \"timing\": [\r\n"
				+ "                \"15:00\",\r\n" + "                \"15:10\",\r\n" + "                \"16:00\",\r\n"
				+ "                \"16:35\",\r\n" + "                \"17:30\",\r\n" + "                \"00:05\"\r\n"
				+ "            ]\r\n" + "        },\r\n" + "        \"friday\": {\r\n"
				+ "            \"status\": \"ON\",\r\n" + "            \"timing\": [\r\n"
				+ "                \"15:00\",\r\n" + "                \"15:10\",\r\n" + "                \"16:00\",\r\n"
				+ "                \"16:35\",\r\n" + "                \"17:30\",\r\n" + "                \"00:05\"\r\n"
				+ "            ]\r\n" + "        },\r\n" + "        \"saturday\": {\r\n"
				+ "            \"status\": \"ON\",\r\n" + "            \"timing\": [\r\n"
				+ "                \"15:00\",\r\n" + "                \"15:10\",\r\n" + "                \"16:00\",\r\n"
				+ "                \"16:35\",\r\n" + "                \"17:30\",\r\n" + "                \"00:05\"\r\n"
				+ "            ]\r\n" + "        },\r\n" + "        \"sunday\": {\r\n"
				+ "            \"status\": \"ON\",\r\n" + "            \"timing\": [ \"15:00\",\r\n"
				+ "                \"15:10\",\r\n" + "                \"16:00\",\r\n" + "                \"16:35\",\r\n"
				+ "                \"17:30\",\r\n" + "                \"00:05\"]\r\n" + "        }\r\n" + "    },\r\n"
				+ "    \"social_account_type\": \"page\",\r\n" + "    \"social_account_id\": \"108744804130666\",\r\n"
				+ "    \"timezone\": \"Asia/Kolkata\",\r\n" + "    \"status\": \"active\"\r\n" + "}";

		RestAssured.baseURI = "https://pyxis-app.azure-api.net/post/api/schedule/update";
		RequestSpecification requestCheck = RestAssured.given();

		Response response = requestCheck.header("Ocp-Apim-Subscription-Key", "664acb9d60144b798dbdf3ef2f33e6b7")
				.header("content-type", "application/json").header("token", "df01435a1621b40187d6f832e6698aa5f929eda9")
				.body(body).post();
		String responseBody = response.asString();                    // COnverting response to string
		System.out.println(responseBody);
		Assert.assertTrue(response.getStatusCode()==200);
		HashMap<String, HashMap<String, String>> schedule = response.then().extract().path("data.schedule");
		System.out.println("*******************************************" + schedule);

		/*
		 * Set<String>s=schedule.keySet(); for(String e:s) { try {
		 * System.out.println(e+"---> "+schedule.get(e)+""); } catch(Exception E) {
		 * Set<String> r=(Set<String>) schedule.get(e);
		 * 
		 * for(String f:r) { System.out.println(f+"-------> "+schedule.get(e).get(f));
		 * return schedule.get(e).get("monday").toString();
		 * 
		 * } } }
		 */

		return schedule;

	}

	@Test (priority=4)
	public void verifyUpdateTimeSlotResponse() {
		HashMap<String, HashMap<String, String>> scheduleFromPostRequest = VerifyQueuedScheuled();
		System.out.println("****************************************************first " + scheduleFromPostRequest);
		String validateFirstResponse = scheduleFromPostRequest.toString();

		RestAssured.baseURI = "https://pyxis-app.azure-api.net/post/api/schedule/?social_account_id=108744804130666&social_account_type=page";
		RequestSpecification requestCheck = RestAssured.given();

		Response response = requestCheck.header("Ocp-Apim-Subscription-Key", "664acb9d60144b798dbdf3ef2f33e6b7")
				.header("content-type", "application/json").header("token", "df01435a1621b40187d6f832e6698aa5f929eda9")
				.queryParam("social_account_id", "108744804130666").queryParam("social_account_type", "page").get();

		String responseBody = response.asString();
		System.out.println(responseBody);
		HashMap<String, HashMap<String, String>> scheduleFromGetRequest = response.then().extract()
				.path("data.schedule");
		String validateSecondResponse = scheduleFromGetRequest.toString();

		System.out.println("*********************************** second " + scheduleFromGetRequest);
		Assert.assertTrue(validateFirstResponse.equals(validateSecondResponse));

	}
}