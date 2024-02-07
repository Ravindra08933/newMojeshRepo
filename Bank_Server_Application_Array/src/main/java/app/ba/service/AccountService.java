package app.ba.service;
import app.ba.bean.Account;
import app.ba.bean.Customer;
public interface AccountService {
    double checkBalance(int accountNumber) ;
    String deposit(int accountNumber, double amount);
    String withdraw(int accountNumber, double amount);
    String transfer(int fromAccountNumber, int toAccountNumber, double amount);
    Account createAccount(String name, String email,double amount);
    Customer updateAccount(int accountNumber, String Name);
    Customer updateAccountEmail(int accountNumber, String Name);
    String deleteAccount(int accountNumber);


}
