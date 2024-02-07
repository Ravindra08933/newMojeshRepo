package app.ba.service;

import app.ba.bean.Transaction;
import java.util.List;
public interface TransactionService {

    List<Transaction> getTransactionByType(int accNUm, String type);

    List<Transaction> getAllTransactions(int accountNumber);

    List<Transaction> getLast10Transactions(int accountNumber);

    Transaction getTransactionByTransactionId(int accountNum, int transactionId);

    List<Transaction> getPreviousDaysTransactions(int accountNumber, int days);
}
