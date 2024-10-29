package com.example.jlestoque.repositories;

import com.example.jlestoque.domain.produtos.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface    ProductRepository extends JpaRepository<Product, Long> {
}
