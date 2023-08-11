Feature: adding new jobs in HRMS

  Background:
    When user enters admin username and password
    And user clicks on login button
   And use is successfully logged in the application
    @addJob
    Scenario: user adds a new job
    * user clicks on admin button
    * user clicks on job
    * user clicks on job title
    * user clicks on add  button
    * user enters job " QA A" title
    * user enters job description " Teaches Java"
    * user enters job note "java programming note"
    * user clicks on the save button
    * verify data is stored peoperly in database