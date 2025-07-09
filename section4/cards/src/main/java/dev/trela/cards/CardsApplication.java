package dev.trela.cards;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Cards microservice RESTAPI documentation",
				description = "TrelaDev Cards microservice REST API documentation",
				version = "V1",
				contact = @Contact(
						name = "Marcin Trela",
						email = "marcin.trela.dev@gmail.com",
						url = "https://github.com/Trela-dev"
				),
				license=@License(
						name = "Apache 2.0",
						url = "https://github.com/Trela-dev"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "TrelaDev Accounts microservice REST API Documentation",
				url = "https://github.com/Trela-de"
)
)
public class CardsApplication {
	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}
}
