package framework;

import com.google.common.base.Strings;
import configuration.Log;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import java.util.concurrent.*;


public class BaseDriver implements WebDriver {

    protected WebDriver webdriver;
    private WebDriverWait wait;
    private String browser;
    private String homeUrl;
    private final int maxWaitTime = 15;

    public BaseDriver()
    {
        this.browser = getBrowserName();
        setBrowser(browser);
        this.webdriver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
        this.webdriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        this.webdriver.manage().window().setSize(new Dimension(1080, 1920));
        this.webdriver.manage().window().maximize();

        wait = new WebDriverWait(this.webdriver, maxWaitTime);
        Log.info("Webdriver launched successfully for: " + browser + " on " + System.getProperty("os.name").toLowerCase());
    }

    private String getBrowserName() {
        String browserFromEnvVariable = System.getenv("TestBrowserName");
        if(Strings.isNullOrEmpty(browserFromEnvVariable)) { return BrowserName.Firefox.toString(); }
        else return browserFromEnvVariable;
    }

    private void setBrowser(String browserName) {
       switch(browserName) {
           case "Chrome":
               WebDriverManager.chromedriver().setup();
               this.webdriver = new ChromeDriver();
               break;
           case "Firefox":
           default:
               WebDriverManager.firefoxdriver().setup();
               System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "null");
               this.webdriver = new FirefoxDriver();
               break;
       }
    }

    @Override public void get(String url) {webdriver.get(url);}
    @Override public String getCurrentUrl() {return webdriver.getCurrentUrl();}
    @Override public String getTitle() {return webdriver.getTitle();}
    @Override public List<WebElement> findElements(By by) {return webdriver.findElements(by);}
    @Override public WebElement findElement(By by) {return webdriver.findElement(by);}
    @Override public String getPageSource() {return webdriver.getPageSource();}
    @Override public void close() {webdriver.close();}
    @Override public void quit() {webdriver.quit();}
    @Override public Set<String> getWindowHandles() {return webdriver.getWindowHandles();}
    @Override public String getWindowHandle() {return webdriver.getWindowHandle();}
    @Override public TargetLocator switchTo() {return webdriver.switchTo();}
    @Override public Navigation navigate() {return webdriver.navigate();}
    @Override public Options manage() {return webdriver.manage();}

    public WebDriver getDriver() {
        return webdriver;
    }

    public String getBrowser() {
        return browser.toString();
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public String getHomeUrl() {
        return homeUrl;
    }

    public void setHomeUrl(String url) {
        this.homeUrl = url;
    }
}