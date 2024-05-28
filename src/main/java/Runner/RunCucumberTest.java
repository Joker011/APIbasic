package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {
                "html:target/cucumber.html","json:target/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:target/failed_scenarios.txt",
                "pretty"
        },
        features = "src/main/java/Feature",
        glue = "classpath:stepDefinition",
        tags = "@checkingvariable",
        dryRun = false,
        monochrome = true,
        publish = true
)
public class RunCucumberTest extends AbstractTestNGCucumberTests {
}
