package com.cg.scb.controller;

import java.util.List;
import java.util.Optional;

import com.cg.scb.dto.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.cg.scb.entity.Account;
import com.cg.scb.entity.Customer;
import com.cg.scb.entity.Transaction;
import com.cg.scb.repository.AccountRepository;
import com.cg.scb.repository.AdminRepository;
import com.cg.scb.repository.CustomerRepository;
import com.cg.scb.service.AccountService;
import com.cg.scb.service.CustomerService;
import com.cg.scb.service.TransactionService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/login")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	@Autowired
	AccountService accountService;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	TransactionService transactionService;
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	CustomerRepository customerRepository;
	@GetMapping(value = "/findaccount/{accountNumber}")
	public Account findAccount(@PathVariable Integer accountNumber) {
		Optional<Account> account =  accountService.findAccountByNumber(accountNumber);
		if(account.isPresent())
			return account.get();
		return null;
	}
	@GetMapping(value = "/alltransactionsdisplay/{accountNumber}")
	public List<Transaction> AllTransactionsDisplay(@PathVariable int accountNumber) {

				List<Transaction> transactions = transactionService.getAllTransactions(accountNumber);
				return transactions;
	}

		@PostMapping(value = "/registration/{name}/{email}/{password}")
	public Customer registerUser(
			@PathVariable String name,
			@PathVariable String email,
			@PathVariable String password
			) {
		Customer customer = customerService.createCustomer(name, email, password);
		if(customer!=null)
		{
			return customer;
		}
		return null;
	}
	@GetMapping(value = "/operations")
	public ModelAndView Operations(HttpSession session,
            ModelMap model)
	{
		Customer username = (Customer) session.getAttribute("username");
		model.addAttribute("username", username.getName());
		return new ModelAndView("Operations");
	}


	@GetMapping("/depositmoney/{accountNumber}/{amount}")
    public String performTransaction(
            @PathVariable Integer accountNumber,
            @PathVariable double amount)
	{
		return accountService.deposit(accountNumber,amount);
    }
	@GetMapping("/withdrawmoney/{accountNumber}/{amount}")
    public String withdraw(
            @PathVariable Integer accountNumber,
            @PathVariable double amount) {

			return accountService.withdraw(accountNumber, amount);
    }

	@GetMapping("/transfermoney/{fromaccountNumber}/{toaccountNumber}/{amount}")
    public String transfer(
            @PathVariable Integer fromaccountNumber,@PathVariable Integer toaccountNumber,
            @PathVariable double amount) {
			return accountService.transfer(fromaccountNumber, toaccountNumber, amount);
	}
	@GetMapping(value = "/allcustomers")
	public List<Customer> allCustomers(HttpSession session, ModelMap model)
	{
		List<Customer> customers = customerRepository.findAll();
		return  customers;
	}
	@GetMapping(value = "getadmin/{name}")
	 public Admin getAdmin(@PathVariable String name)
	 {
		 return adminRepository.findByUsername(name);
	 }
	 @GetMapping(value = "/accountcreation/{name}/{email}/{password}/{amount}")
	public Account createAccount(@PathVariable String name,@PathVariable String email,@PathVariable String password,@PathVariable double amount)
	{
		Customer customer = customerService.createCustomer(name,email,password);
		Account account = accountService.createAccount(customer, amount, "Savings");
		return account;
	}
	@GetMapping(value = "/deleteaccount/{accountNumber}")
	public String deleteAccount(@PathVariable Integer accountNumber)
	{
			return accountService.deleteAccount(accountNumber);
	}
	@GetMapping(value = "/allaccounts")
	public List<Account> allAccounts(HttpSession session, ModelMap model)
	{
		List<Account> accounts = accountRepository.findAll();
		return accounts;
	}
	@GetMapping(value = "/deletecustomer/{customerId}")
	public String deleteCustomer(@PathVariable Integer customerId,HttpSession session, ModelMap model)
	{
		Admin admin  = (Admin) session.getAttribute("username");
		model.addAttribute("username", admin.getName());
		Optional<Customer> cuOptional = customerRepository.findById(customerId);
		if(cuOptional.isPresent())
		{
			customerRepository.deleteById(customerId);
			return "Customer Deleted Successfully";
		}
		return "No Customer Found With ID";
	}
	@GetMapping(value = "/findcustomer/{customerId}")
	public Customer findCustomer(@PathVariable int customerId)
	{
		Optional<Customer> customer = customerService.findCustomerById(customerId);
		if(customer.isPresent())
			return customer.get();
		return null;
	}
	@PostMapping("/update/name/{customerId}/{newName}")
	public String updateName(@PathVariable int customerId, @PathVariable String newName,HttpSession session, ModelMap model ) {
		customerService.updateCustomerName(customerId, newName);
		return "Customer Name updated Successfully";
	}

	@PostMapping("/update/email/{customerId}/{newEmail}")
	public String updateEmail(@RequestParam int customerId, @PathVariable String newEmail, HttpSession session, ModelMap model) {
		customerService.updateCustomerEmail(customerId, newEmail);
		return "Customer email updated Successfully";
	}
	
}
