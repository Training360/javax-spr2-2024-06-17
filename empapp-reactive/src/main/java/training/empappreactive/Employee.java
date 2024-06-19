package training.empappreactive;

import org.springframework.data.annotation.Id;

public record Employee(@Id long id, String name) {
}
