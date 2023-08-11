package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.PageInitializer;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
// we extend PageInitializer for the locators
public class CommonMethods extends PageInitializer {
    // we have to use static keyword to not create an instance and let it be executed
    // we have to add keyword public static void to all methods in the common method
    public static WebDriver driver;// to use the webdriver with all classes
    public static void openBrowserAndNavigateToURL() {

        ConfigReader.readProperties(Constants.CONFIG_READER_PATH);

        switch (ConfigReader.getPropertyValue("browser")) {
            case "chrome":
// we use this code lines(33-36) in case we use Jenkins to not run the head without using the UI
                ChromeOptions ops = new ChromeOptions();
                ops.addArguments("--no-sandbox");
                ops.addArguments("--remote-allow-origins=*");
                ops.addArguments("--headless=new");

                driver = new ChromeDriver(ops);// we have to add (ops) to driver = new ChromeDriver(ops) to use Jenkins

                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
        }
        driver.manage().window().maximize();
        driver.get(ConfigReader.getPropertyValue("url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        // we have to add this method that we add in the pageIntializer to CommonMethod
        // this method will initialize all the objects available inside the method(that we create in(initializePage)for Pages Package
        // inside open method
        initializePageObjects();
    }

    public static void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
    // to clear the previous data
    public static void sendText(String text, WebElement element) {
        element.clear();
        element.sendKeys(text);
    }
    // for waiting(returning wait)
    public static WebDriverWait getWait(){
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        return wait;
    }
    public static void waitForClickability(WebElement element){
        getWait().until(ExpectedConditions.elementToBeClickable(element));

    }
    public static void click(WebElement element){
        waitForClickability(element);
        element.click();
    }

    public static void selectFromDropdown(WebElement dropDown, String visibleText) {
        Select sel = new Select(dropDown);
        sel.selectByVisibleText(visibleText);
    }

    public static void selectFromDropdown(String value, WebElement dropDown) {
        Select sel = new Select(dropDown);
        sel.selectByValue(value);
    }

    public static void selectFromDropdown(WebElement dropDown, int index) {
        Select sel = new Select(dropDown);
        sel.selectByIndex(index);
    }
    // to get the screen shot
    public static byte [] takeScreenshot(String fileName)  {
        TakesScreenshot ts = (TakesScreenshot) driver;

        // we write this line coz cucumber accepts array of byte for screenshot
        byte[] picBytes =ts.getScreenshotAs(OutputType.BYTES);

        File screenShot = ts.getScreenshotAs(OutputType.FILE);// we have to add the ext as .png
        //in case it doesn't find file name or path it will throw an exception
        try {
            FileUtils.copyFile(screenShot,
                    new File(Constants.SCREENSHOT_FILEPATH + fileName + " "
                            + getTimeStamp("MM-dd-yyyy-HH-mm-ss") + ".png"));
        }catch (IOException e){
            e.printStackTrace();
        }

        return picBytes;
}
// for the time stamp on the the screenshot
public static String getTimeStamp(String pattern){
        // it (displays)returns the current date and time in java
       Date date= new Date();
       //this function  sdf to format the date as per pattern we are passing
     SimpleDateFormat sdf=new SimpleDateFormat(pattern);
    // to return the formetted date
    return sdf.format(date);


}
}