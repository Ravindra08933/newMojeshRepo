package app.ba.dao;
import app.ba.bean.Customer;

import java.sql.SQLException;
public interface CustomerDao {
    Customer createCustomer(int customerId,String name, String email) throws SQLException;
    Customer findCustomerById(int customerId) throws SQLException;
    Customer findCustomerByName(String Name)throws SQLException ;
    Customer updateCustomerName(int customerId,String newName) throws SQLException;
    Customer updateCustomerEmail(int customerId,String newEmail) throws SQLException;
    Customer findCustomerByAccountNumber(int accNum) throws SQLException;
}
