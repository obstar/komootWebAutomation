package configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {
    private static Logger Log = LoggerFactory.getLogger(Log.class.getName());

    public static void startTestCase(String scenarioName) {
        info("Start scenario: " + scenarioName);
    }

    public static void endTestCase(String scenarioName) {
        info("End of scenario: " + scenarioName);
    }

    public static void info(String message) {
        Log. info(message);
    }

    public static void warn(String message) {
        Log.warn(message);
    }

    public static void error(String message) {
        Log.error(message);
    }

    public static void debug(String message) {
        Log.debug(message);
    }
}