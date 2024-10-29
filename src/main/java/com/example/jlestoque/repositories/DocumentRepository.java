package com.example.jlestoque.repositories;

import com.example.jlestoque.domain.documentos.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
