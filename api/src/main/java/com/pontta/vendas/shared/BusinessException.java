package com.pontta.vendas.shared;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) { super(message); }
}