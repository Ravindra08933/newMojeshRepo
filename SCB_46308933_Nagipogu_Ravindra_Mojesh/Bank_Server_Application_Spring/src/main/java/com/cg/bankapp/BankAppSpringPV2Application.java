package com.cg.bankapp;
import com.cg.bankapp.service.AccountService;
import com.cg.bankapp.service.CustomerService;
import com.cg.bankapp.service.TransactionService;
import com.cg.bankapp.ui.Bank_Server_Application_Spring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BankAppSpringPV2Application implements CommandLineRunner {

    private AccountService accountService;
    private CustomerService customerService;
    private TransactionService transactionService;
    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public static void main(String[] args) {
        SpringApplication.run(BankAppSpringPV2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        new Bank_Server_Application_Spring().Main(accountService,customerService,transactionService);

    }
}
