package co.com.aruma;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class SpringTestApplication implements ApplicationListener<ContextRefreshedEvent> {

	public static void main(String[] args) {
		SpringApplication.run(SpringTestApplication.class, args);
	}

	@Value("${spring.application.name}") String name;
	@Value("${spring.application.version}") String version;
	@Value("${spring.application.restPort}") String restPort;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

        log.info("{} Application started", name);
        log.info("Port: {}", restPort);
        log.info("Version: {}", version);
        log.info("Launched [OK]");

    }

}
