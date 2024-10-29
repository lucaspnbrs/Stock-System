package com.example.jlestoque.controller;


import com.example.jlestoque.domain.documentos.Document;
import com.example.jlestoque.domain.documentos.DocumentRequestDTO;
import com.example.jlestoque.domain.produtos.Product;
import com.example.jlestoque.domain.produtos.ProductRequestDTO;
import com.example.jlestoque.domain.produtos.ProductResponseDTO;
import com.example.jlestoque.repositories.DocumentRepository;
import com.example.jlestoque.repositories.ProductRepository;

import org.apache.coyote.Response;
import org.hibernate.persister.collection.mutation.UpdateRowsCoordinator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/products")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private DocumentRepository documentRepository;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<Product> listProduct = repository.findAll();
        List<ProductResponseDTO> productResponseList = listProduct.stream()
                .map(ProductResponseDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productResponseList);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> cadastrarProduto(@RequestBody ProductRequestDTO productRequestDTO) {
        Product product = new Product(productRequestDTO);

        // Criar e associar os documentos, se existirem
        if (productRequestDTO.documentos() != null) {
            List<Document> documents = productRequestDTO.documentos().stream()
                    .map(Document::new)
                    .peek(doc -> doc.setProduct(product))
                    .collect(Collectors.toList());
            product.setDocuments(documents);
        }

        repository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductResponseDTO(product));
    }

    //Atualizar somente o campo de Preço do produto
    @PatchMapping("/{id}/precoProduto")
    public ResponseEntity<ProductResponseDTO> atualizarPrecoProduto(@PathVariable Long id, @RequestBody BigDecimal novoPreco){
        return repository.findById(id).map(product -> {
            product.setPrecoProduto(novoPreco);
            repository.save(product);
            return ResponseEntity.ok(new ProductResponseDTO(product));
        }).orElse(ResponseEntity.notFound().build());
    }

    //Atualizar somente o campo de nome do Produto
    @PatchMapping("/{id}/nomeProduto")
    public ResponseEntity<ProductResponseDTO> atualizarNomeProduto(@PathVariable Long id, @RequestBody String novoNome) {
        return repository.findById(id).map(product -> {
            product.setNomeProduto(novoNome);
            repository.save(product);
            return ResponseEntity.ok(new ProductResponseDTO(product));
        }).orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/descricaoProduto")
    public ResponseEntity<ProductResponseDTO> atualizarDescricaoProduto(@PathVariable Long id, @RequestBody String novaDescricao) {
        return repository.findById(id).map(product -> {
            product.setDescricaoProduto(novaDescricao);
            repository.save(product);
            return ResponseEntity.ok(new ProductResponseDTO(product));
        }).orElse(ResponseEntity.notFound().build());
    }

  @PatchMapping("/{id}/medidaProduto")
  public ResponseEntity<ProductResponseDTO> atualizarMedidaProduto(@PathVariable Long id, @RequestBody float novaMedida){
        return repository.findById(id).map(product -> {
            product.setMedidaProduto(novaMedida);
            repository.save(product);
            return ResponseEntity.ok(new ProductResponseDTO(product));
        }).orElse(ResponseEntity.notFound().build());
  }

  @PatchMapping("/{id}/fornecedorProduto")
  public ResponseEntity<ProductResponseDTO> atualizarFornecedorProduto(@PathVariable Long id, @RequestBody String novoFornecedor) {
        return repository.findById(id).map(product -> {
            product.setFornecedorProduto(novoFornecedor);
            repository.save(product);
            return ResponseEntity.ok(new ProductResponseDTO(product));
        }).orElse(ResponseEntity.notFound().build());
  }

  @PatchMapping("/{id}/localizacaoProduto")
  public ResponseEntity<ProductResponseDTO> atualizarLocalizacaoProduto(@PathVariable Long id, @RequestBody String novaLocalizacao) {
        return repository.findById(id).map(product -> {
            product.setLocalizacaoProduto(novaLocalizacao);
            repository.save(product);
            return ResponseEntity.ok(new ProductResponseDTO(product));
        }).orElse(ResponseEntity.notFound().build());
  }

  @PatchMapping("/{id}/quantidadeProduto")
  public ResponseEntity<ProductResponseDTO> atualizarQuantidadeProduto(@PathVariable Long id, @RequestBody int novaQuantidade){
        return repository.findById(id).map(product -> {
            product.setQuantidadeProduto(novaQuantidade);
            repository.save(product);
            return ResponseEntity.ok(new ProductResponseDTO(product));
        }).orElse(ResponseEntity.notFound().build());
  }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}