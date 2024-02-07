package app.ba.dao;

import app.Generator.NumberGenerator;
import app.ba.bean.Transaction;
import app.ba.Exception.TransactionNotFoundException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import static app.ba.util.JDBCUtil.*;

public class TransactionDaoImpl implements TransactionDao {
    NumberGenerator numberGenerator = new NumberGenerator();
    @Override
    public void createTransaction(int accountNumber, String transactionType, double amount) throws SQLException {
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO transaction (transaction_id, transaction_date, transaction_type, amount, account_id) VALUES (?, CURRENT_DATE, ?, ?, ?)");

        preparedStatement.setInt(1, numberGenerator.generateNumber());
        preparedStatement.setString(2, transactionType);
        preparedStatement.setDouble(3, amount);
        preparedStatement.setInt(4, accountNumber);
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    @Override
    public List<Transaction> getAllTransactions(int accountNumber) throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM transaction WHERE account_id = ?");

        preparedStatement.setInt(1, accountNumber);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int transactionId = resultSet.getInt("transaction_id");
            LocalDate transactionDate = resultSet.getDate("transaction_date").toLocalDate();
            String transactionType = resultSet.getString("transaction_type");
            double amount = resultSet.getDouble("amount");
            Transaction transaction = new Transaction(transactionId, transactionDate, transactionType, amount);
            transactions.add(transaction);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return transactions;
    }



    @Override
    public List<Transaction> getLast10Transactions(int accountNumber) throws TransactionNotFoundException, SQLException {
        List<Transaction> transactions = getAllTransactions(accountNumber);
        int size = transactions.size();
        int startIndex = size > 10 ? size - 10 : 0;
        int endIndex = size;

        return transactions.subList(startIndex, endIndex);
    }

    @Override
    public List<Transaction> getPreviousDaysTransactions(int accountNumber, int days) throws SQLException {
        List<Transaction> allTransactions = getAllTransactions(accountNumber);
        List<Transaction> lastNDaysTransactions = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        for (Transaction transaction : allTransactions) {
            if (transaction != null && transaction.getTimestamp().isAfter(currentDate.minusDays(days))) {
                lastNDaysTransactions.add(transaction);
            }
        }

        return lastNDaysTransactions;
    }

}
