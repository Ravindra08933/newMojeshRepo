import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
public class Customer {
    String name;
    int id;
    public Customer(String name, int id) {
        this.name = name;
        this.id = id;
    }
    public Customer() {
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && Objects.equals(name, customer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
    public static void main(String[] args) {
//        HashSet<Customer> hashSet = new HashSet<>();
//        HashSet<Integer> hashSet1 = new HashSet<>();
//        hashSet.add(new Customer("niga",1));
//        hashSet.add(new Customer("niga",1));
//        System.out.println(hashSet);
//        hashSet1.add(1);
//        hashSet1.add(1);
//        System.out.println(hashSet1);
        Map<Integer,Integer> map = new HashMap<>();
        map.put(1,7);
        map.put(2,8);
        map.put(3,6);
        map.put(4,5);
        map.putIfAbsent(4,61);
        map.computeIfPresent(3,(k,v)->null);
        map.values().forEach(System.out::print);

    }
}

