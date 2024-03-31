package com.amigoscode.springbootexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class SpringBootExampleApplication {

	private final CustomerRepository customerRepository;

	public SpringBootExampleApplication(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootExampleApplication.class, args);
	}

	@GetMapping
	public List<Customer> getCustomers (){

		return customerRepository.findAll();
	}

	//making a DTO for mapping request body and later feed entity
	record NewCustomerRequest (
			String name ,
			String email,
			Integer age
	){}


	//Now converting the Json request body to the data transfer object NewCustomerRequest
	@PostMapping("/add")
	public void addCustomer(@RequestBody NewCustomerRequest newCustomerRequest){

		Customer customer=new Customer();
		customer.setName(newCustomerRequest.name());
		customer.setEmail(newCustomerRequest.email());
		customer.setAge(newCustomerRequest.age());

		customerRepository.save(customer);

	}

	@DeleteMapping("/delete/{customerID}")
	public void deleteCustomer (@PathVariable ("customerID") Integer id){
		customerRepository.deleteById(id);
	}

	@PutMapping("/update/{id}") //request body needed
	public Customer updateCustomer(@PathVariable ("id") Integer id , @RequestBody NewCustomerRequest newCustomerRequest){
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
		customer.setName(newCustomerRequest.name());
		customer.setEmail(newCustomerRequest.email());
		customer.setAge(newCustomerRequest.age());

		customerRepository.save(customer);
		return customer;

	}
}
