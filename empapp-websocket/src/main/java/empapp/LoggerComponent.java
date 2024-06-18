package empapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LoggerComponent {

    @EventListener
    public void handleEvent(Message message) {
        log.info("Event has come: {}", message);
    }
}
