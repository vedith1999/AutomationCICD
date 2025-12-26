package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/cucumber", glue = "VedithKumarJupaka.stepDefinitions", 
		monochrome = true, tags = "@RegressionTest", plugin = {"html:target/cucumber.html" })

public class TestNGTestRunnerTest extends AbstractTestNGCucumberTests {

}
