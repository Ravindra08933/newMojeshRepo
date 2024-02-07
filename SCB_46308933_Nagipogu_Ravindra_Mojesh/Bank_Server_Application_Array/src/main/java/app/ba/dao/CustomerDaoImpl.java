package app.ba.dao;
import app.ba.exception.CustomerNotFoundException;
import app.ba.bean.Customer;
import app.ba.util.ArrayUtil;
public class CustomerDaoImpl implements CustomerDao{
    @Override
    public Customer findCustomerById(int customerId) throws CustomerNotFoundException  {
        for (Customer customer : ArrayUtil.customers) {
            if (customer != null && customer.getCustomerId() == customerId) {
                return customer;
            }
        }
        throw new CustomerNotFoundException("Customer with customer ID " + customerId + " not found");
    }
    @Override
    public Customer findCustomerByName(String Name) throws CustomerNotFoundException {
        for (Customer customer : ArrayUtil.customers) {

            if (customer != null && customer.getName().equals(Name)) {
                return customer;
            }
        }
        throw new CustomerNotFoundException("Customer with name " + Name + " not found");
    }


}
