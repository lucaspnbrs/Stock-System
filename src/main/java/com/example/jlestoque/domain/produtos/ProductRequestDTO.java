package com.example.jlestoque.domain.produtos;

import com.example.jlestoque.domain.documentos.DocumentRequestDTO;
import com.example.jlestoque.domain.produtos.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record ProductRequestDTO(

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

        List<DocumentRequestDTO> documentos
) {
    public ProductRequestDTO(Product produto) {
        this(
                produto.getSku(),
                produto.getNomeProduto(),
                produto.getDescricaoProduto(),
                produto.getCategoriaProduto(),
                produto.getMedidaProduto(),
                produto.getFornecedorProduto(),
                produto.getPrecoProduto(),
                produto.getLocalizacaoProduto(),
                produto.getQuantidadeProduto(),
                //produto.getValidadeProduto(),
                produto.getDocumentos() != null ?
                        produto.getDocumentos().stream().map(DocumentRequestDTO::new).collect(Collectors.toList()) : List.of()
        );
    }
}