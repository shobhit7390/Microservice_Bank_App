package com.shobhit.accounts.controller;

import com.shobhit.accounts.dto.CustomerDetailsDto;
import com.shobhit.accounts.dto.ErrorResponseDto;
import com.shobhit.accounts.service.ICustomersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(
        name = "REST APIs for Customer in Fincrest",
        description = "REST APIs in Fincrest to fetch customer details"
)
@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final ICustomersService iCustomersService;

    public CustomerController(ICustomersService iCustomersService){
        this.iCustomersService = iCustomersService;
    }

    @Operation(
            summary = "Fetch Customer Details REST API",
            description = "REST API to fetch Customer details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping(path = "/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails( @RequestHeader("fincrest-correlation-id")
                                                                        String correlationId,
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be of 10 digits")
            String mobileNumber) {
        logger.debug("fincrest-correlation-id found: {}", correlationId);
        CustomerDetailsDto customerDetailsDto = iCustomersService.fetchCustomerDetails(mobileNumber, correlationId);
        return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
    }

}
