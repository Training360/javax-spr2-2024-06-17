package empapp;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HelloService {

    @SneakyThrows
    @Async
    public void hello() {
        Thread.sleep(5000);
        log.info("hello");
    }

    @Scheduled(fixedRate = 5000)
    public void scheduled() {
        log.info("tick");
    }
}
