package com.pontta.vendas.shared;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    public record ApiError(int status, String message, Map<String, String> fields) {}

    @ExceptionHandler(MethodArgumentNotValidException.class)   // @Valid falhou: 400
    public ResponseEntity<ApiError> invalid(MethodArgumentNotValidException ex) {
        Map<String, String> fields = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> fields.put(e.getField(), e.getDefaultMessage()));
        return ResponseEntity.badRequest().body(new ApiError(400, "Dados inválidos", fields));
    }

    @ExceptionHandler(NotFoundException.class)                 // 404
    public ResponseEntity<ApiError> notFound(NotFoundException ex) {
        return ResponseEntity.status(404).body(new ApiError(404, ex.getMessage(), null));
    }

    @ExceptionHandler(BusinessException.class)                 // regra de negócio: 422
    public ResponseEntity<ApiError> business(BusinessException ex) {
        return ResponseEntity.unprocessableEntity().body(new ApiError(422, ex.getMessage(), null));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)   // FK/unique: 409
    public ResponseEntity<ApiError> integrity(DataIntegrityViolationException ex) {
        return ResponseEntity.status(409)
                .body(new ApiError(409, "Registro em uso ou duplicado", null));
    }
}