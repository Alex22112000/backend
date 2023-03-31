package controller.websocket;

public class Message {
    private String text;
    private String username;
    private String date; 

    public void setText(String text) {
        this.text = text;
    }

    public void setUsernameString(String username) {
        this.username= username;
    }

    public String getText() {
        return text;
    }

    public String getUsername() {
        return username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

