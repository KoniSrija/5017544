package com.example.BookstoreAPI.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.example.BookstoreAPI.dto.BookDTO;
import com.example.BookstoreAPI.Controllers.BookController;

@Component
public class BookResourceAssembler {

    public EntityModel<BookDTO> toModel(BookDTO bookDTO) {
        EntityModel<BookDTO> bookResource = EntityModel.of(bookDTO);

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class)
                .getBookById(bookDTO.getId())).withSelfRel();

        bookResource.add(selfLink);

        Link allBooksLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class)
                .getAllBooks()).withRel("all-books");

        bookResource.add(allBooksLink);

        return bookResource;
    }
}

