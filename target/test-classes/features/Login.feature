Feature: login related scenarios
  Background:
    #Given user is navigated to HRMS Application # we don't need it anymore coz we create the Hooks class it will work from there

  #step declaration
  @smoke @sprint1 @newTestcase @newFeature @sprint2 @login1
  Scenario: valid admin login
  #  Given user is navigated to HRMS Application
    When user enters admin username and password
    And user clicks on login button
    Then use is successfully logged in the application

    # normal employee login
@employee @sprint1 @login1
  Scenario: valid ess login
   # Given user is navigated to HRMS Application
    When user enters ess username and password
    And user clicks on login button
    Then use is successfully logged in the application
@invalid @sprint1 @login
  Scenario: invalid Admin login
   # Given user is navigated to HRMS Application
    When user enters invalid admin username and password
    And user clicks on login button
    Then error message is displayed


    #when we provide <> first it will pop up automatically in examples:
  #when we verify the invalid credentials(invalid login) we use Scenario Outline:
  @negative
  Scenario Outline: negative login test
    When user enters "<username>" and "<password>" and verifying the "<error>" for the combinations
    Examples:
      | username | password | error |
      |admin     |fkfkkkj   |Invalid credentials|
      |admin1    |Hum@nhrm123|Invalid credentials|
      |          |Hum@nhrm123|Username cannot be empty|
      |admin     |           |Password cannot be empty|




    #step Definition theully logged in the application