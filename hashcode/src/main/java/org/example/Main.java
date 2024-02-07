package org.example;
import java.util.Objects;

class Person {
    private String name;
    private int age;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    // Override equals() method
    @Override
    public boolean equals(Object o) {
        if (this == o)
        {
            System.out.println("1");
            return true;}
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name);
    }

    // Override hashCode() method
    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}

public class Main {
    public static void main(String[] args) {
        Person person1 = new Person("John", 25);
        Person person2 = new Person("John", 25);

        // Using equals() method
        System.out.println("Are persons equal? " + person1.equals(person2));

        // Using hashCode() method
        System.out.println("HashCode of person1: " + person1.hashCode());
        System.out.println("HashCode of person2: " + person2.hashCode());
    }
}
