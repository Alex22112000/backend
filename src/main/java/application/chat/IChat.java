package application.chat;

import application.chat.message.Message;

public interface IChat {
    Message getMessage(String username, String text);
    Message getHelloMessage(String username);
}
