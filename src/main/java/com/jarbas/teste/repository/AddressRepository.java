package com.jarbas.teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jarbas.teste.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

	
}
