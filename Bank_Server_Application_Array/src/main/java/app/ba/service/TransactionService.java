package app.ba.service;
import app.ba.bean.Transaction;
public interface TransactionService {

    Transaction[] getTransactionByType(int accNUm, String Type);
    Transaction[] getAllTransactions(int accountNumber);
    Transaction[] getLast10Transactions(int accountNumber);
    Transaction getTransactionByTransactionId(int accountNum,int transactionId);
    Transaction[] getPreviousDaysTransactions(int accountNumber,int days);
}
