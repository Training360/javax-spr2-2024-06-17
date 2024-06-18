package empapp;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
@RequestMapping("/api/messages")
@AllArgsConstructor
public class MessagesController {

    private final MessagesService messagesService;

    @GetMapping
    public SseEmitter getMessages() {
        var emitter = new SseEmitter();
        messagesService.sendMessages(emitter);
        return emitter;
    }
}
