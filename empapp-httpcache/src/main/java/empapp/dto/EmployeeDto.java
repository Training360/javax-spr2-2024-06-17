package empapp.dto;

import java.util.List;

public record EmployeeDto(Long id, String name, int version, List<AddressDto> addresses) {

}
