package APIStepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utils.APIConstants;
import utils.APIPayLoadConstants;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class APIWorkFlowSteps {
    // we decalre these instance to access from anywhere
    RequestSpecification request;
    Response response;
    public static String employee_id;

    @Given("a request is prepared for creating an employee")
    public void a_request_is_prepared_for_creating_an_employee() {
        //1- prepare the request, we need (2 headers and 1 body)
        /*request = given().header("Content-Type", "application/json").
                // to call the token we call the class name (GenerateTokenStep)(in which we generated the token) before the variable(token)
                        header("Authorization", GenerateTokenStep.token).
                         body("{\n" +
                        "    \"emp_firstname\": \"Wael\",\n" +
                        "    \"emp_lastname\": \"Boles\",\n" +
                        "    \"emp_middle_name\": \"s\",\n" +
                        "    \"emp_gender\": \"M\",\n" +
                        "    \"emp_birthday\": \"2023-07-23\",\n" +
                        "    \"emp_status\": \"Happy\",\n" +
                        "    \"emp_job_title\": \"QA\"\n" +
                        "}");*/
        request = given().header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE).// see APIConstant
                // to call the token we call the class name (GenerateTokenStep)(in which we generated the token) before the variable(token)
                        header(APIConstants.HEADER_AUTHORIZATION_KEY, GenerateTokenStep.token).
                body(APIPayLoadConstants.createEmployeePayload());// see APIPayloadConstants


    }

    @When("a post call is made to create an employee")
    public void a_post_call_is_made_to_create_an_employee() {

      //  response = request.when().post("/createEmployee.php");
        response = request.when().post(APIConstants.CREATE_EMPLOYEE_URI);

        response.prettyPrint();// to print
    }

    @Then("the status code for creating an employee is {int}")
    //see here we pass the para as statuscode
    public void the_status_code_for_creating_an_employee_is(Integer statusCode) {
                                            // we use the parameter status code
        response.then().assertThat().statusCode(statusCode);


    }

    @Then("the employee created contains key {string} and the value {string}")
    // we pass key and valye here
    public void the_employee_created_contains_key_and_the_value(String key, String value) {
        response.then().assertThat().body(key, equalTo(value));
    }

    @Then("the employee id {string} is stored as a global variable")
    public void the_employee_id_is_stored_as_a_global_variable(String empId) {
        // we need to store the value of empId(para))(in form of json format(jsonPath().getString(empId))store it  in the global variable (employee_id)
        // here we have to greate a global varible for employee_id as ( public static String employee_id;) and we use (empId) as parameter

        employee_id = response.jsonPath().getString(empId); // <----

        System.out.println(employee_id);

    }
    //------------------------------------------------------------------------------------------//

    @Given("a request is prepared for retrieving an employee")
    public void a_request_is_prepared_for_retrieving_an_employee() {
        request = given().header(APIConstants.HEADER_AUTHORIZATION_KEY, GenerateTokenStep.token)
                                //"employee_id" as key, employee_id varible)
                .queryParam("employee_id", employee_id);
    }

    @When("a GET call is made to retrieve the employee")
    public void a_get_call_is_made_to_retrieve_the_employee() {
       // response = request.when().get("/getOneEmployee.php");
        response = request.when().get(APIConstants.GET_EMPLOYEE_URI); // see APIConstant
    }

    @Then("the status code for this employee is {int}")
    public void the_status_code_for_this_employee_is(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    @Then("the employee id {string} must match with globally stored employee id")
    public void the_employee_id_must_match_with_globally_stored_employee_id(String empId) {
        // we create a new varible for temp empId
        String temporaryEmpid = response.jsonPath().getString(empId);
        // we make asseration between the employee_id and temporaryEmpid
        Assert.assertEquals(employee_id, temporaryEmpid);


    }
// DataTable
    @Then("this employee data at {string} object matches with the data used to create the employee")
    public void this_employee_data_at_object_matches_with_the_data_used_to_create_the_employee
            // we pass here employeeObject  from feature.file as PAR
            (String employeeObject, io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // we start with the expected data as list
        List<Map<String,String>> expectedData= dataTable.asMaps();
       // we need all object we call .get() instead of  .getStringMethod
       Map<String,String>  actualData=response.body().jsonPath().get(employeeObject);
       // for  each loop and we need a map here coz we need just one employee(if there are multiple we use List)
       for (Map<String,String > map:expectedData){
           // keyset to get all the keys
           Set<String> keys =map.keySet();// return all the keys-(set)to keep the order and to avoid duplicate value
           // we get key one by one
           for (String key:keys){
               // from the key, we will get value
               String expectedValue= map.get(key);
               String actualValue= actualData.get(key);
               Assert.assertEquals(expectedValue,actualValue);
           }
       }

    }
    @Given("a request is prepared for creating an employee using json payload")
    public void a_request_is_prepared_for_creating_an_employee_using_json_payload() {
        request = given().header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE).// see APIConstant
                // to call the token we call the class name (GenerateTokenStep)(in which we generated the token) before the variable(token)
                        header(APIConstants.HEADER_AUTHORIZATION_KEY, GenerateTokenStep.token).
                body(APIPayLoadConstants.createEmployeeJsonPayload());// see APIPayloadConstants
    }

    @Given("a request is prepared for creating an employee with data {string} , {string} , {string} ,{string},{string},{string},{string}")
    public void a_request_is_prepared_for_creating_an_employee_with_data
            // we have to add the varible name // see the APIPayLoadConstants
            (String fn, String ln, String mn, String gender, String dob, String status, String jobTitle) {
        request = given().header(APIConstants.HEADER_CONTENT_TYPE_KEY,
                        APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY, GenerateTokenStep.token).
                                                                     // we have to pass the parameters
                body(APIPayLoadConstants.createEmployeeJsonPayloadDynamic(fn,ln,mn,gender,dob,status,jobTitle));//see APIPayLoadConstants
    }

}