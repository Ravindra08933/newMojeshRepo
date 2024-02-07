package app.ba.bean;
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
}
