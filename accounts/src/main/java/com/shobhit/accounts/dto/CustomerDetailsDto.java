package com.shobhit.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "CustomerDetails",
        description = "Schema to hold Customer, Account, Cards and Loans details"
)
public class CustomerDetailsDto {

    @Schema(
            description = "Name of the Customer",
            example = "Eazy Bytes"
    )
    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min=5, max=30, message = "Name should be between 5 to 30 characters")
    private String name;

    @Schema(
            description = "Email Address of the Customer",
            example = "tutor@eazybytes.com"
    )
    @NotEmpty(message = "Email address cannot be null or empty")
    @Email(message = "Email address should be a valid value")
    private String email;

    @Schema(
            description = "Mobile Number of the Customer",
            example = "9602563801"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be of 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Account Details of the Customer"
    )
    private AccountsDto accountsDto;

    @Schema(
            description = "Loans Details of the Customer"
    )
    private LoansDto loansDto;

    @Schema(
            description = "Cards Details of the Customer"
    )
    private CardsDto cardsDto;

}
