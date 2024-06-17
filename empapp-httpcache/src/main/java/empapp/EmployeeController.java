package empapp;

import empapp.dto.EmployeeDto;
import lombok.AllArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @GetMapping
    @SuppressWarnings("unused")
    public List<EmployeeDto> employees() {
        return employeeService.listEmployees();
    }

    @GetMapping("/{id}")
    @SuppressWarnings("unused")
    public ResponseEntity<EmployeeDto> findEmployeeById(@PathVariable("id") long id) {
        var employee = employeeService.findEmployeeById(id);
        return ResponseEntity
            .ok()
//                .eTag(Integer.toString(employee.hashCode()))
                .cacheControl(CacheControl.maxAge(Duration.of(1, ChronoUnit.HOURS)))
                .eTag(Integer.toString(employee.version()))
                .header("Request-Id", UUID.randomUUID().toString())
                .body(employee);
    }

    @PostMapping
    @SuppressWarnings("unused")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto command) {
        EmployeeDto employeeDto = employeeService.createEmployee(command);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(employeeDto.id()).toUri()).body(employeeDto);
    }

    @PutMapping("/{id}")
    @SuppressWarnings("unused")
    public EmployeeDto updateEmployee(@PathVariable("id") long id, @RequestBody EmployeeDto command) {
        return employeeService.updateEmployee(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @SuppressWarnings("unused")
    public void deleteEmployee(@PathVariable("id") long id) {
        employeeService.deleteEmployee(id);
    }

}
