package com.example.jlestoque.domain.documentos;


import com.example.jlestoque.domain.documentos.Document;

import java.time.LocalDateTime;

public record DocumentResponseDTO(
        String nomeArquivo,
        String tipoArquivo,
        String urlArquivo,
        LocalDateTime dataUpload
) {

    public DocumentResponseDTO(Document document) {
        this(
                document.getNomeArquivo(),
                document.getTipoArquivo(),
                document.getUrlArquivo(),
                document.getDataUpload()
        );
    }
}
