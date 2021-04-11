package framework;

import configuration.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static steps.ScenarioHooks.browserActions;

public class BrowserActions extends BaseDriver {

    public BrowserActions() {
        super();
    }

    public void navigateTo(String url)  {
        webdriver.navigate().to(url);
        waitForAjaxToFinish();
    }

    public void click(By target)  {
        waitElementExists(target);
        try{
            webdriver.findElement(target).click();
        }
        catch(Throwable t){
            Log.info("Known bug with Edge browser detected 'Element is obscured', using workaround javascript click");
            WebElement element = webdriver.findElement(target);
            JavascriptExecutor executor = (JavascriptExecutor)webdriver;
            executor.executeScript("arguments[0].click();", element);
        }
        waitForAjaxToFinish();
    }

    public void sendKeys(By target, String textToSend){
        waitElementExists(target);
        clearText(target);
        webdriver.findElement(target).sendKeys(textToSend);
        waitForAjaxToFinish();
    }

    public void sendKeys(By target, Keys keyToSend){
        waitElementExists(target);
        clearText(target);
        webdriver.findElement(target).sendKeys(keyToSend);
        waitForAjaxToFinish();
    }

    public void selectListValueByIndex(By target, int index) {
        waitElementExists(target);
        Select select = new Select(webdriver.findElement(target));
        select.selectByIndex(index);
        waitForAjaxToFinish();
    }

    public void selectListValueByText(By target, String text) {
        waitElementExists(target);
        Select select = new Select(webdriver.findElement(target));
        select.selectByVisibleText(text);
        waitForAjaxToFinish();
        int iWaitTime = 0;
        while(!getListItemText(target).contains(text)){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            iWaitTime++;
            if (iWaitTime==10){break;}
        }
    }

    public String getText(By target) {
        waitElementExists(target);
        return webdriver.findElement(target).getText();
    }

    public String getInnerHtml(By target) {
        waitElementExists(target);
        return webdriver.findElement(target).getAttribute("innerHTML");
    }

    public String getListItemText(By target) {
        waitElementExists(target);
        Select select = new Select(webdriver.findElement(target));
        return select.getFirstSelectedOption().getText();
    }

    public boolean checkTextExists(String text) {
        return webdriver.getPageSource().toLowerCase().contains(text.toLowerCase());
    }

