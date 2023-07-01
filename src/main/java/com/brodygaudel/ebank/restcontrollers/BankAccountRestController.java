package com.brodygaudel.ebank.restcontrollers;

import com.brodygaudel.ebank.dtos.*;
import com.brodygaudel.ebank.exceptions.BankAccountNotFoundException;
import com.brodygaudel.ebank.exceptions.CustomerNotFoundException;
import com.brodygaudel.ebank.services.BankAccountService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/accounts")
@CrossOrigin(origins = "*")
public class BankAccountRestController {

    private final BankAccountService bankAccountService;

    public BankAccountRestController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PostMapping("/save/current")
    public CurrentAccountDTO saveCurrentBankAccount(@RequestBody CurrentAccountCreationForm form) throws CustomerNotFoundException{
        return bankAccountService.saveCurrentBankAccount(form);
    }

    @PostMapping("/save/saving")
    public SavingAccountDTO saveSavingBankAccount(@RequestBody SavingAccountCreationForm form) throws CustomerNotFoundException{
        return bankAccountService.saveSavingBankAccount(form);
    }

    @GetMapping("/get/{id}")
    public BankAccountDTO getBankAccountById(@PathVariable String id) throws BankAccountNotFoundException{
        return bankAccountService.getBankAccountById(id);
    }

    @GetMapping("/find/{customerId}")
    public List<BankAccountDTO> getBankAccountByCustomerId(@PathVariable(name = "customerId") Long id) throws BankAccountNotFoundException{
        return bankAccountService.getBankAccountByCustomerId(id);
    }

    @GetMapping("/list")
    public List<BankAccountDTO> getAllBankAccounts(){
        return bankAccountService.getAllBankAccounts();
    }

    @PatchMapping("/update/{id}")
    public BankAccountDTO updateBankAccountStatus(@PathVariable String id, @RequestBody UpdateBankAccountStatus status) throws BankAccountNotFoundException{
        return bankAccountService.updateBankAccountStatus(id, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(@NotNull Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
