package com.example.banksystem.Controller;

import com.example.banksystem.ApiResponse.ApiResponse;
import com.example.banksystem.Model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/bank")
public class CustomerController {

    ArrayList<Customer> customers = new ArrayList<>();


    @GetMapping("/get")
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    @PostMapping("/add")
    public ApiResponse addCustomer(@RequestBody Customer customer) {
        customers.add(customer);
        return new ApiResponse("customer added");
    }


    @PutMapping("/update/{index}")
    public ApiResponse updateCustomer(@PathVariable int index, @RequestBody Customer customer) {
        customers.set(index, customer);
        return new ApiResponse("customer updated");
    }


    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteCustomer(@PathVariable int index) {
        customers.remove(index);
        return new ApiResponse("customer deleted");
    }


    @PutMapping("/deposit/{index}/{amount}")
    public ApiResponse depositMoney(@PathVariable int index, @PathVariable double amount) {

        double balance = customers.get(index).getBalance();
        customers.get(index).setBalance(balance + amount);
        return new ApiResponse(amount + " deposited successfully to customer " + customers.get(index).getUsername());
    }


    @PutMapping("/withdraw/{index}/{amount}")
    public ApiResponse withdrawMoney(@PathVariable int index, @PathVariable double amount) {
        double balance = customers.get(index).getBalance();
        if (customers.get(index).getBalance() > 0){
            customers.get(index).setBalance(balance - amount);
            return new ApiResponse(amount + " withdrew successfully from customer " + customers.get(index).getUsername() );
        } else
            return new ApiResponse(customers.get(index).getUsername() + " does not have enough money");
    }

}
