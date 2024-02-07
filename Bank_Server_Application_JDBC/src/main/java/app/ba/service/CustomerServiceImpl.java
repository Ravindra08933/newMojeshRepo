package app.ba.service;
import app.Generator.NumberGenerator;
import app.ba.Exception.CustomerNotFoundException;
import app.ba.bean.Account;
import app.ba.bean.Customer;
import app.ba.dao.AccountDao;
import app.ba.dao.AccountDaoImpl;
import app.ba.dao.CustomerDao;
import app.ba.dao.CustomerDaoImpl;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;
public class CustomerServiceImpl implements CustomerService {

    CustomerDao customerDao = new CustomerDaoImpl();
    NumberGenerator numberGenerator = new NumberGenerator();
    @Override
    public Customer createCustomer(String name, String email) throws SQLException {
        int customerId = numberGenerator.generateNumber();
        return customerDao.createCustomer(customerId,name,email);
    }
    @Override
    public Customer findCustomerByName(String name) throws CustomerNotFoundException, SQLException {
       return  customerDao.findCustomerByName(name);

    }
    @Override
    public Customer findCustomerById(int num) throws CustomerNotFoundException, SQLException {
        return customerDao.findCustomerById(num);
    }
    @Override
    public Customer findCustomerByAccountNumber(int accNum) throws CustomerNotFoundException, SQLException {
        return customerDao.findCustomerByAccountNumber(accNum);
    }
    @Override
    public Customer updateCustomerName(int customerId, String newName) throws CustomerNotFoundException, SQLException {
        return customerDao.updateCustomerName(customerId,newName);
    }
    @Override
    public Customer updateCustomerEmail(int customerId, String newEmail) throws CustomerNotFoundException, SQLException {
        return customerDao.updateCustomerEmail(customerId,newEmail);
    }
}
