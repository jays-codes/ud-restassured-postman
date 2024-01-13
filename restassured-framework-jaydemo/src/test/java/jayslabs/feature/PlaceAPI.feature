
@tag
Feature: Validating Place APIs

  @AddPlace
  Scenario Outline: Verify if Place is being successfully added using AddPlace API
    Given Add Place Payload with "<name>" "<lang>" "<addr>"
    When User calls "addPlaceAPI" with "post" http request
    Then the API call is a successful with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps to "<name>" using getPlaceAPI
   # And User calls "deletePlaceAPI" with "delete" http request
   # And "status" in response body is "OK"
		
	 Examples: 
      | name  		| lang 		 | addr  		|
      | Jay Haus 	| Filipino | Manila 	|
      | Jay Labs	| English  | Toronto  |

@DeletePlace
 Scenario: Verify if Delete Place functionality is working
 		Given DeletePlace Payload
 	  When User calls "deletePlaceAPI" with "delete" http request
    Then the API call is a successful with status code 200
    And "status" in response body is "OK"
    