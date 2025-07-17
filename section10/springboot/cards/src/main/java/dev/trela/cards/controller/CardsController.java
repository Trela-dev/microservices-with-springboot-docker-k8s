package dev.trela.cards.controller;

import dev.trela.cards.dto.CardsContactInfoDto;
import dev.trela.cards.dto.CardsDto;
import dev.trela.cards.service.CardsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@Log4j2
public class CardsController implements CardsApiDocumentation {

    private final CardsService cardService;
    private final Environment environment;
    private final CardsContactInfoDto cardsContactInfoDto;


    @Value("${build.version}")
    private String buildVersion;

    @Override
    @PostMapping("/create")
    public ResponseEntity<CardsDto> createCard( @RequestParam String mobileNumber) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                cardService.createCard(mobileNumber)
        );
    }

    @Override
    @GetMapping("/fetch")
    public ResponseEntity<CardsDto> fetchCardDetails(@RequestHeader("trelaBank-correlation-id") String correlationId,@RequestParam  String mobileNumber) {
        log.debug("trelaBank-correlation-id found: {}", correlationId);
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

    @Override
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
    }

    @Override
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));
    }

    @Override
    @GetMapping("/contact-info")
    public ResponseEntity<CardsContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardsContactInfoDto);
    }
}