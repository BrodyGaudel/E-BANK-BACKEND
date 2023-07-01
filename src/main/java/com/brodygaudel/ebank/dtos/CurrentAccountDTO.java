package com.brodygaudel.ebank.dtos;

import com.brodygaudel.ebank.enums.AccountStatus;

import java.math.BigDecimal;
import java.util.Date;

public class CurrentAccountDTO extends BankAccountDTO{
    private String id;
    private BigDecimal balance;
    private Date createdAt;
    private AccountStatus status;
    private CustomerDTO customerDTO;
    private double overDraft;

    public CurrentAccountDTO() {
        super();
    }

    public CurrentAccountDTO(String type, String id, BigDecimal balance, Date createdAt, AccountStatus status, CustomerDTO customerDTO, double overDraft) {
        super(type);
        this.id = id;
        this.balance = balance;
        this.createdAt = createdAt;
        this.status = status;
        this.customerDTO = customerDTO;
        this.overDraft = overDraft;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public double getOverDraft() {
        return overDraft;
    }

    public void setOverDraft(double overDraft) {
        this.overDraft = overDraft;
    }

    @Override
    public String toString() {
        return "CurrentAccountDTO{" +
                "id='" + id + '\'' +
                ", balance=" + balance +
                ", createdAt=" + createdAt +
                ", status=" + status +
                ", customerDTO=" + customerDTO +
                ", overDraft=" + overDraft +
                '}';
    }
}
