package com.shobhit.accounts.service.impl;

import com.shobhit.accounts.dto.AccountsDto;
import com.shobhit.accounts.dto.CardsDto;
import com.shobhit.accounts.dto.CustomerDetailsDto;
import com.shobhit.accounts.dto.LoansDto;
import com.shobhit.accounts.entity.Accounts;
import com.shobhit.accounts.entity.Customer;
import com.shobhit.accounts.exception.ResourceNotFoundException;
import com.shobhit.accounts.mapper.AccountsMapper;
import com.shobhit.accounts.mapper.CustomerMapper;
import com.shobhit.accounts.repository.AccountsRepository;
import com.shobhit.accounts.repository.CustomerRepository;
import com.shobhit.accounts.service.ICustomersService;
import com.shobhit.accounts.service.client.CardsFeignClient;
import com.shobhit.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements ICustomersService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()-> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity= loansFeignClient.fetchLoanDetails(mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity= cardsFeignClient.fetchCardDetails(mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        return customerDetailsDto;
    }
}
