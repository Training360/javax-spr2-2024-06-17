package empapp;

import org.quartz.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class EmployeesApplication
{

	public static void main(String[] args) {
		SpringApplication.run(EmployeesApplication.class, args);
	}

	@Bean
	public JobDetail buildJobDetail() {
		JobDetail jobDetail = JobBuilder.newJob(EmployeeJob.class)
				.withIdentity(UUID.randomUUID().toString(), "employees-job")
				.withDescription("Print employees Job")
				//.usingJobData("key1", "value1")
				.storeDurably()
				.build();
		return jobDetail;
	}

	@Bean
	public Trigger buildJobTrigger(JobDetail jobDetail) {
		return TriggerBuilder.newTrigger()
				.forJob(jobDetail)
				.withIdentity("PrintEmployeesCountJob")
				.withDescription("Print employees Trigger")
				.withSchedule(CronScheduleBuilder.cronSchedule("*/10 * * * * ?"))
				.build();
	}

}
