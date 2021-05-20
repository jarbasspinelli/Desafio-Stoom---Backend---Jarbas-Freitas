package com.jarbas.teste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import com.jarbas.teste.entities.Address;
import com.jarbas.teste.service.AddressService;


public class AddressServiceTest {
	
	@InjectMocks
	private AddressService service;
	
	@BeforeEach
	void setUp() {
		service = Mockito.mock(AddressService.class);
	}
	
	@Test
	public void findByIdTest() {
		
		Address addresss = new Address();
		addresss.setId(1L);
		
		Mockito.when(service.findById(1L)).thenReturn(addresss);
		
		Address result = service.findById(1L);
		
		assertEquals(result.getId(), addresss.getId());

	}
	
	@Test
	public void findAllTest() {
		
		List<Address> list = new ArrayList<>();
		list.add(new Address());
		list.add(new Address());
		list.add(new Address());
		
		Mockito.when(service.findAll()).thenReturn(list);
		
		List<Address> result = service.findAll();
		
		assertFalse(result.isEmpty());
	}
	
}
