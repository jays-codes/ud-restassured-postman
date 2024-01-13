package jayslabs.stepdefinition;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException{

		PlaceValidationsSD sd = new PlaceValidationsSD();
		if (sd.rsobj==null) {
			sd.add_place_payload_with("Jay Werks", "Pinoy", "SSM");
			sd.user_calls_with_http_request("addPlaceAPI", "post");			
		}
	}
}
