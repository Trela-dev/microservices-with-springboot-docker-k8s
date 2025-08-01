package dev.trela.loans.controller;

import dev.trela.loans.dto.ErrorResponseDto;
import dev.trela.loans.dto.LoanDto;
import dev.trela.loans.dto.LoansContactInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.ResponseEntity;

@Tag(
        name = "CRUD REST APIs for loans in TrelaBank",
        description = "CRUD REST APIs in TRELABANK to CREATE_UPDATE_FETCH AND DELETE loans details"
)
public interface LoansApiDocumentation {

    @Operation(
            summary = "Create Loan REST API",
            description = "REST API to create new Loan in TrelaBank"
    )
    @ApiResponse(
                    responseCode = "201",
                    description = "Http Status CREATED"
            )
    @ApiResponse(
                    responseCode = "409",
                    description = "Http Status CONFLICT",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )

            )
    @ApiResponse(
                    responseCode = "500",
                    description = "Http Status INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    public ResponseEntity<LoanDto> createLoan(@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits") String mobileNumber);
    @Operation(
            summary = "Fetch Loan REST API",
            description = "REST API to fetch Loan details in TrelaBank by mobile number"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status OK"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Http Status NOT FOUND",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            )

    )
    @ApiResponse(
            responseCode = "500",
            description = "Http Status INTERNAL SERVER ERROR",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            )
    )
    public ResponseEntity<LoanDto> fetchLoanDetails(String correlationId,@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits") String mobileNumber);
    @Operation(
            summary = "Update Loan REST API",
            description = "REST API to update Loan details in TrelaBank"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status OK"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Http Status INTERNAL SERVER ERROR",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            )
    )
    @ApiResponse(
            responseCode = "500",
            description = "Http Status BAD REQUEST",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            )
    )
    public ResponseEntity<LoanDto> updateLoanDetails(@Valid LoanDto loanDto);
    @Operation(
            summary = "Delete Loan REST API",
            description = "REST API to delete Loan details in TrelaBank by mobile number"
    )
    @ApiResponse(
            responseCode = "204",
            description = "Http Status NO CONTENT"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Http Status NOT FOUND",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            )
    )
    @ApiResponse(
            responseCode = "500",
            description = "Http Status INTERNAL SERVER ERROR",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            )
    )
    public ResponseEntity<Void> deleteLoanDetails(@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits") String mobileNumber);



    @Operation(
            summary = "GET build information",
            description = "Get Build information that is deployed into lonas microservice"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @ApiResponse(
            responseCode = "500",
            description = "HTTP Status INTERNAL SERVER ERROR",
            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
    )
    public ResponseEntity<String> getBuildInfo();

    @Operation(
            summary = "Get java version",
            description = "Get java version details that is installed into loans microservice"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @ApiResponse(
            responseCode = "500",
            description = "HTTP Status INTERNAL SERVER ERROR",
            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
    )
    public ResponseEntity<String> getJavaVersion();

    @Operation(
            summary = "Get loan contact information",
            description = "Get contact information that is deployed into loans microservice"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @ApiResponse(
            responseCode = "500",
            description = "HTTP Status INTERNAL SERVER ERROR",
            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
    )
    public ResponseEntity<LoansContactInfoDto> getLoansContactInfo();
}
