package com.cg.scb.controller;

import com.cg.scb.dto.Account;
import com.cg.scb.dto.Admin;
import com.cg.scb.dto.Customer;
import com.cg.scb.dto.Transaction;
import com.cg.scb.service.AccountService;
import com.cg.scb.service.CustomerService;
import com.cg.scb.service.TransactionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value ="login")
public class AdminController {
    @Autowired
    CustomerService customerService;
    @Autowired
    AccountService accountService;
    @Autowired
    TransactionService transactionService;

    @GetMapping(value="/adminloginpage")
    public ModelAndView displayAdminLoginPage(@ModelAttribute("loginObj")
                                              Admin user)
    {
        ModelAndView loginPageView=
                new ModelAndView("AdminLogin");
        return loginPageView;
    }
    @GetMapping(value = "adminoperations")
    public ModelAndView adminOperations(HttpSession session, ModelMap model)
    {
        Admin admin  = (Admin) session.getAttribute("username");
        model.addAttribute("username", admin.getName());
        return new ModelAndView("AdminOperations");
    }
    @PostMapping(value="/validateadmin")
    public ModelAndView validateAdmin(@RequestParam String username, @RequestParam String password,
                                      HttpSession session, ModelMap model) {
        Admin admin = customerService.findAdmin(username);
        if (admin != null && admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
            session.setAttribute("username", admin);
            model.addAttribute("username", admin.getName());
            return new ModelAndView("AdminOperations");
        } else {
            model.addAttribute("error", "Invalid username or password");
            return new ModelAndView("adminLogin", model);
        }
    }
    @GetMapping( value="/createaccount")
    public ModelAndView createAccount(HttpSession session, ModelMap model)
    {
        Admin admin  = (Admin) session.getAttribute("username");
        model.addAttribute("username", admin.getName());
        return new ModelAndView("AccountCreation");
    }
    @GetMapping( value="/accountcreation")
    public ModelAndView createAccount(@RequestParam String name,
                                      @RequestParam String email,@RequestParam String password,
                                      @RequestParam double amount,HttpSession session, ModelMap model) {
        Admin admin  = (Admin) session.getAttribute("username");
        model.addAttribute("username", admin.getName());
        Account account = accountService.createAccount(name,email,password,amount);
        return new ModelAndView("AccountDetails","account",account);
    }
    @GetMapping(value = "/delete")
    public ModelAndView delete(HttpSession session, ModelMap model)
    {
        Admin admin  = (Admin) session.getAttribute("username");
        model.addAttribute("username", admin.getName());
        return new ModelAndView("DeleteAccount");
    }
    @GetMapping(value = "/deleteaccount")
    public ModelAndView deleteAccount(@RequestParam Integer accountNumber,HttpSession session, ModelMap model)
    {
        Admin admin  = (Admin) session.getAttribute("username");
        model.addAttribute("username", admin.getName());
        Account account = accountService.findAccountByNumber(accountNumber);
        if(account!=null)
        {
            accountService.deleteAccount(accountNumber);
            return new ModelAndView("DeleteAccount","balanceMessage", "Account Deleted Successfully");
        }
        return new ModelAndView("DeleteAccount","balanceMessage","Account Not Found");
    }
    @GetMapping(value = "/allaccounts")
    public ModelAndView allAccounts(HttpSession session, ModelMap model)
    {
        Admin admin  = (Admin) session.getAttribute("username");
        model.addAttribute("username", admin.getName());
        List<Account> accounts = accountService.getAllAccounts();
        return new ModelAndView("AllAccounts","mcList",accounts);
    }
    @GetMapping(value = "/allcustomers")
    public ModelAndView allCustomers(HttpSession session, ModelMap model)
    {
        Admin admin  = (Admin) session.getAttribute("username");
        model.addAttribute("username", admin.getName());
        List<Customer> customers = customerService.getAllCustomers();
        return new ModelAndView("ListofCustomers","mcList",customers);
    }
    @GetMapping(value = "/deletecust")
    public ModelAndView deletecust(HttpSession session, ModelMap model)
    {
        Admin admin  = (Admin) session.getAttribute("username");
        model.addAttribute("username", admin.getName());
        return new ModelAndView("DeleteCustomer");
    }
    @GetMapping(value = "/deletecustomer")
    public ModelAndView deleteCustomer(@RequestParam Integer customerId,HttpSession session, ModelMap model) {
        Admin admin = (Admin) session.getAttribute("username");
        model.addAttribute("username", admin.getName());
        Customer customer = customerService.findCustomerById(customerId);
        if (customer != null) {
            customerService.deleteCustomer(customerId);
            return new ModelAndView("DeleteCustomer", "balanceMessage", "Customer Deleted Successfully");
        }
        return new ModelAndView("DeleteCustomer","balanceMessage", "No Customer with ID found");
    }
    @GetMapping("/updatedetails")
    public ModelAndView updateDetailsPage(HttpSession session, ModelMap model)
    {
        Admin admin  = (Admin) session.getAttribute("username");
        model.addAttribute("username", admin.getName());
        return new ModelAndView("UpdateDetails");
    }
    @PostMapping("/updateDetails")
    public ModelAndView updateDetails(@RequestParam(name = "customerId", required = false) String customerIdStr, HttpSession session, ModelMap model) {
        Admin admin  = (Admin) session.getAttribute("username");
        model.addAttribute("username", admin.getName());
        if (customerIdStr == null || customerIdStr.isEmpty()) {
            return new ModelAndView("UpdateDetails", "message", "Customer ID is required");
        }

        try {
            int customerId = Integer.parseInt(customerIdStr);
            System.out.println(customerId);
            Customer customer = customerService.findCustomerById(customerId);

            if (customer!=null) {
                model.addAttribute("customerId", customerId);
                return new ModelAndView("UpdateOptions");
            } else {
                return new ModelAndView("UpdateDetails", "message", "Customer ID not Found");
            }
        } catch (NumberFormatException e) {
            return new ModelAndView("UpdateDetails", "message", "Invalid Customer ID format");
        }
    }



