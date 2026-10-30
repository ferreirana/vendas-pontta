package com.pontta.vendas.customers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController 
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomersService service;

    public CustomerController ( CustomersService service ){
        this.service = service;
    }

    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    public CustomerResponse create (@Valid @RequestBody CustomersRequest r) {
        return service.create(r);
    }

    @GetMapping("/{id}")
    public CustomerResponse getCustomer (@PathVariable Long id, @Valid @RequestBody CustomersRequest r) {
        return service.get(id);
    }
    
    @PutMapping("/{id}")
    public CustomerResponse update (@PathVariable Long id, @RequestBody CustomersRequest r) {
        return service.update(id, r);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Long id){
        service.delete(id);
    }
    
}
