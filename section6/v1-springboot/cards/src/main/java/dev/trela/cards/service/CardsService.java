package dev.trela.cards.service;

import dev.trela.cards.dto.CardsDto;
import dev.trela.cards.entity.Cards;


public interface CardsService {
    CardsDto createCard(String mobileNumber);
    CardsDto fetchCardDetails(String mobileNumber);
    CardsDto updateCard(CardsDto cardDto);
    Cards findByMobileNumber(String mobileNumber);
    Cards findByCardNumber(String cardNumber);
    void deleteCard(String mobileNumber);
}
