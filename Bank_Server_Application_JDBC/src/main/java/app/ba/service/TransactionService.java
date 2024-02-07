package app.ba.service;

import app.ba.bean.Transaction;

import java.sql.SQLException;
import java.util.List;
public interface TransactionService {

    List<Transaction> getTransactionByType(int accNUm, String type) throws SQLException;

    List<Transaction> getAllTransactions(int accountNumber) throws SQLException;

    List<Transaction> getLast10Transactions(int accountNumber) throws SQLException;

    Transaction getTransactionByTransactionId(int accountNum, int transactionId) throws SQLException;

    List<Transaction> getPreviousDaysTransactions(int accountNumber, int days) throws SQLException;
}
