package jayslabs.resources;

import java.util.ArrayList;
import java.util.List;

import jayslabs.pojo.AddPlace;
import jayslabs.pojo.DeletePlace;
import jayslabs.pojo.Location;

public class TestDataBuild {
	public AddPlace getAddPlacePayload(String name, String lang, String addr) {
		AddPlace place = new AddPlace();

		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		place.setLocation(loc);
		place.setAccuracy(50);
		place.setName(name);
		place.setPhone_number("(+91) 123 456 7891");
		place.setAddress(addr);
		List<String> types = new ArrayList<String>();
		types.add("school");
		types.add("kitchen");
		place.setTypes(types);
		place.setWebsite("http://google.com");
		place.setLanguage(lang);
		
		return place;
	}
	
	public DeletePlace getDeletePlacePayload(String plid) {
		DeletePlace dp = new DeletePlace();
		dp.setPlace_id(plid);
		return dp;
	}
}
