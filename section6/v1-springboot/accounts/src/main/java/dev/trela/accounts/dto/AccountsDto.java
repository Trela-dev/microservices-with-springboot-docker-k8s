package dev.trela.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


@Schema(
        name = "Accounts",
        description= "Schema to hold Account information"
)
public record AccountsDto(

        @NotBlank(message = "Account number can not be a null or empty")
        @Pattern(regexp = "^[0-9]{10}$", message = "AccountNumber must be 10 digits")
        @Schema(
                description = "Account Number of Trela Bank account",
                example = "3454433243"
        )
        Long accountNumber,
        @NotBlank(message = "AccountType can not be a null or empty")
        @Schema(
                description = "Account type of Trela Bank account", example = "Savings"
        )
        String accountType,
        @NotBlank(message = "BranchAddress can not be a null or empty")
        @Schema(
                description = "Trela Bank account address",
                example = "123 NewYork"
        )
        String branchAddress)
{

}
