
@tag
Feature: Validating Place APIs

  @tag1
  Scenario Outline: Verify if Place is being successfully added using AddPlace API
    Given Add Place Payload with "<name>" "<lang>" "<addr>"
    When User calls "addPlaceAPI" with "post" http request
    Then the API call is a successful with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"

   Examples: 
      | name  		| lang 		 | addr  		|
      | Jay Haus 	| Filipino | Manila 	|
      | Jay Labs	| English  | Toronto  |
