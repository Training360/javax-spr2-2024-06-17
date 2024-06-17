package empapp;

import empapp.dto.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

@AllArgsConstructor
@Slf4j
public class EmployeeJob extends QuartzJobBean {

    private EmployeeService employeeService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        List<EmployeeDto> employees = employeeService.listEmployees();
        log.info("Number of employees: {}", employees.size());
    }
}
