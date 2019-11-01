package escuelaing.edu.co.TaskPlaner;

import escuelaing.edu.co.TaskPlaner.config.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TaskPlanerApplication {
        
    @Bean
	public FilterRegistrationBean jwtFilter()
	{
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter( new JwtFilter() );
		registrationBean.addUrlPatterns( "/taskplanner/*" );

		return registrationBean;
	}
	public static void main(String[] args) {
		SpringApplication.run(TaskPlanerApplication.class, args);
	}

}
