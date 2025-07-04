package dev.trela.cards.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CardsDto(

        @NotBlank(message = "Mobile number is required")
        @Pattern(regexp = ("(^$|[0-9]{10})"), message = "Mobile number should be 10 digits")
        String mobileNumber,

        @NotBlank(message = "Mobile number is required")
        @Pattern(regexp = "(^$|[0-9]{10})", message = "Card number should be 10 digits")
        String cardNumber,
        @NotBlank(message = "Card type is required")
        String cardType,
        @NotBlank(message = "Card limit is required")
        int totalLimit,
        @NotBlank(message = "Amount used is required")
        int amountUsed,
        @NotBlank(message = "Available amount is required")
        int availableAmount
) {
}
