package app.ba.dao;
import app.ba.bean.Transaction;
public interface TransactionDao {

    Transaction[] getAllTransactions(int accountNumber);
    Transaction[] getLast10Transactions(int accountNumber);
    Transaction[] getPreviousDaysTransactions(int accountNumber,int days);
}
