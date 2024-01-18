package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {"pretty"},
        features = "src/main/java/Feature",
        glue = "classpath:stepDefinition",
        tags = "@checkingvariable"
)
public class RunCucumberTest extends AbstractTestNGCucumberTests {
}
