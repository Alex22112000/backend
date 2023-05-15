package application.chat;

import java.time.LocalDate;

import application.chat.message.Message;

public class Chat implements IChat {

    private String getDate(){
        return LocalDate.now().toString();
    }

    @Override
    public Message getMessage(String username, String text){
        Message message = new Message();
        message.setText(text);
        message.setUsername(username);
        message.setDate(getDate());
        message.setType("user");
        return message;
    }

    @Override
    public Message getHelloMessage(String username){
        Message message = new Message();
        message.setText("Пользователь " + username + " присоединился к чату.");
        message.setDate(getDate());
        message.setType("system");
        return message;
    }
}