    public boolean verifyImage(By by)  {
        WebElement ImageFile = webdriver.findElement(by);
        return  (Boolean) ((JavascriptExecutor)webdriver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", ImageFile);
    }

    public void waitElementExists(By target) {
        getWait().until(ExpectedConditions.presenceOfElementLocated(target));
    }

    public void clearText(By target) {
        try{
            if(!webdriver.findElement(target).getAttribute("innerHTML").equals("") ||
                    !webdriver.findElement(target).getText().equals("")){
                webdriver.findElement(target).clear();
            }
        }catch(Throwable t){
            standardWarningOutput(t.getMessage());
        }
    }

    public int countMatchingElements(By target) {
        try{
            return webdriver.findElements(target).size();
        }
        catch(Throwable t){
            standardWarningOutput(t.getMessage());
        }
        return 0;
    }

    public List<WebElement> getAllMatchingElements(By target)  {
        try{
            return webdriver.findElements(target);
        }
        catch(Throwable t){
            standardWarningOutput(t.getMessage());
        }
        return null;
    }

    public boolean checkElementExists(By target) {
        try{
            return webdriver.findElements(target).size() > 0;
        }
        catch(Throwable t){
            standardWarningOutput(t.getMessage());
        }
        return false;
    }

    public boolean checkElementDisplayed(By target) {
        try{
            if (checkElementExists(target)){
                return webdriver.findElement(target).isDisplayed();
            }
            return false;
        }catch(Throwable t){
            standardWarningOutput(t.getMessage());
        }
        return false;
    }

    public boolean checkElementEnabled(By target) {
        try{
            if (checkElementExists(target)){
                return webdriver.findElement(target).isEnabled();
            }
            return false;
        }
        catch(Throwable t){
            standardWarningOutput(t.getMessage());
        }
        return false;
    }

    public void waitElementVisible(By target) {
        try{
            getWait().until(ExpectedConditions.visibilityOfElementLocated(target));
        }
        catch (Throwable t){
            standardWarningOutput(t.getMessage());
        }
    }

    public void waitElementInvisible(By target) {
        try{
            getWait().until(ExpectedConditions.invisibilityOfElementLocated(target));
        }
        catch(Throwable t){
            standardWarningOutput(t.getMessage());
        }
    }

    public void waitElementClickable(By target) {
        try{
            getWait().until(ExpectedConditions.elementToBeClickable(target));
        }
        catch(Throwable t){
            standardWarningOutput(t.getMessage());
        }
    }

    public void waitElementNotClickable(By target) {
        try{
            getWait().until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(target)));
        }
        catch(Throwable t){
            standardWarningOutput(t.getMessage());
        }
    }

    public void moveToElementAndWait(By target) {
        long startTime = System.currentTimeMillis();
        waitElementExists(target);
        try{
            WebElement element = webdriver.findElement(target);
            ((JavascriptExecutor) webdriver).executeScript("arguments[0].scrollIntoView(true);", element);
            Actions action = new Actions(webdriver);
            action.moveToElement(webdriver.findElement(target)).perform();
        }
        catch(Throwable t){
            standardWarningOutput(t.getMessage());
        }
        waitForAjaxToFinish();
        waitElementVisible(target);
        highLightElement(target);

        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        if (duration > 5000){
            Log.info("[warning] " + getBrowser() + " Execution time for move_to_element_and_verify took: " + duration + "MS");
        }
    }

    public void scrollByPixel(int pixels) {
        try{
            ((JavascriptExecutor) webdriver).executeScript("window.scrollBy(0," + pixels +")", "");
            Thread.sleep(500);
        }
        catch(Throwable t){
            standardWarningOutput(t.getMessage());
        }
    }

    public void scrollBottomPage()  {
        try{
            ((JavascriptExecutor) webdriver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Thread.sleep(500);
        }
        catch(Throwable t){
            standardWarningOutput(t.getMessage());
        }
    }

    public void scrollTopPage() {
        try{
            ((JavascriptExecutor) webdriver).executeScript("window.scrollTo(0, 0)");
            Thread.sleep(500);
        }
        catch(Throwable t){
            standardWarningOutput(t.getMessage());
        }
    }

    public void setAttributeElementId(String elementId, String attributeName, String attributeValue) {
        try{
            String script = "document.getElementById('"+ elementId + "')" +
                    ".setAttribute('" + attributeName + "', '" + attributeValue + "')";
            ((JavascriptExecutor) webdriver).executeScript(script);
            Thread.sleep(500);
        }
        catch(Throwable t){
            standardWarningOutput(t.getMessage());
        }
    }

    public void slideHorizontallyByPixels(By target, int slideByPixels) {
        Actions slider=new Actions(browserActions.getDriver());
        Action action = slider.dragAndDropBy(browserActions.findElement(target), slideByPixels, 0).build();
        action.perform();
    }

    public void highLightElement(By target)  {
        try{
            WebElement we = webdriver.findElement(target);
            ((JavascriptExecutor) webdriver).executeScript("arguments[0].style.border='3px dotted blue'", we);
        }
        catch(Throwable t){
            standardWarningOutput(t.getMessage());
        }
    }

    public void deleteCookies() {
        try{
            if (webdriver.getCurrentUrl().equals("data:,") || webdriver.getCurrentUrl().equals("about:blank")) {
                navigate();
            }
            webdriver.manage().deleteAllCookies();
        }
        catch(Throwable t){
            standardWarningOutput(t.getMessage());
        }
    }

    public void waitForPageLoad() {
        try{
            new WebDriverWait(webdriver, 30).until((ExpectedCondition<Boolean>) wd ->
                    ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
        }
        catch(Throwable t){
            standardWarningOutput(t.getMessage());
        }
    }

    public void waitForAjaxToFinish() {
        long startTime = System.currentTimeMillis();
        waitForPageLoad();
        try{
            webdriver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
            ((JavascriptExecutor) webdriver).executeAsyncScript(
                    "var callback = arguments[arguments.length - 1];" +
                            "var xhr = new XMLHttpRequest();" +
                            "xhr.open('POST', '/" + "selenium_call" + "', true);" +
                            "xhr.onreadystatechange = function() {" +
                            "  if (xhr.readyState == 4) {" +
                            "    callback(xhr.responseText);" +
                            "  }" +
                            "};" +
                            "xhr.send();");
        }
        catch(Throwable t){
            standardWarningOutput(t.getMessage());
        }
        finally{
            long endTime = System.currentTimeMillis();
            long duration = (endTime - startTime);
        }
    }

    public void gotoNewTab() {
        try{
            String parentWindow;
            String childWindow;
            parentWindow = webdriver.getWindowHandle();
            childWindow = null;
            Set<String> allWindows =  webdriver.getWindowHandles();

            for(String wHandle: allWindows){
                if (wHandle != parentWindow) {
                    childWindow = wHandle;
                }
            }

            int attempts=1;
            if (!childWindow.equals(parentWindow)){
                while(webdriver.getWindowHandle().equals(parentWindow)) {
                    webdriver.switchTo().window(childWindow);
                    attempts++;
                }
            }
        }
        catch(Throwable t){
            standardWarningOutput(t.getMessage());
        }
    }

    private void standardWarningOutput(String message){
        Log.info("[Warning]");
        Log.info(message);
        Log.info("");
        Log.info("[Continuing test scenerio]");
        Log.info("Selenium will fail if normal execution flow is impacted");
        Log.info("");
    }
}