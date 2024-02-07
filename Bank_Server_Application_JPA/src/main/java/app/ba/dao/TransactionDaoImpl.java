package app.ba.dao;

import app.ba.Exception.TransactionNotFoundException;
import app.ba.bean.Account;
import app.ba.bean.Transaction;
import jakarta.persistence.*;
import app.ba.util.JPAUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionDaoImpl implements TransactionDao {

    EntityManager em = JPAUtil.getEntityManager();
    @Override
    public void recordTransaction(Account account) {
        EntityManager entityManager = JPAUtil.getEntityManager();
            entityManager.getTransaction().begin();
            Transaction depositTransaction = new Transaction(LocalDate.now(), "Deposit", account.getCurrentBalance(), account);
            entityManager.persist(depositTransaction);
            entityManager.getTransaction().commit();
    }

    @Override
    public List<Transaction> getAllTransactions(int account_number) throws TransactionNotFoundException {
        String jpql = "SELECT t FROM Transaction t WHERE t.account.accountNumber = :account_number";
        TypedQuery<Transaction> query = em.createQuery(jpql, Transaction.class);
        query.setParameter("account_number", account_number);

        List<Transaction> transactions = query.getResultList();
        if (transactions.size() == 0)
            throw new TransactionNotFoundException("No transactions found for account number: " + account_number);

        return transactions;
    }
    @Override
    public List<Transaction> getLast10Transactions(int account_number) throws TransactionNotFoundException {
        List<Transaction> transactions = getAllTransactions(account_number);
        if(transactions.size()!=0) {
            int size = transactions.size();
            int startIndex = size > 10 ? size - 10 : 0;
            int endIndex = size;
            transactions.subList(startIndex, endIndex);
        }
        throw new TransactionNotFoundException("No Transaction found in last 10 days");
    }
    @Override
    public List<Transaction> getPreviousDaysTransactions(int account_number, int days) throws TransactionNotFoundException {
        List<Transaction> allTransactions = getAllTransactions(account_number);
        List<Transaction> lastNDaysTransactions = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        for (Transaction transaction : allTransactions) {
            if (transaction != null && transaction.getTransactionDate().isAfter(currentDate.minusDays(days))) {
                lastNDaysTransactions.add(transaction);
            }
        }
        if (lastNDaysTransactions.size() != 0)
            return lastNDaysTransactions;
        throw new TransactionNotFoundException("No Transactions found in last " + days +" days");
    }
}
