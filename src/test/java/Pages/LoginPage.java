package Pages;
//POM - page object model design pattern
//for every screen/page in the application, we create a separate  class for it and
//keep all the locators/webelements of this page in this class and we create a key of
//locators which we will pass in every step.
//enery class on steps has a Page
// we have to extend Pages to CommonMehtods
// this is object repository of POM
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class LoginPage extends CommonMethods {

    // this is object repository of POM

    @FindBy(xpath="//input[@id='txtUsername']")
    public WebElement userNameField;
    @FindBy(xpath="//input[@id='txtPassword']")
    public WebElement passwordField;

    @FindBy(xpath="//input[@id='btnLogin']")
    public WebElement loginBtn;
    @FindBy(id="spanMessage")
    public WebElement errorMessageField;

    // to initialize all the elements of this page we have to call them inside a constructor
    public LoginPage() {
        // function implemented inside
        // PageFactory is a class from Selenium to initialize the elements
        // this keyword that we use as pointer in Java for constructor
        //driver drom commonMthod and (this) keyword for the costructor
        PageFactory.initElements(driver,this);


    }
}
