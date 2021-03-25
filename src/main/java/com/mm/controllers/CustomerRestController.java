package com.mm.controllers;


import com.mm.exception.ResourceNotFoundException;
import com.mm.entity.Customer;
import com.mm.repositories.CustomerRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.mm.service.CustomerService;
import com.mm.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
@RestController
@RequestMapping("/customers")
public class CustomerRestController {

    private final CustomerRepository customerRepository;
    private final CustomerService customerService;

    public CustomerRestController(CustomerRepository customerRepository, CustomerService customerService) {
        this.customerRepository = customerRepository;
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> findAll() {
        return customerService.findAll();
    }


    @GetMapping("/{customerId}")
    public Customer findById(@PathVariable(name = "customerId") Long customerId) throws ResourceNotFoundException {
        return customerService.findById(customerId);
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }


    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteCustomer(@PathVariable(value = "id") Long customerId) throws ResourceNotFoundException {
        customerService.deleteUser(customerId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseEntity<Customer>> updateCustomer(@PathVariable(value = "id") Long customerId,
                                                                   @RequestBody Customer customerDetails) throws ResourceNotFoundException {

        final ResponseEntity<Customer> updatedCustomer = customerService.updatedCustomer(customerId, customerDetails);
        return ResponseEntity.ok(updatedCustomer);
    }


}






