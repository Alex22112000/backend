package infrastructure.controller.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import application.chat.IChat;
import application.chat.message.Message;
import application.chat.message.json.decoder.MessageDecoder;
import application.chat.message.json.encoder.MessageEncoder;
import jakarta.inject.Inject;
import jakarta.websocket.EncodeException;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/chat/{username}",
                decoders = MessageDecoder.class,
                encoders = MessageEncoder.class)
public class Chat {

    @Inject
    private IChat chat;

    private Session session;
    private static Set<Chat> chatServices = new CopyOnWriteArraySet<>();
    private static HashMap<String, String> users = new HashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) throws IOException, EncodeException{
        this.session = session;
        chatServices.add(this);
        users.put(session.getId(), username);
        sendAll(chat.getHelloMessage(username));
    }

    @OnClose
    public void onClose(Session session){
        chatServices.remove(this);
    }  

    @OnMessage
    public void onMessage(Session session, Message message) throws IOException, EncodeException{
        sendAll(chat.getMessage(message.getUsername(), message.getText()));
    }

    public void sendAll(Message message){
        chatServices.forEach(chat -> {
            synchronized (chat){
                try {
                    chat.session.getBasicRemote().sendObject(message);
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}