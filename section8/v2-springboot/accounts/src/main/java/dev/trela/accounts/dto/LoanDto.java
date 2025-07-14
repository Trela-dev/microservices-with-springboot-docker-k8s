package dev.trela.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Schema(
        name  = "Loans",
        description = "Schema to hold loan details"
)
public record LoanDto(
        @NotBlank(message = "Mobile number is required")
        @Pattern(regexp = "[0-9]{10}", message = "Mobile number should be 10 digits")
        @Schema(
                description = "Mobile number of TrelaBank account holder",
                example = "1234567890"
        )
        String mobileNumber,

        @NotBlank(message = "Loan number is required")
        @Pattern(regexp = "^[0-9]{12}$", message = "Loan number should be 12 digits")
        @Schema(
                description = "Loan number of TrelaBank account holder",
                example = "100525685727"
        )
        String loanNumber,

        @NotBlank(message = "Loan type is required")
        @Schema(
                description = "Loan type of TrelaBank account holder",
                example = "Home Loan"
        )
        String loanType,

        @Positive(message = "Total loan amount should be greater than zero")
        @Schema(
                description = "Total loan of TrelaBank account holder",
                example = "100000"
        )
        int totalLoan,

        @Schema(
                description = "Amount paid by TrelaBank account holder",
                example = "50000")
        @PositiveOrZero(message = "Amount paid has to be greater than or equal to zero")
        int amountPaid,

        @Schema(
                description = "Outstanding amount of TrelaBank account holder",
                example = "50000"
        )
        @PositiveOrZero(message = "Outstanding amount has to be greater than or equal to zero")
        int outstandingAmount) {
}
