package com.example.egringotts.utilities;

public class Secrets {
    /**
     * Method to get email address
     * @return email address of self
     */
    public static String getSenderEmail(){
        return "harimaudmn@gmail.com";
    }

    /**
     * Method to get location of secrets JSON
     * @return path to json containing API keys
     */
    public static String getJSON(){
        return "nothing-to-see.json";
    }

    /**
     * Method to get client ID for Google API
     * @return client id string
     */
    public static String getClientID(){
        return "270478638209-neebvqddq3bdbfsp3pcbshrut5hrpnt7.apps.googleusercontent.com";
    }

    /**
     * Method to get client secret for Google API
     * @return client secret string
     */
    public static String getClientSecret(){
        return "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";
    }
}