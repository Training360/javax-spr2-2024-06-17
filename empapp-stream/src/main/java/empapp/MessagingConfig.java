package empapp;

import empapp.dto.EmployeeDto;
import empapp.dto.TextMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
import java.util.function.Function;

@Configuration
@Slf4j
public class MessagingConfig {

    @Bean
    public Consumer<EmployeeDto> handleMessage() {
        return employeeDto -> log.info("Message has come: {}", employeeDto);
    }

    @Bean
    public Function<TextMessage, TextMessage> sayHello() {
        return input -> new TextMessage("Hello:" + input.text());
    }
}
