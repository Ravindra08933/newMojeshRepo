package app.ba.service;
import app.ba.Exception.CustomerNotFoundException;
import app.ba.bean.Account;
import app.ba.bean.Customer;
import app.ba.dao.AccountDao;
import app.ba.dao.AccountDaoImpl;
import app.ba.dao.CustomerDao;
import app.ba.dao.CustomerDaoImpl;
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
        for(Account account:accountDao.getAllAccounts())
        {
            if(account!=null && account.getAccountNumber() == accNum)
            {
                return account.getCustomer();
            }
        }
        throw new CustomerNotFoundException("Customer With Account Number "+accNum + " Not Found");
    }
}
