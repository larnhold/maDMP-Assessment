package org.arnhold.evaluation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@ComponentScan(basePackages = {"at.ac.tuwien", "org.arnhold"})
public class EvaluationApplication {
	public static void main(String[] args) {
		SpringApplication.run(EvaluationApplication.class, args);
	}

}
