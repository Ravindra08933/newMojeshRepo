package app.ba.dao;
import app.ba.Exception.CustomerNotFoundException;
import app.ba.bean.Customer;
import app.ba.util.CollectionUtil;

import java.util.Iterator;
public class CustomerDaoImpl implements CustomerDao{

    @Override
    public Customer findCustomerById(int customerId) throws CustomerNotFoundException  {
        Iterator<Customer> iterator = CollectionUtil.customers.iterator();
        while(iterator.hasNext())
        {
            Customer customer = iterator.next();
            if (customer != null && customer.getCustomerId() == customerId) {
                return customer;
            }
        }
        throw new CustomerNotFoundException("Customer with customer ID " + customerId + " not found");
    }
    @Override
    public Customer findCustomerByName(String Name) throws CustomerNotFoundException {
        Iterator<Customer> iterator = CollectionUtil.customers.iterator();
        while(iterator.hasNext())
        {
            Customer customer = iterator.next();
            if (customer != null && customer.getName().equals(Name)) {
                return customer;
            }
        }
        throw new CustomerNotFoundException("Customer with name " + Name + " not found");
    }


}
