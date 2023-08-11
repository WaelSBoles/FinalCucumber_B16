package runner;
// for local execution purpose
// just 2 things required in this class(failedRunner) feature and glue

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
// notice we added @ before the package name(target)
        features = "@target/failed.txt",//Directory path// step Declaration

        glue = "steps",//we link the steps in jave with feature in resources(steps decalaration with the step definition)

        plugin = {"pretty"}

)
// note we use the annotations before the class name

public class FailedRunner {
}
