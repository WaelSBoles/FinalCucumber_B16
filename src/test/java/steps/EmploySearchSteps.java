package steps;


import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonMethods;

public class EmploySearchSteps extends CommonMethods {

    @When("user clicks on PIM option and employee list option")
    public void user_clicks_on_pim_option_and_employee_list_option() {
     //  WebElement PimOption=driver.findElement(By.id("menu_pim_viewPimModule"));
        //PimOption.click();
        click(dashBoardPage.PimOption);// coz we build the method in common Method
      //  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
       // WebElement empListOption=driver.findElement(By.id("menu_pim_viewEmployeeList"));
       // EmployeeOption.click();
        click(dashBoardPage.empListOption);
       // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @When("user enters the valid employee id")
    public void user_enters_the_valid_employee_id() {
       // WebElement searchIdtxtBox=driver.findElement(By.id("empsearch_id"));
        //searchIdtxtBox.sendKeys("54714A");
        sendText("54714A",employeeSearchPage.searchIdtxtBox);
        // WebElement searchIdTextBox = driver.findElement(By.id("empsearch_id"));
        // searchIdTextBox.sendKeys("54469A");

    }


    @When("user clicks on search button")
    public void user_clicks_on_search_button() {
       // WebElement searchBtn= driver.findElement(By.xpath("//input[@id='searchBtn']"));
       // searchBtn.click();
        click(employeeSearchPage.searchBtn);
    }

    @Then("user is able to get the employee information")
    public void user_is_able_to_get_the_employee_information() {
        System.out.println("we got the employee information");
    }
    @When("user enters the valid employee name")
    public void user_enters_the_valid_employee_name()  {
       // WebElement employeeNameTxtBox=driver.findElement(By.name("empsearch[employee_name][empName]"));
        // employeeNameTxtBox.sendKeys("Selab ms");
        sendText("Selab ms",employeeSearchPage.employeeNameTxtBox);
    }

}
