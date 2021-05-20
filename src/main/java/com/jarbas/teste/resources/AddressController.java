package com.jarbas.teste.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jarbas.teste.entities.Address;
import com.jarbas.teste.service.AddressService;

@RestController
@RequestMapping(value = "/address")
public class AddressController {
	
	@Autowired
	private AddressService service;
	
	@PostMapping
	public ResponseEntity<Address> save(@RequestBody Address address) {
		
		Address r = service.save(address);
		
		return ResponseEntity.ok(r);
	}
	
	@GetMapping
	public ResponseEntity<List<Address>> findAll() {
		
		List<Address> r = service.findAll();
		
		return ResponseEntity.ok(r);
	}
	
	@GetMapping
	@RequestMapping(value = "/get/{id}")
	public ResponseEntity<Address> findNyId(@PathVariable Long  id) {
		
		Address r = service.findById(id);
		
		return ResponseEntity.ok(r);
	}
	
	@PutMapping
	@RequestMapping(value = "/update/{id}")
	public ResponseEntity<Address> findNyId(@PathVariable Long  id, @RequestBody Address address) {
		
		Address r = service.update(id, address);
		
		return ResponseEntity.ok(r);
	}
	
	@DeleteMapping
	@RequestMapping(value = "/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long  id) {
		
		var removed = service.deleteById(id);
		
		if (removed.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
		
		return ResponseEntity.noContent().build();
	}


}
