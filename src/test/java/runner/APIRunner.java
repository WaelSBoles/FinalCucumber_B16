package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        // features we use to provide the path of all the features files
        features = "src/test/resources/features/",//Directory path// step Declaration
        // glue keyword we use to provide the path of the package of step definitions that are available
        glue = "APIStepDef",//we link the steps in jave with feature in resources(steps decalaration with the step definition)
        // dryRun = true it stops actual expectation, it quickly scans all the steps and
        // we use only to get the new code or missing code we add to step declaration
        //dryRun = true// it will provide the missing step definition
        dryRun =false,// we have to set it to false to run and execute test case
        // to execute the specific testcase that we need
        // we apply tags like smoke or regression before Scenario
        // we have to use @ with tags
        // we use (or) to execute as many as test case we need
        //we use (and) in case the expectation for the same testcase
        // tags - to execute scenarios of our choice, we can use different tags in scenarios
        // tags = "@smoke"
        //tags="@sprint1 or @sprint20 or smoke  "
        tags = "@api",
        // for monochrome= true,to clean up the console ouput
        //it means sometimes the console output for cucumber test is having some
        //irrelevant information, when you set it to true, it removes all that
        //irrelevant information from the console and will give you simple output
        monochrome = true,
        // "perrty" it used to print all the steps in console(local report)
        // html plugin is generting the report, this cucumber report will be  generated under target folder
        plugin = {"pretty"}
        //html plugin to generate the report
        // to generate html report we need to type "html:target/cucumber.html", this will generate the html report
        // in target package/
        // we use json:target/cucumber.json from pom.xml
        // we have to add this path (rerun:target/failed.txt)to create the failed file
        // for the failed testcase we type this code "rerun:target/failed.txt", this will appear in target package as failed
        // to rerun another time the failed testcase we create another class as failed runner

)
// note we use the annotations before the class name


public class APIRunner {
}
