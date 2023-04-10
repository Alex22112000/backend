package controller.websocket;

public class Message {
    private String text;
    private String username;
    private String date; 
    private String type;


    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setUsername(String username) {
        this.username= username;
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

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

