package app.ba.dao;

import app.ba.bean.Transaction;
import java.util.List;

public interface TransactionDao {

    List<Transaction> getAllTransactions(int accountNumber);
    List<Transaction> getLast10Transactions(int accountNumber);
    List<Transaction> getPreviousDaysTransactions(int accountNumber, int days);
}
