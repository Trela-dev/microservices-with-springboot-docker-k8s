package dev.trela.cards.controller;

import dev.trela.cards.dto.CardsContactInfoDto;
import dev.trela.cards.dto.CardsDto;
import dev.trela.cards.dto.ErrorResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.hibernate.sql.Delete;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for Cards in TrelaBank",
        description = "CRUD REST APIs in TrelaBank to CREATE,UPDATE,FETCH AND DELETE cards details"
)
public interface CardsApiDocumentation {

    @Operation(
            summary = "Create Card REST API",
            description = "REST API to create new Card in TrelaBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status BAD REQUEST",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })

    ResponseEntity<CardsDto> createCard(@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
            String mobileNumber);

    @Operation(
            summary = "Fetch Card REST API",
            description = "REST API to fetch Card details in TrelaBank by mobileNumber"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP Status NOT FOUND",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status BAD REQUEST",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })

    ResponseEntity<CardsDto> fetchCardDetails(
            String correlationId,
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
            String mobileNumber);

    @Operation(
            summary = "Update Card Rest API",
            description = "REST API to update Card details in TrelaBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status NOT FOUND",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status BAD REQUEST",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    ResponseEntity<CardsDto> updateCardDetails( @Valid CardsDto cardDto);

    @Operation(
            summary = "Delete Card REST API",
            description = "REST API to delete Card details in TrelaBank by mobileNumber"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "HTTP Status NO CONTENT"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP Status NOT FOUND",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status BAD REQUEST",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    ResponseEntity<Void> deleteCardDetails(
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
            String mobileNumber);


    @Operation(
            summary = "GET build information",
            description = "Get Build information that is deployed into accounts microservice"
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
            description = "Get java version details that is installed into cards microservice"
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
            summary = "Get contact information",
            description = "Get contact information that is deployed into cards microservice"
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
    public ResponseEntity<CardsContactInfoDto> getContactInfo();

}