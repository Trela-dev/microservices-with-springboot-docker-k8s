package dev.trela.accounts.controller;

import dev.trela.accounts.dto.AccountsContactInfoDto;
import dev.trela.accounts.dto.CustomerDto;
import dev.trela.accounts.dto.ErrorResponseDto;
import dev.trela.accounts.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.TimeoutException;

@Tag(
        name = "CRUD REST APIs for Accounts in TrelaBank",
        description = "CRUD REST APIs in TrelaBank to CREATE,UPDATE,FETCH AND DELETE accounts details"
)
public interface AccountsApiDocumentation {

    @ApiResponse(
            responseCode = "201",
            description= "HTTP Status CREATED"
    )
    @ApiResponse(
            responseCode  = "409",
            description= "HTTP Status CONFLICT",
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
            summary= "Create Account REST API",
            description = "REST API to create new Customer & Account in TrelaBank"
    )
    ResponseEntity<ResponseDto> createAccount(@Valid CustomerDto customerDto);






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
            summary= "Fetch Account REST API",
            description = "REST API to fetch Customer & Account details in TrelaBank by mobile number"
    )
    ResponseEntity<CustomerDto> fetchAccountDetails( @Pattern(regexp = ("(^$|[0-9]{10})"), message = "Mobile number should be 10 digits") String mobileNumber);





    @ApiResponse(
            responseCode = "200",
            description= "HTTP Status OK",
            content=@Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            )
    )
    @ApiResponse(
            responseCode  = "404",
            description= "HTTP Status NOT FOUND",
            content=@Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            )

    )@ApiResponse(
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
            summary= "Update Account REST API",
            description = "REST API to update Customer & Account details in TrelaBank"
    )
    ResponseEntity<CustomerDto> updateAccountDetails(@Valid CustomerDto customerDto);




    @ApiResponse(
            responseCode = "204",
            description= "HTTP Status NO CONTENT"
    )
    @ApiResponse(
            responseCode  = "404",
            description= "HTTP Status NOT FOUND",
            content=@Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            )

    )@ApiResponse(
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
            summary= "Delete Account REST API",
            description = "REST API to delete Customer & Account details in TrelaBank by mobile number"
    )
    ResponseEntity<Void> deleteAccount( @Pattern(regexp = ("(^$|[0-9]{10})"), message = "Mobile number should be 10 digits") String mobileNumber);


    @ApiResponse(
            responseCode = "200",
            description= "HTTP Status OK"
    )
    @ApiResponse(
            responseCode = "500",
            description= "HTTP Status INTERNAL SERVER ERROR",
            content=@Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            )
    )
    @Operation(
            summary= "Get build information",
            description = "Get build information that is deployed into accounts microservice"
    )
    ResponseEntity<String> getBuildInfo() throws TimeoutException;


    @ApiResponse(
            responseCode = "200",
            description= "HTTP Status OK"
    )
    @ApiResponse(
            responseCode = "500",
            description= "HTTP Status INTERNAL SERVER ERROR",
            content=@Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            )
    )
    @Operation(
            summary= "Get Java version information",
            description = "Get Java version details that is installed into accounts microservice"
    )
    ResponseEntity<String> getJavaVersion();


    @ApiResponse(
            responseCode = "200",
            description= "HTTP Status OK"
    )
    @ApiResponse(
            responseCode = "500",
            description= "HTTP Status INTERNAL SERVER ERROR",
            content=@Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            )
    )
    @Operation(
            summary= "Get contact Info",
            description = "Contact Info details that can be reached out in case of any issues"
    )
    ResponseEntity<AccountsContactInfoDto> getContactInfo();




}
