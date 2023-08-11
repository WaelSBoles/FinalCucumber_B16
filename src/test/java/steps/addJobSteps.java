package steps;

import Pages.JobPage;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utils.CommonMethods;
import utils.DBUtils;

import java.util.List;
import java.util.Map;

public class addJobSteps extends CommonMethods {
    String jTitleFN; // Fn (front End)
    String jDescFN;
    String jNoteFN;

    @When("user clicks on admin button")
    public void user_clicks_on_admin_button() {

       click(dashBoardPage.adminButton);// we use click() method here that we created in CommonMethod(for wait)

    }
    @When("user clicks on job")
    public void user_clicks_on_job() {
      click(dashBoardPage.adminJobButton);

    }
    @When("user clicks on job title")
    public void user_clicks_on_job_title() {
      click(dashBoardPage.adminJobTitleButton);
    }
    @When("user clicks on add  button")
    public void user_clicks_on_add_button() {
        click(jobPage.btnAdd);
    }
    @When("user enters job {string} title")// parameters in the feature file
    public void user_enters_job_title(String title) {
        sendText(title,jobPage.jobTitleF);
        jTitleFN = title;


    }
    //title,jobDescription,note),parameters are treated as local veriable ind order to use them globally we have to create them as instance
    // declare in  the class level to allow us to access it in the last step
    @When("user enters job description {string}")
    public void user_enters_job_description(String jobDescription) {
        sendText(jobDescription,jobPage.jobDescF);
        jDescFN=jobDescription;

    }

    @When("user enters job note {string}")
    public void user_enters_job_note(String note) {
   sendText(note,jobPage.jobNoteF);
   jNoteFN=note;
    }

    @When("user clicks on the save button")
    public void user_clicks_on_the_save_button() {
    click(jobPage.jobSvBtn);

    }
    @When("verify data is stored peoperly in database")
    public void verify_data_is_stored_peoperly_in_database() {
        // we get this query from mySQL, and replace jobtitle with the instance varible that we created at the class level
        String query = "select * from ohrm_job_title where job_title='" + jTitleFN + "' and job_description='" + jDescFN + "' and note='" + jNoteFN + "';";
        List<Map<String, String>> data = DBUtils.fetch(query);
        Map<String,String> firstROw=data.get(0);
        String jTitleBE=firstROw.get("job_title");
        String jDescBE=firstROw.get("job_description");
        String jNoteBE=firstROw.get("note");

        Assert.assertEquals(jTitleFN,jTitleBE);
        Assert.assertEquals(jDescFN,jDescBE);
        Assert.assertEquals(jNoteFN,jNoteBE);
    }

}
