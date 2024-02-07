package app.ba.util;
import app.Generator.NumberGenerator;
import app.ba.Exception.AccountNotFoundException;
import app.ba.bean.Account;
import app.ba.bean.Customer;
import app.ba.bean.Transaction;
import java.time.LocalDate;
import java.util.*;
public class ArrayUtil {

    private static final int INITIAL_SIZE = 10;
    public static Set<Customer> customers = new HashSet<>(INITIAL_SIZE);
    public static Set<Account> accounts = new HashSet<>(INITIAL_SIZE);

    static {
        System.out.println("Hello");
        customers.add(new Customer(1001, "James", "James@gmail.com"));
        customers.add(new Customer(1002, "Parley", "parley@hotmail.com"));
        customers.add(new Customer(1003, "Steve", "steve@outlook.com"));

        accounts.add(new Account(121, 500, "Savings", customers.iterator().next()));
        accounts.add(new Account(122, 500, "Savings", customers.iterator().next()));
        accounts.add(new Account(123, 500, "Current", customers.iterator().next()));
        HashMap<Integer, List<Transaction>> transactionMap1 = new HashMap<>();
        List<Transaction> transactions = new ArrayList<>(10);
        Transaction transaction = new Transaction(new NumberGenerator().generateNumber(), LocalDate.now().minusDays(2), "Deposit", 500);
        transactions.add(transaction);
        transactionMap1.put(121, transactions);
        Iterator<Account> iterator1 = accounts.iterator();
        while (iterator1.hasNext()) {
            Account account = iterator1.next();
            if (account.getAccountNumber() == 121) {
                account.setTransactions(transactionMap1);
            }
        }

        HashMap<Integer, List<Transaction>> transactionMap2 = new HashMap<>();
        List<Transaction> transactions1 = new ArrayList<>(10);
        Transaction transaction1 = new Transaction(new NumberGenerator().generateNumber(), LocalDate.now().minusDays(2), "Deposit", 500);
        transactions1.add(transaction1);
        transactionMap2.put(122, transactions1);
        Iterator<Account> iterator2 = accounts.iterator();
        while (iterator2.hasNext()) {
            Account account = iterator2.next();
            if (account.getAccountNumber() == 122) {
                account.setTransactions(transactionMap2);
            }
        }
        HashMap<Integer, List<Transaction>> transactionMap3 = new HashMap<>();
        List<Transaction> transactions2 = new ArrayList<>(10);
        Transaction transaction2 = new Transaction(new NumberGenerator().generateNumber(), LocalDate.now().minusDays(2), "Initial Deposit", 500);
        transactions2.add(transaction2);
        transactionMap3.put(123, transactions2);
        Iterator<Account> iterator3 = accounts.iterator();
        while (iterator3.hasNext()) {
            Account account = iterator3.next();
            if (account.getAccountNumber() == 123) {
                account.setTransactions(transactionMap3);
            }
        }
    }
    public static void createAccount(int accountNumber, double initialBalance, String accountType, Customer customer) {
        Account newAccount = new Account(accountNumber, initialBalance, accountType, customer);
        Transaction transaction = new Transaction(new NumberGenerator().generateNumber(), LocalDate.now(), "Deposit", initialBalance);
        List<Transaction> transactions = new ArrayList<>(10);
        transactions.add(transaction);
        HashMap<Integer, List<Transaction>> newAccountTransactions = new HashMap<>();
        newAccountTransactions.put(accountNumber, transactions);
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
        Customer customer = accounts.stream().filter(account -> account != null && account.getAccountNumber() == accNum).findFirst().map(Account::getCustomer).orElse(null);

        // Find the corresponding customer in the customers set
        if (customer != null) {
            // Update the customer's name
            customer.setName(name);

            // Update the customer in the accounts set
            accounts.removeIf(account -> account != null && account.getAccountNumber() == accNum);
            accounts.add(new Account(accNum, 0, "", customer));

            // Update the customer in the customers set
            customers.removeIf(c -> c != null && c.getCustomerId() == customer.getCustomerId());
            customers.add(customer);

            return customer;
        }

        return null;
    }


    public static Customer updateAccountEmail(int accNum, String email) {
        Customer customer = null;
        for (Account account : accounts) {
            if (account != null && account.getAccountNumber() == accNum) {
                customer = account.getCustomer();
                break;
            }
        }

        if (customer != null) {
            customer.setEmail(email);
            for (Account account : accounts) {
                if (account != null && account.getAccountNumber() == accNum) {
                    account.setCustomer(customer);
                    break;
                }
            }
            for (Customer c : customers) {
                if (c != null && c.equals(customer)) {
                    customers.remove(c);
                    customers.add(customer);
                    break;
                }
            }
            return customer;
        }
        return null;
    }


    private static int findAccountIndexByNumber(int accountNumber) {
        int index = 0;
        for (Account account : accounts) {
            if (account != null && account.getAccountNumber() == accountNumber) {
                return index;
            }
            index++;
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

        if (customer != null) {
            int index = 0;
            for (Customer c : customers) {
                if (c != null && c.equals(customer)) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

}
