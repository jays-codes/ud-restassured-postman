# ud-restassured-postman
Jays proj repo for RestAssured Postman Training (conducted by R.Shetty - udemy)

Pls. refer to project restassured-tester (java, mvn). covers ff. topics
- RestAssured APIs:
  - given, log, all, when, then, queryParam, header, body, equalTo (hamcrest.Matchers) post, assertThat, statusCode
  - extract, response, asString
  - io.restassured.path.json.JsonPath to traverse to and extract from json (resp) body
  - using .pathParam() / {param} to pass in parameter values to REST API
  - using SessionFilter to pass session info to subsequent requests
  - using .multiPart() to send attachments to a Jira issue
  - using .formParam() to send param in Body form in POST
  - using RequestSpecBuilder/RequestSpecification and ResponseSpecBuilder/ResponseSpecification, builder instance passed in .spec;
  - using relaxedHTTPSValidation to bypass invalid SSL Certification
- Integrated TestNG
  - using DataProvider
- OAuth 2.0
  - getting token from Auth server via client credentials; typical contract
- JSON serialization / deserialization
  - creating POJO for complex JSON
  - jackson databind
  - using .body(pojo instance) to serialize; deserialize using .as() to generate POJO instance
- GraphQL - Added test to send POST request to graphQL API (mutation)
Added restassured-framework-jaydemo project
  - v1.0 : RestAssured, Cucumber, JUnit 4
    - AddPlace API implemented
    - added resources package, TestDataBuild (return pojos), FrameworkUtil (calling spec builders) extended by StepDefinition
    - added logging (in Util) via addFilter(), Request/Response LoggingFilter, to a log file
    - added global.properties file, added Util method to get property value
    - added Data Driven execution via 'Examples' in feature file, refactored TestDataBuild to parameterize payload generation
    - used enum to parameterize resource param passed to POST API calls
    - parameterize http method call in feature file to have one SD method implementation handle multiple http methods
    - Added for get and delete API call in SD method
    - Added Util method to parse value from json given Response obj and key
    - Added tagging control to Runner class
    - Added Hooks class - io.cucumber.java.Before - to do prerequisite code prior to executing test step (DeletePlace)
    - modified pom to add plugin maven-cucumber-reporting 5.7.8 for pretty formatted reports
    - Executed TestRunner via mvn cmdline
    - Added jenkins job to run framework, added parameterization of tags
    
