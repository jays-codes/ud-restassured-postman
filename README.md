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
