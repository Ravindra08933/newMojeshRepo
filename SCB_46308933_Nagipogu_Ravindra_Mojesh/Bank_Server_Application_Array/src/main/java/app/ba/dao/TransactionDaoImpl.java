package app.ba.dao;
import app.ba.bean.Account;
import app.ba.bean.Transaction;
import app.ba.exception.AccountNotFoundException;
import app.ba.exception.TransactionNotFoundException;
import java.time.LocalDate;
public class TransactionDaoImpl implements TransactionDao
{
    @Override
    public Transaction[] getAllTransactions(int accountNumber) throws AccountNotFoundException {
        Account account = new AccountDaoImpl().findAccountByNumber(accountNumber);
        if (account != null) {
            return account.getTransactions();
        } else {
           throw new AccountNotFoundException("Account Not Found");
        }
    }
    @Override
    public Transaction[] getLast10Transactions(int accountNumber) throws TransactionNotFoundException {
        int j = -1;
        Transaction[] transactions1 = new Transaction[10];
        Transaction[] transactions = getAllTransactions(accountNumber);
        int index = findAvailableTransactionIndex(transactions);
        if(transactions.length>10)
        {
            for(int i = index-1;i>=index-10;i--)
            {
                j++;
                transactions1[j] = transactions[i];
            }
        }
        else
        {
            for(int i = index-1;i>=0;i--)
            {
                j++;
                transactions1[j] = transactions[i];
            }
        }
        return transactions1;
    }
    @Override
    public Transaction[] getPreviousDaysTransactions(int accountNumber,int days) {
        Transaction[] last10DaysTransactions = new Transaction[getAllTransactions(accountNumber).length];
        int count = 0;
        LocalDate currentDate = LocalDate.now();
        for (Transaction transaction : getAllTransactions(accountNumber)) {
            if (transaction!=null && transaction.getTimestamp().isAfter(currentDate.minusDays(days))) {
                last10DaysTransactions[count++] = transaction;
            }
        }
        Transaction[] result = new Transaction[count];
        System.arraycopy(last10DaysTransactions, 0, result, 0, count);
        return result;
    }
    public static int findAvailableTransactionIndex(Transaction[] transactions) {
        for (int i = 0; i < transactions.length; i++) {
            if (transactions[i] == null) {
                return i;
            }
        }
        return -1;
    }
}
