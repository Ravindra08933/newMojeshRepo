package app.ba.service;
import app.ba.bean.Customer;

import java.sql.SQLException;
public interface CustomerService {

    Customer createCustomer(String name,String email) throws SQLException;

    Customer findCustomerByName(String name) throws SQLException;
    Customer findCustomerById(int num) throws SQLException;
    Customer findCustomerByAccountNumber(int accNum) throws SQLException;
    Customer updateCustomerName(int customerId,String newName) throws SQLException;
    Customer updateCustomerEmail(int customerId,String newEmail) throws SQLException;

}
