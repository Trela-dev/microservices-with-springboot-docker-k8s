package dev.trela.cards.service.impl;

import dev.trela.cards.contstants.CardsConstants;
import dev.trela.cards.dto.CardsDto;
import dev.trela.cards.entity.Cards;
import dev.trela.cards.exception.CardAlreadyExistsException;
import dev.trela.cards.exception.ResourceNotFoundException;
import dev.trela.cards.mapper.CardsMapper;
import dev.trela.cards.repository.CardsRepository;
import dev.trela.cards.service.CardsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
public class CardServiceImpl implements CardsService {

    private final CardsRepository cardsRepository;

    @Override
    public CardsDto createCard(String mobileNumber) {
        if(cardsRepository.findByMobileNumber(mobileNumber).isPresent()){
            throw new CardAlreadyExistsException("Card already registered with given mobileNumber",mobileNumber);
        }
        return CardsMapper.mapToCardsDto(cardsRepository.save(createNewCard(mobileNumber)));
    }

    @Override
    public CardsDto fetchCardDetails(String mobileNumber) {
        // return or validate if exits
        return CardsMapper.mapToCardsDto(findByMobileNumber(mobileNumber));
    }

    @Override
    public CardsDto updateCard(CardsDto cardDto) {
        String cardNumber = cardDto.cardNumber();
        //validate if card exists and save to variable
        Cards card = findByCardNumber(cardNumber);

        card.setMobileNumber(cardDto.mobileNumber());
        card.setCardNumber(cardDto.cardNumber());
        card.setCardType(cardDto.cardType());
        card.setTotalLimit(cardDto.totalLimit());
        card.setAmountUsed(cardDto.amountUsed());
        card.setAvailableAmount(cardDto.availableAmount());

        return CardsMapper.mapToCardsDto(cardsRepository.save(cardsRepository.save(card)));
    }


    private Cards createNewCard(String mobileNumber){
        Cards newCard = new Cards();
        long randomCardNumber = 100_000_000_000L + new Random().nextInt(900_000_000);
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }

    @Override
    public Cards findByMobileNumber(String mobileNumber) {
        return cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Card","mobileNumber",mobileNumber));
    }

    @Override
    public Cards findByCardNumber(String cardNumber) {
        return cardsRepository.findByCardNumber(cardNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Card","cardNumber",cardNumber));
    }

    @Override
    public void deleteCard(String mobileNumber) {
        findByMobileNumber(mobileNumber);
        cardsRepository.deleteByMobileNumber(mobileNumber);
    }


}
