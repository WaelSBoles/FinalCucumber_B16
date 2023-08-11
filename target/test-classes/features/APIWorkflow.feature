Feature:  syntax API HRMS Flow

  Background:
    Given  a JWT is generated
    #1-first way to create an employee
@api
  Scenario: Creating the employee using API

    Given a request is prepared for creating an employee
    When  a post call is made to create an employee
    Then the status code for creating an employee is 201
   And the employee created contains key "Message" and the value "Employee Created"
  And the employee id "Employee.employee_id" is stored as a global variable

  @api
  Scenario: Get the created employee
    Given a request is prepared for retrieving an employee
    When a GET call is made to retrieve the employee
    Then the status code for this employee is 200
    And the employee id "employee.employee_id" must match with globally stored employee id
    #we try to match the body in Get call(line .17) with body in post call(line .9)
    #"employee" here from get to match the data used in create employee
    And this employee data at "employee" object matches with the data used to create the employee
      | emp_firstname|emp_lastname|emp_middle_name|emp_gender|emp_birthday|emp_status|emp_job_title|
      |Wael           |Boles      |s              |Male        |2023-07-23  |Happy  |QA           |
    #2- second way to create an employee using json body to get the data in String format
@json
    Scenario: Creating an employee using json body
      Given  a request is prepared for creating an employee using json payload
      When  a post call is made to create an employee
      Then the status code for creating an employee is 201
      And the employee created contains key "Message" and the value "Employee Created"
      And the employee id "Employee.employee_id" is stored as a global variable
# 3- third way we passing the data directly from the feature file(to create an employee)
  @dynamic
  Scenario: Creating an employee using highly dynamic scenario
    Given a request is prepared for creating an employee with data "Wael" , "Boles" , "s" ,"M","2023-07-23","Happy","QA"
    When  a post call is made to create an employee
    Then the status code for creating an employee is 201
    And the employee created contains key "Message" and the value "Employee Created"
    And the employee id "Employee.employee_id" is stored as a global variable
