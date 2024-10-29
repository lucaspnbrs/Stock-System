package com.example.jlestoque.domain.produtos;

import com.example.jlestoque.domain.documentos.Document;
import com.example.jlestoque.domain.documentos.DocumentRequestDTO;
import com.example.jlestoque.domain.produtos.ProductRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sku;

    private String nomeProduto;

    private String descricaoProduto;

    private String categoriaProduto;

    private float medidaProduto;

    private String fornecedorProduto;

    private BigDecimal precoProduto;

    private String localizacaoProduto;

    private int quantidadeProduto;
    //private LocalDate validadeProduto;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Document> documentos = new ArrayList<>();

    public Product(ProductRequestDTO data) {
        this.sku = data.sku();
        this.nomeProduto = data.nomeProduto();
        this.descricaoProduto = data.descricaoProduto();
        this.categoriaProduto = data.categoriaProduto();
        this.medidaProduto = data.medidaProduto();
        this.fornecedorProduto = data.fornecedorProduto();
        this.precoProduto = data.precoProduto();
        this.localizacaoProduto = data.localizacaoProduto();
        this.quantidadeProduto = data.quantidadeProduto();
        //this.validadeProduto = data.validadeProduto();


        if (data.documentos() != null) {
            for (DocumentRequestDTO docDTO : data.documentos()) {
                Document document = new Document(docDTO);
                document.setProduct(this);
                this.documentos.add(document);
            }
        }
    }

    public void setDocuments(List<Document> documents) {
    }
}