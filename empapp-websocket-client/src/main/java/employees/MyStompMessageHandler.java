package employees;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.WebSocketHttpHeaders;

import java.lang.reflect.Type;

@Slf4j
public class MyStompMessageHandler implements StompSessionHandler {

    @Override
    public void afterConnected(StompSession stompSession, StompHeaders stompHeaders) {
        log.info("Connected");
    }

    @Override
    public void handleException(StompSession stompSession, StompCommand stompCommand, StompHeaders stompHeaders, byte[] bytes, Throwable throwable) {

    }

    @Override
    public void handleTransportError(StompSession stompSession, Throwable throwable) {

    }

    @Override
    public Type getPayloadType(StompHeaders stompHeaders) {
        return null;
    }

    @Override
    public void handleFrame(StompHeaders stompHeaders, Object o) {

    }
}
