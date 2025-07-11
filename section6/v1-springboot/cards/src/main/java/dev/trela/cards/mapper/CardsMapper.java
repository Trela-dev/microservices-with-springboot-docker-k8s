package dev.trela.cards.mapper;

import dev.trela.cards.dto.CardsDto;
import dev.trela.cards.entity.Cards;

public class CardsMapper {
    public static CardsDto mapToCardsDto(Cards card){
        return new CardsDto(
                card.getMobileNumber(),
                card.getCardNumber(),
                card.getCardType(),
                card.getTotalLimit(),
                card.getAmountUsed(),
                card.getAvailableAmount()
        );
    }

    public static Cards mapToCards(CardsDto cardsDto){
        Cards card = new Cards();
        card.setMobileNumber(cardsDto.mobileNumber());
        card.setCardNumber(cardsDto.cardNumber());
        card.setCardType(cardsDto.cardType());
        card.setTotalLimit(cardsDto.totalLimit());
        card.setAmountUsed(cardsDto.amountUsed());
        card.setAvailableAmount(cardsDto.availableAmount());
        return card;
    }


}
