package jayslabs;

public class Payload {

	public static String getBody_addPlace() {
		String body = "{\r\n"
				+ "    \"location\": {\r\n"
				+ "        \"lat\": -38.383494,\r\n"
				+ "        \"lng\": 33.427362\r\n"
				+ "    },\r\n"
				+ "    \"accuracy\": 50,\r\n"
				+ "    \"name\": \"Frontline house\",\r\n"
				+ "    \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "    \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "    \"types\": [\r\n"
				+ "        \"shoe park\",\r\n"
				+ "        \"shop\"\r\n"
				+ "    ],\r\n"
				+ "    \"website\": \"http://google.com\",\r\n"
				+ "    \"language\": \"French-IN\"\r\n"
				+ "}";
		return body;
	}
	
	public static String getBody_updatePlace(String placeId) {
		String body = "{\r\n"
				+ "    \"place_id\": \""
				+ placeId
				+ "\",\r\n"
				+ "    \"address\": \"70 summer walk, USA\",\r\n"
				+ "    \"key\": \"qaclick123\"\r\n"
				+ "}";
		System.out.println("PUT req body: " + body);
		return body;
	}
	
	public static String getMockBody1(){
		String body = "{\r\n"
				+ "    \"dashboard\": {\r\n"
				+ "        \"purchaseAmount\": 910,\r\n"
				+ "        \"website\": \"rahulshettyacademy.com\"\r\n"
				+ "    },\r\n"
				+ "    \"courses\": [\r\n"
				+ "        {\r\n"
				+ "            \"title\": \"Selenium Python\",\r\n"
				+ "            \"price\": 50,\r\n"
				+ "            \"copies\": 6\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \"title\": \"Cypress\",\r\n"
				+ "            \"price\": 40,\r\n"
				+ "            \"copies\": 4\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \"title\": \"RPA\",\r\n"
				+ "            \"price\": 45,\r\n"
				+ "            \"copies\": 10\r\n"
				+ "        }\r\n"
				+ "    ]\r\n"
				+ "}";
		return body;
	}

	public static String getBody_addBook(String isbn, String aisle) {
		String body = "{\r\n"
				+ "\"name\":\"Learn Appium Automatiosdgfsbdgsdrfgn with Java in 1 day\",\r\n"
				+ "\"isbn\":\"" + isbn + "\",\r\n"
				+ "\"aisle\":\"" + aisle + "\",\r\n"
				+ "\"author\":\"JayMenorca\"\r\n"
				+ "}";
		return body;
	}
	
	public static String getBody_deleteBook(String id) {
		String body = "{\r\n"
				+ "\"ID\":\"" + id + "\"\r\n"
				+ "}";
		return body;
	}
}
