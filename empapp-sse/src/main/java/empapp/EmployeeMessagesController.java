package empapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.*;

@Controller
@RequestMapping("/api/employees/messages")
@Slf4j
public class EmployeeMessagesController {

    private final List<SseEmitter> emitters = Collections.synchronizedList(new LinkedList<>());

    @GetMapping
    public SseEmitter connect() {
        log.info("Connect");
        var emitter = new SseEmitter();
        emitters.add(emitter);
        return emitter;
    }

    @EventListener
    public void handleEvent(EmployeeHasBeenCreatedEvent event) {
        var invalidEmitters = new ArrayList<>();
        for (var emitter : emitters) {
            try {
                var builder = SseEmitter.event()
                        .id(UUID.randomUUID().toString())
                        .comment("random number")
                        .name("create_employee")
                        .reconnectTime(10_000)
                        .data(event);

                emitter.send(builder);
            } catch (Throwable t) {
//                log.error("Can not send", ioe);
                invalidEmitters.add(emitter);
            }
        }
        emitters.removeAll(invalidEmitters);
    }
}
