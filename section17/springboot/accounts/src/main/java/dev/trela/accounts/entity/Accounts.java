package dev.trela.accounts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class Accounts extends BaseEntity {
    private Long customerId;
    @Id
    private Long accountNumber;

    private String accountType;

    private String branchAddress;

    private Boolean communicationSw = false;

    public Accounts(Long accountNumber,
                    String accountType,
                    String branchAddress) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.branchAddress = branchAddress;
    }
}
