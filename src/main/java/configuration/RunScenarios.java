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
            features = {"src/test/java/com/mastercard/dis/mids/bdd/features"},
            glue = {"com.mastercard.dis.mids.bdd.steps"})
    public class RunScenarios {
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