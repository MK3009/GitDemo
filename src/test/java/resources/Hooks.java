package resources;

import java.io.IOException;

import io.cucumber.java.Before;
import stepDefinations.StepDefination;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenarios() throws IOException {
		StepDefination sd = new StepDefination();
		System.out.println("**********Inside Before hook");
		if(StepDefination.place_id==null) {
		
			sd.add_place_payload("A-10", "Hindi", "PVK");
			sd.user_calls_with_post_http_request("AddPlaceAPI", "POST");
			sd.the_api_call_got_success_with_status_code(200);
			sd.verify_place_id_created_with_using("A-10", "GetPlaceAPI");
		}
	}

}
