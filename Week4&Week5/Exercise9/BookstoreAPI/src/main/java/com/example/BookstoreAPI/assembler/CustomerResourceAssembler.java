package com.example.BookstoreAPI.assembler;

import org.springframework.stereotype.Component;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import com.example.BookstoreAPI.dto.CustomerDTO;
import com.example.BookstoreAPI.Controllers.CustomerController;

@Component
public class CustomerResourceAssembler {

    public EntityModel<CustomerDTO> toModel(CustomerDTO customerDTO) {
        EntityModel<CustomerDTO> customerResource = EntityModel.of(customerDTO);

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class)
                .getCustomerById(customerDTO.getId())).withSelfRel();

        customerResource.add(selfLink);

        Link allCustomersLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class)
                .getAllCustomers()).withRel("all-customers");

        customerResource.add(allCustomersLink);

        return customerResource;
    }
}