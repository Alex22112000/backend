package infrastructure.controller.websocket;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import application.alert.ISender;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/authTimer")
public class Alert implements ISender {

    private Session session;
    private static Set<Alert> services = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        services.add(this);
    }

    @OnClose
    public void onClose(Session session){
        services.remove(this);
    }  

    public void sendAll(String alert){
        services.forEach(chat -> {
            synchronized (chat){
                try {
                    chat.session.getBasicRemote().sendText(alert);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
