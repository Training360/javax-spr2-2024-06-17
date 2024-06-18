package empapp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@AllArgsConstructor
public class MessagesController {

    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/messages")
    @SendTo("/topic/employees")
    public Message sendMessage(MessageCommand command) {
        log.info("Message received: " + command);
        return new Message("Reply: " + command.content());
    }

    @EventListener
    public void handleMessage(Message message) {
        log.info("Spring event has come: {}", message);

        messagingTemplate.convertAndSend("/topic/employees", message);
    }
}
