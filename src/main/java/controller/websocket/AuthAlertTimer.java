package controller.websocket;

import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.ejb.Timeout;
import jakarta.ejb.Timer;
import jakarta.ejb.TimerService;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@Singleton
@ServerEndpoint("/authTimer")
public class AuthAlertTimer {

    @Resource
    TimerService timerService;

    private Session session;

    @OnOpen
    public void openConnection(Session session){
        this.session = session;
        //long ms = 24*60*60*1000 + 30*60*1000;
        long ms = 10*1000;
        timerService.createTimer(ms, "До истечения токена осталось 30 минут. \n Пожалуйста, перезайдите в систему!");
    }

    @OnClose
    public void closeConnection(Session session){
        this.session = null;
    }

    @Timeout
    public void handleTimer(Timer timer){
        this.session.getAsyncRemote().sendText((String)timer.getInfo());
    }
    
}