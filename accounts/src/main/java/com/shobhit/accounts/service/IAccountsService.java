package com.shobhit.accounts.service;

import com.shobhit.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     * Create a new account in the system.
     *
     * @param customerDto Customer info to create an account for.
     */
    void createAccount(CustomerDto customerDto);

    /**
     * Fetch account info for a given mobile number.
     *
     * @param mobileNumber Mobile number to fetch account info for.
     * @return Account info for the given mobile number.
     */
    CustomerDto fetchAccount(String mobileNumber);

    /**
     * Update an existing account in the system.
     *
     * @param customerDto Updated customer information.
     * @return true if the account was successfully updated, false otherwise.
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     * Delete an existing account in the system.
     *
     * @param mobileNumber Mobile number of the account to be deleted.
     * @return true if the account was successfully deleted, false otherwise.
    */
    boolean deleteAccount(String mobileNumber);

    /**
     *
     * @param accountNumber - Long.
     * @return boolean indicating if the update of communication status issuccessful or not.
     */
    boolean updateCommunicationStatus(Long accountNumber);
}
