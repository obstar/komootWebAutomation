package steps;

import configuration.Log;
import framework.BrowserActions;
import io.cucumber.java.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class ScenarioHooks {
    public static BrowserActions browserActions = null;

    @Before()
    public static void InitialTest(Scenario scenario) {
        Log.startTestCase(scenario.getName());
        browserActions = new BrowserActions();

    }

    @After()
    public void TearDown(Scenario scenario) {
        Log.endTestCase(scenario.getName());
        browserActions.getDriver().manage().deleteAllCookies();
        browserActions.getDriver().navigate().refresh();
        browserActions.getDriver().close();
    }
}