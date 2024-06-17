package empapp;

import empapp.dto.EmployeeDto;
import empapp.entity.Employee;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    private EmployeeMapper employeeMapper;

    @CachePut(value = "employee", key="#result.id()")
    @CacheEvict(value = "employees", allEntries = true)
    public EmployeeDto createEmployee(EmployeeDto command) {
        Employee employee = employeeMapper.toEmployee(command);
        employeeRepository.save(employee);
        return employeeMapper.toEmployeeDto(employee);
    }

    @Cacheable("employees")
    public List<EmployeeDto> listEmployees() {
        return employeeMapper.toEmployeesDto(employeeRepository.findAllWithAddresses());
    }

    @Cacheable("employee")
    public EmployeeDto findEmployeeById(long id) {
        return employeeMapper.toEmployeeDto(employeeRepository.findByIdWithAddresses(id)
                        .orElseThrow(notFoundException(id)));
    }

    @Transactional
//    @CacheEvict(value = "employee", key="#id")
    @CachePut(value = "employee", key="#id")
    @CacheEvict(value = "employees", allEntries = true)
    public EmployeeDto updateEmployee(long id, EmployeeDto command) {
        Employee employeeToModify = employeeRepository
                .findById(id)
                .orElseThrow(notFoundException(id));
        employeeToModify.setName(command.name());
        return employeeMapper.toEmployeeDto(employeeToModify);
    }

    @Caching(evict = {
            @CacheEvict("employee"),
            @CacheEvict(value = "employees", allEntries = true)
    })
    public void deleteEmployee(long id) {
        Employee employee = employeeRepository.findByIdWithAddresses(id)
                .orElseThrow(notFoundException(id));
        employeeRepository.delete(employee);
    }

    private static Supplier<NotFoundException> notFoundException(long id) {
        return () -> new NotFoundException("Employee not found with id: " + id);
    }

}
