package Pyxis.RestAssured.APITest;

import static io.restassured.RestAssured.given;

import java.util.Random;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class LoginSignupTest {
	
	public void runMultipleTest(int i)
	{

		JSONObject req = new JSONObject();
		
		req.put("full_name", "api testpostthree");
		req.put("email", "apitestpostth66re"+i+"e@test.comff");
		req.put("password1", "qwerty");
		req.put("password2", "qwerty");
		
		System.out.println(req);

		given().header("Ocp-Apim-Subscription-Key", "664acb9d60144b798dbdf3ef2f33e6b7").header("content-type","application/json").
		//		.header("token", "86ba53ca8bede821b2d6829c0b361357da9d1785").
				body(req.toJSONString()).post("https://pyxis-app.azure-api.net/user/auth/register/").then()
				.statusCode(201);
				

	}
	

	@SuppressWarnings("unchecked")
	@Test
	public void VerifyRegisterAPI() {
		
Random r =new Random();	
	int j=r.nextInt(1000000);
	System.out.println("**********random nummber used : --> "+j);
			
	runMultipleTest(j);



	}

}
