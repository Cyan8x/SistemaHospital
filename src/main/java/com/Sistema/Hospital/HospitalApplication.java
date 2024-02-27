package com.Sistema.Hospital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class HospitalApplication { //SOLO PARA DESPLIGUE => extends SpringBootServletInitializer

	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);
	}

	//SOLO PARA DESPLIGUE
	//@Override
	//protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	//	return application.sources(HospitalApplication.class);
	//}
	//SOLO PARA DESPLIGUE
}
