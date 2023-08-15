package br.com.wk.taskhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class TaskhubApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskhubApplication.class, args);
    }

}
