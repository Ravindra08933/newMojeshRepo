package app.ba.dao;

import app.Generator.NumberGenerator;
import app.ba.Exception.AccountNotFoundException;
import app.ba.bean.Account;
import app.ba.bean.Customer;
import app.ba.util.CollectionUtil;

import java.util.Iterator;
import java.util.Set;

import static app.ba.util.CollectionUtil.accounts;
public class AccountDaoImpl implements AccountDao {

    private NumberGenerator numberGenerator = new NumberGenerator();



    @Override
    public Account findAccountByNumber(int accountNumber) throws AccountNotFoundException {
        Iterator<Account> iterator = accounts.iterator();
        while(iterator.hasNext())
        {
            Account account = iterator.next();
            if (account != null && account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        throw new AccountNotFoundException("Account Not found");
    }

    @Override
    public Account findAccountByCustomerId(int customerId) {
        Iterator<Account> iterator = accounts.iterator();
        while(iterator.hasNext())
        {
            Account account = iterator.next();
            if (account != null && account.getCustomer().getCustomerId() == customerId) {
                return account;
            }
        }
        return null;
    }

    @Override
    public Set<Account> getAllAccounts() {
        return accounts;
    }

    @Override
    public Account createAccount(Customer customer, double amount) {
        int accNum = numberGenerator.generateNumber();
        CollectionUtil.createAccount(accNum, amount, "Savings", customer);
        return findAccountByNumber(accNum);
    }

    @Override
    public String deleteAccount(int accountNumber) throws AccountNotFoundException {
        return CollectionUtil.deleteAccount(accountNumber);
    }

    @Override
    public Customer updateAccount(int accNum, String name) {
        return CollectionUtil.updateAccount(accNum, name);
    }

    @Override
    public Customer updateAccountEmail(int accNum, String name) {
        return CollectionUtil.updateAccountEmail(accNum, name);
    }
}
