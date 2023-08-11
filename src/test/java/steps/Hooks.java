package steps;
// we must create Hooks class in the Steps Package

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.CommonMethods;
// basically Hooks for launching and closing the browser
// Background to the repeated steps

public class Hooks extends CommonMethods {
    //we have to use   @before io.cucumber.java
   // pre condition of all the test cases
    @Before
    public void start(){
        openBrowserAndNavigateToURL();

    }
    // @After io.cucumber.java
    //@after - post condition of all the test cases
    @After
    // for screenshot we need
    //Scenario class (in cucumber framework)holds all the complete info of my execution

    public void end(Scenario scenario){
        // we need this variable (byte[] pic;) coz my screenshot method returns array of bytes
    byte[] pic;
// (if condition )here to  hold (and decides)the test case,if it  fails so the Screenshot goes to the failed folder,
// and if it's passed it goes to passed folder
        if(scenario.isFailed()) {
            // we capture the screenshot and attach it to the report
            // for fail folder/ if the Test case fails, the SS goes to failed folder with its name(scenario.getName()); to get the scenario name
            pic = takeScreenshot("failed/" + scenario.getName());// scenario.getName()will return the name of scenario
        }else{ // for passed scenarios folder
            pic = takeScreenshot("passed/" + scenario.getName());

        }

        // attach this screenshot to the report
        scenario.attach(pic,"image/png",scenario.getName());

        closeBrowser();


    }

}
