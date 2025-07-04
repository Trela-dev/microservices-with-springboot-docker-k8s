package dev.trela.accounts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;
    private String name;
    private String email;
    private String mobileNumber;

    public Customer(String name, String email, String mobileNumber) {
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;

    }

}
