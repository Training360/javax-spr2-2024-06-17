package empapp.empsoap;

import empapp.EmployeeService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@WebService
@Service
@AllArgsConstructor
public class EmployeeWebService {

    private final EmployeeService employeeService;

    public ListEmployeesRespons listEmployees() {
        return new ListEmployeesRespons(
                employeeService
                        .listEmployees()
                        .stream()
                        .map(e -> new Employee(e.id(), e.name()))
                        .toList()
        );
    }
}
