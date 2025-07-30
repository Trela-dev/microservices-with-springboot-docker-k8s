package dev.trela.loans;

import dev.trela.loans.dto.LoansContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Loans microservice REST API documentation",
				description = "TrelaDev Loans microservice REST API documentation",
				version = "V1",
				contact = @Contact(
						name ="Marcin Trela",
						email = "marcin.trela.dev@gmail.com",
						url = "https://github.com/Trela-dev"
				),
				license=@License(
						name = "Apache 2.0",
						url = "https://github.com/Trela-dev"
				)

		),
		externalDocs = @ExternalDocumentation(
		description = "TrelaDev Loans microservice RESTAPI documentation",
				url = "https://github.com/Trela-dev"

)
)
@EnableConfigurationProperties(value = {LoansContactInfoDto.class})
@EnableDiscoveryClient
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
