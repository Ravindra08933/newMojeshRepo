package app.ba.bean;
import java.util.Objects;
public class Customer {

    private int customerId;
    private String name;
    private String email;
    public Customer() {
    }
    public Customer(int customerId, String name,String email) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
    }
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", email=" + email + '\'';
    }
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + customerId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Customer customer = (Customer) obj;

        if (customerId != customer.customerId) return false;
        if (!Objects.equals(name, customer.name)) return false;
        return Objects.equals(email, customer.email);
    }
}
