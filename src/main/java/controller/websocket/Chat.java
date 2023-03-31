package controller.websocket;

import java.io.IOException;
import java.time.LocalDate;

import controller.gson.decoder.MessageDecoder;
import controller.gson.encoder.MessageEncoder;
import jakarta.websocket.EncodeException;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint(
    value = "/chat",
    decoders = MessageDecoder.class,
    encoders = MessageEncoder.class
)

public class Chat {

    private String getCurrentDate(){
        return LocalDate.now().toString();
    }
    
    @OnOpen
    public void onOpen(Session session) throws IOException, EncodeException{
        Message message = new Message();
        message.setText("Пользователь присоединился к чату");
        //message.setDate(LocalDate.now());
        //session.getBasicRemote().sendObject(message);
    }

    @OnMessage
    public void onMessage(Session session, Message message) throws IOException, EncodeException{
        message.setDate(this.getCurrentDate());
        for(Session sess : session.getOpenSessions()){
            sess.getBasicRemote().sendObject(message);
        }
    }
}