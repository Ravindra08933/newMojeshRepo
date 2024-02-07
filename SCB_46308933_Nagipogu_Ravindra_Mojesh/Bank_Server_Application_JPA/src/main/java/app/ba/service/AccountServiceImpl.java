package app.ba.service;
import app.Generator.NumberGenerator;
import app.ba.Exception.AccountNotFoundException;
import app.ba.bean.Account;
import app.ba.bean.Customer;
import app.ba.bean.Transaction;
import app.ba.dao.AccountDao;
import app.ba.dao.AccountDaoImpl;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class AccountServiceImpl implements AccountService {
    AccountDao accountDao = new AccountDaoImpl();
    public static NumberGenerator numberGenerator = new NumberGenerator();
    @Override
    public double checkBalance(int accountNumber) throws AccountNotFoundException {
        return accountDao.findAccountByNumber(accountNumber).getCurrentBalance();
    }
    @Override
    public String deposit(int accountNumber, double amount)throws AccountNotFoundException {
        return accountDao.deposit(accountNumber,amount);
    }
    @Override
    public String withdraw(int accountNumber, double amount)throws AccountNotFoundException {
        return accountDao.withdraw(accountNumber,amount);
    }
    @Override
    public String transfer(int fromAccountNumber, int toAccountNumber, double amount)throws AccountNotFoundException {
        return accountDao.transfer(fromAccountNumber,toAccountNumber,amount);
    }
    @Override
    public Account createAccount(String name,String email,double amount) {
        Customer customer = new Customer(name,email);
        Account account = new Account(amount,"Savings",customer);
       return  accountDao.createAccount(account);

    }

    @Override
    public String deleteAccount(int accountNumber) throws AccountNotFoundException {
        return accountDao.deleteAccount(accountNumber);
    }

}
