package stepDefinition;

import Utilities.extentreport;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    ExtentReports extent = extentreport.getReporterObject();
    ExtentTest test;

    @Before
    public void beforeScenario(Scenario scenario)
    {
        test = extent.createTest(scenario.getName());
        extentreport.setTest(test);
    }

    @After
    public void afterScenario(Scenario scenario)
    {
        if(scenario.isFailed())
        {
            test.fail("Scenario failed");
        }
        else {
            test.pass("Scenarion passed");
        }
        extent.flush();
    }
}
