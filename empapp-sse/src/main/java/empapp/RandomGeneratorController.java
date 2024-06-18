package empapp;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/api/randoms")
@Slf4j
public class RandomGeneratorController {

    private final List<SseEmitter> emitters = Collections.synchronizedList(new LinkedList<>());

    private final Random random = new Random();

    @GetMapping
    public SseEmitter getRandoms() {
        var emitter = new SseEmitter();
        emitters.add(emitter);
        return emitter;
    }

    @Scheduled(fixedRate = 1000)
    public void generateRandomNumber() {
        var number = random.nextInt(100);
        var invalidEmitters = new ArrayList<>();
        for (var emitter : emitters) {
            try {
                emitter.send(number);
            } catch (Throwable t) {
//                log.error("Can not send", ioe);
                invalidEmitters.add(emitter);
            }
        }
        emitters.removeAll(invalidEmitters);
    }
}
