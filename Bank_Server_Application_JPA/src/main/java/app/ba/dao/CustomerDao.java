package app.ba.dao;
import app.ba.bean.Customer;
public interface CustomerDao
{


    Customer findCustomerById(int customerId);
    Customer findCustomerByName(String Name);
    Customer updateCustomerName(int customerId,String newName);
    Customer updateCustomerEmail(int customerId,String newEmail);
    Customer findCustomerByAccountNumber(int accNum);

}
