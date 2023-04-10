package controller.websocket;

import java.io.IOException;
import java.time.LocalDate;

import controller.json.decoder.MessageDecoder;
import controller.json.encoder.MessageEncoder;
import jakarta.websocket.EncodeException;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint(
    value = "/chat/{username}",
    decoders = MessageDecoder.class,
    encoders = MessageEncoder.class
)

public class Chat {

    private String getCurrentDate(){
        return LocalDate.now().toString();
    }
    
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) throws IOException, EncodeException{
        Message message = new Message();
        message.setType("server");
        message.setText("Пользователь " + username + " присоединился к чату");
        //message.setDate(LocalDate.now());
        // session.getBasicRemote().sendObject(message);

        for(Session sess : session.getOpenSessions()){
            sess.getBasicRemote().sendObject(message);
        }
    }

    @OnMessage
    public void onMessage(Session session, Message message) throws IOException, EncodeException{
        message.setType("user");
        message.setDate(this.getCurrentDate());
        for(Session sess : session.getOpenSessions()){
            sess.getBasicRemote().sendObject(message);
        }
    }

    /*
    @OnMessage
    public void onMessage(Session session, Message message) throws IOException, EncodeException{
        message.setType("user");
        broadcast(message, session);
    }

    private void broadcast(Message message, Session session) throws IOException, EncodeException{
        for(Session sess : session.getOpenSessions()){
            sess.getBasicRemote().sendObject(message);
        }
    }
    */
}