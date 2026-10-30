package com.pontta.vendas.customers;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepository extends JpaRepository<Customers, Long> {
    boolean existsByEmail(String email);

    void delete(Customers customers);

}
