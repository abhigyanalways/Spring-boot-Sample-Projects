package entity_jpa_demo.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import entity_jpa_demo.example.demo.entity.Address;

public interface AddressDao extends JpaRepository<Address, Long>{

	@SuppressWarnings("unchecked")
	public Address save(Address address);
	
		
	
}
