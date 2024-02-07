package app.ba.dao;
import app.ba.Exception.CustomerNotFoundException;
import app.ba.bean.Customer;

import java.sql.*;

import static app.ba.util.JDBCUtil.*;


public class CustomerDaoImpl implements CustomerDao{
    @Override
    public Customer createCustomer(int customerId,String name, String email) throws SQLException {
        String INSERT_CUSTOMER_SQL = "INSERT INTO customer (customer_id, name, email) VALUES (?, ?, ?)";
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

        PreparedStatement preparedStatementCustomer = connection.prepareStatement(INSERT_CUSTOMER_SQL);
        preparedStatementCustomer.setInt(1, customerId);
        preparedStatementCustomer.setString(2, name);
        preparedStatementCustomer.setString(3, email);
        int rowsAffected = preparedStatementCustomer.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Customer Created Successfully.");
        } else {
            System.out.println("Failed to save customer.");
        }
        preparedStatementCustomer.close();
        connection.close();
        return findCustomerById(customerId);
    }
    @Override
    public Customer findCustomerById(int customerId) throws CustomerNotFoundException, SQLException {
        String FIND_CUSTOMER_BY_ID_QUERY = "SELECT * FROM customer WHERE customer_id = ?";
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_CUSTOMER_BY_ID_QUERY);
        preparedStatement.setInt(1, customerId);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            Customer customer = new Customer();
            customer.setCustomerId(resultSet.getInt("customer_id"));
            customer.setName(resultSet.getString("name"));
            customer.setEmail(resultSet.getString("email"));
            resultSet.close();
            preparedStatement.close();
            connection.close();
            return customer;
        } else {
            resultSet.close();
            preparedStatement.close();
            connection.close();
            throw new CustomerNotFoundException("Customer not found with customer_id: " + customerId);
        }
    }

    @Override
    public Customer findCustomerByName(String name) throws CustomerNotFoundException, SQLException {
        String FIND_CUSTOMER_BY_NAME_QUERY = "SELECT * FROM customer WHERE name = ?";
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_CUSTOMER_BY_NAME_QUERY);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            Customer customer = new Customer();
            customer.setCustomerId(resultSet.getInt("customer_id"));
            customer.setName(resultSet.getString("name"));
            customer.setEmail(resultSet.getString("email"));
            resultSet.close();
            preparedStatement.close();
            connection.close();
            return customer;
        } else {
            resultSet.close();
            preparedStatement.close();
            connection.close();
            throw new CustomerNotFoundException("Customer not found with name: " + name);
        }
    }

    @Override
    public Customer updateCustomerName(int customerId, String newName) throws CustomerNotFoundException, SQLException {
        String updateQuery = "UPDATE customer SET name = ? WHERE customer_id = ?";
        String selectQuery = "SELECT * FROM customer WHERE customer_id = ?";
        Connection connection = null;
        PreparedStatement updateStatement = null;
        PreparedStatement selectStatement = null;
        ResultSet resultSet = null;

        connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

        updateStatement = connection.prepareStatement(updateQuery);
        updateStatement.setString(1, newName);
        updateStatement.setInt(2, customerId);
        int rowsUpdated = updateStatement.executeUpdate();

        if (rowsUpdated == 0) {
            throw new CustomerNotFoundException("No customer found with customer_id: " + customerId);
        }

        selectStatement = connection.prepareStatement(selectQuery);
        selectStatement.setInt(1, customerId);

        resultSet = selectStatement.executeQuery();

        if (resultSet.next()) {
            Customer updatedCustomer = new Customer();
            updatedCustomer.setCustomerId(resultSet.getInt("customer_id"));
            updatedCustomer.setName(resultSet.getString("name"));
            updatedCustomer.setEmail(resultSet.getString("email"));
            resultSet.close();
            selectStatement.close();
            return updatedCustomer;
        } else {
            throw new CustomerNotFoundException("No customer found with customer_id: " + customerId);
        }
    }

    @Override
    public Customer updateCustomerEmail(int customerId, String newEmail) throws CustomerNotFoundException, SQLException {
        String updateQuery = "UPDATE customer SET email = ? WHERE customer_id = ?";
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        PreparedStatement updateStatement = connection.prepareStatement(updateQuery);

        updateStatement.setString(1, newEmail);
        updateStatement.setInt(2, customerId);
        int rowsUpdated = updateStatement.executeUpdate();

        if (rowsUpdated == 0) {
            connection.close();
            throw new CustomerNotFoundException("No customer found with customer_id: " + customerId);
        }
        connection.close();
        updateStatement.close();
        return findCustomerById(customerId);

    }

    @Override
    public Customer findCustomerByAccountNumber(int accNum) throws CustomerNotFoundException, SQLException {
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT c.customer_id, c.name, c.email FROM customer c JOIN Accounts a ON c.customer_id = a.customer_id WHERE a.account_number = ?");
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int customerId = resultSet.getInt("customer_id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            resultSet.close();
            preparedStatement.close();
            connection.close();
            return new Customer(customerId, name, email);
        } else {
            resultSet.close();
            preparedStatement.close();
            connection.close();
            throw new CustomerNotFoundException("Customer not found for account number: " + accNum);
        }
    }

}
