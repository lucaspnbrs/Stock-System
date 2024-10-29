package com.example.jlestoque.domain.produtos;

import com.example.jlestoque.domain.documentos.DocumentResponseDTO;
import com.example.jlestoque.domain.produtos.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record ProductResponseDTO(

        String sku,

        String nomeProduto,

        String descricaoProduto,

        String categoriaProduto,

        float medidaProduto,

        String fornecedorProduto,

        BigDecimal precoProduto,

        String localizacaoProduto,

        int quantidadeProduto,

        //LocalDate validadeProduto,

        List<DocumentResponseDTO> documentos) {

    public ProductResponseDTO(Product product) {
        this(
                product.getSku(),
                product.getNomeProduto(),
                product.getDescricaoProduto(),
                product.getCategoriaProduto(),
                product.getMedidaProduto(),
                product.getFornecedorProduto(),
                product.getPrecoProduto(),
                product.getLocalizacaoProduto(),
                product.getQuantidadeProduto(),
                //product.getValidadeProduto(),
                product.getDocumentos().stream()
                        .map(DocumentResponseDTO::new)
                        .toList()
        );
    }
}
