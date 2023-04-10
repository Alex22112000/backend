package controller.json.encoder;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import controller.websocket.Message;
import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;

public class MessageEncoder implements Encoder.Text<Message>{
    
    private static Jsonb jsonb = JsonbBuilder.create();

    @Override
    public String encode(Message message) throws EncodeException {
        return jsonb.toJson(message);
    }
}
