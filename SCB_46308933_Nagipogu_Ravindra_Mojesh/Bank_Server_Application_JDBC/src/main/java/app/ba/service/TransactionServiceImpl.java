package app.ba.service;

import app.ba.Exception.TransactionNotFoundException;
import app.ba.bean.Transaction;
import app.ba.dao.TransactionDao;
import app.ba.dao.TransactionDaoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {

    TransactionDao transactionDao = new TransactionDaoImpl();

    @Override
    public List<Transaction> getTransactionByType(int accNum, String type) throws SQLException {
        List<Transaction> transactions1 = new ArrayList<>();
        List<Transaction> transactions = transactionDao.getAllTransactions(accNum);
        for (Transaction transaction : transactions) {
            if (transaction.getTransactionType().equals(type)) {
                transactions1.add(transaction);
            }
        }
        return transactions1;
    }

    @Override
    public List<Transaction> getAllTransactions(int accountNumber) throws SQLException {
        return transactionDao.getAllTransactions(accountNumber);
    }

    @Override
    public List<Transaction> getLast10Transactions(int accountNumber) throws TransactionNotFoundException, SQLException {
        return transactionDao.getLast10Transactions(accountNumber);
    }

    @Override
    public Transaction getTransactionByTransactionId(int accNum, int transactionId) throws TransactionNotFoundException, SQLException {
        List<Transaction> transactions = transactionDao.getAllTransactions(accNum);
        for (Transaction transaction : transactions) {
            if (transaction != null && transaction.getTransactionId() == transactionId) {
                return transaction;
            }
        }
        throw new TransactionNotFoundException("Transaction with " + transactionId + " not found");
    }

    @Override
    public List<Transaction> getPreviousDaysTransactions(int accountNumber, int days) throws SQLException {
        return transactionDao.getPreviousDaysTransactions(accountNumber, days);
    }
}
