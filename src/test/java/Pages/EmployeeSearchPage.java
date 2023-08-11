package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class EmployeeSearchPage extends CommonMethods {

    @FindBy(id="empsearch_id")
    public WebElement searchIdtxtBox;

    @FindBy(name="empsearch[employee_name][empName]")
    public WebElement employeeNameTxtBox;


    @FindBy(xpath="//input[@id='searchBtn']")
    public WebElement searchBtn;

    public EmployeeSearchPage(){
        PageFactory.initElements(driver,this);
    }


}
