package dev.trela.accounts.controller;


import dev.trela.accounts.dto.CustomerDetailsDto;
import dev.trela.accounts.dto.ErrorResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.ResponseEntity;

@Tag(
        name = "REST API for Customers in TrelaBank",
        description = "REST APIs in TrelaBank to fetch customer details"
)
public interface CustomerControllerApiDocumentation {

    @ApiResponse(
            responseCode = "200",
            description= "HTTP Status OK"
    )
    @ApiResponse(
            responseCode  = "404",
            description= "HTTP Status NOT FOUND",
            content=@Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            )

    )
    @ApiResponse(
            responseCode = "400",
            description= "HTTP Status BAD REQUEST",
            content=@Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            )
    )
    @ApiResponse(
            responseCode = "500",
            description= "HTTP Status INTERNAL SERVER ERROR",
            content=@Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            )
    )
    @Operation(
            summary= "Fetch Customer Details REST API",
            description = "REST API to fetch Customer details based on a mobile number"
    )
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(String correlationId,@Pattern(regexp = ("(^$|[0-9]{10})"), message = "Mobile number should be 10 digits") String mobileNumber);

}
