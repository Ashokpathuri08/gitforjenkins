package com.foodhub.foodservice.config;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ErroeDetails {

    private Date timestamp;
    private String message;
    private String errorMessage;
    private String suggestions;

    public void printTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(timestamp);
        System.out.println("Timestamp: " + formattedDate);
    }


    public ErroeDetails(Date timestamp, String message, String errorMessage, String suggestions) {
        this.timestamp = timestamp;
        this.message = message;
        this.errorMessage = errorMessage;
        this.suggestions = suggestions;
    }



    public Date getTimestamp() {
        return timestamp;
    }


    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(String suggestions) {
        this.suggestions = suggestions;
    }
}
