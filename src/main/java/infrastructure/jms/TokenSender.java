package infrastructure.jms;

import application.auth.token.IToken;
import jakarta.annotation.Resource;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSConsumer;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSProducer;
import jakarta.jms.Message;
import jakarta.jms.Queue;

public class TokenSender implements IToken {

    @Resource(mappedName = "jms/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "jms/TokenQueue")
    private Queue queueToken;

    @Resource(mappedName = "jms/BackendQueue")
    private Queue queueBackend;

    @Override
    public String createToken(String login) {
        try {
            JMSContext context = connectionFactory.createContext();
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(queueBackend);
            Message message = context.createMessage();
            message.setJMSType("createToken");
            message.setStringProperty("login", login);
            message.setStringProperty("role", "");
            producer.send(queueToken, message);
            Message answer = consumer.receive();
            return answer.getStringProperty("token");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean checkToken(String login, String token) {
        try {
            JMSContext context = connectionFactory.createContext();
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(queueBackend);
            Message message = context.createMessage();
            message.setJMSType("checkToken");
            message.setStringProperty("login", login);
            message.setStringProperty("token", token);
            producer.send(queueToken, message);
            Message answer = consumer.receive();
            return answer.getBooleanProperty("check");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
