package com.example.user.testnotification;

public class AlertMessages {
    String key;
    String alarmName;
    String acknowledge;
    String dateTime;
    String userName;

    public AlertMessages() {
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmname) {
        this.alarmName = alarmname;
    }

    public String getAcknowledge() {
        return acknowledge;
    }

    public void setAcknowledge(String acknowledge) {
        this.acknowledge = acknowledge;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
