package com.example.BookstoreAPI.Controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BookstoreAPI.dto.CustomerDTO;
import com.example.BookstoreAPI.mapper.CustomerMapper;
import com.example.BookstoreAPI.model.Customer;
import com.example.BookstoreAPI.repository.CustomerRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
@Validated
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = customerRepository.findAll().stream()
                .map(CustomerMapper.INSTANCE::customerToCustomerDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            CustomerDTO customerDTO = CustomerMapper.INSTANCE.customerToCustomerDTO(customer.get());
            return ResponseEntity.ok().body(customerDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.INSTANCE.customerDTOToCustomer(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO savedCustomerDTO = CustomerMapper.INSTANCE.customerToCustomerDTO(savedCustomer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomerDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDTO customerDTO) {
        if (!customerRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Customer customer = CustomerMapper.INSTANCE.customerDTOToCustomer(customerDTO);
        customer.setId(id); // Ensure the ID is set correctly
        Customer updatedCustomer = customerRepository.save(customer);
        CustomerDTO updatedCustomerDTO = CustomerMapper.INSTANCE.customerToCustomerDTO(updatedCustomer);
        return ResponseEntity.ok().body(updatedCustomerDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}