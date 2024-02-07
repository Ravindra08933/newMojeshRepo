package app.ba.util;
import app.Generator.NumberGenerator;
import app.ba.Exception.AccountNotFoundException;
import app.ba.bean.Account;
import app.ba.bean.Customer;
import app.ba.bean.Transaction;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArrayUtil {

    private static final int INITIAL_SIZE = 10;

    public static List<Customer> customers = new ArrayList<>(INITIAL_SIZE);
    public static List<Account> accounts = new ArrayList<>(INITIAL_SIZE);

    static {
        customers.add(new Customer(1001, "James", "James@gmail.com"));
        customers.add(new Customer(1002, "Parley", "parley@hotmail.com"));
        customers.add(new Customer(1003, "Steve", "steve@outlook.com"));

        accounts.add(new Account(121, 500, "Savings", customers.get(0)));
        accounts.add(new Account(122, 500, "Savings", customers.get(1)));
        accounts.add(new Account(123, 500, "Current", customers.get(2)));
        HashMap<Integer,List<Transaction>> transactionMap1 = new HashMap<>();
        List<Transaction> transactions = new ArrayList<>(10);
        Transaction transaction = new Transaction(new NumberGenerator().generateNumber(), LocalDate.now().minusDays(2), "Deposit", 500);
        transactions.add(transaction);
        transactionMap1.put(121,transactions);
        accounts.get(0).setTransactions(transactionMap1);

        HashMap<Integer,List<Transaction>> transactionMap2 = new HashMap<>();
        List<Transaction> transactions1 = new ArrayList<>(10);
        Transaction transaction1 = new Transaction(new NumberGenerator().generateNumber(), LocalDate.now().minusDays(2), "Deposit", 500);
        transactions1.add(transaction1);
        transactionMap2.put(122,transactions1);
        accounts.get(1).setTransactions(transactionMap2);


        HashMap<Integer,List<Transaction>> transactionMap3 = new HashMap<>();
        List<Transaction> transactions2 = new ArrayList<>(10);
        Transaction transaction2 = new Transaction(new NumberGenerator().generateNumber(), LocalDate.now().minusDays(2), "Initial Deposit", 500);
        transactions2.add(transaction2);
        transactionMap3.put(123,transactions2);
        accounts.get(2).setTransactions(transactionMap3);
    }

    public static void createAccount(int accountNumber, double initialBalance, String accountType, Customer customer) {
            Account newAccount = new Account(accountNumber, initialBalance, accountType, customer);
            Transaction transaction = new Transaction(new NumberGenerator().generateNumber(), LocalDate.now(), "Deposit", initialBalance);
            List<Transaction> transactions = new ArrayList<>(10);
            transactions.add(transaction);
            HashMap<Integer,List<Transaction>> newAccountTransactions = new HashMap<>();
            newAccountTransactions.put(accountNumber,transactions);
            newAccount.setTransactions(newAccountTransactions);
            accounts.add(newAccount);
            customers.add(customer);
            System.out.println("Account created successfully!");
        }

    public static String deleteAccount(int accountNumber) throws AccountNotFoundException {
        Customer customer = null;
        int accountIndex = findAccountIndexByNumber(accountNumber);
        for (Account account : accounts) {
            if (account != null && account.getAccountNumber() == accountNumber) {
                customer = account.getCustomer();
            }
        }
        int index = findCustomerIndexById(customer.getCustomerId());
        customers.remove(index);

        if (accountIndex != -1) {
            accounts.remove(accountIndex);
            return "Account with number " + accountNumber + " deleted successfully!";
        } else {
            return "Account with number " + accountNumber + " not found.";
        }
    }

    public static Customer updateAccount(int accNum, String name) {
        Customer customer = null;
        for (Account account : accounts) {
            if (account != null && account.getAccountNumber() == accNum) {
                customer = account.getCustomer();
            }
        }
        if (customer != null) {
            customer.setName(name);
            int index = findAccountIndexByNumber(accNum);
            accounts.get(index).setCustomer(customer);
            int index1 = findCustomerIndexById(customer.getCustomerId());
            customers.set(index1, customer);
            return customer;
        }
        return null;
    }

    public static Customer updateAccountEmail(int accNum, String email) {
        Customer customer = null;
        for (Account account : accounts) {
            if (account != null && account.getAccountNumber() == accNum) {
                customer = account.getCustomer();
            }
        }
        if (customer != null) {
            customer.setEmail(email);
            int index = findAccountIndexByNumber(accNum);
            accounts.get(index).setCustomer(customer);
            int index1 = findCustomerIndexById(customer.getCustomerId());
            customers.set(index1, customer);
            return customer;
        }
        return null;
    }

    private static int findAccountIndexByNumber(int accountNumber) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i) != null && accounts.get(i).getAccountNumber() == accountNumber) {
                return i;
            }
        }
        return -1;
    }


    private static Customer findCustomerById(int customerId) {
        for (Customer customer : customers) {
            if (customer != null && customer.getCustomerId() == customerId) {
                return customer;
            }
        }
        return null;
    }

    private static int findCustomerIndexById(int num) {
        Customer customer = findCustomerById(num);
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i) != null && customers.get(i).getCustomerId() == customer.getCustomerId())
                return i;
        }
        return -1;
    }
}
