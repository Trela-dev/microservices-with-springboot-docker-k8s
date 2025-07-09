package dev.trela.cards.controller;

import dev.trela.cards.dto.CardsDto;
import dev.trela.cards.service.CardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CardsController implements CardsApiDocumentation {

    private final CardsService cardService;

    @Override
    @PostMapping("/create")
    public ResponseEntity<CardsDto> createCard( @RequestParam String mobileNumber) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                cardService.createCard(mobileNumber)
        );
    }

    @Override
    @GetMapping("/fetch")
    public ResponseEntity<CardsDto> fetchCardDetails(@RequestParam  String mobileNumber) {
        CardsDto cardsDto = cardService.fetchCardDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<CardsDto> updateCardDetails(@RequestBody CardsDto cardDto) {
        CardsDto updatedCard = cardService.updateCard(cardDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCard);
    }

    @Override
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCardDetails(@RequestParam String mobileNumber) {
        cardService.deleteCard(mobileNumber);
        return ResponseEntity.noContent().build();
    }
}