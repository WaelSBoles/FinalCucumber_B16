package APIStepDef;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class GenerateTokenStep  {
// to generate the token automatically
    // we have to decalre the instance (baseURI and Token before the step
    String baseURI= RestAssured.baseURI="http://hrm.syntaxtechs.net/syntaxapi/api";
    // Create variable using public to be global, static to not use an object
    public static String token;
// from feature file
    @Given("a JWT is generated")
    public void a_jwt_is_generated() {
       //we write the code to generate JWT token automatically we need header() and Body(username and password)// from create tpken on PM
        RequestSpecification request= given().header("Content-Type", "application/json").
                                              body("{\n" +
                                                      "    \"email\": \"admin@new.com\",\n" +
                                                      "    \"password\": \"test123*\"\n" +
                                                      "}");

        Response response= request.when().post("generateToken.php");
        // we need to extraxt the token in form of json format so we need jsonPathon
        // so we use the variable token = "Bearer "+ then we add Bearer and one space then +
        token ="Bearer " + response.jsonPath().getString("token");
        System.out.println(token);


    }

}
