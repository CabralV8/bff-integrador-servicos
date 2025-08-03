package com.cabral.bffgestaotarefas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BffGestaoTarefasApplication {

	public static void main(String[] args) {
		SpringApplication.run(BffGestaoTarefasApplication.class, args);
	}

}
