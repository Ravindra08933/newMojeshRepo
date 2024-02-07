package com.cg.scb.controller;

import com.cg.scb.dto.Account;
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

@RestController
@RequestMapping(value ="login")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    AccountService accountService;
    @Autowired
    TransactionService transactionService;

    @GetMapping(value = "/register")
    public ModelAndView register()
    {
        return new ModelAndView("Registration");
    }
    @PostMapping("/registration")
    public ModelAndView registerUser(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password,
            Model model) {
        Customer customer = customerService.createCustomer(name, email, password);
        if(customer!=null)
        {
            return new ModelAndView("Registration","Message","Customer details saved Successfully");
        }
        return null;
    }


    @GetMapping(value = "/userloginpage")
    public ModelAndView displayUserLoginPage() {
        ModelAndView loginPageView =
                new ModelAndView("UserLogin", "fname", "Vaishali");
        return loginPageView;
    }

    @PostMapping(value = "/validateuser")
    public ModelAndView validateUser(
            @RequestParam String username, @RequestParam String password,
            HttpSession session,
            ModelMap model) {
        List<Customer> customers = customerService.getAllCustomers();
        Customer temp = isCustomerIdPresent(customers, username, password);
        if (temp != null) {
            session.setAttribute("username", temp);
            model.addAttribute("username", temp.getName());
            return new ModelAndView("Operations");
        } else {
            model.addAttribute("error", "Invalid username or password");
            return new ModelAndView("UserLogin");
        }
    }

    private static Customer isCustomerIdPresent(List<Customer> customers, String username, String password) {
        for (Customer customer : customers) {
            if (customer.getEmail().equals(username) && customer.getPassword().equals(password)) {
                return customer;
            }
        }
        return null;
    }

    @GetMapping(value = "/alltransactions")
    public ModelAndView AllTransactions(HttpSession session, ModelMap model) {
        Customer username = (Customer) session.getAttribute("username");
        model.addAttribute("username", username.getName());
        return new ModelAndView("Alltransactions");
    }

    @GetMapping(value = "/alltransactionsdisplay")
    public ModelAndView AllTransactionsDisplay(@RequestParam Integer accountNumber, HttpSession session, ModelMap model) {
        Customer username = (Customer) session.getAttribute("username");
        model.addAttribute("username", username.getName());
        Account account = accountService.findAccountByNumber(accountNumber);
        if (account != null) {
            if(username.getCustomerId()!=account.getCustomer().getCustomerId())
            {
                return new ModelAndView("Alltransactions", "balanceMessage", "Account Number Mismatch");
            }
            List<Transaction> transactions = transactionService.getAllTransactions(accountNumber);
            return new ModelAndView("Alltransactions", "transactionList", transactions);
        }
        else
        {
            return new ModelAndView("All Transactions", "balanceMessage", "Account not found");

        }
    }

    @GetMapping(value = "/balance")
    public ModelAndView balance(HttpSession session, ModelMap model) {
        Customer username = (Customer) session.getAttribute("username");
        model.addAttribute("username", username.getName());
        return new ModelAndView("Balance");
    }

    @GetMapping("/checkBalance")
    public ModelAndView checkBalance(@RequestParam Integer accountNumber, HttpSession session, ModelMap model) {
        Customer username = (Customer) session.getAttribute("username");
        model.addAttribute("username", username.getName());
        Account account = accountService.findAccountByNumber(accountNumber);
        if (account != null) {
            if (username.getCustomerId() == account.getCustomer().getCustomerId()) {
                return new ModelAndView("Balance", "balanceMessage", account.getCurrentBalance());
            } else {
                return new ModelAndView("Balance", "balanceMessage", "Account Number Mismatch");
            }
        }
        return new ModelAndView("Balance", "balanceMessage", "No Account Found");
    }
    @GetMapping(value = "/deposit")
    public ModelAndView deposit(HttpSession session, ModelMap model)
    {
        Customer username = (Customer) session.getAttribute("username");
        model.addAttribute("username", username.getName());
        return new ModelAndView("Deposit");
    }
    @GetMapping("/depositmoney")
    public ModelAndView performTransaction(
            @RequestParam Integer accountNumber,
            @RequestParam double amount,HttpSession session, ModelMap model)
    {
        Customer username = (Customer) session.getAttribute("username");
        model.addAttribute("username", username.getName());
        Account account = accountService.findAccountByNumber(accountNumber);
        if(account!=null)
        {
            if(username.getCustomerId()!=account.getCustomer().getCustomerId())
            {
                return new ModelAndView("Deposit", "balanceMessage", "Account Number Mismatch");
            }
            accountService.deposit(accountNumber, amount);
            return new ModelAndView("Deposit", "balanceMessage", account.getCurrentBalance()+amount);
        }
        else
        {
            return new ModelAndView("Deposit", "balanceMessage", "Account not found");

        }

    }
    @GetMapping(value = "/withdraw")
    public ModelAndView withdraw(HttpSession session, ModelMap model)
    {
        Customer username = (Customer) session.getAttribute("username");
        model.addAttribute("username", username.getName());
        return new ModelAndView("Withdraw");
    }
    @GetMapping("/withdrawmoney")
    public ModelAndView withdraw(
            @RequestParam Integer accountNumber,
            @RequestParam double amount,HttpSession session, ModelMap model) {
        Customer username = (Customer) session.getAttribute("username");
        model.addAttribute("username", username.getName());
        Account account = accountService.findAccountByNumber(accountNumber);
        if(account!=null)
        {
            if(username.getCustomerId()!=account.getCustomer().getCustomerId())
            {
                return new ModelAndView("Withdraw", "balanceMessage", "Account Number Mismatch");
            }
            accountService.withdraw(accountNumber, amount);
            return new ModelAndView("Withdraw", "balanceMessage", account.getCurrentBalance()-amount);
        }
        else
        {
            return new ModelAndView("Withdraw", "balanceMessage", "Account not found");

        }

    }
    @GetMapping(value = "/transfer")
    public ModelAndView transfer(HttpSession session, ModelMap model)
    {
        Customer username = (Customer) session.getAttribute("username");
        model.addAttribute("username", username.getName());
        return new ModelAndView("Transfer");
    }
    @GetMapping("/transfermoney")
    public ModelAndView transfer(
            @RequestParam Integer fromaccountNumber,@RequestParam Integer toaccountNumber,
            @RequestParam double amount,HttpSession session, ModelMap model) {
        Customer username = (Customer) session.getAttribute("username");
        model.addAttribute("username", username.getName());
        Account fromAccount = accountService.findAccountByNumber(fromaccountNumber);
        Account toAccount = accountService.findAccountByNumber(toaccountNumber);
        if(fromAccount!=null && toAccount!=null)
        {
            if(username.getCustomerId()!=fromAccount.getCustomer().getCustomerId())
            {
                return new ModelAndView("Transfer", "balanceMessage", "Account Number Mismatch");
            }
            accountService.transfer(fromaccountNumber, toaccountNumber, amount);
            return new ModelAndView("Transfer", "balanceMessage", fromAccount.getCurrentBalance()-amount);
        }
        else
        {
            return new ModelAndView("Transfer", "balanceMessage", "Account not found");

        }

    }
    @GetMapping(value = "/last10transactions")
    public ModelAndView last10Transactions(HttpSession session, ModelMap model)
    {
        Customer username = (Customer) session.getAttribute("username");
        model.addAttribute("username", username.getName());
        return new ModelAndView("Last10Transactions");
    }
    @GetMapping(value = "/last10transactionsdisplay")
    public ModelAndView last10TransactionsDisplay(@RequestParam Integer accountNumber,HttpSession session, ModelMap model)
    {
        Customer username = (Customer) session.getAttribute("username");
        model.addAttribute("username", username.getName());
        Account account = accountService.findAccountByNumber(accountNumber);
       if(account!=null)
       {
            if(username.getCustomerId()!=account.getCustomer().getCustomerId())
                return new ModelAndView("Last10Transactions", "balanceMessage", "Account Number Mismatch");
            List<Transaction> transactions = transactionService.getLast10Transactions(accountNumber);
            return new ModelAndView("Last10Transactions","transactionList",transactions);
        }
        else
        {
            return new ModelAndView("Last10Transactions", "balanceMessage", "Account not found");

        }

    }
    @GetMapping(value = "/accountdetails")
    public ModelAndView AccountDetails(HttpSession session, ModelMap model)
    {
        Customer username = (Customer) session.getAttribute("username");
        model.addAttribute("username", username.getName());
        return new ModelAndView("Account");
    }
    @GetMapping("/checkaccountdetails")
    public ModelAndView checkAccountDetails(@RequestParam Integer accountNumber, HttpSession session, ModelMap model) {
        Customer username = (Customer) session.getAttribute("username");
        model.addAttribute("username", username.getName());
       Account account = accountService.findAccountByNumber(accountNumber);
       if(account!=null)
       {
            if (username.getCustomerId() == account.getCustomer().getCustomerId()) {
                return new ModelAndView("AccountDetails", "account", account);
            } else {
                return new ModelAndView("AccountDetails", "balanceMessage", "Account Number Mismatch");
            }
        } else {
            return new ModelAndView("AccountDetails", "balanceMessage", "Account not found");
        }
    }
    @GetMapping("/customerdetails")
    public ModelAndView checkCustomerDetails(HttpSession session, ModelMap model) {
        Customer username = (Customer) session.getAttribute("username");
        model.addAttribute("username", username.getName());
        return new ModelAndView("CustomerDetails","customer",username);
    }


    @GetMapping(value = "/operations")
    public ModelAndView Operations(HttpSession session,
                                   ModelMap model)
    {
        Customer username = (Customer) session.getAttribute("username");
        model.addAttribute("username", username.getName());
        return new ModelAndView("Operations");
    }
}
