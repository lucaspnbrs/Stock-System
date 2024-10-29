package com.example.jlestoque.domain.documentos;

import com.example.jlestoque.domain.produtos.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "documents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    private String nomeArquivo;

    private String tipoArquivo;

    private String urlArquivo;

    private LocalDateTime dataUpload;

    public Document(DocumentRequestDTO documento) {
        this.tipoArquivo = documento.tipoArquivo();
        this.nomeArquivo = documento.nomeArquivo();
        this.urlArquivo = documento.urlArquivo();
        this.dataUpload = LocalDateTime.now();
    }


    public void setProduct(Product product) {
        this.product = product;
    }
}
