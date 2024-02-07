package app.ba.dao;

import app.ba.bean.Account;
import app.ba.bean.Transaction;
import app.ba.Exception.TransactionNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TransactionDaoImpl implements TransactionDao {
    @Override
    public List<Transaction> getAllTransactions(int accountNumber) {
        Account account = new AccountDaoImpl().findAccountByNumber(accountNumber);
        if (account != null) {
            HashMap<Integer,List<Transaction>> transactions =  account.getTransactions();
            return transactions.get(accountNumber);
        } else {
            System.out.println("Invalid account number.");
            return null;
        }
    }

    @Override
    public List<Transaction> getLast10Transactions(int accountNumber) throws TransactionNotFoundException {
        List<Transaction> transactions = getAllTransactions(accountNumber);
        int size = transactions.size();
        int startIndex = size > 10 ? size - 10 : 0;
        int endIndex = size;

        return transactions.subList(startIndex, endIndex);
    }

    @Override
    public List<Transaction> getPreviousDaysTransactions(int accountNumber, int days) {
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
