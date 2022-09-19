package de.hwg_lu.java_star.utils;

public class Comment {

    String user = "";
    String comment = "";
    String time = "";
    
    public Comment(String user, String comment, String timestamp) {
        this.user = user;
        this.comment = comment;
        this.time = timestamp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    
    
}
