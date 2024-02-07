package app.ba.dao;
import app.ba.bean.Customer;
public interface CustomerDao {

    Customer findCustomerById(int customerId);
    Customer findCustomerByName(String Name);

}
