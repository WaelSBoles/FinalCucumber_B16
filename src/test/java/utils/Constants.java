package utils;

public class Constants {
    // to make  path Dynamic we create the Constants
    // public to access it from anywhere
    //we use static because  we do not want to create an object and save memory
    // FINAL so none can change it.
    //define things that will not change
    public static final String CONFIG_READER_PATH = System.getProperty("user.dir") + "/src/test/resources/config/config.properties";
    // we will create screenshot
    // we create the screenshot folder here to store screenshot
    public static final String SCREENSHOT_FILEPATH = System.getProperty("user.dir") + "/screenshots/";

    public static final String EXCEL_READER_PATH =System.getProperty("user.dir")+"/src/test/resources/testdata/Book1.xlsx";

}



