package jayslabs.cucumber.stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl {

	@Given("do precondition step")
	public void do_precondition_step() {
		System.out.println("doing precondtion step 1...");
	}

	@When("do test step with {string} and password {string}")
	public void do_test_step_with_and_password(String usr, String pwd) {
		System.out.println("doing test step with " + usr + " and pwd: " + pwd);
	}
	
	@When("do test step 1")
	public void do_test_step_1() {
		System.out.println("doing step 1...");
	}

	@When("do test step 2")
	public void do_test_step_2() {
		System.out.println("doing step 2...");
	}

	@Then("do test step validation 1")
	public void do_test_step_validation_1() {
		System.out.println("doing test step validation 1...");
	}

	@Then("do test step validation 2")
	public void do_test_step_validation_2() {
		System.out.println("doing test step validation 2...");
	}
}
