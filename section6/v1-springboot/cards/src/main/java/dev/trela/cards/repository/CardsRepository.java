package dev.trela.cards.repository;

import dev.trela.cards.entity.Cards;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface CardsRepository extends JpaRepository<Cards, Long> {
    Optional<Cards> findByMobileNumber(String phoneNumber);
   Optional<Cards> findByCardNumber(String cardNumber);
    void deleteByMobileNumber(String mobileNumber);
}
