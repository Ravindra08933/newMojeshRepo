package app.ba.service;
import app.Generator.NumberGenerator;
import app.ba.exception.AccountNotFoundException;
import app.ba.bean.Account;
import app.ba.bean.Customer;
import app.ba.bean.Transaction;
import app.ba.dao.AccountDaoImpl;
import java.time.LocalDate;
import static app.ba.dao.TransactionDaoImpl.findAvailableTransactionIndex;
public class AccountServiceImpl implements AccountService {
    AccountDaoImpl accountDao = new AccountDaoImpl();
    public static NumberGenerator numberGenerator = new NumberGenerator();

    @Override
    public double checkBalance(int accountNumber) throws AccountNotFoundException
    {
            Account account = accountDao.findAccountByNumber(accountNumber);
            if (account != null)
            {
                return account.getCurrentBalance();
            }
                throw new AccountNotFoundException("Account not found for account number: " + accountNumber);
    }
    @Override
    public String deposit(int accountNumber, double amount) throws AccountNotFoundException  {
        if(amount<0)
        {
            return "Amount should be greater than 0";
        }
        else {
                Account account = accountDao.findAccountByNumber(accountNumber);
                if (account != null) {
                    double newBalance = account.getCurrentBalance() + amount;
                    account.setCurrentBalance(newBalance);
                    recordTransaction(account, "Deposit", amount);
                    return "Deposit successful. New balance: " + newBalance;
                }
                throw new AccountNotFoundException("Account not found for account number: " + accountNumber);

        }
    }
    @Override
    public String withdraw(int accountNumber, double amount) throws AccountNotFoundException {
        if(amount<0)
        {
            return "Amount should be greater than 0";
        }
        else
        {
                Account account = accountDao.findAccountByNumber(accountNumber);
                if (account != null)
                {
                    double currentBalance = account.getCurrentBalance();
                    if (currentBalance - amount >= 0)
                    {
                        double newBalance = currentBalance - amount;
                        account.setCurrentBalance(newBalance);
                        recordTransaction(account, "Withdrawal", -amount);
                        return "Withdrawal successful. New balance: " + newBalance;
                    }
                    else {
                        return "Insufficient amount";
                    }

                }
            throw new AccountNotFoundException("Account not found for account number: " + accountNumber);
        }
    }
    @Override
    public String transfer(int fromAccountNumber, int toAccountNumber, double amount) throws AccountNotFoundException {
        if(amount<0)
        {
            return "Amount should be greater than 0";
        }
        else {
            Account fromAccount = accountDao.findAccountByNumber(fromAccountNumber);
            Account toAccount = accountDao.findAccountByNumber(toAccountNumber);
            if (fromAccount != null && toAccount != null)
            {
                double fromBalance = fromAccount.getCurrentBalance();
                if (fromBalance - amount >= 0)
                {
                    double toBalance = toAccount.getCurrentBalance();
                    fromAccount.setCurrentBalance(fromBalance - amount);
                    toAccount.setCurrentBalance(toBalance + amount);
                    recordTransaction(fromAccount, "Transfer to " + toAccountNumber, -amount);
                    recordTransaction(toAccount, "Transfer from " + fromAccountNumber, amount);
                    return "Transfer successful.";
                }
                else
                {
                    return "Insufficient funds for transfer.";
                }
            }
            throw new AccountNotFoundException("Account not found for account number: " + fromAccountNumber);
        }
    }

    public static void recordTransaction(Account account, String description, double amount) {
        Transaction[] transactions = account.getTransactions();
        int index = findAvailableTransactionIndex(transactions);
        if(index == -1)
        {
            int s = transactions.length;
            int newSize = 2*transactions.length;
            Transaction[] newArray = new Transaction[newSize];
            System.arraycopy(transactions, 0, newArray, 0, transactions.length);
            transactions = newArray;
            index = s;
        }
        Transaction transaction = new Transaction(numberGenerator.generateNumber()
                ,
                LocalDate.now(),
                description,
                amount
        );
        transactions[index] = transaction;
    }

    @Override
    public Account createAccount(String name,String email,double amount) {
        Customer customer = new Customer(numberGenerator.generateNumber(),name,email);
       return  accountDao.createAccount(customer,amount);

    }
    @Override
    public Customer updateAccount(int accountNumber, String Name) {
        return accountDao.updateAccount(accountNumber,Name);
    }
    @Override
    public Customer updateAccountEmail(int accountNumber, String Name) {
        return accountDao.updateAccountEmail(accountNumber,Name);
    }
    @Override
    public String deleteAccount(int accountNumber) throws AccountNotFoundException {
        return accountDao.deleteAccount(accountNumber);
    }


}
