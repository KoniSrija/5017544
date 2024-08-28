package com.example.BookstoreAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BookstoreAPI.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {}