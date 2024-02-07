package app.ba.dao;

import app.ba.bean.Transaction;

import java.sql.SQLException;
import java.util.List;

public interface TransactionDao {

    void createTransaction(int accountNumber, String transactionType, double amount) throws SQLException;
    List<Transaction> getAllTransactions(int accountNumber) throws SQLException;
    List<Transaction> getLast10Transactions(int accountNumber) throws SQLException;
    List<Transaction> getPreviousDaysTransactions(int accountNumber, int days) throws SQLException;
}
