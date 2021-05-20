package com.jarbas.teste.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.jarbas.teste.entities.Address;
import com.jarbas.teste.entities.Root;
import com.jarbas.teste.exception.NotFoundException;
import com.jarbas.teste.exception.StandardException;
import com.jarbas.teste.repository.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository repository;
	
	@Autowired
	private ApiGoogleService api;
	
	@Autowired
	private Gson gson;
	
	public List<Address> findAll() {
		
		List<Address> result = repository.findAll();
		
		if(result.isEmpty()) {
			throw new NotFoundException("Nenhum registro encontrado!");
		}
		
		return result;
	}

	public Address findById(Long id) {
		
		Address result;
		
		try {
			result = repository.findById(id).get();
		}catch(NoSuchElementException e) {
			throw new StandardException("Parametros inválidos, id inválido!");
		}
		
		if(result == null) {
			throw new NotFoundException("Nenhum registro encontrado!");
		}
		
		return result;
	}
	
	public Address save(Address address) {
		
		if(address.getLatitude() == null && address.getLongitude() == null) {
			String json = api.getLatitudeLongitude(address);
			Root root = gson.fromJson(json, Root.class);
			address.setLatitude(root.results.get(0).getGeometry().getLocation().getLat());
			address.setLongitude(root.results.get(0).getGeometry().getLocation().getLng());
		}
		
		Address result = repository.save(address);
		
		if(result == null) {
			throw new StandardException("Erro ao salvar registro, tente novamente!");
		}
		
		return result;
	}
	
	public String deleteById(Long id) {
		
		Address result;
		
		try {
			result = repository.findById(id).get();
		}catch(NoSuchElementException e) {
			throw new StandardException("Parametros inválidos, id inválido!");
		}
		
		if(result == null) {
			throw new NotFoundException("Nenhum registro encontrado!");
		}
		
		repository.deleteById(id);
		
		return "Resgistro deletado com sucesso";
	}
	
	public Address update(Long id, Address address) {
		
		Address addressUpdate = null;
		
		if(address.getLatitude() == null && address.getLongitude() == null) {
			Root root = gson.fromJson(api.getLatitudeLongitude(address), Root.class);
			address.setLatitude(root.results.get(0).getGeometry().getLocation().getLat());
			address.setLongitude(root.results.get(0).getGeometry().getLocation().getLng());
		}
		
		try {
			addressUpdate = repository.findById(id).get();
		}catch(NoSuchElementException e) {
			throw new StandardException("Parametros inválidos, id inválido!");
		}
		
		if(addressUpdate == null) {
			throw new NotFoundException("Nenhum registro encontrado!");
		}
		
			addressUpdate.setCity(address.getCity());
			addressUpdate.setComplement(address.getComplement());
			addressUpdate.setCountry(address.getCountry());
			addressUpdate.setLatitude(address.getLatitude());
			addressUpdate.setLongitude(address.getLongitude());
			addressUpdate.setNeighbourhood(address.getNeighbourhood());
			addressUpdate.setNumber(address.getNumber());
			addressUpdate.setState(address.getState());
			addressUpdate.setStreetName(address.getStreetName());
			addressUpdate.setZipCode(address.getZipCode());
		
		repository.save(addressUpdate);
		
		return addressUpdate;
	}
}
