package app.ba.dao;

import app.ba.Exception.AccountNotFoundException;
import app.ba.bean.Account;
import app.ba.bean.Customer;
import app.ba.bean.Transaction;
import app.ba.util.JPAUtil;
import jakarta.persistence.*;

import java.util.List;
public class AccountDaoImpl implements AccountDao {
    TransactionDao transactionDao = new TransactionDaoImpl();
    public EntityManager entityManager = JPAUtil.getEntityManager();
    public EntityTransaction transaction = entityManager.getTransaction();
    @Override
    public Account createAccount(Account account) {
        transaction.begin();
            if (account.getCustomer() != null) {
                Customer existingCustomer = entityManager.find(Customer.class, account.getCustomer().getCustomerId());
                if (existingCustomer == null) {
                    account.setCustomer(entityManager.merge(account.getCustomer()));
                }
            }
            Customer customer = account.getCustomer();
            entityManager.persist(customer);
            entityManager.persist(account);
            transaction.commit();
            transactionDao.recordTransaction(account);
        return account;
    }
    @Override
    public Account findAccountByNumber(int accountNumber) throws AccountNotFoundException {
            Account account = entityManager.find(Account.class, accountNumber);
            if(account == null)
                throw new AccountNotFoundException("Account with account number " + accountNumber + " not found.");
            return account;
    }
    @Override
    public Account findAccountByCustomerId(int customerId) throws AccountNotFoundException {
            String jpql = "SELECT a FROM accounts a WHERE a.customer.customerId = :customerId";
            TypedQuery<Account> query = entityManager.createQuery(jpql, Account.class);
            query.setParameter("customerId", customerId);
            Account account = query.getSingleResult();
            if (account == null) {
                throw new AccountNotFoundException("Account not found for customer id: " + customerId);
            }
            return account;

    }
    @Override
    public String deleteAccount(int accountNumber) throws AccountNotFoundException {
            transaction.begin();
            Account account = entityManager.find(Account.class, accountNumber);
            if (account == null) {
                throw new AccountNotFoundException("Account with account number " + accountNumber + " not found.");
            }
            Customer customer = account.getCustomer();
            entityManager.remove(account);
            if (customer != null) {
                entityManager.remove(customer);
            }
            List<Transaction> transactions = transactionDao.getAllTransactions(accountNumber);
            for (Transaction transaction : transactions) {
                entityManager.remove(entityManager.contains(transaction) ? transaction : entityManager.merge(transaction));
            }
            transaction.commit();
            return "Account and associated customer deleted successfully.";
    }
    @Override
    public String deposit(int accountNumber, double amount) throws AccountNotFoundException {
        transaction.begin();
            Account account = entityManager.find(Account.class, accountNumber);
            if (account == null) {
                throw new AccountNotFoundException("Account not found with accountNumber: " + accountNumber);
            }
            double currentBalance = account.getCurrentBalance();
            currentBalance += amount;
            account.setCurrentBalance(currentBalance);
            entityManager.merge(account);
            transaction.commit();
            transactionDao.recordTransaction(account);
            return "Amount deposited successfully. New balance: " + currentBalance;
    }
    @Override
    public String withdraw(int accountNumber, double amount) throws AccountNotFoundException {
        transaction.begin();
            Account account = entityManager.find(Account.class, accountNumber);
            if (account == null) {
                throw new AccountNotFoundException("Account not found with accountNumber: " + accountNumber);
            }
            double currentBalance = account.getCurrentBalance();
            currentBalance -= amount;
            account.setCurrentBalance(currentBalance);
            entityManager.merge(account);
            transaction.commit();
            transactionDao.recordTransaction(account);
            return "Amount withdraw successfully. New balance: " + currentBalance;
    }
    @Override
    public String transfer(int fromAccountNumber, int toAccountNumber, double amount)throws AccountNotFoundException {
        transaction.begin();
            Account fromAccount = entityManager.find(Account.class, fromAccountNumber);
            if (fromAccount == null) {
                throw new AccountNotFoundException("Account not found with accountNumber: " + fromAccountNumber);
            }
            Account toAccount = entityManager.find(Account.class, toAccountNumber);
            if (toAccount == null) {
                throw new AccountNotFoundException("Account not found with accountNumber: " + toAccountNumber);
            }
            double fromAccountBalance = fromAccount.getCurrentBalance();
            if (fromAccountBalance < amount) {
                throw new AccountNotFoundException("Insufficient balance in accountNumber: " + fromAccountNumber);
            }
            fromAccount.setCurrentBalance(fromAccountBalance - amount);
            toAccount.setCurrentBalance(toAccount.getCurrentBalance() + amount);
            entityManager.merge(fromAccount);
            entityManager.merge(toAccount);
            transaction.commit();
            transactionDao.recordTransaction(fromAccount);
            transactionDao.recordTransaction(toAccount);
            return "Amount transferred successfully. New balance in account " + fromAccountNumber + ": " +
                    fromAccount.getCurrentBalance() + ". New balance in account " + toAccountNumber + ": " +
                    toAccount.getCurrentBalance();
    }
}