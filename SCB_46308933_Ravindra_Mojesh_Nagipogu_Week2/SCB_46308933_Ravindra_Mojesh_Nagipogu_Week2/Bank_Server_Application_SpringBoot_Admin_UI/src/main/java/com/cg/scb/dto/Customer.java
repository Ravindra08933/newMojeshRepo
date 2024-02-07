package com.cg.scb.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Customer
{

    private int customerId;
    private String name;
    private String email;
    private String password;

    @Override
    public String toString() {
        return
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", email='" + email;
    }
}
