Feature: Search an employee information

  Background:
   # we use Background, so one backgroud for all the repeated steps in all test case
    #for repeating steps in all testcase like @beforeMethod
    #for openBrowser and close Browser we use Hooks Class

   # Given user is navigated to HRMS Application # we don't it anymore coz we create the Hooks class it will work from there
    When user enters admin username and password
    And user clicks on login button
    Then use is successfully logged in the application
    When user clicks on PIM option and employee list option
  #step declaration
  #we can use as many as tags before Scenario
  @regression @smoke @background #we use tagds in Runner class to execute the specific test case
  Scenario: Search name by id
  #  Given user is navigated to HRMS Application
   # When user enters admin username and password
   # And user clicks on login button
   # Then use is successfully logged in the application
   # When user clicks on PIM option and employee list option
    And user enters the valid employee id
    And user clicks on search button
    Then user is able to get the employee information

  @sprint20 @newtestCase @background

  Scenario: Search an employee by name
    And user enters the valid employee name
    And user clicks on search button
    Then user is able to get the employee information