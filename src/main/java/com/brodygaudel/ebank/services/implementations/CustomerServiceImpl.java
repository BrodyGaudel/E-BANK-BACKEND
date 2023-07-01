package com.brodygaudel.ebank.services.implementations;

import com.brodygaudel.ebank.dtos.CustomerDTO;
import com.brodygaudel.ebank.entities.Customer;
import com.brodygaudel.ebank.exceptions.CinAlreadyExistsException;
import com.brodygaudel.ebank.exceptions.CustomerNotFoundException;
import com.brodygaudel.ebank.mapping.Mappers;
import com.brodygaudel.ebank.repositories.CustomerRepository;
import com.brodygaudel.ebank.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final Mappers mappers;

    public CustomerServiceImpl(CustomerRepository customerRepository, Mappers mappers) {
        this.customerRepository = customerRepository;
        this.mappers = mappers;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return mappers.fromListOfCustomers(customers);
    }

    @Override
    public List<CustomerDTO> searchCustomers(String keyword) {
        List<Customer> customers = customerRepository.search("%"+keyword+"%");
        return mappers.fromListOfCustomers(customers);
    }

    @Override
    public CustomerDTO getCustomerById(Long id) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(id)
                .orElseThrow( () -> new CustomerNotFoundException("customer not found"));
        log.info("customer found");
        return mappers.fromCustomer(customer);
    }

    @Override
    public CustomerDTO saveCustomer(@NotNull CustomerDTO customerDTO) throws CinAlreadyExistsException {
        Boolean cinExists = customerRepository.checkIfCinExists(customerDTO.cin());
        if(Boolean.TRUE.equals(cinExists)){
            throw new CinAlreadyExistsException("customer with this cin already exists");
        }
        Customer customer = mappers.fromCustomerDTO(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        log.info("customer saved");
        return mappers.fromCustomer(savedCustomer);
    }

    @Override
    public CustomerDTO updateCustomer(Long id, @NotNull CustomerDTO customerDTO) throws CustomerNotFoundException, CinAlreadyExistsException {
        Customer customer = customerRepository.findById(id)
                .orElseThrow( () -> new CustomerNotFoundException("customer not found"));
        if(!customer.getCin().equals(customerDTO.cin())){
            Boolean cinExists = customerRepository.checkIfCinExists(customerDTO.cin());
            if(Boolean.TRUE.equals(cinExists)){
                throw new CinAlreadyExistsException("customer with this cin already exists");
            }
        }
        customer.setCin(customerDTO.cin());
        customer.setName(customerDTO.name());
        customer.setFirstname(customerDTO.firstname());
        customer.setDateOfBirth(customerDTO.dateOfBirth());
        customer.setPlaceOfBirth(customerDTO.placeOfBirth());
        customer.setNationality(customerDTO.nationality());
        customer.setSex(customerDTO.sex());
        Customer updatedCustomer = customerRepository.save(customer);
        log.info("customer updated");
        return mappers.fromCustomer(updatedCustomer);
    }


    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
        log.info("customer deleted");
    }
}
