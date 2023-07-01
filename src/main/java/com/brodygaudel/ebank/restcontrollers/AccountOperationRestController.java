package com.brodygaudel.ebank.restcontrollers;

import com.brodygaudel.ebank.dtos.*;
import com.brodygaudel.ebank.exceptions.AccountOperationNotFoundException;
import com.brodygaudel.ebank.exceptions.BalanceNotSufficientException;
import com.brodygaudel.ebank.exceptions.BankAccountNotActivatedException;
import com.brodygaudel.ebank.exceptions.BankAccountNotFoundException;
import com.brodygaudel.ebank.services.AccountOperationService;
import com.brodygaudel.ebank.util.pdf.InvoiceGenerator;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/operations")
@CrossOrigin(origins = "*")
public class AccountOperationRestController {

    private final AccountOperationService accountOperationService;
    private final InvoiceGenerator invoiceGenerator;

    public AccountOperationRestController(AccountOperationService accountOperationService, InvoiceGenerator invoiceGenerator) {
        this.accountOperationService = accountOperationService;
        this.invoiceGenerator = invoiceGenerator;
    }

    @PostMapping("/debit")
    public DebitDTO debit(@RequestBody DebitDTO debitDTO) throws BankAccountNotFoundException, BalanceNotSufficientException, BankAccountNotActivatedException {
        return accountOperationService.debit(debitDTO);
    }

    @PostMapping("/credit")
    public CreditDTO credit(@RequestBody CreditDTO creditDTO) throws BankAccountNotFoundException, BankAccountNotActivatedException {
        return accountOperationService.credit(creditDTO);
    }

    @PostMapping("/transfer")
    public TransferDTO transfer(@RequestBody TransferDTO transferDTO) throws BankAccountNotFoundException, BalanceNotSufficientException, BankAccountNotActivatedException {
        return accountOperationService.transfer(transferDTO);
    }

    @GetMapping("/list/{accountId}")
    public List<AccountOperationDTO> getAllOperationsByAccountId(@PathVariable String accountId){
        return accountOperationService.getAllOperationsByAccountId(accountId);
    }

    @GetMapping("/get/{id}")
    public AccountOperationDTO getOperationById(@PathVariable Long id) throws AccountOperationNotFoundException{
        return accountOperationService.getOperationById(id);
    }

    @GetMapping("/{accountId}/pageOperations")
    public AccountHistoryDTO getAccountHistory(@PathVariable String accountId,
                                               @RequestParam(name ="page", defaultValue = "0") int page,
                                               @RequestParam(name ="size", defaultValue = "5") int size) throws BankAccountNotFoundException{
        return accountOperationService.getAccountHistory(accountId, page, size);
    }

    @GetMapping("/generate/{id}")
    public String generateInvoice(@PathVariable Long id) throws AccountOperationNotFoundException {
        return invoiceGenerator.generateInvoice(id);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(@NotNull Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
