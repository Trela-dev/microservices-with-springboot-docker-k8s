package dev.trela.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Schema(
        name = "Cards",
        description = "Schema to hold Account Information"
)
public record CardsDto(

        @NotBlank(message = "Mobile number is required")
        @Pattern(regexp = "[0-9]{10}", message = "Mobile number should be 10 digits")
        @Schema(
                description = "Mobile number of TrelaBank account holder",
                example = "1234567890"
        )
        String mobileNumber,
        @NotBlank(message = "Card number is required")
        @Pattern(regexp = "^[0-9]{12}$", message = "CardNumber should be exactly 12 digits")
        @Schema(
                description = "Card number of TrelaBank account holder",
                example = "100694188961"
        )
        String cardNumber,
        @NotBlank(message = "Card type is required")
        @Schema(
                description = "Card type of TrelaBank account holder",
                example = "Credit Card"
        )
        String cardType,
        @NotNull(message = "Card limit is required")
        @Schema(
                description = "Card's limit of TrelaBank account holder",
                example = "300000"
        ) //
        int totalLimit,
        @NotNull(message = "Amount used is required")
        @Schema(
                description = "Amount used of TrelaBank acount holder",
                example = "10000"
        )
        int amountUsed,
        @NotNull(message = "Available amount is required")
        @Schema(
                description = "Available amount of TrelaBank account holder",
                example = "290000"
        )
        int availableAmount
) {
}
