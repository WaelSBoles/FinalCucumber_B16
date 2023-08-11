Feature: adding employee in HRMS application
  #we use * instead of the KeyWords like, Given, And ,then
  Background:
    * user enters admin username and password
    * user clicks on login button
    * use is successfully logged in the application
    * user clicks on PIM option
    * user clicks on add employee button
  @test
  Scenario: adding one employ

    * user enters firstname and lastname
    * click on save button
    * employee added succesfully
     @sample
    Scenario: Adding one employee from feature file
       #when we use " "  we call this "value"
      When  user enters "Wael" and "Samir" and "Boles"
      * click on save button
      * employee added succesfully
   @outline
       Scenario Outline:adding multiple employee using scenario outlina
     #when we use <> this is key
     When  user enters "<firstName>" and "<middleName>" and "<lastName>" in data driven format
     And click on save button
     Then employee added succesfully
     Examples:
       |firstName|middleName|lastName|
       |leny     |Darzi     |fraud   |
       |Celina   |Wael      |Boles   |
       |Natalie  |Wael      |Boles   |
@datatable #in data table there is just one line as it shows below
     Scenario: adding multiple employees using data table
       When user enters firstname and middlename and lastname and verify employee has added
       |firstName|middleName|lastName|
       |Zara     |ms        |Zaca    |
       |Mary     |ms        |Django  |
       |Sadam    |ms        |Adam    |

@excel  #in excel there is just one line as it shows below
  Scenario: adding multiple employees using excel file
  #we type the sheet name here (not the file name)
  When user adds multiple employees using excel from "EmployeeDataBatch16" and verify it

  @DB
  Scenario: Adding one employee from feature file
       #when we use " "  we call this "value"
    When  user enters "Wael" and "Samir" and "Boles"
    * click on save button
    * employee added succesfully
    Then verify employee is stored in database