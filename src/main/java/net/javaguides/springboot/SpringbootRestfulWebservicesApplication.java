package net.javaguides.springboot;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "SpringBoot-restfull webservices", description = "SpringBoot-restfull webservices-demo", version = "V1.0", contact = @Contact(name = "SAI", email = "saisayyapureddy112233@gmail.com", url = "http://localhost:8080/swagger-ui/index.html"

), license = @License(name = "Appache2.0", url = "http://localhost:8080/swagger-ui/index.html"

)

), externalDocs = @ExternalDocumentation(

		description = "springboot api management documentation", url = "http://localhost:8080/swagger-ui/index.html")

)
public class SpringbootRestfulWebservicesApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();

	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestfulWebservicesApplication.class, args);
	}

}
