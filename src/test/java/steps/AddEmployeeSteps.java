package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;
import utils.Constants;
import utils.DBUtils;
import utils.ExcelReader;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {
    // declare instance variable to access this info anywhere in the class(DB 7/26/23)
    String fnFirstName;
    String fnMiddleName;
    String fnLastName;
    String empId;

    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        //WebElement PimOption=driver.findElement(By.id("menu_pim_viewPimModule"));
       // PimOption.click();
        click(dashBoardPage.PimOption);// this the new method that we created in CommonMethod Class
    }
    @When("user clicks on add employee button")
    public void user_clicks_on_add_employee_button() {
     //WebElement addEmployeeButton= driver.findElement(By.id("menu_pim_addEmployee"));
      // addEmployBtn.click();
       click(dashBoardPage.addEmployeeButton);

    }
    @When("user enters firstname and lastname")
    public void user_enters_firstname_and_lastname() {
      //  WebElement firstNameTextField=driver.findElement(By.id("firstName"));
       // WebElement lastNameTextField=driver.findElement(By.id("lastName"));
       // firstNameTextField.sendKeys("Aendro");
        //lastNameTextField.sendKeys("Moraga");
        sendText("Wael",addEmployeePage.firstNameTextField);
        sendText("Boles",addEmployeePage.lastNameTextField);
    }
    @Given("click on save button")
    public void click_on_save_button() {
      //  WebElement saveButton = driver.findElement(By.id("btnSave"));
       // saveButton.click();
        click(addEmployeePage.saveButton);
        takeScreenshot("addEmployee");
    }
    @Then("employee added succesfully")
    public void employee_added_succesfully() {
        System.out.println("employee added succesfully");
    }


    @When("user enters {string} and {string} and {string}")
    public void user_enters_and_and(String firstName, String middleName, String lastName) {

        this.fnFirstName =firstName;
        this.fnMiddleName =middleName;
        this.fnLastName =lastName;

        sendText(firstName,addEmployeePage.firstNameTextField);
        sendText(middleName,addEmployeePage.middleNameTextField);
        sendText(lastName,addEmployeePage.lastNameTextField);
        empId= addEmployeePage.employeeIdField.getAttribute("value");// to get emp id # // we did,t this keyword here coz there is no confilct

    }
    @When("user enters {string} and {string} and {string} in data driven format")
    public void user_enters_and_and_in_data_driven_format(String firstName, String middleName, String lastName) {
        sendText(firstName,addEmployeePage.firstNameTextField);
        sendText(middleName,addEmployeePage.middleNameTextField);
        sendText(lastName,addEmployeePage.lastNameTextField);
    }
    @When("user enters firstname and middlename and lastname and verify employee has added")
    public void user_enters_firstname_and_middlename_and_lastname_and_verify_employee_has_added
            (io.cucumber.datatable.DataTable dataTable) {
        // we need list of maps to get multiple values from datatable which is coming from feature file
        // we decalered anew variable"employeeNames" and used the datatable above as Maps().
        List<Map<String,String>> employeeNames=dataTable.asMaps();
        for (Map<String, String> employee : employeeNames) {
            // in maps to get the firstname we use .get()

            String firstNameValue=employee.get("firstName");
            String middleNameValue=employee.get("middleName");
            String lastNameValue=employee.get("lastName");
            // here we say send the value that we created in @datatable "firstname" to the addNamepage in the firstname text field
            sendText(firstNameValue,addEmployeePage.firstNameTextField);
            sendText(middleNameValue,addEmployeePage.middleNameTextField);
            sendText(lastNameValue,addEmployeePage.lastNameTextField);
// to save that employee we click
            click(addEmployeePage.saveButton);
            //after adding one employee, we will add another employee
            // for this we are clicking on add employee button in the loop itself
            click(dashBoardPage.addEmployeeButton);

        }
    }
    @When("user adds multiple employees using excel from {string} and verify it")
    public void user_adds_multiple_employees_using_excel_from_and_verify_it
            (String sheetName) throws InterruptedException {
        //here we are getting the data from excel file using parameters(sheetName) from ExcelReader class
        // we call ExcelReader class and read Method,and Constants with the path
        List<Map<String, String>> newEmployees =
                ExcelReader.read(sheetName, Constants.EXCEL_READER_PATH);
// using Itorator to iterate the new employees
        Iterator<Map<String, String>> itr = newEmployees.iterator();

        //while , will check whether we have new element/value or not
        while (itr.hasNext()){

            //in this map, we have data from every single employee one by one it will give us that data
            Map<String,String> mapNewEmp =  itr.next();
            //we are filling(adding to website) the employee data now using mapNewEmp variable
            //BATCH 16, KEYS WHAT WE ARE PASSING HERE SHOULD MATCH WITH THE KEYS IN EXCEL
            sendText(mapNewEmp.get("firstName"),addEmployeePage.firstNameTextField);
            sendText(mapNewEmp.get("middleName"), addEmployeePage.middleNameTextField);
            sendText(mapNewEmp.get("lastName"), addEmployeePage.lastNameTextField);
            sendText(mapNewEmp.get("photograph"), addEmployeePage.photograph);
            Thread.sleep(3000);

            //we can enter username and password only after selecting the checkbox
            if(!addEmployeePage.checkBoxLocator.isSelected()){
                click(addEmployeePage.checkBoxLocator);
            }
            sendText(mapNewEmp.get("username"),addEmployeePage.usernameTextFieldBox);
            sendText(mapNewEmp.get("password"), addEmployeePage.passwordTextFieldBox);
            sendText(mapNewEmp.get("confirmPassword"), addEmployeePage.confirmPasswordBox);

            //here we are fetching the employee id from the UI using get attribute method
            String empIdValue = addEmployeePage.employeeIdField.getAttribute("value");
            Assert.assertTrue(addEmployeePage.saveButton.isDisplayed());// for verifying
            click(addEmployeePage.saveButton);
            Thread.sleep(3000);
            //we have to verify that the employee has been added, so we gotta gp tho dashboardPage and sendtexID to get the id
            click(dashBoardPage.empListOption);
            Thread.sleep(3000);
            //searching the employee using emp id which we just got
            sendText(empIdValue, employeeSearchPage.searchIdtxtBox);
            click(employeeSearchPage.searchBtn);

            //print the value from the table row
            List<WebElement> rowData = driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr"));
            for (int i=0; i<rowData.size(); i++){
                System.out.println("I am inside the loop");
                //it will return one by one all the data from the row
                String rowText = rowData.get(i).getText();
                //it will print the complete row data
                //output of this will be empid firstname middlename lastname
                System.out.println(rowText);
                //we have to verify this data against the data coming from excel
                                  // "empIdValue" comping from UI and "the rest" coming from excel sheetss
                String expectedData = empIdValue + " "+mapNewEmp.get("firstName")+" "+
                        mapNewEmp.get("middleName")+" "+mapNewEmp.get("lastName");

                Assert.assertEquals(expectedData, rowText);
                //you can use below code too to verify the data
                //  Assert.assertTrue(expectedData.equals(rowText));

            }
            //to add more employees we need to click on add employee button
            click(dashBoardPage.addEmployeeButton);
        }
    }
    // SQL DATA BAse 7/26/23
// we try to fetch the info that we entered (backend)
    @Then("verify employee is stored in database")
    public void verifyEmployeeIsStoredInDatabase() {
   String query="select emp_firstName,emp_middle_name,emp_lastname from hs_hr_employees where employee_id="+empId+";";
       List<Map<String,String>> mapList= DBUtils.fetch(query);// will return a list of Maps, so we need return type List<Map<String,String>>
       Map<String,String> firstRow=mapList.get(0);
        String dbFirstName=firstRow.get("emp_firstName");
       String dbMiddleName=firstRow.get("emp_middle_name");
       String dbLastName=firstRow.get("emp_lastname");                    // Message, expected,actual
       Assert.assertEquals("FirstName from frontend does not match the firstname from dataBase", fnFirstName,dbFirstName);
       Assert.assertEquals("MiddletName from frontend does not match the middlename from dataBase", fnMiddleName,dbMiddleName);
       Assert.assertEquals("LastName from frontend does not match the LastName from dataBase", fnLastName,dbLastName);


    }
}
