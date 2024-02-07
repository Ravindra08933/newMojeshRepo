package app.ba.service;
import app.ba.exception.TransactionNotFoundException;
import app.ba.bean.Transaction;
import app.ba.dao.TransactionDao;
import app.ba.dao.TransactionDaoImpl;
public class TransactionServiceImpl implements TransactionService {

    TransactionDao transactionDao = new TransactionDaoImpl();
    @Override
    public Transaction[] getTransactionByType(int accNUm, String Type) {
        Transaction[] transactions1 = new Transaction[10];
        int i = -1;
        Transaction[] transactions = transactionDao.getAllTransactions(accNUm);
        for(Transaction transaction:transactions)
        {
            if(transaction.getTransactionType()==Type)
            {
                transactions1[++i] = transaction;
            }

        }
        return transactions1;
    }
    @Override
    public Transaction[] getAllTransactions(int accountNumber) {
        return transactionDao.getAllTransactions(accountNumber);
    }
    @Override
    public Transaction[] getLast10Transactions(int accountNumber) throws TransactionNotFoundException {
        return transactionDao.getLast10Transactions(accountNumber);
    }
    @Override
    public Transaction getTransactionByTransactionId(int accNum,int transactionId) throws TransactionNotFoundException {
        Transaction[] transactions = transactionDao.getAllTransactions(accNum);
        for(Transaction transaction:transactions)
        {
            if(transaction!=null && transaction.getTransactionId()==transactionId)
            {
                return transaction;
            }

        }
        throw new TransactionNotFoundException("Transaction with "+transactionId + " not found");
    }
    @Override
    public Transaction[] getPreviousDaysTransactions(int accountNumber,int days) {
        return transactionDao.getPreviousDaysTransactions(accountNumber,days);
    }
}
