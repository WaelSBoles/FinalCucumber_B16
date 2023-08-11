package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
//import static org.hamcrest.CoreMatchers.equalTo;
// to keep the order(alphaptic order) of executing the test case
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamples {

        //baseURI = baaseURL + endpoint
        // given -preparation  baseURi
        //when - hitting the endpoint
        // base URI = base URL
        // Datatype       Rest assured        value(BaseURL)
    // coa this is automation we use only the URL
                                                  // in the URI we have to add (http://) before it
        String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
        // value of token should be same as Postman
        String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2OTA5Nzk1NTcsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY5MTAyMjc1NywidXNlcklkIjoiNTY1NiJ9.AOJxD64Vjqxyjbqd0_-mpnAvQxNvnD4GNTZQ9po9Zak";
        static String employee_id;// instance to be global// in a class level to use it eveywhere
         // in this method we are going to create an employee
        // we need headers , body to prepare the request
        @Test
        public void acreateEmployee(){
            //1- prepare the request
          RequestSpecification request= given().header("Content-Type", "application/json").
                    header("Authorization", token).body("{\n" +
                            "    \"emp_firstname\": \"Wael\",\n" +
                            "    \"emp_lastname\": \"Boles\",\n" +
                            "    \"emp_middle_name\": \"s\",\n" +
                            "    \"emp_gender\": \"M\",\n" +
                            "    \"emp_birthday\": \"2023-07-23\",\n" +
                            "    \"emp_status\": \"Happy\",\n" +
                            "    \"emp_job_title\": \"QA\"\n" +
                            "}");

          //2- hitting the end point// send the request
            // we use Response coz we need the response
            // we use the variable request.when().post(endpoint)
       Response response= request.when().post("/createEmployee.php");
       //3- verifing the reponse/ assertion and verification
       response.then().assertThat().statusCode(201);
          // this is the method that we use to print the response of API in console
            response.prettyPrint();
            // whatever we need to verify we use response variable
            // verify all the values and headers from response

            response.then().assertThat().body("Employee.emp_firstname",equalTo("Wael"));
            response.then().assertThat().body("Employee.emp_middle_name",equalTo("s"));
            response.then().assertThat().body("Employee.emp_lastname",equalTo("Boles"));
            response.then().assertThat().body("Message",equalTo("Employee Created"));
            response.then().assertThat().header("X-Powered-By","PHP/7.2.18");
            // it will return the employee id(in Jason Format) and save it in variable(emoloyee_id)
            employee_id =response.jsonPath().getString("Employee.employee_id");
            System.out.println(employee_id);


        }
     @Test
      public void bgetCreatedEmployee(){
        RequestSpecification request = given().header("Authorization", token)
                .queryParam("employee_id", employee_id);
        Response response = request.when().get("/getOneEmployee.php");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
        // cucumber assert Junit
        String tempEmpId = response.jsonPath().getString("employee.employee_id");
        Assert.assertEquals(employee_id, tempEmpId);

        }

        @Test
    public void cUpdateEmployee(){
            RequestSpecification request= given().
                    header("Content-Type", "application/json").
                    header("Authorization", token).
                    body("{\n" +
                            "  \"employee_id\": \""+employee_id+"\",\n" +
                            "  \"emp_firstname\": \"Lol\",\n" +
                            "  \"emp_lastname\": \"Boles\",\n" +
                            "  \"emp_middle_name\": \"Tiko\",\n" +
                            "  \"emp_gender\": \"M\",\n" +
                            "  \"emp_birthday\": \"2020-01-01\",\n" +
                            "  \"emp_status\": \"employed\",\n" +
                            "  \"emp_job_title\": \"Egypt\"\n" +
                            "}");

            Response response=request.when().put("\n" +
                    "/updateEmployee.php");
            response.then().assertThat().statusCode(200);
            response.then().assertThat().body("Message",equalTo("Employee record Updated"));



        }
        // get updatedEmployee(to retrieve the update)
    @Test
    public void dgetUpdatedEmployee(){
        RequestSpecification request = given().header("Authorization", token)
                .queryParam("employee_id", employee_id);
        Response response = request.when().get("/getOneEmployee.php");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
        //String tempEmpId = response.jsonPath().getString("employee.employee_id");
        //Assert.assertEquals(employee_id, tempEmpId);

    }
    }
