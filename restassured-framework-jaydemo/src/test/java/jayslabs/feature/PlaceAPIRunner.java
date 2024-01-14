
package jayslabs.feature;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
plugin="json:target/jsonReports/cucumber-report.json",
features="src/test/java/jayslabs/feature",
glue={"jayslabs.stepdefinition"}

//,tags="@AddPlace"

)
public class PlaceAPIRunner {
}