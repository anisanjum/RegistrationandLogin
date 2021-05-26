package com.learn.fundtransfer1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.fundtransfer1.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
