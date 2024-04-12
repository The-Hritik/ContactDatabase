package com.contactdetails.ContactDetails.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.contactdetails.ContactDetails.models.Customer;
import com.contactdetails.ContactDetails.models.CustomerDto;
import com.contactdetails.ContactDetails.services.CustomerRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerRepository repo;
	
	@GetMapping({"","/"})
	public String showCustomerList(Model model) {
		List<Customer> customer = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
		model.addAttribute("customer", customer);
		return "customer/index";
	}
	
	@GetMapping("/create")
	public String showCreatePage(Model model) {
		CustomerDto customerDto = new CustomerDto();
		model.addAttribute("customerDto", customerDto);
		return "customer/CreateCustomer";
	}
	
	@PostMapping("/create")
	public String createCustomer(
			@Valid @ModelAttribute CustomerDto customerDto,
			BindingResult result
			) {
		if (result.hasErrors()) {
			return "customer/CreateCustomer";
		}
		
		Customer customer = new Customer();
		customer.setName(customerDto.getName());
		customer.setPhoneno(customerDto.getPhoneno());
		customer.setEmail(customerDto.getEmail());
		customer.setDob(customerDto.getDob());
		customer.setCity(customerDto.getCity());
		
		repo.save(customer);
		
		
		return "redirect:/customer";
	}
	
	@GetMapping("/edit")
	public String showEditPage(Model model , @RequestParam int id) {
		
		try {
			Customer customer = repo.findById(id).get();
			model.addAttribute("customer", customer);
			
			CustomerDto customerDto = new CustomerDto();
			customerDto.setName(customer.getName());
			customerDto.setPhoneno(customer.getPhoneno());
			customerDto.setEmail(customer.getEmail());
			customerDto.setDob(customer.getDob());
			customerDto.setCity(customer.getCity());
			
			model.addAttribute("customerDto", customerDto);
		}
		catch(Exception ex) {
			System.out.println("Exception: "+ ex.getMessage());
			return "redirect:/customer";
		}
		return "customer/EditCustomer";
		
	}
	
	
	@PostMapping("/edit")
	public String updateCustomer(
			Model model,
			@RequestParam int id,
			@Valid @ModelAttribute CustomerDto customerDto,
			BindingResult result) {
		try {
			Customer customer = repo.findById(id).get();
			model.addAttribute("customer", customer);
			
			if (result.hasErrors()) {
				return "customer/EditCustomer";
			}
			
			customer.setName(customerDto.getName());
			customer.setPhoneno(customerDto.getPhoneno());
			customer.setEmail(customerDto.getEmail());
			customer.setDob(customerDto.getDob());
			customer.setCity(customerDto.getCity());
			
			repo.save(customer);
		}
		catch(Exception ex) {
			System.out.println("Exception: "+ ex.getMessage());
		}
		
		return "redirect:/customer";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(
			@RequestParam int id
			) {
		try {
			Customer customer = repo.findById(id).get();
			repo.delete(customer);
			
		}
		catch(Exception ex) {
			System.out.println("Exception: "+ ex.getMessage());
		}
		return "redirect:/customer";
	}
	
}
