package app.ba.service;
import app.ba.bean.Customer;
public interface CustomerService {

    Customer findCustomerByName(String name);
    Customer findCustomerById(int num);
    Customer findCustomerByAccountNumber(int accNum);

}
