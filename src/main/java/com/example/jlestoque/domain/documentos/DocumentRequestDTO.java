package com.example.jlestoque.domain.documentos;


import com.example.jlestoque.domain.documentos.Document;

public record DocumentRequestDTO(
        String nomeArquivo,
        String tipoArquivo,
        String urlArquivo
) {

    public DocumentRequestDTO(Document document) {
        this(document.getNomeArquivo(), document.getTipoArquivo(), document.getUrlArquivo());
    }
}