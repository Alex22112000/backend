package controller.gson.encoder;

import com.google.gson.Gson;

import controller.websocket.Message;
import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;

public class MessageEncoder implements Encoder.Text<Message>{
    
    public static Gson gson = new Gson();

    @Override
    public String encode(Message object) throws EncodeException {
        return gson.toJson(object);
    }
}
