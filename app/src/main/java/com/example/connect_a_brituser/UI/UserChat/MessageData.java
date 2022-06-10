package com.example.connect_a_brituser.UI.UserChat;

public class MessageData {


    String message,senderID;
    long timeStamp;

    public MessageData() {
    }

    public MessageData(String message, String senderID, long timeStamp) {
        this.message = message;
        this.senderID = senderID;
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
