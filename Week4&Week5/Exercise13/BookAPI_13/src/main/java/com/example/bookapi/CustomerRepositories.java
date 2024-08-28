package com.example.bookapi;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepositories extends JpaRepository<Customer, Long> {
}
