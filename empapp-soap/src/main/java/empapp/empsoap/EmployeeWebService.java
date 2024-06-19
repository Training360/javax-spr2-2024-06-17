package empapp.empsoap;

import empapp.EmployeeService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@WebService(serviceName = "EmployeeWebService", targetNamespace = EmployeeWebService.EMPAPP_NAMESPACE)
@Service
@AllArgsConstructor
public class EmployeeWebService {

    public static final String EMPAPP_NAMESPACE = "http://training360.com/services/empapp";

    private final EmployeeService employeeService;

    @WebResult(name = "employeeResponse")
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
