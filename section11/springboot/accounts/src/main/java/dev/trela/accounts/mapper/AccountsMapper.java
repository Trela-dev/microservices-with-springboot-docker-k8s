package dev.trela.accounts.mapper;

import dev.trela.accounts.dto.AccountsDto;
import dev.trela.accounts.entity.Accounts;

public class AccountsMapper {

    public static AccountsDto mapToAccountsDto(Accounts accounts){
        return new AccountsDto(
                accounts.getAccountNumber(),
                accounts.getAccountType(),
                accounts.getBranchAddress());
    }


    public static Accounts mapToAccounts(AccountsDto accountsDto){
        return new Accounts(
                accountsDto.accountNumber(),
                accountsDto.accountType(),
                accountsDto.branchAddress()
        );
    }

}
