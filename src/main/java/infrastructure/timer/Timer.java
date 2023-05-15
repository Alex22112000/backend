package infrastructure.timer;

import java.util.Date;

import application.alert.IAlert;
import infrastructure.builder.Build.Built;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.Timeout;
import jakarta.ejb.TimerConfig;
import jakarta.ejb.TimerService;
import jakarta.inject.Inject;

@Singleton
@Startup
public class Timer {

    @Inject @Built
    private IAlert alert;

    @Resource
    TimerService timerService;

    @PostConstruct    
    public void start() {
        timerService.createIntervalTimer(new Date(), 70000, new TimerConfig()); 
    }
  
    @Timeout
    public void timeout() {
        alert.sendAll("Присоединяйтесь к нашему телеграмм каналу, чтобы не пропустить \nежедневные акции и специальные предложения. \nСсылка на наш канал: https://t.me/GluttonyShop");
    }

}
