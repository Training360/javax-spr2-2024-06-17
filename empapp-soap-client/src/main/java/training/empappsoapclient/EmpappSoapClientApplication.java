package training.empappsoapclient;

import com.training360.services.empapp.EmployeeWebService_Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class EmpappSoapClientApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EmpappSoapClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Hello SOAP");

		var service = new EmployeeWebService_Service();
		var port = service.getEmployeeWebServicePort();
		var response = port.listEmployees();
		var employees = response.getEmployees();
		for (var employee: employees.getEmployee()) {
			log.info("Employee: " + employee.getName());
		}
	}
}
