package com.contactdetails.ContactDetails.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contactdetails.ContactDetails.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
