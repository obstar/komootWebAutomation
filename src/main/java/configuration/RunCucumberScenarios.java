package configuration;

import context.AppContext;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "summary", "json:target/report/cucumber.json", "junit:target/report/cucumber.xml"},
        features = {"src/main/java/resources/features"},
        glue = {"steps"})
public class RunCucumberScenarios {
    @BeforeClass
    public static void initAll() {
        Log.info("STARTING Cucumber Session");
        Log.info("Website url: " + AppContext.Url);
    }

    @AfterClass
    public static void tearDownAll() {
        Log.info("END OF Cucumber Session");
    }
}