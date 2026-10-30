package com.pontta.vendas.customers;

import org.springframework.stereotype.Service;

import com.pontta.vendas.shared.BusinessException;
import com.pontta.vendas.shared.NotFoundException;

import jakarta.transaction.Transactional;

@Service 
public class CustomersService {
    private final CustomersRepository repository; 

    public CustomersService(CustomersRepository repository) {
        this.repository = repository;
    } 

    @Transactional
    public CustomerResponse create( CustomersRequest r ){
        if (repository.existsByEmail(r.email())) throw new BusinessException("E-mail já cadastrado.");
        return CustomerResponse.from(repository.save(new Customers(, r.name(), r.email())));
    }

    @Transactional 
    public CustomerResponse update(Long id, CustomersRequest r){
        Customers c = find(id);
        c.update(r.name(), r.email());
        return CustomerResponse.from(c);
    }

    @Transactional 
    public void delete(Long id){
        repository.delete(find(id));
    }

    private Customers find(Long id) {
        return ((Object) repository.findById(id)).orElseThrow(() -> new NotFoundException("Cliente não encontrado."));
    }

    public CustomerResponse get(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

}
