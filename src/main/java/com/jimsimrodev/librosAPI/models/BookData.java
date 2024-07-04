package com.jimsimrodev.librosAPI.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookData(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<AutoresData> autores,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count") Long numeroDeDescargas
) {


}
