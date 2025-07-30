package dev.trela.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(
        name = "Customer",
        description = "Schema to hold Customer,Account and Loans information"
)
public record CustomerDetailsDto(
        @Schema(
                description = "Name of the customer", example = "John Doe"
        )
        @NotBlank(message= "Name can not be null or empty")
        @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
        String name,

        @Schema(
                description = "Email address of the customer", example = "johndoe32145@gmail.com"
        )
        @NotBlank(message= "E-mail can not be null or empty")
        @Email(message = "E-mail should be valid")
        String email,

        @Schema(
                description = "Mobile number of the customer", example = "9345432123"
        )
        @NotBlank(message = "Mobile number is required")
        @Pattern(regexp = ("(^$|[0-9]{10})"), message = "Mobile number should be 10 digits")
        String mobileNumber,

        @Schema(
                description = "Account details of the customer"
        )
        AccountsDto accountsDto,

        @Schema(
                   description = "Loan details of the customer"
           )
                LoanDto loansDto,
        @Schema(
                description = "Card details of the customer"
        )
        CardsDto cardsDto

){

}
