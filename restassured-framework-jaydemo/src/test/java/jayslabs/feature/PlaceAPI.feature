
@tag
Feature: Validating Place APIs

  @tag1
  Scenario: Verify if Place is being successfully added using AddPlace API
    Given Add Place Payload
    When User calls "AddPlace" API with POST http request
    Then the API call is a successful with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"


