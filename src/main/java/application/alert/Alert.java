package application.alert;

public class Alert implements IAlert {

    private ISender sender;

    @Override
    public void setSender(ISender sender) {
        this.sender = sender;
    }

    @Override
    public void sendAll(String alert) {
        sender.sendAll(alert);
    }
    
}
