package empapp;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Random;

@Service
@Slf4j
public class LongProcessService {

    @SneakyThrows
    @Async
    public void process(DeferredResult<Integer> result) {
        log.info("Start long process in service");
        Thread.sleep(5000);

        var random = new Random();
        var number = random.nextInt(100);
        result.setResult(number);

        log.info("End long process in service");
    }
}
