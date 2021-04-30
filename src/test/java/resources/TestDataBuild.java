package resources;

import java.util.ArrayList;
import java.util.List;

import com.bdd.APIFramework.pojo.AddPlaceSerializePOJO;
import com.bdd.APIFramework.pojo.Location;

public class TestDataBuild {
	
	public AddPlaceSerializePOJO addPlacePayload(String name, String language, String address){
		
		AddPlaceSerializePOJO ap = new AddPlaceSerializePOJO();

		ap.setAccuracy(50);
		ap.setAddress(address);
		ap.setLanguage(language);
		ap.setPhone_number("+91-987654321");
		ap.setName(name);
		ap.setWebsite("http://google.com");
		List<String> myList = new ArrayList<String>();
		myList.add("Shoe park");
		myList.add("shop");
		ap.setTypes(myList);

		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);

		ap.setLocation(l);
		
		return ap;
		
	}
	
	public String deletePayload(String placeID) {
		String payload="{\"place_id\":\""+placeID+"\"}";
		return payload;
	}

}
