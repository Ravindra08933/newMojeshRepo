package app.ba.dao;
import app.ba.Exception.AccountNotFoundException;
import app.ba.Exception.CustomerNotFoundException;
import app.ba.bean.Account;
import app.ba.bean.Customer;
import app.ba.util.JPAUtil;
import jakarta.persistence.*;

import java.util.List;
public class CustomerDaoImpl implements CustomerDao{
    EntityManager em = JPAUtil.getEntityManager();
    EntityTransaction tx = JPAUtil.em.getTransaction();
    @Override
    public Customer findCustomerById(int customerId) throws CustomerNotFoundException {

        Customer customer = em.find(Customer.class, customerId);

        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found with ID " + customerId);
        }

        return customer;
    }
    @Override
    public Customer findCustomerByName(String Name) throws CustomerNotFoundException {
        tx.begin();
        String queryString = "SELECT c FROM Customer c WHERE c.name = :name";
        Query query = em.createQuery(queryString);
        query.setParameter("name", Name);
        List<Customer> customers = query.getResultList();
        tx.commit();
        if (customers.isEmpty()) {
            throw new CustomerNotFoundException("Customer not found with name: " + Name);
        }
        return customers.get(0);

    }
    @Override
    public Customer updateCustomerName(int customerId, String newName) throws CustomerNotFoundException {
            tx.begin();
            Customer customer = em.find(Customer.class, customerId);
            if (customer == null) {
                throw new CustomerNotFoundException("Customer not found with customerId: " + customerId);
            }
            customer.setName(newName);
            em.merge(customer);
            tx.commit();
            return customer;
    }
    @Override
    public Customer updateCustomerEmail(int customerId, String newEmail) throws CustomerNotFoundException {
        tx.begin();
        Customer customer = em.find(Customer.class, customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found with customerId: " + customerId);
        }
        customer.setEmail(newEmail);
        em.merge(customer);
        tx.commit();
        return customer;
    }
    @Override
    public Customer findCustomerByAccountNumber(int accNum) throws CustomerNotFoundException {
            String jpql = "SELECT c FROM Customer c JOIN FETCH c.accounts a WHERE a.accountNumber = :accNum";
            TypedQuery<Customer> query = em.createQuery(jpql, Customer.class);
            query.setParameter("accNum", accNum);

            Customer customer = query.getSingleResult();
            if(customer!=null)
                return customer;
            throw new CustomerNotFoundException("Customer not found for account number: " + accNum);
    }
}
