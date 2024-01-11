
package jayslabs.feature;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
features="src/test/java/jayslabs/feature",
glue="jayslabs.stepdefinition"
		)
public class PlaceAPIRunner {
}