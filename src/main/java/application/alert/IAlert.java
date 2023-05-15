package application.alert;

public interface IAlert {
    void setSender(ISender sender);
    void sendAll(String alert);
}
