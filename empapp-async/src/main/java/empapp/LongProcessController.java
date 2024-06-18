package empapp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@RequestMapping("/api/long-process")
@Slf4j
@AllArgsConstructor
public class LongProcessController {

    private final LongProcessService longProcessService;

    @GetMapping
    public DeferredResult<Integer> startLongProcess() {
        // Ezt még a http szál szolgálja ki
        log.info("Starting long process");
        var result = new DeferredResult<Integer>();

        // Hívni service metódust új szálon!
        longProcessService.process(result);

        log.info("Ending long process");
        return result;
    }
}
