package com.pontta.vendas.customers;

public record CustomerResponse(Long id, String name, String email) {
    public static CustomerResponse from (Customers c) {
        return new CustomerResponse( c.getId(), c.getName(), c.getEmail());
    }
}
