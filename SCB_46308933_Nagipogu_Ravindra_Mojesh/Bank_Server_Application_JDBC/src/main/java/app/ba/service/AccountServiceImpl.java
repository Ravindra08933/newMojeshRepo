package app.ba.service;
import app.Generator.NumberGenerator;
import app.ba.Exception.AccountNotFoundException;
import app.ba.bean.Account;
import app.ba.bean.Customer;
import app.ba.dao.*;

import java.sql.SQLException;
public class AccountServiceImpl implements AccountService {
    AccountDao accountDao = new AccountDaoImpl();
    TransactionDao transactionDao = new TransactionDaoImpl();
    CustomerDao customerDao = new CustomerDaoImpl();
    public static NumberGenerator numberGenerator = new NumberGenerator();

    @Override
    public double checkBalance(int accountNumber) throws AccountNotFoundException, SQLException {
            Account account = accountDao.findAccountByNumber(accountNumber);
            if (account != null)
            {
                return account.getCurrentBalance();
            }
                throw new AccountNotFoundException("Account not found for account number: " + accountNumber);
    }
    @Override
    public String deposit(int accountNumber, double amount) throws AccountNotFoundException, SQLException {
            if(amount<0)
            {
                return "Amount should be greater than 0";
            }
            else
            {

                Account account = accountDao.findAccountByNumber(accountNumber);
                if (account == null) {
                    throw new AccountNotFoundException("Account not found with account number: " + accountNumber);
                }
                double newBalance = account.getCurrentBalance() + amount;
                accountDao.updateBalance(accountNumber, newBalance);
                transactionDao.createTransaction(accountNumber, "DEPOSIT", amount);
                return "Deposit successful. New balance: " + newBalance;
            }
        }

    @Override
    public String withdraw(int accountNumber, double amount) throws AccountNotFoundException, SQLException {
        if(amount<0)
        {
            return "Amount should be greater than 0";
        }
        else
        {

            Account account = accountDao.findAccountByNumber(accountNumber);
            if (account == null) {
                throw new AccountNotFoundException("Account not found with account number: " + accountNumber);
            }
            double newBalance = account.getCurrentBalance() - amount;
            accountDao.updateBalance(accountNumber, newBalance);
            transactionDao.createTransaction(accountNumber, "WITHDRAW", amount);
            return "Withdraw successful. New balance: " + newBalance;
        }
    }
    @Override
    public String transfer(int fromAccountNumber, int toAccountNumber, double amount) throws AccountNotFoundException, SQLException {
        if(amount<0)
        {
            return "Amount should be greater than 0";
        }
        else {
            Account fromAccount = accountDao.findAccountByNumber(fromAccountNumber);
            Account toAccount = accountDao.findAccountByNumber(toAccountNumber);
            if (fromAccount != null && toAccount != null)
            {
                double fromBalance = fromAccount.getCurrentBalance();
                if (fromBalance - amount >= 0)
                {
                    double toBalance = toAccount.getCurrentBalance();
                    accountDao.updateBalance(fromAccountNumber,fromBalance);
                    transactionDao.createTransaction(fromAccountNumber, "SENT", amount);
                    accountDao.updateBalance(toAccountNumber,toBalance);
                    transactionDao.createTransaction(toAccountNumber, "RECEIVED", amount);
                    return "Transfer successful.";
                }
                else
                {
                    return "Insufficient funds for transfer.";
                }
            }
            throw new AccountNotFoundException("Account not found for account number: " + fromAccountNumber);
        }
    }


    @Override
    public Account createAccount(String name,String email,double amount) throws SQLException {
        Customer customer = new Customer(numberGenerator.generateNumber(),name,email);
       return  accountDao.createAccount(customer,amount);

    }

    @Override
    public String deleteAccount(int accountNumber) throws AccountNotFoundException, SQLException {
        return accountDao.deleteAccount(accountNumber);
    }
    @Override
    public Account createAccount1(int id,double money) throws SQLException {
        Customer customer = customerDao.findCustomerById(id);
        return accountDao.createAccount1(money,customer);
    }
}
