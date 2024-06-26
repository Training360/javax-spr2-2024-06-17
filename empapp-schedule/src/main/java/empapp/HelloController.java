package empapp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
@AllArgsConstructor
@Slf4j
public class HelloController {

    private HelloService helloService;

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void hello() {
        log.info(helloService.getClass().getName());
        log.info("hello controller 3");
        helloService.hello();
    }
}
