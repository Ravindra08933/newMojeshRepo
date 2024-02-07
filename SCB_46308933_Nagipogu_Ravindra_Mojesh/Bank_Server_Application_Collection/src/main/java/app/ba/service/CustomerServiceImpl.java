package app.ba.service;
import app.ba.Exception.CustomerNotFoundException;
import app.ba.bean.Account;
import app.ba.bean.Customer;
import app.ba.dao.AccountDao;
import app.ba.dao.AccountDaoImpl;
import app.ba.dao.CustomerDao;
import app.ba.dao.CustomerDaoImpl;

import java.util.Iterator;
import java.util.Set;
public class CustomerServiceImpl implements CustomerService {
    CustomerDao customerDao = new CustomerDaoImpl();
    AccountDao accountDao = new AccountDaoImpl();
    @Override
    public Customer findCustomerByName(String name) throws CustomerNotFoundException {
       return  customerDao.findCustomerByName(name);

    }
    @Override
    public Customer findCustomerById(int num) throws CustomerNotFoundException {
        return customerDao.findCustomerById(num);
    }
    @Override
    public Customer findCustomerByAccountNumber(int accNum) throws CustomerNotFoundException  {
        Set<Account> accounts = accountDao.getAllAccounts();
        Iterator<Account> iterator = accounts.iterator();
        while (iterator.hasNext())
        {
            Account account = iterator.next();
            if(account!=null && account.getAccountNumber() == accNum)
            {
                return account.getCustomer();
            }
        }
        throw new CustomerNotFoundException("Customer With Account Number "+accNum + " Not Found");
    }
}
