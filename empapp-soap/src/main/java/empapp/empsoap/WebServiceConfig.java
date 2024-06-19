package empapp.empsoap;

import jakarta.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class WebServiceConfig {

    @Bean
    public Endpoint employeeEndpoint(Bus bus, EmployeeWebService employeeWebService) {
        var endpoint = new EndpointImpl(bus, employeeWebService);
        endpoint.publish("/employees");
        return endpoint;
    }
}
