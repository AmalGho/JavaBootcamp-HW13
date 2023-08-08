package com.example.banksystem.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {
    private String  id,
                    username;
    private double balance;
}
