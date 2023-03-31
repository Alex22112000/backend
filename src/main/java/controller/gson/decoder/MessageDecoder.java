package controller.gson.decoder;

import com.google.gson.Gson;

import controller.websocket.Message;
import jakarta.websocket.DecodeException;
import jakarta.websocket.Decoder;

public class MessageDecoder implements Decoder.Text<Message>{

    public static Gson gson = new Gson();

    @Override
    public Message decode(String jsoString) throws DecodeException {
        Message message = gson.fromJson(jsoString, Message.class);
        return message;
    }

    @Override
    public boolean willDecode(String jString) {
        return (jString != null);
    }
    
}
