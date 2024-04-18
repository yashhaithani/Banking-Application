package com.Aithani.BankingApp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor   // generate a constructor with no parameters.
@AllArgsConstructor  // generates a constructor with one parameter for every field in the class
@Table(name="accounts")
@Entity
public class Account
{
    // Creating Table in MySQL by writing command here
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account_holder_name")
    private String accountHolderName;
    private  double balance;
}
