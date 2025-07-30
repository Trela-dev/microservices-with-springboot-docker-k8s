package dev.trela.accounts;

import dev.trela.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
// Open api for swagger documentation
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts microservice REST API Documentation",
		description =  "TrelaDev Accounts microservice REST API Documentation",
				version = "v1",
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
				url="https://github.com/Trela-dev"
		)

)
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
@EnableFeignClients
@EnableDiscoveryClient
public class AccountsApplication {
	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}
}
