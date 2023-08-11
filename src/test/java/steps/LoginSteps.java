package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Assert;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.Log;

//for the nullpointer exception we need to check(the spelling of words)the keys that we tyed like"username, password,firstname
public class LoginSteps extends CommonMethods {
    // public WebDriver driver;//instance
    @Given("user is navigated to HRMS Application")
    public void user_is_navigated_to_hrms_application() {
        openBrowserAndNavigateToURL();
       // driver=new ChromeDriver();
       // driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
       // driver.manage().window().maximize();

    }
    @When("user enters admin username and password")
    public void user_enters_admin_username_and_password() {
        // creating the object of the class to access all the web element from it after we create loginpage POM
        // we created loginPage and extendes pageInitializer  to commonmethod to stop repeating the object in Mehtod
       // LoginPage loginPage=new LoginPage();
      //  WebElement userNameField= driver.findElement(By.xpath("//input[@id='txtUsername']"));
       // WebElement passwordField=driver.findElement(By.xpath("//input[@id='txtPassword']"));
        // we make it generic by using ConfigReader.getPropertyValue("username")
       // userName.sendKeys(ConfigReader.getPropertyValue("username"));// fromConfireader+(config.properties)
       // password.sendKeys(ConfigReader.getPropertyValue("password"));
       // sendText("admin",userName);
      //  sendText("Hum@nhrm123",password);
        // we use this code below in case we use a sensitive data like valid userName and password from ConfigReader.getPropertyValue
                                                  // username here from config.ptoperties ,userNameField the variable
                      // loginPage from loginPage when we store the locators pages and pageIntializer

        DOMConfigurator.configure("log4j.xml");// for log4J, it works like System.out.printLN{}
        Log.startTestCase("My batch 16 test case starts here");// for log4J, it works like System.out.printLN{}
        sendText(ConfigReader.getPropertyValue("username"),loginPage.userNameField);
        sendText(ConfigReader.getPropertyValue("password"),loginPage.passwordField);
        Log.info("my password has beed entered");// for log4J, it works like System.out.printLN{}


    }
    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        //LoginPage loginPage=new LoginPage();// we create the object
      //  WebElement loginBtn=driver.findElement(By.xpath("//input[@id='btnLogin']"));
       // loginBtn.click();// we commented this out coz we created a Generic or Dynamic method below in Commonmethod
        click(loginPage.loginBtn);
    }
    @Then("use is successfully logged in the application")
    public void use_is_successfully_logged_in_the_application() {
        System.out.println("My test case is passed");
    }
    @When("user enters ess username and password")
    public void user_enters_ess_username_and_password() {
      //  LoginPage loginPage=new LoginPage();// we create the object
      //  WebElement userNameField= driver.findElement(By.xpath("//input[@id='txtUsername']"));
       // userNameField.sendKeys("dalima123");
        sendText("dalima123",loginPage.userNameField);
       // WebElement passwordField=driver.findElement(By.xpath("//input[@id='txtPassword']"));
       // passwordField.sendKeys("Hum@nhrm123");
        sendText("Hum@nhrm123",loginPage.passwordField);


    }
    @When("user enters invalid admin username and password")
    public void user_enters_invalid_admin_username_and_password() {
       // LoginPage loginPage=new LoginPage();// we create the object
       // WebElement userNameField= driver.findElement(By.xpath("//input[@id='txtUsername']"));
      //  WebElement passwordField=driver.findElement(By.xpath("//input[@id='txtPassword']"));
       // userNameField.sendKeys("admin23");
        //passwordField.sendKeys("Hum@nhrm123");
        sendText("admin23",loginPage.userNameField);
        sendText("Hum@nhrm123",loginPage.passwordField);
        takeScreenshot("login");

    }
    @Then("error message is displayed")
    public void error_message_is_displayed() {
        System.out.println("Error message is displayed ");



    }
    @When("user enters {string} and {string} and verifying the {string} for the combinations")
    public void user_enters_and_and_verifying_the_for_the_combinations
            (String username, String password, String errorMessageExpected) {
        sendText(username, loginPage.userNameField);
        sendText(password, loginPage.passwordField);
        click(loginPage.loginBtn);
     // fetching error message from the web element
        String errorMessageActual=loginPage.errorMessageField.getText();
     // error message coming from feature file which we can compare
        // so if your code right in the login feature file , the messages will not show up in the console
        Assert.assertEquals("Value does not match",errorMessageExpected,errorMessageActual);




    }



}
