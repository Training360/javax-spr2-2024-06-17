package empapp;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
@Service
public class MessagesService {

    @Async
    @SneakyThrows
    public void sendMessages(SseEmitter emitter) {
        //        IntStream
//                .range(0, 10)
//                        .peek(i -> Thread.sleep(1000))
//                                .forEach(i -> emitter.send("hello"));
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            log.info("Send");
            emitter.send("hello");
        }
        emitter.complete();

    }
}
