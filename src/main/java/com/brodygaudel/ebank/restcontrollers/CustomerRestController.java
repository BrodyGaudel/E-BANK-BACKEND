package com.brodygaudel.ebank.restcontrollers;

import com.brodygaudel.ebank.dtos.CustomerDTO;
import com.brodygaudel.ebank.exceptions.CinAlreadyExistsException;
import com.brodygaudel.ebank.exceptions.CustomerNotFoundException;
import com.brodygaudel.ebank.services.CustomerService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customers")
@CrossOrigin(origins = "*")
public class CustomerRestController {

    private final CustomerService customerService;

    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/list")
    public List<CustomerDTO> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/search")
    public List<CustomerDTO> searchCustomers(@RequestParam(name = "keyword", defaultValue = "") String keyword){
        return customerService.searchCustomers(keyword);
    }

    @GetMapping("/get/{id}")
    public CustomerDTO getCustomerById(@PathVariable Long id) throws CustomerNotFoundException{
        return customerService.getCustomerById(id);
    }

    @PostMapping("/save")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) throws CinAlreadyExistsException{
        return customerService.saveCustomer(customerDTO);
    }

    @PutMapping("/update/{id}")
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) throws CustomerNotFoundException, CinAlreadyExistsException{
        return customerService.updateCustomer(id, customerDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCustomerById(@PathVariable Long id){
        customerService.deleteCustomerById(id);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(@NotNull Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
