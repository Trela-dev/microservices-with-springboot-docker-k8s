package dev.trela.cards.controller;

import dev.trela.cards.contstants.CardsConstants;
import dev.trela.cards.dto.CardsDto;
import dev.trela.cards.dto.ResponseDto;
import dev.trela.cards.service.CardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@Tag(
        name = "CRUD REST APIs for Cards in TrelaBank",
        description = "CRUD REST APIs in TrelaBank to CREATE,UPDATE,FETCH AND DELETE cards details"
)
@RestController
@RequiredArgsConstructor
@RequestMapping(path= "/api", produces= {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CardsController {

    private final CardsService cardService;

    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED"
    )
    @Operation(
            summary = "Create Card REST API",
            description = "REST API to create new Card in TrelaBank"
    )
    @PostMapping("/create")
    public ResponseEntity<CardsDto> createCard(@RequestParam @Pattern(regexp = ("(^$|[0-9]{10})"), message = "Mobile number should be 10 digits") String mobileNumber){
        return ResponseEntity.status(HttpStatus.CREATED).body(
            cardService.createCard(mobileNumber)
        );
    }



    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @Operation(
            summary ="Fetch Card REst API",
            description = "REST API to fetch Card details in TrelaBank by mobileNumber"
    )
    @GetMapping("/fetch")
    public ResponseEntity<CardsDto> fetchCardDetails(@RequestParam @Pattern(regexp = ("(^$|[0-9]{10})"), message = "Mobile number should be 10 digits") String mobileNumber){
        CardsDto cardsDto = cardService.fetchCardDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
    }



    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @Operation(
            summary = "Update Card Rest API",
            description = "REST API to update Card details in TrelaBank"
    )
    @PutMapping("/update")
    public ResponseEntity<CardsDto> updateCardDetails(@RequestBody  CardsDto cardDto){
        CardsDto updatedCard = cardService.updateCard(cardDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCard);
    }



    @ApiResponse(
            responseCode = "204",
            description = "HTTP STATUS NO CONTENT"
    )
    @Operation(
            summary = "Delete Card REST API",
            description = "REST API to delete Card details in TrelaBank by mobileNumber"
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCardDetails(@RequestParam @Pattern(regexp = ("(^$|[0-9]{10})"), message = "Mobile number should be 10 digits") String mobileNumber){
        cardService.deleteCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
    }


}
