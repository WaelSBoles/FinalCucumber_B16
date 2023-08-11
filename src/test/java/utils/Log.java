package utils;
import org.apache.log4j.Logger;
// customized functions to log the data
// this class (log) works as System.out.printLn, it has prepared method to print out
// we need to add the dependency to pom .xml
// also we need to add the file log4j.xml to target package and add the code
public class Log {






        // Initialize Log4j logs

        private static Logger Log = Logger.getLogger(Log.class.getName());

        // This is to print log for the beginning of the test case, as we usually run so many test cases as a test suite

        public static void startTestCase(String sTestCaseName){

            Log.info("****************************************************************************************");

            Log.info("****************************************************************************************");

            Log.info("$$$$$$$$$$$$$$$$$$$$$                 "+sTestCaseName+ "       $$$$$$$$$$$$$$$$$$$$$$$$$");

            Log.info("****************************************************************************************");

            Log.info("****************************************************************************************");

        }

        //This is to print log for the ending of the test case

        public static void endTestCase(String sTestCaseName){

            Log.info("provide info            "+"-E---N---D-"+"             XXXXXXXXXXXXXXXXXXXXXX");

            Log.info("Chaymae");

            Log.info("Chaymae");

            Log.info("Chaymae");

            Log.info("Chaymae");

        }

        // Need to create these methods, so that they can be called

        public static void info(String message) {

            Log.info(message);

        }

        public static void warn(String message) {

            Log.warn(message);

        }

        public static void error(String message) {

            Log.error(message);

        }

        public static void fatal(String message) {

            Log.fatal(message);

        }

        public static void debug(String message) {

            Log.debug(message);

        }
    }

