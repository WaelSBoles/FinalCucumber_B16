package steps;
// to stop repeating the object with every method in class we create the object here and extends it in commonmethod
// we extend PageInitializer in commonMethod
// we can have this class also in utilis

import Pages.*;

public class PageInitializer {
    // declare the object
    public static LoginPage loginPage;
    public static DashBoardPage dashBoardPage;

    public static AddEmployeePage addEmployeePage;


    public static EmployeeSearchPage employeeSearchPage;

    public static JobPage jobPage;

    // initialize the object by this method
    public static void initializePageObjects() {

        loginPage = new LoginPage();

        dashBoardPage = new DashBoardPage();

        addEmployeePage = new AddEmployeePage();

        employeeSearchPage = new EmployeeSearchPage();

        jobPage = new JobPage();

    }
}