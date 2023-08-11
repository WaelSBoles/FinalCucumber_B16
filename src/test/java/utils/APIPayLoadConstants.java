package utils;


import org.json.JSONObject;

public class APIPayLoadConstants {
    public static String createEmployeePayload(){

        String createEmployeePayload = "{\n" +
                "    \"emp_firstname\": \"Wael\",\n" +
                "    \"emp_lastname\": \"Boles\",\n" +
                "    \"emp_middle_name\": \"s\",\n" +
                "    \"emp_gender\": \"M\",\n" +
                "    \"emp_birthday\": \"2023-07-23\",\n" +
                "    \"emp_status\": \"Happy\",\n" +
                "    \"emp_job_title\": \"QA\"\n" +
                "}";
        return createEmployeePayload;
    }
    // method using the json payload, will return the data in String format
    public static String createEmployeeJsonPayload(){
        JSONObject obj =new JSONObject() ;
        obj.put("emp_firstname","Wael");
        obj.put("emp_lastname","Boles");
        obj.put("emp_middle_name","s");
        obj.put("emp_gender","M");
        obj.put("emp_birthday","2023-07-23");
        obj.put("emp_status","Happy");
        obj.put("emp_job_title","QA");


        return obj.toString();
    }
    // for Dynamic way we use this method
// we pass the parametere in the method and add them to obj.put("emp_firstname", fn);
    public static String createEmployeeJsonPayloadDynamic
            (String fn, String ln, String mn, String gender,
             String dob, String status, String jobTitle){
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", fn);
        obj.put("emp_lastname", ln);
        obj.put("emp_middle_name", mn);
        obj.put("emp_gender", gender);
        obj.put("emp_birthday", dob);
        obj.put("emp_status",status);
        obj.put("emp_job_title",jobTitle);
        return obj.toString();
    }
// for partially update
    public static String updateEmployeePartiallyPayload(String empID,String key,String value){
        JSONObject obj = new JSONObject();
        obj.put("employee_id",empID);
        obj.put(key, value);
        return obj.toString();
    }



}