    @PostMapping("/update/name")
    public ModelAndView updateName(@RequestParam int customerId, @RequestParam String newName,HttpSession session, ModelMap model ) {
        Admin admin  = (Admin) session.getAttribute("username");
        model.addAttribute("username", admin.getName());
        customerService.updateCustomerName(customerId, newName);
        model.addAttribute("message", "Name updated successfully: " + newName);
        return new ModelAndView("UpdateOptions","message","Name Updated Successfully");
    }

    @PostMapping("/update/email")
    public ModelAndView updateEmail(@RequestParam int customerId, @RequestParam String newEmail, HttpSession session, ModelMap model) {
        Admin admin  = (Admin) session.getAttribute("username");
        model.addAttribute("username", admin.getName());
        customerService.updateCustomerEmail(customerId, newEmail);
        return new ModelAndView("UpdateOptions","message","Email Updated Successfully");

    }
    @GetMapping(value = "/alltransactions1")
    public ModelAndView AllTransactionsadmin(HttpSession session, ModelMap model)
    {
        Admin admin  = (Admin) session.getAttribute("username");
        model.addAttribute("username", admin.getName());
        return new ModelAndView("Alltransactionsadmin");
    }
    @GetMapping(value = "/alltransactionsdisplayadmin")
    public ModelAndView AllTransactionsDisplayForAdmin(@RequestParam Integer accountNumber,HttpSession session, ModelMap model)
    {
        Admin admin  = (Admin) session.getAttribute("username");
        model.addAttribute("username", admin.getName());
        Account account = accountService.findAccountByNumber(accountNumber);
        if (account!=null)
        {
            List<Transaction> transactions = transactionService.getAllTransactions(accountNumber);
            return new ModelAndView("Alltransactionsadmin","transactionList",transactions);
        }
        else
        {
            return new ModelAndView("Alltransactionsadmin", "balanceMessage", "Account not found");

        }

    }
    @GetMapping(value = "/last10transactions1")
    public ModelAndView last10Transactionsadmin(HttpSession session, ModelMap model)
    {
        Admin admin  = (Admin) session.getAttribute("username");
        model.addAttribute("username", admin.getName());
        return new ModelAndView("Last10TransactionsAdmin");
    }
    @GetMapping(value = "/last10transactionsdisplayadmin")
    public ModelAndView last10TransactionsDisplayForAdmin(@RequestParam Integer accountNumber,HttpSession session, ModelMap model)
    {
        Admin admin  = (Admin) session.getAttribute("username");
        model.addAttribute("username", admin.getName());
        Account account = accountService.findAccountByNumber(accountNumber);
        if (account!=null)
        {
            List<Transaction> transactions = transactionService.getLast10Transactions(accountNumber);
            return new ModelAndView("Last10TransactionsAdmin","transactionList",transactions);
        }
        else
        {
            return new ModelAndView("Last10TransactionsAdmin", "balanceMessage", "Account not found");

        }

    }

}

