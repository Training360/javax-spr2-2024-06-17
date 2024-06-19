package training.empappreactive;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @GetMapping
    public Flux<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping(value="messages", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Integer> getMessages() {
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1));
    }
}
