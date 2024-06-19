package empapp;

import empapp.dto.EmployeeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class MessagingConfig {

    @Bean
    public Consumer<EmployeeDto> handleMessage() {
        return employeeDto -> log.info("Message has come: {}", employeeDto);
    }
}
