package co.com.cmdb.initializer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"co.com.cmdb.controller"})
public class CmdbApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmdbApplication.class, args);
	}

}
