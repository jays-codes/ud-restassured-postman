package jayslabs.cucumber.archive;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
features="src/test/java/jayslabs/cucumber",
glue="jayslabs.cucumber.stepdefinition"
		)
public class TestRunner1 {
	
}
